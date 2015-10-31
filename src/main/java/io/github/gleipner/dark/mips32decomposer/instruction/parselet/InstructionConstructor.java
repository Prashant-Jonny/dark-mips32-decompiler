package io.github.gleipner.dark.mips32decomposer.instruction.parselet;

import io.github.gleipner.dark.mips32decomposer.instruction.Instruction;

import java.util.function.Function;

@FunctionalInterface
public interface InstructionConstructor extends Function<Integer, Instruction> {
    /** This a type synonym for Function<Integer, Instruction> */
}
