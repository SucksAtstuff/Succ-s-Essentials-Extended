package net.succ.succsessentials_extended.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.succ.succsessentials_extended.api.machine.multi_block_structure.marker.MultiblockInput;
import net.succ.succsessentials_extended.block.entity.custom.NuclearReactorInputBlockEntity;
import org.jetbrains.annotations.Nullable;

public class NuclearReactorInputBlock extends BaseEntityBlock
        implements MultiblockInput {

    public static final MapCodec<NuclearReactorInputBlock> CODEC =
            simpleCodec(NuclearReactorInputBlock::new);

    public NuclearReactorInputBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new NuclearReactorInputBlockEntity(pos, state);
    }
}
