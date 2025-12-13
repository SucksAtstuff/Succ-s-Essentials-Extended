package net.succ.succsessentials_extended.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.succ.succsessentials_extended.block.entity.ModBlockEntities;
import net.succ.succsessentials_extended.block.entity.custom.AlloyForgerBlockEntity;
import org.jetbrains.annotations.Nullable;

public class AlloyForgerBlock extends BaseEntityBlock {

    /* ==========================================================
       BLOCKSTATE PROPERTIES
       ========================================================== */
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public static final MapCodec<AlloyForgerBlock> CODEC =
            simpleCodec(AlloyForgerBlock::new);

    /* ==========================================================
       SHAPE (FULL BLOCK LIKE FURNACE)
       ========================================================== */
    public static final VoxelShape SHAPE =
            Block.box(0, 0, 0, 16, 16, 16);

    public AlloyForgerBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    /* ==========================================================
       FACING
       ========================================================== */
    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public VoxelShape getShape(BlockState state,
                               BlockGetter level,
                               BlockPos pos,
                               CollisionContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(LIT, false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    /* ==========================================================
       BLOCK ENTITY
       ========================================================== */
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AlloyForgerBlockEntity(pos, state);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState state,
                         Level level,
                         BlockPos pos,
                         BlockState newState,
                         boolean isMoving) {

        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof AlloyForgerBlockEntity alloyForger) {
                alloyForger.drops();
            }
        }

        super.onRemove(state, level, pos, newState, isMoving);
    }

    /* ==========================================================
       INTERACTION
       ========================================================== */
    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack,
                                              BlockState state,
                                              Level level,
                                              BlockPos pos,
                                              Player player,
                                              InteractionHand hand,
                                              BlockHitResult hit) {

        if (!level.isClientSide()) {
            BlockEntity entity = level.getBlockEntity(pos);

            if (entity instanceof AlloyForgerBlockEntity alloyForger) {
                ((ServerPlayer) player).openMenu(
                        new SimpleMenuProvider(
                                alloyForger,
                                Component.translatable("block.succsessentials_extended.alloy_forger")
                        ),
                        pos
                );
            } else {
                throw new IllegalStateException("Alloy Forger container provider missing!");
            }
        }

        return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }

    /* ==========================================================
       TICKER
       ========================================================== */
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level,
                                                                  BlockState state,
                                                                  BlockEntityType<T> type) {
        if (level.isClientSide()) return null;

        return createTickerHelper(
                type,
                ModBlockEntities.ALLOY_FORGER_BE.get(),
                (lvl, pos, st, be) -> be.tick(lvl, pos, st)
        );
    }

    /* ==========================================================
       LIT PARTICLES / SOUND (FURNACE STYLE)
       ========================================================== */
    @Override
    public void animateTick(BlockState state,
                            Level level,
                            BlockPos pos,
                            RandomSource random) {

        if (!state.getValue(LIT)) return;

        double x = pos.getX() + 0.5;
        double y = pos.getY();
        double z = pos.getZ() + 0.5;

        if (random.nextDouble() < 0.1) {
            level.playLocalSound(
                    x, y, z,
                    SoundEvents.FURNACE_FIRE_CRACKLE,
                    SoundSource.BLOCKS,
                    1.0F,
                    1.0F,
                    false
            );
        }

        Direction direction = state.getValue(FACING);
        Direction.Axis axis = direction.getAxis();

        double offset = random.nextDouble() * 0.6 - 0.3;
        double xOffset = axis == Direction.Axis.X ? direction.getStepX() * 0.52 : offset;
        double yOffset = random.nextDouble() * 6.0 / 16.0;
        double zOffset = axis == Direction.Axis.Z ? direction.getStepZ() * 0.52 : offset;

        level.addParticle(
                ParticleTypes.SMOKE,
                x + xOffset,
                y + yOffset,
                z + zOffset,
                0.0, 0.0, 0.0
        );

        // Occasionally spit out item particles from INPUT A
        if (level.getBlockEntity(pos) instanceof AlloyForgerBlockEntity be &&
                !be.itemHandler.getStackInSlot(0).isEmpty()) {

            level.addParticle(
                    new ItemParticleOption(
                            ParticleTypes.ITEM,
                            be.itemHandler.getStackInSlot(0)
                    ),
                    x + xOffset,
                    y + yOffset,
                    z + zOffset,
                    0.0, 0.0, 0.0
            );
        }
    }
}
