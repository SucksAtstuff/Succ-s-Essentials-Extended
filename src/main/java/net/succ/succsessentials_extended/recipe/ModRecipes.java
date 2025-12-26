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

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, Succsessentials_extended.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, Succsessentials_extended.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<AlloyForgingRecipe>> ALLOY_FORGING_SERIALIZER =
            SERIALIZERS.register("alloy_forging", AlloyForgingRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<AlloyForgingRecipe>> ALLOY_FORGING_TYPE =
            TYPES.register("alloy_forging", () -> new RecipeType<AlloyForgingRecipe>() {
                @Override
                public String toString() {
                    return "alloy_forging";
                }
            });

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<InfusingRecipe>> INFUSING_SERIALIZER =
            SERIALIZERS.register("infusing", InfusingRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<InfusingRecipe>> INFUSING_TYPE =
            TYPES.register("infusing", () -> new RecipeType<InfusingRecipe>() {
                @Override
                public String toString() {
                    return "infusing";
                }
            });

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<PulverizingRecipe>> PULVERIZING_SERIALIZER =
            SERIALIZERS.register("pulverizing", PulverizingRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<PulverizingRecipe>> PULVERIZING_TYPE =
            TYPES.register("pulverizing", () -> new RecipeType<PulverizingRecipe>() {
                @Override
                public String toString() {
                    return "pulverizing";
                }
            });

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<HammerRecipe>> HAMMERING_SERIALIZER =
            SERIALIZERS.register("hammering", HammerRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<HammerRecipe>> HAMMERING_TYPE =
            TYPES.register("hammering", () -> new RecipeType<HammerRecipe>() {
                @Override
                public String toString() {
                    return "hammering";
                }
            });

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<WireCutterRecipe>> WIRE_CUTTING_SERIALIZER =
            SERIALIZERS.register("wire_cutting", WireCutterRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<WireCutterRecipe>> WIRE_CUTTING_TYPE =
            TYPES.register("wire_cutting", () -> new RecipeType<WireCutterRecipe>() {
                @Override
                public String toString() {
                    return "wire_cutting";
                }
            });

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
