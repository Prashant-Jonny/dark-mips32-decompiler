package io.github.gleipner.dark.mips32decomposer.instruction;

import io.github.gleipner.dark.mips32decomposer.ExceptionFunction;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.HashMap;
import java.util.Map;



/**
 * A MIPS32 instruction.
 */
public abstract class Instruction {
    @FunctionalInterface
    private interface Constructor extends
            ExceptionFunction<Instruction, InputStream, IOException> {
        /** This is a type synonym for the ExceptionFunction interface */
    }

    private final static Map<Integer, Constructor> map = new HashMap<>();

    static {
        /** Exclusively R-instructions */
        map.put(0x00, RTypeInstruction::new);
        map.put(0x1c, RTypeInstruction::new);

        /** Certain branch instructions */
        map.put(0x01, ITypeInstruction::new);

        /** Some jump instructions */
        map.put(0x02, JTypeInstruction::new);
        map.put(0x03, JTypeInstruction::new);

        /** Some arithmetic and logical instructions */
        map.put(0x08, ITypeInstruction::new);
        map.put(0x09, ITypeInstruction::new);
        map.put(0xc, ITypeInstruction::new);
        map.put(0xd, ITypeInstruction::new);

        /** Constant manipulating instructions */
        map.put(0xf, ITypeInstruction::new);

        /** Some comparison instructions */
        map.put(0xa, ITypeInstruction::new);
        map.put(0xb, ITypeInstruction::new);

        /** Some branch instructions */
        map.put(0x04, ITypeInstruction::new);
        map.put(0x07, ITypeInstruction::new);
        map.put(0x06, ITypeInstruction::new);
        map.put(0x05, ITypeInstruction::new);

        /** Load instructions */
        map.put(0x20, ITypeInstruction::new),
        map.put(0x21, ITypeInstruction::new),


    }

    public static Instruction fromInputStream(InputStream is) throws IOException, UnknownInstructionException {
        /**
         * We need to read the OpCode from the input stream.
         * The byte representing the opcode is then pushed back into the stream
         * for simplified continued reading from the stream. In doing this
         * consistently through any intermediary constructors the final
         * instruction will have all the information originally passed so
         * that it may appropriately validate itself.
         */
        PushbackInputStream pb = new PushbackInputStream(is);

        byte containsOpcode = (byte) pb.read();
        pb.unread(containsOpcode);

        /** An opcode is represented by the 6 left-most bits */
        byte opCode = (byte) (containsOpcode & 0x3f);

        /**
         * Get the OpCode corresponding to the numerical value read
         * from the stream and supply the associated constructor with
         * the stream as it was passed to us.
         */
        OpCode op = OpCode.getOpcode(opCode);
        if (op != null) {
            return op.constructor.apply(pb);
        } else {
            throw new UnknownInstructionException(opCode);
        }
    }

    public abstract Format getFormat();
}
