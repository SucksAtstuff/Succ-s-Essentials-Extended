package net.succ.succsessentials_extended.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, Succsessentials_extended.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        // --------------------------------------------------
        //  VANILLA TOOL TAGS (SWORDS, PICKAXES, AXES, SHOVELS, HOES)
        // --------------------------------------------------

        // SWORDS
        tag(ItemTags.SWORDS)
                .add(ModItems.CHROMIUM_SWORD.get());

        // PICKAXES
        tag(ItemTags.PICKAXES)
                .add(ModItems.CHROMIUM_PICKAXE.get());

        // AXES
        tag(ItemTags.AXES)
                .add(ModItems.CHROMIUM_AXE.get());

        // SHOVELS
        tag(ItemTags.SHOVELS)
                .add(ModItems.CHROMIUM_SHOVEL.get());

        // HOES
        tag(ItemTags.HOES)
                .add(ModItems.CHROMIUM_HOE.get());

        // --------------------------------------------------
        //  TRIMMABLE ARMOR
        // --------------------------------------------------
        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.CHROMIUM_HELMET.get());

        // --------------------------------------------------
        //  ARMOR ENCHANTABLE (ALL PIECES)
        // --------------------------------------------------
        tag(ItemTags.ARMOR_ENCHANTABLE)
                .add(ModItems.CHROMIUM_HELMET.get());
        // --------------------------------------------------
        //  HEAD ARMOR ONLY
        // --------------------------------------------------
        tag(ItemTags.HEAD_ARMOR_ENCHANTABLE)
                .add(ModItems.CHROMIUM_HELMET.get());
        // --------------------------------------------------
        //  CHEST ARMOR ONLY
        // --------------------------------------------------
        tag(ItemTags.CHEST_ARMOR_ENCHANTABLE)
                .add(ModItems.CHROMIUM_CHESTPLATE.get());

        // --------------------------------------------------
        //  LEG ARMOR ONLY
        // --------------------------------------------------
        tag(ItemTags.LEG_ARMOR_ENCHANTABLE)
                .add(ModItems.CHROMIUM_LEGGINGS.get());

        // --------------------------------------------------
        //  FOOT ARMOR ONLY
        // --------------------------------------------------
        tag(ItemTags.FOOT_ARMOR_ENCHANTABLE)
                .add(ModItems.CHROMIUM_BOOTS.get());

        // --------------------------------------------------
        //  DURABILITY ENCHANTABLE (TOOLS + ARMOR)
        // --------------------------------------------------
        tag(ItemTags.DURABILITY_ENCHANTABLE)

                // swords
                .add(ModItems.CHROMIUM_SWORD.get())

                // axes
                .add(ModItems.CHROMIUM_AXE.get())

                // pickaxes
                .add(ModItems.CHROMIUM_PICKAXE.get())

                // shovels
                .add(ModItems.CHROMIUM_SHOVEL.get())

                // hoes
                .add(ModItems.CHROMIUM_HOE.get())

                // hammers
                .add(ModItems.CHROMIUM_HAMMER.get())

                // reinforced hammers
                .add(ModItems.CHROMIUM_REINFORCED_HAMMER.get())

                // paxels
                .add(ModItems.CHROMIUM_PAXEL.get())

                // armor
                .add(ModItems.CHROMIUM_HELMET.get())
                .add(ModItems.CHROMIUM_CHESTPLATE.get())
                .add(ModItems.CHROMIUM_LEGGINGS.get())
                .add(ModItems.CHROMIUM_BOOTS.get());


        // --------------------------------------------------
        //  WEAPON ENCHANTABLE (SWORDS + AXES)
        // --------------------------------------------------
        tag(ItemTags.WEAPON_ENCHANTABLE)
                .add(ModItems.CHROMIUM_SWORD.get())

                // axes count as weapons in vanilla
                .add(ModItems.CHROMIUM_AXE.get());

        // --------------------------------------------------
        //  SHARP WEAPON ENCHANTABLE (Swords + Axes)
        // --------------------------------------------------
        tag(ItemTags.SHARP_WEAPON_ENCHANTABLE)
                .add(ModItems.CHROMIUM_SWORD.get())

                .add(ModItems.CHROMIUM_AXE.get());

        // --------------------------------------------------
        //  FIRE ASPECT ENCHANTABLE (Swords + Axes)
        // --------------------------------------------------
        tag(ItemTags.FIRE_ASPECT_ENCHANTABLE)
                .add(ModItems.CHROMIUM_SWORD.get())

                .add(ModItems.CHROMIUM_AXE.get());

        // --------------------------------------------------
        //  MINING ENCHANTABLE (Pickaxe, shovel, hoe, axe, hammer, paxel)
        // --------------------------------------------------
        tag(ItemTags.MINING_ENCHANTABLE)

                // pickaxes
                .add(ModItems.CHROMIUM_PICKAXE.get())

                // shovels
                .add(ModItems.CHROMIUM_SHOVEL.get())

                // hoes
                .add(ModItems.CHROMIUM_HOE.get())

                // axes
                .add(ModItems.CHROMIUM_AXE.get())

                // hammers
                .add(ModItems.CHROMIUM_HAMMER.get())

                // reinforced hammers
                .add(ModItems.CHROMIUM_REINFORCED_HAMMER.get())

                // paxels
                .add(ModItems.CHROMIUM_PAXEL.get());

        // --------------------------------------------------
        //  MINING LOOT ENCHANTABLE (Pickaxe, hammer, paxel)
        // --------------------------------------------------
        tag(ItemTags.MINING_LOOT_ENCHANTABLE)

                // pickaxes
                .add(ModItems.CHROMIUM_PICKAXE.get())

                // hammers
                .add(ModItems.CHROMIUM_HAMMER.get())

                // reinforced hammers
                .add(ModItems.CHROMIUM_REINFORCED_HAMMER.get())

                // paxels
                .add(ModItems.CHROMIUM_PAXEL.get());
    }


}
