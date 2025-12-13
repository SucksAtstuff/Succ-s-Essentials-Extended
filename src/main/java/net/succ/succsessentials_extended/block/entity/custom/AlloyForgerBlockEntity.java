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
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.succ.succsessentials_extended.block.custom.AlloyForgerBlock;
import net.succ.succsessentials_extended.block.entity.ModBlockEntities;
import net.succ.succsessentials_extended.block.entity.energy.ModEnergyStorage;
import net.succ.succsessentials_extended.recipe.AlloyForgingRecipe;
import net.succ.succsessentials_extended.recipe.AlloyForgingRecipeInput;
import net.succ.succsessentials_extended.recipe.ModRecipes;
import net.succ.succsessentials_extended.screen.custom.AlloyForgerBlockMenu;
import net.succ.succsessentials_extended.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class AlloyForgerBlockEntity extends BlockEntity implements MenuProvider {

    /* ================= SLOTS ================= */
    private static final int INPUT_A = 0;
    private static final int INPUT_B = 1;
    private static final int OUTPUT  = 2;

    /* ================= ENERGY ================= */
    private static final int ENERGY_PER_TICK = 20;
    private static final int ENERGY_CAPACITY = 64000;
    private static final int ENERGY_TRANSFER = 320;

    /* ================= INVENTORY ================= */
    public final ItemStackHandler itemHandler = new ItemStackHandler(3) {
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
                case INPUT_A, INPUT_B -> stack.is(ModTags.Items.INGOTS);
                case OUTPUT -> false;
                default -> false;
            };
        }
    };

    /* ================= ENERGY STORAGE ================= */
    private final ModEnergyStorage energyStorage = new ModEnergyStorage(
            ENERGY_CAPACITY, ENERGY_TRANSFER
    ) {
        @Override
        public void onEnergyChanged() {
            setChanged();
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    };

    /* ================= PROGRESS ================= */
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

    public AlloyForgerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALLOY_FORGER_BE.get(), pos, state);
    }

    public IEnergyStorage getEnergyStorage(@Nullable Direction side) {
        return energyStorage;
    }

    /* ================= TICK ================= */
    public void tick(Level level, BlockPos pos, BlockState state) {
        if (hasRecipe() && hasEnoughEnergy()) {
            progress++;
            energyStorage.extractEnergy(ENERGY_PER_TICK, false);

            if (progress >= maxProgress) {
                craftItem();
                progress = 0;
            }

            level.setBlockAndUpdate(pos, state.setValue(AlloyForgerBlock.LIT, true));
        } else {
            progress = 0;
            level.setBlockAndUpdate(pos, state.setValue(AlloyForgerBlock.LIT, false));
        }
    }

    /* ================= RECIPE ================= */
    private boolean hasRecipe() {
        Optional<RecipeHolder<AlloyForgingRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return false;

        maxProgress = recipe.get().value().cookTime();
        return canInsertIntoOutput(recipe.get().value().output());
    }

    private Optional<RecipeHolder<AlloyForgingRecipe>> getCurrentRecipe() {
        return level.getRecipeManager().getRecipeFor(
                ModRecipes.ALLOY_FORGING_TYPE.get(),
                new AlloyForgingRecipeInput(
                        itemHandler.getStackInSlot(INPUT_A),
                        itemHandler.getStackInSlot(INPUT_B)
                ),
                level
        );
    }

    private void craftItem() {
        Optional<RecipeHolder<AlloyForgingRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return;

        ItemStack result = recipe.get().value().output();
        itemHandler.extractItem(INPUT_A, 1, false);
        itemHandler.extractItem(INPUT_B, 1, false);

        itemHandler.setStackInSlot(
                OUTPUT,
                new ItemStack(
                        result.getItem(),
                        itemHandler.getStackInSlot(OUTPUT).getCount() + result.getCount()
                )
        );
    }

    private boolean hasEnoughEnergy() {
        return energyStorage.getEnergyStored() >= ENERGY_PER_TICK;
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
        return Component.translatable("block.succsessentials_extended.alloy_forger");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new AlloyForgerBlockMenu(id, inv, this, data);
    }

    /* ================= SAVE / LOAD ================= */
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", itemHandler.serializeNBT(registries));
        tag.putInt("progress", progress);
        tag.putInt("energy", energyStorage.getEnergyStored());
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        itemHandler.deserializeNBT(registries, tag.getCompound("inventory"));
        progress = tag.getInt("progress");
        energyStorage.setEnergy(tag.getInt("energy"));
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
