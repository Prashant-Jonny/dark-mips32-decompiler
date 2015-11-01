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

    public static OpCode fromNumericalRepresentation(int opcode) {
        return new OpCode(opcode, map.get(opcode));
    }

    public static OpCode fromInstruction(int instruction) {
        int op = toInteger(instruction);
        return new OpCode(op, map.get(op));
    }

    public static int toInteger(int instruction) {
        return (instruction >> 26) & 0x3f;
    }

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
        put(0x00, R);
        put(0x01, I);
        put(0x02, J);
        put(0x03, J);
        put(0x1c, R);
    }
}
