package net.succ.succsessentials_extended;

import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

public class ModConfig {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    /* ================================================================
       ORE GENERATION
       ================================================================ */

    private static final ModConfigSpec.IntValue CHROMIUM_VEIN_SIZE;
    private static final ModConfigSpec.IntValue CHROMIUM_VEINS_PER_CHUNK;
    private static final ModConfigSpec.IntValue CHROMIUM_MIN_Y;
    private static final ModConfigSpec.IntValue CHROMIUM_MAX_Y;

    private static final ModConfigSpec.IntValue TITANIUM_VEIN_SIZE;
    private static final ModConfigSpec.IntValue TITANIUM_VEINS_PER_CHUNK;
    private static final ModConfigSpec.IntValue TITANIUM_MIN_Y;
    private static final ModConfigSpec.IntValue TITANIUM_MAX_Y;

    private static final ModConfigSpec.IntValue TIN_VEIN_SIZE;
    private static final ModConfigSpec.IntValue TIN_VEINS_PER_CHUNK;
    private static final ModConfigSpec.IntValue TIN_MIN_Y;
    private static final ModConfigSpec.IntValue TIN_MAX_Y;

    private static final ModConfigSpec.IntValue TUNGSTEN_VEIN_SIZE;
    private static final ModConfigSpec.IntValue TUNGSTEN_VEINS_PER_CHUNK;
    private static final ModConfigSpec.IntValue TUNGSTEN_MIN_Y;
    private static final ModConfigSpec.IntValue TUNGSTEN_MAX_Y;

    private static final ModConfigSpec.IntValue COBALT_VEIN_SIZE;
    private static final ModConfigSpec.IntValue COBALT_VEINS_PER_CHUNK;
    private static final ModConfigSpec.IntValue COBALT_MIN_Y;
    private static final ModConfigSpec.IntValue COBALT_MAX_Y;

    private static final ModConfigSpec.IntValue OSMIUM_VEIN_SIZE;
    private static final ModConfigSpec.IntValue OSMIUM_VEINS_PER_CHUNK;
    private static final ModConfigSpec.IntValue OSMIUM_MIN_Y;
    private static final ModConfigSpec.IntValue OSMIUM_MAX_Y;

    private static final ModConfigSpec.IntValue ZINC_VEIN_SIZE;
    private static final ModConfigSpec.IntValue ZINC_VEINS_PER_CHUNK;
    private static final ModConfigSpec.IntValue ZINC_MIN_Y;
    private static final ModConfigSpec.IntValue ZINC_MAX_Y;

    private static final ModConfigSpec.IntValue SILVER_VEIN_SIZE;
    private static final ModConfigSpec.IntValue SILVER_VEINS_PER_CHUNK;
    private static final ModConfigSpec.IntValue SILVER_MIN_Y;
    private static final ModConfigSpec.IntValue SILVER_MAX_Y;

    private static final ModConfigSpec.IntValue NICKEL_VEIN_SIZE;
    private static final ModConfigSpec.IntValue NICKEL_VEINS_PER_CHUNK;
    private static final ModConfigSpec.IntValue NICKEL_MIN_Y;
    private static final ModConfigSpec.IntValue NICKEL_MAX_Y;

    /* ================================================================
       MACHINES
       ================================================================ */

    private static final ModConfigSpec.IntValue ALLOY_FORGER_CAPACITY;
    private static final ModConfigSpec.IntValue ALLOY_FORGER_TRANSFER;

    private static final ModConfigSpec.IntValue ELECTRIC_FURNACE_CAPACITY;
    private static final ModConfigSpec.IntValue ELECTRIC_FURNACE_TRANSFER;
    private static final ModConfigSpec.IntValue ELECTRIC_FURNACE_ENERGY_PER_TICK;

    private static final ModConfigSpec.IntValue INFUSER_CAPACITY;
    private static final ModConfigSpec.IntValue INFUSER_TRANSFER;

    private static final ModConfigSpec.IntValue PULVERIZER_CAPACITY;
    private static final ModConfigSpec.IntValue PULVERIZER_TRANSFER;

