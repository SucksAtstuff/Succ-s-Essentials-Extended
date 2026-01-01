package net.succ.succsessentials_extended.screen.custom;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.succ.succsessentials_extended.block.ModBlocks;
import net.succ.succsessentials_extended.block.entity.custom.NuclearReactorControllerBlockEntity;
import net.succ.succsessentials_extended.block.entity.custom.NuclearReactorInputBlockEntity;
import net.succ.succsessentials_extended.block.entity.custom.NuclearReactorOutputBlockEntity;
import net.succ.succsessentials_extended.screen.ModMenuTypes;

/**
 * ============================================================
 * NuclearReactorControllerBlockMenu
 *
 * Controller-only menu (NOT upgradeable).
 * Aggregates input/output from multiblock.
 * ============================================================
 */
public class NuclearReactorControllerBlockMenu
        extends AbstractContainerMenu {

    public final NuclearReactorControllerBlockEntity controller;
    private final Level level;

    /* ================= CLIENT ================= */

    public NuclearReactorControllerBlockMenu(
            int id,
            Inventory inv,
            FriendlyByteBuf buf
    ) {
        this(
                id,
                inv,
                inv.player.level().getBlockEntity(buf.readBlockPos())
        );
    }

    /* ================= SERVER ================= */

    public NuclearReactorControllerBlockMenu(
            int id,
            Inventory inv,
            BlockEntity entity
    ) {
        super(
                ModMenuTypes.NUCLEAR_REACTOR_CONTROLLER_MENU.get(),
                id
        );

        this.controller = (NuclearReactorControllerBlockEntity) entity;
        this.level = inv.player.level();

        /* =====================================================
           MULTIBLOCK IO
           ===================================================== */

        NuclearReactorInputBlockEntity input = controller.getInputBE();
        NuclearReactorOutputBlockEntity output = controller.getOutputBE();

        /* ================= INPUT ================= */

        if (input != null) {
            addSlot(new SlotItemHandler(input.itemHandler, 0, 56, 25));
            addSlot(new SlotItemHandler(input.itemHandler, 1, 56, 44));
        }

        /* ================= OUTPUT ================= */

        if (output != null) {
            addSlot(new SlotItemHandler(output.itemHandler, 0, 116, 35) {
                @Override
                public boolean mayPlace(net.minecraft.world.item.ItemStack stack) {
                    return false;
                }
            });
        }

        /* ================= PLAYER INVENTORY ================= */

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
    }

    /* ================= INVENTORY HELPERS ================= */

    private void addPlayerInventory(Inventory inv) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(
                        new net.minecraft.world.inventory.Slot(
                                inv,
                                col + row * 9 + 9,
                                8 + col * 18,
                                84 + row * 18
                        )
                );
            }
        }
    }

    private void addPlayerHotbar(Inventory inv) {
        for (int col = 0; col < 9; col++) {
            this.addSlot(
                    new net.minecraft.world.inventory.Slot(
                            inv,
                            col,
                            8 + col * 18,
                            142
                    )
            );
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {

        Slot slot = this.slots.get(index);
        if (slot == null || !slot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack stack = slot.getItem();
        ItemStack copy = stack.copy();

        // ===== SLOT LAYOUT =====
        // 0-1 : input
        // 2   : output
        // 3+  : player inventory
        final int INPUT_SLOT_COUNT = 2;
        final int CONTAINER_SLOT_COUNT = 3;
        final int PLAYER_INV_START = CONTAINER_SLOT_COUNT;
        final int PLAYER_INV_END = this.slots.size();

        // ============================================================
        // FROM REACTOR → PLAYER
        // ============================================================
        if (index < CONTAINER_SLOT_COUNT) {

            if (!moveItemStackTo(
                    stack,
                    PLAYER_INV_START,
                    PLAYER_INV_END,
                    true
            )) {
                return ItemStack.EMPTY;
            }

        }
        // ============================================================
        // FROM PLAYER → REACTOR INPUT
        // ============================================================
        else {

            if (!moveItemStackTo(
                    stack,
                    0,
                    INPUT_SLOT_COUNT,
                    false
            )) {
                return ItemStack.EMPTY;
            }
        }

        // ===== FINALIZE SLOT =====
        if (stack.isEmpty()) {
            slot.set(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        return copy;
    }



    /* ================= VALID ================= */



    @Override
    public boolean stillValid(Player player) {
        return controller.isFormed() &&
                stillValid(
                        ContainerLevelAccess.create(level, controller.getBlockPos()),
                        player,
                        ModBlocks.NUCLEAR_REACTOR_CONTROLLER.get()
                );
    }
}
