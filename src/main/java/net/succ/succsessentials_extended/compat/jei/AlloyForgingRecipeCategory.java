package net.succ.succsessentials_extended.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.block.ModBlocks;
import net.succ.succsessentials_extended.compat.jei.JeiRenderHelper;
import net.succ.succsessentials_extended.recipe.AlloyForgingRecipe;
import org.jetbrains.annotations.Nullable;

public class AlloyForgingRecipeCategory implements IRecipeCategory<AlloyForgingRecipe> {

    public static final RecipeType<AlloyForgingRecipe> RECIPE_TYPE =
            new RecipeType<>(
                    ResourceLocation.fromNamespaceAndPath(
                            Succsessentials_extended.MOD_ID,
                            "alloy_forging"
                    ),
                    AlloyForgingRecipe.class
            );

    private static final int A_X = 56, A_Y = 25;
    private static final int B_X = 56, B_Y = 44;

    private final IDrawable background;
    private final IDrawable icon;

    public AlloyForgingRecipeCategory(IGuiHelper helper) {
        background = helper.createDrawable(
                ResourceLocation.fromNamespaceAndPath(
                        Succsessentials_extended.MOD_ID,
                        "textures/gui/container/alloy_forger.png"
                ),
                0, 0, 176, 85
        );

        icon = helper.createDrawableIngredient(
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
        JeiRenderHelper.addInputSlot(builder, A_X, A_Y, recipe.inputA());
        JeiRenderHelper.addInputSlot(builder, B_X, B_Y, recipe.inputB());

        JeiRenderHelper.addOutputSlot(
                builder,
                116,
                35,
                recipe.output(),
                recipe.getTotalEnergy()
        );
    }

    @Override
    public void draw(
            AlloyForgingRecipe recipe,
            IRecipeSlotsView slots,
            GuiGraphics graphics,
            double mouseX,
            double mouseY
    ) {
        JeiRenderHelper.drawEnergyText(graphics, recipe.getTotalEnergy(), 6, 72);
        JeiRenderHelper.drawStackCountOverlay(graphics, recipe.countA(), A_X, A_Y);
        JeiRenderHelper.drawStackCountOverlay(graphics, recipe.countB(), B_X, B_Y);
    }
}
