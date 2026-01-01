package net.succ.succsessentials_extended.block.custom.multiblock;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.succ.succsessentials_extended.api.machine.multi_block_structure.marker.MultiblockRod;

public class NuclearReactorRodBlock
        extends Block
        implements MultiblockRod {

    public NuclearReactorRodBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }
}
