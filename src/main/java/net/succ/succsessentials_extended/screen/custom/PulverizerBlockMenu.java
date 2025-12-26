package net.succ.succsessentials_extended.screen.custom;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.succ.succsessentials_extended.api.machine.UpgradeableMachine;
import net.succ.succsessentials_extended.api.screen.BaseUpgradeableMachineMenu;
import net.succ.succsessentials_extended.block.ModBlocks;
import net.succ.succsessentials_extended.block.entity.custom.PulverizerBlockEntity;
import net.succ.succsessentials_extended.screen.ModMenuTypes;

public class PulverizerBlockMenu extends BaseUpgradeableMachineMenu {

    /* ================= FIELDS ================= */

    public final PulverizerBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    /* ================= SLOT INDEXES ================= */

    private static final int INPUT = 0;
    private static final int OUTPUT_DUST = 1;
    private static final int OUTPUT_BYPRODUCT = 2;

    /* ================= CONSTRUCTORS ================= */

    public PulverizerBlockMenu(int id, Inventory inv, FriendlyByteBuf buf) {
        this(
                id,
                inv,
                inv.player.level().getBlockEntity(buf.readBlockPos()),
                new SimpleContainerData(4) // progress, maxProgress, energy, maxEnergy
        );
    }

    public PulverizerBlockMenu(int id, Inventory inv, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.PULVERIZER_MENU.get(), id, inv, (UpgradeableMachine) entity);

        this.blockEntity = (PulverizerBlockEntity) entity;
        this.level = inv.player.level();
        this.data = data;

        /* ================= PLAYER INVENTORY ================= */

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        /* ================= MACHINE SLOTS ================= */

        // Input slot (same position as Electric Furnace)
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, INPUT, 56, 35));

        // Main output slot (same position as Electric Furnace)
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, OUTPUT_DUST, 116, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });

        // Byproduct output slot (requested position: 115, 58)
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, OUTPUT_BYPRODUCT, 116, 59) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });

        addDataSlots(data);
    }

    /* ================= SHIFT CLICK ================= */

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = slots.get(index);
        if (slot == null || !slot.hasItem()) return ItemStack.EMPTY;

        ItemStack stack = slot.getItem();
        ItemStack copy = stack.copy();

        int playerSlotCount = 36;
        int machineSlotStart = playerSlotCount;
        int machineSlotEnd = machineSlotStart + 3;

        // Player inventory → machine input
        if (index < playerSlotCount) {
            if (!moveItemStackTo(stack, machineSlotStart, machineSlotStart + 1, false)) {
                return ItemStack.EMPTY;
            }
        }
        // Machine → player inventory
        else {
            if (!moveItemStackTo(stack, 0, playerSlotCount, false)) {
                return ItemStack.EMPTY;
            }
        }

        if (stack.isEmpty()) slot.set(ItemStack.EMPTY);
        else slot.setChanged();

        slot.onTake(player, stack);
        return copy;
    }

    /* ================= VALIDITY ================= */

    @Override
    public boolean stillValid(Player player) {
        return stillValid(
                ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player,
                ModBlocks.PULVERIZER.get()
        );
    }

    /* =========================
       CRAFTING PROGRESS
       ========================= */

    public int getCraftProgress() {
        int progress = data.get(0);
        int max = data.get(1);
        int arrowPixels = 24;

        return max > 0 ? progress * arrowPixels / max : 0;
    }

    /* =========================
       ENERGY BAR
       ========================= */

    public int getEnergyStored() {
        return data.get(2);
    }

    public int getMaxEnergyStored() {
        return data.get(3);
    }

    /* ================= PLAYER INVENTORY HELPERS ================= */

    protected void addPlayerInventory(Inventory inv) {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 9; c++) {
                this.addSlot(new Slot(inv, c + r * 9 + 9, 8 + c * 18, 84 + r * 18));
            }
        }
    }

    protected void addPlayerHotbar(Inventory inv) {
        for (int c = 0; c < 9; c++) {
            this.addSlot(new Slot(inv, c, 8 + c * 18, 142));
        }
    }
}
