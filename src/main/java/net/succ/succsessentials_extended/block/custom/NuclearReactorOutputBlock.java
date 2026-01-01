package net.succ.succsessentials_extended.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.succ.succsessentials_extended.api.machine.multi_block_structure.marker.MultiblockOutput;
import net.succ.succsessentials_extended.block.entity.custom.NuclearReactorOutputBlockEntity;
import org.jetbrains.annotations.Nullable;

public class NuclearReactorOutputBlock extends BaseEntityBlock
        implements MultiblockOutput {

    public static final MapCodec<NuclearReactorOutputBlock> CODEC =
            simpleCodec(NuclearReactorOutputBlock::new);

    public NuclearReactorOutputBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new NuclearReactorOutputBlockEntity(pos, state);
    }
}
