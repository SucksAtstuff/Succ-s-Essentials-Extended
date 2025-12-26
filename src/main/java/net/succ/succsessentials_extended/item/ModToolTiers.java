package net.succ.succsessentials_extended.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.succ.succsessentials_extended.util.ModTags;

public class ModToolTiers {

    // ---------- PURE METALS ----------

    public static final Tier TIN = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_TIN_TOOL,
            280,
            6.0F,
            2.0F,
            12,
            () -> Ingredient.of(ModItems.TIN_INGOT)
    );

    public static final Tier TITANIUM = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_TITANIUM_TOOL,
            1700,
            8.5F,
            3.5F,
            14,
            () -> Ingredient.of(ModItems.TITANIUM_INGOT)
    );

    public static final Tier COBALT = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_COBALT_TOOL,
            1850,
            8.75F,
            3.75F,
            15,
            () -> Ingredient.of(ModItems.COBALT_INGOT)
    );

    public static final Tier CHROMIUM = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_CHROMIUM_TOOL,
            2200,
            9.0F,
            4.5F,
            16,
            () -> Ingredient.of(ModItems.CHROMIUM_INGOT)
    );

    public static final Tier TUNGSTEN = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_TUNGSTEN_TOOL,
            2600,
            9.5F,
            5.0F,
            14,
            () -> Ingredient.of(ModItems.TUNGSTEN_INGOT)
    );

    // ---------- ALLOYS ----------

    public static final Tier BRONZE = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_BRONZE_TOOL,
            850,
            7.0F,
            3.0F,
            13,
            () -> Ingredient.of(ModItems.BRONZE_INGOT)
    );

    public static final Tier BRASS = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_BRASS_TOOL,
            700,
            7.25F,
            2.5F,
            15,
            () -> Ingredient.of(ModItems.BRASS_INGOT)
    );

    public static final Tier STEEL = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_STEEL_TOOL,
            1400,
            8.0F,
            3.5F,
            14,
            () -> Ingredient.of(ModItems.STEEL_INGOT)
    );

    public static final Tier INVAR = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_INVAR_TOOL,
            1600,
            7.75F,
            4.0F,
            11,
            () -> Ingredient.of(ModItems.INVAR_INGOT)
    );

    public static final Tier CONSTANTAN = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_CONSTANTAN_TOOL,
            1200,
            7.25F,
            3.5F,
            16,
            () -> Ingredient.of(ModItems.CONSTANTAN_INGOT)
    );

    public static final Tier ELECTRUM = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_ELECTRUM_TOOL,
            900,
            8.5F,
            2.5F,
            22,
            () -> Ingredient.of(ModItems.ELECTRUM_INGOT)
    );

    public static final Tier TITA_CHROME = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_TITA_CHROME_TOOL,
            3000,
            9.75F,
            5.5F,
            18,
            () -> Ingredient.of(ModItems.TITA_CHROME_INGOT)
    );
}
