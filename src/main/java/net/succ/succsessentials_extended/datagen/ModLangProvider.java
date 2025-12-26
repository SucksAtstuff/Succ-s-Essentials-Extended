package net.succ.succsessentials_extended.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.advancement.AllSuccAdvancements;
import net.succ.succsessentials_extended.block.ModBlocks;
import net.succ.succsessentials_extended.effect.ModEffects;
import net.succ.succsessentials_extended.entity.ModEntities;
import net.succ.succsessentials_extended.item.ModCreativeModeTabs;
import net.succ.succsessentials_extended.item.ModItems;
import net.succ.succsessentials_extended.painting.ModPaintings;
import net.succ.succsessentials_extended.painting.PaintingInfo;
import net.succ.succsessentials_extended.potion.ModPotions;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModLangProvider extends LanguageProvider {

    private final CompletableFuture<HolderLookup.Provider> lookup;

    public ModLangProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, Succsessentials_extended.MOD_ID, "en_us");
        this.lookup = lookup;
    }

    @Override
    protected void addTranslations() {

        // ======================================================================
        // 1. AUTOMATIC ITEMS
        // ======================================================================
        ModItems.ITEMS.getEntries().forEach(entry -> {
            Item item = entry.get();
            String path = entry.getId().getPath();

            if (item instanceof BlockItem) return;

            if (path.equals("atherium_helmet")
                    || path.equals("funky_music_disc")
                    || path.equals("clear_mud_disc")
                    || path.equals("bass_music_disc")
                    || path.equals("rock_candy")) {
                return;
            }

            add(item.getDescriptionId(), format(path));
        });

        // ======================================================================
        // 2. AUTOMATIC BLOCKS
        // ======================================================================
        ModBlocks.BLOCKS.getEntries().forEach(entry ->
                add(entry.get().getDescriptionId(), format(entry.getId().getPath()))
        );

        // ======================================================================
        // 3. AUTOMATIC ENTITIES
        // ======================================================================
        ModEntities.ENTRIES.forEach(supplier -> {
            EntityType<?> type = supplier.get();
            add(type.getDescriptionId(), format(type.toShortString()));
        });

        // ======================================================================
        // 4. AUTOMATIC BIOMES
        // ======================================================================
        List<ResourceKey<Biome>> BIOME_KEYS = List.of();

        BIOME_KEYS.forEach(key ->
                add("biome." + Succsessentials_extended.MOD_ID + "." + key.location().getPath(),
                        format(key.location().getPath()))
        );

        // ======================================================================
        // 5. AUTOMATIC ENCHANTMENTS
        // ======================================================================
        List<ResourceKey<Enchantment>> ENCHANT_KEYS = List.of();

        ENCHANT_KEYS.forEach(key ->
                add("enchantment." + Succsessentials_extended.MOD_ID + "." + key.location().getPath(),
                        format(key.location().getPath()))
        );

        // ======================================================================
        // 6. AUTOMATIC EFFECTS
        // ======================================================================
        ModEffects.MOB_EFFECTS.getEntries().forEach(entry ->
                add(entry.get().getDescriptionId(), format(entry.getId().getPath()))
        );

        // ======================================================================
        // 7. AUTOMATIC POTIONS
        // ======================================================================
        ModPotions.POTIONS.getEntries().forEach(entry -> {
            String id = entry.getId().getPath();
            String name = format(id);

            add("item.minecraft.potion.effect." + id, name + " Potion");
            add("item.minecraft.splash_potion.effect." + id, name + " Splash Potion");
            add("item.minecraft.lingering_potion.effect." + id, name + " Lingering Potion");
            add("item.minecraft.tipped_arrow.effect." + id, "Arrow of " + name);
        });

        // ======================================================================
        // 8. ADVANCEMENTS
        // ======================================================================
        AllSuccAdvancements.provideLang(this::add);

        // ======================================================================
        // 9. CREATIVE MODE TAB
        // ======================================================================
        add(
                "creativetab.succsessentials_extended",
                "Succ's Essentials Extended"
        );

        // ======================================================================
        // 10. JEI CATEGORIES
        // ======================================================================
        addJeiCategory("pulverizing", "Pulverizing");
        addJeiCategory("alloying", "Alloying");
        addJeiCategory("electric_smelting", "Machine Smelting");
        addJeiCategory("infusing", "Infusing");
        addJeiCategory("hammering", "Hammering");
        addJeiCategory("wire_cutting", "Wire Cutting");
        addJeiCategory("wire_drawing", "Wire Drawing");
        addJeiCategory("rolling_mill", "Rolling Mill");

        // ======================================================================
        // 11. AUTOMATIC PAINTINGS
        // ======================================================================
        for (PaintingInfo info : ModPaintings.PAINTING_INFOS) {

            String baseKey =
                    "painting." + Succsessentials_extended.MOD_ID + "." + info.id();

            // Painting title
            add(baseKey + ".title", info.title());

            // Painting author
            add(baseKey + ".author", info.author());
        }

    }

    /**
     * Helper for JEI category translation keys.
     * Format: jei.<modid>.<category>
     */
    private void addJeiCategory(String categoryId, String displayName) {
        add(
                "jei." + Succsessentials_extended.MOD_ID + "." + categoryId,
                displayName
        );
    }

    // Converts "ruby_pickaxe" → "Ruby Pickaxe"
    private static String format(String id) {
        String[] parts = id.split("_");
        StringBuilder formatted = new StringBuilder();
        for (String part : parts) {
            formatted.append(Character.toUpperCase(part.charAt(0)))
                    .append(part.substring(1))
                    .append(" ");
        }
        return formatted.toString().trim();
    }
}
