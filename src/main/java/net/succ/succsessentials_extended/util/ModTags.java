package net.succ.succsessentials_extended.util;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.succ.succsessentials_extended.Succsessentials_extended;

public class ModTags {
    public static class Items {
        private static TagKey<Item> createTag (String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Succsessentials_extended.MOD_ID, name));
        }
    }

    public static class Blocks {

        public static final TagKey<Block> PAXEL_MINEABLE = createTag("mineable/paxel");


        private static TagKey<Block> createTag (String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Succsessentials_extended.MOD_ID, name));
        }


    }

    public static class Structures {

    }
}
