package net.succ.succsessentials_extended.block.custom.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.succ.succsessentials_extended.api.machine.multi_block_structure.AbstractMultiblock;
import net.succ.succsessentials_extended.api.machine.multi_block_structure.MultiblockPart;
import net.succ.succsessentials_extended.api.machine.multi_block_structure.MultiblockPartType;
import net.succ.succsessentials_extended.block.ModBlocks;

import java.util.List;
import java.util.Set;

/**
 * ============================================================
 * NuclearReactorMultiblock
 *
 * Controller is FRONT-MIDDLE of a 3x3x3 structure.
 *
 * Layout (facing NORTH):
 *
 * TOP (y = +1)
 *  P | L | P
 *  L | R | L
 *  P | L | P
 *
 * MIDDLE (y = 0)
 *  P | L | P
 *  L | R | L
 *  I | C | O
 *
 * BOTTOM (y = -1)
 *  P | L | P
 *  L | R | L
 *  P | L | P
 * ============================================================
 */
public class NuclearReactorMultiblock extends AbstractMultiblock {

    public NuclearReactorMultiblock() {
        super(
                defineParts());
    }

    /* ============================================================
       PART DEFINITIONS
       ============================================================ */

    private static List<MultiblockPart> defineParts() {
        return List.of(
                // === CONTROLLER (Front-middle, Y = 0) ===
                new MultiblockPart(new BlockPos(0, 0, 0), MultiblockPartType.CONTROLLER),

                // === INPUT / OUTPUT (Y = 0) ===
                new MultiblockPart(new BlockPos(-1, 0, 0), MultiblockPartType.INPUT),
                new MultiblockPart(new BlockPos(1, 0, 0), MultiblockPartType.OUTPUT),

                // === CENTER BACK ROD (Y = 0) ===
                new MultiblockPart(new BlockPos(0, 0, 1), MultiblockPartType.CORE),

                // === CASINGS ===
                casing(-1, 0, -1, ModBlocks.PANEL_BLOCK.get()),   // Top-left
                casing(0, 0, -1, ModBlocks.ALUMINIUM_BLOCK.get()),// Top-center
                casing(1, 0, -1, ModBlocks.PANEL_BLOCK.get()),    // Top-right

                casing(-1, 0, 1, ModBlocks.ALUMINIUM_BLOCK.get()),// Bottom-left
                casing(1, 0, 1, ModBlocks.ALUMINIUM_BLOCK.get())  // Bottom-right
        );
    }





    /* ============================================================
       CASING HELPERS
       ============================================================ */

    private static MultiblockPart casing(int x, int y, int z) {
        return new MultiblockPart(
                new BlockPos(x, y, z),
                MultiblockPartType.CASING
        );
    }

    /* ============================================================
       VALID CASING BLOCKS
       ============================================================ */

    private static MultiblockPart casing(int x, int y, int z, Block allowedBlock) {
        return new MultiblockPart(
                new BlockPos(x, y, z),
                MultiblockPartType.CASING,
                Set.of(allowedBlock)
        );
    }

}
