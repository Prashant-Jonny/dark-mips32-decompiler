package io.github.gleipner.dark.mips32decomposer.instruction;

public class Instruction {
    private final long instruction;

    public Instruction(int instruction) {
        this.instruction = Integer.toUnsignedLong(instruction);
    }

    public OpCode getOpcode() {
        return OpCode.fromNumericalRepresentation((int) instruction);
    }
}
