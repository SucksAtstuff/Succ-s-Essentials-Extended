package net.succ.succsessentials_extended.item;

import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsmod.item.custom.HammerItem;
import net.succ.succsmod.item.custom.ReinforcedHammerItem;
import net.succ.succsmod.item.custom.PaxelItem;


public class ModItems {

    /* =====================================================================
     *                           REGISTRY
     * ===================================================================== */

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(Succsessentials_extended.MOD_ID);

    /* =====================================================================
     *                           STONE MATERIALS
     * ===================================================================== */

    /* ---------- COPPER ---------- */
    public static final DeferredItem<Item> COPPER_DUST = ITEMS.register("copper_dust",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COPPER_PLATE = ITEMS.register("copper_plate",
            () -> new Item(new Item.Properties()));

    /* ---------- IRON ---------- */
    public static final DeferredItem<Item> IRON_DUST = ITEMS.register("iron_dust",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> IRON_PLATE = ITEMS.register("iron_plate",
            () -> new Item(new Item.Properties()));

    /* ---------- GOLD ---------- */
    public static final DeferredItem<Item> GOLD_DUST = ITEMS.register("gold_dust",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GOLD_PLATE = ITEMS.register("gold_plate",
            () -> new Item(new Item.Properties()));

    /* ---------- DIAMOND ---------- */
    public static final DeferredItem<Item> DIAMOND_DUST = ITEMS.register("diamond_dust",
            () -> new Item(new Item.Properties()));

    /* ---------- CHROMIUM ---------- */
    public static final DeferredItem<Item> RAW_CHROMIUM = ITEMS.register("raw_chromium",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CHROMIUM_INGOT = ITEMS.register("chromium_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CHROMIUM_NUGGET = ITEMS.register("chromium_nugget",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CHROMIUM_DUST = ITEMS.register("chromium_dust",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CHROMIUM_PLATE = ITEMS.register("chromium_plate",
            () -> new Item(new Item.Properties()));

    /* ---------- TITANIUM ---------- */
    public static final DeferredItem<Item> RAW_TITANIUM = ITEMS.register("raw_titanium",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TITANIUM_NUGGET = ITEMS.register("titanium_nugget",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TITANIUM_DUST = ITEMS.register("titanium_dust",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TITANIUM_PLATE = ITEMS.register("titanium_plate",
            () -> new Item(new Item.Properties()));

    /* ---------- TIN ---------- */
    public static final DeferredItem<Item> RAW_TIN = ITEMS.register("raw_tin",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TIN_INGOT = ITEMS.register("tin_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TIN_NUGGET = ITEMS.register("tin_nugget",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TIN_DUST = ITEMS.register("tin_dust",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TIN_PLATE = ITEMS.register("tin_plate",
            () -> new Item(new Item.Properties()));

    /* ---------- TUNGSTEN ---------- */
    public static final DeferredItem<Item> RAW_TUNGSTEN = ITEMS.register("raw_tungsten",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TUNGSTEN_INGOT = ITEMS.register("tungsten_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TUNGSTEN_NUGGET = ITEMS.register("tungsten_nugget",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TUNGSTEN_DUST = ITEMS.register("tungsten_dust",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TUNGSTEN_PLATE = ITEMS.register("tungsten_plate",
            () -> new Item(new Item.Properties()));

    /* ---------- COBALT ---------- */
    public static final DeferredItem<Item> RAW_COBALT = ITEMS.register("raw_cobalt",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COBALT_INGOT = ITEMS.register("cobalt_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COBALT_NUGGET = ITEMS.register("cobalt_nugget",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COBALT_DUST = ITEMS.register("cobalt_dust",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COBALT_PLATE = ITEMS.register("cobalt_plate",
            () -> new Item(new Item.Properties()));

    /* ---------- OSMIUM ---------- */
    public static final DeferredItem<Item> RAW_OSMIUM = ITEMS.register("raw_osmium",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> OSMIUM_INGOT = ITEMS.register("osmium_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> OSMIUM_NUGGET = ITEMS.register("osmium_nugget",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> OSMIUM_DUST = ITEMS.register("osmium_dust",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> OSMIUM_PLATE = ITEMS.register("osmium_plate",
            () -> new Item(new Item.Properties()));

    /* ---------- ZINC ---------- */
    public static final DeferredItem<Item> RAW_ZINC = ITEMS.register("raw_zinc",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ZINC_INGOT = ITEMS.register("zinc_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ZINC_NUGGET = ITEMS.register("zinc_nugget",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ZINC_DUST = ITEMS.register("zinc_dust",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ZINC_PLATE = ITEMS.register("zinc_plate",
            () -> new Item(new Item.Properties()));

    /* ---------- SILVER ---------- */
    public static final DeferredItem<Item> RAW_SILVER = ITEMS.register("raw_silver",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SILVER_INGOT = ITEMS.register("silver_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SILVER_NUGGET = ITEMS.register("silver_nugget",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SILVER_DUST = ITEMS.register("silver_dust",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SILVER_PLATE = ITEMS.register("silver_plate",
            () -> new Item(new Item.Properties()));

    /* ---------- NICKEL ---------- */
    public static final DeferredItem<Item> RAW_NICKEL = ITEMS.register("raw_nickel",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> NICKEL_INGOT = ITEMS.register("nickel_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> NICKEL_NUGGET = ITEMS.register("nickel_nugget",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> NICKEL_DUST = ITEMS.register("nickel_dust",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> NICKEL_PLATE = ITEMS.register("nickel_plate",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RAW_ALUMINIUM = ITEMS.register("raw_aluminium",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ALUMINIUM_INGOT = ITEMS.register("aluminium_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ALUMINIUM_NUGGET = ITEMS.register("aluminium_nugget",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ALUMINIUM_DUST = ITEMS.register("aluminium_dust",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ALUMINIUM_PLATE = ITEMS.register("aluminium_plate",
            () -> new Item(new Item.Properties()));

    /* =====================================================================
     *                           ALLOY MATERIAL ITEMS
     * ===================================================================== */

    /* ---------- TITA-CHROME (kept your original registry naming: "tita-chrome_*") ---------- */
    public static final DeferredItem<Item> TITA_CHROME_INGOT = ITEMS.register("tita-chrome_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TITA_CHROME_NUGGET = ITEMS.register("tita-chrome_nugget",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TITA_CHROME_DUST = ITEMS.register("tita-chrome_dust",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TITA_CHROME_PLATE = ITEMS.register("tita-chrome_plate",
            () -> new Item(new Item.Properties()));

    /* ---------- STEEL ---------- */
    public static final DeferredItem<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STEEL_NUGGET = ITEMS.register("steel_nugget",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STEEL_DUST = ITEMS.register("steel_dust",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STEEL_PLATE = ITEMS.register("steel_plate",
            () -> new Item(new Item.Properties()));

    /* ---------- BRONZE ---------- */
    public static final DeferredItem<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BRONZE_NUGGET = ITEMS.register("bronze_nugget",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BRONZE_DUST = ITEMS.register("bronze_dust",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BRONZE_PLATE = ITEMS.register("bronze_plate",
            () -> new Item(new Item.Properties()));

    /* ---------- BRASS ---------- */
    public static final DeferredItem<Item> BRASS_INGOT = ITEMS.register("brass_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BRASS_NUGGET = ITEMS.register("brass_nugget",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BRASS_DUST = ITEMS.register("brass_dust",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BRASS_PLATE = ITEMS.register("brass_plate",
            () -> new Item(new Item.Properties()));

    /* ---------- ELECTRUM ---------- */
    public static final DeferredItem<Item> ELECTRUM_INGOT = ITEMS.register("electrum_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ELECTRUM_NUGGET = ITEMS.register("electrum_nugget",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ELECTRUM_DUST = ITEMS.register("electrum_dust",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ELECTRUM_PLATE = ITEMS.register("electrum_plate",
            () -> new Item(new Item.Properties()));

    /* ---------- INVAR ---------- */
    public static final DeferredItem<Item> INVAR_INGOT = ITEMS.register("invar_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> INVAR_NUGGET = ITEMS.register("invar_nugget",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> INVAR_DUST = ITEMS.register("invar_dust",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> INVAR_PLATE = ITEMS.register("invar_plate",
            () -> new Item(new Item.Properties()));

    /* ---------- CONSTANTAN ---------- */
    public static final DeferredItem<Item> CONSTANTAN_INGOT = ITEMS.register("constantan_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CONSTANTAN_NUGGET = ITEMS.register("constantan_nugget",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CONSTANTAN_DUST = ITEMS.register("constantan_dust",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CONSTANTAN_PLATE = ITEMS.register("constantan_plate",
            () -> new Item(new Item.Properties()));


    /* =====================================================================
     *                           MISC ITEMS
     * ===================================================================== */

    public static final DeferredItem<Item> COAL_DUST = ITEMS.register("coal_dust",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> BIOMASS = ITEMS.register("biomass",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> BIOMASS_PELLET = ITEMS.register("biomass_pellet",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    /* =====================================================================
     *                           MISC MACHINE ITEMS
     * ===================================================================== */

    public static final DeferredItem<Item> CIRCUIT_BOARD = ITEMS.register("circuit_board",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ADVANCED_CIRCUIT_BOARD = ITEMS.register("advanced_circuit_board",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> COPPER_WIRE = ITEMS.register("copper_wire",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> IRON_WIRE = ITEMS.register("iron_wire",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> GOLD_WIRE = ITEMS.register("gold_wire",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ELECTRUM_WIRE = ITEMS.register("electrum_wire",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> EMPTY_SPOOL = ITEMS.register("empty_spool",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> COPPER_SPOOL = ITEMS.register("copper_spool",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> IRON_SPOOL = ITEMS.register("iron_spool",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> GOLD_SPOOL = ITEMS.register("gold_spool",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ELECTRUM_SPOOL = ITEMS.register("electrum_spool",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SPEED_MODULE = ITEMS.register("speed_module",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> EFFICIENCY_MODULE = ITEMS.register("efficiency_module",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> HAMMER = ITEMS.register("hammer",
            () -> new Item(new Item.Properties().durability(250).stacksTo(1)));

    public static final DeferredItem<Item> WIRE_CUTTER = ITEMS.register("wire_cutter",
            () -> new Item(new Item.Properties().durability(200).stacksTo(1)));



    /* =====================================================================
     *                           TOOL & WEAPON TIERS
     * ===================================================================== */

    /* ----------  ✦  TIER-7  CHROMIUM  ✦  -------------------------------- */
    public static final DeferredItem<SwordItem> CHROMIUM_SWORD = ITEMS.register("chromium_sword",
            () -> new SwordItem(ModToolTiers.CHROMIUM,
                    new Item.Properties()
                            .attributes(SwordItem.createAttributes(ModToolTiers.CHROMIUM,
                                    6,         //  Tier-7  ➜  highest sword ΔDMG
                                    -2.4F))));
    public static final DeferredItem<Item> CHROMIUM_PICKAXE = ITEMS.register("chromium_pickaxe",
            () -> new PickaxeItem(ModToolTiers.CHROMIUM,
                    new Item.Properties()
                            .attributes(PickaxeItem.createAttributes(ModToolTiers.CHROMIUM,
                                    5,         //  Tier-7  ➜  highest pick ΔDMG
                                    -2.8F))));
    public static final DeferredItem<Item> CHROMIUM_AXE = ITEMS.register("chromium_axe",
            () -> new AxeItem(ModToolTiers.CHROMIUM,
                    new Item.Properties()
                            .attributes(AxeItem.createAttributes(ModToolTiers.CHROMIUM,
                                    9,         //  Tier-7  ➜  highest axe ΔDMG
                                    -3F))));
    public static final DeferredItem<Item> CHROMIUM_SHOVEL = ITEMS.register("chromium_shovel",
            () -> new ShovelItem(ModToolTiers.CHROMIUM,
                    new Item.Properties()
                            .attributes(ShovelItem.createAttributes(ModToolTiers.CHROMIUM,
                                    5,         //  Tier-7  ➜  highest shovel ΔDMG
                                    -3F))));
    public static final DeferredItem<Item> CHROMIUM_HOE = ITEMS.register("chromium_hoe",
            () -> new HoeItem(ModToolTiers.CHROMIUM,
                    new Item.Properties()
                            .attributes(HoeItem.createAttributes(ModToolTiers.CHROMIUM,
                                    1,        //  Tier-7  ➜  only positive hoe ΔDMG
                                    1F))));
    public static final DeferredItem<Item> CHROMIUM_HAMMER = ITEMS.register("chromium_hammer",
            () -> new HammerItem(ModToolTiers.CHROMIUM,
                    new Item.Properties()
                            .attributes(HammerItem.createAttributes(ModToolTiers.CHROMIUM,
                                    11,        //  Tier-7  ➜  highest hammer ΔDMG
                                    -3.5F))));
    public static final DeferredItem<Item> CHROMIUM_PAXEL = ITEMS.register("chromium_paxel",
            () -> new PaxelItem(ModToolTiers.CHROMIUM,
                    new Item.Properties()
                            .attributes(PaxelItem.createAttributes(ModToolTiers.CHROMIUM,
                                    4,         //  Tier-7  ➜  highest paxel ΔDMG
                                    -3F))));
    public static final DeferredItem<Item> CHROMIUM_REINFORCED_HAMMER = ITEMS.register("chromium_reinforced_hammer",
            () -> new ReinforcedHammerItem(ModToolTiers.CHROMIUM,
                    new Item.Properties()
                            .attributes(HammerItem.createAttributes(ModToolTiers.CHROMIUM,
                                    13,        // +2 damage over base
                                    -3.5F))
                            .durability(ModToolTiers.CHROMIUM.getUses() + 300)));

    /* =====================================================================
     *                      ARMOUR ITEM REGISTRATION
     * ===================================================================== */

    // ★ TIER-7  CHROMIUM
    public static final DeferredItem<ArmorItem> CHROMIUM_HELMET = ITEMS.register("chromium_helmet",
            () -> new ArmorItem(ModArmorMaterials.CHROMIUM_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(42))));
    public static final DeferredItem<ArmorItem> CHROMIUM_CHESTPLATE = ITEMS.register("chromium_chestplate",
            () -> new ArmorItem(ModArmorMaterials.CHROMIUM_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(42))));
    public static final DeferredItem<ArmorItem> CHROMIUM_LEGGINGS = ITEMS.register("chromium_leggings",
            () -> new ArmorItem(ModArmorMaterials.CHROMIUM_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(42))));
    public static final DeferredItem<ArmorItem> CHROMIUM_BOOTS = ITEMS.register("chromium_boots",
            () -> new ArmorItem(ModArmorMaterials.CHROMIUM_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(42))));
}
