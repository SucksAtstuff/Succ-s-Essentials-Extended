package net.succ.succsessentials_extended.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.block.ModBlocks;
import net.succ.succsessentials_extended.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Succsessentials_extended.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        /* ===================================================== */
        /*                     MINEABLE TAGS                     */
        /* ===================================================== */

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                // Chromium
                .add(ModBlocks.CHROMIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_CHROMIUM_ORE.get())
                .add(ModBlocks.CHROMIUM_BLOCK.get())
                .add(ModBlocks.RAW_CHROMIUM_BLOCK.get())

                // Titanium
                .add(ModBlocks.TITANIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_TITANIUM_ORE.get())
                .add(ModBlocks.TITANIUM_BLOCK.get())
                .add(ModBlocks.RAW_TITANIUM_BLOCK.get())

                // Tin
                .add(ModBlocks.TIN_ORE.get())
                .add(ModBlocks.DEEPSLATE_TIN_ORE.get())
                .add(ModBlocks.TIN_BLOCK.get())

                // Tungsten
                .add(ModBlocks.TUNGSTEN_ORE.get())
                .add(ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get())
                .add(ModBlocks.TUNGSTEN_BLOCK.get())
                .add(ModBlocks.RAW_TUNGSTEN_BLOCK.get())

                // Cobalt
                .add(ModBlocks.COBALT_ORE.get())
                .add(ModBlocks.DEEPSLATE_COBALT_ORE.get())
                .add(ModBlocks.COBALT_BLOCK.get())
                .add(ModBlocks.RAW_COBALT_BLOCK.get())

                // Osmium
                .add(ModBlocks.OSMIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_OSMIUM_ORE.get())
                .add(ModBlocks.OSMIUM_BLOCK.get())
                .add(ModBlocks.RAW_OSMIUM_BLOCK.get())

                // Zinc
                .add(ModBlocks.ZINC_ORE.get())
                .add(ModBlocks.DEEPSLATE_ZINC_ORE.get())
                .add(ModBlocks.ZINC_BLOCK.get())
                .add(ModBlocks.RAW_ZINC_BLOCK.get())

                // Silver
                .add(ModBlocks.SILVER_ORE.get())
                .add(ModBlocks.DEEPSLATE_SILVER_ORE.get())
                .add(ModBlocks.SILVER_BLOCK.get())
                .add(ModBlocks.RAW_SILVER_BLOCK.get())

                // Nickel
                .add(ModBlocks.NICKEL_ORE.get())
                .add(ModBlocks.DEEPSLATE_NICKEL_ORE.get())
                .add(ModBlocks.NICKEL_BLOCK.get())
                .add(ModBlocks.RAW_NICKEL_BLOCK.get())

                // Alloys
                .add(ModBlocks.STEEL_BLOCK.get())
                .add(ModBlocks.BRONZE_BLOCK.get())
                .add(ModBlocks.TITA_CHROME_BLOCK.get())

                // Machines
                .add(ModBlocks.ALLOY_FORGER.get())
                .add(ModBlocks.PANEL_BLOCK.get())
                .add(ModBlocks.INFUSER.get())
                .add(ModBlocks.ELECTRIC_FURNACE.get())

                // Tool tiers
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL)
                .addTag(BlockTags.NEEDS_IRON_TOOL);

        tag(ModTags.Blocks.PAXEL_MINEABLE)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(BlockTags.MINEABLE_WITH_AXE)
                .addTag(BlockTags.MINEABLE_WITH_SHOVEL);

        /* ===================================================== */
        /*                  TOOL REQUIREMENTS                    */
        /* ===================================================== */
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.BRONZE_BLOCK.get())
                .add(ModBlocks.BRASS_BLOCK.get())
                .add(ModBlocks.TIN_ORE.get())
                .add(ModBlocks.DEEPSLATE_TIN_ORE.get())
                .add(ModBlocks.ZINC_ORE.get())
                .add(ModBlocks.DEEPSLATE_ZINC_ORE.get())
                .add(ModBlocks.SILVER_ORE.get())
                .add(ModBlocks.DEEPSLATE_SILVER_ORE.get())
                .add(ModBlocks.NICKEL_ORE.get())
                .add(ModBlocks.DEEPSLATE_NICKEL_ORE.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.STEEL_BLOCK.get())
                .add(ModBlocks.INVAR_BLOCK.get())
                .add(ModBlocks.CONSTANTAN_BLOCK.get())
                .add(ModBlocks.ELECTRUM_BLOCK.get())
                .add(ModBlocks.COBALT_ORE.get())
                .add(ModBlocks.DEEPSLATE_COBALT_ORE.get())
                .add(ModBlocks.OSMIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_OSMIUM_ORE.get())
                .add(ModBlocks.TITANIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_TITANIUM_ORE.get());

        tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.TUNGSTEN_ORE.get())
                .add(ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get());

        tag(net.succ.succsmod.util.ModTags.Blocks.NEEDS_ATHERIUM_TOOL)
                .add(ModBlocks.CHROMIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_CHROMIUM_ORE.get());

        tag(ModTags.Blocks.NEEDS_CHROMIUM_TOOL)
                .add(ModBlocks.TITA_CHROME_BLOCK.get());


        /* ===================================================== */
        /*              INCORRECT TOOL ENFORCEMENT               */
        /* ===================================================== */

        // Diamond tools cannot mine Netherite-tier blocks
        tag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .addTag(net.succ.succsmod.util.ModTags.Blocks.NEEDS_ATHERIUM_TOOL);

        // Iron tools cannot mine Diamond+ blocks
        tag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL)
                .addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .addTag(net.succ.succsmod.util.ModTags.Blocks.NEEDS_ATHERIUM_TOOL);

        // Stone tools cannot mine Iron+ blocks
        tag(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .addTag(BlockTags.NEEDS_IRON_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL)
                .addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .addTag(net.succ.succsmod.util.ModTags.Blocks.NEEDS_ATHERIUM_TOOL);

        // Wooden tools cannot mine Stone+ blocks
        tag(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .addTag(BlockTags.NEEDS_IRON_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL)
                .addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .addTag(net.succ.succsmod.util.ModTags.Blocks.NEEDS_ATHERIUM_TOOL);

        // Gold tools are worse than Iron
        tag(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .addTag(BlockTags.NEEDS_IRON_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL)
                .addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .addTag(net.succ.succsmod.util.ModTags.Blocks.NEEDS_ATHERIUM_TOOL);

        // Netherite tools still cannot mine Atherium-gated blocks
        tag(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
                .addTag(net.succ.succsmod.util.ModTags.Blocks.NEEDS_ATHERIUM_TOOL);

        /* ===================================================== */
        /*                   ROOT C: BLOCK TAGS                  */
        /* ===================================================== */
        tag(ModTags.Blocks.ORES_CHROMIUM)
                .add(ModBlocks.CHROMIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_CHROMIUM_ORE.get());
        tag(ModTags.Blocks.ORES_TITANIUM)
                .add(ModBlocks.TITANIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_TITANIUM_ORE.get());
        tag(ModTags.Blocks.ORES_TIN)
                .add(ModBlocks.TIN_ORE.get())
                .add(ModBlocks.DEEPSLATE_TIN_ORE.get());
        tag(ModTags.Blocks.ORES_TUNGSTEN)
                .add(ModBlocks.TUNGSTEN_ORE.get())
                .add(ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get());
        tag(ModTags.Blocks.ORES_COBALT)
                .add(ModBlocks.COBALT_ORE.get())
                .add(ModBlocks.DEEPSLATE_COBALT_ORE.get());
        tag(ModTags.Blocks.ORES_OSMIUM)
                .add(ModBlocks.OSMIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_OSMIUM_ORE.get());
        tag(ModTags.Blocks.ORES_ZINC)
                .add(ModBlocks.ZINC_ORE.get())
                .add(ModBlocks.DEEPSLATE_ZINC_ORE.get());
        tag(ModTags.Blocks.ORES_SILVER)
                .add(ModBlocks.SILVER_ORE.get())
                .add(ModBlocks.DEEPSLATE_SILVER_ORE.get());
        tag(ModTags.Blocks.ORES_NICKEL)
                .add(ModBlocks.NICKEL_ORE.get())
                .add(ModBlocks.DEEPSLATE_NICKEL_ORE.get());

        tag(ModTags.Blocks.ORES_ALUMINUM);
        tag(ModTags.Blocks.ORES_URANIUM);

        tag(ModTags.Blocks.STORAGE_CHROMIUM).add(ModBlocks.CHROMIUM_BLOCK.get());
        tag(ModTags.Blocks.STORAGE_TITANIUM).add(ModBlocks.TITANIUM_BLOCK.get());
        tag(ModTags.Blocks.STORAGE_TIN).add(ModBlocks.TIN_BLOCK.get());
        tag(ModTags.Blocks.STORAGE_TUNGSTEN).add(ModBlocks.TUNGSTEN_BLOCK.get());
        tag(ModTags.Blocks.STORAGE_COBALT).add(ModBlocks.COBALT_BLOCK.get());
        tag(ModTags.Blocks.STORAGE_OSMIUM).add(ModBlocks.OSMIUM_BLOCK.get());
        tag(ModTags.Blocks.STORAGE_ZINC).add(ModBlocks.ZINC_BLOCK.get());
        tag(ModTags.Blocks.STORAGE_ALUMINUM);
        tag(ModTags.Blocks.STORAGE_URANIUM);
        tag(ModTags.Blocks.STORAGE_STEEL).add(ModBlocks.STEEL_BLOCK.get());
        tag(ModTags.Blocks.STORAGE_BRONZE).add(ModBlocks.BRONZE_BLOCK.get());
        tag(ModTags.Blocks.STORAGE_BRASS).add(ModBlocks.BRASS_BLOCK.get());
        tag(ModTags.Blocks.STORAGE_ELECTRUM).add(ModBlocks.ELECTRUM_BLOCK.get());
        tag(ModTags.Blocks.STORAGE_INVAR).add(ModBlocks.INVAR_BLOCK.get());
        tag(ModTags.Blocks.STORAGE_CONSTANTAN).add(ModBlocks.CONSTANTAN_BLOCK.get());
        tag(ModTags.Blocks.STORAGE_BLOCKS_TITA_CHROME).add(ModBlocks.TITA_CHROME_BLOCK.get());
        tag(ModTags.Blocks.STORAGE_LEAD);
        tag(ModTags.Blocks.STORAGE_SILVER).add(ModBlocks.SILVER_BLOCK.get());
        tag(ModTags.Blocks.STORAGE_NICKEL).add(ModBlocks.NICKEL_BLOCK.get());

        tag(ModTags.Blocks.STORAGE_RAW_CHROMIUM).add(ModBlocks.RAW_CHROMIUM_BLOCK.get());

        tag(ModTags.Blocks.STORAGE_RAW_TITANIUM).add(ModBlocks.RAW_TITANIUM_BLOCK.get());

        tag(ModTags.Blocks.STORAGE_RAW_TIN).add(ModBlocks.RAW_TIN_BLOCK.get());

        tag(ModTags.Blocks.STORAGE_RAW_TUNGSTEN).add(ModBlocks.RAW_TUNGSTEN_BLOCK.get());

        tag(ModTags.Blocks.STORAGE_RAW_COBALT).add(ModBlocks.RAW_COBALT_BLOCK.get());

        tag(ModTags.Blocks.STORAGE_RAW_OSMIUM).add(ModBlocks.RAW_OSMIUM_BLOCK.get());

        tag(ModTags.Blocks.STORAGE_RAW_ZINC).add(ModBlocks.RAW_ZINC_BLOCK.get());

        tag(ModTags.Blocks.STORAGE_RAW_SILVER).add(ModBlocks.RAW_SILVER_BLOCK.get());

        tag(ModTags.Blocks.STORAGE_RAW_NICKEL).add(ModBlocks.RAW_NICKEL_BLOCK.get());



    }

}
