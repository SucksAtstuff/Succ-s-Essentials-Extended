package net.succ.succsessentials_extended.worldgen;

import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.succ.succsessentials_extended.Succsessentials_extended;

import java.util.List;

public class ModPlacedFeatures {

    /* =====================================================================
     *                           ORES
     * ===================================================================== */

    public static final ResourceKey<PlacedFeature> CHROMIUM_ORE_PLACED_KEY =
            registerKey("chromium_ore_placed");
    public static final ResourceKey<PlacedFeature> TITANIUM_ORE_PLACED_KEY =
            registerKey("titanium_ore_placed");
    public static final ResourceKey<PlacedFeature> TIN_ORE_PLACED_KEY =
            registerKey("tin_ore_placed");
    public static final ResourceKey<PlacedFeature> TUNGSTEN_ORE_PLACED_KEY =
            registerKey("tungsten_ore_placed");
    public static final ResourceKey<PlacedFeature> COBALT_ORE_PLACED_KEY =
            registerKey("cobalt_ore_placed");
    public static final ResourceKey<PlacedFeature> OSMIUM_ORE_PLACED_KEY =
            registerKey("osmium_ore_placed");
    public static final ResourceKey<PlacedFeature> ZINC_ORE_PLACED_KEY =
            registerKey("zinc_ore_placed");
    public static final ResourceKey<PlacedFeature> SILVER_ORE_PLACED_KEY =
            registerKey("silver_ore_placed");
    public static final ResourceKey<PlacedFeature> NICKEL_ORE_PLACED_KEY =
            registerKey("nickel_ore_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        /* =================================================================
         *                           STONE PLACEMENT
         * ================================================================= */

        // ---------- CHROMIUM ----------
        register(context, CHROMIUM_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.CHROMIUM_ORE_KEY),
                ModOrePlacement.rareOrePlacement(1,
                        HeightRangePlacement.triangle(
                                VerticalAnchor.absolute(-48),
                                VerticalAnchor.absolute(-8))));


        // ---------- TITANIUM ----------
        register(context, TITANIUM_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.TITANIUM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(5,
                        HeightRangePlacement.triangle(
                                VerticalAnchor.absolute(-48),
                                VerticalAnchor.absolute(16))));

        // ---------- TIN ----------
        register(context, TIN_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.TIN_ORE_KEY),
                ModOrePlacement.commonOrePlacement(12,
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(0),
                                VerticalAnchor.absolute(96))));

        // ---------- TUNGSTEN ----------
        register(context, TUNGSTEN_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.TUNGSTEN_ORE_KEY),
                ModOrePlacement.rareOrePlacement(3,
                        HeightRangePlacement.triangle(
                                VerticalAnchor.absolute(-64),
                                VerticalAnchor.absolute(-16))));

        // ---------- COBALT ----------
        register(context, COBALT_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.COBALT_ORE_KEY),
                ModOrePlacement.commonOrePlacement(5,
                        HeightRangePlacement.triangle(
                                VerticalAnchor.absolute(-32),
                                VerticalAnchor.absolute(32))));

        // ---------- OSMIUM ----------
        register(context, OSMIUM_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.OSMIUM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(4,
                        HeightRangePlacement.triangle(
                                VerticalAnchor.absolute(-40),
                                VerticalAnchor.absolute(24))));

        // ---------- ZINC ----------
        register(context, ZINC_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.ZINC_ORE_KEY),
                ModOrePlacement.commonOrePlacement(10,
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(16),
                                VerticalAnchor.absolute(112))));

        // ---------- SILVER ----------
        register(context, SILVER_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.SILVER_ORE_KEY),
                ModOrePlacement.commonOrePlacement(6,
                        HeightRangePlacement.triangle(
                                VerticalAnchor.absolute(-24),
                                VerticalAnchor.absolute(40))));

        // ---------- NICKEL ----------
        register(context, NICKEL_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.NICKEL_ORE_KEY),
                ModOrePlacement.commonOrePlacement(6,
                        HeightRangePlacement.triangle(
                                VerticalAnchor.absolute(-16),
                                VerticalAnchor.absolute(48))));

    }

    /* =====================================================================
     *                           HELPERS
     * ===================================================================== */

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(
                Registries.PLACED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(Succsessentials_extended.MOD_ID, name)
        );
    }

    private static void register(
            BootstrapContext<PlacedFeature> context,
            ResourceKey<PlacedFeature> key,
            Holder<ConfiguredFeature<?, ?>> configuration,
            List<PlacementModifier> modifiers
    ) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
