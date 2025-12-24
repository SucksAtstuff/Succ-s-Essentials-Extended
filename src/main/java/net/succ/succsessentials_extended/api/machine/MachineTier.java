package net.succ.succsessentials_extended.api.machine;

import net.minecraft.ChatFormatting;

/**
 * Public machine tier definition.
 *
 * This enum is intentionally stable and logic-free.
 * Internal systems may interpret it however they want.
 *
 * Visual metadata (like tooltip color) is safe to store here.
 */
public enum MachineTier {
    BASIC(1, ChatFormatting.GRAY),
    ADVANCED(2, ChatFormatting.RED),
    ELITE(3, ChatFormatting.BLUE);

    private final int level;
    private final ChatFormatting color;

    MachineTier(int level, ChatFormatting color) {
        this.level = level;
        this.color = color;
    }

    public int level() {
        return level;
    }

    /**
     * Tooltip / UI color for this tier.
     */
    public ChatFormatting color() {
        return color;
    }
}
