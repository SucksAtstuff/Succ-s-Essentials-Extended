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
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.block.ModBlocks;
import net.succ.succsessentials_extended.compat.jei.category.*;
import net.succ.succsessentials_extended.item.ModItems;
import net.succ.succsessentials_extended.recipe.ModRecipes;
import net.succ.succsessentials_extended.recipe.alloyforging.AlloyForgingRecipe;
import net.succ.succsessentials_extended.recipe.hammering.HammerRecipe;
import net.succ.succsessentials_extended.recipe.infusing.InfusingRecipe;
import net.succ.succsessentials_extended.recipe.pulverizing.PulverizingRecipe;
import net.succ.succsessentials_extended.recipe.rollingmill.RollingMillRecipe;
import net.succ.succsessentials_extended.recipe.wirecutting.WireCutterRecipe;
import net.succ.succsessentials_extended.recipe.wiredrawing.WireDrawingRecipe;
import net.succ.succsessentials_extended.screen.custom.*;

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

    /* ============================================================
     *                        CATEGORIES
     * ============================================================ */

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        var guiHelper = registration.getJeiHelpers().getGuiHelper();

        registration.addRecipeCategories(
                new AlloyForgingRecipeCategory(guiHelper),
                new InfusingRecipeCategory(guiHelper),
                new PulverizingRecipeCategory(guiHelper),
                new ElectricFurnaceRecipeCategory(guiHelper),
                new RollingMillRecipeCategory(guiHelper),
                new WireDrawingRecipeCategory(guiHelper),

                // Hand processing
                new HammeringRecipeCategory(guiHelper),
                new WireCuttingRecipeCategory(guiHelper)
        );
    }

    /* ============================================================
     *                         RECIPES
     * ============================================================ */

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager =
                Minecraft.getInstance().level.getRecipeManager();

        List<AlloyForgingRecipe> alloyRecipes =
                recipeManager.getAllRecipesFor(ModRecipes.ALLOY_FORGING_TYPE.get())
                        .stream().map(RecipeHolder::value).toList();

        List<InfusingRecipe> infusingRecipes =
                recipeManager.getAllRecipesFor(ModRecipes.INFUSING_TYPE.get())
                        .stream().map(RecipeHolder::value).toList();

        List<PulverizingRecipe> pulverizingRecipes =
                recipeManager.getAllRecipesFor(ModRecipes.PULVERIZING_TYPE.get())
                        .stream().map(RecipeHolder::value).toList();

        List<SmeltingRecipe> smeltingRecipes =
                recipeManager.getAllRecipesFor(RecipeType.SMELTING)
                        .stream().map(RecipeHolder::value).toList();

        List<HammerRecipe> hammeringRecipes =
                recipeManager.getAllRecipesFor(ModRecipes.HAMMERING_TYPE.get())
                        .stream().map(RecipeHolder::value).toList();

        List<WireCutterRecipe> wireCuttingRecipes =
                recipeManager.getAllRecipesFor(ModRecipes.WIRE_CUTTING_TYPE.get())
                        .stream().map(RecipeHolder::value).toList();

        List<RollingMillRecipe> rollingMillRecipes =
                recipeManager.getAllRecipesFor(ModRecipes.ROLLING_MILL_TYPE.get())
                        .stream().map(RecipeHolder::value).toList();

        List<WireDrawingRecipe> wireDrawingRecipes =
                recipeManager.getAllRecipesFor(ModRecipes.WIRE_DRAWING_TYPE.get())
                        .stream().map(RecipeHolder::value).toList();

        registration.addRecipes(AlloyForgingRecipeCategory.RECIPE_TYPE, alloyRecipes);
        registration.addRecipes(InfusingRecipeCategory.RECIPE_TYPE, infusingRecipes);
        registration.addRecipes(PulverizingRecipeCategory.RECIPE_TYPE, pulverizingRecipes);
        registration.addRecipes(ElectricFurnaceRecipeCategory.RECIPE_TYPE, smeltingRecipes);
        registration.addRecipes(RollingMillRecipeCategory.RECIPE_TYPE, rollingMillRecipes);
        registration.addRecipes(WireDrawingRecipeCategory.RECIPE_TYPE, wireDrawingRecipes);

        // Hand processing
        registration.addRecipes(HammeringRecipeCategory.RECIPE_TYPE, hammeringRecipes);
        registration.addRecipes(WireCuttingRecipeCategory.RECIPE_TYPE, wireCuttingRecipes);
    }

    /* ============================================================
     *                       GUI CLICK AREAS
     * ============================================================ */

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

        registration.addRecipeClickArea(
                ElectricFurnaceBlockScreen.class,
                79, 34, 24, 16,
                ElectricFurnaceRecipeCategory.RECIPE_TYPE
        );

        registration.addRecipeClickArea(
                RollingMillBlockScreen.class,
                79, 34, 24, 16,
                RollingMillRecipeCategory.RECIPE_TYPE
        );

        registration.addRecipeClickArea(
                WireDrawerBlockScreen.class,
                79, 34, 24, 16,
                WireDrawingRecipeCategory.RECIPE_TYPE
        );
    }


    /* ============================================================
     *                       CATALYSTS
     * ============================================================ */

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

        registration.addRecipeCatalyst(
                new ItemStack(ModBlocks.ELECTRIC_FURNACE.get()),
                ElectricFurnaceRecipeCategory.RECIPE_TYPE
        );

        registration.addRecipeCatalyst(
                new ItemStack(ModBlocks.ROLLING_MILL.get()),
                RollingMillRecipeCategory.RECIPE_TYPE
        );

        registration.addRecipeCatalyst(
                new ItemStack(ModBlocks.WIRE_DRAWER.get()),
                WireDrawingRecipeCategory.RECIPE_TYPE
        );

        // Hand tools
        registration.addRecipeCatalyst(
                new ItemStack(ModItems.HAMMER.get()),
                HammeringRecipeCategory.RECIPE_TYPE
        );

        registration.addRecipeCatalyst(
                new ItemStack(ModItems.WIRE_CUTTER.get()),
                WireCuttingRecipeCategory.RECIPE_TYPE
        );
    }

}
