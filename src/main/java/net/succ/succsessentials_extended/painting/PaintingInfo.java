package net.succ.succsessentials_extended.painting;

/**
 * Simple metadata holder for a painting.
 *
 * This exists ONLY for:
 * - clean registration
 * - language datagen
 *
 * Minecraft itself does NOT store title/author in PaintingVariant.
 */
public record PaintingInfo(
        String id,       // registry id (and texture name)
        int width,       // pixel width (multiple of 16)
        int height,      // pixel height (multiple of 16)
        String title,    // lang title
        String author    // lang author
) {}
