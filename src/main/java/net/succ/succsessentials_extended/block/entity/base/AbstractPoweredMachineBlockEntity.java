package net.succ.succsessentials_extended.block.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.succ.succsessentials_extended.util.energy.ModEnergyStorage;
import org.jetbrains.annotations.Nullable;
import net.succ.succsessentials_extended.util.MachineOutputHelper;

/**
 * ============================================================
 * AbstractPoweredMachineBlockEntity
 * ============================================================
 */
public abstract class AbstractPoweredMachineBlockEntity
        extends BlockEntity{

    /* ================= ENERGY ================= */

    protected final ModEnergyStorage energyStorage;

    /* ================= PROGRESS ================= */

    protected int progress = 0;
    protected int maxProgress = 200;
    protected int energyPerTick = 20;

    protected AbstractPoweredMachineBlockEntity(
            BlockEntityType<?> blockEntityType,
            BlockPos pos,
            BlockState state,
            int energyCapacity,
            int energyTransfer
    ) {
        super(blockEntityType, pos, state);

        this.energyStorage = new ModEnergyStorage(energyCapacity, energyTransfer) {
            @Override
            public void onEnergyChanged() {
                setChanged();
                if (level != null && !level.isClientSide()) {
                    level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
                }
            }
        };
    }

    /* ================= ENERGY ACCESS ================= */

    public IEnergyStorage getEnergyStorage(@Nullable Direction side) {
        return energyStorage;
    }

    protected boolean hasEnoughEnergy() {
        return energyStorage.getEnergyStored() >= energyPerTick;
    }

    /* ================= TICK ================= */

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (hasRecipe() && hasEnoughEnergy()) {
            progress++;
            energyStorage.extractEnergy(energyPerTick, false);

            if (progress >= maxProgress) {
                craftItem();
                progress = 0;
            }

            setLitState(true);
        } else {
            progress = 0;
            resetEnergyUsage();
            setLitState(false);
        }
    }

    protected void resetEnergyUsage() {
        energyPerTick = 20;
    }

    /* ================= ABSTRACT HOOKS ================= */

    protected abstract boolean hasRecipe();
    protected abstract void craftItem();
    protected abstract void setLitState(boolean lit);

    /* ================= SAVE / LOAD ================= */

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putInt("progress", progress);
        tag.putInt("maxProgress", maxProgress);
        tag.putInt("energy", energyStorage.getEnergyStored());
        tag.putInt("energyPerTick", energyPerTick);
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        progress = tag.getInt("progress");
        maxProgress = tag.getInt("maxProgress");
        energyPerTick = tag.getInt("energyPerTick");
        energyStorage.setEnergy(tag.getInt("energy"));
        super.loadAdditional(tag, registries);
    }

    protected boolean canOutputResult(
            ItemStackHandler handler,
            int outputSlot,
            ItemStack result
    ) {
        return MachineOutputHelper.canOutput(handler, outputSlot, result);
    }

    protected void outputResult(
            ItemStackHandler handler,
            int outputSlot,
            ItemStack result
    ) {
        MachineOutputHelper.output(handler, outputSlot, result);
    }
}
