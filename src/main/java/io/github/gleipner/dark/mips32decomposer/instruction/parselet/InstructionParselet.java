package io.github.gleipner.dark.mips32decomposer.instruction.parselet;

import io.github.gleipner.dark.mips32decomposer.instruction.Instruction;

public interface InstructionParselet {
    Instruction parse(int instruction);
}
