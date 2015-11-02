package io.github.gleipner.dark.mips32decomposer.instruction.parser;

import io.github.gleipner.dark.mips32decomposer.instruction.Instruction;

/**
 * Utilized to enable method reference passing of functions that when applied to
 * a numerical representation of an instruction yields the corresponding
 * {@link Instruction} instance.
 */
@FunctionalInterface
public interface InstructionConstructor {
    /**
     * Represents a function that when applied to a numerical representation
     * of a MIPS32 instruction yields the corresponding {@link Instruction}
     * instance.
     *
     * @param instruction the numerical representation of the instruction.
     * @return the corresponding {@link Instruction} instance.
     */
    Instruction apply(int instruction);
}
