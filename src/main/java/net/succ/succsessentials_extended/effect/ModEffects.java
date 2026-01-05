package net.succ.succsessentials_extended.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsmod.effect.CorrosionEffect;

public class ModEffects {
    public static final DeferredRegister<MobEffect>  MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, Succsessentials_extended.MOD_ID);

    public static final Holder<MobEffect> RADIATED_EFFECT = MOB_EFFECTS.register("radiated",
            () -> new RadiatedEffect(MobEffectCategory.HARMFUL, 0x66FF33));

    public static void register (IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
