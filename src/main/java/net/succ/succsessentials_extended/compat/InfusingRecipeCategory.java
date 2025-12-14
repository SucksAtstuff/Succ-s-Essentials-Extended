package net.succ.succsessentials_extended.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.block.ModBlocks;
import net.succ.succsessentials_extended.recipe.InfusingRecipe;
import org.jetbrains.annotations.Nullable;

public class InfusingRecipeCategory implements IRecipeCategory<InfusingRecipe> {

    public static final ResourceLocation UID =
            ResourceLocation.fromNamespaceAndPath(Succsessentials_extended.MOD_ID, "infusing");

    public static final RecipeType<InfusingRecipe> RECIPE_TYPE =
            new RecipeType<>(UID, InfusingRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public InfusingRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(
                ResourceLocation.fromNamespaceAndPath(
                        Succsessentials_extended.MOD_ID,
                        "textures/gui/container/infuser.png"
                ),
                0, 0, 176, 85
        );

        this.icon = helper.createDrawableIngredient(
                VanillaTypes.ITEM_STACK,
                new ItemStack(ModBlocks.INFUSER.get())
        );
    }

    @Override
    public RecipeType<InfusingRecipe> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.succsessentials_extended.infuser");
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
    public void setRecipe(IRecipeLayoutBuilder builder,
                          InfusingRecipe recipe,
                          IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.INPUT, 56, 25)
                .addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.INPUT, 56, 44)
                .addIngredients(recipe.getIngredients().get(1));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 116, 35)
                .addItemStack(recipe.output());
    }
}
