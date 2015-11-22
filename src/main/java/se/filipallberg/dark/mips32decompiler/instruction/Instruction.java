package se.filipallberg.dark.mips32decompiler.instruction;

import se.filipallberg.dark.mips32decompiler.instruction.format.Format;
import se.filipallberg.dark.mips32decompiler.instruction.opcode.Opcode;
import se.filipallberg.dark.mips32decompiler.instruction.mnemonic.MnemonicPattern;
import se.filipallberg.dark.mips32decompiler.instruction.mnemonic.MnemonicRepresentation;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

public class Instruction {
    private final int instruction;
    private final Format format;
    private final InstructionName iname;
    private final DecomposedRepresentation decomposedRepresentation;
    private final MnemonicRepresentation mnemonicRepresentation;

    private Instruction(int instruction) {
        this.instruction = instruction;

        /* Get the opcode of this instruction */
        Opcode op = Opcode.fromInstruction(instruction);

        /* Get the format from the opcode */
        format = Format.fromOpcode(op);

        /* Get the name of this instruction */
        iname = format.getName(op, instruction);

        if (Objects.isNull(iname)) {
            String err = "The instruction: " + instruction
                    + " with opcode: " + op.toNumericalRepresentation()
                    + " is not associated with any known instruction.";
            throw new IllegalStateException(err);
        }

        decomposedRepresentation = format.getDecomposedRepresentation
                (instruction);

        MnemonicPattern rule = iname.getMnemonicPattern();

        mnemonicRepresentation = rule.apply(iname,
                decomposedRepresentation);
    }

    public static Instruction fromInteger(int instruction) {
        return new Instruction(instruction);
    }

    public String asHexadecimalString() {
        return decomposedRepresentation.asHexadecimalString();
    }

    public String asDecimalString() {
        return decomposedRepresentation.asDecimalString();
    }

    public String mnemonic() {
        return mnemonicRepresentation.toString();
    }

    public Format getFormat() {
        return format;
    }

    public InstructionName getInstructionName() {
        return iname;
    }

    public int toNumericalRepresentation() {
        return instruction;
    }

    @Override
    public String toString() {
        String[] representations = {
                Integer.toString(instruction),
                format.toString(),
                asDecimalString(),
                asHexadecimalString(),
                mnemonic()
        };
        StringJoiner sj = new StringJoiner(" ");
        Arrays.stream(representations).forEach(sj::add);
        return sj.toString();
    }
}
