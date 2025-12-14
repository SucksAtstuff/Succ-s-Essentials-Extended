package net.succ.succsessentials_extended.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.recipe.AlloyForgingRecipe;
import net.succ.succsessentials_extended.recipe.InfusingRecipe;
import net.succ.succsessentials_extended.recipe.ModRecipes;
import net.succ.succsessentials_extended.screen.custom.AlloyForgerBlockScreen;
import net.succ.succsessentials_extended.screen.custom.InfuserBlockScreen;

import java.util.List;

@JeiPlugin
public class JEISuccsEssentialsExtendedPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(
                Succsessentials_extended.MOD_ID,
                "jei_plugin"
        );
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        var guiHelper = registration.getJeiHelpers().getGuiHelper();

        registration.addRecipeCategories(
                new AlloyForgingRecipeCategory(guiHelper),
                new InfusingRecipeCategory(guiHelper)
        );
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<AlloyForgingRecipe> alloyRecipes =
                recipeManager.getAllRecipesFor(ModRecipes.ALLOY_FORGING_TYPE.get())
                        .stream().map(RecipeHolder::value).toList();

        List<InfusingRecipe> infusingRecipes =
                recipeManager.getAllRecipesFor(ModRecipes.INFUSING_TYPE.get())
                        .stream().map(RecipeHolder::value).toList();

        registration.addRecipes(AlloyForgingRecipeCategory.RECIPE_TYPE, alloyRecipes);
        registration.addRecipes(InfusingRecipeCategory.RECIPE_TYPE, infusingRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(
                AlloyForgerBlockScreen.class,
                79, 34, 24, 16,
                AlloyForgingRecipeCategory.RECIPE_TYPE
        );

        registration.addRecipeClickArea(
                InfuserBlockScreen.class,
                79, 34, 24, 16,
                InfusingRecipeCategory.RECIPE_TYPE
        );
    }
}
