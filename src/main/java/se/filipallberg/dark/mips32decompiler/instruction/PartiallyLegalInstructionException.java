package se.filipallberg.dark.mips32decompiler.instruction;

import se.filipallberg.dark.mips32decompiler.instruction.mnemonic.MnemonicRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.util.DecomposedRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.util.Format;

import java.util.Arrays;
import java.util.StringJoiner;

public class PartiallyLegalInstructionException extends RuntimeException {
    private final String message;

    private String[] partialOutput;
    public PartiallyLegalInstructionException(String message) {
        super(message);
        this.message = message;
    }

    public PartiallyLegalInstructionException(int instruction,
                                              Format format,
                                              DecomposedRepresentation d,
                                              MnemonicRepresentation m,
                                              String violatedConditions) {
        partialOutput = new String[] {Instruction.asPaddedHexString
                (instruction),
                format.toString(),
                d.asDecimalString(),
                d.asHexadecimalString(),
                m.toString(),
                violatedConditions};

        StringJoiner sj = new StringJoiner(" ");
        Arrays.stream(partialOutput).forEach(sj::add);
        message = sj.toString();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String[] getPartialOutput() {
        return partialOutput;
    }
}
