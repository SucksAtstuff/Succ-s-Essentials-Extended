package net.succ.succsessentials_extended.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?,?>> CHROMIUM_ORE_KEY = registerKey("chromium_ore_key");



    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        // Define RuleTests for blocks that can be replaced by ores
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplacables = new BlockMatchTest(Blocks.END_STONE);
        RuleTest packedIceReplaceables = new BlockMatchTest(Blocks.PACKED_ICE);

        // Define target block states for Chromium ores
        List<OreConfiguration.TargetBlockState> overworldChromiumOres = List.of(
                OreConfiguration.target(endReplacables, ModBlocks.CHROMIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(endReplacables, ModBlocks.DEEPSLATE_CHROMIUM_ORE.get().defaultBlockState())
        );

        // Register Chromium ore configured feature
        register(context, CHROMIUM_ORE_KEY, Feature.ORE, new OreConfiguration(overworldChromiumOres, 10));


    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Succsessentials_extended.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register (BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                           ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
