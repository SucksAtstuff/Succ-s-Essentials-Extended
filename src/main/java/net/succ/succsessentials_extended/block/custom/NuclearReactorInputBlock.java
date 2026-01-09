package net.succ.succsessentials_extended.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.succ.succsessentials_extended.block.entity.custom.NuclearReactorInputBlockEntity;
import org.jetbrains.annotations.Nullable;

public class NuclearReactorInputBlock extends BaseEntityBlock {

    /* ==========================================================
       SHAPE
       ========================================================== */
    public static final VoxelShape SHAPE =
            Block.box(0, 0, 0, 16, 16, 16);

    @Override
    public VoxelShape getShape(BlockState state,
                               BlockGetter level,
                               BlockPos pos,
                               CollisionContext context) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

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
