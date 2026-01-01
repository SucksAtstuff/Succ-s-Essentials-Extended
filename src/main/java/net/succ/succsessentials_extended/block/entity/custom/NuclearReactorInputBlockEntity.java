package net.succ.succsessentials_extended.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.succ.succsessentials_extended.block.entity.ModBlockEntities;

/**
 * ============================================================
 * NuclearReactorInputBlockEntity
 *
 * Holds reactor fuel.
 * No logic, no ticking, no menu.
 * ============================================================
 */
public class NuclearReactorInputBlockEntity
        extends net.minecraft.world.level.block.entity.BlockEntity {

    /* ================= INVENTORY ================= */

    // Slot 0 = fuel
    public final ItemStackHandler itemHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    public NuclearReactorInputBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.NUCLEAR_REACTOR_INPUT_BE.get(), pos, state);
    }

    /* ================= SAVE / LOAD ================= */

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("Inventory", itemHandler.serializeNBT(registries));
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        itemHandler.deserializeNBT(registries, tag.getCompound("Inventory"));
        super.loadAdditional(tag, registries);
    }
}
