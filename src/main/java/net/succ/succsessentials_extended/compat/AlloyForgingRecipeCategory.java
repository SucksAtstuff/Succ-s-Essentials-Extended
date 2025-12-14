package net.succ.succsessentials_extended.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.block.ModBlocks;
import net.succ.succsessentials_extended.recipe.AlloyForgingRecipe;
import org.jetbrains.annotations.Nullable;

public class AlloyForgingRecipeCategory implements IRecipeCategory<AlloyForgingRecipe> {

    public static final ResourceLocation UID =
            ResourceLocation.fromNamespaceAndPath(Succsessentials_extended.MOD_ID, "alloy_forging");

    public static final RecipeType<AlloyForgingRecipe> RECIPE_TYPE =
            new RecipeType<>(UID, AlloyForgingRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public AlloyForgingRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(
                ResourceLocation.fromNamespaceAndPath(
                        Succsessentials_extended.MOD_ID,
                        "textures/gui/container/alloy_forger.png"
                ),
                0, 0, 176, 85
        );

        this.icon = helper.createDrawableIngredient(
                VanillaTypes.ITEM_STACK,
                new ItemStack(ModBlocks.ALLOY_FORGER.get())
        );
    }

    @Override
    public RecipeType<AlloyForgingRecipe> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.succsessentials_extended.alloy_forger");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(
            IRecipeLayoutBuilder builder,
            AlloyForgingRecipe recipe,
            IFocusGroup focuses
    ) {

        /* ================= INPUTS ================= */

        builder.addSlot(RecipeIngredientRole.INPUT, 56, 25)
                .addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.INPUT, 56, 44)
                .addIngredients(recipe.getIngredients().get(1));

        /* ================= OUTPUT ================= */

        builder.addSlot(RecipeIngredientRole.OUTPUT, 116, 35)
                .addItemStack(recipe.output())
                // Tooltip showing total energy cost
                .addRichTooltipCallback((slotView, tooltip) -> {
                    tooltip.add(Component.literal("Energy: " + recipe.getTotalEnergy() + " FE"));
                });

    }

    /* ================= EXTRA DRAWING ================= */

    @Override
    public void draw(
            AlloyForgingRecipe recipe,
            IRecipeSlotsView recipeSlotsView,
            GuiGraphics graphics,
            double mouseX,
            double mouseY
    ) {
        // Draw energy cost as text in the JEI recipe GUI
        graphics.drawString(
                Minecraft.getInstance().font,
                "Energy: " + recipe.getTotalEnergy() + " FE",
                6,
                72,
                0xFFFFFF,
                false
        );
    }
}
