package net.succ.succs_essentials_extended.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class ModEntityLootTablesProvider extends LootTableProvider {
    public ModEntityLootTablesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Set.of(),
                List.of(new SubProviderEntry(ModEntityLootTables::new, LootContextParamSets.ENTITY)),
                lookupProvider);
    }

    public static class ModEntityLootTables extends EntityLootSubProvider {
        protected ModEntityLootTables(HolderLookup.Provider lookupProvider) {
            super(FeatureFlags.REGISTRY.allFlags(), lookupProvider);
        }

        @Override
        public void generate() {
        }

        @Override
        protected Stream<EntityType<?>> getKnownEntityTypes() {
            return Stream.empty();
        }
    }
}
