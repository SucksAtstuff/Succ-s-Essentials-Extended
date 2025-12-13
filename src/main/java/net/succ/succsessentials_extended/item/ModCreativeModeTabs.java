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

    public static final Supplier<CreativeModeTab> SUCCSESSENTIALS_EXTENDED_TAB_INGOTS = CREATIVE_MODE_TAB.register("succsessentials_extended_tab_ingots",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.CHROMIUM_INGOT.get()))
                    .title(Component.translatable("creativetab.succsessentials_extended.ingots.tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.CHROMIUM_INGOT);
                        output.accept(ModItems.RAW_CHROMIUM);
                    })
                    .build());

    public static final Supplier<CreativeModeTab> SUCCSESSENTIALS_EXTENDED_TAB_BLOCKS = CREATIVE_MODE_TAB.register("succsessentials_extended_tab_blocks",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBlocks.CHROMIUM_ORE.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(Succsessentials_extended.MOD_ID, "succsessentials_extended_tab_ingots"))
                    .title(Component.translatable("creativetab.succsessentials_extended.blocks.tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.CHROMIUM_ORE);
                        output.accept(ModBlocks.DEEPSLATE_CHROMIUM_ORE);
                        output.accept(ModBlocks.CHROMIUM_BLOCK);
                        output.accept(ModBlocks.ALLOY_FORGER);
                    })
                    .build());

    public static final Supplier<CreativeModeTab> SUCCSESSENTIALS_EXTENDED_TAB_TOOLS = CREATIVE_MODE_TAB.register("succsessentials_extended_tab_tools",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.CHROMIUM_SWORD.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(Succsessentials_extended.MOD_ID, "succsessentials_extended_tab_blocks"))
                    .title(Component.translatable("creativetab.succsessentials.tools.tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.CHROMIUM_SWORD.get());
                        output.accept(ModItems.CHROMIUM_PICKAXE.get());
                        output.accept(ModItems.CHROMIUM_AXE.get());
                        output.accept(ModItems.CHROMIUM_SHOVEL.get());
                        output.accept(ModItems.CHROMIUM_HOE.get());
                        output.accept(ModItems.CHROMIUM_HAMMER.get());
                        output.accept(ModItems.CHROMIUM_PAXEL.get());
                        output.accept(ModItems.CHROMIUM_REINFORCED_HAMMER);
                    })
                    .build());

    public static final Supplier<CreativeModeTab> SUCCSESSENTIALS_EXTENDED_TAB_ARMOR = CREATIVE_MODE_TAB.register("succsessentials_extended_tab_armor",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.CHROMIUM_HELMET.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(Succsessentials_extended.MOD_ID, "succsessentials_extended_tab_tools"))
                    .title(Component.translatable("creativetab.succsessentials_extended.armor.tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.CHROMIUM_HELMET.get());
                        output.accept(ModItems.CHROMIUM_CHESTPLATE.get());
                        output.accept(ModItems.CHROMIUM_LEGGINGS.get());
                        output.accept(ModItems.CHROMIUM_BOOTS.get());
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
