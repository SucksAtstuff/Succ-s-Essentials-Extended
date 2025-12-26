package net.succ.succsessentials_extended.recipe.infusing;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

/**
 * ============================================================
 * InfusingRecipeInput
 *
 * Input container for Infuser recipes.
 * Slot 0: Ingot
 * Slot 1: Dust
 * ============================================================
 */
public record InfusingRecipeInput(
        ItemStack inputA,
        ItemStack inputB
) implements RecipeInput {

    @Override
    public ItemStack getItem(int index) {
        return switch (index) {
            case 0 -> inputA;
            case 1 -> inputB;
            default -> ItemStack.EMPTY;
        };
    }

    @Override
    public int size() {
        return 2;
    }
}
