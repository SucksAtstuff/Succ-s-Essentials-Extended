package net.succ.succsessentials_extended.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.succ.succsessentials_extended.api.multiblock.MultiblockController;
import net.succ.succsessentials_extended.api.multiblock.MultiblockStructure;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

public class MultiblockPreviewRenderer {

    // -------------------------------------------------------------------------
    // 3-D wireframe outlines — rendered when looking at an unformed controller
    // -------------------------------------------------------------------------

    public static void onRenderLevel(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_ENTITIES) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null || mc.hitResult == null) return;
        if (!(mc.hitResult instanceof BlockHitResult blockHit)) return;

        BlockPos controllerPos = blockHit.getBlockPos();
        if (!(mc.level.getBlockEntity(controllerPos) instanceof MultiblockController controller)) return;
        if (controller.isFormed()) return; // structure complete — hide outlines

        List<MultiblockStructure.PositionEntry> allEntries =
                controller.getStructure().evaluate(mc.level, controllerPos, controller.getFacing());

        // Default: show first incomplete layer (build guide bottom→top).
        // Sneak: show every layer at once.
        List<MultiblockStructure.PositionEntry> toRender;
        if (mc.player.isShiftKeyDown()) {
            toRender = allEntries;
        } else {
            OptionalInt firstIncompleteLayer = allEntries.stream()
                    .filter(e -> !e.isCorrect())
                    .mapToInt(MultiblockStructure.PositionEntry::layer)
                    .min();
            toRender = firstIncompleteLayer.isPresent()
                    ? allEntries.stream().filter(e -> e.layer() == firstIncompleteLayer.getAsInt()).toList()
                    : allEntries;
        }

        Vec3 cam = event.getCamera().getPosition();
        PoseStack poseStack = event.getPoseStack();

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.lineWidth(2.5f);

        MultiBufferSource.BufferSource buffers = mc.renderBuffers().bufferSource();
        var lines = buffers.getBuffer(RenderType.lines());

        poseStack.pushPose();
        poseStack.translate(-cam.x, -cam.y, -cam.z);

        for (MultiblockStructure.PositionEntry entry : toRender) {
            BlockPos p = entry.worldPos();
            double x = p.getX(), y = p.getY(), z = p.getZ();
            AABB box = new AABB(x, y, z, x + 1, y + 1, z + 1);

            if (entry.isCorrect()) {
                LevelRenderer.renderLineBox(poseStack, lines, box, 0.0f, 1.0f, 0.2f, 0.9f); // green
            } else {
                LevelRenderer.renderLineBox(poseStack, lines, box, 1.0f, 0.2f, 0.2f, 0.9f); // red
            }
        }

        poseStack.popPose();
        buffers.endBatch(RenderType.lines());

        RenderSystem.lineWidth(1.0f);
        RenderSystem.disableBlend();
    }

    // -------------------------------------------------------------------------
    // 2-D HUD list — shows what blocks are missing, bottom-left of screen
    // -------------------------------------------------------------------------

    public static void onRenderGui(RenderGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.screen != null) return; // don't show while a GUI is open
        if (mc.player == null || mc.level == null || mc.hitResult == null) return;
        if (!(mc.hitResult instanceof BlockHitResult blockHit)) return;

        BlockPos controllerPos = blockHit.getBlockPos();
        if (!(mc.level.getBlockEntity(controllerPos) instanceof MultiblockController controller)) return;
        if (controller.isFormed()) return; // hide when complete

        List<MultiblockStructure.PositionEntry> allEntries =
                controller.getStructure().evaluate(mc.level, controllerPos, controller.getFacing());

        // Count missing blocks per type (preserving insertion order)
        Map<Block, Integer> missing = new LinkedHashMap<>();
        for (MultiblockStructure.PositionEntry entry : allEntries) {
            if (!entry.isCorrect() && entry.expected() != null) {
                missing.merge(entry.expected(), 1, Integer::sum);
            }
        }
        if (missing.isEmpty()) return;

        var graphics = event.getGuiGraphics();
        int screenH = mc.getWindow().getGuiScaledHeight();
        int rows = missing.size();
        int panelH = 14 + rows * 20 + 4;
        int panelW = 160;
        int px = 8;
        int py = screenH - panelH - 8;

        // Dark background
        graphics.fill(px - 2, py - 2, px + panelW, py + panelH, 0xA0000000);

        // Header
        graphics.drawString(mc.font, Component.literal("Missing blocks:"), px, py, 0xFFFFAA, false);
        py += 14;

        for (Map.Entry<Block, Integer> e : missing.entrySet()) {
            ItemStack icon = new ItemStack(e.getKey().asItem());
            graphics.renderItem(icon, px, py);
            String label = e.getValue() + "x " + e.getKey().getName().getString();
            graphics.drawString(mc.font, label, px + 18, py + 4, 0xFF6666, false);
            py += 20;
        }
    }
}
