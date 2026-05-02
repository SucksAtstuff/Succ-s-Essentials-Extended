package net.succ.succsessentials_extended.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.succ.succsessentials_extended.api.multiblock.MultiblockController;
import net.succ.succsessentials_extended.api.multiblock.MultiblockStructure;
import net.succ.succsessentials_extended.block.ModBlocks;
import net.succ.succsessentials_extended.block.custom.NuclearReactorControllerBlock;
import net.succ.succsessentials_extended.block.entity.ModBlockEntities;
import net.succ.succsessentials_extended.block.entity.base.AbstractGeneratorBlockEntity;
import net.succ.succsessentials_extended.item.ModItems;
import net.succ.succsessentials_extended.screen.custom.NuclearReactorControllerBlockMenu;
import net.succ.succsessentials_extended.util.energy.ModEnergyStorage;
import org.jetbrains.annotations.Nullable;

/**
 * ============================================================
 * NuclearReactorControllerBlockEntity
 *
 * Central brain of the reactor.
 *
 * This is a CONTINUOUS generator (not burn-based).
 * It overrides energy storage creation so it can
 * RECEIVE internally generated FE.
 * ============================================================
 */
public class NuclearReactorControllerBlockEntity
        extends AbstractGeneratorBlockEntity
        implements MenuProvider, MultiblockController {

    /* no item slots on the controller — uranium and water are inserted via the input block */

    /* ======================== MULTIBLOCK ======================== */

    // 3×3×3 structure. Controller sits at the front-center of the middle layer.
    // Depth: forward=0 (front/player side) → forward=2 (back)
    // The cross center of each 3×3 layer is at (right=0, forward=1).
    //
    // Bottom (y=-1) & Top (y=+1): cross — corners=Panel, cardinals=Lead, center=Core
    // Middle (y=0): Lead↔Panel swapped — front row (forward=0) = Input/Ctrl/Output
    //
    // Viewed from above (middle layer):
    //   fwd=0: [Input ] [Ctrl ] [Output]   ← facing player
    //   fwd=1: [Panel ] [Core ] [Panel ]
    //   fwd=2: [Lead  ] [Panel] [Lead  ]
    private static final MultiblockStructure STRUCTURE = MultiblockStructure.builder()
            // --- Bottom layer (y = -1) ---
            .require(-1, -1, 0, ModBlocks.PANEL_BLOCK.get())           // front-left corner
            .require( 0, -1, 0, ModBlocks.LEAD_BLOCK.get())            // front cardinal
            .require( 1, -1, 0, ModBlocks.PANEL_BLOCK.get())           // front-right corner
            .require(-1, -1, 1, ModBlocks.LEAD_BLOCK.get())            // mid-left cardinal
            .require( 0, -1, 1, ModBlocks.NUCLEAR_REACTOR_CORE.get())  // center (reactor rod)
            .require( 1, -1, 1, ModBlocks.LEAD_BLOCK.get())            // mid-right cardinal
            .require(-1, -1, 2, ModBlocks.PANEL_BLOCK.get())           // back-left corner
            .require( 0, -1, 2, ModBlocks.LEAD_BLOCK.get())            // back cardinal
            .require( 1, -1, 2, ModBlocks.PANEL_BLOCK.get())           // back-right corner
            // --- Middle layer (y = 0) --- front row replaced by machine blocks
            .require(-1,  0, 0, ModBlocks.NUCLEAR_REACTOR_INPUT.get())  // input
            .require( 1,  0, 0, ModBlocks.NUCLEAR_REACTOR_OUTPUT.get()) // output
            .require(-1,  0, 1, ModBlocks.PANEL_BLOCK.get())            // mid-left (lead→panel)
            .require( 0,  0, 1, ModBlocks.NUCLEAR_REACTOR_CORE.get())   // center core
            .require( 1,  0, 1, ModBlocks.PANEL_BLOCK.get())            // mid-right (lead→panel)
            .require(-1,  0, 2, ModBlocks.LEAD_BLOCK.get())             // back-left (panel→lead)
            .require( 0,  0, 2, ModBlocks.PANEL_BLOCK.get())            // back cardinal (lead→panel)
            .require( 1,  0, 2, ModBlocks.LEAD_BLOCK.get())             // back-right (panel→lead)
            // --- Top layer (y = +1) — identical to bottom ---
            .require(-1,  1, 0, ModBlocks.PANEL_BLOCK.get())
            .require( 0,  1, 0, ModBlocks.LEAD_BLOCK.get())
            .require( 1,  1, 0, ModBlocks.PANEL_BLOCK.get())
            .require(-1,  1, 1, ModBlocks.LEAD_BLOCK.get())
            .require( 0,  1, 1, ModBlocks.NUCLEAR_REACTOR_CORE.get())
            .require( 1,  1, 1, ModBlocks.LEAD_BLOCK.get())
            .require(-1,  1, 2, ModBlocks.PANEL_BLOCK.get())
            .require( 0,  1, 2, ModBlocks.LEAD_BLOCK.get())
            .require( 1,  1, 2, ModBlocks.PANEL_BLOCK.get())
            .build();

    /* ======================== FUEL RATES ======================== */

    // FE/t generated per fuel type — add plutonium/polonium here when items exist
    public static final int RATE_THORIUM  =  60;   // 0.5x — low-grade fissile
    public static final int RATE_URANIUM  = 120;   // 1.0x — baseline
    // public static final int RATE_PLUTONIUM = 240; // 2.0x
    // public static final int RATE_POLONIUM  = 480; // 4.0x

    private static int getFuelRate(net.minecraft.world.item.ItemStack stack) {
        if (stack.is(ModItems.THORIUM_INGOT.get()))  return RATE_THORIUM;
        if (stack.is(ModItems.URANIUM_INGOT.get()))  return RATE_URANIUM;
        // if (stack.is(ModItems.PLUTONIUM_INGOT.get())) return RATE_PLUTONIUM;
        // if (stack.is(ModItems.POLONIUM_INGOT.get()))  return RATE_POLONIUM;
        return 0;
    }

    private static boolean isFuel(net.minecraft.world.item.ItemStack stack) {
        return getFuelRate(stack) > 0;
    }

    /* ======================== STATE ======================== */

    public static final int MAX_WATER_TICKS = 1200; // 1 bucket = 60 seconds
    private static final int STRUCTURE_CHECK_INTERVAL = 20;
    private static final int OVERHEAT_LIMIT = 100; // 5 seconds without water while running
    private boolean formed = false;
    private boolean isRunning = false;
    private int tickCounter = 0;
    private int waterTicks = 0;
    private int overheatTicks = 0;
    private int cachedTankWater = 0; // client-side mirror of the input block's fluid tank
    private int currentFuelRate = RATE_URANIUM; // FE/t for the active burn cycle

    public final ContainerData data = new ContainerData() {
        @Override public int get(int index) {
            return switch (index) {
                case 0 -> waterTicks;
                case 1 -> MAX_WATER_TICKS;
                case 2 -> overheatTicks;
                case 3 -> { // actual mB in the fluid tank
                    if (level != null && !level.isClientSide()) {
                        NuclearReactorInputBlockEntity input = getInputBE();
                        yield input != null ? input.waterTank.getFluidAmount() : 0;
                    }
                    yield cachedTankWater;
                }
                case 4 -> 10_000; // tank capacity in mB
                case 5 -> isRunning ? 1 : 0;
                default -> 0;
            };
        }
        @Override public void set(int index, int value) {
            if (index == 0) waterTicks = value;
            if (index == 2) overheatTicks = value;
            if (index == 3) cachedTankWater = value;
            if (index == 5) isRunning = value != 0;
        }
        @Override public int getCount() { return 6; }
    };

    /* ======================== CONSTRUCTOR ======================== */

    public NuclearReactorControllerBlockEntity(BlockPos pos, BlockState state) {
        super(
                ModBlockEntities.NUCLEAR_REACTOR_CONTROLLER_BE.get(),
                pos,
                state,
                1_000_000, // ENERGY CAPACITY
                4_000,     // ENERGY TRANSFER RATE
                200        // Dummy burn time (unused)
        );
    }

    /* ============================================================
       ENERGY STORAGE OVERRIDE
       ============================================================ */

    @Override
    protected ModEnergyStorage createEnergyStorage(int capacity, int transferRate) {
        return new ModEnergyStorage(
                capacity,
                transferRate, // maxReceive (generation)
                transferRate  // maxExtract (output)
        ) {
            @Override
            public void onEnergyChanged() {
                setChanged();
                if (level != null && !level.isClientSide()) {
                    level.sendBlockUpdated(
                            worldPosition,
                            getBlockState(),
                            getBlockState(),
                            3
                    );
                }
            }
        };
    }

    /* ======================== TICK ======================== */

    public static void tick(Level level,
                            BlockPos pos,
                            BlockState state,
                            NuclearReactorControllerBlockEntity be) {
        if (level.isClientSide()) return;

        be.tickCounter++;

        if (be.tickCounter % STRUCTURE_CHECK_INTERVAL == 0) {
            be.updateStructureState((ServerLevel) level, pos, state);
        }

        if (be.formed) {
            if (!be.isRunning && be.isBurning) {
                be.isBurning = false;
                be.burnProgress = be.maxBurnProgress;
            }
            be.convertBucketsToFluid();
            be.handleWater();
            be.handleOverheat(level, pos);
            be.runGeneratorTick(level, pos, state);
        } else {
            be.overheatTicks = 0;
            be.setLitState(false);
        }
    }

    private void runGeneratorTick(Level level, BlockPos pos, BlockState state) {
        super.tick(level, pos, state);
    }

    private void updateStructureState(ServerLevel level,
                                      BlockPos pos,
                                      BlockState state) {
        Direction facing = state.getValue(NuclearReactorControllerBlock.FACING);
        boolean nowFormed = STRUCTURE.validate(level, pos, facing);
        if (formed != nowFormed) {
            formed = nowFormed;

            level.setBlock(
                    pos,
                    state.setValue(NuclearReactorControllerBlock.FORMED, formed)
                            .setValue(BlockStateProperties.LIT, formed),
                    Block.UPDATE_CLIENTS
            );

            setChanged();
        }
    }

    /* ======================== IO LOOKUP (RESTORED) ======================== */

    @Nullable
    public NuclearReactorInputBlockEntity getInputBE() {
        if (level == null) return null;

        Direction facing = getBlockState()
                .getValue(NuclearReactorControllerBlock.FACING);

        BlockPos pos = worldPosition.relative(facing.getCounterClockWise());

        return level.getBlockEntity(pos) instanceof NuclearReactorInputBlockEntity be
                ? be
                : null;
    }

    @Nullable
    public NuclearReactorOutputBlockEntity getOutputBE() {
        if (level == null) return null;

        Direction facing = getBlockState()
                .getValue(NuclearReactorControllerBlock.FACING);

        BlockPos pos = worldPosition.relative(facing.getClockWise());

        return level.getBlockEntity(pos) instanceof NuclearReactorOutputBlockEntity be
                ? be
                : null;
    }

    /* ======================== OVERHEAT ======================== */

    private void handleOverheat(Level level, BlockPos pos) {
        if (isBurning && waterTicks <= 0) {
            overheatTicks++;
            if (overheatTicks >= OVERHEAT_LIMIT) {
                level.explode(null,
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                        7.0f, Level.ExplosionInteraction.TNT);
                overheatTicks = 0;
            }
        } else {
            overheatTicks = 0;
        }
    }

    /* ======================== WATER ======================== */

    // Converts any water bucket sitting in input slot 1 into fluid in the tank immediately.
    // This lets multiple buckets accumulate and keeps the slot available for the empty bucket.
    private void convertBucketsToFluid() {
        NuclearReactorInputBlockEntity input = getInputBE();
        if (input == null) return;
        ItemStack bucket = input.itemHandler.getStackInSlot(1);
        if (!bucket.is(Items.WATER_BUCKET)) return;
        int filled = input.waterTank.fill(
                new net.neoforged.neoforge.fluids.FluidStack(net.minecraft.world.level.material.Fluids.WATER, 1000),
                net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction.EXECUTE);
        if (filled > 0) {
            input.itemHandler.setStackInSlot(1, new ItemStack(Items.BUCKET));
        }
    }

    // Draws 1000 mB from the fluid tank every MAX_WATER_TICKS ticks while burning.
    private void handleWater() {
        if (!isBurning) return;
        if (waterTicks > 0) {
            waterTicks--;
            return;
        }
        NuclearReactorInputBlockEntity input = getInputBE();
        if (input == null) return;
        if (input.waterTank.getFluidAmount() >= 1000) {
            input.waterTank.drain(1000, net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction.EXECUTE);
            waterTicks = MAX_WATER_TICKS;
        }
    }

    /* ======================== ENERGY ======================== */

    public boolean isRunning() { return isRunning; }

    public void setRunning(boolean running) {
        this.isRunning = running;
        setChanged();
    }

    @Override
    protected boolean hasFuel() {
        if (!formed || !isRunning) return false;
        NuclearReactorInputBlockEntity input = getInputBE();
        if (input == null || !isFuel(input.itemHandler.getStackInSlot(0))) return false;
        NuclearReactorOutputBlockEntity output = getOutputBE();
        if (output == null) return false;
        ItemStack waste = output.itemHandler.getStackInSlot(0);
        return waste.isEmpty()
                || (waste.is(ModItems.NUCLEAR_WASTE.get()) && waste.getCount() < waste.getMaxStackSize());
    }

    @Override
    protected void consumeFuel() {
        NuclearReactorInputBlockEntity input = getInputBE();
        NuclearReactorOutputBlockEntity output = getOutputBE();
        if (input == null || output == null) return;
        ItemStack fuel = input.itemHandler.getStackInSlot(0);
        currentFuelRate = getFuelRate(fuel);
        input.itemHandler.extractItem(0, 1, false);
        output.itemHandler.insertItem(0, new ItemStack(ModItems.NUCLEAR_WASTE.get()), false);
    }

    @Override
    protected void generateEnergy() {
        if (isRunning && waterTicks > 0) {
            energyStorage.receiveEnergy(currentFuelRate, false);
        }
    }

    @Override
    protected int getEnergyTransferRate() {
        return 4_000;
    }

    @Override
    public int getPowerGenerationRate() {
        return currentFuelRate;
    }

    @Override
    protected void setLitState(boolean lit) {
        if (level == null) return;

        BlockState state = getBlockState();
        if (state.getValue(BlockStateProperties.LIT) != lit) {
            level.setBlock(
                    worldPosition,
                    state.setValue(BlockStateProperties.LIT, lit),
                    Block.UPDATE_CLIENTS
            );
        }
    }

    /* ======================== SAVE / LOAD ======================== */

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putBoolean("formed", formed);
        tag.putBoolean("isRunning", isRunning);
        tag.putInt("waterTicks", waterTicks);
        tag.putInt("overheatTicks", overheatTicks);
        tag.putInt("currentFuelRate", currentFuelRate);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        formed = tag.getBoolean("formed");
        isRunning = tag.getBoolean("isRunning");
        waterTicks = tag.getInt("waterTicks");
        overheatTicks = tag.getInt("overheatTicks");
        if (tag.contains("currentFuelRate")) currentFuelRate = tag.getInt("currentFuelRate");
    }

    /* ======================== MENU ======================== */

    @Override
    public Component getDisplayName() {
        return Component.translatable(
                "block.succsessentials_extended.nuclear_reactor_controller"
        );
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(
            int id,
            Inventory inventory,
            Player player
    ) {
        return new NuclearReactorControllerBlockMenu(id, inventory, this);
    }

    public boolean isFormed() {
        // On the client, read the synced block state rather than the un-synced field.
        if (level != null && level.isClientSide()) {
            return getBlockState().getValue(NuclearReactorControllerBlock.FORMED);
        }
        return formed;
    }

    @Override
    public MultiblockStructure getStructure() {
        return STRUCTURE;
    }

    @Override
    public Direction getFacing() {
        return getBlockState().getValue(NuclearReactorControllerBlock.FACING);
    }
}
