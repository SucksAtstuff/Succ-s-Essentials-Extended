package net.succ.succsessentials_extended.datagen;

import com.simibubi.create.AllItems;
import com.simibubi.create.Create;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.block.ModBlocks;
import net.succ.succsessentials_extended.item.ModItems;
import net.succ.succsessentials_extended.recipe.AlloyForgingRecipe;
import net.succ.succsessentials_extended.recipe.InfusingRecipe;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput){
        List<ItemLike> CHROMIUM_SMELTABLES = List.of(ModItems.RAW_CHROMIUM, ModBlocks.CHROMIUM_ORE, ModBlocks.DEEPSLATE_CHROMIUM_ORE);
        List<ItemLike> TITANIUM_SMELTABLES = List.of(ModItems.RAW_TITANIUM, ModBlocks.TITANIUM_ORE, ModBlocks.DEEPSLATE_TITANIUM_ORE);


        oreSmelting(recipeOutput, CHROMIUM_SMELTABLES, RecipeCategory.MISC, ModItems.CHROMIUM_INGOT.get(), 0.25f, 200, "chromium");
        oreBlasting(recipeOutput, CHROMIUM_SMELTABLES, RecipeCategory.MISC, ModItems.CHROMIUM_INGOT.get(), 0.25f, 100, "chromium");

        oreSmelting(recipeOutput, TITANIUM_SMELTABLES, RecipeCategory.MISC, ModItems.TITANIUM_INGOT.get(), 0.25f, 200, "titanium");
        oreBlasting(recipeOutput, TITANIUM_SMELTABLES, RecipeCategory.MISC, ModItems.TITANIUM_INGOT.get(), 0.25f, 100, "titanium");

        alloyForging(
                recipeOutput,
                ModItems.CHROMIUM_INGOT,
                ModItems.TITANIUM_INGOT,
                ModItems.TITA_CHROME_INGOT,
                "tita-chrome",
                100, // 5 seconds
                40 // FE/t
        );

        infusing(
                recipeOutput,
                Items.IRON_INGOT,
                ModItems.COAL_DUST,
                ModItems.STEEL_INGOT,
                "steel",
                400, // 20 seconds
                120 // FE/t
        );

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALLOY_FORGER.get(), 1)
                .pattern("SPS")
                .pattern("PFP")
                .pattern("SPS")
                .define('P', ModBlocks.PANEL_BLOCK)
                .define('S', ModItems.STEEL_INGOT)
                .define('F', Blocks.BLAST_FURNACE)
                .unlockedBy(getHasName(Blocks.BLAST_FURNACE), has(Blocks.BLAST_FURNACE))
                .unlockedBy(getHasName(ModItems.STEEL_INGOT), has(ModItems.STEEL_INGOT))
                .unlockedBy(getHasName(ModBlocks.PANEL_BLOCK), has(ModBlocks.PANEL_BLOCK))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PANEL_BLOCK.get(), 1)
                .pattern("ISI")
                .pattern("SRS")
                .pattern("ISI")
                .define('I', Items.IRON_INGOT)
                .define('S', Items.COPPER_INGOT)
                .define('R', Items.REDSTONE)
                .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ELECTRIC_FURNACE.get())
                .pattern("PSP")
                .pattern("PFP")
                .pattern("PSP")
                .define('P', ModBlocks.PANEL_BLOCK)
                .define('S', ModItems.STEEL_INGOT)
                .define('F', Blocks.FURNACE)
                .unlockedBy(getHasName(Blocks.FURNACE), has(Blocks.FURNACE))
                .unlockedBy(getHasName(ModBlocks.PANEL_BLOCK), has(ModBlocks.PANEL_BLOCK))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COAL_GENERATOR.get())
                .pattern("IPI")
                .pattern("IFI")
                .pattern("IRI")
                .define('I', Items.IRON_INGOT)
                .define('P', ModBlocks.PANEL_BLOCK)
                .define('F', Blocks.FURNACE)
                .define('R', Items.REDSTONE_BLOCK)
                .unlockedBy(getHasName(ModBlocks.PANEL_BLOCK), has(ModBlocks.PANEL_BLOCK))
                .unlockedBy(getHasName(Items.REDSTONE_BLOCK), has(Items.REDSTONE_BLOCK))
                .save(recipeOutput);
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
                                       int cookTime,
                                       int energyPerTick) {

        AlloyForgingRecipe recipe =
                new AlloyForgingRecipe(
                        Ingredient.of(inputA),     // First metal input
                        Ingredient.of(inputB),     // Second metal input
                        new ItemStack(result),     // Output item
                        cookTime,                  // Alloying time (ticks)
                        energyPerTick              // Energy cost per tick
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

    protected static void infusing (RecipeOutput recipeOutput,
                                       ItemLike inputA,
                                       ItemLike inputB,
                                       ItemLike result,
                                       String group,
                                       int cookTime,
                                       int energyPerTick) {

        InfusingRecipe recipe =
                new InfusingRecipe(
                        Ingredient.of(inputA),     // First metal input
                        Ingredient.of(inputB),     // Second metal input
                        new ItemStack(result),     // Output item
                        cookTime,                  // Infusing time (ticks)
                        energyPerTick              // Energy cost per tick
                );

        recipeOutput.accept(
                ResourceLocation.fromNamespaceAndPath(
                        Succsessentials_extended.MOD_ID,
                        getItemName(result) + "_from_infusing"
                ),
                recipe,
                null
        );


    }


}
