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

    /* ====================================================================== */
    /*                          REGISTRATION SETUP                             */
    /* ====================================================================== */

    // Deferred register for all creative mode tabs belonging to this mod
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Succsessentials_extended.MOD_ID);

    /* ====================================================================== */
    /*                          MATERIALS TAB                                  */
    /*  Everything the player crafts WITH: ingots, nuggets, dusts, alloys     */
    /* ====================================================================== */

    public static final Supplier<CreativeModeTab> SUCCSESSENTIALS_EXTENDED_TAB_MATERIALS =
            CREATIVE_MODE_TAB.register("succsessentials_extended_tab_materials",
                    () -> CreativeModeTab.builder()

                            // Icon chosen to clearly represent "materials"
                            .icon(() -> new ItemStack(ModItems.CHROMIUM_INGOT.get()))

                            // Translation key for lang files
                            .title(Component.translatable(
                                    "creativetab.succsessentials_extended.materials.tab"
                            ))

                            // Manual ordering to keep things readable
                            .displayItems((params, output) -> {

                                /* ---------- RAW MATERIALS ---------- */
                                output.accept(ModItems.RAW_CHROMIUM);
                                output.accept(ModItems.RAW_TITANIUM);

                                /* ---------- INGOTS ---------- */
                                output.accept(ModItems.CHROMIUM_INGOT);
                                output.accept(ModItems.TITANIUM_INGOT);
                                output.accept(ModItems.STEEL_INGOT);
                                output.accept(ModItems.TITA_CHROME_INGOT);

                                /* ---------- NUGGETS ---------- */
                                output.accept(ModItems.CHROMIUM_NUGGET);
                                output.accept(ModItems.TITANIUM_NUGGET);
                                output.accept(ModItems.STEEL_NUGGET);
                                output.accept(ModItems.TITA_CHROME_NUGGET);

                                /* ---------- DUSTS ---------- */
                                output.accept(ModItems.TITANIUM_DUST);
                                output.accept(ModItems.COAL_DUST);
                            })

                            .build()
            );

    /* ====================================================================== */
    /*                        BLOCKS & MACHINES TAB                             */
    /*  Everything the player PLACES: ores, storage blocks, machines           */
    /* ====================================================================== */

    public static final Supplier<CreativeModeTab> SUCCSESSENTIALS_EXTENDED_TAB_BLOCKS =
            CREATIVE_MODE_TAB.register("succsessentials_extended_tab_blocks",
                    () -> CreativeModeTab.builder()

                            // Machines are a strong visual indicator for this tab
                            .icon(() -> new ItemStack(ModBlocks.ALLOY_FORGER.get()))

                            // Make this appear directly after the materials tab
                            .withTabsBefore(ResourceLocation.fromNamespaceAndPath(
                                    Succsessentials_extended.MOD_ID,
                                    "succsessentials_extended_tab_materials"
                            ))

                            .title(Component.translatable(
                                    "creativetab.succsessentials_extended.blocks.tab"
                            ))

                            .displayItems((params, output) -> {

                                /* ---------- ORES ---------- */
                                output.accept(ModBlocks.CHROMIUM_ORE);
                                output.accept(ModBlocks.DEEPSLATE_CHROMIUM_ORE);
                                output.accept(ModBlocks.TITANIUM_ORE);
                                output.accept(ModBlocks.DEEPSLATE_TITANIUM_ORE);

                                /* ---------- STORAGE BLOCKS ---------- */
                                output.accept(ModBlocks.CHROMIUM_BLOCK);
                                output.accept(ModBlocks.TITANIUM_BLOCK);
                                output.accept(ModBlocks.STEEL_BLOCK);
                                output.accept(ModBlocks.TITA_CHROME_BLOCK);

                                output.accept(ModBlocks.RAW_CHROMIUM_BLOCK);
                                output.accept(ModBlocks.RAW_TITANIUM_BLOCK);

                                /* ---------- MACHINES ---------- */
                                output.accept(ModBlocks.ALLOY_FORGER);
                                output.accept(ModBlocks.ELECTRIC_FURNACE);
                                output.accept(ModBlocks.INFUSER);
                                output.accept(ModBlocks.PULVERIZER);
                                output.accept(ModBlocks.COAL_GENERATOR);

                                /* ---------- MISC / TECH BLOCKS ---------- */
                                output.accept(ModBlocks.PANEL_BLOCK);
                            })

                            .build()
            );

    /* ====================================================================== */
    /*                          EQUIPMENT TAB                                   */
    /*  Everything the player USES or WEARS: tools, weapons, armor             */
    /* ====================================================================== */

    public static final Supplier<CreativeModeTab> SUCCSESSENTIALS_EXTENDED_TAB_EQUIPMENT =
            CREATIVE_MODE_TAB.register("succsessentials_extended_tab_equipment",
                    () -> CreativeModeTab.builder()

                            // Weapon icon instantly communicates "gear"
                            .icon(() -> new ItemStack(ModItems.CHROMIUM_SWORD.get()))

                            // Place after the blocks tab
                            .withTabsBefore(ResourceLocation.fromNamespaceAndPath(
                                    Succsessentials_extended.MOD_ID,
                                    "succsessentials_extended_tab_blocks"
                            ))

                            .title(Component.translatable(
                                    "creativetab.succsessentials_extended.equipment.tab"
                            ))

                            .displayItems((params, output) -> {

                                /* ---------- TOOLS & WEAPONS ---------- */
                                output.accept(ModItems.CHROMIUM_SWORD.get());
                                output.accept(ModItems.CHROMIUM_PICKAXE.get());
                                output.accept(ModItems.CHROMIUM_AXE.get());
                                output.accept(ModItems.CHROMIUM_SHOVEL.get());
                                output.accept(ModItems.CHROMIUM_HOE.get());
                                output.accept(ModItems.CHROMIUM_HAMMER.get());
                                output.accept(ModItems.CHROMIUM_PAXEL.get());
                                output.accept(ModItems.CHROMIUM_REINFORCED_HAMMER);

                                /* ---------- ARMOR ---------- */
                                output.accept(ModItems.CHROMIUM_HELMET.get());
                                output.accept(ModItems.CHROMIUM_CHESTPLATE.get());
                                output.accept(ModItems.CHROMIUM_LEGGINGS.get());
                                output.accept(ModItems.CHROMIUM_BOOTS.get());
                            })

                            .build()
            );

    /* ====================================================================== */
    /*                              REGISTER                                    */
    /* ====================================================================== */

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
