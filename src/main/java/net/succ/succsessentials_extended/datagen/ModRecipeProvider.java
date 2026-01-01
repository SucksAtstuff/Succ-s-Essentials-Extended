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
import net.succ.succsessentials_extended.recipe.alloyforging.AlloyForgingRecipe;
import net.succ.succsessentials_extended.recipe.hammering.HammerRecipe;
import net.succ.succsessentials_extended.recipe.infusing.InfusingRecipe;
import net.succ.succsessentials_extended.recipe.pulverizing.PulverizingRecipe;
import net.succ.succsessentials_extended.recipe.rollingmill.RollingMillRecipe;
import net.succ.succsessentials_extended.recipe.wirecutting.WireCutterRecipe;
import net.succ.succsessentials_extended.recipe.wiredrawing.WireDrawingRecipe;
import net.succ.succsessentials_extended.util.ModTags;
import net.succ.succsessentials_extended.util.PulverizingSource;


import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }


    /* =====================================================================
     *                          DATA RECORDS
     * ===================================================================== */

    /**
     * Immutable data holder describing how an ore pulverizes.
     * One entry produces BOTH stone and deepslate recipes.
     */
    private static record OrePulverizingData(
            Supplier<? extends ItemLike> stoneOre,
            Supplier<? extends ItemLike> deepslateOre,
            Supplier<? extends ItemLike> dust,
            int cookTime,
            int energy
    ) {}

    /**
     * Central table for all ore pulverizing recipes.
     * Changing balance here updates both variants.
     */
    private static final OrePulverizingData[] ORE_PULVERIZING = {
            new OrePulverizingData(ModBlocks.CHROMIUM_ORE, ModBlocks.DEEPSLATE_CHROMIUM_ORE, ModItems.CHROMIUM_DUST, 200, 20),
            new OrePulverizingData(ModBlocks.TITANIUM_ORE, ModBlocks.DEEPSLATE_TITANIUM_ORE, ModItems.TITANIUM_DUST, 200, 20),
            new OrePulverizingData(ModBlocks.TIN_ORE, ModBlocks.DEEPSLATE_TIN_ORE, ModItems.TIN_DUST, 200, 20),
            new OrePulverizingData(ModBlocks.TUNGSTEN_ORE, ModBlocks.DEEPSLATE_TUNGSTEN_ORE, ModItems.TUNGSTEN_DUST, 240, 25),
            new OrePulverizingData(ModBlocks.COBALT_ORE, ModBlocks.DEEPSLATE_COBALT_ORE, ModItems.COBALT_DUST, 200, 20),
            new OrePulverizingData(ModBlocks.OSMIUM_ORE, ModBlocks.DEEPSLATE_OSMIUM_ORE, ModItems.OSMIUM_DUST, 200, 20),
            new OrePulverizingData(ModBlocks.ZINC_ORE, ModBlocks.DEEPSLATE_ZINC_ORE, ModItems.ZINC_DUST, 180, 15),
            new OrePulverizingData(ModBlocks.SILVER_ORE, ModBlocks.DEEPSLATE_SILVER_ORE, ModItems.SILVER_DUST, 180, 15),
            new OrePulverizingData(ModBlocks.NICKEL_ORE, ModBlocks.DEEPSLATE_NICKEL_ORE, ModItems.NICKEL_DUST, 180, 15),
            new OrePulverizingData(ModBlocks.ALUMINIUM_ORE, ModBlocks.DEEPSLATE_ALUMINIUM_ORE, ModItems.ALUMINIUM_DUST, 180, 15)

    };

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput){


        /* =====================================================================
         *                           SMELTING / BLASTING
         * ===================================================================== */

        List<ItemLike> CHROMIUM_SMELTABLES = List.of(ModItems.RAW_CHROMIUM, ModItems.CHROMIUM_DUST, ModBlocks.CHROMIUM_ORE, ModBlocks.DEEPSLATE_CHROMIUM_ORE);
        List<ItemLike> TITANIUM_SMELTABLES = List.of(ModItems.RAW_TITANIUM, ModItems.TITANIUM_DUST, ModBlocks.TITANIUM_ORE, ModBlocks.DEEPSLATE_TITANIUM_ORE);
        List<ItemLike> TIN_SMELTABLES = List.of(ModItems.RAW_TIN, ModItems.TIN_DUST, ModBlocks.TIN_ORE, ModBlocks.DEEPSLATE_TIN_ORE);
        List<ItemLike> TUNGSTEN_SMELTABLES = List.of(ModItems.RAW_TUNGSTEN, ModItems.TUNGSTEN_DUST, ModBlocks.TUNGSTEN_ORE, ModBlocks.DEEPSLATE_TUNGSTEN_ORE);
        List<ItemLike> COBALT_SMELTABLES = List.of(ModItems.RAW_COBALT, ModItems.COBALT_DUST, ModBlocks.COBALT_ORE, ModBlocks.DEEPSLATE_COBALT_ORE);
        List<ItemLike> OSMIUM_SMELTABLES = List.of(ModItems.RAW_OSMIUM, ModItems.OSMIUM_DUST, ModBlocks.OSMIUM_ORE, ModBlocks.DEEPSLATE_OSMIUM_ORE);
        List<ItemLike> ZINC_SMELTABLES = List.of(ModItems.RAW_ZINC, ModItems.ZINC_DUST, ModBlocks.ZINC_ORE, ModBlocks.DEEPSLATE_ZINC_ORE);
        List<ItemLike> SILVER_SMELTABLES = List.of(ModItems.RAW_SILVER, ModItems.SILVER_DUST, ModBlocks.SILVER_ORE, ModBlocks.DEEPSLATE_SILVER_ORE);
        List<ItemLike> NICKEL_SMELTABLES = List.of(ModItems.RAW_NICKEL, ModItems.NICKEL_DUST, ModBlocks.NICKEL_ORE, ModBlocks.DEEPSLATE_NICKEL_ORE);
        List<ItemLike> ALUMINIUM_SMELTABLES = List.of(ModItems.RAW_ALUMINIUM, ModItems.ALUMINIUM_DUST, ModBlocks.ALUMINIUM_ORE, ModBlocks.DEEPSLATE_ALUMINIUM_ORE);

        List<ItemLike> STEEL_SMELTABLES = List.of(ModItems.STEEL_DUST);
        List<ItemLike> BRONZE_SMELTABLES = List.of(ModItems.BRONZE_DUST);
        List<ItemLike> BRASS_SMELTABLES = List.of(ModItems.BRASS_DUST);
        List<ItemLike> ELECTRUM_SMELTABLES = List.of(ModItems.ELECTRUM_DUST);
        List<ItemLike> INVAR_SMELTABLES = List.of(ModItems.INVAR_DUST);
        List<ItemLike> CONSTANTAN_SMELTABELS = List.of(ModItems.CONSTANTAN_DUST);
        List<ItemLike> TITA_CHROME_SMELTABLES = List.of(ModItems.TITA_CHROME_DUST);
        List<ItemLike> COPPER_SMELTABLES = List.of(ModItems.COPPER_DUST);
        List<ItemLike> IRON_SMELTABLES = List.of(ModItems.IRON_DUST);
        List<ItemLike> GOLD_SMELTABLES = List.of(ModItems.GOLD_DUST);

        oreSmelting(recipeOutput, CHROMIUM_SMELTABLES, RecipeCategory.MISC, ModItems.CHROMIUM_INGOT.get(), 0.25f, 200, "chromium");
        oreBlasting(recipeOutput, CHROMIUM_SMELTABLES, RecipeCategory.MISC, ModItems.CHROMIUM_INGOT.get(), 0.25f, 100, "chromium");

        oreSmelting(recipeOutput, TITANIUM_SMELTABLES, RecipeCategory.MISC, ModItems.TITANIUM_INGOT.get(), 0.25f, 200, "titanium");
        oreBlasting(recipeOutput, TITANIUM_SMELTABLES, RecipeCategory.MISC, ModItems.TITANIUM_INGOT.get(), 0.25f, 100, "titanium");

        oreSmelting(recipeOutput, TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT.get(), 0.25f, 200, "tin");
        oreBlasting(recipeOutput, TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT.get(), 0.25f, 100, "tin");

        oreSmelting(recipeOutput, TUNGSTEN_SMELTABLES, RecipeCategory.MISC, ModItems.TUNGSTEN_INGOT.get(), 0.35f, 300, "tungsten");
        oreBlasting(recipeOutput, TUNGSTEN_SMELTABLES, RecipeCategory.MISC, ModItems.TUNGSTEN_INGOT.get(), 0.35f, 150, "tungsten");

        oreSmelting(recipeOutput, COBALT_SMELTABLES, RecipeCategory.MISC, ModItems.COBALT_INGOT.get(), 0.30f, 200, "cobalt");
        oreBlasting(recipeOutput, COBALT_SMELTABLES, RecipeCategory.MISC, ModItems.COBALT_INGOT.get(), 0.30f, 100, "cobalt");

        oreSmelting(recipeOutput, OSMIUM_SMELTABLES, RecipeCategory.MISC, ModItems.OSMIUM_INGOT.get(), 0.30f, 200, "osmium");
        oreBlasting(recipeOutput, OSMIUM_SMELTABLES, RecipeCategory.MISC, ModItems.OSMIUM_INGOT.get(), 0.30f, 100, "osmium");

        oreSmelting(recipeOutput, ZINC_SMELTABLES, RecipeCategory.MISC, ModItems.ZINC_INGOT.get(), 0.20f, 200, "zinc");
        oreBlasting(recipeOutput, ZINC_SMELTABLES, RecipeCategory.MISC, ModItems.ZINC_INGOT.get(), 0.20f, 100, "zinc");

        oreSmelting(recipeOutput, SILVER_SMELTABLES, RecipeCategory.MISC, ModItems.SILVER_INGOT.get(), 0.25f, 200, "silver");
        oreBlasting(recipeOutput, SILVER_SMELTABLES, RecipeCategory.MISC, ModItems.SILVER_INGOT.get(), 0.25f, 100, "silver");

        oreSmelting(recipeOutput, NICKEL_SMELTABLES, RecipeCategory.MISC, ModItems.NICKEL_INGOT.get(), 0.25f, 200, "nickel");
        oreBlasting(recipeOutput, NICKEL_SMELTABLES, RecipeCategory.MISC, ModItems.NICKEL_INGOT.get(), 0.25f, 100, "nickel");

        oreSmelting(recipeOutput, ALUMINIUM_SMELTABLES, RecipeCategory.MISC, ModItems.ALUMINIUM_INGOT.get(), 0.25f, 200, "aluminium");
        oreBlasting(recipeOutput, ALUMINIUM_SMELTABLES, RecipeCategory.MISC, ModItems.ALUMINIUM_INGOT.get(), 0.25f, 100, "aluminium");

        oreSmelting(recipeOutput, STEEL_SMELTABLES, RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), 0.25f, 200, "steel");
        oreBlasting(recipeOutput, STEEL_SMELTABLES, RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), 0.25f, 100, "steel");

        oreSmelting(recipeOutput, BRONZE_SMELTABLES, RecipeCategory.MISC, ModItems.BRONZE_INGOT.get(), 0.25f, 200, "bronze");
        oreBlasting(recipeOutput, BRONZE_SMELTABLES, RecipeCategory.MISC, ModItems.BRONZE_INGOT.get(), 0.25f, 100, "bronze");

        oreSmelting(recipeOutput, BRASS_SMELTABLES, RecipeCategory.MISC, ModItems.BRASS_INGOT.get(), 0.25f, 200, "brass");
        oreBlasting(recipeOutput, BRASS_SMELTABLES, RecipeCategory.MISC, ModItems.BRASS_INGOT.get(), 0.25f, 100, "brass");

        oreSmelting(recipeOutput, ELECTRUM_SMELTABLES, RecipeCategory.MISC, ModItems.ELECTRUM_INGOT.get(), 0.25f, 200, "electrum");
        oreBlasting(recipeOutput, ELECTRUM_SMELTABLES, RecipeCategory.MISC, ModItems.ELECTRUM_INGOT.get(), 0.25f, 100, "electrum");

        oreSmelting(recipeOutput, INVAR_SMELTABLES, RecipeCategory.MISC, ModItems.INVAR_INGOT.get(), 0.25f, 200, "invar");
        oreBlasting(recipeOutput, INVAR_SMELTABLES, RecipeCategory.MISC, ModItems.INVAR_INGOT.get(), 0.25f, 100, "invar");

        oreSmelting(recipeOutput, CONSTANTAN_SMELTABELS, RecipeCategory.MISC, ModItems.CONSTANTAN_INGOT.get(), 0.25f, 200, "constantan");
        oreBlasting(recipeOutput, CONSTANTAN_SMELTABELS, RecipeCategory.MISC, ModItems.CONSTANTAN_INGOT.get(), 0.25f, 100, "constantan");

        oreSmelting(recipeOutput, TITA_CHROME_SMELTABLES, RecipeCategory.MISC, ModItems.TITA_CHROME_INGOT.get(), 0.25f, 200, "tita-chrome");
        oreBlasting(recipeOutput, TITA_CHROME_SMELTABLES, RecipeCategory.MISC, ModItems.TITA_CHROME_INGOT.get(), 0.25f, 100, "tita-chrome");

        oreSmelting(recipeOutput, COPPER_SMELTABLES, RecipeCategory.MISC, Items.COPPER_INGOT, 0.25f, 200, "copper");
        oreBlasting(recipeOutput, COPPER_SMELTABLES, RecipeCategory.MISC, Items.COPPER_INGOT, 0.25f, 100, "copper");

        oreSmelting(recipeOutput, IRON_SMELTABLES, RecipeCategory.MISC, Items.IRON_INGOT, 0.25f, 200, "iron");
        oreBlasting(recipeOutput, IRON_SMELTABLES, RecipeCategory.MISC, Items.IRON_INGOT, 0.25f, 100, "iron");

        oreSmelting(recipeOutput, GOLD_SMELTABLES, RecipeCategory.MISC, Items.GOLD_INGOT, 0.25f, 200, "gold");
        oreBlasting(recipeOutput, GOLD_SMELTABLES, RecipeCategory.MISC, Items.GOLD_INGOT, 0.25f, 100, "gold");

        /* =====================================================================
         *                           ALLOYING / INFUSING
         * ===================================================================== */

        // ---------- TITA-CHROME ----------
        alloyForging(
                recipeOutput,
                ModTags.Items.INGOTS_CHROMIUM,
                1,
                ModTags.Items.INGOTS_TITANIUM,
                1,
                ModItems.TITA_CHROME_INGOT,
                1,
                100,
                40
        );

        // ---------- BRONZE ----------
        alloyForging(
                recipeOutput,
                ModTags.Items.INGOTS_COPPER,
                3,
                ModTags.Items.INGOTS_TIN,
                1,
                ModItems.BRONZE_INGOT,
                4,
                100,
                40
        );

        // ---------- STEEL (infusing) ----------
        infusing(
                recipeOutput,
                ModTags.Items.INGOTS_IRON,
                ModTags.Items.DUSTS_COAL,
                ModItems.STEEL_INGOT,
                "steel",
                400,
                120
        );

        infusing(
                recipeOutput,
                ModTags.Items.INGOTS_STEEL,
                ModTags.Items.DUSTS_REDSTONE,
                ModItems.CIRCUIT_BOARD,
                "machine",
                400,
                120
        );

        upgradeRecipe(
                recipeOutput,
                ModTags.Items.BASIC_CIRCUITS, // Base item
                ModItems.ADVANCED_CIRCUIT_BOARD.get(), // Result
                ModTags.Items.WIRES_GOLD // Upgrade Item
        );

        upgradeModuleRecipe(
                recipeOutput,
                ModTags.Items.DUSTS_OSMIUM,
                ModItems.SPEED_MODULE.get(),
                ModTags.Items.WIRES_COPPER,   // horizontal
                ModTags.Items.WIRES_GOLD      // vertical
        );

        upgradeModuleRecipe(
                recipeOutput,
                ModTags.Items.DUSTS_GOLD,
                ModItems.EFFICIENCY_MODULE.get(),
                ModTags.Items.WIRES_COPPER,   // horizontal
                ModTags.Items.WIRES_GOLD      // vertical
        );


        // ---------- BRASS ----------
        // Classic brass: copper + zinc
        alloyForging(
                recipeOutput,
                ModTags.Items.INGOTS_COPPER,
                1,
                ModTags.Items.INGOTS_ZINC,
                1,
                ModItems.BRASS_INGOT,
                2,
                100,
                40
        );

        // ---------- ELECTRUM ----------
        // Classic electrum: gold + silver
        alloyForging(
                recipeOutput,
                ModTags.Items.INGOTS_GOLD,
                1,
                ModTags.Items.INGOTS_SILVER,
                1,
                ModItems.ELECTRUM_INGOT,
                2,
                100,
                40
        );

        // ---------- INVAR ----------
        // Classic invar: iron + nickel (2:1)
        alloyForging(
                recipeOutput,
                ModTags.Items.INGOTS_IRON,
                2,
                ModTags.Items.INGOTS_NICKEL,
                1,
                ModItems.INVAR_INGOT,
                3,
                120,
                50
        );

        // ---------- CONSTANTAN ----------
        // Classic constantan: copper + nickel (1:1)
        alloyForging(
                recipeOutput,
                ModTags.Items.INGOTS_COPPER,
                1,
                ModTags.Items.INGOTS_NICKEL,
                1,
                ModItems.CONSTANTAN_INGOT,
                2,
                120,
                50
        );

        /* =====================================================================
         *                           MACHINES / BLOCK RECIPES
         * ===================================================================== */

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALLOY_FORGER.get(), 1)
                .pattern("SPS")
                .pattern("PFP")
                .pattern("SPS")
                .define('P', ModBlocks.PANEL_BLOCK)
                .define('S', Ingredient.of(ModTags.Items.INGOTS_STEEL))
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

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PULVERIZER.get())
                .pattern("SPS")
                .pattern("PFP")
                .pattern("SRS")
                .define('S', Ingredient.of(ModTags.Items.INGOTS_STEEL))
                .define('P', ModBlocks.PANEL_BLOCK)
                .define('F', Blocks.PISTON)
                .define('R', Items.REDSTONE)
                .unlockedBy(getHasName(Blocks.PISTON), has(Blocks.PISTON))
                .unlockedBy(getHasName(ModBlocks.PANEL_BLOCK), has(ModBlocks.PANEL_BLOCK))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.INFUSER.get())
                .pattern("SPS")
                .pattern("PFP")
                .pattern("SRS")
                .define('S', Ingredient.of(ModTags.Items.INGOTS_STEEL))
                .define('P', ModBlocks.PANEL_BLOCK)
                .define('F', Blocks.SMOKER)
                .define('R', Items.REDSTONE)
                .unlockedBy(getHasName(Blocks.SMOKER), has(Blocks.SMOKER))
                .unlockedBy(getHasName(ModBlocks.PANEL_BLOCK), has(ModBlocks.PANEL_BLOCK))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BIO_FUEL_GENERATOR.get())
                .pattern("SPS")
                .pattern("SFS")
                .pattern("SRS")
                .define('S', ModItems.STEEL_INGOT)
                .define('P', ModBlocks.PANEL_BLOCK)
                .define('F', Blocks.COMPOSTER)
                .define('R', Items.REDSTONE_BLOCK)
                .unlockedBy(getHasName(Blocks.COMPOSTER), has(Blocks.COMPOSTER))
                .unlockedBy(getHasName(ModBlocks.PANEL_BLOCK), has(ModBlocks.PANEL_BLOCK))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.WIRE_DRAWER.get())
                .pattern("ISI")
                .pattern("PFP")
                .pattern("IRI")
                .define('I', Items.IRON_BARS)
                .define('S', Ingredient.of(ModTags.Items.INGOTS_STEEL))
                .define('P', ModBlocks.PANEL_BLOCK)
                .define('F', Items.TRIPWIRE_HOOK)
                .define('R', Items.REDSTONE)
                .unlockedBy(getHasName(Items.IRON_BARS), has(Items.IRON_BARS))
                .unlockedBy(getHasName(Items.TRIPWIRE_HOOK), has(Items.TRIPWIRE_HOOK))
                .unlockedBy(getHasName(ModBlocks.PANEL_BLOCK), has(ModBlocks.PANEL_BLOCK))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ROLLING_MILL.get())
                .pattern("SSS")
                .pattern("PFP")
                .pattern("SRS")
                .define('S', Ingredient.of(ModTags.Items.INGOTS_STEEL))
                .define('P', ModBlocks.PANEL_BLOCK)
                .define('F', Blocks.SMOOTH_STONE)
                .define('R', Items.REDSTONE)
                .unlockedBy(getHasName(Blocks.SMOOTH_STONE), has(Blocks.SMOOTH_STONE))
                .unlockedBy(getHasName(ModBlocks.PANEL_BLOCK), has(ModBlocks.PANEL_BLOCK))
                .save(recipeOutput);


        /* =====================================================================
         *                           METAL CONVERSIONS
         * ===================================================================== */

        metalConversions(recipeOutput, ModTags.Items.INGOTS_CHROMIUM, ModTags.Items.NUGGETS_CHROMIUM, ModBlocks.CHROMIUM_BLOCK, ModItems.CHROMIUM_INGOT, ModItems.CHROMIUM_NUGGET);
        metalConversions(recipeOutput, ModTags.Items.INGOTS_TITANIUM, ModTags.Items.NUGGETS_TITANIUM, ModBlocks.TITANIUM_BLOCK, ModItems.TITANIUM_INGOT, ModItems.TITANIUM_NUGGET);
        metalConversions(recipeOutput, ModTags.Items.INGOTS_TIN, ModTags.Items.NUGGETS_TIN, ModBlocks.TIN_BLOCK, ModItems.TIN_INGOT, ModItems.TIN_NUGGET);

        metalConversions(recipeOutput, ModTags.Items.INGOTS_TUNGSTEN, ModTags.Items.NUGGETS_TUNGSTEN, ModBlocks.TUNGSTEN_BLOCK, ModItems.TUNGSTEN_INGOT, ModItems.TUNGSTEN_NUGGET);
        metalConversions(recipeOutput, ModTags.Items.INGOTS_COBALT, ModTags.Items.NUGGETS_COBALT, ModBlocks.COBALT_BLOCK, ModItems.COBALT_INGOT, ModItems.COBALT_NUGGET);
        metalConversions(recipeOutput, ModTags.Items.INGOTS_OSMIUM, ModTags.Items.NUGGETS_OSMIUM, ModBlocks.OSMIUM_BLOCK, ModItems.OSMIUM_INGOT, ModItems.OSMIUM_NUGGET);
        metalConversions(recipeOutput, ModTags.Items.INGOTS_ZINC, ModTags.Items.NUGGETS_ZINC, ModBlocks.ZINC_BLOCK, ModItems.ZINC_INGOT, ModItems.ZINC_NUGGET);
        metalConversions(recipeOutput, ModTags.Items.INGOTS_SILVER, ModTags.Items.NUGGETS_SILVER, ModBlocks.SILVER_BLOCK, ModItems.SILVER_INGOT, ModItems.SILVER_NUGGET);
        metalConversions(recipeOutput, ModTags.Items.INGOTS_NICKEL, ModTags.Items.NUGGETS_NICKEL, ModBlocks.NICKEL_BLOCK, ModItems.NICKEL_INGOT, ModItems.NICKEL_NUGGET);
        metalConversions(recipeOutput, ModTags.Items.INGOTS_ALUMINUM, ModTags.Items.NUGGETS_ALUMINUM, ModBlocks.ALUMINIUM_BLOCK, ModItems.ALUMINIUM_INGOT, ModItems.ALUMINIUM_NUGGET);

        metalConversions(recipeOutput, ModTags.Items.INGOTS_STEEL, ModTags.Items.NUGGETS_STEEL, ModBlocks.STEEL_BLOCK, ModItems.STEEL_INGOT, ModItems.STEEL_NUGGET);
        metalConversions(recipeOutput, ModTags.Items.INGOTS_BRONZE, ModTags.Items.NUGGETS_BRONZE, ModBlocks.BRONZE_BLOCK, ModItems.BRONZE_INGOT, ModItems.BRONZE_NUGGET);
        metalConversions(recipeOutput, ModTags.Items.INGOTS_BRASS, ModTags.Items.NUGGETS_BRASS, ModBlocks.BRASS_BLOCK, ModItems.BRASS_INGOT, ModItems.BRASS_NUGGET);
        metalConversions(recipeOutput, ModTags.Items.INGOTS_ELECTRUM, ModTags.Items.NUGGETS_ELECTRUM, ModBlocks.ELECTRUM_BLOCK, ModItems.ELECTRUM_INGOT, ModItems.ELECTRUM_NUGGET);
        metalConversions(recipeOutput, ModTags.Items.INGOTS_INVAR, ModTags.Items.NUGGETS_INVAR, ModBlocks.INVAR_BLOCK, ModItems.INVAR_INGOT, ModItems.INVAR_NUGGET);
        metalConversions(recipeOutput, ModTags.Items.INGOTS_CONSTANTAN, ModTags.Items.NUGGETS_CONSTANTAN, ModBlocks.CONSTANTAN_BLOCK, ModItems.CONSTANTAN_INGOT, ModItems.CONSTANTAN_NUGGET);
        metalConversions(recipeOutput, ModTags.Items.INGOTS_TITA_CHROME, ModTags.Items.NUGGETS_TITA_CHROME, ModBlocks.TITA_CHROME_BLOCK, ModItems.TITA_CHROME_INGOT, ModItems.TITA_CHROME_NUGGET);

        /* =====================================================================
         *                           RAW MATERIAL CONVERSIONS
         * ===================================================================== */

        rawMaterialConversions(recipeOutput, ModTags.Items.RAW_CHROMIUM, ModBlocks.RAW_CHROMIUM_BLOCK, ModItems.RAW_CHROMIUM);
        rawMaterialConversions(recipeOutput, ModTags.Items.RAW_TIN, ModBlocks.RAW_TIN_BLOCK, ModItems.RAW_TIN);
        rawMaterialConversions(recipeOutput, ModTags.Items.RAW_TITANIUM, ModBlocks.RAW_TITANIUM_BLOCK, ModItems.RAW_TITANIUM);

        rawMaterialConversions(recipeOutput, ModTags.Items.RAW_TUNGSTEN, ModBlocks.RAW_TUNGSTEN_BLOCK, ModItems.RAW_TUNGSTEN);
        rawMaterialConversions(recipeOutput, ModTags.Items.RAW_COBALT, ModBlocks.RAW_COBALT_BLOCK, ModItems.RAW_COBALT);
        rawMaterialConversions(recipeOutput, ModTags.Items.RAW_OSMIUM, ModBlocks.RAW_OSMIUM_BLOCK, ModItems.RAW_OSMIUM);
        rawMaterialConversions(recipeOutput, ModTags.Items.RAW_ZINC, ModBlocks.RAW_ZINC_BLOCK, ModItems.RAW_ZINC);
        rawMaterialConversions(recipeOutput, ModTags.Items.RAW_SILVER, ModBlocks.RAW_SILVER_BLOCK, ModItems.RAW_SILVER);
        rawMaterialConversions(recipeOutput, ModTags.Items.RAW_NICKEL, ModBlocks.RAW_NICKEL_BLOCK, ModItems.RAW_NICKEL);
        rawMaterialConversions(recipeOutput, ModTags.Items.RAW_ALUMINUM, ModBlocks.RAW_ALUMINIUM_BLOCK, ModItems.RAW_ALUMINIUM);


        /* =====================================================================
         *                           PULVERIZING
         * ===================================================================== */

        /* ---------- ORES (generated from data table) ---------- */

        for (OrePulverizingData data : ORE_PULVERIZING) {

            // Stone ore variant → cobblestone byproduct
            pulverizing(
                    recipeOutput,
                    Ingredient.of(data.stoneOre().get().asItem()),
                    data.dust().get(),
                    Blocks.COBBLESTONE,
                    data.cookTime(),
                    data.energy(),
                    PulverizingSource.STONE
            );

            // Deepslate ore variant → deepslate byproduct
            pulverizing(
                    recipeOutput,
                    Ingredient.of(data.deepslateOre().get().asItem()),
                    data.dust().get(),
                    Blocks.COBBLED_DEEPSLATE,
                    data.cookTime(),
                    data.energy(),
                    PulverizingSource.DEEPSLATE
            );
        }

        /* ---------- INGOTS ---------- */
        pulverizing(recipeOutput, Ingredient.of(ModTags.Items.INGOTS_IRON), ModItems.IRON_DUST, null, 80, 10, PulverizingSource.INGOT);
        pulverizing(recipeOutput, Ingredient.of(ModTags.Items.INGOTS_COPPER), ModItems.COPPER_DUST, null, 80, 10, PulverizingSource.INGOT);
        pulverizing(recipeOutput, Ingredient.of(Items.DIAMOND), ModItems.DIAMOND_DUST, null, 80, 10, PulverizingSource.INGOT);
        pulverizing(recipeOutput, Ingredient.of(ModTags.Items.INGOTS_GOLD), ModItems.GOLD_DUST, null, 80, 10, PulverizingSource.INGOT);

        pulverizing(recipeOutput, Ingredient.of(ModTags.Items.INGOTS_ALUMINUM), ModItems.ALUMINIUM_DUST, null, 80, 10, PulverizingSource.INGOT);
        pulverizing(recipeOutput, Ingredient.of(ModTags.Items.INGOTS_CHROMIUM), ModItems.CHROMIUM_DUST, null, 80, 10, PulverizingSource.INGOT);
        pulverizing(recipeOutput, Ingredient.of(ModTags.Items.INGOTS_TITANIUM), ModItems.TITANIUM_DUST, null, 80, 10, PulverizingSource.INGOT);
        pulverizing(recipeOutput, Ingredient.of(ModTags.Items.INGOTS_TIN), ModItems.TIN_DUST, null, 80, 10, PulverizingSource.INGOT);
        pulverizing(recipeOutput, Ingredient.of(ModTags.Items.INGOTS_TUNGSTEN), ModItems.TUNGSTEN_DUST, null, 100, 15, PulverizingSource.INGOT);
        pulverizing(recipeOutput, Ingredient.of(ModTags.Items.INGOTS_COBALT), ModItems.COBALT_DUST, null, 80, 10, PulverizingSource.INGOT);
        pulverizing(recipeOutput, Ingredient.of(ModTags.Items.INGOTS_OSMIUM), ModItems.OSMIUM_DUST, null, 80, 10, PulverizingSource.INGOT);
        pulverizing(recipeOutput, Ingredient.of(ModTags.Items.INGOTS_ZINC), ModItems.ZINC_DUST, null, 60, 8, PulverizingSource.INGOT);
        pulverizing(recipeOutput, Ingredient.of(ModTags.Items.INGOTS_SILVER), ModItems.SILVER_DUST, null, 60, 8, PulverizingSource.INGOT);
        pulverizing(recipeOutput, Ingredient.of(ModTags.Items.INGOTS_NICKEL), ModItems.NICKEL_DUST, null, 60, 8, PulverizingSource.INGOT);

        /* ---------- ALLOY INGOTS ---------- */

        pulverizing(recipeOutput, Ingredient.of(ModTags.Items.INGOTS_STEEL), ModItems.STEEL_DUST, null, 100, 15, PulverizingSource.INGOT);
        pulverizing(recipeOutput, Ingredient.of(ModTags.Items.INGOTS_BRONZE), ModItems.BRONZE_DUST, null, 80, 10, PulverizingSource.INGOT);
        pulverizing(recipeOutput, Ingredient.of(ModTags.Items.INGOTS_BRASS), ModItems.BRASS_DUST, null, 80, 10, PulverizingSource.INGOT);
        pulverizing(recipeOutput, Ingredient.of(ModTags.Items.INGOTS_ELECTRUM), ModItems.ELECTRUM_DUST, null, 80, 10, PulverizingSource.INGOT);
        pulverizing(recipeOutput, Ingredient.of(ModTags.Items.INGOTS_INVAR), ModItems.INVAR_DUST, null, 100, 15, PulverizingSource.INGOT);
        pulverizing(recipeOutput, Ingredient.of(ModTags.Items.INGOTS_CONSTANTAN), ModItems.CONSTANTAN_DUST, null, 100, 15, PulverizingSource.INGOT);
        pulverizing(recipeOutput, Ingredient.of(ModTags.Items.INGOTS_TITA_CHROME), ModItems.TITA_CHROME_DUST, null, 120, 20, PulverizingSource.INGOT);

        /* ---------- MISC ---------- */

        pulverizing(recipeOutput, Ingredient.of(ItemTags.COALS), ModItems.COAL_DUST, null, 80, 10, PulverizingSource.COAL);

        compostableBiomassPulverizing(recipeOutput, 60, 8);
        basicMetalProcessing(recipeOutput);
    }

    /* =====================================================================
     *                           HELPERS
     * ===================================================================== */

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
                        Ingredient.of(inputA),
                        Ingredient.of(inputB),
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

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ingot)
                .pattern("NNN")
                .pattern("NNN")
                .pattern("NNN")
                .define('N', Ingredient.of(nuggetTag))
                .unlockedBy("has_nugget", has(nuggetTag))
                .save(recipeOutput, id(ingot, "_from_nuggets"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, nugget, 9)
                .requires(Ingredient.of(ingotTag))
                .unlockedBy("has_ingot", has(ingotTag))
                .save(recipeOutput, id(nugget, "_from_ingot"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block)
                .pattern("III")
                .pattern("III")
                .pattern("III")
                .define('I', Ingredient.of(ingotTag))
                .unlockedBy("has_ingot", has(ingotTag))
                .save(recipeOutput, id(block, "_from_ingots"));

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

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, rawBlock)
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .define('R', Ingredient.of(rawItemTag))
                .unlockedBy("has_raw", has(rawItemTag))
                .save(recipeOutput, id(rawBlock, "_from_raw_items"));

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
            int energyPerTick
    ) {

        // ------------------------------------------------------------
        // Build the byproduct stack (or empty if you pass null)
        // ------------------------------------------------------------
        ItemStack byproductStack =
                byproduct == null ? ItemStack.EMPTY : new ItemStack(byproduct);

        // ------------------------------------------------------------
        // Infer the source from the byproduct item you pass.
        //
        // Rule:
        // - If you pass DEEPSLATE (or COBBLED_DEEPSLATE), treat as "deepslate"
        // - Otherwise, treat as "stone"
        //
        // This lets you use ONE ore tag (stone + deepslate),
        // and only differ recipes by the byproduct you pass.
        // ------------------------------------------------------------
        PulverizingSource inferredSource;

        if (byproduct != null
                && (byproduct.asItem() == Blocks.DEEPSLATE.asItem()
                || byproduct.asItem() == Blocks.COBBLED_DEEPSLATE.asItem())) {
            inferredSource = PulverizingSource.DEEPSLATE;
        } else {
            inferredSource = PulverizingSource.STONE;
        }

        // ------------------------------------------------------------
        // Create the recipe object
        // ------------------------------------------------------------
        PulverizingRecipe recipe =
                new PulverizingRecipe(
                        Ingredient.of(input),
                        new ItemStack(result),
                        byproductStack,
                        cookTime,
                        energyPerTick
                );

        // ------------------------------------------------------------
        // Register with an ID that includes the inferred source
        // ------------------------------------------------------------
        recipeOutput.accept(
                ResourceLocation.fromNamespaceAndPath(
                        Succsessentials_extended.MOD_ID,
                        getItemName(result)
                                + "_from_pulverizing_"
                                + inferredSource.id()
                ),
                recipe,
                null
        );
    }


    protected static void pulverizing(
            RecipeOutput recipeOutput,
            Ingredient input,
            ItemLike result,
            ItemLike byproduct,
            int cookTime,
            int energyPerTick,
            PulverizingSource source
    ) {

        // ------------------------------------------------------------
        // Build the byproduct stack.
        // If no byproduct is supplied, use an empty stack.
        // ------------------------------------------------------------
        ItemStack byproductStack =
                byproduct == null ? ItemStack.EMPTY : new ItemStack(byproduct);

        // ------------------------------------------------------------
        // Create the pulverizing recipe definition
        // ------------------------------------------------------------
        PulverizingRecipe recipe =
                new PulverizingRecipe(
                        input,
                        new ItemStack(result),
                        byproductStack,
                        cookTime,
                        energyPerTick
                );

        // ------------------------------------------------------------
        // Register the recipe using a deterministic ID
        // based on output item + source type
        // ------------------------------------------------------------
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

        ComposterBlock.COMPOSTABLES.object2FloatEntrySet().forEach(entry -> {

            Item item = entry.getKey().asItem();
            float chance = entry.getFloatValue();

            if (item == Items.AIR) {
                return;
            }

            int biomassAmount;
            if (chance >= 1.0f) {
                biomassAmount = 3;
            } else if (chance >= 0.65f) {
                biomassAmount = 2;
            } else {
                biomassAmount = 1;
            }

            PulverizingRecipe recipe =
                    new PulverizingRecipe(
                            Ingredient.of(item),
                            new ItemStack(ModItems.BIOMASS.get(), biomassAmount),
                            ItemStack.EMPTY,
                            cookTime,
                            energyPerTick
                    );

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

    protected static void upgradeRecipe(
            RecipeOutput recipeOutput,
            TagKey<Item> baseItem,     // Item being upgraded (center)
            ItemLike resultItem,       // Upgraded result
            TagKey<Item> upgradeItem   // Left & right item
    ) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, resultItem)
                // Middle row
                .pattern("   ")
                .pattern("UCU")
                .pattern("   ")
                .define('C', Ingredient.of(baseItem))
                .define('U', Ingredient.of(upgradeItem))
                // Unlock when base item is obtained
                .unlockedBy("has_base", has(baseItem))
                // <result>_from_<base>_upgrade
                .save(
                        recipeOutput,
                        ResourceLocation.fromNamespaceAndPath(
                                Succsessentials_extended.MOD_ID,
                                getItemName(resultItem) + "_upgrade"
                        )
                );
    }


    protected static void upgradeModuleRecipe(
            RecipeOutput recipeOutput,
            TagKey<Item> baseItem,          // Core dust
            ItemLike resultItem,            // Upgrade module
            TagKey<Item> horizontalItem,    // Left & right
            TagKey<Item> verticalItem       // Top & bottom
    ) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, resultItem)
                // Top
                .pattern(" V ")
                // Middle
                .pattern("HCH")
                // Bottom
                .pattern(" V ")
                .define('C', Ingredient.of(baseItem))
                .define('H', Ingredient.of(horizontalItem))
                .define('V', Ingredient.of(verticalItem))
                // Unlock when base item is obtained
                .unlockedBy("has_base", has(baseItem))
                // <result>_from_<base>_module
                .save(
                        recipeOutput,
                        ResourceLocation.fromNamespaceAndPath(
                                Succsessentials_extended.MOD_ID,
                                getItemName(resultItem) + "_module"
                        )
                );
    }

    protected static void hammering(
            RecipeOutput recipeOutput,
            TagKey<Item> input,          // ingot tag
            ItemLike result,             // plate
            int resultCount,
            int durabilityCost
    ) {

        HammerRecipe recipe =
                new HammerRecipe(
                        Ingredient.of(input),
                        Ingredient.of(ModTags.Items.HAMMERS),
                        new ItemStack(result, resultCount),
                        durabilityCost
                );

        recipeOutput.accept(
                ResourceLocation.fromNamespaceAndPath(
                        Succsessentials_extended.MOD_ID,
                        getItemName(result) + "_from_hammering"
                ),
                recipe,
                null
        );
    }

    protected static void wireCutting(
            RecipeOutput recipeOutput,
            TagKey<Item> input,          // plate tag
            ItemLike result,             // wire
            int resultCount,
            int durabilityCost
    ) {

        WireCutterRecipe recipe =
                new WireCutterRecipe(
                        Ingredient.of(input),
                        Ingredient.of(ModTags.Items.WIRE_CUTTERS),
                        new ItemStack(result, resultCount),
                        durabilityCost
                );

        recipeOutput.accept(
                ResourceLocation.fromNamespaceAndPath(
                        Succsessentials_extended.MOD_ID,
                        getItemName(result) + "_from_wire_cutting"
                ),
                recipe,
                null
        );
    }

    protected static void wireDrawing(
            RecipeOutput recipeOutput,
            TagKey<Item> input,
            ItemLike result,
            int resultCount,
            int cookTime,
            int energyPerTick
    ) {

        WireDrawingRecipe recipe =
                new WireDrawingRecipe(
                        Ingredient.of(input),
                        1, // inputCount: 1 plate
                        new ItemStack(result, resultCount),
                        cookTime,
                        energyPerTick
                );

        recipeOutput.accept(
                ResourceLocation.fromNamespaceAndPath(
                        Succsessentials_extended.MOD_ID,
                        getItemName(result) + "_from_wire_drawing"
                ),
                recipe,
                null
        );
    }


    protected static void rollingMill(
            RecipeOutput recipeOutput,
            TagKey<Item> input,      // ingot tag
            ItemLike result,         // plate
            int resultCount,
            int cookTime,
            int energyPerTick
    ) {

        RollingMillRecipe recipe =
                new RollingMillRecipe(
                        Ingredient.of(input),
                        1, // inputCount: 1 ingot
                        new ItemStack(result, resultCount),
                        cookTime,
                        energyPerTick
                );

        recipeOutput.accept(
                ResourceLocation.fromNamespaceAndPath(
                        Succsessentials_extended.MOD_ID,
                        getItemName(result) + "_from_rolling_mill"
                ),
                recipe,
                null
        );
    }



    private static void basicMetalProcessing(RecipeOutput recipeOutput) {

        hammering(recipeOutput, ModTags.Items.INGOTS_IRON, ModItems.IRON_PLATE.get(), 1, 1);
        wireCutting(recipeOutput, ModTags.Items.PLATES_IRON, ModItems.IRON_WIRE.get(), 2, 1);

        hammering(recipeOutput, ModTags.Items.INGOTS_COPPER, ModItems.COPPER_PLATE.get(), 1, 1);
        wireCutting(recipeOutput, ModTags.Items.PLATES_COPPER, ModItems.COPPER_WIRE.get(), 2, 1);

        hammering(recipeOutput, ModTags.Items.INGOTS_GOLD, ModItems.GOLD_PLATE.get(), 1, 1);
        wireCutting(recipeOutput, ModTags.Items.PLATES_GOLD, ModItems.GOLD_WIRE.get(), 3, 1);

        hammering(recipeOutput, ModTags.Items.INGOTS_ELECTRUM, ModItems.ELECTRUM_PLATE.get(), 1, 1);
        wireCutting(recipeOutput, ModTags.Items.PLATES_ELECTRUM, ModItems.ELECTRUM_WIRE.get(), 3, 1);

        hammering(recipeOutput, ModTags.Items.INGOTS_CHROMIUM, ModItems.CHROMIUM_PLATE.get(), 1, 1);
        hammering(recipeOutput, ModTags.Items.INGOTS_TITANIUM, ModItems.TITANIUM_PLATE.get(), 1, 1);
        hammering(recipeOutput, ModTags.Items.INGOTS_TIN, ModItems.TIN_PLATE.get(), 1, 1);
        hammering(recipeOutput, ModTags.Items.INGOTS_TUNGSTEN, ModItems.TUNGSTEN_PLATE.get(), 1, 1);
        hammering(recipeOutput, ModTags.Items.INGOTS_COBALT, ModItems.COBALT_PLATE.get(), 1, 1);
        hammering(recipeOutput, ModTags.Items.INGOTS_OSMIUM, ModItems.OSMIUM_PLATE.get(), 1, 1);
        hammering(recipeOutput, ModTags.Items.INGOTS_ZINC, ModItems.ZINC_PLATE.get(), 1, 1);
        hammering(recipeOutput, ModTags.Items.INGOTS_SILVER, ModItems.SILVER_PLATE.get(), 1, 1);
        hammering(recipeOutput, ModTags.Items.INGOTS_NICKEL, ModItems.NICKEL_PLATE.get(), 1, 1);
        hammering(recipeOutput, ModTags.Items.INGOTS_STEEL, ModItems.STEEL_PLATE.get(), 1, 1);
        hammering(recipeOutput, ModTags.Items.INGOTS_BRONZE, ModItems.BRONZE_PLATE.get(), 1, 1);
        hammering(recipeOutput, ModTags.Items.INGOTS_BRASS, ModItems.BRASS_PLATE.get(), 1, 1);
        hammering(recipeOutput, ModTags.Items.INGOTS_INVAR, ModItems.INVAR_PLATE.get(), 1, 1);
        hammering(recipeOutput, ModTags.Items.INGOTS_CONSTANTAN, ModItems.CONSTANTAN_PLATE.get(), 1, 1);
        hammering(recipeOutput, ModTags.Items.INGOTS_TITA_CHROME, ModItems.TITA_CHROME_PLATE.get(), 1, 1);
        hammering(recipeOutput, ModTags.Items.INGOTS_ALUMINUM, ModItems.ALUMINIUM_PLATE.get(), 1, 1);


        rollingMill(recipeOutput, ModTags.Items.INGOTS_IRON, ModItems.IRON_PLATE.get(), 1, 80, 20);
        rollingMill(recipeOutput, ModTags.Items.INGOTS_ALUMINUM, ModItems.ALUMINIUM_PLATE.get(), 1, 80, 20);
        rollingMill(recipeOutput, ModTags.Items.INGOTS_COPPER, ModItems.COPPER_PLATE.get(), 1, 80, 20);
        rollingMill(recipeOutput, ModTags.Items.INGOTS_GOLD, ModItems.GOLD_PLATE.get(), 1, 80, 20);
        rollingMill(recipeOutput, ModTags.Items.INGOTS_ELECTRUM, ModItems.ELECTRUM_PLATE.get(), 1, 80, 20);
        rollingMill(recipeOutput, ModTags.Items.INGOTS_CHROMIUM, ModItems.CHROMIUM_PLATE.get(), 1, 100, 25);
        rollingMill(recipeOutput, ModTags.Items.INGOTS_TITANIUM, ModItems.TITANIUM_PLATE.get(), 1, 100, 25);
        rollingMill(recipeOutput, ModTags.Items.INGOTS_TUNGSTEN, ModItems.TUNGSTEN_PLATE.get(), 1, 120, 30);

        rollingMill(recipeOutput, ModTags.Items.INGOTS_STEEL, ModItems.STEEL_PLATE.get(), 1, 100, 25);
        rollingMill(recipeOutput, ModTags.Items.INGOTS_BRONZE, ModItems.BRONZE_PLATE.get(), 1, 80, 20);
        rollingMill(recipeOutput, ModTags.Items.INGOTS_BRASS, ModItems.BRASS_PLATE.get(), 1, 80, 20);
        rollingMill(recipeOutput, ModTags.Items.INGOTS_INVAR, ModItems.INVAR_PLATE.get(), 1, 100, 25);
        rollingMill(recipeOutput, ModTags.Items.INGOTS_CONSTANTAN, ModItems.CONSTANTAN_PLATE.get(), 1, 100, 25);
        rollingMill(recipeOutput, ModTags.Items.INGOTS_TITA_CHROME, ModItems.TITA_CHROME_PLATE.get(), 1, 120, 30);

        wireDrawing(recipeOutput, ModTags.Items.PLATES_IRON, ModItems.IRON_WIRE.get(), 2, 60, 15);
        wireDrawing(recipeOutput, ModTags.Items.PLATES_COPPER, ModItems.COPPER_WIRE.get(), 2, 60, 15);
        wireDrawing(recipeOutput, ModTags.Items.PLATES_GOLD, ModItems.GOLD_WIRE.get(), 3, 60, 15);
        wireDrawing(recipeOutput, ModTags.Items.PLATES_ELECTRUM, ModItems.ELECTRUM_WIRE.get(), 3, 60, 15);
    }
}
