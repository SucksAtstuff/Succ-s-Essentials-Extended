package net.succ.succsessentials_extended.util;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;

/**
 * ============================================================
 * MachineOutputHelper
 *
 * Centralized, SAFE machine output handling.
 * - Bypasses slot validation
 * - Prevents item voiding
 * - Handles stacking
 * - Enforces overflow protection
 *
 * This should be used by ALL machines when producing outputs.
 * ============================================================
 */
public final class MachineOutputHelper {

    private MachineOutputHelper() {}

    /**
     * Checks whether the result stack can be inserted into the given slot
     * WITHOUT overflowing.
     */
    public static boolean canOutput(
            ItemStackHandler handler,
            int outputSlot,
            ItemStack result
    ) {
        ItemStack existing = handler.getStackInSlot(outputSlot);

        if (existing.isEmpty()) return true;

        if (!ItemStack.isSameItemSameComponents(existing, result)) return false;

        return existing.getCount() + result.getCount()
                <= existing.getMaxStackSize();
    }

    /**
     * Force-inserts the result stack into the output slot.
     * MUST only be called after canOutput() returns true.
     */
    public static void output(
            ItemStackHandler handler,
            int outputSlot,
            ItemStack result
    ) {
        ItemStack existing = handler.getStackInSlot(outputSlot);

        if (existing.isEmpty()) {
            handler.setStackInSlot(outputSlot, result);
        } else {
            existing.grow(result.getCount());
            handler.setStackInSlot(outputSlot, existing);
        }
    }
}
