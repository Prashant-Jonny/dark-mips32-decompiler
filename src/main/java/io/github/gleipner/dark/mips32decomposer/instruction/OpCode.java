package io.github.gleipner.dark.mips32decomposer.instruction;

import java.util.HashMap;
import java.util.Map;

import static io.github.gleipner.dark.mips32decomposer.instruction.Format.I;
import static io.github.gleipner.dark.mips32decomposer.instruction.Format.J;
import static io.github.gleipner.dark.mips32decomposer.instruction.Format.R;

/**
 * All MIPS32 instructions, 32-bit numbers, have an associated opcode.
 * The opcode is <i>always</i> encased in the 6 leftmost bits. For certain
 * instructions their identity may be inferred solely by their opcode but
 * this is <i>not</i> true for all instructions. However, the opcode is
 * <i>always</i> sufficient to infer the format of the instruction.
 */
public final class OpCode {
    private final int opcode;
    private final Format format;

    private static final Map<Integer, Format> map = new HashMap<>();

    private OpCode(int instruction, Format format) {
        opcode = OpCode.toInteger(instruction);
        this.format = format;
    }

    /**
     * Returns the opcode corresponding to the numerical representation of
     * the given opcode. For instance, the opcode 0x00 corresponds to
     * the R-format.
     *
     * Hence, calling {@code fromNumericalRepresentation(0x00)} yields
     * {@link Format#R}.
     *
     * @param opcode The numerical representation of the opcode.
     * @return a corresponding OpCode instance.
     */
    public static OpCode fromNumericalRepresentation(int opcode) {
        return new OpCode(opcode, map.get(opcode));
    }

    /**
     * Returns the opcode corresponding to the numerical representation of
     * the instruction.
     *
     * For instance, the opcode 0x00 corresponds to the R-format.
     * Hence, calling {@code fromInstruction(0x71014802)} yields
     * {@link Format#R}.
     *
     * @param instruction The numerical representation of a MIPS32 instruction.
     * @return a corresponding OpCode instance.
     */
    public static OpCode fromInstruction(int instruction) {
        int op = toInteger(instruction);
        return new OpCode(op, map.get(op));
    }

    /**
     * Yanks the opcode numerical representation from a 32-bit number
     * corresponding to a known MIPS32 instruction.
     *
     * @param instruction the numerical representation of the instruction
     * @return the numerical representation of the corresponding opcode.
     */
    public static int toInteger(int instruction) {
        return (instruction >> 26) & 0x3f;
    }

    /**
     * Gets the format corresponding to this opcode.
     *
     * @return the format corresponding to this opcode.
     */
    public Format getFormat() {
        return format;
    }

    /**
     * Gets the format of the supplied numerical representation of
     * a MIPS32 instruction.
     *
     * @param instruction the numerical representation of the MIPS32 instruction.
     * @return the format of the instruction.
     */
    public static Format getFormat(int instruction) {
        return map.get(toInteger(instruction));
    }

    /**
     * @return the numerical representation of this opcode.
     */
    public int toInteger() {
        return opcode;
    }

    /**
     * Associates the opcode with the corresponding format.
     */
    private static void put(int opcode, Format format) {
        map.put(opcode, format);
    }

    static {
        /* Pair known opcodes with their corresponding format */
        put(0x00, R);
        put(0x01, I);
        put(0x02, J);
        put(0x03, J);
        put(0x1c, R);
    }
}
