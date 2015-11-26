package se.filipallberg.dark.mips32decompiler.instruction.mnemonic;

import java.util.Arrays;
import java.util.StringJoiner;

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
