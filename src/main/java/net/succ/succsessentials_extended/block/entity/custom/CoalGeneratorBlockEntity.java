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
import net.succ.succsessentials_extended.screen.custom.CoalGeneratorMenu;
import org.jetbrains.annotations.Nullable;

public class CoalGeneratorBlockEntity extends AbstractGeneratorBlockEntity
        implements MenuProvider {

    /* ================= CONSTANTS ================= */

    /** Energy generated per tick */
    public static final int POWER_GENERATION_RATE = 80;   // FE/t
    public static final int BURN_TIME = 160;


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

    public CoalGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(
                ModBlockEntities.COAL_GENERATOR_BE.get(),
                pos,
                state,
                64000,          // ENERGY CAPACITY
                POWER_GENERATION_RATE, // ENERGY TRANSFER RATE
                BURN_TIME             // BURN TIME PER FUEL
        );
    }

    /* ================= GENERATOR LOGIC ================= */

    @Override
    protected boolean hasFuel() {
        return itemHandler.getStackInSlot(INPUT_SLOT).is(ItemTags.COALS);
    }

    @Override
    protected void consumeFuel() {
        itemHandler.extractItem(INPUT_SLOT, 1, false);
    }

    @Override
    protected void generateEnergy() {
        energyStorage.receiveEnergy(POWER_GENERATION_RATE, false);
    }

    @Override
    protected int getEnergyTransferRate() {
        return POWER_GENERATION_RATE;
    }

    @Override
    public int getPowerGenerationRate() {
        return POWER_GENERATION_RATE;
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
        return Component.literal("Coal Generator");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new CoalGeneratorMenu(id, inv, this, data);
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
