package se.filipallberg.dark.mips32decompiler.instruction;

import java.util.function.BiFunction;

@FunctionalInterface
interface ParametrizedConstructor extends
        BiFunction<InstructionName, DecomposedRepresentation,
                Instruction> {
    /* Intentionally left empty */
}

