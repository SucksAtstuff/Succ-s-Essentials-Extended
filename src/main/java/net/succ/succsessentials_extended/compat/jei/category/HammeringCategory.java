package net.succ.succsessentials_extended.compat.jei.category;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.item.ModItems;
import net.succ.succsessentials_extended.recipe.hammering.HammerRecipe;
import net.succ.succsessentials_extended.util.ModTags;
import org.jetbrains.annotations.Nullable;

public class HammeringCategory implements IRecipeCategory<HammerRecipe> {

    public static final RecipeType<HammerRecipe> RECIPE_TYPE =
            new RecipeType<>(
                    ResourceLocation.fromNamespaceAndPath(
                            Succsessentials_extended.MOD_ID,
                            "hammering"
                    ),
                    HammerRecipe.class
            );

    private final IDrawable background;
    private final IDrawable icon;

    public HammeringCategory(IGuiHelper helper) {
        background = helper.createBlankDrawable(150, 50);

        icon = helper.createDrawableIngredient(
                VanillaTypes.ITEM_STACK,
                new ItemStack(ModItems.HAMMER.get())
        );
    }

    @Override
    public RecipeType<HammerRecipe> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.succsessentials_extended.hammering");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(
            IRecipeLayoutBuilder builder,
            HammerRecipe recipe,
            IFocusGroup focuses
    ) {
        // Input ingot
        builder.addSlot(RecipeIngredientRole.INPUT, 20, 18)
                .addIngredients(recipe.input());

        // Hammer tool (tag-based)
        builder.addSlot(RecipeIngredientRole.INPUT, 50, 18)
                .addIngredients(Ingredient.of(ModTags.Items.HAMMERS));

        // Output plate
        builder.addSlot(RecipeIngredientRole.OUTPUT, 110, 18)
                .addItemStack(recipe.output());
    }

    @Override
    public void draw(
            HammerRecipe recipe,
            IRecipeSlotsView slots,
            GuiGraphics graphics,
            double mouseX,
            double mouseY
    ) {
        graphics.drawString(
                net.minecraft.client.Minecraft.getInstance().font,
                Component.literal("Durability Cost: " + recipe.durabilityCost()),
                6,
                38,
                0x404040,
                false
        );
    }
}
