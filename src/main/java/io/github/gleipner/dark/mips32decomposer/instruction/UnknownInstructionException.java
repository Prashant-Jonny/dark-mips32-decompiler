package io.github.gleipner.dark.mips32decomposer.instruction;

/**
 * This exception should be thrown whenever one attempts to construct an
 * {@link Instruction} using an opcode that does not correspond to any
 * known {@link Instruction}.
 */
public class UnknownInstructionException extends RuntimeException {
    public UnknownInstructionException(int opcode) {
        super("Instruction corresponding to: " + opcode + " not known");
    }
}
