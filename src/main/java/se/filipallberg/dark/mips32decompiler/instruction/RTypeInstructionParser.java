package se.filipallberg.dark.mips32decompiler.instruction;

import se.filipallberg.dark.mips32decompiler.instruction.opcode.Format;
import se.filipallberg.dark.mips32decompiler.instruction.opcode.Opcode;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * An R-type instruction is determined uniquely by the combination
 * of its opcode and its <i>funct </i>(function) field. The opcode is
 * the leftmost 6-bits of the instruction when represented as a 32-bit
 * number and the rightmost 6-bits compose the <i>funct</i> field.
 *
 * A common decomposition of an R-type instruction is into bitfields
 * of lengths (6, 5, 5, 5, 5, 6). The bit-fields then represent the
 * following units of effect
 *
 * | 6 bits  | 5 bits | 5 bits | 5 bits | 5 bits | 6 bits |
 * |:-------:|:------:|:------:|:------:|:------:|:------:|
 * | op      | rs     | rt     | rd     | shamt  | funct  |
 */
public class RTypeInstructionParser {
    /**
     * (opcode, funct) tuples uniquely identifies the name of an
     * instruction
     */
    private static final Map<OpcodeFunctPair, InstructionName> map = new
            HashMap<>();

    /**
     * Each instruction has a particular pattern. Associate the name
     * with a function of two arguments, the name and the decomposed
     * representation, such that when called with those arguments the
     * function returns and {@link Instruction} instance.
     */
    private static final Map<InstructionName,
            ParametrizedConstructor> constructorMap = new HashMap<>();


    /**
     * Yields an {@link Instruction} instance corresponding to the
     * supplied numerical representation of the MIPS32 instruction.
     *
     * @param instruction the numerical representation of a MIPS32 instruction.
     * @return the corresponding {@link Instruction} instance.
     */
    public static Instruction parse(int instruction) {
        /* Assert that the instruction has the correct format */
        assert(Opcode.getFormatFromInstruction(instruction) == Format.R);

        /*
         * We consider all R-type format type instructions to have the
         * same decomposition
         */
        DecomposedRepresentation d = DecomposedRepresentation.fromNumber
                (instruction, 6, 5, 5, 5, 5, 6);

        /*
         * Get the int array so that we can retrieve the opcode and
         * funct field
         */
        int[] decomposedArray = d.toIntArray();
        int opcode = decomposedArray[0];
        int funct = decomposedArray[5];
        /* Use the opcode funct pair to get the associated name */
        InstructionName iname = map.get(new OpcodeFunctPair(opcode,
                funct));
        /*
         * From the name get the appropriate constructor pattern and
         * apply it.
         */
        return constructorMap.get(iname).apply(iname, d);
    }

    static {
        /* Store all opcode-funct pairs */
        put(InstructionName.MUL,
                RTypeInstructionParser::INAME_RD_RS_RT,
                0x1c, 2);

        put(InstructionName.SUB,
                RTypeInstructionParser::INAME_RD_RS_RT,
                0x00, 0x22);
    }

    private static void put(InstructionName iname,
                            ParametrizedConstructor constructor,
                            int opcode, int funct) {
        put(iname, opcode, funct);
        put(iname, constructor);
    }

    private static void put(InstructionName name, int opcode, int funct) {
        map.put(new OpcodeFunctPair(opcode, funct), name);
    }

    private static void put(InstructionName iname,
                            ParametrizedConstructor constructor) {
        constructorMap.put(iname, constructor);
    }

    private static Instruction INAME_RD_RS_RT(InstructionName iname,
                                              DecomposedRepresentation d) {
        int[] decomposedArray = d.toIntArray();

        /* Get the numerical register values from the decomposition */
        int rd = decomposedArray[3];
        int rs = decomposedArray[1];
        int rt = decomposedArray[2];

        /* Get the names of the registers */
        String[] instructionParameters = {
                Register.toString(rd),
                Register.toString(rs),
                Register.toString(rt)
        };

        /* Create the mnemonic representation of the instruction */
        MnemonicRepresentation mnemonic = new MnemonicRepresentation
                (iname, instructionParameters);

        /* Return the instruction instance */
        return new Instruction(iname, Format.R, d, mnemonic);
    }

    @FunctionalInterface
    private interface ParametrizedConstructor extends
            BiFunction<InstructionName, DecomposedRepresentation,
                    Instruction> {
        /* Intentionally left empty */
    }

    private static class OpcodeFunctPair {
        private final int opcode;
        private final int funct;

        OpcodeFunctPair(int opcode, int funct) {
            this.opcode = opcode;
            this.funct = funct;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            OpcodeFunctPair that = (OpcodeFunctPair) o;

            return opcode == that.opcode && funct == that.funct;
        }

        @Override
        public int hashCode() {
            int result = opcode;
            result = 31 * result + funct;
            return result;
        }
    }
}
