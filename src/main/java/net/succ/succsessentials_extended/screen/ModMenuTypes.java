package net.succ.succsessentials_extended.screen;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.screen.custom.AlloyForgerBlockMenu;
import net.succ.succsessentials_extended.screen.custom.CoalGeneratorMenu;
import net.succ.succsessentials_extended.screen.custom.ElectricFurnaceBlockMenu;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, Succsessentials_extended.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<AlloyForgerBlockMenu>> ALLOY_FORGER_MENU =
            registerMenuType("alloy_forger_menu", AlloyForgerBlockMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<CoalGeneratorMenu>> COAL_GENERATOR_MENU =
            registerMenuType("coal_generator_menu", CoalGeneratorMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<ElectricFurnaceBlockMenu>> ELECTRIC_FURNACE_MENU =
            registerMenuType("electric_furnace_menu", ElectricFurnaceBlockMenu::new);

    private static <T extends AbstractContainerMenu>DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name,
                                                                                                              IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void register (IEventBus eventBus) {
        MENUS.register(eventBus);
    }

}
