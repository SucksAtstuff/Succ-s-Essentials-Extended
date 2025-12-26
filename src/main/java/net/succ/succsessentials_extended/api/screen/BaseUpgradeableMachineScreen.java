package net.succ.succsessentials_extended.api.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.succ.succsessentials_extended.api.machine.UpgradeableMachine;
import net.succ.succsessentials_extended.util.MouseUtil;

import java.util.List;
import java.util.Optional;

/**
 * ============================================================
 * BaseUpgradeableMachineScreen
 *
 * Shared screen logic for ALL machines that support upgrades.
 *
 * Provides:
 *  - Speed upgrade tooltip
 *  - Efficiency upgrade tooltip
 *  - Safe tooltip positioning (no overlap)
 *
 * ALL powered machine screens should extend this.
 * ============================================================
 */
public abstract class BaseUpgradeableMachineScreen<T extends BaseUpgradeableMachineMenu>
        extends AbstractContainerScreen<T> {

    /* ================= UPGRADE SLOT POSITIONS ================= */

    protected static final int UPGRADE_SPEED_X = 8;
    protected static final int UPGRADE_SPEED_Y = 175;

    protected static final int UPGRADE_EFF_X = 26;
    protected static final int UPGRADE_EFF_Y = 175;

    protected static final int SLOT_SIZE = 18;
    protected static final int TOOLTIP_Y_OFFSET = 40;

    protected BaseUpgradeableMachineScreen(
            T menu,
            Inventory inv,
            Component title
    ) {
        super(menu, inv, title);
    }

    /**
     * Renders upgrade tooltips.
     * Call this from renderLabels().
     */
    protected void renderUpgradeTooltips(
            GuiGraphics guiGraphics,
            int mouseX,
            int mouseY
    ) {
        UpgradeableMachine machine = menu.getMachine();
        if (machine == null) return;

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        // ---------------- Speed Upgrade ----------------
        if (MouseUtil.isMouseOver(
                mouseX, mouseY,
                x + UPGRADE_SPEED_X,
                y + UPGRADE_SPEED_Y,
                SLOT_SIZE,
                SLOT_SIZE
        )) {
            guiGraphics.renderTooltip(
                    this.font,
                    List.of(
                            Component.literal("Speed Upgrade"),
                            Component.literal(String.format(
                                    "• Multiplier: x%.2f",
                                    machine.getSpeedMultiplier()
                            )),
                            Component.literal(String.format(
                                    "• Level: %d / 8",
                                    machine.getUpgradeInventory()
                                            .getStackInSlot(0)
                                            .getCount()
                            ))
                    ),
                    Optional.empty(),
                    mouseX - x,
                    mouseY - y - TOOLTIP_Y_OFFSET
            );
        }

        // ---------------- Efficiency Upgrade ----------------
        if (MouseUtil.isMouseOver(
                mouseX, mouseY,
                x + UPGRADE_EFF_X,
                y + UPGRADE_EFF_Y,
                SLOT_SIZE,
                SLOT_SIZE
        )) {
            guiGraphics.renderTooltip(
                    this.font,
                    List.of(
                            Component.literal("Efficiency Upgrade"),
                            Component.literal(String.format(
                                    "• Energy Use: %.0f%%",
                                    machine.getEnergyUsageMultiplier() * 100.0
                            )),
                            Component.literal(String.format(
                                    "• Level: %d / 8",
                                    machine.getUpgradeInventory()
                                            .getStackInSlot(1)
                                            .getCount()
                            ))
                    ),
                    Optional.empty(),
                    mouseX - x,
                    mouseY - y - TOOLTIP_Y_OFFSET
            );
        }
    }
}
