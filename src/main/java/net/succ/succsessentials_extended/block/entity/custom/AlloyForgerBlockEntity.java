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
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.succ.succsessentials_extended.block.custom.AlloyForgerBlock;
import net.succ.succsessentials_extended.block.entity.ModBlockEntities;
import net.succ.succsessentials_extended.block.entity.base.AbstractPoweredMachineBlockEntity;
import net.succ.succsessentials_extended.recipe.AlloyForgingRecipe;
import net.succ.succsessentials_extended.recipe.AlloyForgingRecipeInput;
import net.succ.succsessentials_extended.recipe.ModRecipes;
import net.succ.succsessentials_extended.screen.custom.AlloyForgerBlockMenu;
import net.succ.succsessentials_extended.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * ============================================================
 * AlloyForgerBlockEntity
 *
 * Uses AbstractPoweredMachineBlockEntity to inherit:
 *  - Energy storage
 *  - Progress ticking
 *  - Energy consumption
 *  - NBT save/load for machine data
 *
 * Only contains logic UNIQUE to alloy forging.
 * ============================================================
 */
public class AlloyForgerBlockEntity extends AbstractPoweredMachineBlockEntity
        implements MenuProvider {

    /* ================= SLOTS ================= */

    private static final int INPUT_A = 0;
    private static final int INPUT_B = 1;
    private static final int OUTPUT  = 2;

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
        super(
                ModBlockEntities.ALLOY_FORGER_BE.get(),
                pos,
                state,
                64000, // ENERGY CAPACITY
                320    // ENERGY TRANSFER
        );
    }

    /* ================= RECIPE ================= */

    @Override
    protected boolean hasRecipe() {
        Optional<RecipeHolder<AlloyForgingRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return false;

        // Dynamic values per recipe (same as before)
        maxProgress = recipe.get().value().cookTime();
        energyPerTick = recipe.get().value().energyPerTick();

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

    @Override
    protected void craftItem() {
        Optional<RecipeHolder<AlloyForgingRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return;

        ItemStack result = recipe.get().value().output();

        itemHandler.extractItem(INPUT_A, 1, false);
        itemHandler.extractItem(INPUT_B, 1, false);

        itemHandler.insertItem(OUTPUT, result.copy(), false);
    }

    private boolean canInsertIntoOutput(ItemStack stack) {
        ItemStack output = itemHandler.getStackInSlot(OUTPUT);
        if (output.isEmpty()) return true;
        if (!ItemStack.isSameItemSameComponents(output, stack)) return false;
        return output.getCount() + stack.getCount() <= output.getMaxStackSize();
    }

    /* ================= BLOCK STATE ================= */

    @Override
    protected void setLitState(boolean lit) {
        if (level == null) return;

        BlockState state = getBlockState();
        if (state.getValue(AlloyForgerBlock.LIT) != lit) {
            level.setBlock(worldPosition, state.setValue(AlloyForgerBlock.LIT, lit), 3);
        }
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
