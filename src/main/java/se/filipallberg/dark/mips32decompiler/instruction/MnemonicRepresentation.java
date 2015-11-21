package se.filipallberg.dark.mips32decompiler.instruction;

import java.util.Arrays;
import java.util.StringJoiner;

public class MnemonicRepresentation {
    private final InstructionName iname;
    private final String[] instructionParameters;
    private final String stringRepresentation;

    public MnemonicRepresentation(InstructionName iname,
                                  String... instructionParameters) {
        this.iname = iname;
        this.instructionParameters = instructionParameters;
        StringJoiner sj = new StringJoiner(", ");
        Arrays.stream(instructionParameters).forEach(sj::add);
        stringRepresentation = iname.toString() + " " + sj.toString();
    }

    @Override
    public String toString() {
        return stringRepresentation;
    }
}
