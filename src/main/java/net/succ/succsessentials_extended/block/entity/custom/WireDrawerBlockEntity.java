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
import net.succ.succsessentials_extended.block.custom.WireDrawerBlock;
import net.succ.succsessentials_extended.block.entity.ModBlockEntities;
import net.succ.succsessentials_extended.block.entity.base.AbstractPoweredMachineBlockEntity;
import net.succ.succsessentials_extended.recipe.ModRecipes;
import net.succ.succsessentials_extended.recipe.wiredrawing.WireDrawingRecipe;
import net.succ.succsessentials_extended.recipe.wiredrawing.WireDrawingRecipeInput;
import net.succ.succsessentials_extended.screen.custom.WireDrawerBlockMenu;
import net.succ.succsessentials_extended.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class WireDrawerBlockEntity extends AbstractPoweredMachineBlockEntity
        implements MenuProvider {

    private static final int INPUT  = 0;
    private static final int OUTPUT = 1;

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
            return slot == INPUT && stack.is(ModTags.Items.PLATES);
        }

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            if (slot == OUTPUT) {
                return super.insertItem(slot, stack, simulate);
            }
            return isItemValid(slot, stack)
                    ? super.insertItem(slot, stack, simulate)
                    : stack;
        }
    };

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

    public WireDrawerBlockEntity(BlockPos pos, BlockState state) {
        super(
                ModBlockEntities.WIRE_DRAWER_BE.get(),
                pos,
                state,
                64000,
                320
        );
    }

    /* ================= RECIPE ================= */

    @Override
    protected boolean hasRecipe() {
        Optional<RecipeHolder<WireDrawingRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return false;

        WireDrawingRecipe value = recipe.get().value();

        baseMaxProgress = value.cookTime();
        baseEnergyPerTick = value.energyPerTick();
        recalculateUpgrades();

        return canOutputResult(itemHandler, OUTPUT, value.output());
    }

    private Optional<RecipeHolder<WireDrawingRecipe>> getCurrentRecipe() {
        return level.getRecipeManager().getRecipeFor(
                ModRecipes.WIRE_DRAWING_TYPE.get(),
                new WireDrawingRecipeInput(itemHandler.getStackInSlot(INPUT)),
                level
        );
    }

    @Override
    protected void craftItem() {
        Optional<RecipeHolder<WireDrawingRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return;

        WireDrawingRecipe value = recipe.get().value();

        itemHandler.extractItem(INPUT, value.inputCount(), false);
        outputResult(itemHandler, OUTPUT, value.output().copy());
    }

    /* ================= BLOCK STATE ================= */

    @Override
    protected void setLitState(boolean lit) {
        if (level == null) return;

        BlockState state = getBlockState();
        if (state.getValue(WireDrawerBlock.LIT) != lit) {
            level.setBlock(worldPosition, state.setValue(WireDrawerBlock.LIT, lit), 3);
        }
    }

    /* ================= MENU ================= */

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.succsessentials_extended.wire_drawer");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new WireDrawerBlockMenu(id, inv, this, data);
    }

    /* ================= DROPS ================= */

    public void drops() {
        SimpleContainer container = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            container.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(level, worldPosition, container);
    }

    /* ================= NETWORK ================= */

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
