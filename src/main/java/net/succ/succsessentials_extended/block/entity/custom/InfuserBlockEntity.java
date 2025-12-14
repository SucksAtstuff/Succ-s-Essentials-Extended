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
import net.succ.succsessentials_extended.block.custom.InfuserBlock;
import net.succ.succsessentials_extended.block.entity.ModBlockEntities;
import net.succ.succsessentials_extended.block.entity.base.AbstractPoweredMachineBlockEntity;
import net.succ.succsessentials_extended.recipe.InfusingRecipe;
import net.succ.succsessentials_extended.recipe.InfusingRecipeInput;
import net.succ.succsessentials_extended.recipe.ModRecipes;
import net.succ.succsessentials_extended.screen.custom.InfuserBlockMenu;
import net.succ.succsessentials_extended.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * ============================================================
 * InfuserBlockEntity
 *
 * Slow, energy-hungry machine that combines:
 *  - 1 ingot
 *  - 1 dust
 *
 * Uses AbstractPoweredMachineBlockEntity for:
 *  - Energy storage
 *  - Progress ticking
 *  - NBT save/load
 * ============================================================
 */
public class InfuserBlockEntity extends AbstractPoweredMachineBlockEntity
        implements MenuProvider {

    /* ================= SLOTS ================= */

    private static final int INPUT_INGOT = 0;
    private static final int INPUT_DUST  = 1;
    private static final int OUTPUT      = 2;

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
                case INPUT_INGOT -> stack.is(ModTags.Items.INGOTS);
                case INPUT_DUST  -> stack.is(ModTags.Items.DUSTS);
                case OUTPUT      -> false;
                default          -> false;
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

    /* ================= CONSTRUCTOR ================= */

    public InfuserBlockEntity(BlockPos pos, BlockState state) {
        super(
                ModBlockEntities.INFUSER_BE.get(),
                pos,
                state,
                128000, // ENERGY CAPACITY (larger than alloy forger)
                640     // ENERGY TRANSFER (hungry machine)
        );

        // Default behavior: slow & expensive
        this.maxProgress = 400;   // slow
        this.energyPerTick = 80;  // power-hungry
    }

    /* ================= RECIPE ================= */

    @Override
    protected boolean hasRecipe() {
        Optional<RecipeHolder<InfusingRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return false;

        // Recipe-defined values override defaults if present
        maxProgress = recipe.get().value().cookTime();
        energyPerTick = recipe.get().value().energyPerTick();

        return canInsertIntoOutput(recipe.get().value().output());
    }

    private Optional<RecipeHolder<InfusingRecipe>> getCurrentRecipe() {
        return level.getRecipeManager().getRecipeFor(
                ModRecipes.INFUSING_TYPE.get(),
                new InfusingRecipeInput(
                        itemHandler.getStackInSlot(INPUT_INGOT),
                        itemHandler.getStackInSlot(INPUT_DUST)
                ),
                level
        );
    }

    @Override
    protected void craftItem() {
        Optional<RecipeHolder<InfusingRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return;

        ItemStack result = recipe.get().value().output();

        // Consume inputs
        itemHandler.extractItem(INPUT_INGOT, 1, false);
        itemHandler.extractItem(INPUT_DUST, 1, false);

        // Manually handle output to avoid silent insert failures
        ItemStack output = itemHandler.getStackInSlot(OUTPUT);

        if (output.isEmpty()) {
            itemHandler.setStackInSlot(OUTPUT, result.copy());
        } else {
            output.grow(result.getCount());
            itemHandler.setStackInSlot(OUTPUT, output);
        }
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
        if (state.getValue(InfuserBlock.LIT) != lit) {
            level.setBlock(worldPosition, state.setValue(InfuserBlock.LIT, lit), 3);
        }
    }

    /* ================= MENU ================= */

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.succsessentials_extended.infuser");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new InfuserBlockMenu(id, inv, this, data);
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
