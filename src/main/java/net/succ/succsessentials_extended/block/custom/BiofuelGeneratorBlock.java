package net.succ.succsessentials_extended.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
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
import net.succ.succsessentials_extended.api.machine.GeneratorMachine;
import net.succ.succsessentials_extended.api.machine.MachineTier;
import net.succ.succsessentials_extended.api.machine.TieredMachine;
import net.succ.succsessentials_extended.block.entity.ModBlockEntities;
import net.succ.succsessentials_extended.block.entity.custom.BiofuelGeneratorBlockEntity;
import org.jetbrains.annotations.Nullable;

public class BiofuelGeneratorBlock extends BaseEntityBlock
        implements TieredMachine, GeneratorMachine {

    /* ==========================================================
       BLOCKSTATE PROPERTIES
       ========================================================== */
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    /* ==========================================================
       MACHINE METADATA (API)
       ========================================================== */
    private final MachineTier tier;
    private final int generationRate;

    /* ==========================================================
       CODEC
       ========================================================== */
    public static final MapCodec<BiofuelGeneratorBlock> CODEC =
            simpleCodec(BiofuelGeneratorBlock::new);

    /* ==========================================================
       SHAPE
       ========================================================== */
    public static final VoxelShape SHAPE =
            Block.box(0, 0, 0, 16, 16, 16);

    /**
     * Explicit constructor (Option B).
     */
    public BiofuelGeneratorBlock(Properties properties,
                                 MachineTier tier,
                                 int generationRate) {
        super(properties);
        this.tier = tier;
        this.generationRate = generationRate;

        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(FACING, Direction.NORTH)
                        .setValue(LIT, false)
        );
    }

    /**
     * Codec constructor.
     * Must be deterministic and registry-safe.
     */
    private BiofuelGeneratorBlock(Properties properties) {
        this(properties, MachineTier.BASIC, BiofuelGeneratorBlockEntity.POWER_GENERATION_RATE);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    /* ==========================================================
       API
       ========================================================== */

    @Override
    public MachineTier getTier() {
        return tier;
    }

    @Override
    public int getGenerationRate() {
        return BiofuelGeneratorBlockEntity.POWER_GENERATION_RATE;
    }

    /* ==========================================================
       BLOCKSTATE / SHAPE
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
        return new BiofuelGeneratorBlockEntity(pos, state);
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
            if (blockEntity instanceof BiofuelGeneratorBlockEntity generator) {
                generator.drops();
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    /* ==========================================================
       PLAYER INTERACTION
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
            if (entity instanceof BiofuelGeneratorBlockEntity generator) {
                player.openMenu(
                        new SimpleMenuProvider(
                                generator,
                                Component.literal("Bio Fuel Generator")
                        ),
                        pos
                );
            } else {
                throw new IllegalStateException("Biofuel Generator container provider missing!");
            }
        }

        return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }

    /* ==========================================================
       SERVER TICKING
       ========================================================== */

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level,
                                                                  BlockState state,
                                                                  BlockEntityType<T> type) {
        if (level.isClientSide()) return null;

        return createTickerHelper(
                type,
                ModBlockEntities.BIO_FUEL_GENERATOR_BE.get(),
                (lvl, pos, st, be) -> be.tick(lvl, pos, st)
        );
    }
}
