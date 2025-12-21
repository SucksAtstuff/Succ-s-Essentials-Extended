package net.succ.succsessentials_extended.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.block.ModBlocks;
import net.succ.succsessentials_extended.item.ModItems;
import net.succ.succsessentials_extended.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {

    public ModItemTagProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            CompletableFuture<TagLookup<Block>> blockTags,
            @Nullable ExistingFileHelper existingFileHelper
    ) {
        super(output, lookupProvider, blockTags, Succsessentials_extended.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {


        /* ===================================================================== */
        /*                         STORAGE BLOCK ITEM TAGS                        */
        /* ===================================================================== */

        tag(ModTags.Items.STORAGE_BLOCKS)
                .addTag(ModTags.Items.STORAGE_IRON)
                .addTag(ModTags.Items.STORAGE_GOLD)
                .addTag(ModTags.Items.STORAGE_COPPER)
                .addTag(ModTags.Items.STORAGE_TIN)
                .addTag(ModTags.Items.STORAGE_CHROMIUM)
                .addTag(ModTags.Items.STORAGE_TITANIUM)
                .addTag(ModTags.Items.STORAGE_STEEL)
                .addTag(ModTags.Items.STORAGE_BRONZE)
                .addTag(ModTags.Items.STORAGE_BRASS)
                .addTag(ModTags.Items.STORAGE_ELECTRUM)
                .addTag(ModTags.Items.STORAGE_INVAR)
                .addTag(ModTags.Items.STORAGE_CONSTANTAN)
                .addTag(ModTags.Items.STORAGE_ZINC)
                .addTag(ModTags.Items.STORAGE_OSMIUM)
                .addTag(ModTags.Items.STORAGE_URANIUM)
                .addTag(ModTags.Items.STORAGE_ALUMINUM)
                .addTag(ModTags.Items.STORAGE_NICKEL)
                .addTag(ModTags.Items.STORAGE_SILVER)
                .addTag(ModTags.Items.STORAGE_LEAD)
                .addTag(ModTags.Items.STORAGE_TITA_CHROME)
                .addTag(ModTags.Items.STORAGE_TUNGSTEN)
                .addTag(ModTags.Items.STORAGE_COBALT);

        tag(ModTags.Items.STORAGE_IRON);
        tag(ModTags.Items.STORAGE_GOLD);
        tag(ModTags.Items.STORAGE_COPPER);

        tag(ModTags.Items.STORAGE_TIN)
                .add(ModBlocks.TIN_BLOCK.get().asItem());


        tag(ModTags.Items.STORAGE_CHROMIUM)
                .add(ModBlocks.CHROMIUM_BLOCK.get().asItem())
                .add(ModBlocks.RAW_CHROMIUM_BLOCK.get().asItem());


        tag(ModTags.Items.STORAGE_TITANIUM)
                .add(ModBlocks.TITANIUM_BLOCK.get().asItem())
                .add(ModBlocks.RAW_TITANIUM_BLOCK.get().asItem());


        tag(ModTags.Items.STORAGE_STEEL)
                .add(ModBlocks.STEEL_BLOCK.get().asItem());


        tag(ModTags.Items.STORAGE_BRONZE)
                .add(ModBlocks.BRONZE_BLOCK.get().asItem());


        tag(ModTags.Items.STORAGE_TITA_CHROME)
                .add(ModBlocks.TITA_CHROME_BLOCK.get().asItem());

        tag(ModTags.Items.STORAGE_BRASS)
                .add(ModBlocks.BRASS_BLOCK.get().asItem());

        tag(ModTags.Items.STORAGE_ELECTRUM)
                .add(ModBlocks.ELECTRUM_BLOCK.get().asItem());

        tag(ModTags.Items.STORAGE_INVAR)
                .add(ModBlocks.INVAR_BLOCK.get().asItem());

        tag(ModTags.Items.STORAGE_CONSTANTAN)
                .add(ModBlocks.CONSTANTAN_BLOCK.get().asItem());


        tag(ModTags.Items.STORAGE_ZINC)
                .add(ModBlocks.ZINC_BLOCK.get().asItem())
                .add(ModBlocks.RAW_ZINC_BLOCK.get().asItem());

        tag(ModTags.Items.STORAGE_OSMIUM)
                .add(ModBlocks.OSMIUM_BLOCK.get().asItem())
                .add(ModBlocks.RAW_OSMIUM_BLOCK.get().asItem());

        tag(ModTags.Items.STORAGE_SILVER)
                .add(ModBlocks.SILVER_BLOCK.get().asItem())
                .add(ModBlocks.RAW_SILVER_BLOCK.get().asItem());

        tag(ModTags.Items.STORAGE_NICKEL)
                .add(ModBlocks.NICKEL_BLOCK.get().asItem())
                .add(ModBlocks.RAW_NICKEL_BLOCK.get().asItem());

        tag(ModTags.Items.STORAGE_TUNGSTEN)
                .add(ModBlocks.TUNGSTEN_BLOCK.get().asItem())
                .add(ModBlocks.RAW_TUNGSTEN_BLOCK.get().asItem());

        tag(ModTags.Items.STORAGE_COBALT)
                .add(ModBlocks.COBALT_BLOCK.get().asItem())
                .add(ModBlocks.RAW_COBALT_BLOCK.get().asItem());

        tag(ModTags.Items.STORAGE_URANIUM);
        tag(ModTags.Items.STORAGE_ALUMINUM);
        tag(ModTags.Items.STORAGE_LEAD);
        tag(ModTags.Items.STORAGE_TUNGSTEN);

        /* ===================================================================== */
        /*                           VANILLA TOOL TAGS                            */
        /* ===================================================================== */

        tag(ItemTags.SWORDS).add(ModItems.CHROMIUM_SWORD.get());
        tag(ItemTags.PICKAXES).add(ModItems.CHROMIUM_PICKAXE.get());
        tag(ItemTags.AXES).add(ModItems.CHROMIUM_AXE.get());
        tag(ItemTags.SHOVELS).add(ModItems.CHROMIUM_SHOVEL.get());
        tag(ItemTags.HOES).add(ModItems.CHROMIUM_HOE.get());

        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.CHROMIUM_HELMET.get())
                .add(ModItems.CHROMIUM_CHESTPLATE.get())
                .add(ModItems.CHROMIUM_LEGGINGS.get())
                .add(ModItems.CHROMIUM_BOOTS.get());

        tag(ItemTags.ARMOR_ENCHANTABLE)
                .add(ModItems.CHROMIUM_HELMET.get());
        tag(ItemTags.HEAD_ARMOR_ENCHANTABLE)
                .add(ModItems.CHROMIUM_HELMET.get());
        tag(ItemTags.CHEST_ARMOR_ENCHANTABLE)
                .add(ModItems.CHROMIUM_CHESTPLATE.get());
        tag(ItemTags.LEG_ARMOR_ENCHANTABLE)
                .add(ModItems.CHROMIUM_LEGGINGS.get());
        tag(ItemTags.FOOT_ARMOR_ENCHANTABLE)
                .add(ModItems.CHROMIUM_BOOTS.get());

        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .add(ModItems.CHROMIUM_SWORD.get())
                .add(ModItems.CHROMIUM_AXE.get())
                .add(ModItems.CHROMIUM_PICKAXE.get())
                .add(ModItems.CHROMIUM_SHOVEL.get())
                .add(ModItems.CHROMIUM_HOE.get())
                .add(ModItems.CHROMIUM_HAMMER.get())
                .add(ModItems.CHROMIUM_REINFORCED_HAMMER.get())
                .add(ModItems.CHROMIUM_PAXEL.get())
                .add(ModItems.CHROMIUM_HELMET.get())
                .add(ModItems.CHROMIUM_CHESTPLATE.get())
                .add(ModItems.CHROMIUM_LEGGINGS.get())
                .add(ModItems.CHROMIUM_BOOTS.get());

        tag(ItemTags.WEAPON_ENCHANTABLE)
                .add(ModItems.CHROMIUM_SWORD.get())
                .add(ModItems.CHROMIUM_AXE.get());

        tag(ItemTags.SHARP_WEAPON_ENCHANTABLE)
                .add(ModItems.CHROMIUM_SWORD.get())
                .add(ModItems.CHROMIUM_AXE.get());

        tag(ItemTags.FIRE_ASPECT_ENCHANTABLE)
                .add(ModItems.CHROMIUM_SWORD.get())
                .add(ModItems.CHROMIUM_AXE.get());

        tag(ItemTags.MINING_ENCHANTABLE)
                .add(ModItems.CHROMIUM_PICKAXE.get())
                .add(ModItems.CHROMIUM_SHOVEL.get())
                .add(ModItems.CHROMIUM_HOE.get())
                .add(ModItems.CHROMIUM_AXE.get())
                .add(ModItems.CHROMIUM_HAMMER.get())
                .add(ModItems.CHROMIUM_REINFORCED_HAMMER.get())
                .add(ModItems.CHROMIUM_PAXEL.get());

        tag(ItemTags.MINING_LOOT_ENCHANTABLE)
                .add(ModItems.CHROMIUM_PICKAXE.get())
                .add(ModItems.CHROMIUM_HAMMER.get())
                .add(ModItems.CHROMIUM_REINFORCED_HAMMER.get())
                .add(ModItems.CHROMIUM_PAXEL.get());

        /* ===================================================================== */
        /*                            ROOT C: TAGS                                */
        /* ===================================================================== */

        tag(ModTags.Items.INGOTS)
                .add(ModItems.CHROMIUM_INGOT.get())
                .add(ModItems.TITANIUM_INGOT.get())
                .add(ModItems.TIN_INGOT.get())
                .add(ModItems.TUNGSTEN_INGOT.get())
                .add(ModItems.COBALT_INGOT.get())
                .add(ModItems.OSMIUM_INGOT.get())
                .add(ModItems.ZINC_INGOT.get())
                .add(ModItems.SILVER_INGOT.get())
                .add(ModItems.NICKEL_INGOT.get())
                .add(ModItems.STEEL_INGOT.get())
                .add(ModItems.BRONZE_INGOT.get())
                .add(ModItems.BRASS_INGOT.get())
                .add(ModItems.ELECTRUM_INGOT.get())
                .add(ModItems.INVAR_INGOT.get())
                .add(ModItems.CONSTANTAN_INGOT.get())
                .add(ModItems.TITA_CHROME_INGOT.get());

        tag(ModTags.Items.NUGGETS)
                .add(ModItems.CHROMIUM_NUGGET.get())
                .add(ModItems.TITANIUM_NUGGET.get())
                .add(ModItems.TIN_NUGGET.get())
                .add(ModItems.TUNGSTEN_NUGGET.get())
                .add(ModItems.COBALT_NUGGET.get())
                .add(ModItems.OSMIUM_NUGGET.get())
                .add(ModItems.ZINC_NUGGET.get())
                .add(ModItems.SILVER_NUGGET.get())
                .add(ModItems.NICKEL_NUGGET.get())
                .add(ModItems.STEEL_NUGGET.get())
                .add(ModItems.BRONZE_NUGGET.get())
                .add(ModItems.BRASS_NUGGET.get())
                .add(ModItems.ELECTRUM_NUGGET.get())
                .add(ModItems.INVAR_NUGGET.get())
                .add(ModItems.CONSTANTAN_NUGGET.get())
                .add(ModItems.TITA_CHROME_NUGGET.get());

        tag(ModTags.Items.DUSTS)
                .add(ModItems.COAL_DUST.get())
                .add(ModItems.CHROMIUM_DUST.get())
                .add(ModItems.TITANIUM_DUST.get())
                .add(ModItems.TIN_DUST.get())
                .add(ModItems.TUNGSTEN_DUST.get())
                .add(ModItems.COBALT_DUST.get())
                .add(ModItems.OSMIUM_DUST.get())
                .add(ModItems.ZINC_DUST.get())
                .add(ModItems.SILVER_DUST.get())
                .add(ModItems.NICKEL_DUST.get())
                .add(ModItems.STEEL_DUST.get())
                .add(ModItems.BRONZE_DUST.get())
                .add(ModItems.BRASS_DUST.get())
                .add(ModItems.ELECTRUM_DUST.get())
                .add(ModItems.INVAR_DUST.get())
                .add(ModItems.CONSTANTAN_DUST.get())
                .add(ModItems.TITA_CHROME_DUST.get());

        tag(ModTags.Items.RAW_MATERIALS)
                .add(ModItems.RAW_CHROMIUM.get())
                .add(ModItems.RAW_TITANIUM.get())
                .add(ModItems.RAW_TIN.get())
                .add(ModItems.RAW_TUNGSTEN.get())
                .add(ModItems.RAW_COBALT.get())
                .add(ModItems.RAW_OSMIUM.get())
                .add(ModItems.RAW_ZINC.get())
                .add(ModItems.RAW_SILVER.get())
                .add(ModItems.RAW_NICKEL.get());

        tag(ModTags.Items.ORES)
                .add(ModBlocks.CHROMIUM_ORE.get().asItem())
                .add(ModBlocks.DEEPSLATE_CHROMIUM_ORE.get().asItem())
                .add(ModBlocks.TITANIUM_ORE.get().asItem())
                .add(ModBlocks.DEEPSLATE_TITANIUM_ORE.get().asItem())
                .add(ModBlocks.TIN_ORE.get().asItem())
                .add(ModBlocks.DEEPSLATE_TIN_ORE.get().asItem())
                .add(ModBlocks.TUNGSTEN_ORE.get().asItem())
                .add(ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get().asItem())
                .add(ModBlocks.COBALT_ORE.get().asItem())
                .add(ModBlocks.DEEPSLATE_COBALT_ORE.get().asItem())
                .add(ModBlocks.OSMIUM_ORE.get().asItem())
                .add(ModBlocks.DEEPSLATE_OSMIUM_ORE.get().asItem())
                .add(ModBlocks.ZINC_ORE.get().asItem())
                .add(ModBlocks.DEEPSLATE_ZINC_ORE.get().asItem())
                .add(ModBlocks.SILVER_ORE.get().asItem())
                .add(ModBlocks.DEEPSLATE_SILVER_ORE.get().asItem())
                .add(ModBlocks.NICKEL_ORE.get().asItem())
                .add(ModBlocks.DEEPSLATE_NICKEL_ORE.get().asItem());

        tag(ModTags.Items.PLATES);
        tag(ModTags.Items.RODS);
        tag(ModTags.Items.GEARS);


        /* ===================================================================== */
        /*                           TOOL / ARMOR TAGS                            */
        /* ===================================================================== */

        tag(ModTags.Items.TOOLS)
                .add(ModItems.CHROMIUM_SWORD.get())
                .add(ModItems.CHROMIUM_PICKAXE.get())
                .add(ModItems.CHROMIUM_AXE.get())
                .add(ModItems.CHROMIUM_SHOVEL.get())
                .add(ModItems.CHROMIUM_HOE.get())
                .add(ModItems.CHROMIUM_HAMMER.get())
                .add(ModItems.CHROMIUM_REINFORCED_HAMMER.get())
                .add(ModItems.CHROMIUM_PAXEL.get());

        tag(ModTags.Items.MINING_TOOLS)
                .addTag(ModTags.Items.TOOLS);
        tag(ModTags.Items.MELEE_WEAPONS)
                .add(ModItems.CHROMIUM_SWORD.get())
                .add(ModItems.CHROMIUM_AXE.get())
                .add(ModItems.CHROMIUM_HAMMER.get())
                .add(ModItems.CHROMIUM_REINFORCED_HAMMER.get());

        tag(ModTags.Items.PICKAXES).add(ModItems.CHROMIUM_PICKAXE.get());
        tag(ModTags.Items.AXES).add(ModItems.CHROMIUM_AXE.get());
        tag(ModTags.Items.SHOVELS).add(ModItems.CHROMIUM_SHOVEL.get());
        tag(ModTags.Items.SWORDS).add(ModItems.CHROMIUM_SWORD.get());

        tag(ModTags.Items.ARMOR)
                .add(ModItems.CHROMIUM_HELMET.get())
                .add(ModItems.CHROMIUM_CHESTPLATE.get())
                .add(ModItems.CHROMIUM_LEGGINGS.get())
                .add(ModItems.CHROMIUM_BOOTS.get());

        tag(ModTags.Items.ENCHANTABLES)
                .addTag(ModTags.Items.TOOLS)
                .addTag(ModTags.Items.ARMOR);

        /* ===================================================================== */
        /*                     MATERIAL-SPECIFIC TAG INITIALIZATION               */
        /* ===================================================================== */

        // Chromium
        tag(ModTags.Items.INGOTS_CHROMIUM).add(ModItems.CHROMIUM_INGOT.get());
        tag(ModTags.Items.NUGGETS_CHROMIUM).add(ModItems.CHROMIUM_NUGGET.get());
        tag(ModTags.Items.DUSTS_CHROMIUM).add(ModItems.CHROMIUM_DUST.get());
        tag(ModTags.Items.PLATES_CHROMIUM);
        tag(ModTags.Items.RODS_CHROMIUM);
        tag(ModTags.Items.GEARS_CHROMIUM);
        tag(ModTags.Items.RAW_CHROMIUM).add(ModItems.RAW_CHROMIUM.get());
        tag(ModTags.Items.ORES_CHROMIUM)
                .add(ModBlocks.CHROMIUM_ORE.get().asItem());
        tag(ModTags.Items.DEEPSLATE_ORES_CHROMIUM)
                .add(ModBlocks.DEEPSLATE_CHROMIUM_ORE.get().asItem());

        // Titanium
        tag(ModTags.Items.INGOTS_TITANIUM).add(ModItems.TITANIUM_INGOT.get());
        tag(ModTags.Items.NUGGETS_TITANIUM).add(ModItems.TITANIUM_NUGGET.get());
        tag(ModTags.Items.DUSTS_TITANIUM).add(ModItems.TITANIUM_DUST.get());
        tag(ModTags.Items.PLATES_TITANIUM);
        tag(ModTags.Items.RODS_TITANIUM);
        tag(ModTags.Items.GEARS_TITANIUM);
        tag(ModTags.Items.RAW_TITANIUM).add(ModItems.RAW_TITANIUM.get());
        tag(ModTags.Items.ORES_TITANIUM)
                .add(ModBlocks.TITANIUM_ORE.get().asItem());
        tag(ModTags.Items.DEEPSLATE_ORES_TITANIUM)
                .add(ModBlocks.DEEPSLATE_TITANIUM_ORE.get().asItem());

        // Tin
        tag(ModTags.Items.INGOTS_TIN).add(ModItems.TIN_INGOT.get());
        tag(ModTags.Items.NUGGETS_TIN).add(ModItems.TIN_NUGGET.get());
        tag(ModTags.Items.DUSTS_TIN).add(ModItems.TIN_DUST.get());
        tag(ModTags.Items.PLATES_TIN);
        tag(ModTags.Items.RODS_TIN);
        tag(ModTags.Items.GEARS_TIN);
        tag(ModTags.Items.RAW_TIN).add(ModItems.RAW_TIN.get());
        tag(ModTags.Items.ORES_TIN)
                .add(ModBlocks.TIN_ORE.get().asItem());
        tag(ModTags.Items.DEEPSLATE_ORES_TIN)
                .add(ModBlocks.DEEPSLATE_TIN_ORE.get().asItem());

        // Tungsten
        tag(ModTags.Items.INGOTS_TUNGSTEN).add(ModItems.TUNGSTEN_INGOT.get());
        tag(ModTags.Items.NUGGETS_TUNGSTEN).add(ModItems.TUNGSTEN_NUGGET.get());
        tag(ModTags.Items.DUSTS_TUNGSTEN).add(ModItems.TUNGSTEN_DUST.get());
        tag(ModTags.Items.PLATES_TUNGSTEN);
        tag(ModTags.Items.RODS_TUNGSTEN);
        tag(ModTags.Items.GEARS_TUNGSTEN);
        tag(ModTags.Items.RAW_TUNGSTEN).add(ModItems.RAW_TUNGSTEN.get());
        tag(ModTags.Items.ORES_TUNGSTEN)
                .add(ModBlocks.TUNGSTEN_ORE.get().asItem());
        tag(ModTags.Items.DEEPSLATE_ORES_TUNGSTEN)
                .add(ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get().asItem());

        // Cobalt
        tag(ModTags.Items.INGOTS_COBALT).add(ModItems.COBALT_INGOT.get());
        tag(ModTags.Items.NUGGETS_COBALT).add(ModItems.COBALT_NUGGET.get());
        tag(ModTags.Items.DUSTS_COBALT).add(ModItems.COBALT_DUST.get());
        tag(ModTags.Items.PLATES_COBALT);
        tag(ModTags.Items.RODS_COBALT);
        tag(ModTags.Items.GEARS_COBALT);
        tag(ModTags.Items.RAW_COBALT).add(ModItems.RAW_COBALT.get());
        tag(ModTags.Items.ORES_COBALT)
                .add(ModBlocks.COBALT_ORE.get().asItem());
        tag(ModTags.Items.DEEPSLATE_ORES_COBALT)
                .add(ModBlocks.DEEPSLATE_COBALT_ORE.get().asItem());

        // Steel
        tag(ModTags.Items.INGOTS_STEEL).add(ModItems.STEEL_INGOT.get());
        tag(ModTags.Items.NUGGETS_STEEL).add(ModItems.STEEL_NUGGET.get());
        tag(ModTags.Items.DUSTS_STEEL).add(ModItems.STEEL_DUST.get());
        tag(ModTags.Items.PLATES_STEEL);
        tag(ModTags.Items.RODS_STEEL);
        tag(ModTags.Items.GEARS_STEEL);

        // Bronze
        tag(ModTags.Items.INGOTS_BRONZE).add(ModItems.BRONZE_INGOT.get());
        tag(ModTags.Items.NUGGETS_BRONZE).add(ModItems.BRONZE_NUGGET.get());
        tag(ModTags.Items.DUSTS_BRONZE).add(ModItems.BRONZE_DUST.get());
        tag(ModTags.Items.PLATES_BRONZE);
        tag(ModTags.Items.RODS_BRONZE);
        tag(ModTags.Items.GEARS_BRONZE);

        // Brass
        tag(ModTags.Items.INGOTS_BRASS).add(ModItems.BRASS_INGOT.get());
        tag(ModTags.Items.NUGGETS_BRASS).add(ModItems.BRASS_NUGGET.get());
        tag(ModTags.Items.DUSTS_BRASS).add(ModItems.BRASS_DUST.get());
        tag(ModTags.Items.PLATES_BRASS);
        tag(ModTags.Items.RODS_BRASS);
        tag(ModTags.Items.GEARS_BRASS);

        // Electrum
        tag(ModTags.Items.INGOTS_ELECTRUM).add(ModItems.ELECTRUM_INGOT.get());
        tag(ModTags.Items.NUGGETS_ELECTRUM).add(ModItems.ELECTRUM_NUGGET.get());
        tag(ModTags.Items.DUSTS_ELECTRUM).add(ModItems.ELECTRUM_DUST.get());
        tag(ModTags.Items.PLATES_ELECTRUM);
        tag(ModTags.Items.RODS_ELECTRUM);
        tag(ModTags.Items.GEARS_ELECTRUM);

        // Invar
        tag(ModTags.Items.INGOTS_INVAR).add(ModItems.INVAR_INGOT.get());
        tag(ModTags.Items.NUGGETS_INVAR).add(ModItems.INVAR_NUGGET.get());
        tag(ModTags.Items.DUSTS_INVAR).add(ModItems.INVAR_DUST.get());
        tag(ModTags.Items.PLATES_INVAR);
        tag(ModTags.Items.RODS_INVAR);
        tag(ModTags.Items.GEARS_INVAR);

        // Constantan
        tag(ModTags.Items.INGOTS_CONSTANTAN).add(ModItems.CONSTANTAN_INGOT.get());
        tag(ModTags.Items.NUGGETS_CONSTANTAN).add(ModItems.CONSTANTAN_NUGGET.get());
        tag(ModTags.Items.DUSTS_CONSTANTAN).add(ModItems.CONSTANTAN_DUST.get());
        tag(ModTags.Items.PLATES_CONSTANTAN);
        tag(ModTags.Items.RODS_CONSTANTAN);
        tag(ModTags.Items.GEARS_CONSTANTAN);

        // Tita-Chrome
        tag(ModTags.Items.INGOTS_TITA_CHROME).add(ModItems.TITA_CHROME_INGOT.get());
        tag(ModTags.Items.NUGGETS_TITA_CHROME).add(ModItems.TITA_CHROME_NUGGET.get());
        tag(ModTags.Items.DUSTS_TITA_CHROME).add(ModItems.TITA_CHROME_DUST.get());
        tag(ModTags.Items.PLATES_TITA_CHROME);
        tag(ModTags.Items.RODS_TITA_CHROME);
        tag(ModTags.Items.GEARS_TITA_CHROME);

        // Lead
        tag(ModTags.Items.NUGGETS_LEAD);
        tag(ModTags.Items.DUSTS_LEAD);
        tag(ModTags.Items.PLATES_LEAD);
        tag(ModTags.Items.RODS_LEAD);
        tag(ModTags.Items.GEARS_LEAD);
        tag(ModTags.Items.RAW_LEAD);
        tag(ModTags.Items.ORES_LEAD);
        tag(ModTags.Items.DEEPSLATE_ORES_LEAD);

        // Silver
        tag(ModTags.Items.INGOTS_SILVER).add(ModItems.SILVER_INGOT.get());
        tag(ModTags.Items.NUGGETS_SILVER).add(ModItems.SILVER_NUGGET.get());
        tag(ModTags.Items.DUSTS_SILVER).add(ModItems.SILVER_DUST.get());
        tag(ModTags.Items.PLATES_SILVER);
        tag(ModTags.Items.RODS_SILVER);
        tag(ModTags.Items.GEARS_SILVER);
        tag(ModTags.Items.RAW_SILVER).add(ModItems.RAW_SILVER.get());
        tag(ModTags.Items.ORES_SILVER)
                .add(ModBlocks.SILVER_ORE.get().asItem());
        tag(ModTags.Items.DEEPSLATE_ORES_SILVER)
                .add(ModBlocks.DEEPSLATE_SILVER_ORE.get().asItem());


        // Nickel
        tag(ModTags.Items.INGOTS_NICKEL).add(ModItems.NICKEL_INGOT.get());
        tag(ModTags.Items.NUGGETS_NICKEL).add(ModItems.NICKEL_NUGGET.get());
        tag(ModTags.Items.DUSTS_NICKEL).add(ModItems.NICKEL_DUST.get());
        tag(ModTags.Items.PLATES_NICKEL);
        tag(ModTags.Items.RODS_NICKEL);
        tag(ModTags.Items.GEARS_NICKEL);
        tag(ModTags.Items.RAW_NICKEL).add(ModItems.RAW_NICKEL.get());
        tag(ModTags.Items.ORES_NICKEL)
                .add(ModBlocks.NICKEL_ORE.get().asItem());
        tag(ModTags.Items.DEEPSLATE_ORES_NICKEL)
                .add(ModBlocks.DEEPSLATE_NICKEL_ORE.get().asItem());


        // Zinc
        tag(ModTags.Items.INGOTS_ZINC).add(ModItems.ZINC_INGOT.get());
        tag(ModTags.Items.NUGGETS_ZINC).add(ModItems.ZINC_NUGGET.get());
        tag(ModTags.Items.DUSTS_ZINC).add(ModItems.ZINC_DUST.get());
        tag(ModTags.Items.PLATES_ZINC);
        tag(ModTags.Items.RODS_ZINC);
        tag(ModTags.Items.GEARS_ZINC);
        tag(ModTags.Items.RAW_ZINC).add(ModItems.RAW_ZINC.get());
        tag(ModTags.Items.ORES_ZINC)
                .add(ModBlocks.ZINC_ORE.get().asItem());
        tag(ModTags.Items.DEEPSLATE_ORES_ZINC)
                .add(ModBlocks.DEEPSLATE_ZINC_ORE.get().asItem());


        // Aluminum
        tag(ModTags.Items.NUGGETS_ALUMINUM);
        tag(ModTags.Items.DUSTS_ALUMINUM);
        tag(ModTags.Items.PLATES_ALUMINUM);
        tag(ModTags.Items.RODS_ALUMINUM);
        tag(ModTags.Items.GEARS_ALUMINUM);
        tag(ModTags.Items.RAW_ALUMINUM);
        tag(ModTags.Items.ORES_ALUMINUM);
        tag(ModTags.Items.DEEPSLATE_ORES_ALUMINUM);

        // Uranium
        tag(ModTags.Items.NUGGETS_URANIUM);
        tag(ModTags.Items.DUSTS_URANIUM);
        tag(ModTags.Items.PLATES_URANIUM);
        tag(ModTags.Items.RODS_URANIUM);
        tag(ModTags.Items.GEARS_URANIUM);
        tag(ModTags.Items.RAW_URANIUM);
        tag(ModTags.Items.ORES_URANIUM);
        tag(ModTags.Items.DEEPSLATE_ORES_URANIUM);

        // Osmium
        tag(ModTags.Items.INGOTS_OSMIUM).add(ModItems.OSMIUM_INGOT.get());
        tag(ModTags.Items.NUGGETS_OSMIUM).add(ModItems.OSMIUM_NUGGET.get());
        tag(ModTags.Items.DUSTS_OSMIUM).add(ModItems.OSMIUM_DUST.get());
        tag(ModTags.Items.PLATES_OSMIUM);
        tag(ModTags.Items.RODS_OSMIUM);
        tag(ModTags.Items.GEARS_OSMIUM);
        tag(ModTags.Items.RAW_OSMIUM).add(ModItems.RAW_OSMIUM.get());
        tag(ModTags.Items.ORES_OSMIUM)
                .add(ModBlocks.OSMIUM_ORE.get().asItem());
        tag(ModTags.Items.DEEPSLATE_ORES_OSMIUM)
                .add(ModBlocks.DEEPSLATE_OSMIUM_ORE.get().asItem());



        // Coal
        tag(ModTags.Items.DUSTS_COAL)
                .add(ModItems.COAL_DUST.get());

        tag(ModTags.Items.FUELS)
                .add(ModItems.COAL_DUST.get());
        tag(ModTags.Items.BIO_FUELS)
                .add(ModItems.BIOMASS.get())
                .add(ModItems.BIOMASS_PELLET.get());
    }
}
