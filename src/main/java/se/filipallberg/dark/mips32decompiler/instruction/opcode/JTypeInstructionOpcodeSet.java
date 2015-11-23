package se.filipallberg.dark.mips32decompiler.instruction.opcode;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class JTypeInstructionOpcodeSet implements OpcodeSet {
    private static final Set<Opcode> set = new HashSet<>();

    @Override
    public Collection<Opcode> all() {
        return set;
    }

    static {
        OpcodeSet.add(set, 2, 3);
    }
}
