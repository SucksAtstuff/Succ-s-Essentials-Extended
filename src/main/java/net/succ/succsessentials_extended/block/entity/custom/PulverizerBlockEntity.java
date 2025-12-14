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
import net.minecraft.world.level.block.Blocks;
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

/**
 * ============================================================
 * PulverizerBlockEntity
 *
 * Uses AbstractPoweredMachineBlockEntity to inherit:
 *  - Energy storage
 *  - Progress ticking
 *  - Energy consumption
 *  - NBT save/load
 *
 * Handles:
 *  - 1 input
 *  - 2 outputs (dust + byproduct)
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



        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return slot == INPUT;
        }
    };

    private boolean canInsert(ItemStack existing, ItemStack toInsert) {
        if (existing.isEmpty()) return true;
        if (!ItemStack.isSameItemSameComponents(existing, toInsert)) return false;
        return existing.getCount() + toInsert.getCount() <= existing.getMaxStackSize();
    }

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

    public PulverizerBlockEntity(BlockPos pos, BlockState state) {
        super(
                ModBlockEntities.PULVERIZER_BE.get(),
                pos,
                state,
                64000, // ENERGY CAPACITY
                320   // ENERGY TRANSFER
        );

        this.maxProgress = 200;
        this.energyPerTick = 20;
    }

    /* ================= RECIPE LOGIC ================= */

    /**
     * Placeholder recipe check.
     *
     * This will later be replaced with:
     *  - PulverizerRecipe lookup
     *  - Output space validation
     */
    @Override
    protected boolean hasRecipe() {
        if (level == null) return false;

        ItemStack input = itemHandler.getStackInSlot(INPUT);
        if (input.isEmpty()) return false;

        var recipeOpt = level.getRecipeManager().getRecipeFor(
                ModRecipes.PULVERIZING_TYPE.get(),
                new PulverizingRecipeInput(input),
                level
        );

        if (recipeOpt.isEmpty()) return false;

        PulverizingRecipe recipe = recipeOpt.get().value();

        // Dynamic values per recipe
        maxProgress = recipe.cookTime();
        energyPerTick = recipe.energyPerTick();

        // Validate output slots BEFORE running
        ItemStack primary = recipe.output();
        ItemStack secondary = recipe.getByproduct();

        return canInsert(itemHandler.getStackInSlot(OUTPUT_DUST), primary)
                && (secondary.isEmpty()
                || canInsert(itemHandler.getStackInSlot(OUTPUT_BYPRODUCT), secondary));
    }


    /**
     * Executes pulverization once progress completes.
     */
    @Override
    protected void craftItem() {
        if (level == null) return;

        var recipeOpt = level.getRecipeManager().getRecipeFor(
                ModRecipes.PULVERIZING_TYPE.get(),
                new PulverizingRecipeInput(itemHandler.getStackInSlot(INPUT)),
                level
        );

        if (recipeOpt.isEmpty()) return;

        PulverizingRecipe recipe = recipeOpt.get().value();

        ItemStack primary = recipe.output().copy();
        ItemStack secondary = recipe.getByproduct();

        // Consume input
        itemHandler.extractItem(INPUT, 1, false);

        // Insert outputs
        itemHandler.insertItem(OUTPUT_DUST, primary, false);

        if (!secondary.isEmpty()) {
            itemHandler.insertItem(OUTPUT_BYPRODUCT, secondary.copy(), false);
        }
    }


    /* ================= BLOCK STATE ================= */

    @Override
    protected void setLitState(boolean lit) {
        if (level == null) return;

        BlockState state = getBlockState();
        if (state.getValue(PulverizerBlock.LIT) != lit) {
            level.setBlock(
                    worldPosition,
                    state.setValue(PulverizerBlock.LIT, lit),
                    3
            );
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
