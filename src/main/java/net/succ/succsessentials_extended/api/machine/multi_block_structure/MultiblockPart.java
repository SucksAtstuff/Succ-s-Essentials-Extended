package net.succ.succsessentials_extended.api.machine.multi_block_structure;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public record MultiblockPart(
        BlockPos offset,
        MultiblockPartType type,
        Set<Block> allowedBlocks
) {
    public MultiblockPart(BlockPos offset, MultiblockPartType type) {
        this(offset, type, null);
    }
}


