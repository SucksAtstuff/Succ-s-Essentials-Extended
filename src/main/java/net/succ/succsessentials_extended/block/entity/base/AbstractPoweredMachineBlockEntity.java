package net.succ.succsessentials_extended.block.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.succ.succsessentials_extended.api.machine.UpgradeableMachine;
import net.succ.succsessentials_extended.item.ModItems;
import net.succ.succsessentials_extended.util.MachineOutputHelper;
import net.succ.succsessentials_extended.util.energy.ModEnergyStorage;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractPoweredMachineBlockEntity
        extends BlockEntity
        implements UpgradeableMachine {

    /* ================= ENERGY ================= */

    protected final ModEnergyStorage energyStorage;

    /* ================= PROGRESS ================= */


    protected int progress = 0;

    /** Post-upgrade values */
    protected int maxProgress = 200;
    protected int energyPerTick = 20;

    /** Base (recipe-defined) values */
    protected int baseMaxProgress = 200;
    protected int baseEnergyPerTick = 20;

    /* ================= UPGRADE CACHE ================= */

    /** Cached values for UI + logic (single source of truth) */
    protected double cachedSpeedMultiplier = 1.0;
    protected double cachedEnergyUsageMultiplier = 1.0;

    /* ================= UPGRADES ================= */

    protected final ItemStackHandler upgradeInventory =
            new ItemStackHandler(2) {

                @Override
                public boolean isItemValid(int slot, ItemStack stack) {
                    return switch (slot) {
                        case 0 -> stack.is(ModItems.SPEED_MODULE);
                        case 1 -> stack.is(ModItems.EFFICIENCY_MODULE);
                        default -> false;
                    };
                }

                @Override
                public int getSlotLimit(int slot) {
                    return 8;
                }

                @Override
                protected void onContentsChanged(int slot) {
                    setChanged();
                    onUpgradesChanged();

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

    protected AbstractPoweredMachineBlockEntity(
            BlockEntityType<?> type,
            BlockPos pos,
            BlockState state,
            int energyCapacity,
            int energyTransfer
    ) {
        super(type, pos, state);

        this.energyStorage = new ModEnergyStorage(energyCapacity, energyTransfer) {
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

    /* ================= ENERGY ================= */

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
            setLitState(false);
        }
    }

    /* ================= UPGRADE MATH ================= */

    /**
     * Single authoritative upgrade calculation.
     * BOTH machine behavior and UI pull from these results.
     */
    protected void recalculateUpgrades() {
        int speed = getSpeedUpgradeLevel();
        int efficiency = getEfficiencyUpgradeLevel();

        // -------- Speed --------
        cachedSpeedMultiplier = Math.pow(1.333, speed);

        maxProgress = Math.max(
                20,
                (int) Math.round(baseMaxProgress / cachedSpeedMultiplier)
        );

        // -------- Energy --------
        double speedEnergyMultiplier = Math.pow(1.78, speed);
        double efficiencyMultiplier = Math.pow(0.75, efficiency);

        cachedEnergyUsageMultiplier =
                speedEnergyMultiplier * efficiencyMultiplier;

        energyPerTick = Math.max(
                1,
                (int) Math.round(baseEnergyPerTick * cachedEnergyUsageMultiplier)
        );
    }

    /* ================= UPGRADE API ================= */

    @Override
    public ItemStackHandler getUpgradeInventory() {
        return upgradeInventory;
    }

    @Override
    public void onUpgradesChanged() {
        recalculateUpgrades();
    }

    @Override
    public int getUpgradeSlotCount() {
        return 2;
    }

    /* ================= TOOLTIP VALUES ================= */

    @Override
    public double getSpeedMultiplier() {
        return cachedSpeedMultiplier;
    }

    @Override
    public double getEnergyUsageMultiplier() {
        return cachedEnergyUsageMultiplier;
    }

    /* ================= ABSTRACT ================= */

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

        tag.putInt("baseMaxProgress", baseMaxProgress);
        tag.putInt("baseEnergyPerTick", baseEnergyPerTick);

        tag.put("upgrades", upgradeInventory.serializeNBT(registries));
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        progress = tag.getInt("progress");
        maxProgress = tag.getInt("maxProgress");
        energyPerTick = tag.getInt("energyPerTick");

        baseMaxProgress = tag.getInt("baseMaxProgress");
        baseEnergyPerTick = tag.getInt("baseEnergyPerTick");

        energyStorage.setEnergy(tag.getInt("energy"));

        if (tag.contains("upgrades")) {
            upgradeInventory.deserializeNBT(
                    registries,
                    tag.getCompound("upgrades")
            );
        }

        // IMPORTANT: reapply upgrades after load
        recalculateUpgrades();

        super.loadAdditional(tag, registries);
    }

    /* ================= HELPERS ================= */

    protected int getSpeedUpgradeLevel() {
        return upgradeInventory.getStackInSlot(0).getCount();
    }

    protected int getEfficiencyUpgradeLevel() {
        return upgradeInventory.getStackInSlot(1).getCount();
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

    protected void dropUpgrades() {
        if (level == null || level.isClientSide()) return;

        for (int i = 0; i < upgradeInventory.getSlots(); i++) {
            ItemStack stack = upgradeInventory.getStackInSlot(i);
            if (!stack.isEmpty()) {
                Containers.dropItemStack(
                        level,
                        worldPosition.getX(),
                        worldPosition.getY(),
                        worldPosition.getZ(),
                        stack
                );
            }
        }
    }

}
