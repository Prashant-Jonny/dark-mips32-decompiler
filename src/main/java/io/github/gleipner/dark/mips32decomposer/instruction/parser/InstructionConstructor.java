package io.github.gleipner.dark.mips32decomposer.instruction.parser;

import io.github.gleipner.dark.mips32decomposer.instruction.Instruction;

@FunctionalInterface
public interface InstructionConstructor {
    Instruction apply(int instruction);
}
