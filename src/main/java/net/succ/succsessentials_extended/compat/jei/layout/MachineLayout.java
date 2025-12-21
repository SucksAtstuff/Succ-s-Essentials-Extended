package net.succ.succsessentials_extended.compat.jei.layout;

/**
 * Declarative layout description for a machine JEI GUI.
 *
 * All coordinates are TEXTURE-LOCAL (before JEI padding).
 * Use -1 for unused slots.
 */
public record MachineLayout(
        int inputX,
        int inputY,
        int secondaryInputX,
        int secondaryInputY,
        int outputX,
        int outputY,
        int byproductX,
        int byproductY,
        int energyBarX,
        int energyBarY
) {}
