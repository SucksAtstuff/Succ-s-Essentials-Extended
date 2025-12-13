package net.succ.succsessentials_extended.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.succ.succsessentials_extended.block.custom.ElectricFurnaceBlock;
import net.succ.succsessentials_extended.block.entity.ModBlockEntities;
import net.succ.succsessentials_extended.block.entity.energy.ModEnergyStorage;
import net.succ.succsessentials_extended.screen.custom.AlloyForgerBlockMenu;
import net.succ.succsessentials_extended.screen.custom.ElectricFurnaceBlockMenu;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ElectricFurnaceBlockEntity extends BlockEntity implements MenuProvider {

    private static final int INPUT = 0;
    private static final int OUTPUT = 1;

    private int energyPerTick = 20;

    private static final int ENERGY_CAPACITY = 64000;
    private static final int ENERGY_TRANSFER = 320;

    public final ItemStackHandler itemHandler = new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return switch (slot) {
                case INPUT -> true;
                case OUTPUT -> false;
                default -> false;
            };
        }
    };

    private final ModEnergyStorage energyStorage = new ModEnergyStorage(
            ENERGY_CAPACITY, ENERGY_TRANSFER
    ) {
        @Override
        public void onEnergyChanged() {
            setChanged();
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    };

    private int progress = 0;
    private int maxProgress = 200;

    /* ================= DATA SYNC ================= */
    private final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> progress;
                case 1 -> maxProgress;
                case 2 -> energyStorage.getEnergyStored();
                case 3 -> energyStorage.getMaxEnergyStored();
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            if (index == 0) progress = value;
            if (index == 1) maxProgress = value;
        }

        @Override
        public int getCount() {
            return 4;
        }
    };


    public ElectricFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ELECTRIC_FURNACE_BE.get(), pos, state);
    }

    public IEnergyStorage getEnergyStorage(@Nullable Direction side) {
        return energyStorage;
    }

    /* ================= TICK (INSTANCE, SAME AS ALLOY FORGER) ================= */
    public void tick(Level level, BlockPos pos, BlockState state) {
        if (hasRecipe() && hasEnoughEnergy()) {
            progress++;
            energyStorage.extractEnergy(energyPerTick, false);

            if (progress >= maxProgress) {
                craftItem();
                progress = 0;
            }

            if (!state.getValue(ElectricFurnaceBlock.LIT)) {
                level.setBlock(pos, state.setValue(ElectricFurnaceBlock.LIT, true), 3);
            }

        } else {
            progress = 0;
            energyPerTick = 20;

            if (state.getValue(ElectricFurnaceBlock.LIT)) {
                level.setBlock(pos, state.setValue(ElectricFurnaceBlock.LIT, false), 3);
            }
        }
    }


    /* ================= RECIPE (VANILLA SMELTING) ================= */
    private boolean hasRecipe() {
        Optional<RecipeHolder<SmeltingRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return false;

        maxProgress = recipe.get().value().getCookingTime();
        energyPerTick = 20;

        ItemStack result = recipe.get().value().getResultItem(level.registryAccess());
        return canInsertIntoOutput(result);
    }


    private Optional<RecipeHolder<SmeltingRecipe>> getCurrentRecipe() {
        return level.getRecipeManager().getRecipeFor(
                RecipeType.SMELTING,
                new SingleRecipeInput(itemHandler.getStackInSlot(INPUT)),
                level
        );
    }

    private void craftItem() {
        Optional<RecipeHolder<SmeltingRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return;

        ItemStack result = recipe.get().value().getResultItem(level.registryAccess());

        itemHandler.extractItem(INPUT, 1, false);

        ItemStack output = itemHandler.getStackInSlot(OUTPUT);
        if (output.isEmpty()) {
            itemHandler.setStackInSlot(OUTPUT, result.copy());
        } else {
            itemHandler.setStackInSlot(
                    OUTPUT,
                    new ItemStack(result.getItem(), output.getCount() + result.getCount())
            );
        }
    }


    private boolean hasEnoughEnergy() {
        return energyStorage.getEnergyStored() >= energyPerTick;
    }

    private boolean canInsertIntoOutput(ItemStack stack) {
        ItemStack output = itemHandler.getStackInSlot(OUTPUT);
        if (output.isEmpty()) return true;
        if (!ItemStack.isSameItemSameComponents(output, stack)) return false;
        return output.getCount() + stack.getCount() <= output.getMaxStackSize();
    }


    /* ================= MENU ================= */
    @Override
    public Component getDisplayName() {
        return Component.translatable("block.succsessentials_extended.electric_furnace");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new ElectricFurnaceBlockMenu(id, inv, this, data);
    }

    /* ================= SAVE / LOAD ================= */
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", itemHandler.serializeNBT(registries));
        tag.putInt("progress", progress);
        tag.putInt("energy", energyStorage.getEnergyStored());
        tag.putInt("energyPerTick", energyPerTick);
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        itemHandler.deserializeNBT(registries, tag.getCompound("inventory"));
        progress = tag.getInt("progress");
        energyStorage.setEnergy(tag.getInt("energy"));
        energyPerTick = tag.getInt("energyPerTick");
        super.loadAdditional(tag, registries);
    }


    public void drops() {
        SimpleContainer container = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            container.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(level, worldPosition, container);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }
}

