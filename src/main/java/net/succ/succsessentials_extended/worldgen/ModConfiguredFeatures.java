package net.succ.succsessentials_extended.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {

    /* =====================================================================
     *                           STONE KEYS
     * ===================================================================== */

    public static final ResourceKey<ConfiguredFeature<?, ?>> CHROMIUM_ORE_KEY =
            registerKey("chromium_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> TITANIUM_ORE_KEY =
            registerKey("titanium_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> TIN_ORE_KEY =
            registerKey("tin_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> TUNGSTEN_ORE_KEY =
            registerKey("tungsten_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> COBALT_ORE_KEY =
            registerKey("cobalt_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OSMIUM_ORE_KEY =
            registerKey("osmium_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ZINC_ORE_KEY =
            registerKey("zinc_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_ORE_KEY =
            registerKey("silver_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> NICKEL_ORE_KEY =
            registerKey("nickel_ore");

    /* =====================================================================
     *                           BOOTSTRAP
     * ===================================================================== */

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        // ---------- RULE TESTS ----------
        RuleTest stoneReplaceables =
                new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);

        RuleTest deepslateReplaceables =
                new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        /* =================================================================
         *                           STONE TARGETS
         * ================================================================= */

        // ---------- CHROMIUM ----------
        register(context, CHROMIUM_ORE_KEY, Feature.ORE,
                new OreConfiguration(
                        List.of(
                                OreConfiguration.target(stoneReplaceables,
                                        ModBlocks.CHROMIUM_ORE.get().defaultBlockState()),
                                OreConfiguration.target(deepslateReplaceables,
                                        ModBlocks.DEEPSLATE_CHROMIUM_ORE.get().defaultBlockState())
                        ),
                        4
                )
        );

        // ---------- TITANIUM ----------
        register(context, TITANIUM_ORE_KEY, Feature.ORE,
                new OreConfiguration(
                        List.of(
                                OreConfiguration.target(stoneReplaceables,
                                        ModBlocks.TITANIUM_ORE.get().defaultBlockState()),
                                OreConfiguration.target(deepslateReplaceables,
                                        ModBlocks.DEEPSLATE_TITANIUM_ORE.get().defaultBlockState())
                        ),
                        7
                )
        );

        // ---------- TIN ----------
        register(context, TIN_ORE_KEY, Feature.ORE,
                new OreConfiguration(
                        List.of(
                                OreConfiguration.target(stoneReplaceables,
                                        ModBlocks.TIN_ORE.get().defaultBlockState()),
                                OreConfiguration.target(deepslateReplaceables,
                                        ModBlocks.DEEPSLATE_TIN_ORE.get().defaultBlockState())
                        ),
                        9
                )
        );

        // ---------- TUNGSTEN ----------
        register(context, TUNGSTEN_ORE_KEY, Feature.ORE,
                new OreConfiguration(
                        List.of(
                                OreConfiguration.target(stoneReplaceables,
                                        ModBlocks.TUNGSTEN_ORE.get().defaultBlockState()),
                                OreConfiguration.target(deepslateReplaceables,
                                        ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get().defaultBlockState())
                        ),
                        6
                )
        );

        // ---------- COBALT ----------
        register(context, COBALT_ORE_KEY, Feature.ORE,
                new OreConfiguration(
                        List.of(
                                OreConfiguration.target(stoneReplaceables,
                                        ModBlocks.COBALT_ORE.get().defaultBlockState()),
                                OreConfiguration.target(deepslateReplaceables,
                                        ModBlocks.DEEPSLATE_COBALT_ORE.get().defaultBlockState())
                        ),
                        7
                )
        );

        // ---------- OSMIUM ----------
        register(context, OSMIUM_ORE_KEY, Feature.ORE,
                new OreConfiguration(
                        List.of(
                                OreConfiguration.target(stoneReplaceables,
                                        ModBlocks.OSMIUM_ORE.get().defaultBlockState()),
                                OreConfiguration.target(deepslateReplaceables,
                                        ModBlocks.DEEPSLATE_OSMIUM_ORE.get().defaultBlockState())
                        ),
                        6
                )
        );

        // ---------- ZINC ----------
        register(context, ZINC_ORE_KEY, Feature.ORE,
                new OreConfiguration(
                        List.of(
                                OreConfiguration.target(stoneReplaceables,
                                        ModBlocks.ZINC_ORE.get().defaultBlockState()),
                                OreConfiguration.target(deepslateReplaceables,
                                        ModBlocks.DEEPSLATE_ZINC_ORE.get().defaultBlockState())
                        ),
                        10
                )
        );

        // ---------- SILVER ----------
        register(context, SILVER_ORE_KEY, Feature.ORE,
                new OreConfiguration(
                        List.of(
                                OreConfiguration.target(stoneReplaceables,
                                        ModBlocks.SILVER_ORE.get().defaultBlockState()),
                                OreConfiguration.target(deepslateReplaceables,
                                        ModBlocks.DEEPSLATE_SILVER_ORE.get().defaultBlockState())
                        ),
                        8
                )
        );

        // ---------- NICKEL ----------
        register(context, NICKEL_ORE_KEY, Feature.ORE,
                new OreConfiguration(
                        List.of(
                                OreConfiguration.target(stoneReplaceables,
                                        ModBlocks.NICKEL_ORE.get().defaultBlockState()),
                                OreConfiguration.target(deepslateReplaceables,
                                        ModBlocks.DEEPSLATE_NICKEL_ORE.get().defaultBlockState())
                        ),
                        8
                )
        );
    }

    /* =====================================================================
     *                           HELPERS
     * ===================================================================== */

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(
                Registries.CONFIGURED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(
                        Succsessentials_extended.MOD_ID,
                        name
                )
        );
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
            BootstrapContext<ConfiguredFeature<?, ?>> context,
            ResourceKey<ConfiguredFeature<?, ?>> key,
            F feature,
            FC configuration
    ) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
