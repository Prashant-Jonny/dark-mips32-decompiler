package se.filipallberg.dark.mips32decompiler.instruction.opcode;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ITypeInstructionOpcodeSet implements OpcodeSet {
    private static final Set<Opcode> set = new HashSet<>();

    @Override
    public Collection<Opcode> all() {
        return set;
    }

    static {
        OpcodeSet.add(set, 8, 9, 0xc, 43);
    }
}
