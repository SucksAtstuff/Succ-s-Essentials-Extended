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
        //    - Skip BlockItems (their lang is handled by the BLOCKS section)
        //    - Skip any items that have a manual override later in this file
        // ======================================================================
        ModItems.ITEMS.getEntries().forEach(entry -> {
            Item item = entry.get();                 // The actual Item instance
            String path = entry.getId().getPath();   // Registry path, e.g. "atherium_sword"

            // --- 1) Skip block items (BlockItem uses the block.* lang key) ---
            if (item instanceof BlockItem) {
                return; // Blocks are handled in the BLOCKS section below
            }

            // --- 2) Skip items with manual overrides in section 9 ---
            if (path.equals("atherium_helmet")       // custom: "Atherium Crown"
                    || path.equals("funky_music_disc")
                    || path.equals("clear_mud_disc")
                    || path.equals("bass_music_disc")
                    || path.equals("rock_candy")) {  // custom: "'Rock Candy'"
                return;
            }

            // Everything else: generate "nice" name from registry path
            add(item.getDescriptionId(), format(path));
        });

        // ======================================================================
        // 2. AUTOMATIC BLOCKS
        //    - All blocks, including ores and woodsets
        //    - Their BlockItems use the same lang key, but we skipped those above
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
        List<ResourceKey<Biome>> BIOME_KEYS = List.of(

        );

        BIOME_KEYS.forEach(key ->
                add("biome." + Succsessentials_extended.MOD_ID + "." + key.location().getPath(),
                        format(key.location().getPath()))
        );

        // ======================================================================
        // 5. AUTOMATIC ENCHANTMENTS
        // ======================================================================
        List<ResourceKey<Enchantment>> ENCHANT_KEYS = List.of(

        );

        ENCHANT_KEYS.forEach(key ->
                add("enchantment." + Succsessentials_extended.MOD_ID + "." + key.location().getPath(),
                        format(key.location().getPath()))
        );

        // ======================================================================
        // 6. AUTOMATIC EFFECTS
        // ======================================================================
        ModEffects.MOB_EFFECTS.getEntries().forEach(entry ->
                add(entry.get().getDescriptionId(),
                        format(entry.getId().getPath()))
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
        // 9. MANUAL OVERRIDES
        //    (These have matching skips in the automatic sections above!)
        // ======================================================================

        // --- Items needing special names ---


        // --- Music discs ---


        // --- Tooltips ---


        // --- Keybinds ---


        // --- Smithing Template ---


        // --- Paintings (manual because not auto-generated) ---


        // --- Special Cases ---


        // ======================================================================
        // 10. CREATIVE MODE TABS
        // ======================================================================
        ModCreativeModeTabs.CREATIVE_MODE_TAB.getEntries().forEach(entry -> {
            String path = entry.getId().getPath();  // e.g. "succs_essentials_tab_gems"

            // Your creative tabs use:
            //   creativetab.succsessentials.<category>.tab
            //
            // Example:
            //   creativetab.succsessentials.gems.tab
            //
            // So we must convert "succs_essentials_tab_gems" → "gems"

            String clean = path.replace("succs_essentials_extended_tab_", ""); // "gems"

            // Build proper lang key
            String key = "creativetab." + Succsessentials_extended.MOD_ID + "." + clean + ".tab";

            // Convert "gems" → "Gems"
            String name = format(clean);

            add(key, "Succ's Essentials Extended " + name);
        });

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
