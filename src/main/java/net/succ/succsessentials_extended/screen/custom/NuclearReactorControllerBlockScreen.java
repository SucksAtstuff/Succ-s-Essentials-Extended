package net.succ.succsessentials_extended.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.screen.renderer.EnergyDisplayTooltipArea;
import net.succ.succsessentials_extended.screen.renderer.FluidTankRenderer;
import net.succ.succsessentials_extended.util.MouseUtil;

import java.util.Optional;

public class NuclearReactorControllerBlockScreen
        extends AbstractContainerScreen<NuclearReactorControllerBlockMenu> {

    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    Succsessentials_extended.MOD_ID,
                    "textures/gui/container/nuclear_reactor.png"
            );

    // tank inner area: 1 px inside the border drawn at (149, 11, 16, 46), extended 2 px on each side
    private static final int TANK_INNER_X  = 149;
    private static final int TANK_INNER_Y  = 7;
    private static final int TANK_INNER_W  = 16;
    private static final int TANK_INNER_H  = 50;

    private EnergyDisplayTooltipArea energyInfoArea;
    private FluidTankRenderer waterTankRenderer;
    private FluidStack currentWaterFluid = FluidStack.EMPTY;

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

        this.inventoryLabelY = 10000;
        this.titleLabelY     = 10000;

        energyInfoArea = new EnergyDisplayTooltipArea(
                ((width - imageWidth) / 2) + 11,
                ((height - imageHeight) / 2) + 11,
                menu.controller.getEnergyStorage(null),
                8, 64
        );

        waterTankRenderer = new FluidTankRenderer(10_000, true, TANK_INNER_W, TANK_INNER_H);
    }

    /* ================= LABELS ================= */

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        // Energy tooltip
        if (MouseUtil.isMouseOver(mouseX, mouseY, x + 11, y + 11, 8, 64)) {
            guiGraphics.renderTooltip(this.font, energyInfoArea.getTooltips(),
                    Optional.empty(), mouseX - x, mouseY - y);
        }

        // Water tooltip (over the full outer tank border area)
        if (MouseUtil.isMouseOver(mouseX, mouseY, x + 149, y + 11, 16, 46)) {
            guiGraphics.renderTooltip(this.font,
                    waterTankRenderer.getTooltip(currentWaterFluid, TooltipFlag.Default.NORMAL),
                    Optional.empty(), mouseX - x, mouseY - y);
        }

        // Structure warning
        if (!menu.controller.isFormed()) {
            guiGraphics.drawString(this.font,
                    Component.translatable("message.succsessentials_extended.structure_incomplete"),
                    8, 6, 0xFF5555, false);
        }

        // Meltdown warning — flashes every 500 ms
        if (menu.getOverheatTicks() > 0 && (System.currentTimeMillis() / 500) % 2 == 0) {
            guiGraphics.drawCenteredString(this.font,
                    Component.literal("! NUCLEAR MELTDOWN IMMINENT !"),
                    imageWidth / 2, 6, 0xFF6600);
        }
    }

    /* ================= BACKGROUND ================= */

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        energyInfoArea.render(guiGraphics);

        // Water tank fill — use actual mB in the tank, not the tick-based countdown
        int tankWater = menu.getTankWater();
        currentWaterFluid = tankWater > 0 ? new FluidStack(Fluids.WATER, tankWater) : FluidStack.EMPTY;
        waterTankRenderer.render(guiGraphics, x + TANK_INNER_X, y + TANK_INNER_Y, currentWaterFluid);
    }

    /* ================= RENDER ================= */

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics, mouseX, mouseY, delta);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
