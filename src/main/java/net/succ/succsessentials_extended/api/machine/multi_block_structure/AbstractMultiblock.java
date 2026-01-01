package net.succ.succsessentials_extended.api.machine.multi_block_structure;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.succ.succsessentials_extended.api.machine.multi_block_structure.marker.MultiblockController;
import net.succ.succsessentials_extended.api.machine.multi_block_structure.marker.MultiblockInput;
import net.succ.succsessentials_extended.api.machine.multi_block_structure.marker.MultiblockOutput;
import net.succ.succsessentials_extended.api.machine.multi_block_structure.marker.MultiblockRod;

import java.util.List;
import java.util.Set;

/**
 * ============================================================
 * AbstractMultiblock
 *
 * Base class for ALL multiblock structures.
 *
 * - Controller-based validation
 * - Rotation-safe (horizontal)
 * - Supports multiple valid casing blocks
 * - Purely logical (no block destruction)
 *
 * All multiblock machines should extend this.
 * ============================================================
 */
public abstract class AbstractMultiblock {

    /* ============================================================
       MULTIBLOCK DEFINITION
       ============================================================ */

    /**
     * All required parts of the multiblock defined
     * as offsets relative to the controller.
     */
    protected final List<MultiblockPart> parts;

    /**
     * All blocks that are allowed to act as CASING.
     * Example: Lead Block, Panel Block, etc.
     */
    protected final Set<Block> validCasingBlocks;

    protected AbstractMultiblock(List<MultiblockPart> parts, Set<Block> validCasingBlocks) {
        this.parts = parts;
        this.validCasingBlocks = validCasingBlocks;
    }

    /* ============================================================
       PUBLIC ENTRY POINT
       ============================================================ */

    /**
     * Validates the multiblock structure at the given controller position.
     *
     * @param level           The world
     * @param controllerPos   Position of the controller block
     * @param controllerState State of the controller (used for rotation)
     * @return true if the structure is valid
     */
    public boolean validate(Level level, BlockPos controllerPos, BlockState controllerState) {

        // Determine the facing of the controller
        Direction facing = controllerState.getValue(BlockStateProperties.HORIZONTAL_FACING);

        // Validate every defined multiblock part
        for (MultiblockPart part : parts) {

            // Rotate X/Z offsets based on controller facing
            BlockPos rotatedOffset = rotate(
                    part.offset().getX(),
                    part.offset().getZ(),
                    facing
            );

            // Convert relative position to world position
            BlockPos worldPos = controllerPos.offset(
                    rotatedOffset.getX(),
                    part.offset().getY(),
                    rotatedOffset.getZ()
            );

            // Validate the block at this position
            if (!isValidPart(level, worldPos, part.type(), part)) {
                return false;
            }
        }

        return true;
    }

    /* ============================================================
       ROTATION HANDLING
       ============================================================ */

    /**
     * Rotates a relative X/Z offset based on horizontal facing.
     *
     * All multiblock definitions are written assuming NORTH.
     */
    private BlockPos rotate(int x, int z, Direction facing) {
        return switch (facing) {
            case NORTH -> new BlockPos(x, 0, z);
            case SOUTH -> new BlockPos(-x, 0, -z);
            case WEST  -> new BlockPos(z, 0, -x);
            case EAST  -> new BlockPos(-z, 0, x);
            default -> BlockPos.ZERO;
        };
    }

    /* ============================================================
       PART VALIDATION
       ============================================================ */

    /**
     * Validates an individual multiblock part.
     *
     * CASING validation now supports MULTIPLE blocks
     * via {@code validCasingBlocks}.
     */
    protected boolean isValidPart(Level level, BlockPos pos, MultiblockPartType type, MultiblockPart part) {

        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();

        // If this part defines its own allowed blocks, enforce them
        if (part.allowedBlocks() != null) {
            return part.allowedBlocks().contains(block);
        }

        return switch (type) {

            case CONTROLLER ->
                    block instanceof MultiblockController;
            case INPUT ->
                    block instanceof MultiblockInput;
            case OUTPUT ->
                    block instanceof MultiblockOutput;
            case CASING ->
                    validCasingBlocks.contains(block);
            case CORE -> block instanceof MultiblockRod;
            case AIR ->
                    state.isAir();

        };

    }
}
