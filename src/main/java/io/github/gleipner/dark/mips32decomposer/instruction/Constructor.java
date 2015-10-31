package io.github.gleipner.dark.mips32decomposer.instruction;

import io.github.gleipner.dark.mips32decomposer.ExceptionFunction;

import java.io.IOException;
import java.io.InputStream;

@FunctionalInterface
public interface Constructor extends
        ExceptionFunction<Instruction, InputStream, IOException> {
    /** This is a type synonym for the ExceptionFunction interface */
}
