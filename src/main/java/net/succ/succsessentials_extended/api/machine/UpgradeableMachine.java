package net.succ.succsessentials_extended.api.machine;

import net.neoforged.neoforge.items.ItemStackHandler;

/**
 * ============================================================
 * UpgradeableMachine
 *
 * Implemented by any machine that supports upgrade modules.
 *
 * This interface exposes:
 *  - Upgrade inventory access
 *  - Upgrade slot count
 *  - Derived upgrade effects (speed & energy)
 *
 * UI and menus must ONLY depend on this interface.
 * ============================================================
 */
public interface UpgradeableMachine {

    /* ================= INVENTORY ================= */

    /**
     * Inventory that stores upgrade modules.
     * Slot 0 = Speed
     * Slot 1 = Efficiency
     */
    ItemStackHandler getUpgradeInventory();

    /**
     * Number of upgrade slots this machine supports.
     * (Always 2 for now, but future-proofed.)
     */
    int getUpgradeSlotCount();

    /**
     * Called whenever an upgrade stack changes.
     * Used to recompute cached values.
     */
    void onUpgradesChanged();

    /* ================= DERIVED EFFECTS ================= */

    /**
     * @return Speed multiplier derived from speed upgrades.
     * Example: 1.0 = normal, 2.0 = twice as fast
     */
    double getSpeedMultiplier();

    /**
     * @return Energy usage multiplier derived from efficiency upgrades.
     * Example: 1.0 = normal, 0.5 = half energy usage
     */
    double getEnergyUsageMultiplier();
}
