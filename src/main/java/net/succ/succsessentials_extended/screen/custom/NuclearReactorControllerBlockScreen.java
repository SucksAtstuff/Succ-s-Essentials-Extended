package net.succ.succsessentials_extended.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.screen.renderer.EnergyDisplayTooltipArea;
import net.succ.succsessentials_extended.util.MouseUtil;

import java.util.Optional;

/**
 * ============================================================
 * NuclearReactorControllerBlockScreen
 *
 * Controller UI for the Nuclear Reactor.
 *
 * - NO upgrades
 * - Energy display
 * - Structure status warning
 * ============================================================
 */
public class NuclearReactorControllerBlockScreen
        extends AbstractContainerScreen<NuclearReactorControllerBlockMenu> {

    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    Succsessentials_extended.MOD_ID,
                    "textures/gui/container/nuclear_reactor.png"
            );

    /* ================= ENERGY ================= */

    private EnergyDisplayTooltipArea energyInfoArea;

    public NuclearReactorControllerBlockScreen(
            NuclearReactorControllerBlockMenu menu,
            Inventory inv,
            Component title
    ) {
        super(menu, inv, title);
    }

    @Override
    protected void init() {
        super.init();

        // Hide vanilla labels
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;

        assignEnergyInfoArea();
    }

    private void assignEnergyInfoArea() {
        energyInfoArea = new EnergyDisplayTooltipArea(
                ((width - imageWidth) / 2) + 11,
                ((height - imageHeight) / 2) + 11,
                menu.controller.getEnergyStorage(null),
                8,
                64
        );
    }

    /* ================= LABELS ================= */

    @Override
    protected void renderLabels(
            GuiGraphics guiGraphics,
            int mouseX,
            int mouseY
    ) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        // Energy tooltip
        if (MouseUtil.isMouseOver(mouseX, mouseY, x + 11, y + 11, 8, 64)) {
            guiGraphics.renderTooltip(
                    this.font,
                    energyInfoArea.getTooltips(),
                    Optional.empty(),
                    mouseX - x,
                    mouseY - y
            );
        }

        // Structure warning
        if (!menu.controller.isFormed()) {
            guiGraphics.drawString(
                    this.font,
                    Component.translatable(
                            "message.succsessentials_extended.structure_incomplete"
                    ),
                    8,
                    6,
                    0xFF5555,
                    false
            );
        }
    }

    /* ================= BACKGROUND ================= */

    @Override
    protected void renderBg(
            GuiGraphics guiGraphics,
            float partialTick,
            int mouseX,
            int mouseY
    ) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        // Base GUI
        guiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        // Energy bar
        energyInfoArea.render(guiGraphics);
    }

    /* ================= RENDER ================= */

    @Override
    public void render(
            GuiGraphics guiGraphics,
            int mouseX,
            int mouseY,
            float delta
    ) {
        renderBackground(guiGraphics, mouseX, mouseY, delta);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
