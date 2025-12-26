package net.succ.succsessentials_extended.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.api.screen.BaseUpgradeableMachineScreen;
import net.succ.succsessentials_extended.screen.renderer.EnergyDisplayTooltipArea;
import net.succ.succsessentials_extended.util.MouseUtil;

import java.util.Optional;

public class RollingMillBlockScreen
        extends BaseUpgradeableMachineScreen<RollingMillBlockMenu> {

    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    Succsessentials_extended.MOD_ID,
                    "textures/gui/container/rolling_mill.png"
            );

    private EnergyDisplayTooltipArea energyInfoArea;

    public RollingMillBlockScreen(
            RollingMillBlockMenu menu,
            Inventory inv,
            Component title
    ) {
        super(menu, inv, title);
        this.imageHeight = 198;
    }

    @Override
    protected void init() {
        super.init();

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

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        if (MouseUtil.isMouseOver(mouseX, mouseY, x + 11, y + 11, 8, 64)) {
            guiGraphics.renderTooltip(
                    this.font,
                    energyInfoArea.getTooltips(),
                    Optional.empty(),
                    mouseX - x,
                    mouseY - y
            );
        }

        renderUpgradeTooltips(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        energyInfoArea.render(guiGraphics);

        // Progress arrow (same coords as alloy forger unless your texture differs)
        int arrow = menu.getCraftProgress();
        guiGraphics.blit(
                GUI_TEXTURE,
                x + 79,
                y + 34,
                176,
                14,
                arrow + 1,
                16
        );
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics, mouseX, mouseY, delta);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
