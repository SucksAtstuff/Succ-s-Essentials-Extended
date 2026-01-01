package net.succ.succsessentials_extended.block;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.api.machine.GeneratorMachine;
import net.succ.succsessentials_extended.api.machine.MachineTier;
import net.succ.succsessentials_extended.api.machine.TieredMachine;
import net.succ.succsessentials_extended.block.custom.*;
import net.succ.succsessentials_extended.block.custom.multiblock.NuclearReactorRodBlock;
import net.succ.succsessentials_extended.block.entity.custom.BiofuelGeneratorBlockEntity;
import net.succ.succsessentials_extended.block.entity.custom.CoalGeneratorBlockEntity;
import net.succ.succsessentials_extended.item.ModItems;

import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {

    /* =====================================================================
     *                           REGISTRY
     * ===================================================================== */

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Succsessentials_extended.MOD_ID);

    /* =====================================================================
     *                           ORES
     * ===================================================================== */

    // ---------- CHROMIUM ----------
    public static final DeferredBlock<Block> CHROMIUM_ORE = registerBlock("chromium_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_ORE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DEEPSLATE_CHROMIUM_ORE = registerBlock("deepslate_chromium_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_EMERALD_ORE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> RAW_CHROMIUM_BLOCK = registerBlock("raw_chromium_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> CHROMIUM_BLOCK = registerBlock("block_of_chromium",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).requiresCorrectToolForDrops()));

    // ---------- TITANIUM ----------
    public static final DeferredBlock<Block> TITANIUM_ORE = registerBlock("titanium_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_ORE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DEEPSLATE_TITANIUM_ORE = registerBlock("deepslate_titanium_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_EMERALD_ORE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> RAW_TITANIUM_BLOCK = registerBlock("raw_titanium_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> TITANIUM_BLOCK = registerBlock("block_of_titanium",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).requiresCorrectToolForDrops()));

    // ---------- TIN ----------
    public static final DeferredBlock<Block> TIN_ORE = registerBlock("tin_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> RAW_TIN_BLOCK = registerBlock("raw_tin_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> TIN_BLOCK = registerBlock("block_of_tin",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

    // ---------- TUNGSTEN ----------
    public static final DeferredBlock<Block> TUNGSTEN_ORE = registerBlock("tungsten_ore",
            () -> new DropExperienceBlock(UniformInt.of(4,8),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DEEPSLATE_TUNGSTEN_ORE = registerBlock("deepslate_tungsten_ore",
            () -> new DropExperienceBlock(UniformInt.of(4,8),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> RAW_TUNGSTEN_BLOCK = registerBlock("raw_tungsten_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> TUNGSTEN_BLOCK = registerBlock("block_of_tungsten",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).requiresCorrectToolForDrops()));

    // ---------- COBALT ----------
    public static final DeferredBlock<Block> COBALT_ORE = registerBlock("cobalt_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DEEPSLATE_COBALT_ORE = registerBlock("deepslate_cobalt_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> RAW_COBALT_BLOCK = registerBlock("raw_cobalt_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> COBALT_BLOCK = registerBlock("block_of_cobalt",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

    // ---------- OSMIUM ----------
    public static final DeferredBlock<Block> OSMIUM_ORE = registerBlock("osmium_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DEEPSLATE_OSMIUM_ORE = registerBlock("deepslate_osmium_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> RAW_OSMIUM_BLOCK = registerBlock("raw_osmium_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> OSMIUM_BLOCK = registerBlock("block_of_osmium",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

    // ---------- ZINC ----------
    public static final DeferredBlock<Block> ZINC_ORE = registerBlock("zinc_ore",
            () -> new DropExperienceBlock(UniformInt.of(2,5),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DEEPSLATE_ZINC_ORE = registerBlock("deepslate_zinc_ore",
            () -> new DropExperienceBlock(UniformInt.of(2,5),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> RAW_ZINC_BLOCK = registerBlock("raw_zinc_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_COPPER_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> ZINC_BLOCK = registerBlock("block_of_zinc",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK).requiresCorrectToolForDrops()));

    // ---------- SILVER ----------
    public static final DeferredBlock<Block> SILVER_ORE = registerBlock("silver_ore",
            () -> new DropExperienceBlock(UniformInt.of(2,6),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DEEPSLATE_SILVER_ORE = registerBlock("deepslate_silver_ore",
            () -> new DropExperienceBlock(UniformInt.of(2,6),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> RAW_SILVER_BLOCK = registerBlock("raw_silver_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> SILVER_BLOCK = registerBlock("block_of_silver",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

    // ---------- NICKEL ----------
    public static final DeferredBlock<Block> NICKEL_ORE = registerBlock("nickel_ore",
            () -> new DropExperienceBlock(UniformInt.of(2,6),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DEEPSLATE_NICKEL_ORE = registerBlock("deepslate_nickel_ore",
            () -> new DropExperienceBlock(UniformInt.of(2,6),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> RAW_NICKEL_BLOCK = registerBlock("raw_nickel_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> NICKEL_BLOCK = registerBlock("block_of_nickel",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

        
     // ---------- ALUMINIUM ----------
     public static final DeferredBlock<Block> ALUMINIUM_ORE = registerBlock("aluminium_ore",
             () -> new DropExperienceBlock(UniformInt.of(2,6),
                     BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DEEPSLATE_ALUMINIUM_ORE = registerBlock("deepslate_aluminium_ore",
            () -> new DropExperienceBlock(UniformInt.of(2,6),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> RAW_ALUMINIUM_BLOCK = registerBlock("raw_aluminium_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> ALUMINIUM_BLOCK = registerBlock("block_of_aluminium",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

    /* =====================================================================
     *                           ALLOY BLOCKS
     * ===================================================================== */

    public static final DeferredBlock<Block> STEEL_BLOCK = registerBlock("block_of_steel",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> BRONZE_BLOCK = registerBlock("block_of_bronze",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> BRASS_BLOCK = registerBlock("block_of_brass",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> ELECTRUM_BLOCK = registerBlock("block_of_electrum",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> INVAR_BLOCK = registerBlock("block_of_invar",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> CONSTANTAN_BLOCK = registerBlock("block_of_constantan",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> TITA_CHROME_BLOCK = registerBlock("block_of_tita-chrome",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).requiresCorrectToolForDrops()));

    /* =====================================================================
     *                           MACHINES / TECH
     * ===================================================================== */

    public static final DeferredBlock<Block> ALLOY_FORGER = registerMachineBlock("alloy_forger",
            () -> new AlloyForgerBlock(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops(), MachineTier.BASIC));
    public static final DeferredBlock<Block> PULVERIZER = registerMachineBlock("pulverizer",
            () -> new PulverizerBlock(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops(), MachineTier.BASIC));
    public static final DeferredBlock<Block> ELECTRIC_FURNACE = registerMachineBlock("electric_furnace",
            () -> new ElectricFurnaceBlock(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops(), MachineTier.BASIC));
    public static final DeferredBlock<Block> INFUSER = registerMachineBlock("infuser",
            () -> new InfuserBlock(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops(), MachineTier.BASIC));
    public static final DeferredBlock<Block> WIRE_DRAWER = registerMachineBlock("wire_drawer",
            () -> new WireDrawerBlock(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops(), MachineTier.BASIC));
    public static final DeferredBlock<Block> ROLLING_MILL = registerMachineBlock("rolling_mill",
            () -> new RollingMillBlock(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops(), MachineTier.BASIC));
    public static final DeferredBlock<Block> COAL_GENERATOR = registerMachineBlock("coal_generator",
            () -> new CoalGeneratorBlock(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops(), MachineTier.BASIC, CoalGeneratorBlockEntity.POWER_GENERATION_RATE));
    public static final DeferredBlock<Block> BIO_FUEL_GENERATOR = registerMachineBlock("bio_fuel_generator",
            () -> new BiofuelGeneratorBlock(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops(), MachineTier.BASIC, BiofuelGeneratorBlockEntity.POWER_GENERATION_RATE));
    public static final DeferredBlock<Block> PANEL_BLOCK = registerBlock("panel_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

    /* =====================================================================
     *                     NUCLEAR REACTOR (MULTIBLOCK)
     * ===================================================================== */

    public static final DeferredBlock<Block> NUCLEAR_REACTOR_CONTROLLER = registerMachineBlock("nuclear_reactor_controller",
                    () -> new NuclearReactorControllerBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), MachineTier.ADVANCED));

    public static final DeferredBlock<Block> NUCLEAR_REACTOR_INPUT = registerBlock("nuclear_reactor_input",
                    () -> new NuclearReactorInputBlock(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> NUCLEAR_REACTOR_OUTPUT = registerBlock("nuclear_reactor_output",
                    () -> new NuclearReactorOutputBlock(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> NUCLEAR_REACTOR_ROD = registerBlock("nuclear_reactor_rod",
            () -> new NuclearReactorRodBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));

    /* =====================================================================
     *                        INTERNAL REGISTRATION
     * ===================================================================== */

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredBlock<T> registerMachineBlock(
            String name,
            Supplier<T> block
    ) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerMachineBlockItem(name, toReturn);
        return toReturn;
    }


    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> void registerMachineBlockItem(
            String name,
            DeferredBlock<T> block
    ) {
        ModItems.ITEMS.register(name, () ->
                new BlockItem(block.get(), new Item.Properties()) {

                    @Override
                    public void appendHoverText(
                            ItemStack stack,
                            TooltipContext context,
                            List<Component> tooltipComponents,
                            TooltipFlag tooltipFlag
                    ) {
                        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

                        Block b = block.get();

                        // -------------------------------
                        // Tier tooltip
                        // -------------------------------
                        if (b instanceof TieredMachine tiered) {
                            tooltipComponents.add(
                                    Component.literal("Tier: ")
                                            .withStyle(ChatFormatting.DARK_GRAY)
                                            .append(
                                                    Component.literal(tiered.getTier().name())
                                                            .withStyle(tiered.getTier().color())
                                            )
                            );
                        }

                        // -------------------------------
                        // Generator tooltip
                        // -------------------------------
                        if (b instanceof GeneratorMachine generator) {
                            tooltipComponents.add(
                                    Component.literal("Generation: ")
                                            .withStyle(ChatFormatting.DARK_GRAY)
                                            .append(
                                                    Component.literal(generator.getGenerationRate() + " FE/t")
                                                            .withStyle(ChatFormatting.AQUA)
                                            )
                            );
                        }
                    }
                }
        );
    }







    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
