package net.succ.succsessentials_extended.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.succ.succsessentials_extended.block.custom.ElectricFurnaceBlock;
import net.succ.succsessentials_extended.block.entity.ModBlockEntities;
import net.succ.succsessentials_extended.block.entity.base.AbstractGeneratorBlockEntity;
import net.succ.succsessentials_extended.screen.custom.BioFuelGeneratorMenu;
import net.succ.succsessentials_extended.util.ModTags;
import org.jetbrains.annotations.Nullable;

/**
 * ============================================================
 * BioFuelBlockEntity
 *
 * Simple fuel-based generator using bio mass items.
 *
 * Responsibilities kept here:
 *  - Inventory
 *  - Fuel validation
 *  - Energy generation amount
 *  - Block LIT state
 *
 * Responsibilities moved to AbstractGeneratorBlockEntity:
 *  - Burn timing
 *  - Energy storage
 *  - Energy pushing
 *  - NBT handling
 * ============================================================
 */
public class BiofuelGeneratorBlockEntity extends AbstractGeneratorBlockEntity
        implements MenuProvider {

    /* ================= INVENTORY ================= */

    private static final int INPUT_SLOT = 0;

    public final ItemStackHandler itemHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            }
        }
    };

    /* ================= DATA SYNC ================= */

    private final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> burnProgress;
                case 1 -> maxBurnProgress;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            if (index == 0) burnProgress = value;
            if (index == 1) maxBurnProgress = value;
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    /* ================= CONSTRUCTOR ================= */

    public BiofuelGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(
                ModBlockEntities.BIO_FUEL_GENERATOR_BE.get(),
                pos,
                state,
                64000, // ENERGY CAPACITY
                640,   // ENERGY TRANSFER RATE
                80    // BURN TIME PER FUEL
        );
    }

    /* ================= GENERATOR LOGIC ================= */

    /**
     * Determines whether valid fuel exists.
     */
    @Override
    protected boolean hasFuel() {
        return itemHandler.getStackInSlot(INPUT_SLOT).is(ModTags.Items.BIO_FUELS);
    }

    /**
     * Consumes one fuel item when burning starts.
     */
    @Override
    protected void consumeFuel() {
        itemHandler.extractItem(INPUT_SLOT, 1, false);
    }

    /**
     * Generates energy every tick while burning.
     */
    @Override
    protected void generateEnergy() {
        energyStorage.receiveEnergy(320, false);
    }

    /**
     * Defines how much energy this generator can push per tick.
     * Used by the generator base.
     */
    @Override
    protected int getEnergyTransferRate() {
        return 320;
    }

    /* ================= BLOCK STATE ================= */

    @Override
    protected void setLitState(boolean lit) {
        if (level == null) return;

        BlockState state = getBlockState();
        if (state.getValue(ElectricFurnaceBlock.LIT) != lit) {
            level.setBlock(worldPosition, state.setValue(ElectricFurnaceBlock.LIT, lit), 3);
        }
    }

    /* ================= MENU ================= */

    @Override
    public Component getDisplayName() {
        return Component.literal("Bio Fuel Generator");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new BioFuelGeneratorMenu(id, inv, this, data);
    }

    /* ================= DROPS ================= */

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(level, worldPosition, inventory);
    }

    /* ================= NETWORK ================= */

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }
}