    private static final ModConfigSpec.IntValue WIRE_DRAWER_CAPACITY;
    private static final ModConfigSpec.IntValue WIRE_DRAWER_TRANSFER;

    private static final ModConfigSpec.IntValue ROLLING_MILL_CAPACITY;
    private static final ModConfigSpec.IntValue ROLLING_MILL_TRANSFER;

    /* ================================================================
       GENERATORS
       ================================================================ */

    private static final ModConfigSpec.IntValue COAL_CAPACITY;
    private static final ModConfigSpec.IntValue COAL_TRANSFER;
    private static final ModConfigSpec.IntValue COAL_GENERATION_RATE;
    private static final ModConfigSpec.IntValue COAL_BURN_TIME;

    private static final ModConfigSpec.IntValue BIOFUEL_CAPACITY;
    private static final ModConfigSpec.IntValue BIOFUEL_TRANSFER;
    private static final ModConfigSpec.IntValue BIOFUEL_GENERATION_RATE;
    private static final ModConfigSpec.IntValue BIOFUEL_BURN_TIME;

    /* ================================================================
       REACTOR
       ================================================================ */

    private static final ModConfigSpec.IntValue REACTOR_CAPACITY;
    private static final ModConfigSpec.IntValue REACTOR_TRANSFER;
    private static final ModConfigSpec.IntValue REACTOR_THORIUM_RATE;
    private static final ModConfigSpec.IntValue REACTOR_URANIUM_RATE;
    private static final ModConfigSpec.BooleanValue REACTOR_ENABLE_EXPLOSION;

    /* ================================================================
       SPEC BUILD
       ================================================================ */

    public static final ModConfigSpec SPEC;

