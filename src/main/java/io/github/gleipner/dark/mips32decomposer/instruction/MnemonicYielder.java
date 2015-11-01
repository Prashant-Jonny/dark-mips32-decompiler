package io.github.gleipner.dark.mips32decomposer.instruction;

@FunctionalInterface
public interface MnemonicYielder {
    Mnemonic toMnemonic();
}
