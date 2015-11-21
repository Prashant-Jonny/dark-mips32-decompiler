package se.filipallberg.dark.mips32decompiler.instruction;

import se.filipallberg.dark.mips32decompiler.instruction.opcode.Format;

public class Instruction {
    private final InstructionName iname;
    private final DecomposedRepresentation decomposedRepresentation;
    private final MnemonicRepresentation mnemonicRepresentation;
    private final Format format;

    public Instruction(InstructionName iname,
                       Format format,
                       DecomposedRepresentation decomposedRepresentation,
                       MnemonicRepresentation mnemonicRepresentation) {
        this.iname = iname;
        this.format = format;
        this.decomposedRepresentation = decomposedRepresentation;
        this.mnemonicRepresentation = mnemonicRepresentation;
    }


    public MnemonicRepresentation getMnemonicRepresentation() {
        return mnemonicRepresentation;
    }

    public Format getFormat() {
        return format;
    }

    public String toHexadecimalString() {
        return decomposedRepresentation.toHexadecimalString();
    }

    public String toDecimalString() {
        return decomposedRepresentation.toDecimalString();
    }

    public int toNumericalRepresentation() {
        return decomposedRepresentation.composed();
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "iname=" + iname +
                ", decomposedRepresentationDecimal=" +
                toDecimalString() + ", decomposedRepresentationHex=" +
                toHexadecimalString() +
                ", mnemonicRepresentation=" + mnemonicRepresentation +
                ", format=" + format +
                '}';
    }
}
