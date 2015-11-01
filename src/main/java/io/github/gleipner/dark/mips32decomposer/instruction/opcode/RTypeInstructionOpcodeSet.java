package io.github.gleipner.dark.mips32decomposer.instruction.opcode;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents all opcodes associated with instructions in the R-type format.
 */
public enum RTypeInstructionOpcodeSet {
    ;
    private static final Set<Integer> set = new HashSet<>();

    public static Collection<Integer> all() {
        return set;
    }

    private static void add(int... opcodes) {
        Arrays.stream(opcodes).forEach(set::add);
    }

    static {
        add(0x00, 0x1c);
    }
}
