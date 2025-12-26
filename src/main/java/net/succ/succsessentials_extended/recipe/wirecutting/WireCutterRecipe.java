package net.succ.succsessentials_extended.recipe.wirecutting;

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

/**
 * Custom crafting recipe for wire cutting.
 *
 * Turns plates into wires using a wire cutter tool.
 * The tool is NOT consumed, but durability is reduced
 * elsewhere (crafting result slot).
 */
public record WireCutterRecipe(
        Ingredient input,          // Plate input
        Ingredient cutter,         // Wire cutter tool (tag-based)
        ItemStack output,          // Wire result
        int durabilityCost         // Durability damage applied to cutter
) implements Recipe<WireCutterRecipeInput> {

    /**
     * Checks if the recipe matches the current crafting input.
     */
    @Override
    public boolean matches(WireCutterRecipeInput input, Level level) {
        if (level.isClientSide()) {
            return false;
        }

        ItemStack plate = input.plate();
        ItemStack cutterStack = input.cutter();

        // Plate must match input ingredient
        // Cutter must match tool ingredient
        // Cutter must not be broken
        return this.input.test(plate)
                && this.cutter.test(cutterStack)
                && cutterStack.isDamageableItem()
                && cutterStack.getDamageValue() < cutterStack.getMaxDamage();
    }

    /**
     * Creates the recipe output.
     * Actual durability damage is applied elsewhere.
     */
    @Override
    public ItemStack assemble(WireCutterRecipeInput input, HolderLookup.Provider registries) {
        return output.copy();
    }

    /**
     * Used by JEI and the recipe book.
     * Only ingredient *types* matter here.
     */
    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.of(
                Ingredient.EMPTY,
                input,
                cutter
        );
    }

    /**
     * Allows use in any crafting grid that has at least 2 slots.
     */
    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    /**
     * Displayed output in recipe viewers.
     */
    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.WIRE_CUTTING_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.WIRE_CUTTING_TYPE.get();
    }

    /* =====================================================================
     *                           SERIALIZER
     * ===================================================================== */

    public static class Serializer implements RecipeSerializer<WireCutterRecipe> {

        public static final MapCodec<WireCutterRecipe> CODEC =
                RecordCodecBuilder.mapCodec(inst -> inst.group(
                        Ingredient.CODEC_NONEMPTY.fieldOf("input")
                                .forGetter(WireCutterRecipe::input),
                        Ingredient.CODEC_NONEMPTY.fieldOf("cutter")
                                .forGetter(WireCutterRecipe::cutter),
                        ItemStack.CODEC.fieldOf("result")
                                .forGetter(WireCutterRecipe::output),
                        Codec.INT.optionalFieldOf("durability_cost", 1)
                                .forGetter(WireCutterRecipe::durabilityCost)
                ).apply(inst, WireCutterRecipe::new));

        /**
         * Used for network syncing.
         */
        public static final StreamCodec<RegistryFriendlyByteBuf, WireCutterRecipe> STREAM_CODEC =
                StreamCodec.of(
                        // WRITE
                        (buf, recipe) -> {
                            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.input());
                            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.cutter());
                            ItemStack.STREAM_CODEC.encode(buf, recipe.output());
                            buf.writeInt(recipe.durabilityCost());
                        },
                        // READ
                        buf -> {
                            Ingredient input =
                                    Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
                            Ingredient cutter =
                                    Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
                            ItemStack output =
                                    ItemStack.STREAM_CODEC.decode(buf);
                            int durabilityCost =
                                    buf.readInt();

                            return new WireCutterRecipe(
                                    input,
                                    cutter,
                                    output,
                                    durabilityCost
                            );
                        }
                );

        @Override
        public MapCodec<WireCutterRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, WireCutterRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
