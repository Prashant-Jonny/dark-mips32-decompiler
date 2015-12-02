package se.filipallberg.dark.mips32decompiler.instruction;

import se.filipallberg.dark.mips32decompiler.instruction.util.DecomposedRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.util.Format;
import se.filipallberg.dark.mips32decompiler.instruction.util.Opcode;
import se.filipallberg.dark.mips32decompiler.instruction.mnemonic.MnemonicRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.type.ITypeInstruction.ITypeInstruction;
import se.filipallberg.dark.mips32decompiler.instruction.type.JTypeInstruction.JTypeInstruction;
import se.filipallberg.dark.mips32decompiler.instruction.type.RTypeInstruction.RTypeInstruction;

import java.util.Arrays;
import java.util.StringJoiner;

public class Instruction {
    private final int instruction;
    private final Format format;
    private final DecomposedRepresentation decomposedRepresentation;
    private final MnemonicRepresentation mnemonicRepresentation;
    
    public Instruction(int instruction,
                        Format format,
                        DecomposedRepresentation decomposedRepresentation,
                        MnemonicRepresentation mnemonicRepresentation) {
        this.instruction = instruction;
        this.format = format;
        this.decomposedRepresentation = decomposedRepresentation;
        this.mnemonicRepresentation = mnemonicRepresentation;
    }
    
    public static Instruction fromInteger(int instruction) {
        /* Get the opcode of this instruction */
        Opcode op = Opcode.fromInstruction(instruction);

        /* Opcodes map uniquely to a format, get the associated format */
        Format format = Format.fromOpcode(op);
        if (format == Format.R) {
            return RTypeInstruction.fromNumericalRepresentation(instruction);
        } else if (format == Format.I) {
            return ITypeInstruction.fromNumericalRepresentation(instruction);
        } else if (format == Format.J) {
            return JTypeInstruction.fromNumericalRepresentation(instruction);
        }
        throw new IllegalArgumentException(
                "The instruction: " + instruction + " with opcode: " +
                        Opcode.toNumericalRepresentation(instruction) +
                        " is not associated with any Format (R, I, J)"
        );
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

    public int toNumericalRepresentation() {
        return instruction;
    }

    @Override
    public String toString() {
        // Pad the string with leading zeroes. Target length is 10
        // characters (including 0x). So for instance, the number
        // 0x3e00008
        // should be written as
        // 0x03e00008

        // Creates a hexadecimal string without the 0x prefix, target
        // length is 8 characters.
        String hexString = Integer.toHexString(instruction);

        while (hexString.length() < 8) {
            hexString = "0" + hexString;
        }
        String formattedHexString = "0x" + hexString;

        String[] representations = {
                formattedHexString,
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
