package net.succ.succsessentials_extended.block.entity;


import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.block.ModBlocks;
import net.succ.succsessentials_extended.block.entity.custom.*;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Succsessentials_extended.MOD_ID);

    public static final Supplier<BlockEntityType<AlloyForgerBlockEntity>> ALLOY_FORGER_BE =
            BLOCK_ENTITIES.register("alloy_forger_be", () -> BlockEntityType.Builder.of(
                    AlloyForgerBlockEntity::new, ModBlocks.ALLOY_FORGER.get()).build(null));

    public static final Supplier<BlockEntityType<CoalGeneratorBlockEntity>> COAL_GENERATOR_BE =
            BLOCK_ENTITIES.register("coal_generator_be", () -> BlockEntityType.Builder.of(
                    CoalGeneratorBlockEntity::new, ModBlocks.COAL_GENERATOR.get()).build(null));

    public static final Supplier<BlockEntityType<BiofuelGeneratorBlockEntity>> BIO_FUEL_GENERATOR_BE =
            BLOCK_ENTITIES.register("bio_fuel_generator_be", () -> BlockEntityType.Builder.of(
                    BiofuelGeneratorBlockEntity::new, ModBlocks.BIO_FUEL_GENERATOR.get()).build(null));

    public static final Supplier<BlockEntityType<ElectricFurnaceBlockEntity>> ELECTRIC_FURNACE_BE =
            BLOCK_ENTITIES.register("electric_furnace_be", () -> BlockEntityType.Builder.of(
                    ElectricFurnaceBlockEntity::new, ModBlocks.ELECTRIC_FURNACE.get()).build(null));

    public static final Supplier<BlockEntityType<InfuserBlockEntity>> INFUSER_BE =
            BLOCK_ENTITIES.register("infuser_be", () -> BlockEntityType.Builder.of(
                    InfuserBlockEntity::new, ModBlocks.INFUSER.get()).build(null));

    public static final Supplier<BlockEntityType<PulverizerBlockEntity>> PULVERIZER_BE =
            BLOCK_ENTITIES.register("pulverizer_be", () -> BlockEntityType.Builder.of(
                    PulverizerBlockEntity::new, ModBlocks.PULVERIZER.get()).build(null));


    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }

}
