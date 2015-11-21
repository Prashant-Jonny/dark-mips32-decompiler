package se.filipallberg.dark.mips32decompiler.instruction;

import se.filipallberg.dark.mips32decompiler.instruction.opcode.Format;
import se.filipallberg.dark.mips32decompiler.instruction.opcode.Opcode;

import java.util.HashMap;
import java.util.Map;

public class ITypeInstructionParser {
    /**
     * Each instruction has a particular pattern. Associate the name
     * with a function of two arguments, the name and the decomposed
     * representation, such that when called with those arguments the
     * function returns and {@link Instruction} instance.
     */
    private static final Map<InstructionName,
                ParametrizedConstructor> constructorMap = new HashMap<>();

    private static final Map<Opcode, InstructionName> map = new
            HashMap<>();

    /**
     * Yields an {@link Instruction} instance corresponding to the
     * supplied numerical representation of the MIPS32 instruction.
     *
     * @param instruction the numerical representation of a MIPS32 instruction.
     * @return the corresponding {@link Instruction} instance.
     */
    public static Instruction parse(int instruction) {
        /* Assert that the instruction has the correct format */
        Opcode opcode = Opcode.fromInstruction(instruction);
        assert(opcode.getFormat() == Format.I);

        /*
         * We consider all I-type format type instructions to have the
         * same decomposition
         */
        DecomposedRepresentation d = DecomposedRepresentation.fromNumber
                (instruction, 6, 5, 5, 16);

        InstructionName iname = map.get(opcode);

        /*
         * From the name get the appropriate constructor pattern and
         * apply it.
         */
        return constructorMap.get(iname).apply(iname, d);
    }

    static {
        put(8, InstructionName.ADDI);
        put(InstructionName.ADDI,
                ITypeInstructionParser::INAME_RT_RS_ADRESS);
    }

    private static void put(int opcode, InstructionName iname) {
        map.put(Opcode.fromNumericalRepresentation(opcode), iname);
    }

    private static void put(InstructionName iname,
                            ParametrizedConstructor constructor) {
        constructorMap.put(iname, constructor);
    }

    private static Instruction INAME_RT_RS_ADRESS(InstructionName iname,
                                                  DecomposedRepresentation d) {
        int[] decomposedArray = d.toIntArray();

        /* Get the numerical register values from the decomposition */
        String rt = Register.toString(decomposedArray[2]);
        String rs = Register.toString(decomposedArray[1]);

        /* Create the mnemonic representation of the instruction */
        MnemonicRepresentation mnemonic = MnemonicRepresentation.fromString(
                iname.toString() + " " + rs + ", " + rt + "(" +
                        decomposedArray[3] + ")");

        /* Return the instruction instance */
        return new Instruction(iname, Format.R, d, mnemonic);
    }
}
