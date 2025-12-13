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
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.succ.succsessentials_extended.block.custom.AlloyForgerBlock;
import net.succ.succsessentials_extended.block.entity.ModBlockEntities;
import net.succ.succsessentials_extended.recipe.AlloyForgingRecipe;
import net.succ.succsessentials_extended.recipe.AlloyForgingRecipeInput;
import net.succ.succsessentials_extended.recipe.ModRecipes;
import net.succ.succsessentials_extended.screen.custom.AlloyForgerBlockMenu;
import net.succ.succsessentials_extended.util.ModTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class AlloyForgerBlockEntity extends BlockEntity implements MenuProvider {

    /* ==========================================================
       SLOT DEFINITIONS
       ========================================================== */
    private static final int INPUT_SLOT_A = 0;
    private static final int INPUT_SLOT_B = 1;
    private static final int FUEL_SLOT    = 2;
    private static final int OUTPUT_SLOT  = 3;

    /* ==========================================================
       INVENTORY
       ========================================================== */
    public final ItemStackHandler itemHandler = new ItemStackHandler(4) {

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {

                // Two alloy inputs
                case INPUT_SLOT_A, INPUT_SLOT_B ->
                        stack.is(ModTags.Items.INGOTS);

                // Vanilla furnace fuel logic
                case FUEL_SLOT ->
                        AbstractFurnaceBlockEntity.isFuel(stack);

                // Output slot is insert-protected
                case OUTPUT_SLOT -> false;

                default -> false;
            };
        }
    };

    /* ==========================================================
       PROGRESS & FUEL
       ========================================================== */
    private int progress = 0;

    private int maxProgress = 1; // avoid divide-by-zero

    private int burnTime = 0;
    private int maxBurnTime = 0;

    /* ==========================================================
       CONTAINER DATA (SYNC TO CLIENT)
       ========================================================== */
    private final ContainerData data = new ContainerData() {

        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> progress;
                case 1 -> maxProgress;
                case 2 -> burnTime;
                case 3 -> maxBurnTime;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> progress = value;
                case 1 -> maxProgress = value;
                case 2 -> burnTime = value;
                case 3 -> maxBurnTime = value;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };

    /* ==========================================================
       CONSTRUCTOR
       ========================================================== */
    public AlloyForgerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALLOY_FORGER_BE.get(), pos, state);
    }

    /* ==========================================================
       TICK LOGIC
       ========================================================== */
    public void tick(Level level, BlockPos pos, BlockState state) {

        boolean isBurning = burnTime > 0;

        if (isBurning) {
            burnTime--;
        }

        if (hasRecipe()) {

            if (!isBurning && hasFuel()) {
                consumeFuel();
                isBurning = true;
            }

            if (isBurning) {
                progress++;

                if (progress >= maxProgress) {
                    craftItem();
                    progress = 0;
                }
            }

        } else {
            progress = 0;
        }

        if (isBurning != state.getValue(AlloyForgerBlock.LIT)) {
            level.setBlockAndUpdate(pos, state.setValue(AlloyForgerBlock.LIT, isBurning));
        }
    }

    /* ==========================================================
       RECIPE LOGIC
       ========================================================== */
    private boolean hasRecipe() {
        Optional<RecipeHolder<AlloyForgingRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return false;

        this.maxProgress = recipe.get().value().cookTime();

        ItemStack output = recipe.get().value().getResultItem(null);
        return canInsertIntoOutput(output);
    }

    private Optional<RecipeHolder<AlloyForgingRecipe>> getCurrentRecipe() {
        return level.getRecipeManager().getRecipeFor(
                ModRecipes.ALLOY_FORGING_TYPE.get(),
                new AlloyForgingRecipeInput(
                        itemHandler.getStackInSlot(INPUT_SLOT_A),
                        itemHandler.getStackInSlot(INPUT_SLOT_B)
                ),
                level
        );
    }

    private void craftItem() {
        Optional<RecipeHolder<AlloyForgingRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return;

        ItemStack output = recipe.get().value().output();

        itemHandler.extractItem(INPUT_SLOT_A, 1, false);
        itemHandler.extractItem(INPUT_SLOT_B, 1, false);

        itemHandler.setStackInSlot(
                OUTPUT_SLOT,
                new ItemStack(
                        output.getItem(),
                        itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + output.getCount()
                )
        );
    }

    /* ==========================================================
       FUEL
       ========================================================== */
    private boolean hasFuel() {
        return AbstractFurnaceBlockEntity.isFuel(itemHandler.getStackInSlot(FUEL_SLOT));
    }

    private void consumeFuel() {
        ItemStack fuel = itemHandler.getStackInSlot(FUEL_SLOT);

        maxBurnTime = burnTime =
                AbstractFurnaceBlockEntity.getFuel().getOrDefault(fuel.getItem(), 0);

        fuel.shrink(1);
    }

    /* ==========================================================
       OUTPUT CHECKS
       ========================================================== */
    private boolean canInsertIntoOutput(ItemStack stack) {
        ItemStack output = itemHandler.getStackInSlot(OUTPUT_SLOT);

        if (output.isEmpty()) return true;
        if (!ItemStack.isSameItemSameComponents(output, stack)) return false;

        return output.getCount() + stack.getCount() <= output.getMaxStackSize();
    }

    /* ==========================================================
       MENU / UI
       ========================================================== */
    @Override
    public Component getDisplayName() {
        return Component.translatable("block.succsessentials_extended.alloy_forger");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new AlloyForgerBlockMenu(id, inv, this, data);
    }

    /* ==========================================================
       SAVE / LOAD
       ========================================================== */
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", itemHandler.serializeNBT(registries));
        tag.putInt("progress", progress);
        tag.putInt("burnTime", burnTime);
        tag.putInt("maxBurnTime", maxBurnTime);
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        itemHandler.deserializeNBT(registries, tag.getCompound("inventory"));
        progress = tag.getInt("progress");
        burnTime = tag.getInt("burnTime");
        maxBurnTime = tag.getInt("maxBurnTime");
        super.loadAdditional(tag, registries);
    }

    /* ==========================================================
       DROPS
       ========================================================== */
    public void drops() {
        SimpleContainer container = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            container.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(level, worldPosition, container);
    }

    /* ==========================================================
       SYNC
       ========================================================== */
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
