package net.succ.succsessentials_extended.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.succ.succsessentials_extended.api.machine.multi_block_structure.AbstractMultiblock;
import net.succ.succsessentials_extended.block.custom.NuclearReactorControllerBlock;
import net.succ.succsessentials_extended.block.custom.multiblock.NuclearReactorMultiblock;
import net.succ.succsessentials_extended.block.entity.ModBlockEntities;
import net.succ.succsessentials_extended.block.entity.base.AbstractGeneratorBlockEntity;
import net.succ.succsessentials_extended.item.ModItems;
import net.succ.succsessentials_extended.screen.custom.NuclearReactorControllerBlockMenu;
import org.jetbrains.annotations.Nullable;

/**
 * ============================================================
 * NuclearReactorControllerBlockEntity
 *
 * Central brain of the reactor.
 * Handles:
 *  - Multiblock validation
 *  - Fuel consumption (uranium)
 *  - Waste production
 *  - Power generation
 *  - Water handling (NEW)
 * ============================================================
 */
public class NuclearReactorControllerBlockEntity
        extends AbstractGeneratorBlockEntity
        implements MenuProvider {

    /* ======================== SLOT CONSTANTS ======================== */

    public static final int WATER_SLOT = 0;

    /* ======================== INVENTORY HANDLER ======================== */

    public final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return switch (slot) {
                case 2 -> stack.getItem() == Items.WATER_BUCKET;
                default -> false;
            };
        }

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            return isItemValid(slot, stack)
                    ? super.insertItem(slot, stack, simulate)
                    : stack;
        }
    };

    /* ======================== MULTIBLOCK ======================== */

    private static final AbstractMultiblock MULTIBLOCK =
            new NuclearReactorMultiblock();

    /* ======================== STATE ======================== */

    public static final int POWER_GENERATION_RATE = 120;
    private static final int STRUCTURE_CHECK_INTERVAL = 20;
    private boolean formed = false;
    private int tickCounter = 0;

    /* ======================== CONSTRUCTOR ======================== */

    public NuclearReactorControllerBlockEntity(BlockPos pos, BlockState state) {
        super(
                ModBlockEntities.NUCLEAR_REACTOR_CONTROLLER_BE.get(),
                pos,
                state,
                1_000_000,
                4_000,
                200
        );
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
            be.processFuelAndWaste();
            be.handleWater();
            be.runGeneratorTick(level, pos, state);
        } else {
            be.setLitState(false);
        }
    }

    private void runGeneratorTick(Level level, BlockPos pos, BlockState state) {
        super.tick(level, pos, state);
    }

    private void updateStructureState(ServerLevel level,
                                      BlockPos pos,
                                      BlockState state) {
        boolean nowFormed = MULTIBLOCK.validate(level, pos, state);

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

    /* ======================== IO LOOKUP ======================== */

    @Nullable
    public NuclearReactorInputBlockEntity getInputBE() {
        if (level == null) return null;
        Direction facing = getBlockState().getValue(NuclearReactorControllerBlock.FACING);
        BlockPos pos = worldPosition.relative(facing.getCounterClockWise());
        return level.getBlockEntity(pos) instanceof NuclearReactorInputBlockEntity be ? be : null;
    }

    @Nullable
    public NuclearReactorOutputBlockEntity getOutputBE() {
        if (level == null) return null;
        Direction facing = getBlockState().getValue(NuclearReactorControllerBlock.FACING);
        BlockPos pos = worldPosition.relative(facing.getClockWise());
        return level.getBlockEntity(pos) instanceof NuclearReactorOutputBlockEntity be ? be : null;
    }

    /* ======================== FUEL + WASTE ======================== */

    private void processFuelAndWaste() {
        NuclearReactorInputBlockEntity input = getInputBE();
        NuclearReactorOutputBlockEntity output = getOutputBE();

        if (input == null || output == null) return;

        ItemStack fuel = input.itemHandler.getStackInSlot(0);
        if (fuel.isEmpty() || !fuel.is(ModItems.ALUMINIUM_INGOT.get())) return;

        ItemStack waste = output.itemHandler.getStackInSlot(0);
        if (!waste.isEmpty() &&
                (!ItemStack.isSameItemSameComponents(waste, ModItems.BRASS_INGOT.get().getDefaultInstance()) ||
                        waste.getCount() >= waste.getMaxStackSize())) {
            return;
        }

        input.itemHandler.extractItem(0, 1, false);
        output.itemHandler.insertItem(0, new ItemStack(ModItems.BRASS_INGOT.get()), false);
    }

    /* ======================== WATER BUCKET ======================== */

    private void handleWater() {
        ItemStack water = itemHandler.getStackInSlot(WATER_SLOT);
        if (water.getItem() == Items.WATER_BUCKET) {
            itemHandler.setStackInSlot(WATER_SLOT, new ItemStack(Items.BUCKET));
        }
    }

    /* ======================== ENERGY ======================== */

    @Override
    protected boolean hasFuel() {
        return formed;
    }

    @Override
    protected void consumeFuel() {}

    @Override
    protected void generateEnergy() {
        energyStorage.receiveEnergy(getPowerGenerationRate(), false);
    }

    @Override
    protected int getEnergyTransferRate() {
        return 4_000;
    }

    @Override
    public int getPowerGenerationRate() {
        return POWER_GENERATION_RATE;
    }

    @Override
    protected void setLitState(boolean lit) {
        if (level == null) return;

        BlockState state = getBlockState();
        if (state.getValue(BlockStateProperties.LIT) != lit) {
            level.setBlock(worldPosition, state.setValue(BlockStateProperties.LIT, lit), Block.UPDATE_CLIENTS);
        }
    }


    /* ======================== MENU ======================== */

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.succsessentials_extended.nuclear_reactor_controller");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new NuclearReactorControllerBlockMenu(id, inventory, this);
    }

    public boolean isFormed() {
        return formed;
    }
}
