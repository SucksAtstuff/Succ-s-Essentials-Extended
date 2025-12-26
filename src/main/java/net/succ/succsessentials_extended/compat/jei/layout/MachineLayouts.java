package net.succ.succsessentials_extended.compat.jei.layout;

/**
 * Central registry of JEI machine layouts.
 *
 * One layout per machine.
 * All coordinates are texture-local (JEI offset baked in).
 */
public final class MachineLayouts {

    private MachineLayouts() {}

    /**
     * ALLOY FORGER
     */
    public static final MachineLayout ALLOY_FORGER =
            new MachineLayout(
                    56, 25,   // input A
                    56, 44,   // input B
                    116, 35,  // output
                    -1, -1,   // no byproduct
                    11, 11     // energy bar
            );

    /**
     * INFUSER
     */
    public static final MachineLayout INFUSER =
            new MachineLayout(
                    56, 25,   // input A
                    56, 44,   // input B
                    116, 35,  // output
                    -1, -1,   // no byproduct
                    11, 11     // energy bar
            );

    /**
     * PULVERIZER
     *
     * Main output matches other machines.
     * Byproduct is stacked below it.
     */
    public static final MachineLayout PULVERIZER =
            new MachineLayout(
                    56, 35,   // input
                    -1, -1,   // no secondary input
                    116, 35,  // main output
                    116, 59,  // byproduct (below output)
                    11, 11     // energy bar
            );

    /**
     * ELECTRIC FURNACE
     *
     * Vanilla-style furnace layout
     */
    public static final MachineLayout ELECTRIC_FURNACE =
            new MachineLayout(
                    56, 35,   // input
                    -1, -1,   // no secondary input
                    116, 35,  // output
                    -1, -1,   // no byproduct
                    11, 11    // energy bar
            );


    /**
     * ROLLING MILL
     */
    public static final MachineLayout ROLLING_MILL =
            new MachineLayout(
                    56, 35,   // input
                    -1, -1,   // no secondary input
                    116, 35,  // output
                    -1, -1,   // no byproduct
                    11, 11    // energy bar
            );

    /**
     * WIRE DRAWER
     */
    public static final MachineLayout WIRE_DRAWER =
            new MachineLayout(
                    56, 35,   // input
                    -1, -1,   // no secondary input
                    116, 35,  // output
                    -1, -1,   // no byproduct
                    11, 11    // energy bar
            );

}
