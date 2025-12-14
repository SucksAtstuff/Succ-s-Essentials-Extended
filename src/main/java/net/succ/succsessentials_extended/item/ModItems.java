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

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(Succsessentials_extended.MOD_ID);

    public static final DeferredItem<Item> RAW_CHROMIUM = ITEMS.register("raw_chromium",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CHROMIUM_INGOT = ITEMS.register("chromium_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CHROMIUM_NUGGET = ITEMS.register("chromium_nugget",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RAW_TITANIUM = ITEMS.register("raw_titanium",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TITANIUM_NUGGET = ITEMS.register("titanium_nugget",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TITANIUM_DUST = ITEMS.register("titanium_dust",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> TITA_CHROME_INGOT = ITEMS.register("tita-chrome_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TITA_CHROME_NUGGET = ITEMS.register("tita-chrome_nugget",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STEEL_NUGGET = ITEMS.register("steel_nugget",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> COAL_DUST = ITEMS.register("coal_dust",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

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
