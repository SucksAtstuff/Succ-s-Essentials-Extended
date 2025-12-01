package net.succ.succsessentials_extended.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;
import net.minecraft.tags.TagKey;

import java.util.List;

/**
 * A debug tool to print all block tags for the clicked block.
 * NeoForge 1.21 removed tag debugging from F3+I and removed
 * the old /dump commands, so this item is the only fully reliable
 * way to inspect block tags directly in-game.
 */
public class DebugTagItem extends Item {

    public DebugTagItem(Properties properties) {
        super(properties);
    }

    /**
     * Called when the player right-clicks a block with this item.
     * It reads the block's tag list and prints every tag to the chat.
     */
    @Override
    public InteractionResult useOn(UseOnContext context) {

        // Get the clicked block's state
        BlockState state = context.getLevel().getBlockState(context.getClickedPos());
        Block block = state.getBlock();

        // NeoForge 1.21 returns a Stream<TagKey<Block>>
        // We convert it to a List (no Set needed)
        List<TagKey<Block>> tags = block.defaultBlockState().getTags().toList();

        // Header message with clicked block ID
        context.getPlayer().sendSystemMessage(Component.literal(
                "Tags for block: " + block.toString()
        ));

        // If the block has no tags at all
        if (tags.isEmpty()) {
            context.getPlayer().sendSystemMessage(Component.literal(" - (No tags found)"));
        } else {
            // Print every tag's namespace + path
            for (TagKey<Block> tag : tags) {
                context.getPlayer().sendSystemMessage(Component.literal(
                        " - " + tag.location()
                ));
            }
        }

        return InteractionResult.SUCCESS;
    }
}
