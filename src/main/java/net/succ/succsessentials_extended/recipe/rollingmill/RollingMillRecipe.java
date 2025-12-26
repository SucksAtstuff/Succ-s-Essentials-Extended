package net.succ.succsessentials_extended.recipe.rollingmill;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.succ.succsessentials_extended.recipe.ModRecipes;

public record RollingMillRecipe(
        Ingredient input,
        int inputCount,
        ItemStack output,
        int cookTime,
        int energyPerTick
) implements Recipe<RollingMillRecipeInput> {

    /* ================= INGREDIENTS ================= */

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.of(Ingredient.EMPTY, input);
    }

    @Override
    public boolean matches(RollingMillRecipeInput input, Level level) {
        return this.input.test(input.getItem(0))
                && input.getItem(0).getCount() >= inputCount;
    }

    /* ================= RESULT ================= */

    @Override
    public ItemStack assemble(RollingMillRecipeInput input, HolderLookup.Provider registries) {
        return output.copy();
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return output;
    }

    /* ================= MACHINE DATA ================= */

    public int getTotalEnergy() {
        return cookTime * energyPerTick;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.ROLLING_MILL_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.ROLLING_MILL_TYPE.get();
    }

    /* ================= SERIALIZER ================= */

    public static class Serializer implements RecipeSerializer<RollingMillRecipe> {

        public static final MapCodec<RollingMillRecipe> CODEC =
                RecordCodecBuilder.mapCodec(inst -> inst.group(
                        Ingredient.CODEC_NONEMPTY.fieldOf("input")
                                .forGetter(RollingMillRecipe::input),
                        Codec.INT.optionalFieldOf("input_count", 1)
                                .forGetter(RollingMillRecipe::inputCount),
                        ItemStack.CODEC.fieldOf("result")
                                .forGetter(RollingMillRecipe::output),
                        Codec.INT.optionalFieldOf("cook_time", 200)
                                .forGetter(RollingMillRecipe::cookTime),
                        Codec.INT.optionalFieldOf("energy_per_tick", 20)
                                .forGetter(RollingMillRecipe::energyPerTick)
                ).apply(inst, RollingMillRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, RollingMillRecipe> STREAM_CODEC =
                StreamCodec.of(
                        (buf, recipe) -> {
                            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.input());
                            buf.writeInt(recipe.inputCount());
                            ItemStack.STREAM_CODEC.encode(buf, recipe.output());
                            buf.writeInt(recipe.cookTime());
                            buf.writeInt(recipe.energyPerTick());
                        },
                        buf -> new RollingMillRecipe(
                                Ingredient.CONTENTS_STREAM_CODEC.decode(buf),
                                buf.readInt(),
                                ItemStack.STREAM_CODEC.decode(buf),
                                buf.readInt(),
                                buf.readInt()
                        )
                );

        @Override
        public MapCodec<RollingMillRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, RollingMillRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
