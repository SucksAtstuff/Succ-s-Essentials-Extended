package net.succ.succsessentials_extended.compat.jei.layout;

import net.minecraft.client.gui.GuiGraphics;

/**
 * JEI-side energy renderer that visually matches
 * EnergyDisplayTooltipArea without requiring IEnergyStorage.
 */
public final class JeiEnergyRenderer {

    private JeiEnergyRenderer() {}

    /**
     * Renders an energy bar identical to machine GUIs,
     * but using static recipe energy values.
     */
    public static void render(
            GuiGraphics graphics,
            int x,
            int y,
            int width,
            int height,
            int energy,
            int maxEnergy
    ) {
        int clamped = Math.min(energy, maxEnergy);
        int stored = (int) (height * (clamped / (float) maxEnergy));

        // Same colors as EnergyDisplayTooltipArea
        graphics.fillGradient(
                x,
                y + (height - stored),
                x + width,
                y + height,
                0xffb51500,
                0xff600b00
        );
    }
}
