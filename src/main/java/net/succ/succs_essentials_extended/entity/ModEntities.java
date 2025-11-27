package net.succ.succs_essentials_extended.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succs_essentials_extended.Succs_essentials_extended;

import java.util.List;
import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Succs_essentials_extended.MOD_ID);


    public static final List<Supplier<? extends EntityType<?>>> ENTRIES = List.of(

    );


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
