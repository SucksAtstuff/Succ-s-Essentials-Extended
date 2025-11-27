package net.succ.succsessentials_extended.worldgen.biome.region;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

import java.util.function.Consumer;

/**
 * Custom Nether region for TerraBlender integration.
 * This class injects the Crimson Depths biome into the Nether generation system
 * in a way that mimics vanilla Crimson Forest behavior while allowing both to coexist.
 */
public class NetherRegion extends Region {

    public NetherRegion(ResourceLocation name, int weight) {
        // Define this as a Nether-type TerraBlender region with a given priority weight
        super(name, RegionType.NETHER, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        // Create an overlay builder to inject our custom biome without replacing others
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();

        // Register everything
        builder.build().forEach(mapper);
    }
}
