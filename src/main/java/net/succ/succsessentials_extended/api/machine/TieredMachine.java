package net.succ.succsessentials_extended.api.machine;

/**
 * Implemented by blocks or block entities that have a tier.
 */
public interface TieredMachine {

    /**
     * @return the machine's tier
     */
    MachineTier getTier();
}
