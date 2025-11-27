package net.succ.succs_essentials_extended.block.entity;


import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succs_essentials_extended.Succs_essentials_extended;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Succs_essentials_extended.MOD_ID);


    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }

}
