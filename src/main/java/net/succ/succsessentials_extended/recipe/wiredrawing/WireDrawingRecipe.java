package net.succ.succsessentials_extended.recipe.wiredrawing;

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

public record WireDrawingRecipe(
        Ingredient input,
        int inputCount,
        ItemStack output,
        int cookTime,
        int energyPerTick
) implements Recipe<WireDrawingRecipeInput> {

    /* ================= INGREDIENTS ================= */

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.of(Ingredient.EMPTY, input);
    }

    @Override
    public boolean matches(WireDrawingRecipeInput input, Level level) {
        return this.input.test(input.getItem(0))
                && input.getItem(0).getCount() >= inputCount;
    }

    /* ================= RESULT ================= */

    @Override
    public ItemStack assemble(WireDrawingRecipeInput input, HolderLookup.Provider registries) {
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
        return ModRecipes.WIRE_DRAWING_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.WIRE_DRAWING_TYPE.get();
    }

    /* ================= SERIALIZER ================= */

    public static class Serializer implements RecipeSerializer<WireDrawingRecipe> {

        public static final MapCodec<WireDrawingRecipe> CODEC =
                RecordCodecBuilder.mapCodec(inst -> inst.group(
                        Ingredient.CODEC_NONEMPTY.fieldOf("input")
                                .forGetter(WireDrawingRecipe::input),
                        Codec.INT.optionalFieldOf("input_count", 1)
                                .forGetter(WireDrawingRecipe::inputCount),
                        ItemStack.CODEC.fieldOf("result")
                                .forGetter(WireDrawingRecipe::output),
                        Codec.INT.optionalFieldOf("cook_time", 200)
                                .forGetter(WireDrawingRecipe::cookTime),
                        Codec.INT.optionalFieldOf("energy_per_tick", 20)
                                .forGetter(WireDrawingRecipe::energyPerTick)
                ).apply(inst, WireDrawingRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, WireDrawingRecipe> STREAM_CODEC =
                StreamCodec.of(
                        (buf, recipe) -> {
                            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.input());
                            buf.writeInt(recipe.inputCount());
                            ItemStack.STREAM_CODEC.encode(buf, recipe.output());
                            buf.writeInt(recipe.cookTime());
                            buf.writeInt(recipe.energyPerTick());
                        },
                        buf -> new WireDrawingRecipe(
                                Ingredient.CONTENTS_STREAM_CODEC.decode(buf),
                                buf.readInt(),
                                ItemStack.STREAM_CODEC.decode(buf),
                                buf.readInt(),
                                buf.readInt()
                        )
                );

        @Override
        public MapCodec<WireDrawingRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, WireDrawingRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
