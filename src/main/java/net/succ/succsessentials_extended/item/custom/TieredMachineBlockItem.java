package net.succ.succsessentials_extended.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * ============================================================
 * TieredMachineBlockItem
 *
 * Shared BlockItem for ALL machines & generators.
 * Handles tooltip rendering only.
 * ============================================================
 */
public class TieredMachineBlockItem extends BlockItem {

    private final String tierName;
    private final int powerGeneration; // 0 = not a generator

    public TieredMachineBlockItem(
            Block block,
            Item.Properties properties,
            String tierName,
            int powerGeneration
    ) {
        super(block, properties);
        this.tierName = tierName;
        this.powerGeneration = powerGeneration;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {


        /* ---------------- TIER ---------------- */

        tooltipComponents.add(
                Component.literal("Tier: ")
                        .withStyle(ChatFormatting.GRAY)
                        .append(Component.literal(tierName)
                                .withStyle(ChatFormatting.AQUA))
        );

        /* ---------------- POWER GEN (GEN ONLY) ---------------- */

        if (powerGeneration > 0) {
            tooltipComponents.add(
                    Component.literal("Generates: ")
                            .withStyle(ChatFormatting.GRAY)
                            .append(Component.literal(powerGeneration + " FE/t")
                                    .withStyle(ChatFormatting.GREEN))
            );
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
