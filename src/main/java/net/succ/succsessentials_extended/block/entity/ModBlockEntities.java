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
            DeferredRegister.create(
                    BuiltInRegistries.BLOCK_ENTITY_TYPE,
                    Succsessentials_extended.MOD_ID
            );

    public static final Supplier<BlockEntityType<AlloyForgerBlockEntity>> ALLOY_FORGER_BE =
            BLOCK_ENTITIES.register("alloy_forger_be", () ->
                    BlockEntityType.Builder.of(
                            AlloyForgerBlockEntity::new,
                            ModBlocks.ALLOY_FORGER.get()
                    ).build(null)
            );

    public static final Supplier<BlockEntityType<RollingMillBlockEntity>> ROLLING_MILL_BE =
            BLOCK_ENTITIES.register("rolling_mill_be", () ->
                    BlockEntityType.Builder.of(
                            RollingMillBlockEntity::new,
                            ModBlocks.ROLLING_MILL.get()
                    ).build(null)
            );

    public static final Supplier<BlockEntityType<WireDrawerBlockEntity>> WIRE_DRAWER_BE =
            BLOCK_ENTITIES.register("wire_drawer_be", () ->
                    BlockEntityType.Builder.of(
                            WireDrawerBlockEntity::new,
                            ModBlocks.WIRE_DRAWER.get()
                    ).build(null)
            );


    public static final Supplier<BlockEntityType<CoalGeneratorBlockEntity>> COAL_GENERATOR_BE =
            BLOCK_ENTITIES.register("coal_generator_be", () ->
                    BlockEntityType.Builder.of(
                            CoalGeneratorBlockEntity::new,
                            ModBlocks.COAL_GENERATOR.get()
                    ).build(null)
            );

    public static final Supplier<BlockEntityType<BiofuelGeneratorBlockEntity>> BIO_FUEL_GENERATOR_BE =
            BLOCK_ENTITIES.register("bio_fuel_generator_be", () ->
                    BlockEntityType.Builder.of(
                            BiofuelGeneratorBlockEntity::new,
                            ModBlocks.BIO_FUEL_GENERATOR.get()
                    ).build(null)
            );

    public static final Supplier<BlockEntityType<ElectricFurnaceBlockEntity>> ELECTRIC_FURNACE_BE =
            BLOCK_ENTITIES.register("electric_furnace_be", () ->
                    BlockEntityType.Builder.of(
                            ElectricFurnaceBlockEntity::new,
                            ModBlocks.ELECTRIC_FURNACE.get()
                    ).build(null)
            );

    public static final Supplier<BlockEntityType<InfuserBlockEntity>> INFUSER_BE =
            BLOCK_ENTITIES.register("infuser_be", () ->
                    BlockEntityType.Builder.of(
                            InfuserBlockEntity::new,
                            ModBlocks.INFUSER.get()
                    ).build(null)
            );

    public static final Supplier<BlockEntityType<PulverizerBlockEntity>> PULVERIZER_BE =
            BLOCK_ENTITIES.register("pulverizer_be", () ->
                    BlockEntityType.Builder.of(
                            PulverizerBlockEntity::new,
                            ModBlocks.PULVERIZER.get()
                    ).build(null)
            );


    /* ==========================================================
       NUCLEAR REACTOR (MULTIBLOCK)
       ========================================================== */

    public static final Supplier<BlockEntityType<NuclearReactorControllerBlockEntity>>
            NUCLEAR_REACTOR_CONTROLLER_BE =
            BLOCK_ENTITIES.register("nuclear_reactor_controller_be", () ->
                    BlockEntityType.Builder.of(
                            NuclearReactorControllerBlockEntity::new,
                            ModBlocks.NUCLEAR_REACTOR_CONTROLLER.get()
                    ).build(null)
            );

    public static final Supplier<BlockEntityType<NuclearReactorInputBlockEntity>>
            NUCLEAR_REACTOR_INPUT_BE =
            BLOCK_ENTITIES.register("nuclear_reactor_input_be", () ->
                    BlockEntityType.Builder.of(
                            NuclearReactorInputBlockEntity::new,
                            ModBlocks.NUCLEAR_REACTOR_INPUT.get()
                    ).build(null)
            );

    public static final Supplier<BlockEntityType<NuclearReactorOutputBlockEntity>>
            NUCLEAR_REACTOR_OUTPUT_BE =
            BLOCK_ENTITIES.register("nuclear_reactor_output_be", () ->
                    BlockEntityType.Builder.of(
                            NuclearReactorOutputBlockEntity::new,
                            ModBlocks.NUCLEAR_REACTOR_OUTPUT.get()
                    ).build(null)
            );

    /* ==========================================================
       REGISTER
       ========================================================== */

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
