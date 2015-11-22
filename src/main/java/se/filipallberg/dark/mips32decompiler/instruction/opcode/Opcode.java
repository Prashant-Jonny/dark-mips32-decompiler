package se.filipallberg.dark.mips32decompiler.instruction.opcode;

/**
 * All MIPS32 instructions, 32-bit numbers, have an associated opcode.
 * The opcode is <i>always</i> encased in the leftmost 6-bits.
 *
 * An {@link Opcode} is capable of instantiating itself from either an
 * numerical instruction of a MIPS32 instruction, but also from a 6-bit
 * numerical representation of itself.
 */
public class Opcode {
    private final int opcode;

    private Opcode(int opcode) {
        this.opcode = opcode;
    }

    /**
     * Get the numerical representation of an opcode out of a 32-bit numerical
     * representation of a MIPS32 instruction.
     *
     * @param instruction the numerical representation of the instruction.
     * @return the value of the leftmost 6-bits of the instruction.
     */
    public static int toNumericalRepresentation(int instruction) {
        return (instruction >> 26) & 0x3f;
    }

    /**
     * Returns the opcode corresponding to the numerical representation of
     * the given opcode.
     *
     * @param opcode The numerical representation of the opcode.
     * @return a corresponding Opcode instance.
     */
    public static Opcode fromNumericalRepresentation(int opcode) {
        return new Opcode(opcode);
    }

    /**
     * Constructs an opcode from a 32-bit number representing a MIPS32
     * instruction.
     *
     * @param instruction the 32-bit representation of the instruction
     * @return the corresponding Opcode instance.
     */
    public static Opcode fromInstruction(int instruction) {
        return new Opcode(toNumericalRepresentation(instruction));
    }

    public int toNumericalRepresentation() {
        return opcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Opcode opcode1 = (Opcode) o;

        return opcode == opcode1.opcode;

    }

    @Override
    public int hashCode() {
        return opcode;
    }
}