package net.succ.succsessentials_extended.screen.custom;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
 * Handles the server-side logic for the UI of the reactor.
 * Displays:
 *  - Input Slot from Input Block
 *  - Output Slot from Output Block
 *  - Internal water bucket slot for coolant logic
 *  - Player inventory
 * ============================================================
 */
public class NuclearReactorControllerBlockMenu extends AbstractContainerMenu {

    public final NuclearReactorControllerBlockEntity controller;
    private final Level level;

    // === SLOT INDICES ===
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;
    private static final int WATER_SLOT = 2;
    private static final int PLAYER_INV_START = 3;
    private static final int PLAYER_INV_END = PLAYER_INV_START + 36;

    /* ============================================================
       CLIENT CONSTRUCTOR
       ============================================================ */
    public NuclearReactorControllerBlockMenu(int id, Inventory inv, FriendlyByteBuf buf) {
        this(id, inv, inv.player.level().getBlockEntity(buf.readBlockPos()));
    }

    /* ============================================================
       SERVER CONSTRUCTOR
       ============================================================ */
    public NuclearReactorControllerBlockMenu(int id, Inventory inv, BlockEntity entity) {
        super(ModMenuTypes.NUCLEAR_REACTOR_CONTROLLER_MENU.get(), id);

        this.controller = (NuclearReactorControllerBlockEntity) entity;
        this.level = inv.player.level();

        // === MULTIBLOCK INPUT BLOCK ===
        NuclearReactorInputBlockEntity input = controller.getInputBE();
        if (input != null) {
            addSlot(new SlotItemHandler(input.itemHandler, 0, 56, 35)); // slot 0
        }

        // === MULTIBLOCK OUTPUT BLOCK ===
        NuclearReactorOutputBlockEntity output = controller.getOutputBE();
        if (output != null) {
            addSlot(new SlotItemHandler(output.itemHandler, 0, 116, 35) {
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return false;
                }
            }); // slot 1
        }

        // === WATER BUCKET SLOT ON CONTROLLER ===
        addSlot(new SlotItemHandler(controller.itemHandler, WATER_SLOT, 149, 62) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() == Items.WATER_BUCKET;
            }

            @Override
            public boolean mayPickup(Player player) {
                ItemStack stack = getItem();
                return stack.isEmpty() || stack.getItem() == Items.BUCKET;
            }
        }); // slot 2

        // === PLAYER INVENTORY ===
        addPlayerInventory(inv);
        addPlayerHotbar(inv);
    }

    /* ============================================================
       Adds player inventory slots
       ============================================================ */
    private void addPlayerInventory(Inventory inv) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
    }

    /* ============================================================
       Adds player hotbar slots
       ============================================================ */
    private void addPlayerHotbar(Inventory inv) {
        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(inv, col, 8 + col * 18, 142));
        }
    }

    /* ============================================================
       Quick-move stack logic for shift-click
       ============================================================ */
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = this.slots.get(index);
        if (slot == null || !slot.hasItem()) return ItemStack.EMPTY;

        ItemStack stack = slot.getItem();
        ItemStack copy = stack.copy();

        if (index < PLAYER_INV_START) {
            // FROM machine slots TO player inventory
            if (!moveItemStackTo(stack, PLAYER_INV_START, PLAYER_INV_END, true)) {
                return ItemStack.EMPTY;
            }
        } else {
            // FROM player inventory TO water slot
            if (stack.getItem() == Items.WATER_BUCKET) {
                if (!moveItemStackTo(stack, WATER_SLOT, WATER_SLOT + 1, false)) {
                    return ItemStack.EMPTY;
                }
            }
            // FROM player inventory TO input slot
            else if (!moveItemStackTo(stack, INPUT_SLOT, INPUT_SLOT + 1, false)) {
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

    /* ============================================================
       Validates if player can still use the container
       ============================================================ */
    @Override
    public boolean stillValid(Player player) {
        return controller.isFormed() && stillValid(
                ContainerLevelAccess.create(level, controller.getBlockPos()),
                player,
                ModBlocks.NUCLEAR_REACTOR_CONTROLLER.get()
        );
    }
}
