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
import net.succ.succsessentials_extended.recipe.pulverizing.PulverizingRecipe;
import org.jetbrains.annotations.Nullable;

public class PulverizingRecipeCategory implements IRecipeCategory<PulverizingRecipe> {

    public static final RecipeType<PulverizingRecipe> RECIPE_TYPE =
            new RecipeType<>(
                    ResourceLocation.fromNamespaceAndPath(
                            Succsessentials_extended.MOD_ID,
                            "pulverizing"
                    ),
                    PulverizingRecipe.class
            );

    private final IDrawable background;
    private final IDrawable icon;

    public PulverizingRecipeCategory(IGuiHelper helper) {
        background = helper.createDrawable(
                ResourceLocation.fromNamespaceAndPath(
                        Succsessentials_extended.MOD_ID,
                        "textures/gui/container/pulverizer.png"
                ),
                0, 0, 176, 85
        );

        icon = helper.createDrawableIngredient(
                VanillaTypes.ITEM_STACK,
                new ItemStack(ModBlocks.PULVERIZER.get())
        );
    }

    @Override
    public RecipeType<PulverizingRecipe> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.succsessentials_extended.pulverizing");
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
            PulverizingRecipe recipe,
            IFocusGroup focuses
    ) {
        MachineLayout layout = MachineLayouts.PULVERIZER;

        JeiRenderHelper.addInputSlot(
                builder,
                layout.inputX(),
                layout.inputY(),
                recipe.input()
        );

        JeiRenderHelper.addOutputSlot(
                builder,
                layout.outputX(),
                layout.outputY(),
                recipe.output(),
                recipe.getTotalEnergy()
        );

        if (!recipe.byproduct().isEmpty()) {
            JeiRenderHelper.addOutputSlot(
                    builder,
                    layout.byproductX(),
                    layout.byproductY(),
                    recipe.byproduct(),
                    recipe.getTotalEnergy()
            );
        }
    }

    @Override
    public void draw(
            PulverizingRecipe recipe,
            IRecipeSlotsView slots,
            GuiGraphics graphics,
            double mouseX,
            double mouseY
    ) {
        MachineLayout layout = MachineLayouts.PULVERIZER;

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
    }
}
