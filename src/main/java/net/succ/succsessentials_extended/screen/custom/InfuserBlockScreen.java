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

public class InfuserBlockScreen extends AbstractContainerScreen<InfuserBlockMenu> {

    /* =========================
       TEXTURE
       ========================= */

    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    Succsessentials_extended.MOD_ID,
                    "textures/gui/container/infuser.png"
            );

    /* =========================
       ENERGY BAR
       ========================= */

    private EnergyDisplayTooltipArea energyInfoArea;

    public InfuserBlockScreen(InfuserBlockMenu menu,
                              Inventory inv,
                              Component title) {
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
                menu.blockEntity.getEnergyStorage(null),
                8,
                64
        );
    }

    private void renderEnergyAreaTooltip(GuiGraphics guiGraphics,
                                         int mouseX,
                                         int mouseY,
                                         int x,
                                         int y) {

        if (isMouseAboveArea(mouseX, mouseY, x, y, 11, 11, 8, 64)) {
            guiGraphics.renderTooltip(
                    this.font,
                    energyInfoArea.getTooltips(),
                    Optional.empty(),
                    mouseX - x,
                    mouseY - y
            );
        }
    }

    public static boolean isMouseAboveArea(int mouseX,
                                           int mouseY,
                                           int x,
                                           int y,
                                           int offsetX,
                                           int offsetY,
                                           int width,
                                           int height) {
        return MouseUtil.isMouseOver(mouseX, mouseY,
                x + offsetX,
                y + offsetY,
                width,
                height);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        renderEnergyAreaTooltip(guiGraphics, mouseX, mouseY, x, y);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics,
                            float partialTick,
                            int mouseX,
                            int mouseY) {

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        // Base GUI
        guiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        // Energy bar
        energyInfoArea.render(guiGraphics);

        // Progress arrow
        int arrow = menu.getCraftProgress();
        guiGraphics.blit(GUI_TEXTURE, x + 79, y + 34, 176, 14, arrow + 1, 16);
    }

    @Override
    public void render(GuiGraphics guiGraphics,
                       int mouseX,
                       int mouseY,
                       float delta) {
        renderBackground(guiGraphics, mouseX, mouseY, delta);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
