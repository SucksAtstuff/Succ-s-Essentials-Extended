package net.succ.succsessentials_extended.recipe.hammering;

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

public record HammerRecipe(
        Ingredient input,          // ingot
        Ingredient hammer,         // hammer tool (tag!)
        ItemStack output,          // plate
        int durabilityCost         // how much damage to apply
) implements Recipe<HammerRecipeInput> {

    @Override
    public boolean matches(HammerRecipeInput input, Level level) {
        if (level.isClientSide()) return false;

        return this.input.test(input.ingot())
                && this.hammer.test(input.hammer())
                && input.hammer().getDamageValue() < input.hammer().getMaxDamage();
    }

    @Override
    public ItemStack assemble(HammerRecipeInput input, HolderLookup.Provider registries) {
        return output.copy();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.of(
                Ingredient.EMPTY,
                input,
                hammer
        );
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.HAMMERING_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.HAMMERING_TYPE.get();
    }

    /* ===================== SERIALIZER ===================== */

    public static class Serializer implements RecipeSerializer<HammerRecipe> {

        public static final MapCodec<HammerRecipe> CODEC =
                RecordCodecBuilder.mapCodec(inst -> inst.group(
                        Ingredient.CODEC_NONEMPTY.fieldOf("input")
                                .forGetter(HammerRecipe::input),
                        Ingredient.CODEC_NONEMPTY.fieldOf("hammer")
                                .forGetter(HammerRecipe::hammer),
                        ItemStack.CODEC.fieldOf("result")
                                .forGetter(HammerRecipe::output),
                        Codec.INT.optionalFieldOf("durability_cost", 1)
                                .forGetter(HammerRecipe::durabilityCost)
                ).apply(inst, HammerRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, HammerRecipe> STREAM_CODEC =
                StreamCodec.of(
                        // WRITE
                        (buf, recipe) -> {
                            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.input());
                            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.hammer());
                            ItemStack.STREAM_CODEC.encode(buf, recipe.output());
                            buf.writeInt(recipe.durabilityCost());
                        },
                        // READ
                        buf -> {
                            Ingredient input =
                                    Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
                            Ingredient hammer =
                                    Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
                            ItemStack output =
                                    ItemStack.STREAM_CODEC.decode(buf);
                            int durabilityCost =
                                    buf.readInt();

                            return new HammerRecipe(
                                    input,
                                    hammer,
                                    output,
                                    durabilityCost
                            );
                        }
                );

        @Override
        public MapCodec<HammerRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, HammerRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
