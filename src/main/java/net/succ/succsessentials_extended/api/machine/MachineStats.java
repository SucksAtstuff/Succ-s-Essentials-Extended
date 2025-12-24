package net.succ.succsessentials_extended.api.machine;

/**
 * Immutable description of a machine's capabilities.
 *
 * This is metadata, NOT logic.
 */
public record MachineStats(
        MachineTier tier,
        int energyGeneration,
        int inputSlots,
        int outputSlots
) {
    public static MachineStats basicMachine(MachineTier tier) {
        return new MachineStats(tier, 0, 1, 1);
    }

    public static MachineStats generator(MachineTier tier, int genRate) {
        return new MachineStats(tier, genRate, 0, 0);
    }
}
