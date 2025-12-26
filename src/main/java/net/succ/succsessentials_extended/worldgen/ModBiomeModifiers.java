package net.succ.succsessentials_extended.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.succ.succsessentials_extended.Succsessentials_extended;

public class ModBiomeModifiers {

    // ---------- OVERWORLD ORES ----------
    public static final ResourceKey<BiomeModifier> ADD_CHROMIUM_ORE =
            registerKey("add_chromium_ore");
    public static final ResourceKey<BiomeModifier> ADD_TITANIUM_ORE =
            registerKey("add_titanium_ore");
    public static final ResourceKey<BiomeModifier> ADD_TIN_ORE =
            registerKey("add_tin_ore");
    public static final ResourceKey<BiomeModifier> ADD_TUNGSTEN_ORE =
            registerKey("add_tungsten_ore");
    public static final ResourceKey<BiomeModifier> ADD_COBALT_ORE =
            registerKey("add_cobalt_ore");
    public static final ResourceKey<BiomeModifier> ADD_OSMIUM_ORE =
            registerKey("add_osmium_ore");
    public static final ResourceKey<BiomeModifier> ADD_ZINC_ORE =
            registerKey("add_zinc_ore");
    public static final ResourceKey<BiomeModifier> ADD_SILVER_ORE =
            registerKey("add_silver_ore");
    public static final ResourceKey<BiomeModifier> ADD_NICKEL_ORE =
            registerKey("add_nickel_ore");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        // ---------- CHROMIUM ----------
        context.register(ADD_CHROMIUM_ORE,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                        HolderSet.direct(
                                placedFeatures.getOrThrow(
                                        ModPlacedFeatures.CHROMIUM_ORE_PLACED_KEY)),
                        GenerationStep.Decoration.UNDERGROUND_ORES
                )
        );

        // ---------- TITANIUM ----------
        context.register(ADD_TITANIUM_ORE,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                        HolderSet.direct(
                                placedFeatures.getOrThrow(
                                        ModPlacedFeatures.TITANIUM_ORE_PLACED_KEY)),
                        GenerationStep.Decoration.UNDERGROUND_ORES
                )
        );

        // ---------- TIN ----------
        context.register(ADD_TIN_ORE,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                        HolderSet.direct(
                                placedFeatures.getOrThrow(
                                        ModPlacedFeatures.TIN_ORE_PLACED_KEY)),
                        GenerationStep.Decoration.UNDERGROUND_ORES
                )
        );

        // ---------- TUNGSTEN ----------
        context.register(ADD_TUNGSTEN_ORE,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                        HolderSet.direct(
                                placedFeatures.getOrThrow(
                                        ModPlacedFeatures.TUNGSTEN_ORE_PLACED_KEY)),
                        GenerationStep.Decoration.UNDERGROUND_ORES
                )
        );

        // ---------- COBALT ----------
        context.register(ADD_COBALT_ORE,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                        HolderSet.direct(
                                placedFeatures.getOrThrow(
                                        ModPlacedFeatures.COBALT_ORE_PLACED_KEY)),
                        GenerationStep.Decoration.UNDERGROUND_ORES
                )
        );

        // ---------- OSMIUM ----------
        context.register(ADD_OSMIUM_ORE,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                        HolderSet.direct(
                                placedFeatures.getOrThrow(
                                        ModPlacedFeatures.OSMIUM_ORE_PLACED_KEY)),
                        GenerationStep.Decoration.UNDERGROUND_ORES
                )
        );

        // ---------- ZINC ----------
        context.register(ADD_ZINC_ORE,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                        HolderSet.direct(
                                placedFeatures.getOrThrow(
                                        ModPlacedFeatures.ZINC_ORE_PLACED_KEY)),
                        GenerationStep.Decoration.UNDERGROUND_ORES
                )
        );

        // ---------- SILVER ----------
        context.register(ADD_SILVER_ORE,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                        HolderSet.direct(
                                placedFeatures.getOrThrow(
                                        ModPlacedFeatures.SILVER_ORE_PLACED_KEY)),
                        GenerationStep.Decoration.UNDERGROUND_ORES
                )
        );

        // ---------- NICKEL ----------
        context.register(ADD_NICKEL_ORE,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                        HolderSet.direct(
                                placedFeatures.getOrThrow(
                                        ModPlacedFeatures.NICKEL_ORE_PLACED_KEY)),
                        GenerationStep.Decoration.UNDERGROUND_ORES
                )
        );
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(
                NeoForgeRegistries.Keys.BIOME_MODIFIERS,
                ResourceLocation.fromNamespaceAndPath(
                        Succsessentials_extended.MOD_ID,
                        name
                )
        );
    }
}
