# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Development Commands

```bash
./gradlew build          # Build the mod JAR
./gradlew runClient      # Launch Minecraft client with the mod loaded
./gradlew runServer      # Launch a dev server
./gradlew runData        # Regenerate data files (models, recipes, tags, lang)
./gradlew runGameTestServer  # Run game tests
```

`runData` must be run after adding new items/blocks to generate their JSON assets in `src/generated/resources/`.

## Tech Stack

- **NeoForge 21.1.215** on Minecraft 1.21.1 / Java 21
- Key mod dependencies: JEI, Create, Curios API, TerraBlender, Succ's Essentials (base mod)
- No test framework beyond NeoForge's game test server

## Architecture

### Registration Pattern

Every game object type has a `Mod*` registration class (e.g., `ModBlocks`, `ModItems`, `ModRecipes`) that owns a `DeferredRegister` and exposes a `register(IEventBus)` method. The main mod class `Succsessentials_extended.java` calls each one in its constructor.

```java
// Pattern used everywhere:
public static final DeferredRegister.Items ITEMS =
    DeferredRegister.createItems(Succsessentials_extended.MOD_ID);

public static void register(IEventBus eventBus) {
    ITEMS.register(eventBus);
}
```

### Machine System

Machines use a layered API in `api/machine/`:
- `TieredMachine` — machines with `MachineTier` (Basic/Advanced/Elite)
- `GeneratorMachine` — power-generating machines
- `UpgradeableMachine` — machines that accept speed/efficiency upgrade modules

Abstract base classes handle shared logic:
- `AbstractPoweredMachineBlockEntity` — energy storage, item I/O, upgrade slots
- `AbstractGeneratorBlockEntity` — fuel consumption, power output

### Block → Screen Pipeline

Each machine follows this chain:
`Block` → `BlockEntity` (logic + `ContainerData` for sync) → `Menu` (container) → `Screen` (UI)

Energy and fluid display use shared renderer utilities: `EnergyDisplayTooltipArea`, `FluidTankRenderer`.

### Recipe System

Each custom recipe type has three parts: `*Recipe`, `*RecipeInput`, and `*Serializer` (registered in `ModRecipes`). Recipe types: alloy forging, infusing, pulverizing, hammering, wire cutting/drawing, rolling mill.

JEI integration is in `compat/jei/category/` — one category class per recipe type.

### Event Handling

- **MOD bus**: Registration events, setup events (`@EventBusSubscriber(bus = Bus.MOD)`)
- **GAME bus**: Runtime events like right-click hammering/wire-cutting (`@EventBusSubscriber(bus = Bus.GAME)`)

### World Generation

Ores and biomes are added via `worldgen/` using TerraBlender for biome injection and NeoForge biome modifiers for ore placement. Ore configs use data-driven JSON under `data/succsessentials_extended/neoforge/biome_modifier/`.

### Data Generation

Providers in `datagen/` extend NeoForge's data gen classes and are registered via `GatherDataEvent`. After adding a new item or block, the relevant provider must include it, then `runData` regenerates the JSON files in `src/generated/resources/`.

## Adding New Content

**New ore/ingot/nugget set** — touch: `ModItems`, `ModBlocks`, `ModCreativeModeTabs`, `ModTags`, `ModBlockLootTablesProvider`, `ModItemModelProvider`, `ModRecipeProvider`, `en_us.json`, plus ore texture PNGs and worldgen config.

**New machine** — create: Block class, `BlockEntity` extending the appropriate abstract base, `Menu`, `Screen`, then register all four in their respective `Mod*` classes and wire the screen in `Succsessentials_extended` via `RegisterMenuScreensEvent`. Add a JEI category if it has a custom recipe type.
