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
import net.succ.succsessentials_extended.recipe.infusing.InfusingRecipe;
import org.jetbrains.annotations.Nullable;

public class InfusingRecipeCategory implements IRecipeCategory<InfusingRecipe> {

    public static final RecipeType<InfusingRecipe> RECIPE_TYPE =
            new RecipeType<>(
                    ResourceLocation.fromNamespaceAndPath(
                            Succsessentials_extended.MOD_ID,
                            "infusing"
                    ),
                    InfusingRecipe.class
            );

    private final IDrawable background;
    private final IDrawable icon;

    public InfusingRecipeCategory(IGuiHelper helper) {
        background = helper.createDrawable(
                ResourceLocation.fromNamespaceAndPath(
                        Succsessentials_extended.MOD_ID,
                        "textures/gui/container/infuser.png"
                ),
                0, 0, 176, 85
        );

        icon = helper.createDrawableIngredient(
                VanillaTypes.ITEM_STACK,
                new ItemStack(ModBlocks.INFUSER.get())
        );
    }

    @Override
    public RecipeType<InfusingRecipe> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.succsessentials_extended.infusing");
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
            InfusingRecipe recipe,
            IFocusGroup focuses
    ) {
        MachineLayout layout = MachineLayouts.INFUSER;

        JeiRenderHelper.addInputSlot(
                builder,
                layout.inputX(),
                layout.inputY(),
                recipe.getIngredients().get(0)
        );

        JeiRenderHelper.addInputSlot(
                builder,
                layout.secondaryInputX(),
                layout.secondaryInputY(),
                recipe.getIngredients().get(1)
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
            InfusingRecipe recipe,
            IRecipeSlotsView slots,
            GuiGraphics graphics,
            double mouseX,
            double mouseY
    ) {
        MachineLayout layout = MachineLayouts.INFUSER;

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
