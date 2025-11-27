package net.succ.succsessentials_extended.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.block.ModBlocks;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    // Create a DeferredRegister for CreativeModeTabs, using the mod ID
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Succsessentials_extended.MOD_ID);

    public static final Supplier<CreativeModeTab> SUCCS_ESSENTIALS_EXTENDED_TAB_INGOTS = CREATIVE_MODE_TAB.register("succs_essentials_extended_tab_ingots",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.CHROMIUM_INGOT.get()))
                    .title(Component.translatable("creativetab.succs_essentials_extended.ingots.tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.CHROMIUM_INGOT);
                        output.accept(ModItems.RAW_CHROMIUM);
                    })
                    .build());

    public static final Supplier<CreativeModeTab> SUCCS_ESSENTIALS_EXTENDED_TAB_BLOCKS = CREATIVE_MODE_TAB.register("succs_essentials_extended_tab_blocks",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBlocks.CHROMIUM_ORE.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(Succsessentials_extended.MOD_ID, "succs_essentials_extended_tab_ingots"))
                    .title(Component.translatable("creativetab.succs_essentials_extended.blocks.tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.CHROMIUM_ORE);
                        output.accept(ModBlocks.DEEPSLATE_CHROMIUM_ORE);
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
