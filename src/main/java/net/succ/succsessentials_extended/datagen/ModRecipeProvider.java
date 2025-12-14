package net.succ.succsessentials_extended.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
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
import net.succ.succsessentials_extended.util.ModTags;

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
                ModTags.Items.INGOTS_CHROMIUM,     // ANY chromium ingot
                ModTags.Items.INGOTS_TITANIUM,     // ANY titanium ingot
                ModItems.TITA_CHROME_INGOT,
                "tita_chrome",
                100,
                40
        );

        infusing(
                recipeOutput,
                ModTags.Items.INGOTS_IRON,          // ANY iron ingot
                ModTags.Items.DUSTS_COAL,                // ANY coal dust / dust
                ModItems.STEEL_INGOT,
                "steel",
                400,
                120
        );

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALLOY_FORGER.get(), 1)
                .pattern("SPS")
                .pattern("PFP")
                .pattern("SPS")

                // Panel block is still specific to your mod
                .define('P', ModBlocks.PANEL_BLOCK)

                // ANY steel ingot from any mod
                .define('S', Ingredient.of(ModTags.Items.INGOTS_STEEL))

                // Vanilla blast furnace (kept specific on purpose)
                .define('F', Blocks.BLAST_FURNACE)

                .unlockedBy(getHasName(Blocks.BLAST_FURNACE), has(Blocks.BLAST_FURNACE))
                .unlockedBy("has_steel_ingot", has(ModTags.Items.INGOTS_STEEL))
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
                // ANY steel ingot from any mod
                .define('S', Ingredient.of(ModTags.Items.INGOTS_STEEL))
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

    protected static void alloyForging(
            RecipeOutput recipeOutput,
            TagKey<Item> inputA,
            TagKey<Item> inputB,
            ItemLike result,
            String group,
            int cookTime,
            int energyPerTick
    ) {

        AlloyForgingRecipe recipe =
                new AlloyForgingRecipe(
                        Ingredient.of(inputA),     // TAG-based input A
                        Ingredient.of(inputB),     // TAG-based input B
                        new ItemStack(result),
                        cookTime,
                        energyPerTick
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

    protected static void infusing(
            RecipeOutput recipeOutput,
            TagKey<Item> inputA,
            TagKey<Item> inputB,
            ItemLike result,
            String group,
            int cookTime,
            int energyPerTick
    ) {

        InfusingRecipe recipe =
                new InfusingRecipe(
                        Ingredient.of(inputA),     // TAG-based input A
                        Ingredient.of(inputB),     // TAG-based input B
                        new ItemStack(result),
                        cookTime,
                        energyPerTick
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

