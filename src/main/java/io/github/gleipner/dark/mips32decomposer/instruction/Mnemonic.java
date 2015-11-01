package io.github.gleipner.dark.mips32decomposer.instruction;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

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

    private class MnemonicBuilder {
        private Set<Mnemonic> mnemonics = new HashSet<>();

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
