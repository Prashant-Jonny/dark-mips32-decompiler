package se.filipallberg.dark.mips32decompiler.instruction;

public class PartiallyLegalInstructionException extends RuntimeException {
    public PartiallyLegalInstructionException(String errorMessage) {
        super(errorMessage);
    }
}
