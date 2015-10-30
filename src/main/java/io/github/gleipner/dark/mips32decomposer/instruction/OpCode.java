package io.github.gleipner.dark.mips32decomposer.instruction;

import io.github.gleipner.dark.mips32decomposer.ExceptionFunction;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Maps a 6-bit bit-sequence with an appropriate constructor that either
 * corresponds to the specific instruction denoted by the opcode or defers
 * to a constructor that can perform additional inspection of an
 * {@link InputStream} to eventually construct the correct {@link Instruction}.
 */
public enum OpCode {
    J(2, JInstruction::new);

    /** The numerical representation of the opcode */
    public final byte value;

    /**
     * The function corresponding to the constructor of the instruction
     * or another constructor that may continue analysis of the passed
     * input stream to correctly determine the correct instruction.
     */
    public final ExceptionFunction<Instruction, InputStream, IOException>
            constructor;

    OpCode(int value, ExceptionFunction<Instruction,
            InputStream, IOException> constructor) {
        this.value = (byte) value;
        this.constructor = constructor;
    }

    private final static Map<Byte, OpCode> map = new HashMap<>();

    static {
        for (OpCode opCode : OpCode.values()) {
            map.put(opCode.value, opCode);
        }
    }

    public static OpCode getOpcode(final byte op) {
        return map.get(op);
    }
}
