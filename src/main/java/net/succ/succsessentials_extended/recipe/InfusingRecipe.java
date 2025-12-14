package net.succ.succsessentials_extended.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

/**
 * ============================================================
 * InfusingRecipe
 *
 * Ingot + Dust → Infused result
 * Slow and energy-hungry by default.
 * ============================================================
 */
public record InfusingRecipe(
        Ingredient inputA,
        Ingredient inputB,
        ItemStack output,
        int cookTime,
        int energyPerTick
) implements Recipe<InfusingRecipeInput> {

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputA);
        list.add(inputB);
        return list;
    }

    @Override
    public boolean matches(InfusingRecipeInput input, Level level) {
        if (level.isClientSide()) return false;

        ItemStack a = input.getItem(0);
        ItemStack b = input.getItem(1);

        return inputA.test(a) && inputB.test(b);
    }

    @Override
    public ItemStack assemble(InfusingRecipeInput input, HolderLookup.Provider registries) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.INFUSING_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.INFUSING_TYPE.get();
    }

    /* ================= SERIALIZER ================= */

    public static class Serializer implements RecipeSerializer<InfusingRecipe> {

        public static final MapCodec<InfusingRecipe> CODEC =
                RecordCodecBuilder.mapCodec(inst -> inst.group(
                        Ingredient.CODEC_NONEMPTY.fieldOf("input_a")
                                .forGetter(InfusingRecipe::inputA),
                        Ingredient.CODEC_NONEMPTY.fieldOf("input_b")
                                .forGetter(InfusingRecipe::inputB),
                        ItemStack.CODEC.fieldOf("result")
                                .forGetter(InfusingRecipe::output),
                        Codec.INT.optionalFieldOf("cook_time", 400)
                                .forGetter(InfusingRecipe::cookTime),
                        Codec.INT.optionalFieldOf("energy_per_tick", 80)
                                .forGetter(InfusingRecipe::energyPerTick)
                ).apply(inst, InfusingRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, InfusingRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, InfusingRecipe::inputA,
                        Ingredient.CONTENTS_STREAM_CODEC, InfusingRecipe::inputB,
                        ItemStack.STREAM_CODEC, InfusingRecipe::output,
                        ByteBufCodecs.INT, InfusingRecipe::cookTime,
                        ByteBufCodecs.INT, InfusingRecipe::energyPerTick,
                        InfusingRecipe::new
                );

        @Override
        public MapCodec<InfusingRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, InfusingRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
