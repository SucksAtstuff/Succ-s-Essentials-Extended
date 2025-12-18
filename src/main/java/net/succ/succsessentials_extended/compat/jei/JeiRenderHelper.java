package net.succ.succsessentials_extended.compat.jei;

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
 * This class intentionally contains:
 *  - Z-layer fixes
 *  - Slot helpers
 *  - Overlay helpers
 *
 * So JEI quirks are isolated to ONE place.
 */
public final class JeiRenderHelper {

    private JeiRenderHelper() {}

    /* ================= CONSTANTS ================= */

    public static final int SLOT_SIZE = 16;
    public static final int OVERLAY_Z = 200;

    /* ================= SLOT HELPERS ================= */

    public static void addInputSlot(
            IRecipeLayoutBuilder builder,
            int x,
            int y,
            Ingredient ingredient
    ) {
        builder.addSlot(RecipeIngredientRole.INPUT, x, y)
                .addIngredients(ingredient);
    }

    public static void addOutputSlot(
            IRecipeLayoutBuilder builder,
            int x,
            int y,
            ItemStack output,
            int totalEnergy
    ) {
        builder.addSlot(RecipeIngredientRole.OUTPUT, x, y)
                .addItemStack(output)
                .addRichTooltipCallback((slotView, tooltip) ->
                        tooltip.add(
                                Component.literal("Energy: " + totalEnergy + " FE")
                        )
                );
    }

    /* ================= RENDER HELPERS ================= */

    /**
     * Draws vanilla-style stack count overlay ABOVE JEI items.
     */
    public static void drawStackCountOverlay(
            GuiGraphics graphics,
            int count,
            int slotX,
            int slotY
    ) {
        if (count <= 1) return;

        String text = String.valueOf(count);
        var font = Minecraft.getInstance().font;

        int textWidth = font.width(text);
        int x = slotX + SLOT_SIZE - textWidth - 1;
        int y = slotY + SLOT_SIZE - 9;

        graphics.pose().pushPose();
        graphics.pose().translate(0, 0, OVERLAY_Z);

        graphics.drawString(
                font,
                text,
                x,
                y,
                0xFFFFFF,
                true
        );

        graphics.pose().popPose();
    }

    /**
     * Draws energy cost text in the lower-left corner.
     */
    public static void drawEnergyText(
            GuiGraphics graphics,
            int energy,
            int x,
            int y
    ) {
        graphics.drawString(
                Minecraft.getInstance().font,
                "Energy: " + energy + " FE",
                x,
                y,
                0xFFFFFF,
                false
        );
    }
}
