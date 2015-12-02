package se.filipallberg.dark.mips32decompiler.instruction;

import se.filipallberg.dark.mips32decompiler.instruction.mnemonic.MnemonicRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.util.DecomposedRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.util.Format;

import java.util.StringJoiner;

public class PartiallyLegalInstructionException extends RuntimeException {
    private final String message;
    public PartiallyLegalInstructionException(String message) {
        super(message);
        this.message = message;
    }

    public PartiallyLegalInstructionException(int instruction,
                                              Format format,
                                              DecomposedRepresentation d,
                                              MnemonicRepresentation m,
                                              String violatedConditions) {
        StringJoiner sj = new StringJoiner(" ");
        sj.add("0x" + Integer.toHexString(instruction));
        sj.add(format.toString());
        sj.add(d.asDecimalString());
        sj.add(d.asHexadecimalString());
        sj.add(m.toString());
        sj.add(violatedConditions);
        message = sj.toString();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
