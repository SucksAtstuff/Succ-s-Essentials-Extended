package net.succ.succsessentials_extended.compat.jei.category;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.block.ModBlocks;
import net.succ.succsessentials_extended.compat.jei.layout.JeiRenderHelper;
import net.succ.succsessentials_extended.compat.jei.layout.MachineLayout;
import net.succ.succsessentials_extended.compat.jei.layout.MachineLayouts;
import org.jetbrains.annotations.Nullable;

public class ElectricFurnaceRecipeCategory implements IRecipeCategory<SmeltingRecipe> {

    /** IMPORTANT: still vanilla SMELTING */


    public static final RecipeType<SmeltingRecipe> RECIPE_TYPE =
            new RecipeType<>(
                    ResourceLocation.fromNamespaceAndPath(
                            Succsessentials_extended.MOD_ID,
                            "electric_furnace_smelting"
                    ),
                    SmeltingRecipe.class
            );




    private final IDrawable background;
    private final IDrawable icon;

    public ElectricFurnaceRecipeCategory(IGuiHelper helper) {
        background = helper.createDrawable(
                ResourceLocation.fromNamespaceAndPath(
                        Succsessentials_extended.MOD_ID,
                        "textures/gui/container/electric_furnace.png"
                ),
                0, 0, 176, 85
        );

        icon = helper.createDrawableIngredient(
                VanillaTypes.ITEM_STACK,
                new ItemStack(ModBlocks.ELECTRIC_FURNACE.get())
        );
    }

    @Override
    public RecipeType<SmeltingRecipe> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.succsessentials_extended.electric_furnace");
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
            SmeltingRecipe recipe,
            IFocusGroup focuses
    ) {
        MachineLayout layout = MachineLayouts.ELECTRIC_FURNACE;

        // Vanilla smelting input
        JeiRenderHelper.addInputSlot(
                builder,
                layout.inputX(),
                layout.inputY(),
                recipe.getIngredients().get(0)
        );

        // Vanilla smelting output
        JeiRenderHelper.addOutputSlot(
                builder,
                layout.outputX(),
                layout.outputY(),
                recipe.getResultItem(null),
                /* energy */ getEnergyForRecipe(recipe)
        );
    }

    @Override
    public void draw(
            SmeltingRecipe recipe,
            IRecipeSlotsView slots,
            GuiGraphics graphics,
            double mouseX,
            double mouseY
    ) {
        MachineLayout layout = MachineLayouts.ELECTRIC_FURNACE;
        int energy = getEnergyForRecipe(recipe);

        JeiRenderHelper.drawEnergyBar(
                graphics,
                energy,
                layout.energyBarX(),
                layout.energyBarY()
        );

        if (JeiRenderHelper.isMouseOverEnergyBar(
                mouseX,
                mouseY,
                layout.energyBarX(),
                layout.energyBarY()
        )) {
            JeiRenderHelper.drawEnergyTooltip(
                    graphics,
                    mouseX,
                    mouseY,
                    energy
            );
        }

        JeiRenderHelper.drawEnergyText(
                graphics,
                energy,
                6,
                72
        );
    }

    /**
     * Converts vanilla cook time into FE cost.
     * Adjust formula however you want.
     */
    private static int getEnergyForRecipe(SmeltingRecipe recipe) {
        int cookTime = recipe.getCookingTime(); // usually 200
        int fePerTick = 20;                     // example value
        return cookTime * fePerTick;
    }
}
