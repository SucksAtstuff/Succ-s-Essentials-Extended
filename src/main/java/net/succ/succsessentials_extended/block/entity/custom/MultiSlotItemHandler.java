package net.succ.succsessentials_extended.block.entity.custom;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

public class MultiSlotItemHandler implements IItemHandler {
    private final ItemStackHandler handler;
    private final int[] slots;
    private final boolean allowInsert;
    private final boolean allowExtract;

    public MultiSlotItemHandler(ItemStackHandler handler, int[] slots, boolean allowInsert, boolean allowExtract) {
        this.handler = handler;
        this.slots = slots;
        this.allowInsert = allowInsert;
        this.allowExtract = allowExtract;
    }

    @Override
    public int getSlots() {
        return slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return handler.getStackInSlot(slots[index]);
    }

    @Override
    public ItemStack insertItem(int index, ItemStack stack, boolean simulate) {
        if (!allowInsert) return stack;
        return handler.insertItem(slots[index], stack, simulate);
    }

    @Override
    public ItemStack extractItem(int index, int amount, boolean simulate) {
        if (!allowExtract) return ItemStack.EMPTY;
        return handler.extractItem(slots[index], amount, simulate);
    }

    @Override
    public int getSlotLimit(int index) {
        return handler.getSlotLimit(slots[index]);
    }

    @Override
    public boolean isItemValid(int index, ItemStack stack) {
        return allowInsert && handler.isItemValid(slots[index], stack);
    }
}

