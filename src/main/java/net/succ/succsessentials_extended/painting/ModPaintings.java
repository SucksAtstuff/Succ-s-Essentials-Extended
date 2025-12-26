package net.succ.succsessentials_extended.painting;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succsessentials_extended.Succsessentials_extended;

import java.util.ArrayList;
import java.util.List;

/**
 * Central registry for painting variants.
 *
 * IMPORTANT:
 * - DeferredRegister.register() returns DeferredHolder, NOT PaintingVariant
 * - Painting metadata is stored separately for datagen
 */
public class ModPaintings {

    /**
     * Deferred register for painting variants.
     */
    public static final DeferredRegister<PaintingVariant> PAINTINGS =
            DeferredRegister.create(
                    Registries.PAINTING_VARIANT,
                    Succsessentials_extended.MOD_ID
            );

    /**
     * Metadata list used ONLY for language datagen.
     */
    public static final List<PaintingInfo> PAINTING_INFOS = new ArrayList<>();

    /**
     * Registers a painting variant AND records metadata.
     *
     * @param info painting metadata
     * @return DeferredHolder for the painting variant
     */
    private static DeferredHolder<PaintingVariant, PaintingVariant> register(PaintingInfo info) {

        // Store metadata for language datagen
        PAINTING_INFOS.add(info);

        // Texture location:
        // assets/<modid>/textures/painting/<id>.png
        ResourceLocation textureId = ResourceLocation.fromNamespaceAndPath(
                Succsessentials_extended.MOD_ID,
                "painting/" + info.id()
        );

        // Register the painting variant
        return PAINTINGS.register(
                info.id(),
                () -> new PaintingVariant(
                        info.width(),
                        info.height(),
                        textureId
                )
        );
    }

    // ======================================================================
    // PAINTINGS
    // ======================================================================

    public static final DeferredHolder<PaintingVariant, PaintingVariant> DEVELOPER =
            register(new PaintingInfo(
                    "developer",
                    16,
                    32,
                    "Developer",
                    "IsuckAtEverything"
            ));
}
