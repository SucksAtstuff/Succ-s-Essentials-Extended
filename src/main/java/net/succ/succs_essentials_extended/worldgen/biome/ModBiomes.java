package net.succ.succs_essentials_extended.worldgen.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.succ.succs_essentials_extended.Succs_essentials_extended;
import net.succ.succs_essentials_extended.worldgen.biome.region.OverworldRegion;
import terrablender.api.Regions;

public class ModBiomes {
    public static void registerBiomes() {
    }

    public static void bootstrap(BootstrapContext<Biome> context) {
        var carver = context.lookup(Registries.CONFIGURED_CARVER);
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
    }

    private static void register(BootstrapContext<Biome> context, ResourceKey<Biome> key, Biome biome) {
        context.register(key, biome);
    }

    private static ResourceKey<Biome> registerBiomeKey(String name) {
        return ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(Succs_essentials_extended.MOD_ID, name));
    }
}
