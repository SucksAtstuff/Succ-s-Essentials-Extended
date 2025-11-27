package net.succ.succs_essentials_extended.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.succ.succs_essentials_extended.Succs_essentials_extended;
import net.succ.succs_essentials_extended.block.ModBlocks;
import net.succ.succs_essentials_extended.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Succs_essentials_extended.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Pickaxe mineable ores
        tag(BlockTags.MINEABLE_WITH_PICKAXE);

        tag(ModTags.Blocks.PAXEL_MINEABLE)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(BlockTags.MINEABLE_WITH_AXE)
                .addTag(BlockTags.MINEABLE_WITH_SHOVEL);

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL);

        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL);





        // Incorrect tool enforcement
        this.tag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL);

        this.tag(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);

        this.tag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);

        this.tag(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);

        this.tag(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);



        tag(BlockTags.INCORRECT_FOR_NETHERITE_TOOL);


        this.tag(BlockTags.LOGS_THAT_BURN);

        this.tag(BlockTags.LOGS);

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
