package se.filipallberg.dark.mips32decompiler.instruction.opcode;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public interface OpcodeSet {
    Collection<Opcode> all();

    static void add(Set<Opcode> set, int... opcodes) {
        Arrays.stream(opcodes).forEach(e -> {
            set.add(Opcode.fromNumericalRepresentation(e));
        });
    }
}
