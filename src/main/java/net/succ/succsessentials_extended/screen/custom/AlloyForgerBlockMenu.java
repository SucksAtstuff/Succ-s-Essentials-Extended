package net.succ.succsessentials_extended.screen.custom;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.succ.succsessentials_extended.block.ModBlocks;
import net.succ.succsessentials_extended.block.entity.custom.AlloyForgerBlockEntity;
import net.succ.succsessentials_extended.screen.ModMenuTypes;

public class AlloyForgerBlockMenu extends AbstractContainerMenu {

    public final AlloyForgerBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    /* ==========================================================
       SLOT INDEXES (MUST MATCH BLOCK ENTITY)
       ========================================================== */
    private static final int INPUT_SLOT_A = 0;
    private static final int INPUT_SLOT_B = 1;
    private static final int FUEL_SLOT    = 2;
    private static final int OUTPUT_SLOT  = 3;

    /* ==========================================================
       CONSTRUCTORS
       ========================================================== */
    public AlloyForgerBlockMenu(int containerId,
                                Inventory inv,
                                FriendlyByteBuf extraData) {

        this(containerId,
                inv,
                inv.player.level().getBlockEntity(extraData.readBlockPos()),
                new SimpleContainerData(4));
    }

    public AlloyForgerBlockMenu(int containerId,
                                Inventory inv,
                                BlockEntity entity,
                                ContainerData data) {

        super(ModMenuTypes.ALLOY_FORGER_MENU.get(), containerId);

        this.blockEntity = (AlloyForgerBlockEntity) entity;
        this.level = inv.player.level();
        this.data = data;

        /* ======================================================
           PLAYER INVENTORY FIRST (same pattern you use)
           ====================================================== */
        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        /* ======================================================
           BLOCK ENTITY SLOTS (MATCH FURNACE LAYOUT)
           ====================================================== */

        // Input A (top input)
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler,
                INPUT_SLOT_A, 56, 17));

        // Input B (bottom input)
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler,
                INPUT_SLOT_B, 56, 36));

        // Fuel slot
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler,
                FUEL_SLOT, 56, 55) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return AbstractFurnaceBlockEntity.isFuel(stack);
            }
        });

        // Output slot
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler,
                OUTPUT_SLOT, 116, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });

        addDataSlots(data);
    }

    /* ==========================================================
       PROGRESS HELPERS (USED BY SCREEN)
       ========================================================== */

    public boolean isBurning() {
        return data.get(2) > 0;
    }

    public int getBurnProgress() {
        int burn = data.get(2);
        int maxBurn = data.get(3);
        int flamePixels = 13;

        return maxBurn != 0 && burn != 0
                ? burn * flamePixels / maxBurn
                : 0;
    }

    public int getCraftProgress() {
        int progress = data.get(0);
        int maxProgress = data.get(1);
        int arrowPixels = 24;

        return maxProgress != 0 && progress != 0
                ? progress * arrowPixels / maxProgress
                : 0;
    }

    /* ==========================================================
       SHIFT-CLICK LOGIC (UPDATED FOR 4 TE SLOTS)
       ========================================================== */

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INV_ROW_COUNT = 3;
    private static final int PLAYER_INV_COLUMN_COUNT = 9;
    private static final int PLAYER_INV_SLOT_COUNT =
            PLAYER_INV_COLUMN_COUNT * PLAYER_INV_ROW_COUNT;

    private static final int VANILLA_SLOT_COUNT =
            HOTBAR_SLOT_COUNT + PLAYER_INV_SLOT_COUNT;

    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_FIRST_SLOT_INDEX =
            VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    private static final int TE_SLOT_COUNT = 4;

    @Override
    public ItemStack quickMoveStack(Player player, int index) {

        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copy = sourceStack.copy();

        // From player → block
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {

            if (AbstractFurnaceBlockEntity.isFuel(sourceStack)) {
                if (!moveItemStackTo(sourceStack,
                        TE_FIRST_SLOT_INDEX + FUEL_SLOT,
                        TE_FIRST_SLOT_INDEX + FUEL_SLOT + 1,
                        false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!moveItemStackTo(sourceStack,
                        TE_FIRST_SLOT_INDEX,
                        TE_FIRST_SLOT_INDEX + 2,
                        false)) {
                    return ItemStack.EMPTY;
                }
            }

        }
        // From block → player
        else if (index < TE_FIRST_SLOT_INDEX + TE_SLOT_COUNT) {

            if (!moveItemStackTo(sourceStack,
                    VANILLA_FIRST_SLOT_INDEX,
                    VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT,
                    false)) {
                return ItemStack.EMPTY;
            }

        } else {
            return ItemStack.EMPTY;
        }

        if (sourceStack.isEmpty()) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }

        sourceSlot.onTake(player, sourceStack);
        return copy;
    }

    /* ==========================================================
       VALIDITY
       ========================================================== */
    @Override
    public boolean stillValid(Player player) {
        return stillValid(
                ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player,
                ModBlocks.ALLOY_FORGER.get()
        );
    }

    /* ==========================================================
       PLAYER INVENTORY LAYOUT
       ========================================================== */
    private void addPlayerInventory(Inventory playerInventory) {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory,
                        col + row * 9 + 9,
                        8 + col * 18,
                        84 + row * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(playerInventory,
                    col,
                    8 + col * 18,
                    142));
        }
    }
}
