package se.filipallberg.dark.mips32decompiler.instruction.type.RTypeInstruction;

import se.filipallberg.dark.mips32decompiler.instruction.mnemonic.MnemonicRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.type.BitField;
import se.filipallberg.dark.mips32decompiler.instruction.util.DecomposedRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.util.Format;
import se.filipallberg.dark.mips32decompiler.instruction.mnemonic.MnemonicPattern;
import se.filipallberg.dark.mips32decompiler.instruction.util.Opcode;
import se.filipallberg.dark.mips32decompiler.instruction.Instruction;
import se.filipallberg.dark.mips32decompiler.instruction.util.Register;

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
    ADD(0x00, 0x20, {"shamt", 0}, R::rd, R::rs, R::rt),

    /**
     * Addition (without overflow). Put the sum of registers rs and rt into
     * register rd.
     */
    // TODO: Validate that shamt is 0
    ADDU(0, 0x21, R::rd, R::rs, R::rt),

    /** Put the logical AND of registers rs and rt into register rd */
    // TODO: validate that shamt is 0
    AND(0x00, 0x24, R::rd, R::rs, R::rt),

    /**
     * Count leading ones in the word in register rs and put the result
     * into register rd. If a word is all ones, the result is 32.
     */
    // TODO: Validate that shamt is 0 and rt is 0
    CLO(0x1c, 0x21, R::rd, R::rs),

    /**
     * Count leading zeroes in the word in register rs and put the result
     * into register rd. If a word is all zeroes, the result is 32.
     */
    // TODO: Validate that shamt is 0 and rt is 0
    CLZ(0x1c, 0x20, R::rd, R::rs),
    
    /** Divide (with overflow). Divide register rs by register rt. */
    // TODO: Validate that rd and shamt is 0
    DIV(0x00, 0x1a, R::rs, R::rt),

    /** Divide (without overflow). Divide register rs by register rt. */
    // TODO: Validate that rd and shamt is 0
    DIVU(0x00, 0x1b, R::rs, R::rt),

    /**
     * Multiply. Multiply registers rs and rt. Leave the low-order word
     * of the product in the register lo and the high-order word in
     * register hi
     */
    // TODO: Validate that rd and shamt is 0
    MULT(0x00, 0x18, R::rs, R::rt),

    /**
     * Unsigned multiply. Multiply registers rs and rt. Leave the low-order word
     * of the product in the register lo and the high-order word in
     * register hi
     */
    // TODO: Validate that rd and shamt is 0
    MULTU(0x00, 0x19, R::rs, R::rt),

    /**
     * Multiply (without overflow). Put the low-order 32 bits of the product
     * of rs and rt into register rd.
     */
    // TODO: Validate that shamt is 0
    MUL(0x1c, 2, R::rd, R::rs, R::rt),

    /**
     * Multiply add. Multiply registers rs and rt (5 and 5 bits, respectively)
     * and add the resulting 64-bit product to the 64-bit value in the
     * concatenated registers lo and hi. */
    // TODO: Validate that rd and shamt is 0
    MADD(0x1c, 0, R::rs, R::rt),

    /**
     * Unsigned multiply add. Multiply registers rs and rt (5 and 5 bits, respectively)
     * and add the resulting 64-bit product to the 64-bit value in the
     * concatenated registers lo and hi. */
    // TODO: Validate that rd and shamt is 0
    MADDU(0x1c, 1, R::rs, R::rt),

    /**
     * Multiply subtract. Multiply registers rs and rt and subtract the
     * resulting 64-bit product from the 64-bit value in the
     * concatenated registers lo and hi.
     */
    // TODO: Validate that rd and shamt is 0
    MSUB(0x1c, 4, R::rs, R::rt),

    /**
     * Unsigned multiply subtract. Multiply registers rs and rt and subtract
     * the resulting 64-bit product from the 64-bit value in the
     * concatenated registers lo and hi.
     */
    // TODO: Validate that rd and shamt is 0
    MSUBU(0x1c, 5, R::rs, R::rt),
    
    /** Put the logical NOR of registers rs and rt into register rd. */
    // TODO: Validate that shamt is 0
    NOR(0x00, 0x27, R::rd, R::rs, R::rt),

    /** Put the logical OR of registers rs and rt into register rd. */
    // TODO: Validate that shamt is 0
    OR(0x00, 0x25, R::rd, R::rs, R::rt),

    /**
     * Shift left logical. Shift register rt left by the distance indicated
     * by immediate shamt and put the result in register rd.
     */
    SLL(0x00, 0x00, R::rd, R::rt, R::shamt),

    /**
     * Shift left logical variable. Shift register rt left by the distance indicated
     * by immediate shamt or register rs and put the result in register rd.
     */
    // TODO: Validate that shamt is 0
    SLLV(0x00, 4, R::rd, R::rt, R::rs),

    /**
     * Shift right arithmetic. Shift register rt left by the distance indicated
     * by immediate shamt and put the result in register rd.
     */
    SRA(0x00, 0x03, R::rd, R::rt, R::shamt),

    /**
     * Shift right arithmetic variable. Shift register rt left by the distance indicated
     * by immediate shamt or register rs and put the result in register rd.
     */
    // TODO: Validate that shamt is 0
    SRAV(0x00, 7, R::rd, R::rt, R::rs),

    /**
     * Shift right logical. Shift register rt left by the distance indicated
     * by immediate shamt and put the result in register rd.
     */
    SRL(0x00, 0x02, R::rd, R::rt, R::shamt),

    /**
     * Shift right logical variable. Shift register rt left by the distance indicated
     * by immediate shamt or register rs and put the result in register rd.
     */
    // TODO: Validate that shamt is 0
    SRLV(0x00, 0x06, R::rd, R::rt, R::rs),

    /**
     * Subtract (with overflow). Put the difference of registers rs and rt
     * into register rd.
     */
    // TODO: Validate that shamt is 0
    SUB(0x00, 0x22, R::rd, R::rs, R::rt),

    /**
     * Subtract (without overflow). Put the difference of registers rs and rt
     * into register rd.
     */
    // TODO: Validate that shamt is 0
    SUBU(0x00, 0x23, R::rd, R::rs, R::rt),

    /** Put the logical XOR of registers rs and rt into register rd. */
    // TODO: Validate that shamt is 0
    XOR(0x00, 0x26, R::rd, R::rs, R::rt),

    /**
     * Trap if equal. If register rs is equal to register rt, raise a
     * Trap exception.
     */
    // TODO: Validate that shamt and rd is 0
    TEQ(0x00, 0x52, R::rs, R::rt),

    /**
     * Trap if greater equal. If register rs is greater than or equal to
     * register rt, raise a Trap exception.
     */
    // TODO: Validate that shamt and rd is 0
    TGE(0x00, 0x48, R::rs, R::rt),

    /**
     * Unsigned trap if greater equal. If register rs is greater than or equal
     * to register rt, raise a Trap exception.
     */
    // TODO: Validate that shamt and rd is 0
    TGEU(0x00, 0x49, R::rs, R::rt),

    /**
     * Trap if less than. If register rs is less than register rt, raise a
     * Trap exception.
     */
    // TODO: Validate that shamt and rd is 0
    TLT(0x00, 0x50, R::rs, R::rt),

    /**
     * Trap if less than unsigned. If register rs is less than register rt,
     * raise a Trap exception.
     */
    // TODO: Validate that shamt and rd is 0
    TLTU(0x00, 0x51, R::rs, R::rt),

    /**
     * Move from hi
     * The multiply and divide unit produces its result in two additional
     * registers, hi and lo. This instruction moves the hi register
     * to rd.
     */
    // TODO: Verify that rs and rt and shamt is 0
    MFHI(0x00, 0x10, R::rd),

    /**
     * Move from lo
     * The multiply and divide unit produces its result in two additional
     * registers, hi and lo. This instruction moves values from the lo
     * register to rd.
     */
    // TODO: Verify that rs and rt and shamt is 0
    MFLO(0x00, 0x10, R::rd),

    /**
     * Move to hi, move register rs to the hi register.
     */
    // TODO: Validate that rt, rd, and shamt = 0
    MTHI(0x00, 0x11, R::rs),


    /**
     * Move to lo, move register rs to the lo register.
     */
    // TODO: Validate that rt, rd, and shamt = 0
    MTLO(0x00, 0x13, R::rs),

    /**
     * Move from coprocessor 0. Move register rd in a coprocessor (register
     * fs in the FPU) to CPU register rt. The floating-point unit is
     * coprocessor 1.
     */
    // TODO: Validate that rs, shamt, and funct is 0
    MFC0(0x10, 0x00, R::rt, R::rd),

    /**
     * Move from coprocessor 1. Move register rd in a coprocessor (register
     * fs in the FPU) to CPU register rt. The floating-point unit is
     * coprocessor 1. Note that R::fs occupies the rd field
     */
    // TODO: Validate that rs, shamt and funct is 0
    MFC1(0x11, 0x00, R::rt, R::fs),

    /**
     * Move to coprocessor 0, move CPU register rt to register
     * rd in a coprocessor
     */
    // TODO: Validate that rs = 4 and that shamt and funct is 0
    MTC0(0x10, 0x00, R::rd, R::rt),

    /**
     * Move to coprocessor 0, move CPU register rt to register
     * fs in the FPU. fs occupies the rd field. Note the pattern that
     * the MTC and MTF operations share the same opcode and funct
     * field. The rs field distinguishes them.
     */
    // TODO: Validate that rs = 4 and funct and shamt = 0
    MTC1(0x11, 0x00, R::rt, R::fs),

    /**
     * Move conditional not zero. Move register rs to register rd if
     * register rt is not zero.
     */
    MOVN(0x00, 0x11, R::rd, R::rs, R::rt),

    /**
     * Move conditional zero. Move register rs to register rd if
     * register rt is zero.
     */
    MOVZ(0x00, 0x10, R::rd, R::rs, R::rt),

    /**
     * Set less than. Set register rd to 1 if register rs is less than rt,
     * otherwise set register rd to 0.
     */
    SLT(0x00, 0x42, R::rd, R::rs, R::rt),

    /**
     * Set less than unsigned. Set register rd to 1 if register rs is
     * less than rt, otherwise set register rd to 0.
     */
    SLTU(0x00, 0x43, R::rd, R::rs, R::rt),

    /**
     * Unconditionally jump to the instruction whose address is in register
     * rs. Save the address of the next instruction in register rd.
     */
    // TODO Verify that rs and shamt is 0
    JALR(0x00, 0x09, R::rs, R::rd),

    /**
     * Unconditionally jump to the instruction whose address is in
     * register rs.
     */
    // TODO: Validate that rt, rd, and shamt is 0
    JR(0x00, 0x08, R::rs),

    /** Do nothing */
    // TODO: Validate that all fields are 0
    NOP(0x00, 0x00);
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

    //private final MnemonicPattern pattern;
    private final List<BitField> list = new ArrayList<>();
    private final OpcodeFunctPair pair;

    /** All R-format instructions follow the same pattern */
    private final static int[] decomposedPattern = {6, 5, 5, 5, 5, 6};

    RTypeInstruction(int opcode, int funct,
                     BitField... bitFields) {
        list.addAll(Arrays.asList(bitFields));
        pair = new OpcodeFunctPair(opcode, funct);
    }

    public static Set<Opcode> getOpcodeSet() {
        return opcodeSet;
    }

    public static Instruction fromNumericalRepresentation(int instruction) {
        /* Validate input */
        if (!hasCorrectFormat(instruction)) {
            String err = "Expected an instruction in the R-format. The" +
                    " passed instruction is: " + instruction +
                    " which has the opcode: " +
                    Opcode.toNumericalRepresentation(instruction);

            throw new IllegalArgumentException(err);
        }

        /** Get the correct R-type instruction */
        RTypeInstruction rTypeInstruction = identifyInstruction
                (instruction);
        DecomposedRepresentation d = DecomposedRepresentation.fromNumber
                (instruction, decomposedPattern);

        MnemonicRepresentation m = composeMnemonic(rTypeInstruction, d);

        return new Instruction(
                instruction,
                Format.R,
                d,
                m);
    }

    private static MnemonicRepresentation composeMnemonic
            (RTypeInstruction instruction, DecomposedRepresentation d){
        int[] decomposition = d.toIntArray();
        List<String> strings = new ArrayList<>();
        instruction.list.forEach(e -> {
            strings.add(e.apply(decomposition));
        });
        return new MnemonicRepresentation
                (instruction.name().toLowerCase(),
                strings.toArray(new String[strings.size()]));
    }

    private static RTypeInstruction identifyInstruction(int instruction) {
        DecomposedRepresentation d = DecomposedRepresentation.
                fromNumber(instruction, decomposedPattern);
        int[] decomposition = d.toIntArray();
        int funct = R.funct(decomposition);
        OpcodeFunctPair key = new OpcodeFunctPair(d.opcode(), funct);
        return map.get(key);
    }
    
    private static class R {
        static int op(int[] decomposition) {
            return decomposition[0];
        }

        static int funct(int[] decomposition) {
            return decomposition[5];
        }

        static String rd(int[] decomposition) {
            return Register.toString(decomposition[3]);
        }

        static String rs(int[] decomposition) {
            return Register.toString(decomposition[1]);
        }

        static String rt(int[] decomposition) {
            return Register.toString(decomposition[2]);
        }

        static String shamt(int[] decomposition) {
            return Integer.toString(decomposition[4]);
        }

        static String fs(int[] decomposition) {
            return Integer.toString(decomposition[3]);
        }
    }


    /** Returns true if the instruction has the R-format */
    private static boolean hasCorrectFormat(int instruction) {
        int op = Opcode.toNumericalRepresentation(instruction);
        Opcode opcode = Opcode.fromNumericalRepresentation(op);

        return Format.fromOpcode(opcode) == Format.R;
    }
}
