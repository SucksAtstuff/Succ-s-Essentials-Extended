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
        Ingredient inputA,     // first metal type
        int countA,            // required amount of A
        Ingredient inputB,     // second metal type
        int countB,            // required amount of B
        ItemStack output,      // alloy result (count = output amount)
        int cookTime,
        int energyPerTick
) implements Recipe<AlloyForgingRecipeInput> {

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();

        // JEI only cares about ingredient types, not counts
        list.add(inputA);
        list.add(inputB);

        return list;
    }

    @Override
    public boolean matches(AlloyForgingRecipeInput input, Level level) {
        if (level.isClientSide()) {
            return false;
        }

        ItemStack stack0 = input.getItem(0);
        ItemStack stack1 = input.getItem(1);

        // Normal order: A in slot 0, B in slot 1
        boolean normal =
                inputA.test(stack0) &&
                        stack0.getCount() >= countA &&
                        inputB.test(stack1) &&
                        stack1.getCount() >= countB;

        // Swapped order: A in slot 1, B in slot 0
        boolean swapped =
                inputA.test(stack1) &&
                        stack1.getCount() >= countA &&
                        inputB.test(stack0) &&
                        stack0.getCount() >= countB;

        return normal || swapped;
    }

    public int getTotalEnergy() {
        return energyPerTick * cookTime;
    }

    @Override
    public ItemStack assemble(AlloyForgingRecipeInput input, HolderLookup.Provider registries) {
        // Output amount already encoded in ItemStack
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
                        Codec.INT.optionalFieldOf("count_a", 1)
                                .forGetter(AlloyForgingRecipe::countA),
                        Ingredient.CODEC_NONEMPTY.fieldOf("input_b")
                                .forGetter(AlloyForgingRecipe::inputB),
                        Codec.INT.optionalFieldOf("count_b", 1)
                                .forGetter(AlloyForgingRecipe::countB),
                        ItemStack.CODEC.fieldOf("result")
                                .forGetter(AlloyForgingRecipe::output),
                        Codec.INT.optionalFieldOf("cook_time", 200)
                                .forGetter(AlloyForgingRecipe::cookTime),
                        Codec.INT.optionalFieldOf("energy_per_tick", 20)
                                .forGetter(AlloyForgingRecipe::energyPerTick)
                ).apply(inst, AlloyForgingRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, AlloyForgingRecipe> STREAM_CODEC =
                StreamCodec.of(
                        // ================= WRITE =================
                        (buf, recipe) -> {
                            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.inputA());
                            buf.writeInt(recipe.countA());
                            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.inputB());
                            buf.writeInt(recipe.countB());
                            ItemStack.STREAM_CODEC.encode(buf, recipe.output());
                            buf.writeInt(recipe.cookTime());
                            buf.writeInt(recipe.energyPerTick());
                        },

                        // ================= READ =================
                        buf -> {
                            Ingredient inputA =
                                    Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
                            int countA = buf.readInt();

                            Ingredient inputB =
                                    Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
                            int countB = buf.readInt();

                            ItemStack output =
                                    ItemStack.STREAM_CODEC.decode(buf);

                            int cookTime = buf.readInt();
                            int energyPerTick = buf.readInt();

                            return new AlloyForgingRecipe(
                                    inputA,
                                    countA,
                                    inputB,
                                    countB,
                                    output,
                                    cookTime,
                                    energyPerTick
                            );
                        }
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
