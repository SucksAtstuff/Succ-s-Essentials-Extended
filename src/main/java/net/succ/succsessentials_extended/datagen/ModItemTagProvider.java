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

        // Root storage blocks
        tag(ModTags.Items.STORAGE_BLOCKS)
                .addTag(ModTags.Items.STORAGE_CHROMIUM)
                .addTag(ModTags.Items.STORAGE_TITANIUM)
                .addTag(ModTags.Items.STORAGE_STEEL);

        // Chromium
        tag(ModTags.Items.STORAGE_CHROMIUM)
                .add(ModBlocks.CHROMIUM_BLOCK.get().asItem());

        // Titanium
        tag(ModTags.Items.STORAGE_TITANIUM)
                .add(ModBlocks.TITANIUM_BLOCK.get().asItem());

        // Steel
        tag(ModTags.Items.STORAGE_STEEL)
                .add(ModBlocks.STEEL_BLOCK.get().asItem());

        tag(ModTags.Items.STORAGE_IRON);
        tag(ModTags.Items.STORAGE_GOLD);
        tag(ModTags.Items.STORAGE_COPPER);
        tag(ModTags.Items.STORAGE_TIN);
        tag(ModTags.Items.STORAGE_LEAD);
        tag(ModTags.Items.STORAGE_SILVER);
        tag(ModTags.Items.STORAGE_NICKEL);
        tag(ModTags.Items.STORAGE_ZINC);
        tag(ModTags.Items.STORAGE_ALUMINUM);
        tag(ModTags.Items.STORAGE_URANIUM);
        tag(ModTags.Items.STORAGE_OSMIUM);
        tag(ModTags.Items.STORAGE_BRONZE);
        tag(ModTags.Items.STORAGE_BRASS);
        tag(ModTags.Items.STORAGE_ELECTRUM);
        tag(ModTags.Items.STORAGE_INVAR);
        tag(ModTags.Items.STORAGE_CONSTANTAN);

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
                .add(ModItems.TITA_CHROME_INGOT.get())
                .add(ModItems.STEEL_INGOT.get());

        tag(ModTags.Items.NUGGETS)
                .add(ModItems.CHROMIUM_NUGGET.get())
                .add(ModItems.TITANIUM_NUGGET.get())
                .add(ModItems.STEEL_NUGGET.get());

        tag(ModTags.Items.DUSTS)
                .add(ModItems.COAL_DUST.get())
                .add(ModItems.TITANIUM_DUST.get());

        tag(ModTags.Items.RAW_MATERIALS)
                .add(ModItems.RAW_CHROMIUM.get())
                .add(ModItems.RAW_TITANIUM.get());

        tag(ModTags.Items.ORES)
                .add(ModBlocks.CHROMIUM_ORE.get().asItem())
                .add(ModBlocks.DEEPSLATE_CHROMIUM_ORE.get().asItem())
                .add(ModBlocks.TITANIUM_ORE.get().asItem())
                .add(ModBlocks.DEEPSLATE_TITANIUM_ORE.get().asItem());

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
        tag(ModTags.Items.DUSTS_CHROMIUM);
        tag(ModTags.Items.PLATES_CHROMIUM);
        tag(ModTags.Items.RODS_CHROMIUM);
        tag(ModTags.Items.GEARS_CHROMIUM);
        tag(ModTags.Items.RAW_CHROMIUM).add(ModItems.RAW_CHROMIUM.get());
        tag(ModTags.Items.ORES_CHROMIUM)
                .add(ModBlocks.CHROMIUM_ORE.get().asItem())
                .add(ModBlocks.DEEPSLATE_CHROMIUM_ORE.get().asItem());



        // Titanium
        tag(ModTags.Items.INGOTS_TITANIUM)
                .add(ModItems.TITANIUM_INGOT.get());
        tag(ModTags.Items.NUGGETS_TITANIUM)
                .add(ModItems.TITANIUM_NUGGET.get());
        tag(ModTags.Items.DUSTS_TITANIUM)
                .add(ModItems.TITANIUM_DUST.get());
        tag(ModTags.Items.PLATES_TITANIUM);
        tag(ModTags.Items.RODS_TITANIUM);
        tag(ModTags.Items.GEARS_TITANIUM);
        tag(ModTags.Items.RAW_TITANIUM)
                .add(ModItems.RAW_TITANIUM.get());
        tag(ModTags.Items.ORES_TITANIUM)
                .add(ModBlocks.TITANIUM_ORE.get().asItem())
                .add(ModBlocks.DEEPSLATE_TITANIUM_ORE.get().asItem());


        // Tin
        tag(ModTags.Items.NUGGETS_TIN);
        tag(ModTags.Items.DUSTS_TIN);
        tag(ModTags.Items.PLATES_TIN);
        tag(ModTags.Items.RODS_TIN);
        tag(ModTags.Items.GEARS_TIN);
        tag(ModTags.Items.RAW_TIN);
        tag(ModTags.Items.ORES_TIN);

        // Lead
        tag(ModTags.Items.NUGGETS_LEAD);
        tag(ModTags.Items.DUSTS_LEAD);
        tag(ModTags.Items.PLATES_LEAD);
        tag(ModTags.Items.RODS_LEAD);
        tag(ModTags.Items.GEARS_LEAD);
        tag(ModTags.Items.RAW_LEAD);
        tag(ModTags.Items.ORES_LEAD);

        // Silver
        tag(ModTags.Items.NUGGETS_SILVER);
        tag(ModTags.Items.DUSTS_SILVER);
        tag(ModTags.Items.PLATES_SILVER);
        tag(ModTags.Items.RODS_SILVER);
        tag(ModTags.Items.GEARS_SILVER);
        tag(ModTags.Items.RAW_SILVER);
        tag(ModTags.Items.ORES_SILVER);

        // Nickel
        tag(ModTags.Items.NUGGETS_NICKEL);
        tag(ModTags.Items.DUSTS_NICKEL);
        tag(ModTags.Items.PLATES_NICKEL);
        tag(ModTags.Items.RODS_NICKEL);
        tag(ModTags.Items.GEARS_NICKEL);
        tag(ModTags.Items.RAW_NICKEL);
        tag(ModTags.Items.ORES_NICKEL);

        // Zinc
        tag(ModTags.Items.NUGGETS_ZINC);
        tag(ModTags.Items.DUSTS_ZINC);
        tag(ModTags.Items.PLATES_ZINC);
        tag(ModTags.Items.RODS_ZINC);
        tag(ModTags.Items.GEARS_ZINC);
        tag(ModTags.Items.RAW_ZINC);
        tag(ModTags.Items.ORES_ZINC);

        // Aluminum
        tag(ModTags.Items.NUGGETS_ALUMINUM);
        tag(ModTags.Items.DUSTS_ALUMINUM);
        tag(ModTags.Items.PLATES_ALUMINUM);
        tag(ModTags.Items.RODS_ALUMINUM);
        tag(ModTags.Items.GEARS_ALUMINUM);
        tag(ModTags.Items.RAW_ALUMINUM);
        tag(ModTags.Items.ORES_ALUMINUM);

        // Uranium
        tag(ModTags.Items.NUGGETS_URANIUM);
        tag(ModTags.Items.DUSTS_URANIUM);
        tag(ModTags.Items.PLATES_URANIUM);
        tag(ModTags.Items.RODS_URANIUM);
        tag(ModTags.Items.GEARS_URANIUM);
        tag(ModTags.Items.RAW_URANIUM);
        tag(ModTags.Items.ORES_URANIUM);

        // Osmium
        tag(ModTags.Items.NUGGETS_OSMIUM);
        tag(ModTags.Items.DUSTS_OSMIUM);
        tag(ModTags.Items.PLATES_OSMIUM);
        tag(ModTags.Items.RODS_OSMIUM);
        tag(ModTags.Items.GEARS_OSMIUM);
        tag(ModTags.Items.RAW_OSMIUM);
        tag(ModTags.Items.ORES_OSMIUM);

        // Steel
        tag(ModTags.Items.INGOTS_STEEL)
                .add(ModItems.STEEL_INGOT.get());

        tag(ModTags.Items.NUGGETS_STEEL)
                .add(ModItems.STEEL_NUGGET.get());

        tag(ModTags.Items.DUSTS_STEEL);
        tag(ModTags.Items.PLATES_STEEL);
        tag(ModTags.Items.RODS_STEEL);
        tag(ModTags.Items.GEARS_STEEL);


        // Coal
        tag(ModTags.Items.DUSTS_COAL)
                .add(ModItems.COAL_DUST.get());
    }
}
