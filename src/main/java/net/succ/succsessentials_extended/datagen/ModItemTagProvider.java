package net.succ.succsessentials_extended.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.block.ModBlocks;
import net.succ.succsessentials_extended.item.ModItems;
import net.succ.succsessentials_extended.util.ModTags;
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

        tag(ItemTags.SWORDS)
                .add(ModItems.CHROMIUM_SWORD.get());

        tag(ItemTags.PICKAXES)
                .add(ModItems.CHROMIUM_PICKAXE.get());

        tag(ItemTags.AXES)
                .add(ModItems.CHROMIUM_AXE.get());

        tag(ItemTags.SHOVELS)
                .add(ModItems.CHROMIUM_SHOVEL.get());

        tag(ItemTags.HOES)
                .add(ModItems.CHROMIUM_HOE.get());

        // --------------------------------------------------
        //  TRIMMABLE ARMOR
        // --------------------------------------------------
        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.CHROMIUM_HELMET.get())
                .add(ModItems.CHROMIUM_CHESTPLATE.get())
                .add(ModItems.CHROMIUM_LEGGINGS.get())
                .add(ModItems.CHROMIUM_BOOTS.get());


        // --------------------------------------------------
        //  ARMOR ENCHANTABLE
        // --------------------------------------------------
        tag(ItemTags.ARMOR_ENCHANTABLE)
                .add(ModItems.CHROMIUM_HELMET.get());

        tag(ItemTags.HEAD_ARMOR_ENCHANTABLE)
                .add(ModItems.CHROMIUM_HELMET.get());

        tag(ItemTags.CHEST_ARMOR_ENCHANTABLE)
                .add(ModItems.CHROMIUM_CHESTPLATE.get());

        tag(ItemTags.LEG_ARMOR_ENCHANTABLE)
                .add(ModItems.CHROMIUM_LEGGINGS.get());

        tag(ItemTags.FOOT_ARMOR_ENCHANTABLE)
                .add(ModItems.CHROMIUM_BOOTS.get());

        // --------------------------------------------------
        //  DURABILITY ENCHANTABLE (TOOLS + ARMOR)
        // --------------------------------------------------
        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .add(ModItems.CHROMIUM_SWORD.get())
                .add(ModItems.CHROMIUM_AXE.get())
                .add(ModItems.CHROMIUM_PICKAXE.get())
                .add(ModItems.CHROMIUM_SHOVEL.get())
                .add(ModItems.CHROMIUM_HOE.get())
                .add(ModItems.CHROMIUM_HAMMER.get())
                .add(ModItems.CHROMIUM_REINFORCED_HAMMER.get())
                .add(ModItems.CHROMIUM_PAXEL.get())
                .add(ModItems.CHROMIUM_HELMET.get())
                .add(ModItems.CHROMIUM_CHESTPLATE.get())
                .add(ModItems.CHROMIUM_LEGGINGS.get())
                .add(ModItems.CHROMIUM_BOOTS.get());

        // --------------------------------------------------
        //  WEAPON ENCHANTABLE
        // --------------------------------------------------
        tag(ItemTags.WEAPON_ENCHANTABLE)
                .add(ModItems.CHROMIUM_SWORD.get())
                .add(ModItems.CHROMIUM_AXE.get());

        // --------------------------------------------------
        //  SHARP WEAPON ENCHANTABLE
        // --------------------------------------------------
        tag(ItemTags.SHARP_WEAPON_ENCHANTABLE)
                .add(ModItems.CHROMIUM_SWORD.get())
                .add(ModItems.CHROMIUM_AXE.get());

        // --------------------------------------------------
        //  FIRE ASPECT ENCHANTABLE
        // --------------------------------------------------
        tag(ItemTags.FIRE_ASPECT_ENCHANTABLE)
                .add(ModItems.CHROMIUM_SWORD.get())
                .add(ModItems.CHROMIUM_AXE.get());

        // --------------------------------------------------
        //  MINING ENCHANTABLE
        // --------------------------------------------------
        tag(ItemTags.MINING_ENCHANTABLE)
                .add(ModItems.CHROMIUM_PICKAXE.get())
                .add(ModItems.CHROMIUM_SHOVEL.get())
                .add(ModItems.CHROMIUM_HOE.get())
                .add(ModItems.CHROMIUM_AXE.get())
                .add(ModItems.CHROMIUM_HAMMER.get())
                .add(ModItems.CHROMIUM_REINFORCED_HAMMER.get())
                .add(ModItems.CHROMIUM_PAXEL.get());

        // --------------------------------------------------
        //  MINING LOOT ENCHANTABLE
        // --------------------------------------------------
        tag(ItemTags.MINING_LOOT_ENCHANTABLE)
                .add(ModItems.CHROMIUM_PICKAXE.get())
                .add(ModItems.CHROMIUM_HAMMER.get())
                .add(ModItems.CHROMIUM_REINFORCED_HAMMER.get())
                .add(ModItems.CHROMIUM_PAXEL.get());

        // --------------------------------------------------
        //  c:ores  (raw ore items / block items)
        // --------------------------------------------------
        tag(ModTags.Items.ORES)
                .add(ModBlocks.CHROMIUM_ORE.get().asItem())
                .add(ModBlocks.DEEPSLATE_CHROMIUM_ORE.get().asItem());

        // =====================================================================
        //                       C: COMMON TAGS (INTER-MOD TAGS)
        // =====================================================================

        // c:ingots (ingot unification)
        tag(ModTags.Items.INGOTS)
                .add(ModItems.CHROMIUM_INGOT.get());

        // c:tools (all chromium tools)
        tag(ModTags.Items.TOOLS)
                .add(ModItems.CHROMIUM_SWORD.get())
                .add(ModItems.CHROMIUM_PICKAXE.get())
                .add(ModItems.CHROMIUM_AXE.get())
                .add(ModItems.CHROMIUM_SHOVEL.get())
                .add(ModItems.CHROMIUM_HOE.get())
                .add(ModItems.CHROMIUM_HAMMER.get())
                .add(ModItems.CHROMIUM_REINFORCED_HAMMER.get())
                .add(ModItems.CHROMIUM_PAXEL.get());

        // c:tools/mining_tool (everything used to break blocks)
        tag(ModTags.Items.MINING_TOOLS)
                .add(ModItems.CHROMIUM_PICKAXE.get())
                .add(ModItems.CHROMIUM_AXE.get())
                .add(ModItems.CHROMIUM_SHOVEL.get())
                .add(ModItems.CHROMIUM_HOE.get())
                .add(ModItems.CHROMIUM_HAMMER.get())
                .add(ModItems.CHROMIUM_REINFORCED_HAMMER.get())
                .add(ModItems.CHROMIUM_PAXEL.get());

        // c:tools/melee_weapon
        tag(ModTags.Items.MELEE_WEAPONS)
                .add(ModItems.CHROMIUM_SWORD.get())
                .add(ModItems.CHROMIUM_AXE.get())
                .add(ModItems.CHROMIUM_HAMMER.get())
                .add(ModItems.CHROMIUM_REINFORCED_HAMMER.get());

        // c:tools/pickaxes
        tag(ModTags.Items.PICKAXES)
                .add(ModItems.CHROMIUM_PICKAXE.get());

        // c:tools/axes
        tag(ModTags.Items.AXES)
                .add(ModItems.CHROMIUM_AXE.get());

        // c:tools/shovels
        tag(ModTags.Items.SHOVELS)
                .add(ModItems.CHROMIUM_SHOVEL.get());

        // c:tools/swords
        tag(ModTags.Items.SWORDS)
                .add(ModItems.CHROMIUM_SWORD.get());

        // c:armors
        tag(ModTags.Items.ARMOR)
                .add(ModItems.CHROMIUM_HELMET.get())
                .add(ModItems.CHROMIUM_CHESTPLATE.get())
                .add(ModItems.CHROMIUM_LEGGINGS.get())
                .add(ModItems.CHROMIUM_BOOTS.get());

        // c:enchantables
        tag(ModTags.Items.ENCHANTABLES)
                // all swords, axes, pickaxes, shovels, hoes, hammers, reinforced hammers, paxels
                .addTag(ModTags.Items.TOOLS)

                // all armor
                .addTag(ModTags.Items.ARMOR);
    }
}
