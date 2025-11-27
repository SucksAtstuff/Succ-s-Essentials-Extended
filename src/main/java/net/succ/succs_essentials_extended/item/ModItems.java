package net.succ.succs_essentials_extended.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succs_essentials_extended.Succs_essentials_extended;
import net.succ.succs_essentials_extended.block.ModBlocks;
import net.succ.succs_essentials_extended.effect.ModEffects;
import net.succ.succs_essentials_extended.entity.ModEntities;
import net.succ.succs_essentials_extended.item.ModArmorMaterials;
import net.succ.succs_essentials_extended.item.ModFoodProperties;
import net.succ.succs_essentials_extended.item.ModToolTiers;
import net.succ.succs_essentials_extended.sound.ModSounds;

import java.util.Collections;
import java.util.Map;

public class ModItems {

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(Succs_essentials_extended.MOD_ID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