    static {
        // ---- ORE GENERATION ----
        BUILDER.comment("Controls where and how often each ore spawns. Requires world restart.").push("ore_generation");

        BUILDER.push("chromium");
        CHROMIUM_VEIN_SIZE        = BUILDER.comment("Blocks per vein").defineInRange("vein_size", 4, 1, 64);
        CHROMIUM_VEINS_PER_CHUNK  = BUILDER.comment("Vein attempts per chunk. 0 = disabled").defineInRange("veins_per_chunk", 1, 0, 32);
        CHROMIUM_MIN_Y            = BUILDER.comment("Minimum Y level").defineInRange("min_y", -48, -64, 320);
        CHROMIUM_MAX_Y            = BUILDER.comment("Maximum Y level").defineInRange("max_y", -8, -64, 320);
        BUILDER.pop();

        BUILDER.push("titanium");
        TITANIUM_VEIN_SIZE        = BUILDER.comment("Blocks per vein").defineInRange("vein_size", 7, 1, 64);
        TITANIUM_VEINS_PER_CHUNK  = BUILDER.comment("Vein attempts per chunk. 0 = disabled").defineInRange("veins_per_chunk", 5, 0, 32);
        TITANIUM_MIN_Y            = BUILDER.comment("Minimum Y level").defineInRange("min_y", -48, -64, 320);
        TITANIUM_MAX_Y            = BUILDER.comment("Maximum Y level").defineInRange("max_y", 16, -64, 320);
        BUILDER.pop();

        BUILDER.push("tin");
        TIN_VEIN_SIZE             = BUILDER.comment("Blocks per vein").defineInRange("vein_size", 9, 1, 64);
        TIN_VEINS_PER_CHUNK       = BUILDER.comment("Vein attempts per chunk. 0 = disabled").defineInRange("veins_per_chunk", 12, 0, 32);
        TIN_MIN_Y                 = BUILDER.comment("Minimum Y level").defineInRange("min_y", 0, -64, 320);
        TIN_MAX_Y                 = BUILDER.comment("Maximum Y level").defineInRange("max_y", 96, -64, 320);
        BUILDER.pop();

        BUILDER.push("tungsten");
        TUNGSTEN_VEIN_SIZE        = BUILDER.comment("Blocks per vein").defineInRange("vein_size", 6, 1, 64);
        TUNGSTEN_VEINS_PER_CHUNK  = BUILDER.comment("Vein attempts per chunk. 0 = disabled").defineInRange("veins_per_chunk", 1, 0, 32);
        TUNGSTEN_MIN_Y            = BUILDER.comment("Minimum Y level").defineInRange("min_y", -64, -64, 320);
        TUNGSTEN_MAX_Y            = BUILDER.comment("Maximum Y level").defineInRange("max_y", -16, -64, 320);
        BUILDER.pop();

        BUILDER.push("cobalt");
        COBALT_VEIN_SIZE          = BUILDER.comment("Blocks per vein").defineInRange("vein_size", 7, 1, 64);
        COBALT_VEINS_PER_CHUNK    = BUILDER.comment("Vein attempts per chunk. 0 = disabled").defineInRange("veins_per_chunk", 5, 0, 32);
        COBALT_MIN_Y              = BUILDER.comment("Minimum Y level").defineInRange("min_y", -32, -64, 320);
        COBALT_MAX_Y              = BUILDER.comment("Maximum Y level").defineInRange("max_y", 32, -64, 320);
        BUILDER.pop();

        BUILDER.push("osmium");
        OSMIUM_VEIN_SIZE          = BUILDER.comment("Blocks per vein").defineInRange("vein_size", 6, 1, 64);
        OSMIUM_VEINS_PER_CHUNK    = BUILDER.comment("Vein attempts per chunk. 0 = disabled").defineInRange("veins_per_chunk", 4, 0, 32);
        OSMIUM_MIN_Y              = BUILDER.comment("Minimum Y level").defineInRange("min_y", -40, -64, 320);
        OSMIUM_MAX_Y              = BUILDER.comment("Maximum Y level").defineInRange("max_y", 24, -64, 320);
        BUILDER.pop();

        BUILDER.push("zinc");
        ZINC_VEIN_SIZE            = BUILDER.comment("Blocks per vein").defineInRange("vein_size", 10, 1, 64);
        ZINC_VEINS_PER_CHUNK      = BUILDER.comment("Vein attempts per chunk. 0 = disabled").defineInRange("veins_per_chunk", 10, 0, 32);
        ZINC_MIN_Y                = BUILDER.comment("Minimum Y level").defineInRange("min_y", 16, -64, 320);
        ZINC_MAX_Y                = BUILDER.comment("Maximum Y level").defineInRange("max_y", 112, -64, 320);
        BUILDER.pop();

        BUILDER.push("silver");
        SILVER_VEIN_SIZE          = BUILDER.comment("Blocks per vein").defineInRange("vein_size", 8, 1, 64);
        SILVER_VEINS_PER_CHUNK    = BUILDER.comment("Vein attempts per chunk. 0 = disabled").defineInRange("veins_per_chunk", 6, 0, 32);
        SILVER_MIN_Y              = BUILDER.comment("Minimum Y level").defineInRange("min_y", -24, -64, 320);
        SILVER_MAX_Y              = BUILDER.comment("Maximum Y level").defineInRange("max_y", 40, -64, 320);
        BUILDER.pop();

        BUILDER.push("nickel");
        NICKEL_VEIN_SIZE          = BUILDER.comment("Blocks per vein").defineInRange("vein_size", 8, 1, 64);
        NICKEL_VEINS_PER_CHUNK    = BUILDER.comment("Vein attempts per chunk. 0 = disabled").defineInRange("veins_per_chunk", 6, 0, 32);
        NICKEL_MIN_Y              = BUILDER.comment("Minimum Y level").defineInRange("min_y", -16, -64, 320);
        NICKEL_MAX_Y              = BUILDER.comment("Maximum Y level").defineInRange("max_y", 48, -64, 320);
        BUILDER.pop();

        BUILDER.pop(); // ore_generation

        // ---- MACHINES ----
        BUILDER.comment("Energy settings for processing machines. Requires world restart.").push("machines");

        BUILDER.push("alloy_forger");
        ALLOY_FORGER_CAPACITY = BUILDER.comment("Energy capacity in FE").defineInRange("capacity", 64000, 1, Integer.MAX_VALUE);
        ALLOY_FORGER_TRANSFER = BUILDER.comment("Max FE/t input/output").defineInRange("transfer_rate", 320, 1, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.push("electric_furnace");
        ELECTRIC_FURNACE_CAPACITY       = BUILDER.comment("Energy capacity in FE").defineInRange("capacity", 64000, 1, Integer.MAX_VALUE);
        ELECTRIC_FURNACE_TRANSFER       = BUILDER.comment("Max FE/t input/output").defineInRange("transfer_rate", 320, 1, Integer.MAX_VALUE);
        ELECTRIC_FURNACE_ENERGY_PER_TICK = BUILDER.comment("FE consumed per tick while smelting").defineInRange("energy_per_tick", 20, 1, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.push("infuser");
        INFUSER_CAPACITY = BUILDER.comment("Energy capacity in FE").defineInRange("capacity", 128000, 1, Integer.MAX_VALUE);
        INFUSER_TRANSFER = BUILDER.comment("Max FE/t input/output").defineInRange("transfer_rate", 640, 1, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.push("pulverizer");
        PULVERIZER_CAPACITY = BUILDER.comment("Energy capacity in FE").defineInRange("capacity", 64000, 1, Integer.MAX_VALUE);
        PULVERIZER_TRANSFER = BUILDER.comment("Max FE/t input/output").defineInRange("transfer_rate", 320, 1, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.push("wire_drawer");
        WIRE_DRAWER_CAPACITY = BUILDER.comment("Energy capacity in FE").defineInRange("capacity", 64000, 1, Integer.MAX_VALUE);
        WIRE_DRAWER_TRANSFER = BUILDER.comment("Max FE/t input/output").defineInRange("transfer_rate", 320, 1, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.push("rolling_mill");
        ROLLING_MILL_CAPACITY = BUILDER.comment("Energy capacity in FE").defineInRange("capacity", 64000, 1, Integer.MAX_VALUE);
        ROLLING_MILL_TRANSFER = BUILDER.comment("Max FE/t input/output").defineInRange("transfer_rate", 320, 1, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.pop(); // machines

        // ---- GENERATORS ----
        BUILDER.comment("Energy settings for generators. Requires world restart.").push("generators");

        BUILDER.push("coal_generator");
        COAL_CAPACITY        = BUILDER.comment("Energy capacity in FE").defineInRange("capacity", 64000, 1, Integer.MAX_VALUE);
        COAL_TRANSFER        = BUILDER.comment("Max FE/t pushed to adjacent blocks").defineInRange("transfer_rate", 80, 1, Integer.MAX_VALUE);
        COAL_GENERATION_RATE = BUILDER.comment("FE generated per tick while burning").defineInRange("generation_rate", 80, 1, Integer.MAX_VALUE);
        COAL_BURN_TIME       = BUILDER.comment("Ticks of burn time per coal").defineInRange("burn_time", 160, 1, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.push("biofuel_generator");
        BIOFUEL_CAPACITY        = BUILDER.comment("Energy capacity in FE").defineInRange("capacity", 64000, 1, Integer.MAX_VALUE);
        BIOFUEL_TRANSFER        = BUILDER.comment("Max FE/t pushed to adjacent blocks").defineInRange("transfer_rate", 640, 1, Integer.MAX_VALUE);
        BIOFUEL_GENERATION_RATE = BUILDER.comment("FE generated per tick while burning").defineInRange("generation_rate", 160, 1, Integer.MAX_VALUE);
        BIOFUEL_BURN_TIME       = BUILDER.comment("Ticks of burn time per biofuel").defineInRange("burn_time", 80, 1, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.pop(); // generators

        // ---- REACTOR ----
        BUILDER.comment("Nuclear Reactor settings. Requires world restart.").push("reactor");
        REACTOR_CAPACITY         = BUILDER.comment("Energy capacity in FE").defineInRange("capacity", 1_000_000, 1, Integer.MAX_VALUE);
        REACTOR_TRANSFER         = BUILDER.comment("Max FE/t pushed to adjacent blocks").defineInRange("transfer_rate", 4000, 1, Integer.MAX_VALUE);
        REACTOR_THORIUM_RATE     = BUILDER.comment("FE/t generated by thorium fuel").defineInRange("thorium_rate", 60, 1, Integer.MAX_VALUE);
        REACTOR_URANIUM_RATE     = BUILDER.comment("FE/t generated by uranium fuel").defineInRange("uranium_rate", 120, 1, Integer.MAX_VALUE);
        REACTOR_ENABLE_EXPLOSION = BUILDER.comment("Whether the reactor explodes and spawns a radiation cloud on meltdown").define("enable_explosion", true);
        BUILDER.pop(); // reactor

        SPEC = BUILDER.build();
    }

    /* ================================================================
       PUBLIC CACHED VALUES — initialised with defaults so worldgen
       works even if onLoad fires after bootstrap (shouldn't happen).
       ================================================================ */

    // Ores
    public static int chromiumVeinSize = 4;    public static int chromiumVeinsPerChunk = 1;
    public static int chromiumMinY = -48;       public static int chromiumMaxY = -8;

    public static int titaniumVeinSize = 7;    public static int titaniumVeinsPerChunk = 5;
    public static int titaniumMinY = -48;       public static int titaniumMaxY = 16;

    public static int tinVeinSize = 9;         public static int tinVeinsPerChunk = 12;
    public static int tinMinY = 0;              public static int tinMaxY = 96;

    public static int tungstenVeinSize = 6;    public static int tungstenVeinsPerChunk = 1;
    public static int tungstenMinY = -64;       public static int tungstenMaxY = -16;

    public static int cobaltVeinSize = 7;      public static int cobaltVeinsPerChunk = 5;
    public static int cobaltMinY = -32;         public static int cobaltMaxY = 32;

    public static int osmiumVeinSize = 6;      public static int osmiumVeinsPerChunk = 4;
    public static int osmiumMinY = -40;         public static int osmiumMaxY = 24;

    public static int zincVeinSize = 10;       public static int zincVeinsPerChunk = 10;
    public static int zincMinY = 16;            public static int zincMaxY = 112;

    public static int silverVeinSize = 8;      public static int silverVeinsPerChunk = 6;
    public static int silverMinY = -24;         public static int silverMaxY = 40;

    public static int nickelVeinSize = 8;      public static int nickelVeinsPerChunk = 6;
    public static int nickelMinY = -16;         public static int nickelMaxY = 48;

    // Machines
    public static int alloyForgerCapacity = 64000;      public static int alloyForgerTransfer = 320;
    public static int electricFurnaceCapacity = 64000;  public static int electricFurnaceTransfer = 320;
    public static int electricFurnaceEnergyPerTick = 20;
    public static int infuserCapacity = 128000;         public static int infuserTransfer = 640;
    public static int pulverizerCapacity = 64000;       public static int pulverizerTransfer = 320;
    public static int wireDrawerCapacity = 64000;       public static int wireDrawerTransfer = 320;
    public static int rollingMillCapacity = 64000;      public static int rollingMillTransfer = 320;

    // Generators
    public static int coalCapacity = 64000;      public static int coalTransfer = 80;
    public static int coalGenerationRate = 80;   public static int coalBurnTime = 160;
    public static int biofuelCapacity = 64000;   public static int biofuelTransfer = 640;
    public static int biofuelGenerationRate = 160; public static int biofuelBurnTime = 80;

    // Reactor
    public static int reactorCapacity = 1_000_000;  public static int reactorTransfer = 4000;
    public static int reactorThoriumRate = 60;       public static int reactorUraniumRate = 120;
    public static boolean reactorEnableExplosion = true;

    /* ================================================================
       LOAD
       ================================================================ */

    public static void onLoad(final ModConfigEvent event) {
        chromiumVeinSize       = CHROMIUM_VEIN_SIZE.get();
        chromiumVeinsPerChunk  = CHROMIUM_VEINS_PER_CHUNK.get();
        chromiumMinY           = CHROMIUM_MIN_Y.get();
        chromiumMaxY           = CHROMIUM_MAX_Y.get();

        titaniumVeinSize       = TITANIUM_VEIN_SIZE.get();
        titaniumVeinsPerChunk  = TITANIUM_VEINS_PER_CHUNK.get();
        titaniumMinY           = TITANIUM_MIN_Y.get();
        titaniumMaxY           = TITANIUM_MAX_Y.get();

        tinVeinSize            = TIN_VEIN_SIZE.get();
        tinVeinsPerChunk       = TIN_VEINS_PER_CHUNK.get();
        tinMinY                = TIN_MIN_Y.get();
        tinMaxY                = TIN_MAX_Y.get();

        tungstenVeinSize       = TUNGSTEN_VEIN_SIZE.get();
        tungstenVeinsPerChunk  = TUNGSTEN_VEINS_PER_CHUNK.get();
        tungstenMinY           = TUNGSTEN_MIN_Y.get();
        tungstenMaxY           = TUNGSTEN_MAX_Y.get();

        cobaltVeinSize         = COBALT_VEIN_SIZE.get();
        cobaltVeinsPerChunk    = COBALT_VEINS_PER_CHUNK.get();
        cobaltMinY             = COBALT_MIN_Y.get();
        cobaltMaxY             = COBALT_MAX_Y.get();

        osmiumVeinSize         = OSMIUM_VEIN_SIZE.get();
        osmiumVeinsPerChunk    = OSMIUM_VEINS_PER_CHUNK.get();
        osmiumMinY             = OSMIUM_MIN_Y.get();
        osmiumMaxY             = OSMIUM_MAX_Y.get();

        zincVeinSize           = ZINC_VEIN_SIZE.get();
        zincVeinsPerChunk      = ZINC_VEINS_PER_CHUNK.get();
        zincMinY               = ZINC_MIN_Y.get();
        zincMaxY               = ZINC_MAX_Y.get();

        silverVeinSize         = SILVER_VEIN_SIZE.get();
        silverVeinsPerChunk    = SILVER_VEINS_PER_CHUNK.get();
        silverMinY             = SILVER_MIN_Y.get();
        silverMaxY             = SILVER_MAX_Y.get();

        nickelVeinSize         = NICKEL_VEIN_SIZE.get();
        nickelVeinsPerChunk    = NICKEL_VEINS_PER_CHUNK.get();
        nickelMinY             = NICKEL_MIN_Y.get();
        nickelMaxY             = NICKEL_MAX_Y.get();

        alloyForgerCapacity          = ALLOY_FORGER_CAPACITY.get();
        alloyForgerTransfer          = ALLOY_FORGER_TRANSFER.get();
        electricFurnaceCapacity      = ELECTRIC_FURNACE_CAPACITY.get();
        electricFurnaceTransfer      = ELECTRIC_FURNACE_TRANSFER.get();
        electricFurnaceEnergyPerTick = ELECTRIC_FURNACE_ENERGY_PER_TICK.get();
        infuserCapacity              = INFUSER_CAPACITY.get();
        infuserTransfer              = INFUSER_TRANSFER.get();
        pulverizerCapacity           = PULVERIZER_CAPACITY.get();
        pulverizerTransfer           = PULVERIZER_TRANSFER.get();
        wireDrawerCapacity           = WIRE_DRAWER_CAPACITY.get();
        wireDrawerTransfer           = WIRE_DRAWER_TRANSFER.get();
        rollingMillCapacity          = ROLLING_MILL_CAPACITY.get();
        rollingMillTransfer          = ROLLING_MILL_TRANSFER.get();

        coalCapacity        = COAL_CAPACITY.get();
        coalTransfer        = COAL_TRANSFER.get();
        coalGenerationRate  = COAL_GENERATION_RATE.get();
        coalBurnTime        = COAL_BURN_TIME.get();
        biofuelCapacity     = BIOFUEL_CAPACITY.get();
        biofuelTransfer     = BIOFUEL_TRANSFER.get();
        biofuelGenerationRate = BIOFUEL_GENERATION_RATE.get();
        biofuelBurnTime     = BIOFUEL_BURN_TIME.get();

        reactorCapacity         = REACTOR_CAPACITY.get();
        reactorTransfer         = REACTOR_TRANSFER.get();
        reactorThoriumRate      = REACTOR_THORIUM_RATE.get();
        reactorUraniumRate      = REACTOR_URANIUM_RATE.get();
        reactorEnableExplosion  = REACTOR_ENABLE_EXPLOSION.get();
    }
}
