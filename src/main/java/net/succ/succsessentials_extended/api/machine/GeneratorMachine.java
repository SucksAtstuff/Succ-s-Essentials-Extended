package net.succ.succsessentials_extended.api.machine;

/**
 * Implemented by machines that generate energy.
 */
public interface GeneratorMachine {

    /**
     * @return energy generated per tick
     */
    int getGenerationRate();
}
