package net.succ.succsessentials_extended.effect;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succsessentials_extended.Succsessentials_extended;

public class ModEffects {
    public static final DeferredRegister<MobEffect>  MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, Succsessentials_extended.MOD_ID);

    public static void register (IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
