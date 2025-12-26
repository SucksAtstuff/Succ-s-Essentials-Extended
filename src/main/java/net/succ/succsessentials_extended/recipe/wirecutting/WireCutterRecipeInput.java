package net.succ.succsessentials_extended.recipe.wirecutting;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

/**
 * Recipe input wrapper for wire cutting.
 *
 * Slot 0 → plate
 * Slot 1 → wire cutter
 */
public record WireCutterRecipeInput(
        ItemStack plate,
        ItemStack cutter
) implements RecipeInput {

    /**
     * Returns the item in the given logical slot.
     */
    @Override
    public ItemStack getItem(int index) {
        return switch (index) {
            case 0 -> plate;
            case 1 -> cutter;
            default -> ItemStack.EMPTY;
        };
    }

    /**
     * Number of logical inputs.
     */
    @Override
    public int size() {
        return 2;
    }
}
