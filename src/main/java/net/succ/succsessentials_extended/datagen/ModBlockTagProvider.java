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
                .add(ModBlocks.CHROMIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_CHROMIUM_ORE.get())
                .add(ModBlocks.TITANIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_TITANIUM_ORE.get())
                .add(ModBlocks.ALLOY_FORGER.get())
                .add(ModBlocks.PANEL_BLOCK.get())
                .add(ModBlocks.INFUSER.get())
                .add(ModBlocks.CHROMIUM_BLOCK.get())
                .add(ModBlocks.TITANIUM_BLOCK.get())
                .add(ModBlocks.STEEL_BLOCK.get())
                .add(ModBlocks.TITA_CHROME_BLOCK.get())
                .add(ModBlocks.RAW_CHROMIUM_BLOCK.get())
                .add(ModBlocks.RAW_TITANIUM_BLOCK.get())
                .add(ModBlocks.ELECTRIC_FURNACE.get())
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL)
                .addTag(BlockTags.NEEDS_IRON_TOOL);


        tag(ModTags.Blocks.PAXEL_MINEABLE)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(BlockTags.MINEABLE_WITH_AXE)
                .addTag(BlockTags.MINEABLE_WITH_SHOVEL);

        /* ===================================================== */
        /*                  TOOL REQUIREMENTS                    */
        /* ===================================================== */

        // Intentionally referencing addon/base mod tag — DO NOT TOUCH
        tag(net.succ.succsmod.util.ModTags.Blocks.NEEDS_ATHERIUM_TOOL)
                .add(ModBlocks.CHROMIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_CHROMIUM_ORE.get())
                .add(ModBlocks.TITANIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_TITANIUM_ORE.get());

        // Your mod’s custom chromium tier
        tag(ModTags.Blocks.NEEDS_CHROMIUM_TOOL);

        tag(BlockTags.NEEDS_DIAMOND_TOOL);
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.TIN_ORE.get())
                .add(ModBlocks.DEEPSLATE_TIN_ORE.get());

        tag(Tags.Blocks.NEEDS_NETHERITE_TOOL);

        /* ===================================================== */
        /*              INCORRECT TOOL ENFORCEMENT               */
        /* ===================================================== */

        tag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL);

        tag(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);

        tag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);

        tag(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);

        tag(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);

        tag(BlockTags.INCORRECT_FOR_NETHERITE_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_CHROMIUM_TOOL);

        /* ===================================================== */
        /*                   ROOT C: BLOCK TAGS                  */
        /* ===================================================== */

        tag(ModTags.Blocks.ORES)
                .add(ModBlocks.CHROMIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_CHROMIUM_ORE.get())
                .add(ModBlocks.TITANIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_TITANIUM_ORE.get())
                .add(ModBlocks.TIN_ORE.get())
                .add(ModBlocks.DEEPSLATE_TIN_ORE.get());

        tag(ModTags.Blocks.STORAGE_BLOCKS)
                .add(ModBlocks.CHROMIUM_BLOCK.get())
                .add(ModBlocks.TITANIUM_BLOCK.get())
                .add(ModBlocks.STEEL_BLOCK.get())
                .add(ModBlocks.TIN_BLOCK.get())
                .add(ModBlocks.TITA_CHROME_BLOCK.get());

        /* ===================================================== */
        /*              MATERIAL-SPECIFIC ORE TAGS               */
        /* ===================================================== */

        tag(ModTags.Blocks.ORES_CHROMIUM)
                .add(ModBlocks.CHROMIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_CHROMIUM_ORE.get());


        tag(ModTags.Blocks.ORES_IRON);
        tag(ModTags.Blocks.ORES_GOLD);
        tag(ModTags.Blocks.ORES_COPPER);
        tag(ModTags.Blocks.ORES_TIN)
                .add(ModBlocks.TIN_ORE.get())
                .add(ModBlocks.DEEPSLATE_TIN_ORE.get());
        tag(ModTags.Blocks.ORES_LEAD);
        tag(ModTags.Blocks.ORES_SILVER);
        tag(ModTags.Blocks.ORES_NICKEL);
        tag(ModTags.Blocks.ORES_ZINC);
        tag(ModTags.Blocks.ORES_ALUMINUM);
        tag(ModTags.Blocks.ORES_URANIUM);
        tag(ModTags.Blocks.ORES_OSMIUM);
        tag(ModTags.Blocks.ORES_CHROMIUM);

        /* ===================================================== */
        /*           MATERIAL-SPECIFIC STORAGE BLOCK TAGS         */
        /* ===================================================== */

        tag(ModTags.Blocks.STORAGE_IRON);
        tag(ModTags.Blocks.STORAGE_GOLD);
        tag(ModTags.Blocks.STORAGE_COPPER);
        tag(ModTags.Blocks.STORAGE_TIN).add(ModBlocks.TIN_BLOCK.get());
        tag(ModTags.Blocks.STORAGE_TITANIUM).add(ModBlocks.TITANIUM_BLOCK.get());
        tag(ModTags.Blocks.STORAGE_LEAD);
        tag(ModTags.Blocks.STORAGE_SILVER);
        tag(ModTags.Blocks.STORAGE_NICKEL);
        tag(ModTags.Blocks.STORAGE_ZINC);
        tag(ModTags.Blocks.STORAGE_ALUMINUM);
        tag(ModTags.Blocks.STORAGE_URANIUM);
        tag(ModTags.Blocks.STORAGE_OSMIUM);
        tag(ModTags.Blocks.STORAGE_STEEL).add(ModBlocks.STEEL_BLOCK.get());
        tag(ModTags.Blocks.STORAGE_BRONZE).add(ModBlocks.BRONZE_BLOCK.get());
        tag(ModTags.Blocks.STORAGE_BRASS);
        tag(ModTags.Blocks.STORAGE_ELECTRUM);
        tag(ModTags.Blocks.STORAGE_INVAR);
        tag(ModTags.Blocks.STORAGE_CONSTANTAN);
        tag(ModTags.Blocks.STORAGE_CHROMIUM).add(ModBlocks.CHROMIUM_BLOCK.get());
        tag(ModTags.Blocks.STORAGE_BLOCKS_TITA_CHROME).add(ModBlocks.TITA_CHROME_BLOCK.get());

        /* ===================================================== */
        /*          VANILLA TAGS — INITIALIZED EMPTY              */
        /* ===================================================== */

        tag(BlockTags.LOGS);
        tag(BlockTags.LOGS_THAT_BURN);
        tag(BlockTags.LOGS);
        tag(BlockTags.WOODEN_BUTTONS);
        tag(BlockTags.WOODEN_DOORS);
        tag(BlockTags.WOODEN_SLABS);
        tag(BlockTags.WOODEN_STAIRS);
        tag(BlockTags.WOODEN_TRAPDOORS);
        tag(BlockTags.WOODEN_PRESSURE_PLATES);
        tag(BlockTags.WOODEN_FENCES);
        tag(BlockTags.FENCES);
        tag(BlockTags.FENCE_GATES);
        tag(BlockTags.SAPLINGS);
        tag(BlockTags.LEAVES);
        tag(BlockTags.CLIMBABLE);
        tag(BlockTags.MINEABLE_WITH_SHOVEL);
        tag(BlockTags.MINEABLE_WITH_AXE);
        tag(BlockTags.MINEABLE_WITH_HOE);
        tag(BlockTags.SWORD_EFFICIENT);
        tag(BlockTags.ENDERMAN_HOLDABLE);
        tag(BlockTags.REPLACEABLE_BY_TREES);
    }
}
