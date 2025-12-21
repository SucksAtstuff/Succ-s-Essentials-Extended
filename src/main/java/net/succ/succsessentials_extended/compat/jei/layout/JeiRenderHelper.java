package net.succ.succsessentials_extended.compat.jei.layout;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * Shared JEI rendering & layout helpers.
 *
 * - All coordinates passed in are LOGICAL (texture-local)
 * - This class applies drawable padding internally
 * - Recipe categories never apply offsets themselves
 */
public final class JeiRenderHelper {

    private JeiRenderHelper() {}

    /* ================================================== */
    /*                      CONSTANTS                     */
    /* ================================================== */

    public static final int SLOT_SIZE = 16;
    public static final int OVERLAY_Z = 200;

    /** Visual max energy used for JEI scaling */
    public static final int JEI_MAX_ENERGY = 10_000;

    /** Energy bar dimensions */
    public static final int ENERGY_BAR_WIDTH = 8;
    public static final int ENERGY_BAR_HEIGHT = 64;

    /* ================================================== */
    /*                 INTERNAL SNAP HELPERS              */
    /* ================================================== */

    private static int snapX(int x) {
        return x;
    }

    private static int snapY(int y) {
        return y;
    }

    /* ================================================== */
    /*                    SLOT HELPERS                    */
    /* ================================================== */

    public static void addInputSlot(
            IRecipeLayoutBuilder builder,
            int x,
            int y,
            Ingredient ingredient
    ) {
        builder.addSlot(
                RecipeIngredientRole.INPUT,
                snapX(x),
                snapY(y)
        ).addIngredients(ingredient);
    }

    public static void addOutputSlot(
            IRecipeLayoutBuilder builder,
            int x,
            int y,
            ItemStack output,
            int totalEnergy
    ) {
        builder.addSlot(
                        RecipeIngredientRole.OUTPUT,
                        snapX(x),
                        snapY(y)
                )
                .addItemStack(output)
                .addRichTooltipCallback((slotView, tooltip) ->
                        tooltip.add(Component.literal("Energy: " + totalEnergy + " FE"))
                );
    }

    /* ================================================== */
    /*                 STACK COUNT OVERLAY                */
    /* ================================================== */

    public static void drawStackCountOverlay(
            GuiGraphics graphics,
            int count,
            int slotX,
            int slotY
    ) {
        if (count <= 1) return;

        var font = Minecraft.getInstance().font;
        String text = String.valueOf(count);

        int x = snapX(slotX) + SLOT_SIZE - font.width(text) - 1;
        int y = snapY(slotY) + SLOT_SIZE - 9;

        graphics.pose().pushPose();
        graphics.pose().translate(0, 0, OVERLAY_Z);

        graphics.drawString(font, text, x, y, 0xFFFFFF, true);

        graphics.pose().popPose();
    }

    /* ================================================== */
    /*                    ENERGY BAR                      */
    /* ================================================== */

    public static void drawEnergyBar(
            GuiGraphics graphics,
            int energy,
            int x,
            int y
    ) {
        int clamped = Math.min(energy, JEI_MAX_ENERGY);
        int stored = (int) (ENERGY_BAR_HEIGHT * (clamped / (float) JEI_MAX_ENERGY));

        int drawX = snapX(x);
        int drawY = snapY(y);

        graphics.pose().pushPose();
        graphics.pose().translate(0, 0, OVERLAY_Z);

        graphics.fillGradient(
                drawX,
                drawY + (ENERGY_BAR_HEIGHT - stored),
                drawX + ENERGY_BAR_WIDTH,
                drawY + ENERGY_BAR_HEIGHT,
                0xffb51500,
                0xff600b00
        );

        graphics.pose().popPose();
    }

    public static void drawEnergyText(
            GuiGraphics graphics,
            int energy,
            int x,
            int y
    ) {
        graphics.drawString(
                Minecraft.getInstance().font,
                "Energy: " + energy + " FE",
                snapX(x) + 15,
                snapY(y),
                0xFF5555,
                false
        );
    }

    /* ================================================== */
    /*                   TOOLTIP HELPERS                  */
    /* ================================================== */

    public static boolean isMouseOverEnergyBar(
            double mouseX,
            double mouseY,
            int barX,
            int barY
    ) {
        int x = snapX(barX);
        int y = snapY(barY);

        return mouseX >= x && mouseX < x + ENERGY_BAR_WIDTH
                && mouseY >= y && mouseY < y + ENERGY_BAR_HEIGHT;
    }

    public static void drawEnergyTooltip(
            GuiGraphics graphics,
            double mouseX,
            double mouseY,
            int energy
    ) {
        graphics.renderTooltip(
                Minecraft.getInstance().font,
                Component.literal(energy + " FE"),
                (int) mouseX,
                (int) mouseY
        );
    }
}
