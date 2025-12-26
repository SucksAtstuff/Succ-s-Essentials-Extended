package net.succ.succsessentials_extended.screen.custom;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.succ.succsessentials_extended.api.screen.BaseUpgradeableMachineMenu;
import net.succ.succsessentials_extended.block.ModBlocks;
import net.succ.succsessentials_extended.block.entity.custom.WireDrawerBlockEntity;
import net.succ.succsessentials_extended.screen.ModMenuTypes;

/**
 * Wire Drawer menu with shared upgrade slots.
 */
public class WireDrawerBlockMenu
        extends BaseUpgradeableMachineMenu {

    public final WireDrawerBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    private static final int INPUT  = 0;
    private static final int OUTPUT = 1;

    /* ================= CLIENT ================= */

    public WireDrawerBlockMenu(
            int id,
            Inventory inv,
            FriendlyByteBuf buf
    ) {
        this(
                id,
                inv,
                inv.player.level().getBlockEntity(buf.readBlockPos()),
                new SimpleContainerData(4)
        );
    }

    /* ================= SERVER ================= */

    public WireDrawerBlockMenu(
            int id,
            Inventory inv,
            BlockEntity entity,
            ContainerData data
    ) {
        super(
                ModMenuTypes.WIRE_DRAWER_MENU.get(),
                id,
                inv,
                (WireDrawerBlockEntity) entity
        );

        this.blockEntity = (WireDrawerBlockEntity) entity;
        this.level = inv.player.level();
        this.data = data;

        /* ================= MACHINE SLOTS ================= */

        addSlot(new SlotItemHandler(blockEntity.itemHandler, INPUT, 56, 35));

        addSlot(new SlotItemHandler(blockEntity.itemHandler, OUTPUT, 116, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });

        addDataSlots(data);
    }

    public int getCraftProgress() {
        int progress = data.get(0);
        int max = data.get(1);
        return max > 0 ? progress * 24 / max : 0;
    }

    public int getEnergyBarHeight() {
        int energy = data.get(2);
        int max = data.get(3);
        return max > 0 ? energy * 48 / max : 0;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(
                ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player,
                ModBlocks.WIRE_DRAWER.get()
        );
    }
}
