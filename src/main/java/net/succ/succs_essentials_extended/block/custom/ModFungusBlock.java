package net.succ.succs_essentials_extended.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.event.level.BlockGrowFeatureEvent;

import java.util.Optional;
import java.util.function.Supplier;

public class ModFungusBlock extends BushBlock implements BonemealableBlock {

    protected static final VoxelShape SHAPE = Block.box(4.0, 0.0, 4.0, 12.0, 9.0, 12.0);

    private final ResourceKey<ConfiguredFeature<?, ?>> feature;
    private final Supplier<Block> requiredBaseBlock;

    public ModFungusBlock(ResourceKey<ConfiguredFeature<?, ?>> feature,
                          Supplier<Block> requiredBaseBlock,
                          Properties properties) {

        super(properties);
        this.feature = feature;
        this.requiredBaseBlock = requiredBaseBlock;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return null;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return
                state.is(BlockTags.NYLIUM) ||
                        state.is(Blocks.MYCELIUM) ||
                        state.is(Blocks.SOUL_SOIL) ||
                        state.is(requiredBaseBlock.get());
    }

    private Optional<? extends Holder<ConfiguredFeature<?, ?>>> getFeature(LevelReader level) {
        return level.registryAccess()
                .registryOrThrow(Registries.CONFIGURED_FEATURE)
                .getHolder(this.feature);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return level.getBlockState(pos.below()).is(requiredBaseBlock.get());
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return random.nextFloat() < 0.4f;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {

        BlockGrowFeatureEvent event = EventHooks.fireBlockGrowFeature(
                level, random, pos, getFeature(level).orElse(null)
        );

        if (!event.isCanceled()) {

            Optional.ofNullable(event.getFeature())
                    .ifPresent(featureHolder -> {

                        boolean placed = featureHolder.value().place(
                                level,
                                level.getChunkSource().getGenerator(),
                                random,
                                pos
                        );

                        // -----------------------------------------------
                        // NEW: Logging identical to GemSpireFeature
                        // -----------------------------------------------
                        if (placed) {
                            System.out.println("[Succs Essentials] Planted Glowcap Fungus at " + pos);
                        }
                    });
        }
    }
}
