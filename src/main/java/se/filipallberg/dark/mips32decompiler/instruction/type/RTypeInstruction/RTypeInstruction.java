package se.filipallberg.dark.mips32decompiler.instruction.type.RTypeInstruction;

import se.filipallberg.dark.mips32decompiler.instruction.util.DecomposedRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.util.Format;
import se.filipallberg.dark.mips32decompiler.instruction.mnemonic.MnemonicPattern;
import se.filipallberg.dark.mips32decompiler.instruction.util.Opcode;
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
    // TODO: Validate that shamt is 0
    ADD(0x00, 0x20, RTypeMnemonicPattern::RD_RS_RT),

    /**
     * Addition (without overflow). Put the sum of registers rs and rt into
     * register rd.
     */
    // TODO: Validate that shamt is 0
    ADDU(0, 0x21, RTypeMnemonicPattern::RD_RS_RT),

    /** Put the logical AND of registers rs and rt into register rd */
    // TODO: validate that shamt is 0
    AND(0x00, 0x24, RTypeMnemonicPattern::RD_RS_RT),

    /**
     * Count leading ones in the word in register rs and put the result
     * into register rd. If a word is all ones, the result is 32.
     */
    // TODO: Validate that shamt is 0 and rt is 0
    CLO(0x1c, 0x21, RTypeMnemonicPattern::RD_RS),

    /**
     * Count leading zeroes in the word in register rs and put the result
     * into register rd. If a word is all zeroes, the result is 32.
     */
    // TODO: Validate that shamt is 0 and rt is 0
    CLZ(0x1c, 0x20, RTypeMnemonicPattern::RD_RS),
    
    /** Divide (with overflow). Divide register rs by register rt. */
    // TODO: Validate that rd and shamt is 0
    DIV(0x00, 0x1a, RTypeMnemonicPattern::RS_RT),

    /** Divide (without overflow). Divide register rs by register rt. */
    // TODO: Validate that rd and shamt is 0
    DIVU(0x00, 0x1b, RTypeMnemonicPattern::RS_RT),

    /**
     * Multiply. Multiply registers rs and rt. Leave the low-order word
     * of the product in the register lo and the high-order word in
     * register hi
     */
    // TODO: Validate that rd and shamt is 0
    MULT(0x00, 0x18, RTypeMnemonicPattern::RS_RT),

    /**
     * Unsigned multiply. Multiply registers rs and rt. Leave the low-order word
     * of the product in the register lo and the high-order word in
     * register hi
     */
    // TODO: Validate that rd and shamt is 0
    MULTU(0x00, 0x19, RTypeMnemonicPattern::RS_RT),

    /**
     * Multiply (without overflow). Put the low-order 32 bits of the product
     * of rs and rt into register rd.
     */
    // TODO: Validate that shamt is 0
    MUL(0x1c, 2, RTypeMnemonicPattern::RD_RS_RT),

    /**
     * Multiply add. Multiply registers rs and rt (5 and 5 bits, respectively)
     * and add the resulting 64-bit product to the 64-bit value in the
     * concatenated registers lo and hi. */
    // TODO: Validate that rd and shamt is 0
    MADD(0x1c, 0, RTypeMnemonicPattern::RS_RT),

    /**
     * Unsigned multiply add. Multiply registers rs and rt (5 and 5 bits, respectively)
     * and add the resulting 64-bit product to the 64-bit value in the
     * concatenated registers lo and hi. */
    // TODO: Validate that rd and shamt is 0
    MADDU(0x1c, 1, RTypeMnemonicPattern::RS_RT),

    /**
     * Multiply subtract. Multiply registers rs and rt and subtract the
     * resulting 64-bit product from the 64-bit value in the
     * concatenated registers lo and hi.
     */
    // TODO: Validate that rd and shamt is 0
    MSUB(0x1c, 4, RTypeMnemonicPattern::RS_RT),

    /**
     * Unsigned multiply subtract. Multiply registers rs and rt and subtract
     * the resulting 64-bit product from the 64-bit value in the
     * concatenated registers lo and hi.
     */
    // TODO: Validate that rd and shamt is 0
    MSUBU(0x1c, 5, RTypeMnemonicPattern::RS_RT),
    
    /** Put the logical NOR of registers rs and rt into register rd. */
    // TODO: Validate that shamt is 0
    NOR(0x00, 0x27, RTypeMnemonicPattern::RD_RS_RT),

    /** Put the logical OR of registers rs and rt into register rd. */
    // TODO: Validate that shamt is 0
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
    // TODO: Validate that shamt is 0
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
    // TODO: Validate that shamt is 0
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
    // TODO: Validate that shamt is 0
    SRLV(0x00, 0x06, RTypeMnemonicPattern::RD_RT_RS),

    /**
     * Subtract (with overflow). Put the difference of registers rs and rt
     * into register rd.
     */
    // TODO: Validate that shamt is 0
    SUB(0x00, 0x22, RTypeMnemonicPattern::RD_RS_RT),

    /**
     * Subtract (without overflow). Put the difference of registers rs and rt
     * into register rd.
     */
    // TODO: Validate that shamt is 0
    SUBU(0x00, 0x23, RTypeMnemonicPattern::RD_RS_RT),

    /** Put the logical XOR of registers rs and rt into register rd. */
    // TODO: Validate that shamt is 0
    XOR(0x00, 0x26, RTypeMnemonicPattern::RD_RS_RT),

    /**
     * Trap if equal. If register rs is equal to register rt, raise a
     * Trap exception.
     */
    // TODO: Validate that shamt and rd is 0
    TEQ(0x00, 0x52, RTypeMnemonicPattern::RS_RT),

    /**
     * Trap if greater equal. If register rs is greater than or equal to
     * register rt, raise a Trap exception.
     */
    // TODO: Validate that shamt and rd is 0
    TGE(0x00, 0x48, RTypeMnemonicPattern::RS_RT),

    /**
     * Unsigned trap if greater equal. If register rs is greater than or equal
     * to register rt, raise a Trap exception.
     */
    // TODO: Validate that shamt and rd is 0
    TGEU(0x00, 0x49, RTypeMnemonicPattern::RS_RT),

    /**
     * Trap if less than. If register rs is less than register rt, raise a
     * Trap exception.
     */
    // TODO: Validate that shamt and rd is 0
    TLT(0x00, 0x50, RTypeMnemonicPattern::RS_RT),

    /**
     * Trap if less than unsigned. If register rs is less than register rt,
     * raise a Trap exception.
     */
    // TODO: Validate that shamt and rd is 0
    TLTU(0x00, 0x51, RTypeMnemonicPattern::RS_RT),

    /**
     * Move from hi
     * The multiply and divide unit produces its result in two additional
     * registers, hi and lo. This instruction moves the hi register
     * to rd.
     */
    // TODO: Verify that rs and rt and shamt is 0
    MFHI(0x00, 0x10, RTypeMnemonicPattern::RD),

    /**
     * Move from lo
     * The multiply and divide unit produces its result in two additional
     * registers, hi and lo. This instruction moves values from the lo
     * register to rd.
     */
    // TODO: Verify that rs and rt and shamt is 0
    MFLO(0x00, 0x10, RTypeMnemonicPattern::RD),

    /**
     * Move to hi, move register rs to the hi register.
     */
    // TODO: Validate that rt, rd, and shamt = 0
    MTHI(0x00, 0x11, RTypeMnemonicPattern::RS),


    /**
     * Move to lo, move register rs to the lo register.
     */
    // TODO: Validate that rt, rd, and shamt = 0
    MTLO(0x00, 0x13, RTypeMnemonicPattern::RS),

    /**
     * Move from coprocessor 0. Move register rd in a coprocessor (register
     * fs in the FPU) to CPU register rt. The floating-point unit is
     * coprocessor 1.
     */
    // TODO: Validate that rs, shamt, and funct is 0
    MFC0(0x10, 0x00, RTypeMnemonicPattern::RT_RD),

    /**
     * Move from coprocessor 1. Move register rd in a coprocessor (register
     * fs in the FPU) to CPU register rt. The floating-point unit is
     * coprocessor 1. Note that FS occupies the rd field
     */
    // TODO: Validate that rs, shamt and funct is 0
    MFC1(0x11, 0x00, RTypeMnemonicPattern::RT_FS),

    /**
     * Move to coprocessor 0, move CPU register rt to register
     * rd in a coprocessor
     */
    // TODO: Validate that rs = 4 and that shamt and funct is 0
    MTC0(0x10, 0x00, RTypeMnemonicPattern::RD_RT),

    /**
     * Move to coprocessor 0, move CPU register rt to register
     * fs in the FPU. fs occupies the rd field. Note the pattern that
     * the MTC and MTF operations share the same opcode and funct
     * field. The rs field distinguishes them.
     */
    // TODO: Validate that rs = 4 and funct and shamt = 0
    MTC1(0x11, 0x00, RTypeMnemonicPattern::RT_FS),

    /**
     * Move conditional not zero. Move register rs to register rd if
     * register rt is not zero.
     */
    MOVN(0x00, 0x11, RTypeMnemonicPattern::RD_RS_RT),

    /**
     * Move conditional zero. Move register rs to register rd if
     * register rt is zero.
     */
    MOVZ(0x00, 0x10, RTypeMnemonicPattern::RD_RS_RT),

    /**
     * Set less than. Set register rd to 1 if register rs is less than rt,
     * otherwise set register rd to 0.
     */
    SLT(0x00, 0x42, RTypeMnemonicPattern::RD_RS_RT),

    /**
     * Set less than unsigned. Set register rd to 1 if register rs is
     * less than rt, otherwise set register rd to 0.
     */
    SLTU(0x00, 0x43, RTypeMnemonicPattern::RD_RS_RT),

    /**
     * Unconditionally jump to the instruction whose address is in register
     * rs. Save the address of the next instruction in register rd.
     */
    // TODO Verify that rs and shamt is 0
    JALR(0x00, 0x09, RTypeMnemonicPattern::RS_RD),

    /**
     * Unconditionally jump to the instruction whose address is in
     * register rs.
     */
    // TODO: Validate that rt, rd, and shamt is 0
    JR(0x00, 0x08, RTypeMnemonicPattern::RS),

    /** Do nothing */
    // TODO: Validate that all fields are 0
    NOP(0x00, 0x00, RTypeMnemonicPattern::NOP)
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
        RDecomposition di = RDecomposition
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
        RDecomposition d = getInterpreter(instruction);
        OpcodeFunctPair key = new OpcodeFunctPair(d.op(), d.funct());
        return map.get(key).name().toLowerCase();
    }

    /** Returns true if the instruction has the R-format */
    private static boolean hasCorrectFormat(int instruction) {
        int op = Opcode.toNumericalRepresentation(instruction);
        Opcode opcode = Opcode.fromNumericalRepresentation(op);

        return Format.fromOpcode(opcode) == Format.R;
    }

    private static RDecomposition getInterpreter(int instruction) {
        DecomposedRepresentation d =
                DecomposedRepresentation.fromNumber(
                        instruction,
                        decomposedPattern);

        return RDecomposition.fromDecomposedRepresentation(d);
    }
}
