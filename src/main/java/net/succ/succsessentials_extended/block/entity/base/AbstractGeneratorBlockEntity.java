package net.succ.succsessentials_extended.block.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.succ.succsessentials_extended.util.energy.ModEnergyStorage;
import net.succ.succsessentials_extended.util.energy.ModEnergyUtil;
import org.jetbrains.annotations.Nullable;

/**
 * ============================================================
 * AbstractGeneratorBlockEntity
 *
 * Base class for ALL generator-style machines.
 *
 * IMPORTANT:
 * ----------
 * This class is primarily designed for burn-based generators
 * (coal, biofuel, etc).
 *
 * Some machines (reactors, solar, wind) are CONTINUOUS producers.
 * Those machines MUST be allowed to override the energy storage
 * creation logic via createEnergyStorage().
 * ============================================================
 */
public abstract class AbstractGeneratorBlockEntity
        extends BlockEntity {

    /* ================= ENERGY ================= */

    // NOT final on purpose — subclasses may customize construction
    protected ModEnergyStorage energyStorage;

    /* ================= BURN STATE ================= */

    protected int burnProgress;
    protected int maxBurnProgress;
    protected boolean isBurning = false;

    protected AbstractGeneratorBlockEntity(
            BlockEntityType<?> type,
            BlockPos pos,
            BlockState state,
            int energyCapacity,
            int energyTransfer,
            int maxBurnTime
    ) {
        super(type, pos, state);

        /* ============================================================
           ENERGY STORAGE CREATION
           ============================================================

           Burn generators should be OUTPUT-ONLY.
           Continuous generators (reactors) override this method.
         */
        this.energyStorage = createEnergyStorage(energyCapacity, energyTransfer);

        this.maxBurnProgress = maxBurnTime;
        this.burnProgress = maxBurnTime;
    }

    /* ================= ENERGY FACTORY ================= */

    /**
     * Factory method for creating energy storage.
     *
     * Default behavior:
     *  - OUTPUT-ONLY storage
     *  - Correct for coal / biofuel generators
     *
     * Reactors and other continuous generators MUST override this.
     */
    protected ModEnergyStorage createEnergyStorage(int capacity, int transferRate) {
        return new ModEnergyStorage(capacity, transferRate) {
            @Override
            public void onEnergyChanged() {
                setChanged();
                if (level != null && !level.isClientSide()) {
                    level.sendBlockUpdated(
                            worldPosition,
                            getBlockState(),
                            getBlockState(),
                            3
                    );
                }
            }
        };
    }

    /* ================= ENERGY ACCESS ================= */

    public IEnergyStorage getEnergyStorage(@Nullable Direction side) {
        return energyStorage;
    }

    /* ================= TICK ================= */

    public void tick(Level level, BlockPos pos, BlockState state) {

        if (hasFuel() && !isBurning) {
            consumeFuel();
            isBurning = true;
        }

        if (isBurning) {
            burnProgress--;
            generateEnergy();

            if (burnProgress <= 0) {
                resetBurn();
            }
        }

        if (energyStorage.getEnergyStored() > 0) {
            pushEnergy();
        }

        setLitState(isBurning);
    }

    protected void resetBurn() {
        isBurning = false;
        burnProgress = maxBurnProgress;
    }

    /* ================= ENERGY PUSHING ================= */

    protected void pushEnergy() {
        if (level == null) return;

        BlockPos target = worldPosition.above();

        if (ModEnergyUtil.doesBlockHaveEnergyStorage(target, level)) {
            ModEnergyUtil.move(worldPosition, target, getEnergyTransferRate(), level);
        }
    }

    /* ================= ABSTRACT API ================= */

    protected abstract int getEnergyTransferRate();
    public abstract int getPowerGenerationRate();

    protected abstract boolean hasFuel();
    protected abstract void consumeFuel();
    protected abstract void generateEnergy();
    protected abstract void setLitState(boolean lit);

    /* ================= SAVE / LOAD ================= */

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putInt("burnProgress", burnProgress);
        tag.putInt("maxBurnProgress", maxBurnProgress);
        tag.putBoolean("isBurning", isBurning);
        tag.putInt("energy", energyStorage.getEnergyStored());
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        burnProgress = tag.getInt("burnProgress");
        maxBurnProgress = tag.getInt("maxBurnProgress");
        isBurning = tag.getBoolean("isBurning");
        energyStorage.setEnergy(tag.getInt("energy"));
        super.loadAdditional(tag, registries);
    }
}
