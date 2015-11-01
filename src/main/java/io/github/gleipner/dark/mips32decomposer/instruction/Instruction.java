package io.github.gleipner.dark.mips32decomposer.instruction;

import io.github.gleipner.dark.mips32decomposer.instruction.parselet.DecomposedRepresentation;

/**
 * Represents an instruction in the MIPS 32 instruction set
 */
public abstract class Instruction {
    private final long instruction;
    private final Format format;
    private final Mnemonic mnemonic;

    public Instruction(int instruction, Format format, String name) {
        this.instruction = Integer.toUnsignedLong(instruction);
        this.format = format;
        mnemonic = Mnemonic.fromString(name);
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

    public abstract DecomposedRepresentation getDecomposedRepresentation();
}
