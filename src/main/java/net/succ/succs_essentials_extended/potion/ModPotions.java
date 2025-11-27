package net.succ.succs_essentials_extended.potion;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succs_essentials_extended.Succs_essentials_extended;
import net.succ.succs_essentials_extended.effect.ModEffects;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(BuiltInRegistries.POTION, Succs_essentials_extended.MOD_ID);

    public static void register (IEventBus eventBus)
    {
        POTIONS.register(eventBus);
    }

}
