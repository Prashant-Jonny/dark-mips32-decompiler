package se.filipallberg.dark.mips32decompiler.instruction;

public class MnemonicRepresentation {
    private final String stringRepresentation;

    private MnemonicRepresentation(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    public static MnemonicRepresentation fromString(String stringRepresentation) {
        return new MnemonicRepresentation(stringRepresentation);
    }

    @Override
    public String toString() {
        return stringRepresentation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MnemonicRepresentation that = (MnemonicRepresentation) o;

        return stringRepresentation.equals(that.stringRepresentation);

    }

    @Override
    public int hashCode() {
        return stringRepresentation.hashCode();
    }
}
