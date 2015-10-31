package io.github.gleipner.dark.mips32decomposer.instruction;

public class OpCode {
    private final int opcode;

    private OpCode(int instruction) {
        opcode = (instruction >> 26) & 0x3f;
    }

    public static OpCode fromNumericalRepresentation(int instruction) {
        return new OpCode(instruction);
    }

    public int toInteger() {
        return opcode;
    }
}
