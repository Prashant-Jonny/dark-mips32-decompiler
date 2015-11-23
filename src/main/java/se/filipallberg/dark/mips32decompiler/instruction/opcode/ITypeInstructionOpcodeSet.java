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
        OpcodeSet.add(set, 4, 5, 6, 7, 8, 9, 0xa, 0xb, 0xc, 0xd, 0xf, 28,
                29, 0x2a, 0x2b, 0x2e, 30, 38);
    }
}
