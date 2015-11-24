package se.filipallberg.dark.mips32decompiler.instruction.type.RTypeInstruction;

import se.filipallberg.dark.mips32decompiler.instruction.DecomposedRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.format.Format;
import se.filipallberg.dark.mips32decompiler.instruction.mnemonic.MnemonicPattern;
import se.filipallberg.dark.mips32decompiler.instruction.opcode.Opcode;
import se.filipallberg.dark.mips32decompiler.instruction.type.Instruction;

import java.util.*;

/**
 * Describes a stateless representation of all the known R-type
 * instructions. Pass in an numerical representation of an instruction
 * and get the instruction instance.
 */
public enum RTypeInstruction {
    MUL(0x1c, 2, RTypeMnemonicPattern::RD_RS_RT)
    ;

    private static final Map<OpcodeFunctPair, RTypeInstruction> map
            = new HashMap<>();
    private static final Set<Opcode> opcodeSet = new HashSet<>();
    static {

        Arrays.stream(RTypeInstruction.values()).forEach(e -> {
            map.put(e.pair, e);
        });

        RTypeInstruction.map.keySet().forEach(e -> {
            opcodeSet.add(e.getOpcode());
        });
    }

    private final MnemonicPattern pattern;
    private final OpcodeFunctPair pair;
    private final static int[] decomposedPattern = {6, 5, 5, 5, 5, 6};

    RTypeInstruction(int opcode, int funct,
                     MnemonicPattern pattern) {
        this.pattern = pattern;
        pair = new OpcodeFunctPair(opcode, funct);
    }

    public static Set<Opcode> getOpcodeSet() {
        return opcodeSet;
    }

    public static Instruction toInstruction(int instruction) {
        /* Validate input */
        if (!hasCorrectFormat(instruction)) {
            String err = "Expected an instruction in the R-format. The" +
                    " passed instruction is: " + instruction +
                    " which has the opcode: " +
                    Opcode.toNumericalRepresentation(instruction);

            throw new IllegalArgumentException(err);
        }

        return null;
    }

    private static String getInstructionName(int instruction) {
        /*
         * Get the interpreter so that there are named methods
         * for field accesses.
         */
        DecomposedInterpreter d = getInterpreter(instruction);
        OpcodeFunctPair key = new OpcodeFunctPair(d.op(), d.funct());
        return map.get(key).name().toLowerCase();
    }

    /** Returns true if the instruction has the R-format */
    private static boolean hasCorrectFormat(int instruction) {
        int op = Opcode.toNumericalRepresentation(instruction);
        Opcode opcode = Opcode.fromNumericalRepresentation(op);

        return Format.fromOpcode(opcode) == Format.R;
    }

    private static DecomposedInterpreter getInterpreter
            (int instruction) {
        DecomposedRepresentation d =
                DecomposedRepresentation.fromNumber(
                        instruction,
                        decomposedPattern);

        return DecomposedInterpreter.fromDecomposedRepresentation(d);
    }

}
