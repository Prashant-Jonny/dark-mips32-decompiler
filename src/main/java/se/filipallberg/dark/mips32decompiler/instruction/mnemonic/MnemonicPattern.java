package se.filipallberg.dark.mips32decompiler.instruction.mnemonic;

import se.filipallberg.dark.mips32decompiler.instruction.DecomposedRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.InstructionName;
import se.filipallberg.dark.mips32decompiler.instruction.Register;
import se.filipallberg.dark.mips32decompiler.instruction.format.Format;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.function.BiFunction;

@FunctionalInterface
public interface MnemonicPattern extends BiFunction<InstructionName,
        DecomposedRepresentation, MnemonicRepresentation> {
    /* Intentionally left empty */
}
