package net.succ.succsessentials_extended.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.succ.succsessentials_extended.block.entity.ModBlockEntities;
import net.succ.succsessentials_extended.item.ModItems;

public class NuclearReactorInputBlockEntity
        extends net.minecraft.world.level.block.entity.BlockEntity {

    // Slot 0 = uranium ingot, Slot 1 = water bucket
    public final ItemStackHandler itemHandler = new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return switch (slot) {
                case 0 -> stack.is(ModItems.URANIUM_INGOT.get()) || stack.is(ModItems.THORIUM_INGOT.get());
                case 1 -> stack.is(Items.WATER_BUCKET);
                default -> false;
            };
        }

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            return isItemValid(slot, stack) ? super.insertItem(slot, stack, simulate) : stack;
        }
    };

    // Accepts water from fluid pipes (10 buckets capacity).
    // fill() is overridden to match only by fluid type, bypassing the strict
    // isSameFluidSameComponents() check that prevents pipes from filling a non-empty tank.
    public final FluidTank waterTank = new FluidTank(10_000) {
        @Override
        public boolean isFluidValid(FluidStack stack) {
            return stack.getFluid().isSame(Fluids.WATER);
        }

        @Override
        public int fill(FluidStack resource, IFluidHandler.FluidAction action) {
            if (resource.isEmpty() || !isFluidValid(resource)) return 0;
            int toFill = Math.min(capacity - getFluidAmount(), resource.getAmount());
            if (toFill <= 0) return 0;
            if (!action.simulate()) {
                if (fluid.isEmpty()) {
                    fluid = new FluidStack(resource.getFluid(), toFill);
                } else {
                    fluid.grow(toFill);
                }
                onContentsChanged();
            }
            return toFill;
        }

        @Override
        protected void onContentsChanged() {
            setChanged();
        }
    };

    public NuclearReactorInputBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.NUCLEAR_REACTOR_INPUT_BE.get(), pos, state);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("Inventory", itemHandler.serializeNBT(registries));
        tag.put("WaterTank", waterTank.writeToNBT(registries, new CompoundTag()));
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        CompoundTag inv = tag.getCompound("Inventory");
        if (inv.contains("Size") && inv.getInt("Size") < 2) {
            inv.putInt("Size", 2);
        }
        itemHandler.deserializeNBT(registries, inv);
        waterTank.readFromNBT(registries, tag.getCompound("WaterTank"));
        super.loadAdditional(tag, registries);
    }
}
