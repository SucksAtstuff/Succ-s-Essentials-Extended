package net.succ.succsessentials_extended.screen.custom;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.succ.succsessentials_extended.block.ModBlocks;
import net.succ.succsessentials_extended.block.entity.custom.InfuserBlockEntity;
import net.succ.succsessentials_extended.screen.ModMenuTypes;

public class InfuserBlockMenu extends AbstractContainerMenu {

    /* =========================
       MACHINE
       ========================= */
    public final InfuserBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    /* =========================
       SLOTS
       ========================= */
    private static final int INPUT_INGOT = 0;
    private static final int INPUT_DUST  = 1;
    private static final int OUTPUT      = 2;

    /* =========================
       CONSTRUCTORS
       ========================= */

    public InfuserBlockMenu(int id, Inventory inv, FriendlyByteBuf buf) {
        this(
                id,
                inv,
                inv.player.level().getBlockEntity(buf.readBlockPos()),
                new SimpleContainerData(4) // progress + maxProgress + energy + maxEnergy
        );
    }

    public InfuserBlockMenu(int id, Inventory inv, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.INFUSER_MENU.get(), id);

        this.blockEntity = (InfuserBlockEntity) entity;
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        /* =========================
           MACHINE SLOTS
           ========================= */

        // Ingot input
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, INPUT_INGOT, 56, 25));

        // Dust input
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, INPUT_DUST, 56, 44));

        // Output
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, OUTPUT, 116, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });

        addDataSlots(data);
    }

    /* =========================
       PROGRESS
       ========================= */

    public int getCraftProgress() {
        int progress = data.get(0);
        int max = data.get(1);
        int pixels = 24;

        return max > 0 ? progress * pixels / max : 0;
    }

    /* =========================
       ENERGY
       ========================= */

    public int getEnergyBarHeight() {
        int energy = data.get(2);
        int max = data.get(3);
        int pixels = 48;

        return max > 0 ? energy * pixels / max : 0;
    }

    /* =========================
       SHIFT CLICK
       ========================= */

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = slots.get(index);
        if (!slot.hasItem()) return ItemStack.EMPTY;

        ItemStack stack = slot.getItem();
        ItemStack copy = stack.copy();

        int playerSlots = 36;
        int machineStart = playerSlots;
        int machineEnd = machineStart + 3;

        if (index < playerSlots) {
            if (!moveItemStackTo(stack, machineStart, machineEnd, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            if (!moveItemStackTo(stack, 0, playerSlots, false)) {
                return ItemStack.EMPTY;
            }
        }

        if (stack.isEmpty()) slot.set(ItemStack.EMPTY);
        else slot.setChanged();

        return copy;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(
                ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player,
                ModBlocks.INFUSER.get()
        );
    }

    /* =========================
       PLAYER INVENTORY
       ========================= */

    private void addPlayerInventory(Inventory inv) {
        for (int r = 0; r < 3; ++r)
            for (int c = 0; c < 9; ++c)
                this.addSlot(new Slot(inv, c + r * 9 + 9, 8 + c * 18, 84 + r * 18));
    }

    private void addPlayerHotbar(Inventory inv) {
        for (int c = 0; c < 9; ++c)
            this.addSlot(new Slot(inv, c, 8 + c * 18, 142));
    }
}
