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
import net.succ.succsessentials_extended.block.entity.custom.AlloyForgerBlockEntity;
import net.succ.succsessentials_extended.screen.ModMenuTypes;

public class AlloyForgerBlockMenu extends AbstractContainerMenu {

    public final AlloyForgerBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    private static final int INPUT_A = 0;
    private static final int INPUT_B = 1;
    private static final int OUTPUT  = 2;

    public AlloyForgerBlockMenu(int id, Inventory inv, FriendlyByteBuf buf) {
        this(id, inv,
                inv.player.level().getBlockEntity(buf.readBlockPos()),
                new SimpleContainerData(4) // ✅ 2 progress + 2 energy
        );
    }

    public AlloyForgerBlockMenu(int id, Inventory inv, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.ALLOY_FORGER_MENU.get(), id);

        this.blockEntity = (AlloyForgerBlockEntity) entity;
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        // Inputs
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, INPUT_A, 56, 25));
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, INPUT_B, 56, 44));

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
        int arrowPixels = 24;

        return max > 0 ? progress * arrowPixels / max : 0;
    }

    /* =========================
       ENERGY
       ========================= */

    /**
     * Converts the synced FE value into a pixel height for the GUI energy bar.
     * data index mapping (must match BlockEntity ContainerData):
     * 0 = progress
     * 1 = maxProgress
     * 2 = energyStored
     * 3 = maxEnergyStored
     */
    public int getEnergyBarHeight() {
        int energy = data.get(2);
        int max = data.get(3);
        int barPixels = 48; // height of energy bar in pixels in your texture

        return max > 0 ? energy * barPixels / max : 0;
    }

    /* =========================
       SHIFT CLICK (ENERGY SAFE)
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
                ModBlocks.ALLOY_FORGER.get()
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
