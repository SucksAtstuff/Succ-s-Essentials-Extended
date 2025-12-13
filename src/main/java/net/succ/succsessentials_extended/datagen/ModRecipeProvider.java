package net.succ.succsessentials_extended.datagen;

import com.simibubi.create.AllItems;
import com.simibubi.create.Create;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.block.ModBlocks;
import net.succ.succsessentials_extended.item.ModItems;
import net.succ.succsessentials_extended.recipe.AlloyForgingRecipe;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput){
        List<ItemLike> CHROMIUM_SMELTABLES = List.of(ModItems.RAW_CHROMIUM, ModBlocks.CHROMIUM_ORE, ModBlocks.DEEPSLATE_CHROMIUM_ORE);

        oreSmelting(recipeOutput, CHROMIUM_SMELTABLES, RecipeCategory.MISC, ModItems.CHROMIUM_INGOT.get(), 0.25f, 200, "chromium");
        oreBlasting(recipeOutput, CHROMIUM_SMELTABLES, RecipeCategory.MISC, ModItems.CHROMIUM_INGOT.get(), 0.25f, 100, "chromium");
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List <ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, Succsessentials_extended.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    protected static void alloyForging(RecipeOutput recipeOutput,
                                       ItemLike inputA,
                                       ItemLike inputB,
                                       ItemLike result,
                                       String group,
                                       int cookTime) {

        AlloyForgingRecipe recipe =
                new AlloyForgingRecipe(
                        Ingredient.of(inputA),     // First metal input
                        Ingredient.of(inputB),     // Second metal input
                        new ItemStack(result),     // Output item
                        cookTime                   // How long alloying takes (in ticks)
                );

        recipeOutput.accept(
                ResourceLocation.fromNamespaceAndPath(
                        Succsessentials_extended.MOD_ID,
                        getItemName(result) + "_from_alloying"
                ),
                recipe,
                null
        );

    }

}
