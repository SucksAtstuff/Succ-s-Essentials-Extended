package net.succ.succsessentials_extended.recipe.pulverizing;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

/**
 * ============================================================
 * PulverizingRecipeInput
 *
 * Recipe input wrapper for the Pulverizer.
 *
 * The pulverizer has exactly ONE input slot.
 * This mirrors AlloyForgingRecipeInput but simplified.
 *
 * ============================================================
 */
public record PulverizingRecipeInput(
        ItemStack input
) implements RecipeInput {

    @Override
    public ItemStack getItem(int index) {
        return index == 0 ? input : ItemStack.EMPTY;
    }

    @Override
    public int size() {
        return 1;
    }
}
