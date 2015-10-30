package io.github.gleipner.dark.mips32decomposer.instruction;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

/**
 * A MIPS32 instruction.
 */
public abstract class Instruction {
    public static Instruction fromInputStream(InputStream is) throws IOException {
        /**
         * We need to read the OpCode from the input stream.
         * The byte representing the opcode is then pushed back into the stream
         * for simplified continued reading from the stream. In doing this
         * consistently through any intermediary constructors the final
         * instruction will have all the information originally passed so
         * that it may appropriately validate itself.
         */
        PushbackInputStream pb = new PushbackInputStream(is);
        byte opCode = (byte) pb.read();
        pb.unread(opCode);

        /**
         * Get the OpCode corresponding to the numerical value read
         * from the stream and supply the associated constructor with
         * the stream as it was passed to us.
         */
        return OpCode.getOpcode(opCode).constructor.apply(pb);
    }

    public abstract Format getFormat();
}
