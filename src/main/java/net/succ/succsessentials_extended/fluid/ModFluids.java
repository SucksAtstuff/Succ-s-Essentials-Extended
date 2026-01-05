package net.succ.succsessentials_extended.fluid;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.block.ModBlocks;
import net.succ.succsessentials_extended.block.custom.NuclearWasteLiquidBlock;
import net.succ.succsessentials_extended.item.ModItems;

import java.util.function.Supplier;

public class ModFluids {

    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(BuiltInRegistries.FLUID, Succsessentials_extended.MOD_ID);

    /* ==========================================================
       SOURCE + FLOWING FLUIDS
       ========================================================== */
    public static final Supplier<FlowingFluid> SOURCE_NUCLEAR_WASTE_WATER = FLUIDS.register(
            "source_nuclear_waste_water",
            () -> new BaseFlowingFluid.Source(ModFluids.NUCLEAR_WASTE_WATER_PROPERTIES)
    );

    public static final Supplier<FlowingFluid> FLOWING_NUCLEAR_WASTE_WATER = FLUIDS.register(
            "flowing_nuclear_waste_water",
            () -> new BaseFlowingFluid.Flowing(ModFluids.NUCLEAR_WASTE_WATER_PROPERTIES)
    );

    /* ==========================================================
       FLUID BLOCK
       ==========================================================
       CHANGE: Use our custom NuclearWasteLiquidBlock so we can apply
       radiation effect via entityInside(...) without needing tick events.
     */
    public static final DeferredBlock<NuclearWasteLiquidBlock> NUCLEAR_WASTE_WATER_BLOCK =
            ModBlocks.BLOCKS.register(
                    "nuclear_waste_water_block",
                    () -> new NuclearWasteLiquidBlock(
                            ModFluids.SOURCE_NUCLEAR_WASTE_WATER,
                            BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable().lightLevel(state -> 8)
                    )
            );

    /* ==========================================================
       BUCKET
       ========================================================== */
    public static final DeferredItem<Item> NUCLEAR_WASTE_WATER_BUCKET =
            ModItems.ITEMS.registerItem(
                    "nuclear_waste_water_bucket",
                    properties -> new BucketItem(
                            ModFluids.SOURCE_NUCLEAR_WASTE_WATER.get(),
                            properties.craftRemainder(Items.BUCKET).stacksTo(1)
                    )
            );

    /* ==========================================================
       FLUID PROPERTIES
       ========================================================== */
    public static final BaseFlowingFluid.Properties NUCLEAR_WASTE_WATER_PROPERTIES =
            new BaseFlowingFluid.Properties(
                    ModFluidTypes.NUCLEAR_WASTE_WATER_FLUID_TYPE,
                    SOURCE_NUCLEAR_WASTE_WATER,
                    FLOWING_NUCLEAR_WASTE_WATER
            )
                    // How often the fluid updates (lower = faster spread)
                    .tickRate(5)

                    // How far it can search downhill to flow
                    .slopeFindDistance(4)

                    // How much the fluid level drops per block
                    .levelDecreasePerBlock(1)

                    // Block that represents the placed fluid
                    .block(NUCLEAR_WASTE_WATER_BLOCK)

                    // Bucket item for the fluid
                    .bucket(NUCLEAR_WASTE_WATER_BUCKET);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
