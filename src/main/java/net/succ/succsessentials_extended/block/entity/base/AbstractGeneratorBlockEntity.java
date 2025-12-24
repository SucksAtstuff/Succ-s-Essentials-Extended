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
 * ============================================================
 */
public abstract class AbstractGeneratorBlockEntity
        extends BlockEntity {

    /* ================= ENERGY ================= */

    protected final ModEnergyStorage energyStorage;

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

        this.energyStorage = new ModEnergyStorage(energyCapacity, energyTransfer) {
            @Override
            public void onEnergyChanged() {
                setChanged();
                if (level != null && !level.isClientSide()) {
                    level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
                }
            }
        };

        this.maxBurnProgress = maxBurnTime;
        this.burnProgress = maxBurnTime;
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

    /**
     * How much energy this generator can push per tick.
     */
    protected abstract int getEnergyTransferRate();

    /**
     * How much energy this generator PRODUCES per tick.
     * Used exclusively for tooltip display.
     */
    public abstract int getPowerGenerationRate();

    /* ================= ABSTRACT HOOKS ================= */

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
