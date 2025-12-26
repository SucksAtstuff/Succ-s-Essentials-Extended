package net.succ.succsessentials_extended.recipe.hammering;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record HammerRecipeInput(
        ItemStack ingot,
        ItemStack hammer
) implements RecipeInput {

    @Override
    public ItemStack getItem(int index) {
        return switch (index) {
            case 0 -> ingot;
            case 1 -> hammer;
            default -> ItemStack.EMPTY;
        };
    }

    @Override
    public int size() {
        return 2;
    }
}
