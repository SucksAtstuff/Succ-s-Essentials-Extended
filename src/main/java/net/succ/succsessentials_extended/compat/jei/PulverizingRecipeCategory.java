package net.succ.succsessentials_extended.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.block.ModBlocks;
import net.succ.succsessentials_extended.recipe.PulverizingRecipe;
import org.jetbrains.annotations.Nullable;

public class PulverizingRecipeCategory implements IRecipeCategory<PulverizingRecipe> {

    public static final RecipeType<PulverizingRecipe> RECIPE_TYPE =
            new RecipeType<>(
                    ResourceLocation.fromNamespaceAndPath(
                            Succsessentials_extended.MOD_ID,
                            "pulverizing"
                    ),
                    PulverizingRecipe.class
            );

    private final IDrawable background;
    private final IDrawable icon;

    public PulverizingRecipeCategory(IGuiHelper helper) {
        background = helper.createDrawable(
                ResourceLocation.fromNamespaceAndPath(
                        Succsessentials_extended.MOD_ID,
                        "textures/gui/container/pulverizer.png"
                ),
                0, 0, 176, 85
        );

        icon = helper.createDrawableIngredient(
                VanillaTypes.ITEM_STACK,
                new ItemStack(ModBlocks.PULVERIZER.get())
        );
    }

    @Override
    public RecipeType<PulverizingRecipe> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.succsessentials_extended.pulverizer");
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
            PulverizingRecipe recipe,
            IFocusGroup focuses
    ) {
        JeiRenderHelper.addInputSlot(
                builder,
                56,
                35,
                recipe.input()
        );

        JeiRenderHelper.addOutputSlot(
                builder,
                104,
                35,
                recipe.output(),
                recipe.getTotalEnergy()
        );

        if (!recipe.byproduct().isEmpty()) {
            JeiRenderHelper.addOutputSlot(
                    builder,
                    128,
                    35,
                    recipe.byproduct(),
                    recipe.getTotalEnergy()
            );
        }
    }
}
