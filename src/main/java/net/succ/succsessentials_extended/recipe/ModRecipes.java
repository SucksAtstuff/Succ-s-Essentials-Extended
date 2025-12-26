package net.succ.succsessentials_extended.recipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.recipe.alloyforging.AlloyForgingRecipe;
import net.succ.succsessentials_extended.recipe.hammering.HammerRecipe;
import net.succ.succsessentials_extended.recipe.infusing.InfusingRecipe;
import net.succ.succsessentials_extended.recipe.pulverizing.PulverizingRecipe;
import net.succ.succsessentials_extended.recipe.wirecutting.WireCutterRecipe;
import net.succ.succsessentials_extended.recipe.rollingmill.RollingMillRecipe;
import net.succ.succsessentials_extended.recipe.wiredrawing.WireDrawingRecipe;

public class ModRecipes {

    /* ==========================================================
       REGISTERS
       ========================================================== */

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, Succsessentials_extended.MOD_ID);

    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, Succsessentials_extended.MOD_ID);

    /* ==========================================================
       ALLOY FORGING
       ========================================================== */

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<AlloyForgingRecipe>> ALLOY_FORGING_SERIALIZER =
            SERIALIZERS.register("alloy_forging", AlloyForgingRecipe.Serializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<AlloyForgingRecipe>> ALLOY_FORGING_TYPE =
            TYPES.register("alloy_forging", () -> new RecipeType<>() {
                @Override
                public String toString() {
                    return "alloy_forging";
                }
            });

    /* ==========================================================
       INFUSING
       ========================================================== */

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<InfusingRecipe>> INFUSING_SERIALIZER =
            SERIALIZERS.register("infusing", InfusingRecipe.Serializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<InfusingRecipe>> INFUSING_TYPE =
            TYPES.register("infusing", () -> new RecipeType<>() {
                @Override
                public String toString() {
                    return "infusing";
                }
            });

    /* ==========================================================
       PULVERIZING
       ========================================================== */

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<PulverizingRecipe>> PULVERIZING_SERIALIZER =
            SERIALIZERS.register("pulverizing", PulverizingRecipe.Serializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<PulverizingRecipe>> PULVERIZING_TYPE =
            TYPES.register("pulverizing", () -> new RecipeType<>() {
                @Override
                public String toString() {
                    return "pulverizing";
                }
            });

    /* ==========================================================
       HAMMERING
       ========================================================== */

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<HammerRecipe>> HAMMERING_SERIALIZER =
            SERIALIZERS.register("hammering", HammerRecipe.Serializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<HammerRecipe>> HAMMERING_TYPE =
            TYPES.register("hammering", () -> new RecipeType<>() {
                @Override
                public String toString() {
                    return "hammering";
                }
            });

    /* ==========================================================
       WIRE CUTTING
       ========================================================== */

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<WireCutterRecipe>> WIRE_CUTTING_SERIALIZER =
            SERIALIZERS.register("wire_cutting", WireCutterRecipe.Serializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<WireCutterRecipe>> WIRE_CUTTING_TYPE =
            TYPES.register("wire_cutting", () -> new RecipeType<>() {
                @Override
                public String toString() {
                    return "wire_cutting";
                }
            });

    /* ==========================================================
       ROLLING MILL (NEW)
       ========================================================== */

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<RollingMillRecipe>> ROLLING_MILL_SERIALIZER =
            SERIALIZERS.register("rolling_mill", RollingMillRecipe.Serializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<RollingMillRecipe>> ROLLING_MILL_TYPE =
            TYPES.register("rolling_mill", () -> new RecipeType<>() {
                @Override
                public String toString() {
                    return "rolling_mill";
                }
            });

    /* ==========================================================
       WIRE DRAWING (NEW)
       ========================================================== */

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<WireDrawingRecipe>> WIRE_DRAWING_SERIALIZER =
            SERIALIZERS.register("wire_drawing", WireDrawingRecipe.Serializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<WireDrawingRecipe>> WIRE_DRAWING_TYPE =
            TYPES.register("wire_drawing", () -> new RecipeType<>() {
                @Override
                public String toString() {
                    return "wire_drawing";
                }
            });

    /* ==========================================================
       REGISTER
       ========================================================== */

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
