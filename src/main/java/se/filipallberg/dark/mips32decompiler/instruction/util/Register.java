package se.filipallberg.dark.mips32decompiler.instruction.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a register source or destination.
 */
public class Register {
    /** Pairs address numbers with their corresponding name */
    private static final Map<Integer, String> map = new HashMap<>();

    public static String toString(int registerAddress) {
        if (map.containsKey(registerAddress)) {
            return map.get(registerAddress);
        }
        return Integer.toString(registerAddress);
    }

    /** Method used for brevity in the static initializer */
    private static void put(int address, String name) {
        map.put(address, "$" + name);
    }

    /**
     * Associates the (consecutive) range of addresses with names starting
     * with the specified prefix with an incrementing id.
     *
     * For an example, the call put(4, 5, v) pairs the
     * numbers with names like so:
     * 4 -> v0
     * 5 -> v1
     *
     * @param startAddress the starting address.
     * @param stopAddress the last address.
     * @param prefix the start of each register name.
     */
    private static void put(int startAddress, int stopAddress, String prefix) {
        for (int i = startAddress; i <= stopAddress; i++) {
            put(i, prefix + (i - startAddress));
        }
    }

    static {
        /* Associate numbers with their corresponding register names */
        put(0, "zero");
        put(1, "at");
        put(2, 3, "v");
        put(4, 7, "a");
        put(8, 15, "t");
        put(16, 23, "s");
        put(24, "t8");
        put(25, "t9");
        put(26, 27, "k");
        put(28, "gp");
        put(29, "sp"); // Stack pointer
        put(30, "fp"); // Frame pointer
        put(31, "ra");
    }
}