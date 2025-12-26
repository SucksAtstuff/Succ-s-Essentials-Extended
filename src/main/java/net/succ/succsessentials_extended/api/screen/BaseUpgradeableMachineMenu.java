package net.succ.succsessentials_extended.api.screen;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.succ.succsessentials_extended.api.machine.UpgradeableMachine;

/**
 * ============================================================
 * BaseUpgradeableMachineMenu
 *
 * Shared menu logic for ALL machines that support upgrades.
 *
 * Provides:
 *  - Speed + Efficiency upgrade slots
 *  - Centralized player inventory layout
 *  - Safe shift-click logic
 * ============================================================
 */
public abstract class BaseUpgradeableMachineMenu extends AbstractContainerMenu {

    protected final UpgradeableMachine machine;

    protected static final int SPEED_UPGRADE_SLOT = 0;
    protected static final int EFFICIENCY_UPGRADE_SLOT = 1;
    protected static final int UPGRADE_SLOT_COUNT = 2;

    protected int playerInvStart;
    protected int playerInvEnd;
    protected int hotbarStart;
    protected int hotbarEnd;

    protected BaseUpgradeableMachineMenu(
            MenuType<?> type,
            int id,
            Inventory inv,
            UpgradeableMachine machine
    ) {
        super(type, id);
        this.machine = machine;

        // ---------------- Upgrade slots ----------------
        addSlot(new SlotItemHandler(
                machine.getUpgradeInventory(),
                SPEED_UPGRADE_SLOT,
                8, 175
        ));

        addSlot(new SlotItemHandler(
                machine.getUpgradeInventory(),
                EFFICIENCY_UPGRADE_SLOT,
                26, 175
        ));

        // ---------------- Player inventory ----------------
        addPlayerInventory(inv);
        addPlayerHotbar(inv);
    }

    /* ================= PLAYER INVENTORY ================= */

    protected void addPlayerInventory(Inventory inv) {
        playerInvStart = slots.size();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(
                        inv,
                        col + row * 9 + 9,
                        8 + col * 18,
                        84 + row * 18
                ));
            }
        }

        playerInvEnd = slots.size();
    }

    protected void addPlayerHotbar(Inventory inv) {
        hotbarStart = slots.size();

        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(
                    inv,
                    col,
                    8 + col * 18,
                    142
            ));
        }

        hotbarEnd = slots.size();
    }

    /* ================= SHIFT CLICK ================= */

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = slots.get(index);
        if (slot == null || !slot.hasItem()) return ItemStack.EMPTY;

        ItemStack stack = slot.getItem();
        ItemStack copy = stack.copy();

        // From upgrade slots → player inventory
        if (index < UPGRADE_SLOT_COUNT) {
            if (!moveItemStackTo(stack, playerInvStart, hotbarEnd, true)) {
                return ItemStack.EMPTY;
            }
        }
        // From player inventory → upgrades (only if valid)
        else {
            if (!slot.mayPlace(stack)) {
                return ItemStack.EMPTY;
            }

            if (!moveItemStackTo(stack, 0, UPGRADE_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        }

        if (stack.isEmpty()) {
            slot.set(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        return copy;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }


    public UpgradeableMachine getMachine() {
        return machine;
    }
}
