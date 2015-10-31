package io.github.gleipner.dark.mips32decomposer.instruction;

public interface Parselet {
    Instruction parse(int instruction);
}
