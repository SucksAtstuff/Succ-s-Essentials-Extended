package net.succ.succsessentials_extended.util.energy;

import net.neoforged.neoforge.energy.EnergyStorage;

/**
 * ============================================================
 * ModEnergyStorage
 *
 * Custom energy storage used by Succ's Essentials machines.
 *
 * IMPORTANT:
 * ----------
 * This class MUST support both:
 *  - Burn generators (output-only)
 *  - Continuous generators (reactors, solar, etc.)
 *
 * The original implementation only supported a SINGLE maxTransfer
 * value, which caused ambiguity and silent failures.
 *
 * This corrected version explicitly supports:
 *  - maxReceive
 *  - maxExtract
 * ============================================================
 */
public abstract class ModEnergyStorage extends EnergyStorage {

    /* ============================================================
       CONSTRUCTORS
       ============================================================ */

    /**
     * Output-only constructor
     *
     * Used by burn generators (coal, biofuel, etc).
     * These machines should NEVER receive external FE.
     */
    public ModEnergyStorage(int capacity, int maxExtract) {
        super(capacity, 0, maxExtract);
    }

    /**
     * Full constructor
     *
     * Used by continuous generators (nuclear reactor, solar, etc).
     * These machines MUST be able to receive internally generated FE.
     */
    public ModEnergyStorage(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    /* ============================================================
       ENERGY IO
       ============================================================ */

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int extractedEnergy = super.extractEnergy(maxExtract, simulate);

        // Notify listeners only when energy ACTUALLY changes
        if (extractedEnergy != 0) {
            onEnergyChanged();
        }

        return extractedEnergy;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int receivedEnergy = super.receiveEnergy(maxReceive, simulate);

        // Notify listeners only when energy ACTUALLY changes
        if (receivedEnergy != 0) {
            onEnergyChanged();
        }

        return receivedEnergy;
    }

    /* ============================================================
       INTERNAL SETTER
       ============================================================ */

    /**
     * Directly sets the stored energy.
     * Used for syncing / loading.
     */
    public int setEnergy(int energy) {
        this.energy = energy;
        onEnergyChanged();
        return energy;
    }

    /* ============================================================
       CHANGE CALLBACK
       ============================================================ */

    /**
     * Called whenever the stored energy value changes.
     * Typically used to:
     *  - mark block entity dirty
     *  - sync to client
     *  - update GUIs
     */
    public abstract void onEnergyChanged();
}
