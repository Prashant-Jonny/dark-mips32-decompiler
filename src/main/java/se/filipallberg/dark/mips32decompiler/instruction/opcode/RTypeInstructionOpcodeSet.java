package se.filipallberg.dark.mips32decompiler.instruction.opcode;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the set of all the opcodes (in their numerical representation)
 * that are associated with instructions in the R-type format.
 */
public class RTypeInstructionOpcodeSet implements OpcodeSet {
    private static final Set<Opcode> set = new HashSet<>();

    @Override
    public Collection<Opcode> all() {
        return set;
    }

    static {
        OpcodeSet.add(set, 0x00, 0x1c);
    }
}