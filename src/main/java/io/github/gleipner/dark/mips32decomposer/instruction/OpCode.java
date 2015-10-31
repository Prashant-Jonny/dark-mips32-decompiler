package io.github.gleipner.dark.mips32decomposer.instruction;

/**
 * A named wrapper class representing an opcode.
 */
public class OpCode {
    private final int opcode;

    private OpCode(int instruction) {
        opcode = OpCode.toInteger(instruction);
    }

    public static OpCode fromNumericalRepresentation(int opcode) {
        return new OpCode(opcode);
    }

    public static int toInteger(int instruction) {
        return (instruction >> 26) & 0x3f;
    }

    public int toInteger() {
        return opcode;
    }
}
