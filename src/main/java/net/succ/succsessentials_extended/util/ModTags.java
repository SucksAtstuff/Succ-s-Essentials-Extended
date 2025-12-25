package net.succ.succsessentials_extended.util;


import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.succ.succsessentials_extended.Succsessentials_extended;

public class ModTags {
    public static class Items {
        /* ================= ROOT TAGS ================= */

        public static final TagKey<Item> INGOTS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots"));
        public static final TagKey<Item> NUGGETS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets"));
        public static final TagKey<Item> DUSTS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dusts"));
        public static final TagKey<Item> PLATES =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "plates"));
        public static final TagKey<Item> RODS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "rods"));
        public static final TagKey<Item> GEARS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gears"));
        public static final TagKey<Item> RAW_MATERIALS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "raw_materials"));
        public static final TagKey<Item> ORES =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores"));
        public static final TagKey<Item> STORAGE_BLOCKS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks"));
        public static final TagKey<Item> WIRES =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "wires"));
        public static final TagKey<Item> WIRES_METAL =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "wires/all_metal"));

        public static final TagKey<Item> TOOLS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "tools"));
        public static final TagKey<Item> MINING_TOOLS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "tools/mining_tool"));
        public static final TagKey<Item> MELEE_WEAPONS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "tools/melee_weapon"));
        public static final TagKey<Item> PICKAXES =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "tools/pickaxes"));
        public static final TagKey<Item> SHOVELS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "tools/shovels"));
        public static final TagKey<Item> AXES =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "tools/axes"));
        public static final TagKey<Item> SWORDS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "tools/swords"));
        public static final TagKey<Item> ARMOR =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "armors"));
        public static final TagKey<Item> ENCHANTABLES =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "enchantables"));

        public static final TagKey<Item> FUELS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "fuels"));
        public static final TagKey<Item> BIO_FUELS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "fuels/bio"));

        public static final TagKey<Item> CIRCUITS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "circuits"));
        public static final TagKey<Item> BASIC_CIRCUITS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "circuits/basic"));
        public static final TagKey<Item> ADVANCED_CIRCUITS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "circuits/advanced"));
        public static final TagKey<Item> ELITE_CIRCUITS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "circuits/elite"));

        /* ================= IRON ================= */

        public static final TagKey<Item> INGOTS_IRON =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/iron"));
        public static final TagKey<Item> NUGGETS_IRON =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets/iron"));
        public static final TagKey<Item> DUSTS_IRON =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dusts/iron"));
        public static final TagKey<Item> PLATES_IRON =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "plates/iron"));
        public static final TagKey<Item> RODS_IRON =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "rods/iron"));
        public static final TagKey<Item> GEARS_IRON =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gears/iron"));
        public static final TagKey<Item> RAW_IRON =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "raw_materials/iron"));
        public static final TagKey<Item> ORES_IRON =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/iron"));
        public static final TagKey<Item> DEEPSLATE_ORES_IRON =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/iron/deepslate"));
        public static final TagKey<Item> STORAGE_IRON =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/iron"));
        public static final TagKey<Item> WIRES_IRON =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "wires/iron"));

        /* ================= GOLD ================= */

        public static final TagKey<Item> INGOTS_GOLD =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/gold"));
        public static final TagKey<Item> NUGGETS_GOLD =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets/gold"));
        public static final TagKey<Item> DUSTS_GOLD =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dusts/gold"));
        public static final TagKey<Item> PLATES_GOLD =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "plates/gold"));
        public static final TagKey<Item> RODS_GOLD =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "rods/gold"));
        public static final TagKey<Item> GEARS_GOLD =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gears/gold"));
        public static final TagKey<Item> RAW_GOLD =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "raw_materials/gold"));
        public static final TagKey<Item> ORES_GOLD =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/gold"));
        public static final TagKey<Item> DEEPSLATE_ORES_GOLD =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/gold/deepslate"));
        public static final TagKey<Item> STORAGE_GOLD =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/gold"));
        public static final TagKey<Item> WIRES_GOLD =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "wires/gold"));

        /* ================= DIAMOND ================= */
        public static final TagKey<Item> DUSTS_DIAMOND =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dusts/diamond"));

        /* ================= COPPER ================= */

        public static final TagKey<Item> INGOTS_COPPER =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/copper"));
        public static final TagKey<Item> NUGGETS_COPPER =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets/copper"));
        public static final TagKey<Item> DUSTS_COPPER =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dusts/copper"));
        public static final TagKey<Item> PLATES_COPPER =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "plates/copper"));
        public static final TagKey<Item> RODS_COPPER =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "rods/copper"));
        public static final TagKey<Item> GEARS_COPPER =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gears/copper"));
        public static final TagKey<Item> RAW_COPPER =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "raw_materials/copper"));
        public static final TagKey<Item> ORES_COPPER =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/copper"));
        public static final TagKey<Item> DEEPSLATE_ORES_COPPER =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/copper/deepslate"));
        public static final TagKey<Item> STORAGE_COPPER =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/copper"));
        public static final TagKey<Item> WIRES_COPPER =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "wires/copper"));

        /* ================= TIN ================= */

        public static final TagKey<Item> INGOTS_TIN =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/tin"));
        public static final TagKey<Item> NUGGETS_TIN =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets/tin"));
        public static final TagKey<Item> DUSTS_TIN =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dusts/tin"));
        public static final TagKey<Item> PLATES_TIN =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "plates/tin"));
        public static final TagKey<Item> RODS_TIN =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "rods/tin"));
        public static final TagKey<Item> GEARS_TIN =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gears/tin"));
        public static final TagKey<Item> RAW_TIN =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "raw_materials/tin"));
        public static final TagKey<Item> ORES_TIN =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/tin"));
        public static final TagKey<Item> DEEPSLATE_ORES_TIN =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/tin/deepslate"));
        public static final TagKey<Item> STORAGE_TIN =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/tin"));

        /* ================= TITANIUM ================= */

        public static final TagKey<Item> INGOTS_TITANIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/titanium"));
        public static final TagKey<Item> NUGGETS_TITANIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets/titanium"));
        public static final TagKey<Item> DUSTS_TITANIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dusts/titanium"));
        public static final TagKey<Item> PLATES_TITANIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "plates/titanium"));
        public static final TagKey<Item> RODS_TITANIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "rods/titanium"));
        public static final TagKey<Item> GEARS_TITANIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gears/titanium"));
        public static final TagKey<Item> RAW_TITANIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "raw_materials/titanium"));
        public static final TagKey<Item> ORES_TITANIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/titanium"));
        public static final TagKey<Item> DEEPSLATE_ORES_TITANIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/titanium/deepslate"));
        public static final TagKey<Item> STORAGE_TITANIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/titanium"));

        /* ================= LEAD ================= */

        public static final TagKey<Item> INGOTS_LEAD =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/lead"));
        public static final TagKey<Item> NUGGETS_LEAD =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets/lead"));
        public static final TagKey<Item> DUSTS_LEAD =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dusts/lead"));
        public static final TagKey<Item> PLATES_LEAD =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "plates/lead"));
        public static final TagKey<Item> RODS_LEAD =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "rods/lead"));
        public static final TagKey<Item> GEARS_LEAD =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gears/lead"));
        public static final TagKey<Item> RAW_LEAD =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "raw_materials/lead"));
        public static final TagKey<Item> ORES_LEAD =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/lead"));
        public static final TagKey<Item> DEEPSLATE_ORES_LEAD =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/lead/deepslate"));
        public static final TagKey<Item> STORAGE_LEAD =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/lead"));

        /* ================= SILVER ================= */

        public static final TagKey<Item> INGOTS_SILVER =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/silver"));
        public static final TagKey<Item> NUGGETS_SILVER =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets/silver"));
        public static final TagKey<Item> DUSTS_SILVER =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dusts/silver"));
        public static final TagKey<Item> PLATES_SILVER =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "plates/silver"));
        public static final TagKey<Item> RODS_SILVER =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "rods/silver"));
        public static final TagKey<Item> GEARS_SILVER =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gears/silver"));
        public static final TagKey<Item> RAW_SILVER =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "raw_materials/silver"));
        public static final TagKey<Item> ORES_SILVER =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/silver"));
        public static final TagKey<Item> DEEPSLATE_ORES_SILVER =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/silver/deepslate"));
        public static final TagKey<Item> STORAGE_SILVER =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/silver"));

        /* ================= NICKEL ================= */

        public static final TagKey<Item> INGOTS_NICKEL =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/nickel"));
        public static final TagKey<Item> NUGGETS_NICKEL =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets/nickel"));
        public static final TagKey<Item> DUSTS_NICKEL =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dusts/nickel"));
        public static final TagKey<Item> PLATES_NICKEL =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "plates/nickel"));
        public static final TagKey<Item> RODS_NICKEL =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "rods/nickel"));
        public static final TagKey<Item> GEARS_NICKEL =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gears/nickel"));
        public static final TagKey<Item> RAW_NICKEL =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "raw_materials/nickel"));
        public static final TagKey<Item> ORES_NICKEL =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/nickel"));
        public static final TagKey<Item> DEEPSLATE_ORES_NICKEL =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/nickel/deepslate"));
        public static final TagKey<Item> STORAGE_NICKEL =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/nickel"));

        /* ================= ZINC ================= */

        public static final TagKey<Item> INGOTS_ZINC =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/zinc"));
        public static final TagKey<Item> NUGGETS_ZINC =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets/zinc"));
        public static final TagKey<Item> DUSTS_ZINC =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dusts/zinc"));
        public static final TagKey<Item> PLATES_ZINC =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "plates/zinc"));
        public static final TagKey<Item> RODS_ZINC =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "rods/zinc"));
        public static final TagKey<Item> GEARS_ZINC =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gears/zinc"));
        public static final TagKey<Item> RAW_ZINC =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "raw_materials/zinc"));
        public static final TagKey<Item> ORES_ZINC =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/zinc"));
        public static final TagKey<Item> DEEPSLATE_ORES_ZINC =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/zinc/deepslate"));
        public static final TagKey<Item> STORAGE_ZINC =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/zinc"));

        /* ================= ALUMINUM ================= */

        public static final TagKey<Item> ORES_ALUMINUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/aluminum"));
        public static final TagKey<Item> DEEPSLATE_ORES_ALUMINUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/aluminum/deepslate"));
        public static final TagKey<Item> RAW_ALUMINUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "raw_materials/aluminum"));
        public static final TagKey<Item> INGOTS_ALUMINUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/aluminum"));
        public static final TagKey<Item> NUGGETS_ALUMINUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets/aluminum"));
        public static final TagKey<Item> DUSTS_ALUMINUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dusts/aluminum"));
        public static final TagKey<Item> PLATES_ALUMINUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "plates/aluminum"));
        public static final TagKey<Item> RODS_ALUMINUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "rods/aluminum"));
        public static final TagKey<Item> GEARS_ALUMINUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gears/aluminum"));
        public static final TagKey<Item> STORAGE_ALUMINUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/aluminum"));

        /* ================= URANIUM ================= */

        public static final TagKey<Item> ORES_URANIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/uranium"));
        public static final TagKey<Item> DEEPSLATE_ORES_URANIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/uranium/deepslate"));
        public static final TagKey<Item> RAW_URANIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "raw_materials/uranium"));
        public static final TagKey<Item> INGOTS_URANIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/uranium"));
        public static final TagKey<Item> NUGGETS_URANIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets/uranium"));
        public static final TagKey<Item> DUSTS_URANIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dusts/uranium"));
        public static final TagKey<Item> PLATES_URANIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "plates/uranium"));
        public static final TagKey<Item> RODS_URANIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "rods/uranium"));
        public static final TagKey<Item> GEARS_URANIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gears/uranium"));
        public static final TagKey<Item> STORAGE_URANIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/uranium"));

        /* ================= OSMIUM ================= */

        public static final TagKey<Item> ORES_OSMIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/osmium"));
        public static final TagKey<Item> DEEPSLATE_ORES_OSMIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/osmium/deepslate"));
        public static final TagKey<Item> RAW_OSMIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "raw_materials/osmium"));
        public static final TagKey<Item> INGOTS_OSMIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/osmium"));
        public static final TagKey<Item> NUGGETS_OSMIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets/osmium"));
        public static final TagKey<Item> DUSTS_OSMIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dusts/osmium"));
        public static final TagKey<Item> PLATES_OSMIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "plates/osmium"));
        public static final TagKey<Item> RODS_OSMIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "rods/osmium"));
        public static final TagKey<Item> GEARS_OSMIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gears/osmium"));
        public static final TagKey<Item> STORAGE_OSMIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/osmium"));

        /* ================= CHROMIUM ================= */

        public static final TagKey<Item> INGOTS_CHROMIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/chromium"));
        public static final TagKey<Item> NUGGETS_CHROMIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets/chromium"));
        public static final TagKey<Item> DUSTS_CHROMIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dusts/chromium"));
        public static final TagKey<Item> PLATES_CHROMIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "plates/chromium"));
        public static final TagKey<Item> RODS_CHROMIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "rods/chromium"));
        public static final TagKey<Item> GEARS_CHROMIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gears/chromium"));
        public static final TagKey<Item> RAW_CHROMIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "raw_materials/chromium"));
        public static final TagKey<Item> ORES_CHROMIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/chromium"));
        public static final TagKey<Item> DEEPSLATE_ORES_CHROMIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/chromium/deepslate"));
        public static final TagKey<Item> STORAGE_CHROMIUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/chromium"));

        /* ================= STEEL ================= */

        public static final TagKey<Item> INGOTS_STEEL =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/steel"));
        public static final TagKey<Item> NUGGETS_STEEL =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets/steel"));
        public static final TagKey<Item> DUSTS_STEEL =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dusts/steel"));
        public static final TagKey<Item> PLATES_STEEL =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "plates/steel"));
        public static final TagKey<Item> RODS_STEEL =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "rods/steel"));
        public static final TagKey<Item> GEARS_STEEL =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gears/steel"));
        public static final TagKey<Item> STORAGE_STEEL =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/steel"));

        /* ================= TITA-CHROME ================= */

        public static final TagKey<Item> INGOTS_TITA_CHROME =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/tita_chrome"));
        public static final TagKey<Item> NUGGETS_TITA_CHROME =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets/tita_chrome"));
        public static final TagKey<Item> DUSTS_TITA_CHROME =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dusts/tita_chrome"));
        public static final TagKey<Item> PLATES_TITA_CHROME =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "plates/tita_chrome"));
        public static final TagKey<Item> RODS_TITA_CHROME =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "rods/tita_chrome"));
        public static final TagKey<Item> GEARS_TITA_CHROME =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gears/tita_chrome"));
        public static final TagKey<Item> ORES_TITA_CHROME =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/tita_chrome"));
        public static final TagKey<Item> STORAGE_TITA_CHROME =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/tita_chrome"));

        /* ---------- BRONZE ---------- */

        public static final TagKey<Item> INGOTS_BRONZE =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/bronze"));
        public static final TagKey<Item> NUGGETS_BRONZE =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets/bronze"));
        public static final TagKey<Item> DUSTS_BRONZE =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dusts/bronze"));
        public static final TagKey<Item> PLATES_BRONZE =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "plates/bronze"));
        public static final TagKey<Item> RODS_BRONZE =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "rods/bronze"));
        public static final TagKey<Item> GEARS_BRONZE =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gears/bronze"));
        public static final TagKey<Item> STORAGE_BRONZE =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/bronze"));

        /* ================= TUNGSTEN ================= */

        public static final TagKey<Item> INGOTS_TUNGSTEN =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/tungsten"));
        public static final TagKey<Item> NUGGETS_TUNGSTEN =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets/tungsten"));
        public static final TagKey<Item> DUSTS_TUNGSTEN =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dusts/tungsten"));
        public static final TagKey<Item> RAW_TUNGSTEN =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "raw_materials/tungsten"));
        public static final TagKey<Item> ORES_TUNGSTEN =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/tungsten"));
        public static final TagKey<Item> DEEPSLATE_ORES_TUNGSTEN =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/tungsten/deepslate"));
        public static final TagKey<Item> PLATES_TUNGSTEN =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "plates/tungsten"));
        public static final TagKey<Item> RODS_TUNGSTEN =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "rods/tungsten"));
        public static final TagKey<Item> GEARS_TUNGSTEN =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gears/tungsten"));
        public static final TagKey<Item> STORAGE_TUNGSTEN =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/tungsten"));


        /* ================= COBALT ================= */

        public static final TagKey<Item> INGOTS_COBALT =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/cobalt"));
        public static final TagKey<Item> NUGGETS_COBALT =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets/cobalt"));
        public static final TagKey<Item> DUSTS_COBALT =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dusts/cobalt"));
        public static final TagKey<Item> RAW_COBALT =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "raw_materials/cobalt"));
        public static final TagKey<Item> ORES_COBALT =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/cobalt"));
        public static final TagKey<Item> DEEPSLATE_ORES_COBALT =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores/cobalt/deepslate"));
        public static final TagKey<Item> PLATES_COBALT =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "plates/cobalt"));
        public static final TagKey<Item> RODS_COBALT =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "rods/cobalt"));
        public static final TagKey<Item> GEARS_COBALT =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gears/cobalt"));
        public static final TagKey<Item> STORAGE_COBALT =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/cobalt"));


        /* ---------- BRASS ---------- */

        public static final TagKey<Item> STORAGE_BRASS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/brass"));
        public static final TagKey<Item> INGOTS_BRASS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/brass"));
        public static final TagKey<Item> NUGGETS_BRASS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets/brass"));
        public static final TagKey<Item> DUSTS_BRASS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dusts/brass"));
        public static final TagKey<Item> PLATES_BRASS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "plates/brass"));
        public static final TagKey<Item> RODS_BRASS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "rods/brass"));
        public static final TagKey<Item> GEARS_BRASS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gears/brass"));


        /* ---------- ELECTRUM ---------- */

        public static final TagKey<Item> PLATES_ELECTRUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "plates/electrum"));
        public static final TagKey<Item> RODS_ELECTRUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "rods/electrum"));
        public static final TagKey<Item> GEARS_ELECTRUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gears/electrum"));
        public static final TagKey<Item> STORAGE_ELECTRUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/electrum"));
        public static final TagKey<Item> INGOTS_ELECTRUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/electrum"));
        public static final TagKey<Item> NUGGETS_ELECTRUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets/electrum"));
        public static final TagKey<Item> DUSTS_ELECTRUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dusts/electrum"));
        public static final TagKey<Item> WIRES_ELECTRUM =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "wires/electrum"));

        /* ---------- INVAR ---------- */
        public static final TagKey<Item> PLATES_INVAR =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "plates/invar"));
        public static final TagKey<Item> RODS_INVAR =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "rods/invar"));
        public static final TagKey<Item> GEARS_INVAR =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gears/invar"));
        public static final TagKey<Item> STORAGE_INVAR =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/invar"));
        public static final TagKey<Item> INGOTS_INVAR =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/invar"));
        public static final TagKey<Item> NUGGETS_INVAR =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets/invar"));
        public static final TagKey<Item> DUSTS_INVAR =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dusts/invar"));

        /* ---------- CONSTANTAN ---------- */

        public static final TagKey<Item> PLATES_CONSTANTAN =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "plates/constantan"));
        public static final TagKey<Item> RODS_CONSTANTAN =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "rods/constantan"));
        public static final TagKey<Item> GEARS_CONSTANTAN =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gears/constantan"));
        public static final TagKey<Item> STORAGE_CONSTANTAN =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/constantan"));
        public static final TagKey<Item> INGOTS_CONSTANTAN =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/constantan"));
        public static final TagKey<Item> NUGGETS_CONSTANTAN =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets/constantan"));
        public static final TagKey<Item> DUSTS_CONSTANTAN =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dusts/constantan"));

        /* ================= MISC ================= */

        public static final TagKey<Item> DUSTS_COAL =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dusts/coal"));

        public static final TagKey<Item> DUSTS_REDSTONE =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dusts/redstone"));



        private static TagKey<Item> createTag (String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Succsessentials_extended.MOD_ID, name));
        }
    }



    public static class Blocks {

        public static final TagKey<Block> NEEDS_CHROMIUM_TOOL = createTag("needs_chromium_tool");
        public static final TagKey<Block> INCORRECT_FOR_CHROMIUM_TOOL = createTag("incorrect_for_chromium_tool");

        public static final TagKey<Block> PAXEL_MINEABLE = createTag("mineable/paxel");

        /* ================= ROOT TAGS ================= */

        public static final TagKey<Block> ORES =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores"));
        public static final TagKey<Block> STORAGE_BLOCKS =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks"));

        /* ================= IRON ================= */

        public static final TagKey<Block> ORES_IRON =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/iron"));
        public static final TagKey<Block> DEEPSLATE_ORES_IRON =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/iron/deepslate"));
        public static final TagKey<Block> STORAGE_IRON =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/iron"));

        /* ================= GOLD ================= */

        public static final TagKey<Block> ORES_GOLD =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/gold"));
        public static final TagKey<Block> DEEPSLATE_ORES_GOLD =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/gold/deepslate"));
        public static final TagKey<Block> STORAGE_GOLD =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/gold"));

        /* ================= COPPER ================= */

        public static final TagKey<Block> ORES_COPPER =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/copper"));
        public static final TagKey<Block> DEEPSLATE_ORES_COPPER =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/copper/deepslate"));
        public static final TagKey<Block> STORAGE_COPPER =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/copper"));

        /* ================= TIN ================= */

        public static final TagKey<Block> ORES_TIN =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/tin"));
        public static final TagKey<Block> DEEPSLATE_ORES_TIN =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/tin/deepslate"));
        public static final TagKey<Block> STORAGE_TIN =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/tin"));

        /* ================= TITANIUM ================= */

        public static final TagKey<Block> ORES_TITANIUM =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/titanium"));
        public static final TagKey<Block> DEEPSLATE_ORES_TITANIUM =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/titanium/deepslate"));
        public static final TagKey<Block> STORAGE_TITANIUM =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/titanium"));

        /* ================= LEAD ================= */

        public static final TagKey<Block> ORES_LEAD =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/lead"));
        public static final TagKey<Block> DEEPSLATE_ORES_LEAD =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/lead/deepslate"));
        public static final TagKey<Block> STORAGE_LEAD =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/lead"));

        /* ================= SILVER ================= */

        public static final TagKey<Block> ORES_SILVER =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/silver"));
        public static final TagKey<Block> DEEPSLATE_ORES_SILVER =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/silver/deepslate"));
        public static final TagKey<Block> STORAGE_SILVER =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/silver"));

        /* ================= NICKEL ================= */

        public static final TagKey<Block> ORES_NICKEL =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/nickel"));
        public static final TagKey<Block> DEEPSLATE_ORES_NICKEL =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/nickel/deepslate"));
        public static final TagKey<Block> STORAGE_NICKEL =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/nickel"));

        /* ================= ZINC ================= */

        public static final TagKey<Block> ORES_ZINC =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/zinc"));
        public static final TagKey<Block> DEEPSLATE_ORES_ZINC =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/zinc/deepslate"));
        public static final TagKey<Block> STORAGE_ZINC =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/zinc"));

        /* ================= ALUMINUM ================= */

        public static final TagKey<Block> ORES_ALUMINUM =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/aluminum"));
        public static final TagKey<Block> DEEPSLATE_ORES_ALUMINUM =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/aluminum/deepslate"));
        public static final TagKey<Block> STORAGE_ALUMINUM =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/aluminum"));

        /* ================= URANIUM ================= */

        public static final TagKey<Block> ORES_URANIUM =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/uranium"));
        public static final TagKey<Block> DEEPSLATE_ORES_URANIUM =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/uranium/deepslate"));
        public static final TagKey<Block> STORAGE_URANIUM =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/uranium"));

        /* ================= OSMIUM ================= */

        public static final TagKey<Block> ORES_OSMIUM =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/osmium"));
        public static final TagKey<Block> DEEPSLATE_ORES_OSMIUM =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/osmium/deepslate"));
        public static final TagKey<Block> STORAGE_OSMIUM =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/osmium"));

        /* ================= TUNGSTEN ================= */

        public static final TagKey<Block> ORES_TUNGSTEN =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/tungsten"));
        public static final TagKey<Block> DEEPSLATE_ORES_TUNGSTEN =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/tungsten/deepslate"));
        public static final TagKey<Block> STORAGE_TUNGSTEN =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/tungsten"));

        /* ================= COBALT ================= */

        public static final TagKey<Block> ORES_COBALT =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/cobalt"));
        public static final TagKey<Block> DEEPSLATE_ORES_COBALT =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/cobalt/deepslate"));
        public static final TagKey<Block> STORAGE_COBALT =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/cobalt"));

        /* ================= STEEL ================= */

        public static final TagKey<Block> STORAGE_STEEL =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/steel"));

        /* ================= BRONZE ================= */

        public static final TagKey<Block> STORAGE_BRONZE =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/bronze"));

        /* ================= BRASS ================= */

        public static final TagKey<Block> STORAGE_BRASS =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/brass"));

        /* ================= ELECTRUM ================= */

        public static final TagKey<Block> STORAGE_ELECTRUM =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/electrum"));

        /* ================= INVAR ================= */

        public static final TagKey<Block> STORAGE_INVAR =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/invar"));

        /* ================= CONSTANTAN ================= */

        public static final TagKey<Block> STORAGE_CONSTANTAN =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/constantan"));

        /* ================= CHROMIUM ================= */

        public static final TagKey<Block> ORES_CHROMIUM =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/chromium"));
        public static final TagKey<Block> DEEPSLATE_ORES_CHROMIUM =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores/chromium/deepslate"));
        public static final TagKey<Block> STORAGE_CHROMIUM =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/chromium"));

        /* ================= TITA-CHROME ================= */

        public static final TagKey<Block> STORAGE_BLOCKS_TITA_CHROME =
                TagKey.create(Registries.BLOCK,
                        ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/tita_chrome"));

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(
                    Succsessentials_extended.MOD_ID, name));
        }
    }

    public static class Structures {

    }
}
