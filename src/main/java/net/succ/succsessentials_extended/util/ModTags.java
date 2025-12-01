package net.succ.succsessentials_extended.util;


import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.succ.succsessentials_extended.Succsessentials_extended;

public class ModTags {
    public static class Items {
        // Global Tags
        public static final TagKey<Item> ORES =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores"));
        public static final TagKey<Item> INGOTS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots"));
        public static final TagKey<Item> TOOLS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "tools"));
        public static final TagKey<Item> MINING_TOOLS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "tools/mining_tool"));
        public static final TagKey<Item> MELEE_WEAPONS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "tools/melee_weapon"));
        public static final TagKey<Item> PICKAXES =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "tools/pickaxes"));
        public static final TagKey<Item> SHOVELS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "tools/shovels"));
        public static final TagKey<Item> AXES =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "tools/axes"));
        public static final TagKey<Item> SWORDS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "tools/swords"));
        public static final TagKey<Item> ARMOR =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "armors"));
        public static final TagKey<Item> ENCHANTABLES =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "enchantables"));

        private static TagKey<Item> createTag (String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Succsessentials_extended.MOD_ID, name));
        }
    }



    public static class Blocks {
        public static final TagKey<Block> NEEDS_CHROMIUM_TOOL = createTag("needs_chromium_tool");
        public static final TagKey<Block> INCORRECT_FOR_CHROMIUM_TOOL = createTag("incorrect_for_chromium_tool");

        // Global Tags
        public static final TagKey<Block> ORES =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores"));
        public static final TagKey<Block> STORAGE_BLOCKS =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks"));

        public static final TagKey<Block> PAXEL_MINEABLE = createTag("mineable/paxel");


        private static TagKey<Block> createTag (String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Succsessentials_extended.MOD_ID, name));
        }


    }

    public static class Structures {

    }
}
