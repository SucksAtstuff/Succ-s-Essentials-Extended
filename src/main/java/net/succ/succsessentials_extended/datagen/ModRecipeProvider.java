package net.succ.succsessentials_extended.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.block.ModBlocks;
import net.succ.succsessentials_extended.item.ModItems;
import net.succ.succsessentials_extended.recipe.AlloyForgingRecipe;
import net.succ.succsessentials_extended.recipe.InfusingRecipe;
import net.succ.succsessentials_extended.recipe.PulverizingRecipe;
import net.succ.succsessentials_extended.util.ModTags;
import net.succ.succsessentials_extended.util.PulverizingSource;

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
        List<ItemLike> TIN_SMELTABLES = List.of(ModItems.RAW_TIN, ModBlocks.TIN_ORE, ModBlocks.DEEPSLATE_TIN_ORE);


        oreSmelting(recipeOutput, CHROMIUM_SMELTABLES, RecipeCategory.MISC, ModItems.CHROMIUM_INGOT.get(), 0.25f, 200, "chromium");
        oreBlasting(recipeOutput, CHROMIUM_SMELTABLES, RecipeCategory.MISC, ModItems.CHROMIUM_INGOT.get(), 0.25f, 100, "chromium");

        oreSmelting(recipeOutput, TITANIUM_SMELTABLES, RecipeCategory.MISC, ModItems.TITANIUM_INGOT.get(), 0.25f, 200, "titanium");
        oreBlasting(recipeOutput, TITANIUM_SMELTABLES, RecipeCategory.MISC, ModItems.TITANIUM_INGOT.get(), 0.25f, 100, "titanium");

        oreSmelting(recipeOutput, TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT.get(), 0.25f, 200, "tin");
        oreBlasting(recipeOutput, TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT.get(), 0.25f, 100, "tin");

        alloyForging(
                recipeOutput,
                ModTags.Items.INGOTS_CHROMIUM,     // ANY chromium ingot
                1,
                ModTags.Items.INGOTS_TITANIUM,     // ANY titanium ingot
                1,
                ModItems.TITA_CHROME_INGOT,
                1,
                100,
                40
        );

        alloyForging(
                recipeOutput,
                ModTags.Items.INGOTS_COPPER,     // ANY chromium ingot
                3,
                ModTags.Items.INGOTS_TIN,     // ANY titanium ingot
                1,
                ModItems.BRONZE_INGOT,
                4,
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


        metalConversions(
                recipeOutput,
                ModTags.Items.INGOTS_CHROMIUM,
                ModTags.Items.NUGGETS_CHROMIUM,
                ModBlocks.CHROMIUM_BLOCK,
                ModItems.CHROMIUM_INGOT,
                ModItems.CHROMIUM_NUGGET
        );

        metalConversions(
                recipeOutput,
                ModTags.Items.INGOTS_TITANIUM,
                ModTags.Items.NUGGETS_TITANIUM,
                ModBlocks.TITANIUM_BLOCK,
                ModItems.TITANIUM_INGOT,
                ModItems.TITANIUM_NUGGET
        );

        metalConversions(
                recipeOutput,
                ModTags.Items.INGOTS_STEEL,
                ModTags.Items.NUGGETS_STEEL,
                ModBlocks.STEEL_BLOCK,
                ModItems.STEEL_INGOT,
                ModItems.STEEL_NUGGET
        );

        metalConversions(
                recipeOutput,
                ModTags.Items.INGOTS_BRONZE,
                ModTags.Items.NUGGETS_BRONZE,
                ModBlocks.BRONZE_BLOCK,
                ModItems.BRONZE_NUGGET,
                ModItems.BRONZE_INGOT
        );

        metalConversions(
                recipeOutput,
                ModTags.Items.INGOTS_TIN,
                ModTags.Items.NUGGETS_TIN,
                ModBlocks.TIN_BLOCK,
                ModItems.TIN_NUGGET,
                ModItems.TIN_INGOT
        );

        metalConversions(
                recipeOutput,
                ModTags.Items.INGOTS_TITA_CHROME,
                ModTags.Items.NUGGETS_TITA_CHROME,
                ModBlocks.TITA_CHROME_BLOCK,
                ModItems.TITA_CHROME_INGOT,
                ModItems.TITA_CHROME_NUGGET
        );

        rawMaterialConversions(
                recipeOutput,
                ModTags.Items.RAW_CHROMIUM,
                ModBlocks.RAW_CHROMIUM_BLOCK,
                ModItems.RAW_CHROMIUM
        );

        rawMaterialConversions(
                recipeOutput,
                ModTags.Items.RAW_TIN,
                ModBlocks.RAW_TIN_BLOCK,
                ModItems.RAW_TIN
        );

        rawMaterialConversions(
                recipeOutput,
                ModTags.Items.RAW_TITANIUM,
                ModBlocks.RAW_TITANIUM_BLOCK,
                ModItems.RAW_TITANIUM
        );

        pulverizing(
                recipeOutput,
                ModTags.Items.ORES_TITANIUM,
                ModItems.TITANIUM_DUST,
                Blocks.COBBLESTONE,
                200,
                20,
                PulverizingSource.ORE
        );


        pulverizing(
                recipeOutput,
                ModTags.Items.DEEPSLATE_ORES_TITANIUM,
                ModItems.TITANIUM_DUST,
                Blocks.DEEPSLATE,
                200,
                20,
                PulverizingSource.DEEPSLATE
        );

        pulverizing(
                recipeOutput,
                ModTags.Items.INGOTS_TITANIUM,
                ModItems.TITANIUM_DUST,
                null,
                80,
                10,
                PulverizingSource.INGOT
        );


        pulverizing(
                recipeOutput,
                ItemTags.COALS,
                ModItems.COAL_DUST,
                null,
                80,
                10,
                PulverizingSource.COAL
        );

        compostableBiomassPulverizing(
                recipeOutput,
                60,   // cook time
                8     // energy per tick
        );

    }


    protected static ResourceLocation id(ItemLike item, String suffix) {
        return ResourceLocation.fromNamespaceAndPath(
                Succsessentials_extended.MOD_ID,
                getItemName(item) + suffix
        );
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
            int countA,
            TagKey<Item> inputB,
            int countB,
            ItemLike result,
            int resultCount,
            int cookTime,
            int energyPerTick
    ) {

        AlloyForgingRecipe recipe =
                new AlloyForgingRecipe(
                        Ingredient.of(inputA),
                        countA,
                        Ingredient.of(inputB),
                        countB,
                        new ItemStack(result, resultCount),
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

    protected static void metalConversions(
            RecipeOutput recipeOutput,
            TagKey<Item> ingotTag,
            TagKey<Item> nuggetTag,
            ItemLike block,
            ItemLike ingot,
            ItemLike nugget
    ) {

        // 9 NUGGETS -> 1 INGOT
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ingot)
                .pattern("NNN")
                .pattern("NNN")
                .pattern("NNN")
                .define('N', Ingredient.of(nuggetTag))
                .unlockedBy("has_nugget", has(nuggetTag))
                .save(recipeOutput, id(ingot, "_from_nuggets"));

        // 1 INGOT -> 9 NUGGETS
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, nugget, 9)
                .requires(Ingredient.of(ingotTag))
                .unlockedBy("has_ingot", has(ingotTag))
                .save(recipeOutput, id(nugget, "_from_ingot"));

        // 9 INGOTS -> 1 BLOCK
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block)
                .pattern("III")
                .pattern("III")
                .pattern("III")
                .define('I', Ingredient.of(ingotTag))
                .unlockedBy("has_ingot", has(ingotTag))
                .save(recipeOutput, id(block, "_from_ingots"));

        // 1 BLOCK -> 9 INGOTS
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ingot, 9)
                .requires(block)
                .unlockedBy("has_block", has(block))
                .save(recipeOutput, id(ingot, "_from_block"));
    }

    protected static void rawMaterialConversions(
            RecipeOutput recipeOutput,
            TagKey<Item> rawItemTag,
            ItemLike rawBlock,
            ItemLike rawItem
    ) {

        // 9 RAW ITEMS -> 1 RAW BLOCK
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, rawBlock)
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .define('R', Ingredient.of(rawItemTag))
                .unlockedBy("has_raw", has(rawItemTag))
                .save(recipeOutput, id(rawBlock, "_from_raw_items"));

        // 1 RAW BLOCK -> 9 RAW ITEMS
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, rawItem, 9)
                .requires(rawBlock)
                .unlockedBy("has_raw_block", has(rawBlock))
                .save(recipeOutput, id(rawItem, "_from_raw_block"));
    }

    protected static void pulverizing(
            RecipeOutput recipeOutput,
            TagKey<Item> input,
            ItemLike result,
            ItemLike byproduct,
            int cookTime,
            int energyPerTick,
            PulverizingSource source
    ) {

        // Safely resolve optional byproduct
        ItemStack byproductStack =
                byproduct == null ? ItemStack.EMPTY : new ItemStack(byproduct);

        PulverizingRecipe recipe =
                new PulverizingRecipe(
                        Ingredient.of(input),
                        new ItemStack(result),
                        byproductStack,
                        cookTime,
                        energyPerTick
                );

        recipeOutput.accept(
                ResourceLocation.fromNamespaceAndPath(
                        Succsessentials_extended.MOD_ID,
                        getItemName(result)
                                + "_from_pulverizing_"
                                + source.id()
                ),
                recipe,
                null
        );
    }

    protected void compostableBiomassPulverizing(
            RecipeOutput recipeOutput,
            int cookTime,
            int energyPerTick
    ) {

        // Iterate using FastUtil's entry set to avoid lambda type inference issues
        ComposterBlock.COMPOSTABLES.object2FloatEntrySet().forEach(entry -> {

            // Explicit, hard-typed values — no ambiguity possible
            Item item = entry.getKey().asItem();
            float chance = entry.getFloatValue();

            // Safety check
            if (item == Items.AIR) {
                return;
            }

            // Determine biomass output based on vanilla compost tiers
            int biomassAmount;
            if (chance >= 1.0f) {
                biomassAmount = 3;
            } else if (chance >= 0.65f) {
                biomassAmount = 2;
            } else {
                biomassAmount = 1;
            }

            // Create pulverizing recipe
            PulverizingRecipe recipe =
                    new PulverizingRecipe(
                            Ingredient.of(item), // Item implements ItemLike, safe
                            new ItemStack(ModItems.BIO_MASS.get(), biomassAmount),
                            ItemStack.EMPTY,
                            cookTime,
                            energyPerTick
                    );

            // Register recipe with stable registry-based ID
            recipeOutput.accept(
                    ResourceLocation.fromNamespaceAndPath(
                            Succsessentials_extended.MOD_ID,
                            "biomass_from_" + BuiltInRegistries.ITEM.getKey(item).getPath()
                    ),
                    recipe,
                    null
            );
        });
    }
}

