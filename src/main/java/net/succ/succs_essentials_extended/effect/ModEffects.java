package net.succ.succs_essentials_extended.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succs_essentials_extended.Succs_essentials_extended;

public class ModEffects {
    public static final DeferredRegister<MobEffect>  MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, Succs_essentials_extended.MOD_ID);

    public static void register (IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
