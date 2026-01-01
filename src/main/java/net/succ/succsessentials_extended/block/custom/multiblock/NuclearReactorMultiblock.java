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
                defineParts(),
                defineCasingBlocks()
        );
    }

    /* ============================================================
       PART DEFINITIONS
       ============================================================ */

    private static List<MultiblockPart> defineParts() {
        return List.of(

                /* ================= CONTROLLER ================= */
                new MultiblockPart(
                        new BlockPos(0, 0, 0),
                        MultiblockPartType.CONTROLLER
                ),

                /* ================= INPUT / OUTPUT ================= */
                new MultiblockPart(
                        new BlockPos(-1, 0, 0),
                        MultiblockPartType.INPUT
                ),
                new MultiblockPart(
                        new BlockPos(1, 0, 0),
                        MultiblockPartType.OUTPUT
                ),

                /* ================= REACTOR RODS ================= */
                new MultiblockPart(
                        new BlockPos(0, -1, -1),
                        MultiblockPartType.CORE
                ),
                new MultiblockPart(
                        new BlockPos(0, 0, -1),
                        MultiblockPartType.CORE
                ),
                new MultiblockPart(
                        new BlockPos(0, 1, -1),
                        MultiblockPartType.CORE
                ),

                /* ================= CASING — BOTTOM ================= */
                casing(-1, -1, -1), casing(0, -1, -1), casing(1, -1, -1),
                casing(-1, -1,  0),                    casing(1, -1,  0),
                casing(-1, -1,  1), casing(0, -1,  1), casing(1, -1,  1),

                /* ================= CASING — MIDDLE ================= */
                casing(-1, 0, -1),                    casing(1, 0, -1),
                casing(-1, 0,  1), casing(0, 0,  1), casing(1, 0,  1),

                /* ================= CASING — TOP ================= */
                casing(-1, 1, -1), casing(0, 1, -1), casing(1, 1, -1),
                casing(-1, 1,  0),                    casing(1, 1,  0),
                casing(-1, 1,  1), casing(0, 1,  1), casing(1, 1,  1)
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

    private static Set<Block> defineCasingBlocks() {
        return Set.of(
                ModBlocks.PANEL_BLOCK.get(),
                ModBlocks.ALUMINIUM_BLOCK.get() // swap to lead later
        );
    }
}
