package net.succ.succsessentials_extended.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

/**
 * ============================================================
 * RadiatedEffect
 *
 * - Damage scales with amplifier
 * - Amplifier decays over time AFTER leaving the source
 *
 * NOTE (1.21+):
 * We must reapply the effect using the REGISTERED
 * MobEffect (from ModEffects), NOT `this`.
 * ============================================================
 */
public class RadiatedEffect extends MobEffect {

    // How often amplifier decays (ticks)
    // 20 ticks = 1 second
    private static final int DECAY_INTERVAL = 60; // every 3 seconds

    public RadiatedEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {

        /* ==========================================================
           DAMAGE SCALING
           ========================================================== */
        float damage = 0.5F + (amplifier * 0.5F);
        entity.hurt(entity.damageSources().magic(), damage);

        /* ==========================================================
           AMPLIFIER DECAY
           ==========================================================
           If the fluid is no longer refreshing the effect,
           the amplifier slowly decreases.
         */
        if (amplifier > 0 && entity.tickCount % DECAY_INTERVAL == 0) {

            // Remove current radiation
            entity.removeEffect(ModEffects.RADIATED_EFFECT);

            // Reapply at a lower amplifier
            entity.addEffect(new MobEffectInstance(
                    ModEffects.RADIATED_EFFECT,
                    60,
                    amplifier - 1,
                    true,
                    false
            ));
        }

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        // Radiation damage happens every tick
        return true;
    }
}
