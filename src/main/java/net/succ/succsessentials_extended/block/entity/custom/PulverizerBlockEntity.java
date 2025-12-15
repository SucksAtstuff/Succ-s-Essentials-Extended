package net.succ.succsessentials_extended.block.entity.custom;

import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.succ.succsessentials_extended.block.custom.PulverizerBlock;
import net.succ.succsessentials_extended.block.entity.ModBlockEntities;
import net.succ.succsessentials_extended.block.entity.base.AbstractPoweredMachineBlockEntity;
import net.succ.succsessentials_extended.recipe.ModRecipes;
import net.succ.succsessentials_extended.recipe.PulverizingRecipe;
import net.succ.succsessentials_extended.recipe.PulverizingRecipeInput;
import net.succ.succsessentials_extended.screen.custom.PulverizerBlockMenu;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * ============================================================
 * PulverizerBlockEntity
 *
 * Energy-powered machine that crushes items into dusts
 * and optional byproducts.
 *
 * ============================================================
 */
public class PulverizerBlockEntity extends AbstractPoweredMachineBlockEntity
        implements MenuProvider {

    /* ================= SLOTS ================= */

    private static final int INPUT = 0;
    private static final int OUTPUT_DUST = 1;
    private static final int OUTPUT_BYPRODUCT = 2;

    /* ================= INVENTORY ================= */

    public final ItemStackHandler itemHandler = new ItemStackHandler(3) {

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            }
        }

        /**
         * IMPORTANT:
         * - Players & automation may ONLY insert into INPUT
         * - Output slots are machine-controlled
         */
        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return slot == INPUT;
        }
    };

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

    /* ================= CONSTRUCTOR ================= */

    public PulverizerBlockEntity(BlockPos pos, BlockState state) {
        super(
                ModBlockEntities.PULVERIZER_BE.get(),
                pos,
                state,
                64000, // energy capacity
                320    // energy transfer
        );
    }

    /* ================= RECIPE ================= */

    @Override
    protected boolean hasRecipe() {
        if (level == null) return false;

        ItemStack input = itemHandler.getStackInSlot(INPUT);
        if (input.isEmpty()) return false;

        Optional<PulverizingRecipe> recipeOpt =
                level.getRecipeManager()
                        .getRecipeFor(
                                ModRecipes.PULVERIZING_TYPE.get(),
                                new PulverizingRecipeInput(input),
                                level
                        )
                        .map(holder -> holder.value());

        if (recipeOpt.isEmpty()) return false;

        PulverizingRecipe recipe = recipeOpt.get();

        // dynamic values per recipe
        maxProgress = recipe.cookTime();
        energyPerTick = recipe.energyPerTick();

        // validate outputs BEFORE running
        return canInsert(OUTPUT_DUST, recipe.output())
                && (recipe.byproduct().isEmpty()
                || canInsert(OUTPUT_BYPRODUCT, recipe.byproduct()));
    }

    @Override
    protected void craftItem() {
        if (level == null) return;

        Optional<PulverizingRecipe> recipeOpt =
                level.getRecipeManager()
                        .getRecipeFor(
                                ModRecipes.PULVERIZING_TYPE.get(),
                                new PulverizingRecipeInput(itemHandler.getStackInSlot(INPUT)),
                                level
                        )
                        .map(holder -> holder.value());

        if (recipeOpt.isEmpty()) return;

        PulverizingRecipe recipe = recipeOpt.get();

        ItemStack primary = recipe.output().copy();
        ItemStack secondary = recipe.byproduct().copy();

        // consume input
        itemHandler.extractItem(INPUT, 1, false);

        // insert outputs (BYPASS isItemValid)
        insertOutput(OUTPUT_DUST, primary);

        if (!secondary.isEmpty()) {
            insertOutput(OUTPUT_BYPRODUCT, secondary);
        }
    }

    /* ================= OUTPUT HELPERS ================= */

    /**
     * Checks whether a stack can be merged into the target slot.
     */
    private boolean canInsert(int slot, ItemStack stack) {
        ItemStack existing = itemHandler.getStackInSlot(slot);

        if (existing.isEmpty()) return true;
        if (!ItemStack.isSameItemSameComponents(existing, stack)) return false;
        return existing.getCount() + stack.getCount() <= existing.getMaxStackSize();
    }

    /**
     * Inserts items into output slots without respecting isItemValid.
     * This is REQUIRED for machine outputs.
     */
    private void insertOutput(int slot, ItemStack stack) {
        ItemStack existing = itemHandler.getStackInSlot(slot);

        if (existing.isEmpty()) {
            itemHandler.setStackInSlot(slot, stack.copy());
        } else {
            existing.grow(stack.getCount());
            itemHandler.setStackInSlot(slot, existing);
        }
    }

    /* ================= BLOCK STATE ================= */

    @Override
    protected void setLitState(boolean lit) {
        if (level == null) return;

        BlockState state = getBlockState();
        if (state.getValue(PulverizerBlock.LIT) != lit) {
            level.setBlock(worldPosition, state.setValue(PulverizerBlock.LIT, lit), 3);
        }
    }

    /* ================= MENU ================= */

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.succsessentials_extended.pulverizer");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new PulverizerBlockMenu(id, inv, this, data);
    }

    /* ================= DROPS ================= */

    public void drops() {
        SimpleContainer container = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            container.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(level, worldPosition, container);
    }

    /* ================= NETWORK SYNC ================= */

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
