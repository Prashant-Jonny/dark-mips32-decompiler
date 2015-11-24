package se.filipallberg.dark.mips32decompiler.instruction.type;

import se.filipallberg.dark.mips32decompiler.instruction.DecomposedRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.InstructionName;
import se.filipallberg.dark.mips32decompiler.instruction.format.Format;
import se.filipallberg.dark.mips32decompiler.instruction.opcode.Opcode;
import se.filipallberg.dark.mips32decompiler.instruction.mnemonic.MnemonicPattern;
import se.filipallberg.dark.mips32decompiler.instruction.mnemonic.MnemonicRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.type.RTypeInstruction.RTypeInstruction;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

public class Instruction {
    private final int instruction;
    private final Format format;
    private final String iname;
    private final DecomposedRepresentation decomposedRepresentation;
    private final MnemonicRepresentation mnemonicRepresentation;
    
    public Instruction(int instruction,
                        Format format,
                        String iname,
                        DecomposedRepresentation decomposedRepresentation,
                        MnemonicRepresentation mnemonicRepresentation) {
        this.instruction = instruction;
        this.format = format;
        this.iname = iname;
        this.decomposedRepresentation = decomposedRepresentation;
        this.mnemonicRepresentation = mnemonicRepresentation;
    }
    
    public static Instruction fromInteger(int instruction) {
        /* Get the opcode of this instruction */
        Opcode op = Opcode.fromInstruction(instruction);

        /* Get the format from the opcode */
        Format format = Format.fromOpcode(op);
        if (format == Format.R) {
            return RTypeInstruction.toInstruction(instruction);
        }
        return null;
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

    public String getInstructionName() {
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
