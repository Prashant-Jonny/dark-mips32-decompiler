package io.github.gleipner.dark.mips32decomposer.instruction;

public class Instruction {
    private final long instruction;

    private final Format format;
    private final Mnemonic mnemonic;

    public Instruction(int instruction, Format format, String name) {
        this.instruction = Integer.toUnsignedLong(instruction);
        this.format = format;
        mnemonic = Mnemonic.fromInstructionName(name);
    }

    public OpCode getOpcode() {
        return OpCode.fromNumericalRepresentation((int) instruction);
    }

    public Format getFormat() {
        return format;
    }

    public Mnemonic getMnemonic() {
        return mnemonic;
    }
}
