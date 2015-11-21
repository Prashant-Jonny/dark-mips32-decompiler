package se.filipallberg.dark.mips32decompiler.instruction.opcode;

import java.util.HashMap;
import java.util.Map;

/**
 * All MIPS32 instructions, 32-bit numbers, have an associated opcode.
 * The opcode is <i>always</i> encased in the leftmost 6-bits.
 *
 * For certain instructions their identity may be inferred solely by their
 * opcode but this is <i>not</i> true for all instructions. However, the
 * opcode is <i>always</i> sufficient to infer the format of the
 * instruction.
 *
 * An {@link Opcode} is capable of instantiating itself from either an
 * numerical instruction of a MIPS32 instruction, but also from a 6-bit
 * numerical representation of itself.
 *
 * As stated, the format may always be inferred from the opcode alone, hence
 * all {@link Opcode} instances know and may supply their associated format.
 */
public class Opcode {
    private final int opcode;

    /**
     * Pairs integer representation of opcodes together with the
     * corresponding format.
     */
    private static final Map<Integer, Format> map = new HashMap<>();

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
     * the given opcode. For instance, the opcode 0x00 corresponds to
     * the R-format.
     *
     * Hence, calling {@code fromNumericalRepresentation(0x00)} yields
     * an {@link Opcode} that has {@link Format#R}.
     *
     * @param opcode The numerical representation of the opcode.
     * @return a corresponding OpCode instance.
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

    public static Format getFormatFromInstruction(int instruction) {
        return map.get(toNumericalRepresentation(instruction));
    }

    public static Format getFormatFromNumericalRepresentation(int opcode) {
        return map.get(opcode);
    }

    public Format getFormat() {
        return map.get(opcode);
    }

    static {
        RTypeOpcodeSet.all().forEach(op -> {
            map.put(op, Format.R);
        });
    }
}
