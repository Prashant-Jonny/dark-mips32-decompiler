package io.github.gleipner.dark.mips32decomposer.mnemonic;

@FunctionalInterface
public interface MnemonicYielder {
    Mnemonic toMnemonic();
}
