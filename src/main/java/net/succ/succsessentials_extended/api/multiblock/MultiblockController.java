package net.succ.succsessentials_extended.api.multiblock;

import net.minecraft.core.Direction;

/**
 * Implement this on a BlockEntity to get automatic multiblock structure preview
 * rendering when the player looks at an unformed controller.
 */
public interface MultiblockController {
    MultiblockStructure getStructure();
    Direction getFacing();
    boolean isFormed();
}