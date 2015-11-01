package io.github.gleipner.dark.mips32decomposer.instruction.opcode;

import io.github.gleipner.dark.mips32decomposer.instruction.Format;
import io.github.gleipner.dark.mips32decomposer.instruction.Instruction;

/**
 * This exception should be thrown whenever one attempts to construct an
 * {@link Instruction} using an opcode that does not correspond to to the
 * expected format.
 */
public class UnexpectedOpCodeException extends RuntimeException {
    public UnexpectedOpCodeException(OpCode op, Format expectedFormat) {
        super("Expected an opcode for an instruction with format " + expectedFormat + "" +
                "but got opcode: " + op.toInteger() + " that has format " +
                op.getFormat());
    }
}
