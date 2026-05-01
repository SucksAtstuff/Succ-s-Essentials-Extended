package net.succ.succsessentials_extended.api.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Defines the shape of a multiblock structure relative to a controller block.
 *
 * Coordinate convention — origin is the controller:
 *   right   (+1) = facing.getClockWise()
 *   right   (-1) = facing.getCounterClockWise()
 *   up      (+1) = Direction.UP
 *   forward (+1) = facing.getOpposite()  (INTO the structure, away from the player)
 *
 * Example:
 *   private static final MultiblockStructure STRUCTURE = MultiblockStructure.builder()
 *       .require(-1, 0, 0, ModBlocks.NUCLEAR_REACTOR_INPUT.get())
 *       .require( 1, 0, 0, ModBlocks.NUCLEAR_REACTOR_OUTPUT.get())
 *       .require( 0, 0, 1, ModBlocks.NUCLEAR_REACTOR_CORE.get())
 *       .build();
 *
 *   // In tick:
 *   boolean formed = STRUCTURE.validate(level, worldPosition, getFacing());
 */
public class MultiblockStructure {

    private record Entry(int right, int up, int forward, Predicate<BlockState> predicate, @Nullable Block hint) {}

    /** Result of evaluating one required position against the world. */
    public record PositionEntry(BlockPos worldPos, int layer, boolean isCorrect, @Nullable Block expected) {}

    private final List<Entry> entries;

    private MultiblockStructure(List<Entry> entries) {
        this.entries = entries;
    }

    /**
     * Returns true when every required position contains the expected block.
     * Call this on the server side only.
     */
    public boolean validate(Level level, BlockPos controllerPos, Direction facing) {
        for (Entry entry : entries) {
            BlockPos worldPos = resolve(controllerPos, facing, entry.right(), entry.up(), entry.forward());
            if (!entry.predicate().test(level.getBlockState(worldPos))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Evaluates every required position and returns whether each one is satisfied.
     * Used by the client-side structure preview renderer.
     */
    public List<PositionEntry> evaluate(Level level, BlockPos controllerPos, Direction facing) {
        List<PositionEntry> result = new ArrayList<>(entries.size());
        for (Entry entry : entries) {
            BlockPos worldPos = resolve(controllerPos, facing, entry.right(), entry.up(), entry.forward());
            boolean correct = entry.predicate().test(level.getBlockState(worldPos));
            result.add(new PositionEntry(worldPos, entry.up(), correct, entry.hint()));
        }
        return result;
    }

    /**
     * Converts relative structure coordinates to a world BlockPos given the
     * controller's position and facing direction.
     */
    public static BlockPos resolve(BlockPos origin, Direction facing, int right, int up, int forward) {
        Direction rightDir = facing.getClockWise();
        Direction intoStructure = facing.getOpposite();
        return origin
                .relative(rightDir, right)
                .relative(Direction.UP, up)
                .relative(intoStructure, forward);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final List<Entry> entries = new ArrayList<>();

        /** Require an exact block at the given relative position. */
        public Builder require(int right, int up, int forward, Block block) {
            entries.add(new Entry(right, up, forward, state -> state.is(block), block));
            return this;
        }

        /** Require any block matching a tag at the given relative position. */
        public Builder requireTag(int right, int up, int forward, TagKey<Block> tag) {
            entries.add(new Entry(right, up, forward, state -> state.is(tag), null));
            return this;
        }

        /** Require a block satisfying a custom predicate at the given relative position. */
        public Builder require(int right, int up, int forward, Predicate<BlockState> predicate) {
            entries.add(new Entry(right, up, forward, predicate, null));
            return this;
        }

        public MultiblockStructure build() {
            return new MultiblockStructure(List.copyOf(entries));
        }
    }
}