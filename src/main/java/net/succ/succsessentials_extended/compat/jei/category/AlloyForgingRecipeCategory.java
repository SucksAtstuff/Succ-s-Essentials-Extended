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
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.block.ModBlocks;
import net.succ.succsessentials_extended.compat.jei.layout.JeiRenderHelper;
import net.succ.succsessentials_extended.compat.jei.layout.MachineLayout;
import net.succ.succsessentials_extended.compat.jei.layout.MachineLayouts;
import net.succ.succsessentials_extended.recipe.alloyforging.AlloyForgingRecipe;
import org.jetbrains.annotations.Nullable;

public class AlloyForgingRecipeCategory implements IRecipeCategory<AlloyForgingRecipe> {

    public static final RecipeType<AlloyForgingRecipe> RECIPE_TYPE =
            new RecipeType<>(
                    ResourceLocation.fromNamespaceAndPath(
                            Succsessentials_extended.MOD_ID,
                            "alloy_forging"
                    ),
                    AlloyForgingRecipe.class
            );

    private final IDrawable background;
    private final IDrawable icon;

    public AlloyForgingRecipeCategory(IGuiHelper helper) {
        background = helper.createDrawable(
                ResourceLocation.fromNamespaceAndPath(
                        Succsessentials_extended.MOD_ID,
                        "textures/gui/container/alloy_forger.png"
                ),
                0, 0, 176, 85
        );

        icon = helper.createDrawableIngredient(
                VanillaTypes.ITEM_STACK,
                new ItemStack(ModBlocks.ALLOY_FORGER.get())
        );
    }

    @Override
    public RecipeType<AlloyForgingRecipe> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.succsessentials_extended.alloying");
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
            AlloyForgingRecipe recipe,
            IFocusGroup focuses
    ) {
        MachineLayout layout = MachineLayouts.ALLOY_FORGER;

        JeiRenderHelper.addInputSlot(
                builder,
                layout.inputX(),
                layout.inputY(),
                recipe.inputA()
        );

        JeiRenderHelper.addInputSlot(
                builder,
                layout.secondaryInputX(),
                layout.secondaryInputY(),
                recipe.inputB()
        );

        JeiRenderHelper.addOutputSlot(
                builder,
                layout.outputX(),
                layout.outputY(),
                recipe.output(),
                recipe.getTotalEnergy()
        );
    }

    @Override
    public void draw(
            AlloyForgingRecipe recipe,
            IRecipeSlotsView slots,
            GuiGraphics graphics,
            double mouseX,
            double mouseY
    ) {
        MachineLayout layout = MachineLayouts.ALLOY_FORGER;

        JeiRenderHelper.drawEnergyBar(
                graphics,
                recipe.getTotalEnergy(),
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
                    recipe.getTotalEnergy()
            );
        }

        JeiRenderHelper.drawEnergyText(
                graphics,
                recipe.getTotalEnergy(),
                6,
                72
        );

        JeiRenderHelper.drawStackCountOverlay(
                graphics,
                recipe.countA(),
                layout.inputX(),
                layout.inputY()
        );

        JeiRenderHelper.drawStackCountOverlay(
                graphics,
                recipe.countB(),
                layout.secondaryInputX(),
                layout.secondaryInputY()
        );
    }
}
