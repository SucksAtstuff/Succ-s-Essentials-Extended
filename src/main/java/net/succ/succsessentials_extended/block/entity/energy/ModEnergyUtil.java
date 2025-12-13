package net.succ.succsessentials_extended.block.entity.energy;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;

public class ModEnergyUtil {

    /**
     * Tries to move FE from one block position to another.
     *
     * IMPORTANT NOTES:
     * - NeoForge capabilities can return NULL if the block doesn't expose that capability.
     *   So we MUST null-check before calling any IEnergyStorage methods.
     * - We simulate first to avoid extracting more than the receiver can accept.
     */
    public static boolean move(BlockPos from, BlockPos to, int amount, Level level) {

        // ------------------------------------------------------------
        // Grab energy storages from both block positions.
        // These can be NULL if the block has no FE capability.
        // ------------------------------------------------------------
        IEnergyStorage fromStorage = level.getCapability(Capabilities.EnergyStorage.BLOCK, from, null);
        IEnergyStorage toStorage   = level.getCapability(Capabilities.EnergyStorage.BLOCK, to, null);

        // ------------------------------------------------------------
        // HARD SAFETY: prevent the exact crash you got
        // If either side doesn't have FE, we cannot transfer.
        // ------------------------------------------------------------
        if (fromStorage == null || toStorage == null) {
            return false;
        }

        // ------------------------------------------------------------
        // If source cannot extract enough, stop.
        // If target cannot receive any more, stop.
        // (Your original code had these two checks flipped.)
        // ------------------------------------------------------------
        if (!canEnergyStorageExtractThisAmount(fromStorage, amount)) {
            return false;
        }

        if (!canEnergyStorageStillReceiveEnergy(toStorage)) {
            return false;
        }

        // ------------------------------------------------------------
        // Simulate how much the target can accept (DO NOT actually fill it yet)
        // NOTE: your old code passed "true" to receiveEnergy but then extracted
        // with "false" and received with "false" (that part is correct),
        // but the "can receive" check above was inverted.
        // ------------------------------------------------------------
        int maxAmountToReceive = toStorage.receiveEnergy(amount, true);

        // If the receiver would accept 0, there's nothing to do.
        if (maxAmountToReceive <= 0) {
            return false;
        }

        // ------------------------------------------------------------
        // Extract exactly what the receiver can take, then insert it.
        // ------------------------------------------------------------
        int extractedEnergy = fromStorage.extractEnergy(maxAmountToReceive, false);

        // If extraction returns 0, nothing moved.
        if (extractedEnergy <= 0) {
            return false;
        }

        // Actually insert (execute)
        int accepted = toStorage.receiveEnergy(extractedEnergy, false);

        // If for some reason it accepted less than extracted, you *could* refund.
        // Most simple setups won't need it, but returning success only if > 0 moved
        // keeps behavior consistent.
        return accepted > 0;
    }

    /**
     * Returns true if the target storage can still receive at least 1 FE.
     */
    private static boolean canEnergyStorageStillReceiveEnergy(IEnergyStorage toStorage) {
        // No more space OR cannot receive at all
        return toStorage.canReceive()
                && toStorage.getEnergyStored() < toStorage.getMaxEnergyStored();
    }

    /**
     * Returns true if the source storage can extract the requested amount.
     */
    private static boolean canEnergyStorageExtractThisAmount(IEnergyStorage fromStorage, int amount) {
        // Not enough energy OR cannot extract at all
        return fromStorage.canExtract()
                && fromStorage.getEnergyStored() >= amount;
    }

    /**
     * Simple helper: checks if a block entity exists AND exposes an FE capability.
     */
    public static boolean doesBlockHaveEnergyStorage(BlockPos positionToCheck, Level level) {
        return level.getBlockEntity(positionToCheck) != null
                && level.getCapability(Capabilities.EnergyStorage.BLOCK, positionToCheck, null) != null;
    }
}
