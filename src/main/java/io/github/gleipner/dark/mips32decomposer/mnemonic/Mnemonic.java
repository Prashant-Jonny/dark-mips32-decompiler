package io.github.gleipner.dark.mips32decomposer.mnemonic;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Represents a mnemonic name for some thing.
 */
public class Mnemonic {
    private final String stringRepresentation;

    public Mnemonic(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    /**
     * Takes a collection of {@link MnemonicYielder}s which are functions
     * that yield a single Mnemonic from some object and
     * concatenates these (in-order).
     *
     * @param mnemonics functions that returns a {@code Mnemonic}.
     */
    public Mnemonic(Iterable<MnemonicYielder> mnemonics) {
        MnemonicBuilder builder = new MnemonicBuilder();
        mnemonics.forEach(builder::add);
        this.stringRepresentation = builder.build().toString();
    }

    @Override
    public String toString() {
        return stringRepresentation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mnemonic mnemonic = (Mnemonic) o;

        return stringRepresentation.equals(mnemonic.stringRepresentation);
    }

    @Override
    public int hashCode() {
        return stringRepresentation.hashCode();
    }

    private class MnemonicBuilder {
        private final List<Mnemonic> mnemonics = new ArrayList<>();

        public MnemonicBuilder add(MnemonicYielder obj) {
            mnemonics.add(obj.toMnemonic());
            return this;
        }

        public Mnemonic build() {
            StringJoiner sj = new StringJoiner(" ");
            mnemonics.forEach(mnemonic -> sj.add(mnemonic.toString()));
            return new Mnemonic(sj.toString());
        }
    }
}
