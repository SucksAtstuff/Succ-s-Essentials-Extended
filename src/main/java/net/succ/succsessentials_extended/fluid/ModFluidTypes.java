package net.succ.succsessentials_extended.fluid;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.succ.succsessentials_extended.Succsessentials_extended;
import org.joml.Vector3f;

import java.util.function.Supplier;

public class ModFluidTypes {
    public static final ResourceLocation NUCLEAR_WASTE_STILL = ResourceLocation.fromNamespaceAndPath(Succsessentials_extended.MOD_ID, "block/nuclear_waste_still");
    public static final ResourceLocation NUCLEAR_WASTE_FLOW = ResourceLocation.fromNamespaceAndPath(Succsessentials_extended.MOD_ID, "block/nuclear_waste_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = ResourceLocation.parse("block/water_overlay");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, Succsessentials_extended.MOD_ID);

    public static final Supplier<FluidType> NUCLEAR_WASTE_WATER_FLUID_TYPE =
            registerFluidType("nuclear_waste_water",
                    new BaseFluidType(
                            NUCLEAR_WASTE_STILL,
                            NUCLEAR_WASTE_FLOW,
                            WATER_OVERLAY_RL,
                            0xFF2CFA1F,
                            new Vector3f(
                                    44f / 255f,
                                    250f / 255f,
                                    31f / 255f
                            ),
                            FluidType.Properties.create()
                                    .canPushEntity(true)
                                    .canDrown(true)
                                    .canSwim(false)
                                    .supportsBoating(false)
                                    .density(2000)
                                    .viscosity(3000)
                                    .temperature(350)
                                    .lightLevel(6)
                    ));


    private static Supplier<FluidType> registerFluidType(String name, FluidType fluidType){
        return FLUID_TYPES.register(name, () -> fluidType);
    }

    public static void register(IEventBus eventBus){
        FLUID_TYPES.register(eventBus);
    }
}
