package net.succ.succsessentials_extended.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
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
import net.succ.succsessentials_extended.api.machine.multi_block_structure.marker.MultiblockController;
import net.succ.succsessentials_extended.block.entity.ModBlockEntities;
import net.succ.succsessentials_extended.block.entity.custom.AlloyForgerBlockEntity;
import net.succ.succsessentials_extended.block.entity.custom.BiofuelGeneratorBlockEntity;
import net.succ.succsessentials_extended.block.entity.custom.NuclearReactorControllerBlockEntity;
import org.jetbrains.annotations.Nullable;

public class NuclearReactorControllerBlock extends BaseEntityBlock
        implements TieredMachine, GeneratorMachine, MultiblockController {

    /* ==========================================================
       BLOCKSTATE PROPERTIES
       ========================================================== */
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final BooleanProperty FORMED =
            BooleanProperty.create("formed");


    /* ==========================================================
       MACHINE METADATA
       ========================================================== */
    private final MachineTier tier;

    public static final MapCodec<NuclearReactorControllerBlock> CODEC =
            simpleCodec(NuclearReactorControllerBlock::new);

    /* ==========================================================
        SHAPE (FULL BLOCK LIKE FURNACE)
       ========================================================== */

    public static final VoxelShape SHAPE =
            Block.box(0, 0, 0, 16, 16, 16);

    /**
     * Main constructor (used by registry).
     */
    public NuclearReactorControllerBlock(Properties properties, MachineTier tier) {
        super(properties);
        this.tier = tier;

        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(FACING, Direction.NORTH)
                        .setValue(LIT, false)
                        .setValue(FORMED, false)
        );
    }

    /**
     * Codec constructor.
     * Must be deterministic.
     */
    private NuclearReactorControllerBlock(Properties properties) {
        this(properties, MachineTier.ADVANCED);
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
        builder.add(FACING, LIT, FORMED);
    }


    /* ==========================================================
       BLOCK ENTITY
       ========================================================== */
    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new NuclearReactorControllerBlockEntity(pos, state);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
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

            if (entity instanceof NuclearReactorControllerBlockEntity reactor) {

                // 🔒 Gate the menu behind multiblock validity
                if (!reactor.isFormed()) {
                    player.displayClientMessage(
                            Component.translatable(
                                    "message.succsessentials_extended.structure_incomplete"
                            ),
                            true
                    );
                    return ItemInteractionResult.CONSUME;
                }

                ((ServerPlayer) player).openMenu(reactor, pos);
            }
        }

        return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }


    @Override
    public void onRemove(BlockState state,
                         Level level,
                         BlockPos pos,
                         BlockState newState,
                         boolean isMoving) {

        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof NuclearReactorControllerBlockEntity reactor) {
                reactor.drops();
            }
        }

        super.onRemove(state, level, pos, newState, isMoving);
    }

    public int getGenerationRate() {
        return NuclearReactorControllerBlockEntity.POWER_GENERATION_RATE;
    }

    @Override
    public MachineTier getTier () {
        return tier;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level,
                                                                  BlockState state,
                                                                  BlockEntityType<T> type) {
        return level.isClientSide ? null :
                createTickerHelper(
                        type,
                        ModBlockEntities.NUCLEAR_REACTOR_CONTROLLER_BE.get(),
                        NuclearReactorControllerBlockEntity::tick
                );
    }

}
