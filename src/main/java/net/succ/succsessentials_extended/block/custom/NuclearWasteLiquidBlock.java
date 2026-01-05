package net.succ.succsessentials_extended.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.succ.succsessentials_extended.effect.ModEffects;
import net.succ.succsessentials_extended.item.ModItems;

import java.util.function.Supplier;

/**
 * ============================================================
 * NuclearWasteLiquidBlock
 *
 * - Applies radiation while inside
 * - Radiation amplifier ramps up over time
 * - Hazmat armor SLOWS amplifier gain
 * ============================================================
 */
public class NuclearWasteLiquidBlock extends LiquidBlock {

    /* ==========================================================
       CONFIG
       ========================================================== */
    private static final int MAX_AMPLIFIER = 4;
    private static final int BASE_INCREASE_INTERVAL = 40; // 2 seconds

    public NuclearWasteLiquidBlock(Supplier<? extends FlowingFluid> fluidSupplier, Properties properties) {
        super(fluidSupplier.get(), properties);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        super.entityInside(state, level, pos, entity);

        if (level.isClientSide) return;
        if (!(entity instanceof LivingEntity living)) return;

        /* ==========================================================
           CURRENT AMPLIFIER
           ========================================================== */
        int amplifier = 0;
        if (living.hasEffect(ModEffects.RADIATED_EFFECT)) {
            amplifier = living.getEffect(ModEffects.RADIATED_EFFECT).getAmplifier();
        }

        /* ==========================================================
           HAZMAT CHECK (YOUR STYLE)
           ========================================================== */
        int interval = BASE_INCREASE_INTERVAL;

        if (living instanceof Player player && wearingHazmatSet(player)) {
            interval *= 3; // radiation builds 3x slower
        }

        /* ==========================================================
           DEPTH SCALING
           ========================================================== */
        int depth = Math.max(1, living.getBlockY() - pos.getY());
        interval = Math.max(10, interval - depth * 5);

        /* ==========================================================
           RAMP AMPLIFIER
           ========================================================== */
        if (living.tickCount % interval == 0) {
            amplifier = Math.min(amplifier + 1, MAX_AMPLIFIER);
        }

        /* ==========================================================
           APPLY / REFRESH EFFECT
           ========================================================== */
        living.addEffect(new MobEffectInstance(
                ModEffects.RADIATED_EFFECT,
                60,
                amplifier,
                true,
                false
        ));
    }

    /* ==========================================================
       ARMOR CHECK (MATCHES YOUR HUSK CODE STYLE)
       ========================================================== */
    private static boolean wearingHazmatSet(Player p) {
        ItemStack head  = p.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chest = p.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack legs  = p.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack feet  = p.getItemBySlot(EquipmentSlot.FEET);

        // Change to hazmat suit or something else later
        boolean h = !head.isEmpty()  && head.is(ModItems.CHROMIUM_HELMET.get());
        boolean c = !chest.isEmpty() && chest.is(ModItems.CHROMIUM_CHESTPLATE.get());
        boolean l = !legs.isEmpty()  && legs.is(ModItems.CHROMIUM_LEGGINGS.get());
        boolean f = !feet.isEmpty()  && feet.is(ModItems.CHROMIUM_BOOTS.get());

        return h && c && l && f;
    }
}
