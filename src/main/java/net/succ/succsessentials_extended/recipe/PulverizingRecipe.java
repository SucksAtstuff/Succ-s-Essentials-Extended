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
 * PulverizingRecipe
 *
 * Recipe definition for the Pulverizer.
 *
 * Converts:
 *  - Input item → primary output (dust)
 *  - Optional secondary output (byproduct)
 *
 * ============================================================
 */
public record PulverizingRecipe(
        Ingredient input,          // input item
        ItemStack output,          // primary output (dust)  <-- MUST NOT be EMPTY
        ItemStack byproduct,       // secondary output (may be EMPTY)
        int cookTime,
        int energyPerTick

) implements Recipe<PulverizingRecipeInput> {

    /* ================= INGREDIENTS ================= */

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(input);
        return list;
    }

    /* ================= MATCHING ================= */

    @Override
    public boolean matches(PulverizingRecipeInput recipeInput, Level level) {
        if (level.isClientSide()) {
            return false;
        }

        return input.test(recipeInput.getItem(0));
    }

    /* ================= ENERGY ================= */

    public int getTotalEnergy() {
        return energyPerTick * cookTime;
    }

    /* ================= ASSEMBLY ================= */

    @Override
    public ItemStack assemble(PulverizingRecipeInput input,
                              HolderLookup.Provider registries) {
        return output.copy();
    }

    /**
     * Explicit accessor for the secondary output.
     * Not part of vanilla Recipe, so exposed manually.
     */
    public ItemStack getByproduct() {
        return byproduct.copy();
    }

    /* ================= VANILLA OVERRIDES ================= */

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
        return ModRecipes.PULVERIZING_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.PULVERIZING_TYPE.get();
    }

    /* ============================================================
       SERIALIZER
       ============================================================ */

    public static class Serializer implements RecipeSerializer<PulverizingRecipe> {

        public static final MapCodec<PulverizingRecipe> CODEC =
                RecordCodecBuilder.mapCodec(inst -> inst.group(
                        Ingredient.CODEC_NONEMPTY.fieldOf("input")
                                .forGetter(PulverizingRecipe::input),

                        // NOTE: result MUST be non-empty in json or you'll still crash on sync
                        ItemStack.CODEC.fieldOf("result")
                                .forGetter(PulverizingRecipe::output),

                        // Optional byproduct in JSON is fine
                        ItemStack.CODEC.optionalFieldOf("byproduct", ItemStack.EMPTY)
                                .forGetter(PulverizingRecipe::byproduct),

                        Codec.INT.optionalFieldOf("cook_time", 200)
                                .forGetter(PulverizingRecipe::cookTime),

                        Codec.INT.optionalFieldOf("energy_per_tick", 20)
                                .forGetter(PulverizingRecipe::energyPerTick)
                ).apply(inst, PulverizingRecipe::new));

        /**
         * ============================================================
         * STREAM_CODEC (NETWORK SYNC)
         *
         * IMPORTANT:
         * - ItemStack.STREAM_CODEC cannot encode ItemStack.EMPTY in 1.21+
         * - StreamCodec.composite ALWAYS encodes every field (cannot "skip")
         * - Therefore we must manually encode/decode the optional byproduct:
         *     write bool -> only write stack if true
         * ============================================================
         */
        public static final StreamCodec<RegistryFriendlyByteBuf, PulverizingRecipe> STREAM_CODEC =
                new StreamCodec<>() {

                    @Override
                    public PulverizingRecipe decode(RegistryFriendlyByteBuf buf) {
                        // Decode required fields
                        Ingredient input = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
                        ItemStack output = ItemStack.STREAM_CODEC.decode(buf);

                        // Decode optional byproduct
                        boolean hasByproduct = ByteBufCodecs.BOOL.decode(buf);
                        ItemStack byproduct = hasByproduct
                                ? ItemStack.STREAM_CODEC.decode(buf)
                                : ItemStack.EMPTY;

                        int cookTime = ByteBufCodecs.INT.decode(buf);
                        int energyPerTick = ByteBufCodecs.INT.decode(buf);

                        return new PulverizingRecipe(input, output, byproduct, cookTime, energyPerTick);
                    }

                    @Override
                    public void encode(RegistryFriendlyByteBuf buf, PulverizingRecipe recipe) {
                        // Encode required fields
                        Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.input());
                        ItemStack.STREAM_CODEC.encode(buf, recipe.output());

                        // Encode optional byproduct safely
                        boolean hasByproduct = !recipe.byproduct().isEmpty();
                        ByteBufCodecs.BOOL.encode(buf, hasByproduct);

                        if (hasByproduct) {
                            ItemStack.STREAM_CODEC.encode(buf, recipe.byproduct());
                        }

                        ByteBufCodecs.INT.encode(buf, recipe.cookTime());
                        ByteBufCodecs.INT.encode(buf, recipe.energyPerTick());
                    }
                };

        @Override
        public MapCodec<PulverizingRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, PulverizingRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
