package net.succ.succsessentials_extended.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.succ.succsessentials_extended.Succsessentials_extended;

public class AlloyForgerBlockScreen
        extends AbstractContainerScreen<AlloyForgerBlockMenu> {

    /* ==========================================================
       TEXTURE
       ========================================================== */
    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    Succsessentials_extended.MOD_ID,
                    "textures/gui/container/alloy_forger.png"
            );

    public AlloyForgerBlockScreen(AlloyForgerBlockMenu menu,
                                  Inventory playerInventory,
                                  Component title) {
        super(menu, playerInventory, title);
    }

    /* ==========================================================
       INIT
       ========================================================== */
    @Override
    protected void init() {
        super.init();

        // Hide default labels (same trick you used)
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }

    /* ==========================================================
       BACKGROUND RENDER
       ========================================================== */
    @Override
    protected void renderBg(GuiGraphics guiGraphics,
                            float partialTick,
                            int mouseX,
                            int mouseY) {

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        /* ===============================
           MAIN BACKGROUND
           =============================== */
        guiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        /* ===============================
           FUEL FLAME (same as furnace)
           =============================== */
        if (menu.isBurning()) {
            int burnHeight = menu.getBurnProgress();

            guiGraphics.blit(
                    GUI_TEXTURE,

                    // X: centered under arrow
                    x + 83,

                    // Y: below arrow, animated upward
                    y + 54 + 12 - 3 - burnHeight,

                    // Texture coords (vanilla flame)
                    176, 12 - burnHeight,

                    // Flame size
                    14,
                    burnHeight + 1
            );
        }


        /* ===============================
           ARROW PROGRESS
           =============================== */
        int arrowWidth = menu.getCraftProgress();
        guiGraphics.blit(
                GUI_TEXTURE,
                x + 79,
                y + 34,
                176,
                14,
                arrowWidth + 1,
                16
        );
    }

    /* ==========================================================
       FULL RENDER
       ========================================================== */
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
