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

    
    protected AbstractMultiblock(List<MultiblockPart> parts) {
        this.parts = parts;
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

        Direction facing = controllerState.getValue(BlockStateProperties.HORIZONTAL_FACING);

        for (MultiblockPart part : parts) {

            BlockPos rotatedOffset = rotate(
                    part.offset().getX(),
                    part.offset().getZ(),
                    facing
            );

            BlockPos worldPos = controllerPos.offset(
                    rotatedOffset.getX(),
                    part.offset().getY(),
                    rotatedOffset.getZ()
            );

            // ✅ Add debug output here:
            System.out.println("Validating part: " + part.type() +
                    " at offset " + part.offset() +
                    " → worldPos: " + worldPos +
                    " | Expected blocks: " + part.allowedBlocks());

            if (!isValidPart(level, worldPos, part.type(), part)) {
                System.out.println("❌ Invalid part found at " + worldPos + " of type " + part.type());
                return false;
            }
        }

        System.out.println("✅ Multiblock structure is valid.");
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

        // ✅ Debug output for block presence
        System.out.println("Checking " + type + " at " + pos + ": found " + block.getClass().getSimpleName());

        if (part.allowedBlocks() != null) {
            boolean result = part.allowedBlocks().contains(block);
            System.out.println("→ Matches allowedBlocks? " + result);
            return result;
        }

        return switch (type) {
            case CONTROLLER -> block instanceof MultiblockController;
            case INPUT      -> block instanceof MultiblockInput;
            case OUTPUT     -> block instanceof MultiblockOutput;
            case CORE       -> block instanceof MultiblockRod;
            case CASING -> {
                if (part.allowedBlocks() != null) {
                    yield part.allowedBlocks().contains(block);
                }
                yield false; // Or optionally allow anything here
            }

            case AIR        -> state.isAir();
        };
    }

}
