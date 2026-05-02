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
import net.succ.succsessentials_extended.ModConfig;
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
                ModOrePlacement.commonOrePlacement(ModConfig.chromiumVeinsPerChunk,
                        HeightRangePlacement.triangle(
                                VerticalAnchor.absolute(ModConfig.chromiumMinY),
                                VerticalAnchor.absolute(ModConfig.chromiumMaxY))));

        // ---------- TITANIUM ----------
        register(context, TITANIUM_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.TITANIUM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(ModConfig.titaniumVeinsPerChunk,
                        HeightRangePlacement.triangle(
                                VerticalAnchor.absolute(ModConfig.titaniumMinY),
                                VerticalAnchor.absolute(ModConfig.titaniumMaxY))));

        // ---------- TIN ----------
        register(context, TIN_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.TIN_ORE_KEY),
                ModOrePlacement.commonOrePlacement(ModConfig.tinVeinsPerChunk,
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(ModConfig.tinMinY),
                                VerticalAnchor.absolute(ModConfig.tinMaxY))));

        // ---------- TUNGSTEN ----------
        register(context, TUNGSTEN_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.TUNGSTEN_ORE_KEY),
                ModOrePlacement.commonOrePlacement(ModConfig.tungstenVeinsPerChunk,
                        HeightRangePlacement.triangle(
                                VerticalAnchor.absolute(ModConfig.tungstenMinY),
                                VerticalAnchor.absolute(ModConfig.tungstenMaxY))));

        // ---------- COBALT ----------
        register(context, COBALT_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.COBALT_ORE_KEY),
                ModOrePlacement.commonOrePlacement(ModConfig.cobaltVeinsPerChunk,
                        HeightRangePlacement.triangle(
                                VerticalAnchor.absolute(ModConfig.cobaltMinY),
                                VerticalAnchor.absolute(ModConfig.cobaltMaxY))));

        // ---------- OSMIUM ----------
        register(context, OSMIUM_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.OSMIUM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(ModConfig.osmiumVeinsPerChunk,
                        HeightRangePlacement.triangle(
                                VerticalAnchor.absolute(ModConfig.osmiumMinY),
                                VerticalAnchor.absolute(ModConfig.osmiumMaxY))));

        // ---------- ZINC ----------
        register(context, ZINC_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.ZINC_ORE_KEY),
                ModOrePlacement.commonOrePlacement(ModConfig.zincVeinsPerChunk,
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(ModConfig.zincMinY),
                                VerticalAnchor.absolute(ModConfig.zincMaxY))));

        // ---------- SILVER ----------
        register(context, SILVER_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.SILVER_ORE_KEY),
                ModOrePlacement.commonOrePlacement(ModConfig.silverVeinsPerChunk,
                        HeightRangePlacement.triangle(
                                VerticalAnchor.absolute(ModConfig.silverMinY),
                                VerticalAnchor.absolute(ModConfig.silverMaxY))));

        // ---------- NICKEL ----------
        register(context, NICKEL_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.NICKEL_ORE_KEY),
                ModOrePlacement.commonOrePlacement(ModConfig.nickelVeinsPerChunk,
                        HeightRangePlacement.triangle(
                                VerticalAnchor.absolute(ModConfig.nickelMinY),
                                VerticalAnchor.absolute(ModConfig.nickelMaxY))));

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
