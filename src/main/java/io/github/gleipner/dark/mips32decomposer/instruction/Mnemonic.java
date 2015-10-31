package io.github.gleipner.dark.mips32decomposer.instruction;

public class Mnemonic {
    private final String stringRepresentation;

    private Mnemonic(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    public static Mnemonic fromInstructionName(String name) {
        return new Mnemonic(name);
    }

    public static Mnemonic fromRegisterAddress(int address) {
        return null;
    }
}
