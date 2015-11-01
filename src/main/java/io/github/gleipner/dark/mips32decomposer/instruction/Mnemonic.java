package io.github.gleipner.dark.mips32decomposer.instruction;

import java.util.*;

public class Mnemonic {
    private final String stringRepresentation;

    public Mnemonic(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    /** Creates a concatenation of mnemonics */
    public Mnemonic(MnemonicYielder... mnemonics) {
        MnemonicBuilder builder = new MnemonicBuilder();
        Arrays.stream(mnemonics).forEach(builder::add);
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
