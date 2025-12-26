package net.succ.succsessentials_extended.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.succ.succsessentials_extended.block.ModBlocks;
import net.succ.succsessentials_extended.item.ModItems;

import java.util.Set;

public class ModBlockLootTablesProvider extends BlockLootSubProvider {

    protected ModBlockLootTablesProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {

        /* =====================================================================
         *                           SELF-DROPPING BLOCKS
         * ===================================================================== */

        // Storage blocks
        dropSelf(ModBlocks.CHROMIUM_BLOCK.get());
        dropSelf(ModBlocks.TITANIUM_BLOCK.get());
        dropSelf(ModBlocks.TIN_BLOCK.get());
        dropSelf(ModBlocks.TUNGSTEN_BLOCK.get());
        dropSelf(ModBlocks.COBALT_BLOCK.get());
        dropSelf(ModBlocks.OSMIUM_BLOCK.get());
        dropSelf(ModBlocks.ZINC_BLOCK.get());
        dropSelf(ModBlocks.SILVER_BLOCK.get());
        dropSelf(ModBlocks.NICKEL_BLOCK.get());

        // Alloy blocks
        dropSelf(ModBlocks.STEEL_BLOCK.get());
        dropSelf(ModBlocks.BRONZE_BLOCK.get());
        dropSelf(ModBlocks.BRASS_BLOCK.get());
        dropSelf(ModBlocks.ELECTRUM_BLOCK.get());
        dropSelf(ModBlocks.INVAR_BLOCK.get());
        dropSelf(ModBlocks.CONSTANTAN_BLOCK.get());
        dropSelf(ModBlocks.TITA_CHROME_BLOCK.get());

        // Raw blocks
        dropSelf(ModBlocks.RAW_CHROMIUM_BLOCK.get());
        dropSelf(ModBlocks.RAW_TITANIUM_BLOCK.get());
        dropSelf(ModBlocks.RAW_TIN_BLOCK.get());
        dropSelf(ModBlocks.RAW_TUNGSTEN_BLOCK.get());
        dropSelf(ModBlocks.RAW_COBALT_BLOCK.get());
        dropSelf(ModBlocks.RAW_OSMIUM_BLOCK.get());
        dropSelf(ModBlocks.RAW_ZINC_BLOCK.get());
        dropSelf(ModBlocks.RAW_SILVER_BLOCK.get());
        dropSelf(ModBlocks.RAW_NICKEL_BLOCK.get());

        // Machines / tech
        dropSelf(ModBlocks.ALLOY_FORGER.get());
        dropSelf(ModBlocks.PULVERIZER.get());
        dropSelf(ModBlocks.ELECTRIC_FURNACE.get());
        dropSelf(ModBlocks.INFUSER.get());
        dropSelf(ModBlocks.COAL_GENERATOR.get());
        dropSelf(ModBlocks.BIO_FUEL_GENERATOR.get());
        dropSelf(ModBlocks.PANEL_BLOCK.get());

        /* =====================================================================
         *                           STONE DROPS
         * ===================================================================== */

        // Chromium
        add(ModBlocks.CHROMIUM_ORE.get(),
                block -> createOreDrop(block, ModItems.RAW_CHROMIUM.get()));
        add(ModBlocks.DEEPSLATE_CHROMIUM_ORE.get(),
                block -> createOreDrop(block, ModItems.RAW_CHROMIUM.get()));

        // Titanium
        add(ModBlocks.TITANIUM_ORE.get(),
                block -> createOreDrop(block, ModItems.RAW_TITANIUM.get()));
        add(ModBlocks.DEEPSLATE_TITANIUM_ORE.get(),
                block -> createOreDrop(block, ModItems.RAW_TITANIUM.get()));

        // Tin
        add(ModBlocks.TIN_ORE.get(),
                block -> createOreDrop(block, ModItems.RAW_TIN.get()));
        add(ModBlocks.DEEPSLATE_TIN_ORE.get(),
                block -> createOreDrop(block, ModItems.RAW_TIN.get()));

        // Tungsten
        add(ModBlocks.TUNGSTEN_ORE.get(),
                block -> createOreDrop(block, ModItems.RAW_TUNGSTEN.get()));
        add(ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get(),
                block -> createOreDrop(block, ModItems.RAW_TUNGSTEN.get()));

        // Cobalt
        add(ModBlocks.COBALT_ORE.get(),
                block -> createOreDrop(block, ModItems.RAW_COBALT.get()));
        add(ModBlocks.DEEPSLATE_COBALT_ORE.get(),
                block -> createOreDrop(block, ModItems.RAW_COBALT.get()));

        // Osmium
        add(ModBlocks.OSMIUM_ORE.get(),
                block -> createOreDrop(block, ModItems.RAW_OSMIUM.get()));
        add(ModBlocks.DEEPSLATE_OSMIUM_ORE.get(),
                block -> createOreDrop(block, ModItems.RAW_OSMIUM.get()));

        // Zinc
        add(ModBlocks.ZINC_ORE.get(),
                block -> createOreDrop(block, ModItems.RAW_ZINC.get()));
        add(ModBlocks.DEEPSLATE_ZINC_ORE.get(),
                block -> createOreDrop(block, ModItems.RAW_ZINC.get()));

        // Silver
        add(ModBlocks.SILVER_ORE.get(),
                block -> createOreDrop(block, ModItems.RAW_SILVER.get()));
        add(ModBlocks.DEEPSLATE_SILVER_ORE.get(),
                block -> createOreDrop(block, ModItems.RAW_SILVER.get()));

        // Nickel
        add(ModBlocks.NICKEL_ORE.get(),
                block -> createOreDrop(block, ModItems.RAW_NICKEL.get()));
        add(ModBlocks.DEEPSLATE_NICKEL_ORE.get(),
                block -> createOreDrop(block, ModItems.RAW_NICKEL.get()));
    }

    /* =====================================================================
     *                           HELPERS
     * ===================================================================== */

    protected LootTable.Builder createMultipleOreDrops(Block block, Item item, float min, float max) {
        HolderLookup.RegistryLookup<Enchantment> enchantments =
                this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        return this.createSilkTouchDispatchTable(block,
                this.applyExplosionDecay(block,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(
                                        UniformGenerator.between(min, max)))
                                .apply(ApplyBonusCount.addOreBonusCount(
                                        enchantments.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
