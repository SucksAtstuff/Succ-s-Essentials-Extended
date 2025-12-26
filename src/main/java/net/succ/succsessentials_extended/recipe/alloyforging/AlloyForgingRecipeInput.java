package net.succ.succsessentials_extended.recipe.alloyforging;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record AlloyForgingRecipeInput(
        ItemStack inputA,   // metal slot 1
        ItemStack inputB    // metal slot 2
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
