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
import net.succ.succsessentials_extended.recipe.wirecutting.WireCutterRecipe;
import net.succ.succsessentials_extended.util.ModTags;
import org.jetbrains.annotations.Nullable;

public class WireCuttingCategory implements IRecipeCategory<WireCutterRecipe> {

    public static final RecipeType<WireCutterRecipe> RECIPE_TYPE =
            new RecipeType<>(
                    ResourceLocation.fromNamespaceAndPath(
                            Succsessentials_extended.MOD_ID,
                            "wire_cutting"
                    ),
                    WireCutterRecipe.class
            );

    private final IDrawable background;
    private final IDrawable icon;

    public WireCuttingCategory(IGuiHelper helper) {
        background = helper.createBlankDrawable(150, 50);

        icon = helper.createDrawableIngredient(
                VanillaTypes.ITEM_STACK,
                new ItemStack(ModItems.WIRE_CUTTER.get())
        );
    }

    @Override
    public RecipeType<WireCutterRecipe> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.succsessentials_extended.wire_cutting");
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
            WireCutterRecipe recipe,
            IFocusGroup focuses
    ) {
        // Input plate
        builder.addSlot(RecipeIngredientRole.INPUT, 20, 18)
                .addIngredients(recipe.input());

        // Wire cutter tool
        builder.addSlot(RecipeIngredientRole.INPUT, 50, 18)
                .addIngredients(Ingredient.of(ModTags.Items.WIRE_CUTTERS));

        // Output wire
        builder.addSlot(mezz.jei.api.recipe.RecipeIngredientRole.OUTPUT, 110, 18)
                .addItemStack(recipe.output());
    }

    @Override
    public void draw(
            WireCutterRecipe recipe,
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
