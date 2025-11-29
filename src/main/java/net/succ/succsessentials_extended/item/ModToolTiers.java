package net.succ.succsessentials_extended.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.succ.succsessentials_extended.util.ModTags;

public class ModToolTiers {

    public static final Tier CHROMIUM = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_CHROMIUM_TOOL,
            3500, 12.0F, 6.0F, 24, () -> Ingredient.of(ModItems.CHROMIUM_INGOT)
    );

}