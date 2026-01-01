package net.succ.succsessentials_extended.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.block.ModBlocks;
import net.succ.succsessentials_extended.painting.ModPaintings;

import java.util.function.Supplier;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Succsessentials_extended.MOD_ID);

    public static final Supplier<CreativeModeTab> SUCCSESSENTIALS_EXTENDED_TAB =
            CREATIVE_MODE_TAB.register("succsessentials_extended",
                    () -> CreativeModeTab.builder()

                            .icon(() -> new ItemStack(ModItems.CHROMIUM_INGOT.get()))
                            .title(Component.translatable("creativetab.succsessentials_extended"))

                            .displayItems((params, output) -> {

                                /* ===================================================== */
                                /*                     RAW MATERIALS                     */
                                /* ===================================================== */

                                output.accept(ModItems.RAW_CHROMIUM);
                                output.accept(ModItems.RAW_TITANIUM);
                                output.accept(ModItems.RAW_TIN);
                                output.accept(ModItems.RAW_TUNGSTEN);
                                output.accept(ModItems.RAW_COBALT);
                                output.accept(ModItems.RAW_OSMIUM);
                                output.accept(ModItems.RAW_ZINC);
                                output.accept(ModItems.RAW_SILVER);
                                output.accept(ModItems.RAW_NICKEL);
                                output.accept(ModItems.RAW_ALUMINIUM);

                                /* ===================================================== */
                                /*                         INGOTS                        */
                                /* ===================================================== */

                                output.accept(ModItems.CHROMIUM_INGOT);
                                output.accept(ModItems.TITANIUM_INGOT);
                                output.accept(ModItems.TIN_INGOT);
                                output.accept(ModItems.TUNGSTEN_INGOT);
                                output.accept(ModItems.COBALT_INGOT);
                                output.accept(ModItems.OSMIUM_INGOT);
                                output.accept(ModItems.ZINC_INGOT);
                                output.accept(ModItems.SILVER_INGOT);
                                output.accept(ModItems.NICKEL_INGOT);
                                output.accept(ModItems.ALUMINIUM_INGOT);

                                output.accept(ModItems.STEEL_INGOT);
                                output.accept(ModItems.BRONZE_INGOT);
                                output.accept(ModItems.BRASS_INGOT);
                                output.accept(ModItems.ELECTRUM_INGOT);
                                output.accept(ModItems.INVAR_INGOT);
                                output.accept(ModItems.CONSTANTAN_INGOT);
                                output.accept(ModItems.TITA_CHROME_INGOT);

                                /* ===================================================== */
                                /*                        NUGGETS                        */
                                /* ===================================================== */

                                output.accept(ModItems.CHROMIUM_NUGGET);
                                output.accept(ModItems.TITANIUM_NUGGET);
                                output.accept(ModItems.TIN_NUGGET);
                                output.accept(ModItems.TUNGSTEN_NUGGET);
                                output.accept(ModItems.COBALT_NUGGET);
                                output.accept(ModItems.OSMIUM_NUGGET);
                                output.accept(ModItems.ZINC_NUGGET);
                                output.accept(ModItems.SILVER_NUGGET);
                                output.accept(ModItems.NICKEL_NUGGET);
                                output.accept(ModItems.ALUMINIUM_NUGGET);

                                output.accept(ModItems.STEEL_NUGGET);
                                output.accept(ModItems.BRONZE_NUGGET);
                                output.accept(ModItems.BRASS_NUGGET);
                                output.accept(ModItems.ELECTRUM_NUGGET);
                                output.accept(ModItems.INVAR_NUGGET);
                                output.accept(ModItems.CONSTANTAN_NUGGET);
                                output.accept(ModItems.TITA_CHROME_NUGGET);

                                /* ===================================================== */
                                /*                          DUSTS                         */
                                /* ===================================================== */

                                output.accept(ModItems.CHROMIUM_DUST);
                                output.accept(ModItems.TITANIUM_DUST);
                                output.accept(ModItems.TIN_DUST);
                                output.accept(ModItems.TUNGSTEN_DUST);
                                output.accept(ModItems.COBALT_DUST);
                                output.accept(ModItems.OSMIUM_DUST);
                                output.accept(ModItems.ZINC_DUST);
                                output.accept(ModItems.SILVER_DUST);
                                output.accept(ModItems.NICKEL_DUST);
                                output.accept(ModItems.ALUMINIUM_DUST);

                                output.accept(ModItems.STEEL_DUST);
                                output.accept(ModItems.BRONZE_DUST);
                                output.accept(ModItems.BRASS_DUST);
                                output.accept(ModItems.ELECTRUM_DUST);
                                output.accept(ModItems.INVAR_DUST);
                                output.accept(ModItems.CONSTANTAN_DUST);
                                output.accept(ModItems.TITA_CHROME_DUST);
                                output.accept(ModItems.COAL_DUST);
                                output.accept(ModItems.COPPER_DUST);
                                output.accept(ModItems.IRON_DUST);
                                output.accept(ModItems.GOLD_DUST);
                                output.accept(ModItems.DIAMOND_DUST);

                                /* ===================================================== */
                                /*                          PLATES                       */
                                /* ===================================================== */

                                output.accept(ModItems.COPPER_PLATE);
                                output.accept(ModItems.IRON_PLATE);
                                output.accept(ModItems.GOLD_PLATE);
                                output.accept(ModItems.CHROMIUM_PLATE);
                                output.accept(ModItems.TITANIUM_PLATE);
                                output.accept(ModItems.TIN_PLATE);
                                output.accept(ModItems.TUNGSTEN_PLATE);
                                output.accept(ModItems.COBALT_PLATE);
                                output.accept(ModItems.OSMIUM_PLATE);
                                output.accept(ModItems.ZINC_PLATE);
                                output.accept(ModItems.SILVER_PLATE);
                                output.accept(ModItems.NICKEL_PLATE);
                                output.accept(ModItems.ALUMINIUM_PLATE);

                                output.accept(ModItems.STEEL_PLATE);
                                output.accept(ModItems.BRONZE_PLATE);
                                output.accept(ModItems.BRASS_PLATE);
                                output.accept(ModItems.ELECTRUM_PLATE);
                                output.accept(ModItems.INVAR_PLATE);
                                output.accept(ModItems.CONSTANTAN_PLATE);
                                output.accept(ModItems.TITA_CHROME_PLATE);

                                /* ===================================================== */
                                /*                        MACHINE ITEMS                  */
                                /* ===================================================== */

                                output.accept(ModItems.CIRCUIT_BOARD);
                                output.accept(ModItems.ADVANCED_CIRCUIT_BOARD);
                                output.accept(ModItems.COPPER_WIRE);
                                output.accept(ModItems.IRON_WIRE);
                                output.accept(ModItems.GOLD_WIRE);
                                output.accept(ModItems.ELECTRUM_WIRE);
                                output.accept(ModItems.EMPTY_SPOOL);
                                output.accept(ModItems.COPPER_SPOOL);
                                output.accept(ModItems.IRON_SPOOL);
                                output.accept(ModItems.GOLD_SPOOL);
                                output.accept(ModItems.ELECTRUM_SPOOL);
                                output.accept(ModItems.SPEED_MODULE);
                                output.accept(ModItems.EFFICIENCY_MODULE);
                                output.accept(ModItems.HAMMER);
                                output.accept(ModItems.WIRE_CUTTER);

                                /* ===================================================== */
                                /*                          ORES                          */
                                /* ===================================================== */

                                output.accept(ModBlocks.CHROMIUM_ORE);
                                output.accept(ModBlocks.DEEPSLATE_CHROMIUM_ORE);
                                output.accept(ModBlocks.TITANIUM_ORE);
                                output.accept(ModBlocks.DEEPSLATE_TITANIUM_ORE);
                                output.accept(ModBlocks.TIN_ORE);
                                output.accept(ModBlocks.DEEPSLATE_TIN_ORE);
                                output.accept(ModBlocks.TUNGSTEN_ORE);
                                output.accept(ModBlocks.DEEPSLATE_TUNGSTEN_ORE);
                                output.accept(ModBlocks.COBALT_ORE);
                                output.accept(ModBlocks.DEEPSLATE_COBALT_ORE);
                                output.accept(ModBlocks.OSMIUM_ORE);
                                output.accept(ModBlocks.DEEPSLATE_OSMIUM_ORE);
                                output.accept(ModBlocks.ZINC_ORE);
                                output.accept(ModBlocks.DEEPSLATE_ZINC_ORE);
                                output.accept(ModBlocks.SILVER_ORE);
                                output.accept(ModBlocks.DEEPSLATE_SILVER_ORE);
                                output.accept(ModBlocks.NICKEL_ORE);
                                output.accept(ModBlocks.DEEPSLATE_NICKEL_ORE);
                                output.accept(ModBlocks.ALUMINIUM_ORE);
                                output.accept(ModBlocks.DEEPSLATE_ALUMINIUM_ORE);

                                /* ===================================================== */
                                /*                       RAW BLOCKS                      */
                                /* ===================================================== */

                                output.accept(ModBlocks.RAW_CHROMIUM_BLOCK);
                                output.accept(ModBlocks.RAW_TITANIUM_BLOCK);
                                output.accept(ModBlocks.RAW_TIN_BLOCK);
                                output.accept(ModBlocks.RAW_TUNGSTEN_BLOCK);
                                output.accept(ModBlocks.RAW_COBALT_BLOCK);
                                output.accept(ModBlocks.RAW_OSMIUM_BLOCK);
                                output.accept(ModBlocks.RAW_ZINC_BLOCK);
                                output.accept(ModBlocks.RAW_SILVER_BLOCK);
                                output.accept(ModBlocks.RAW_NICKEL_BLOCK);
                                output.accept(ModBlocks.RAW_ALUMINIUM_BLOCK);

                                /* ===================================================== */
                                /*                     STORAGE BLOCKS                    */
                                /* ===================================================== */

                                output.accept(ModBlocks.CHROMIUM_BLOCK);
                                output.accept(ModBlocks.TITANIUM_BLOCK);
                                output.accept(ModBlocks.TIN_BLOCK);
                                output.accept(ModBlocks.TUNGSTEN_BLOCK);
                                output.accept(ModBlocks.COBALT_BLOCK);
                                output.accept(ModBlocks.OSMIUM_BLOCK);
                                output.accept(ModBlocks.ZINC_BLOCK);
                                output.accept(ModBlocks.SILVER_BLOCK);
                                output.accept(ModBlocks.NICKEL_BLOCK);
                                output.accept(ModBlocks.ALUMINIUM_BLOCK);

                                output.accept(ModBlocks.STEEL_BLOCK);
                                output.accept(ModBlocks.BRONZE_BLOCK);
                                output.accept(ModBlocks.BRASS_BLOCK);
                                output.accept(ModBlocks.ELECTRUM_BLOCK);
                                output.accept(ModBlocks.INVAR_BLOCK);
                                output.accept(ModBlocks.CONSTANTAN_BLOCK);
                                output.accept(ModBlocks.TITA_CHROME_BLOCK);

                                /* ===================================================== */
                                /*                       MACHINES                        */
                                /* ===================================================== */

                                output.accept(ModBlocks.ALLOY_FORGER);
                                output.accept(ModBlocks.ELECTRIC_FURNACE);
                                output.accept(ModBlocks.INFUSER);
                                output.accept(ModBlocks.PULVERIZER);
                                output.accept(ModBlocks.WIRE_DRAWER);
                                output.accept(ModBlocks.ROLLING_MILL);
                                output.accept(ModBlocks.COAL_GENERATOR);
                                output.accept(ModBlocks.BIO_FUEL_GENERATOR);
                                output.accept(ModBlocks.PANEL_BLOCK);

                                /* ===================================================== */
                                /*                    TOOLS & ARMOR                     */
                                /* ===================================================== */

                                output.accept(ModItems.CHROMIUM_SWORD.get());
                                output.accept(ModItems.CHROMIUM_PICKAXE.get());
                                output.accept(ModItems.CHROMIUM_AXE.get());
                                output.accept(ModItems.CHROMIUM_SHOVEL.get());
                                output.accept(ModItems.CHROMIUM_HOE.get());
                                output.accept(ModItems.CHROMIUM_HAMMER.get());
                                output.accept(ModItems.CHROMIUM_PAXEL.get());
                                output.accept(ModItems.CHROMIUM_REINFORCED_HAMMER);

                                output.accept(ModItems.CHROMIUM_HELMET.get());
                                output.accept(ModItems.CHROMIUM_CHESTPLATE.get());
                                output.accept(ModItems.CHROMIUM_LEGGINGS.get());
                                output.accept(ModItems.CHROMIUM_BOOTS.get());

                                /* ===================================================== */
                                /*                          MISC                          */
                                /* ===================================================== */

                                output.accept(ModItems.BIOMASS);
                                output.accept(ModItems.BIOMASS_PELLET);
                            })

                            .build()
            );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
