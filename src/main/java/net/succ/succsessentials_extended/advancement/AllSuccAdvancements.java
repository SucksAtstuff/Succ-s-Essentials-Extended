package net.succ.succsessentials_extended.advancement;

import com.google.common.collect.Sets;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.PackOutput.Target;
import net.minecraft.resources.ResourceLocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * AllSuccAdvancements
 *
 * Central registry for all advancements for Succ's Essentials.
 * Structured similar to Create's AllAdvancements.
 */
public class AllSuccAdvancements implements DataProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger("SuccAdvancements");

    /**
     * Every SuccAdvancement instance registers itself here in its constructor.
     */
    public static final List<SuccAdvancement> ENTRIES = new ArrayList<>();


    // ========================================================================
    // DATAGEN IMPLEMENTATION
    // ========================================================================

    private final PackOutput output;
    private final CompletableFuture<HolderLookup.Provider> registries;

    public AllSuccAdvancements(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        this.output = output;
        this.registries = registries;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        return registries.thenCompose(provider -> {

            PackOutput.PathProvider pathProvider =
                    output.createPathProvider(Target.DATA_PACK, "advancement");

            List<CompletableFuture<?>> futures = new ArrayList<>();
            Set<ResourceLocation> usedIds = Sets.newHashSet();

            Consumer<AdvancementHolder> saver = holder -> {
                ResourceLocation id = holder.id();

                if (!usedIds.add(id)) {
                    throw new IllegalStateException("Duplicate advancement: " + id);
                }

                Path path = pathProvider.json(id);
                LOGGER.info("Saving advancement {}", id);

                futures.add(DataProvider.saveStable(
                        cache, provider, Advancement.CODEC, holder.value(), path
                ));
            };

            // Let each SuccAdvancement generate its AdvancementHolder
            for (SuccAdvancement adv : ENTRIES) {
                adv.save(saver, provider);
            }

            return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
        });
    }

    @Override
    public String getName() {
        return "Succ's Essentials Advancements";
    }

    /**
     * Called from your lang provider to auto-generate advancement titles/descriptions.
     */
    public static void provideLang(BiConsumer<String, String> langOut) {
        for (SuccAdvancement adv : ENTRIES) {
            adv.provideLang(langOut);
        }
    }

    public static void register() {
        // Parity with Create – nothing needed here at runtime.
    }
}
