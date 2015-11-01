package io.github.gleipner.dark.mips32decomposer.instruction;

public class Mnemonic {
    private final String stringRepresentation;

    private Mnemonic(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    public static Mnemonic fromString(String s) {
        return new Mnemonic(s);
    }

    public String asString() {
        return stringRepresentation;
    }
}
