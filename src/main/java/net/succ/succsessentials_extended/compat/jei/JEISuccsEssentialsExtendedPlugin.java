package net.succ.succsessentials_extended.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.block.ModBlocks;
import net.succ.succsessentials_extended.recipe.AlloyForgingRecipe;
import net.succ.succsessentials_extended.recipe.InfusingRecipe;
import net.succ.succsessentials_extended.recipe.ModRecipes;
import net.succ.succsessentials_extended.recipe.PulverizingRecipe;
import net.succ.succsessentials_extended.screen.custom.AlloyForgerBlockScreen;
import net.succ.succsessentials_extended.screen.custom.InfuserBlockScreen;
import net.succ.succsessentials_extended.screen.custom.PulverizerBlockScreen;

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
                new InfusingRecipeCategory(guiHelper),
                new PulverizingRecipeCategory(guiHelper)
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

        List<PulverizingRecipe> pulverizingRecipes =
                recipeManager.getAllRecipesFor(ModRecipes.PULVERIZING_TYPE.get())
                        .stream().map(RecipeHolder::value).toList();

        registration.addRecipes(AlloyForgingRecipeCategory.RECIPE_TYPE, alloyRecipes);
        registration.addRecipes(InfusingRecipeCategory.RECIPE_TYPE, infusingRecipes);
        registration.addRecipes(PulverizingRecipeCategory.RECIPE_TYPE, pulverizingRecipes);

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

        registration.addRecipeClickArea(
                PulverizerBlockScreen.class,
                79, 34, 24, 16,
                PulverizingRecipeCategory.RECIPE_TYPE
        );
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {

        registration.addRecipeCatalyst(
                new ItemStack(ModBlocks.ALLOY_FORGER.get()),
                AlloyForgingRecipeCategory.RECIPE_TYPE
        );

        registration.addRecipeCatalyst(
                new ItemStack(ModBlocks.INFUSER.get()),
                InfusingRecipeCategory.RECIPE_TYPE
        );

        registration.addRecipeCatalyst(
                new ItemStack(ModBlocks.PULVERIZER.get()),
                PulverizingRecipeCategory.RECIPE_TYPE
        );
    }
}
