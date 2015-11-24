package se.filipallberg.dark.mips32decompiler.instruction.mnemonic;

import se.filipallberg.dark.mips32decompiler.instruction.util.DecomposedRepresentation;

import java.util.function.BiFunction;

@FunctionalInterface
public interface MnemonicPattern extends BiFunction<String,
        DecomposedRepresentation, MnemonicRepresentation> {
    /* Intentionally left empty */
}
