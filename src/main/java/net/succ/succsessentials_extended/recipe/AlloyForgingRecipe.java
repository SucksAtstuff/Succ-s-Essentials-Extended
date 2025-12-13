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

public record AlloyForgingRecipe(
        Ingredient inputA,   // first metal
        Ingredient inputB,   // second metal
        ItemStack output,     // alloy result
        int cookTime
) implements Recipe<AlloyForgingRecipeInput> {

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();

        // Order matters for JEI / recipe display
        list.add(inputA);
        list.add(inputB);

        return list;
    }

    @Override
    public boolean matches(AlloyForgingRecipeInput input, Level level) {
        if (level.isClientSide()) {
            return false;
        }

        ItemStack stackA = input.getItem(0);
        ItemStack stackB = input.getItem(1);

        // Case 1: A in slot 0, B in slot 1
        boolean normalOrder =
                inputA.test(stackA) && inputB.test(stackB);

        // Case 2: A in slot 1, B in slot 0
        boolean swappedOrder =
                inputA.test(stackB) && inputB.test(stackA);

        return normalOrder || swappedOrder;
    }


    @Override
    public ItemStack assemble(AlloyForgingRecipeInput input, HolderLookup.Provider registries) {
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
        return ModRecipes.ALLOY_FORGING_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.ALLOY_FORGING_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<AlloyForgingRecipe> {

        public static final MapCodec<AlloyForgingRecipe> CODEC =
                RecordCodecBuilder.mapCodec(inst -> inst.group(
                        Ingredient.CODEC_NONEMPTY.fieldOf("input_a")
                                .forGetter(AlloyForgingRecipe::inputA),
                        Ingredient.CODEC_NONEMPTY.fieldOf("input_b")
                                .forGetter(AlloyForgingRecipe::inputB),
                        ItemStack.CODEC.fieldOf("result")
                                .forGetter(AlloyForgingRecipe::output),
                        Codec.INT.optionalFieldOf("cook_time", 200)
                                .forGetter(AlloyForgingRecipe::cookTime)
                ).apply(inst, AlloyForgingRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, AlloyForgingRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, AlloyForgingRecipe::inputA,
                        Ingredient.CONTENTS_STREAM_CODEC, AlloyForgingRecipe::inputB,
                        ItemStack.STREAM_CODEC, AlloyForgingRecipe::output,
                        ByteBufCodecs.INT, AlloyForgingRecipe::cookTime,
                        AlloyForgingRecipe::new
                );


        @Override
        public MapCodec<AlloyForgingRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, AlloyForgingRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
