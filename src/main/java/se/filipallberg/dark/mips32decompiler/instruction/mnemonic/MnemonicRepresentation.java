package se.filipallberg.dark.mips32decompiler.instruction.mnemonic;

import se.filipallberg.dark.mips32decompiler.instruction.Instruction;
import se.filipallberg.dark.mips32decompiler.instruction.type.InstructionType;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.function.Supplier;

public class MnemonicRepresentation {
    private final String stringRepresentation;

    private MnemonicRepresentation(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    public MnemonicRepresentation(String iname, String... strings) {
        String tmp = iname;

        if (strings.length > 0) {
            StringJoiner sj = new StringJoiner(", ");
            Arrays.stream(strings).forEach(sj::add);
            tmp += " " + sj.toString();
        }
        stringRepresentation = tmp;
    }

    public static MnemonicRepresentation fromString(String
                                                      stringRepresentation) {
        return new MnemonicRepresentation(stringRepresentation);
    }

    @Override
    public String toString() {
        return stringRepresentation;
    }
}
