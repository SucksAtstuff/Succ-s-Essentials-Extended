package net.succ.succsessentials_extended.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ModVineBlock extends VineBlock {

    public ModVineBlock() {
        super(Properties
                .of()
                .noCollission()
                .strength(0.2F)
                .lightLevel(state -> 4) // Optional: make it glow
                .randomTicks()
        );
    }

    private boolean canSpread(BlockGetter world, BlockPos pos) {
        int maxNearby = 5;
        int found = 0;

        for (BlockPos checkPos : BlockPos.betweenClosed(
                pos.offset(-4, -1, -4),
                pos.offset(4, 1, 4))) {

            if (world.getBlockState(checkPos).is(this)) {
                found++;
                if (found >= maxNearby) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean hasValidNeighbor(BlockGetter level, BlockPos pos, Direction direction) {
        return VineBlock.isAcceptableNeighbour(level, pos, direction);
    }

    private boolean hasAnyHorizontalFace(BlockState state) {
        return state.getValue(NORTH) || state.getValue(SOUTH) || state.getValue(EAST) || state.getValue(WEST);
    }



    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.getGameRules().getBoolean(GameRules.RULE_DO_VINES_SPREAD)) return;
        if (random.nextInt(4) != 0) return;

        Direction direction = Direction.getRandom(random);
        BlockPos targetPos = pos.relative(direction);

        if (direction.getAxis().isHorizontal() && !state.getValue(VineBlock.getPropertyForFace(direction))) {
            if (canSpread(level, pos)) {
                BlockState above = level.getBlockState(pos.above());
                if (above.is(this) && above.getValue(VineBlock.getPropertyForFace(direction))) {
                    level.setBlock(pos, state.setValue(VineBlock.getPropertyForFace(direction), true), 2);
                } else if (level.isEmptyBlock(targetPos)) {
                    BlockState newState = this.defaultBlockState().setValue(VineBlock.getPropertyForFace(direction.getOpposite()), true);
                    if (hasValidNeighbor(level, targetPos, direction.getOpposite())) {
                        level.setBlock(targetPos, newState, 2);
                    }
                }
            }
        } else if (direction == Direction.UP && pos.getY() < level.getMaxBuildHeight() - 1) {
            if (level.isEmptyBlock(pos.above()) && canSpread(level, pos)) {
                BlockState newState = this.defaultBlockState();
                for (Direction dir : Direction.Plane.HORIZONTAL) {
                    if (random.nextBoolean() && hasValidNeighbor(level, pos.above().relative(dir), dir.getOpposite())) {
                        newState = newState.setValue(VineBlock.getPropertyForFace(dir), true);
                    }
                }
                if (hasAnyHorizontalFace(newState)) {
                    level.setBlock(pos.above(), newState, 2);
                }
            }
        } else if (direction == Direction.DOWN && pos.getY() > level.getMinBuildHeight()) {
            BlockPos below = pos.below();
            BlockState belowState = level.getBlockState(below);

            if (belowState.isAir()) {
                BlockState newState = this.defaultBlockState();
                for (Direction dir : Direction.Plane.HORIZONTAL) {
                    if (random.nextBoolean() && state.getValue(VineBlock.getPropertyForFace(dir))) {
                        newState = newState.setValue(VineBlock.getPropertyForFace(dir), true);
                    }
                }
                if (hasAnyHorizontalFace(newState)) {
                    level.setBlock(below, newState, 2);
                }
            }
        }
    }


    @Override
    public boolean isLadder(BlockState state, LevelReader level, BlockPos pos, LivingEntity entity) {
        return true;
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
        return 15;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
        return 30;
    }
}
