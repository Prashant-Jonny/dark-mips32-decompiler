package io.github.gleipner.dark.mips32decomposer.instruction.parselet;

import io.github.gleipner.dark.mips32decomposer.instruction.Mnemonic;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a register source or destination.
 */
public class Register {
    /** Pairs address numbers with their corresponding name */
    private static final Map<Integer, String> map = new HashMap<>();
    private final int address;

    public Register(int address) {
        this.address = address;
    }

    public Mnemonic toMnemonic(int address) {
        String name = map.get(address);

        /** Not all registers are named */
        if (Objects.isNull(name)) {
            return new Mnemonic(Integer.toString(address));
        }

        return new Mnemonic(map.get(address));
    }

    public Mnemonic toMnemonic() {
        return toMnemonic(address);
    }

    /** Method used for brevity in the static initializer */
    private static void put(int address, String name) {
        map.put(address, name);
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
            put(i, prefix + i);
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
        put(29, "sp");
        put(30, "fp/s8"); // TODO: Ask lab supervisor about this one.
        put(31, "ra");
    }
}
