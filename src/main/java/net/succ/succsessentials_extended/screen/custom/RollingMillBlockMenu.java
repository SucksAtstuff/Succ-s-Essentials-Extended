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
import net.succ.succsessentials_extended.block.entity.custom.RollingMillBlockEntity;
import net.succ.succsessentials_extended.screen.ModMenuTypes;

/**
 * Rolling Mill menu with shared upgrade slots.
 */
public class RollingMillBlockMenu
        extends BaseUpgradeableMachineMenu {

    public final RollingMillBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    private static final int INPUT  = 0;
    private static final int OUTPUT = 1;

    /* ================= CLIENT ================= */

    public RollingMillBlockMenu(
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

    public RollingMillBlockMenu(
            int id,
            Inventory inv,
            BlockEntity entity,
            ContainerData data
    ) {
        super(
                ModMenuTypes.ROLLING_MILL_MENU.get(),
                id,
                inv,
                (RollingMillBlockEntity) entity
        );

        this.blockEntity = (RollingMillBlockEntity) entity;
        this.level = inv.player.level();
        this.data = data;

        /* ================= MACHINE SLOTS ================= */

        // Input (centered like a furnace-style single input)
        addSlot(new SlotItemHandler(blockEntity.itemHandler, INPUT, 56, 35));

        // Output
        addSlot(new SlotItemHandler(blockEntity.itemHandler, OUTPUT, 116, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });

        addDataSlots(data);
    }

    /* ================= PROGRESS ================= */

    public int getCraftProgress() {
        int progress = data.get(0);
        int max = data.get(1);
        return max > 0 ? progress * 24 / max : 0;
    }

    /* ================= ENERGY ================= */

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
                ModBlocks.ROLLING_MILL.get()
        );
    }
}
