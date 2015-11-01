package io.github.gleipner.dark.mips32decomposer.instruction.parselet;

import io.github.gleipner.dark.mips32decomposer.instruction.Instruction;

import java.util.function.Function;

/** This a type synonym for Function<Integer, Instruction> */
@FunctionalInterface
public interface InstructionParseFunction extends Function<Integer, Instruction> {
    // Intentionally left empty
}
