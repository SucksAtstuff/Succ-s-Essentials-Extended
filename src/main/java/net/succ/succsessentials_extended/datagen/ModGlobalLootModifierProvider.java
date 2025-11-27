package net.succ.succsessentials_extended.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.succ.succsessentials_extended.Succsessentials_extended;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, Succsessentials_extended.MOD_ID);
    }

    // Define an array of all_hammers.json dungeon chest loot tables
    ResourceLocation[] dungeonChestLootTables = new ResourceLocation[] {
            ResourceLocation.withDefaultNamespace("chests/simple_dungeon"),
            ResourceLocation.withDefaultNamespace("chests/stronghold_corridor"),
            ResourceLocation.withDefaultNamespace("chests/nether_bridge"),
            ResourceLocation.withDefaultNamespace("chests/bastion_treasure"),
            ResourceLocation.withDefaultNamespace( "chests/end_city_treasure"),
            ResourceLocation.withDefaultNamespace( "chests/abandoned_mineshaft")
    };

    // Define an array of all_hammers.json end dungeon chest loot tables
    ResourceLocation[] endDungeonChestLootTables = new ResourceLocation[] {
            ResourceLocation.withDefaultNamespace("chests/end_city_treasure"),
            ResourceLocation.withDefaultNamespace("chests/stronghold_library")
    };

    // Define an array of all_hammers.json nether dungeon chest loot tables
    ResourceLocation[] netherDungeonChestLootTables = new ResourceLocation[] {
            ResourceLocation.withDefaultNamespace("chests/nether_bridge"),
            ResourceLocation.withDefaultNamespace("chests/bastion_treasure")
    };

    // Define an array of all_hammers.json overworld dungeon chest loot tables
    ResourceLocation[] overworldDungeonChestLootTables = new ResourceLocation[] {
            ResourceLocation.withDefaultNamespace( "chests/simple_dungeon"),
            ResourceLocation.withDefaultNamespace("chests/stronghold_corridor"),
            ResourceLocation.withDefaultNamespace("chests/abandoned_mineshaft")
    };

    ResourceLocation[] highTierChestLootTables = new ResourceLocation[] {
            ResourceLocation.withDefaultNamespace("chests/bastion_treasure"),
            ResourceLocation.withDefaultNamespace("chests/ancient_city"),
            ResourceLocation.withDefaultNamespace("chests/end_city_treasure"),
            ResourceLocation.withDefaultNamespace("chests/stronghold_library"),
            ResourceLocation.withDefaultNamespace("chests/stronghold_corridor"),
            ResourceLocation.withDefaultNamespace("chests/nether_bridge")
    };



    @Override
    protected void start() {

    }

}

