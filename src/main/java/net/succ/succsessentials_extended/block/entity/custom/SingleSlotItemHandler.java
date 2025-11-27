package net.succ.succsessentials_extended.block.entity.custom;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

public class SingleSlotItemHandler implements IItemHandler {
    private final ItemStackHandler handler;
    private final int slot;
    private final boolean allowInsert;
    private final boolean allowExtract;

    public SingleSlotItemHandler(ItemStackHandler handler, int slot, boolean allowInsert, boolean allowExtract) {
        this.handler = handler;
        this.slot = slot;
        this.allowInsert = allowInsert;
        this.allowExtract = allowExtract;
    }

    @Override
    public int getSlots() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return handler.getStackInSlot(slot);
    }

    @Override
    public ItemStack insertItem(int index, ItemStack stack, boolean simulate) {
        return allowInsert ? handler.insertItem(slot, stack, simulate) : stack;
    }

    @Override
    public ItemStack extractItem(int index, int amount, boolean simulate) {
        return allowExtract ? handler.extractItem(slot, amount, simulate) : ItemStack.EMPTY;
    }

    @Override
    public int getSlotLimit(int index) {
        return handler.getSlotLimit(slot);
    }

    @Override
    public boolean isItemValid(int index, ItemStack stack) {
        return allowInsert && handler.isItemValid(slot, stack);
    }
}


