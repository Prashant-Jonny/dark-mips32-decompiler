package se.filipallberg.dark.mips32decompiler.instruction.type.RTypeInstruction;

import se.filipallberg.dark.mips32decompiler.instruction.DecomposedRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.RTypeMnemonicPattern;
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
    /**
     * Addition (with overflow). Put the sum of registers rs and rt into
     * register rd.
     */
    ADD(0x00, 0x20, RTypeMnemonicPattern::RD_RS_RT),

    /**
     * Addition (without overflow). Put the sum of registers rs and rt into
     * register rd.
     */
    ADDU(0, 0x21, RTypeMnemonicPattern::RD_RS_RT),

    /** Put the logical AND of registers rs and rt into register rd */
    AND(0x00, 0x24, RTypeMnemonicPattern::RD_RS_RT),

    /**
     * Count leading ones in the word in register rs and put the result
     * into register rd. If a word is all ones, the result is 32.
     */
    CLO(0x1c, 0x21, RTypeMnemonicPattern::RD_RS),

    /**
     * Count leading zeroes in the word in register rs and put the result
     * into register rd. If a word is all zeroes, the result is 32.
     */
    CLZ(0x1c, 0x20, RTypeMnemonicPattern::RD_RS),
    
    /** Divide (with overflow). Divide register rs by register rt. */
    DIV(0x00, 0x1a, RTypeMnemonicPattern::RS_RT),

    /** Divide (without overflow). Divide register rs by register rt. */
    DIVU(0x00, 0x1b, RTypeMnemonicPattern::RS_RT),

    /**
     * Multiply. Multiply registers rs and rt. Leave the low-order word
     * of the product in the register lo and the high-order word in
     * register hi
     */
    MULT(0x00, 0x18, RTypeMnemonicPattern::RS_RT),

    /**
     * Unsigned multiply. Multiply registers rs and rt. Leave the low-order word
     * of the product in the register lo and the high-order word in
     * register hi
     */
    MULTU(0x00, 0x19, RTypeMnemonicPattern::RS_RT),

    /**
     * Multiply (without overflow). Put the low-order 32 bits of the product
     * of rs and rt into register rd.
     */
    MUL(0x1c, 2, RTypeMnemonicPattern::RD_RS_RT),

    /**
     * Multiply add. Multiply registers rs and rt (5 and 5 bits, respectively)
     * and add the resulting 64-bit product to the 64-bit value in the
     * concatenated registers lo and hi. */
    MADD(0x1c, 0, RTypeMnemonicPattern::RS_RT),

    /**
     * Unsigned multiply add. Multiply registers rs and rt (5 and 5 bits, respectively)
     * and add the resulting 64-bit product to the 64-bit value in the
     * concatenated registers lo and hi. */
    MADDU(0x1c, 1, RTypeMnemonicPattern::RS_RT),

    /**
     * Multiply subtract. Multiply registers rs and rt and subtract the
     * resulting 64-bit product from the 64-bit value in the
     * concatenated registers lo and hi.
     */
    MSUB(0x1c, 4, RTypeMnemonicPattern::RS_RT),

    /**
     * Unsigned multiply subtract. Multiply registers rs and rt and subtract
     * the resulting 64-bit product from the 64-bit value in the
     * concatenated registers lo and hi.
     */
    MSUBU(0x1c, 5, RTypeMnemonicPattern::RS_RT),
    
    /** Put the logical NOR of registers rs and rt into register rd. */
    NOR(0x00, 0x27, RTypeMnemonicPattern::RD_RS_RT),

    /** Put the logical OR of registers rs and rt into register rd. */
    OR(0x00, 0x25, RTypeMnemonicPattern::RD_RS_RT),

    /**
     * Shift left logical. Shift register rt left by the distance indicated
     * by immediate shamt and put the result in register rd.
     */
    SLL(0x00, 0x00, RTypeMnemonicPattern::RD_RT_SHAMT),

    /**
     * Shift left logical variable. Shift register rt left by the distance indicated
     * by immediate shamt or register rs and put the result in register rd.
     */
    SLLV(0x00, 4, RTypeMnemonicPattern::RD_RT_RS),

    /**
     * Shift right arithmetic. Shift register rt left by the distance indicated
     * by immediate shamt and put the result in register rd.
     */
    SRA(0x00, 0x03, RTypeMnemonicPattern::RD_RT_SHAMT),

    /**
     * Shift right arithmetic variable. Shift register rt left by the distance indicated
     * by immediate shamt or register rs and put the result in register rd.
     */
    SRAV(0x00, 7, RTypeMnemonicPattern::RD_RT_RS),

    /**
     * Shift right logical. Shift register rt left by the distance indicated
     * by immediate shamt and put the result in register rd.
     */
    SRL(0x00, 0x02, RTypeMnemonicPattern::RD_RT_SHAMT),

    /**
     * Shift right logical variable. Shift register rt left by the distance indicated
     * by immediate shamt or register rs and put the result in register rd.
     */
    SRLV(0x00, 0x06, RTypeMnemonicPattern::RD_RT_RS),

    /**
     * Subtract (with overflow). Put the difference of registers rs and rt
     * into register rd.
     */
    SUB(0x00, 0x22, RTypeMnemonicPattern::RD_RS_RT),

    /**
     * Subtract (without overflow). Put the difference of registers rs and rt
     * into register rd.
     */
    SUBU(0x00, 0x23, RTypeMnemonicPattern::RD_RS_RT),

    /** Put the logical XOR of registers rs and rt into register rd. */
    XOR(0x00, 0x26, RTypeMnemonicPattern::RD_RS_RT),
    ;

    /**
     * Map the unique identifier instruction identifier with its
     * corresponding instruction.
     */
    private static final Map<OpcodeFunctPair, RTypeInstruction> map
            = new HashMap<>();
    /**
     * Maintain a set of all R-type instruction opcodes so that
     * {@link Format} may map opcodes with the R-format.
     */
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

    /** All R-format instructions follow the same pattern */
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

        /** Get the correct R-type instruction */
        DecomposedRepresentation d =
                DecomposedRepresentation.fromNumber(instruction);
        DecomposedInterpreter di = DecomposedInterpreter
                .fromDecomposedRepresentation(d);
        OpcodeFunctPair key = new OpcodeFunctPair(di.op(), di.funct());
        RTypeInstruction rTypeInstruction = map.get(key);
        String iname = getRTypeMnemonicPattern(instruction);

        return new Instruction(instruction,
                Format.R,
                iname,
                d,
                rTypeInstruction.pattern.apply(iname, d));
    }

    private static String getRTypeMnemonicPattern(int instruction) {
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

    private static DecomposedInterpreter getInterpreter(int instruction) {
        DecomposedRepresentation d =
                DecomposedRepresentation.fromNumber(
                        instruction,
                        decomposedPattern);

        return DecomposedInterpreter.fromDecomposedRepresentation(d);
    }
}
