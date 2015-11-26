package se.filipallberg.dark.mips32decompiler.instruction.type.RTypeInstruction;

import se.filipallberg.dark.mips32decompiler.instruction.mnemonic.MnemonicRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.type.BitField;
import se.filipallberg.dark.mips32decompiler.instruction.util.DecomposedRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.util.Format;
import se.filipallberg.dark.mips32decompiler.instruction.util.Opcode;
import se.filipallberg.dark.mips32decompiler.instruction.Instruction;
import se.filipallberg.dark.mips32decompiler.instruction.util.Register;

import java.util.*;
import java.util.function.Function;

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
    ADD(0x00, 0x20, AsString::rd,  AsString::rs,  AsString::rt),

    /**
     * Addition (without overflow). Put the sum of registers rs and rt into
     * register rd.
     */
    // TODO: Validate that shamt is 0
    ADDU(0, 0x21,  AsString::rd,  AsString::rs,  AsString::rt),

    /** Put the logical AND of registers rs and rt into register rd */
    // TODO: validate that shamt is 0
    AND(0x00, 0x24,  AsString::rd,  AsString::rs,  AsString::rt),

    /**
     * Count leading ones in the word in register rs and put the result
     * into register rd. If a word is all ones, the result is 32.
     * For the instruction to be valid shamt has to be 0 and rt has
     * to be 0.
     */
    CLO(0x1c, 0x21,
            new Condition<RTypeInstruction, Integer>()
                    .checkThat(Int::shamt).is(0x00).
                    andThat(Int::rt).is(0x00),
            AsString::rd, AsString::rs),

    /**
     * Count leading zeroes in the word in register rs and put the result
     * into register rd. If a word is all zeroes, the result is 32.
     */
    // TODO: Validate that shamt is 0 and rt is 0
    CLZ(0x1c, 0x20,  AsString::rd,  AsString::rs),
    
    /** Divide (with overflow). Divide register rs by register rt. */
    // TODO: Validate that rd and shamt is 0
    DIV(0x00, 0x1a,  AsString::rs,  AsString::rt),

    /** Divide (without overflow). Divide register rs by register rt. */
    // TODO: Validate that rd and shamt is 0
    DIVU(0x00, 0x1b,  AsString::rs,  AsString::rt),

    /**
     * Multiply. Multiply registers rs and rt. Leave the low-order word
     * of the product in the register lo and the high-order word in
     * register hi
     */
    // TODO: Validate that rd and shamt is 0
    MULT(0x00, 0x18,  AsString::rs,  AsString::rt),

    /**
     * Unsigned multiply. Multiply registers rs and rt. Leave the low-order word
     * of the product in the register lo and the high-order word in
     * register hi
     */
    // TODO: Validate that rd and shamt is 0
    MULTU(0x00, 0x19,  AsString::rs,  AsString::rt),

    /**
     * Multiply (without overflow). Put the low-order 32 bits of the product
     * of rs and rt into register rd. Is valid iff the shamt field is 0.
     */
    MUL(0x1c, 2, new Condition<RTypeInstruction, Integer>()
            .checkThat(Int::shamt).is(0x00),
            AsString::rd,  AsString::rs,  AsString::rt),

    /**
     * Multiply add. Multiply registers rs and rt (5 and 5 bits, respectively)
     * and add the resulting 64-bit product to the 64-bit value in the
     * concatenated registers lo and hi. */
    // TODO: Validate that rd and shamt is 0
    MADD(0x1c, 0,  AsString::rs,  AsString::rt),

    /**
     * Unsigned multiply add. Multiply registers rs and rt (5 and 5 bits, respectively)
     * and add the resulting 64-bit product to the 64-bit value in the
     * concatenated registers lo and hi. */
    // TODO: Validate that rd and shamt is 0
    MADDU(0x1c, 1,  AsString::rs,  AsString::rt),

    /**
     * Multiply subtract. Multiply registers rs and rt and subtract the
     * resulting 64-bit product from the 64-bit value in the
     * concatenated registers lo and hi.
     */
    // TODO: Validate that rd and shamt is 0
    MSUB(0x1c, 4,  AsString::rs,  AsString::rt),

    /**
     * Unsigned multiply subtract. Multiply registers rs and rt and subtract
     * the resulting 64-bit product from the 64-bit value in the
     * concatenated registers lo and hi.
     */
    // TODO: Validate that rd and shamt is 0
    MSUBU(0x1c, 5,  AsString::rs,  AsString::rt),
    
    /** Put the logical NOR of registers rs and rt into register rd. */
    // TODO: Validate that shamt is 0
    NOR(0x00, 0x27,  AsString::rd,  AsString::rs,  AsString::rt),

    /** Put the logical OR of registers rs and rt into register rd. */
    // TODO: Validate that shamt is 0
    OR(0x00, 0x25,  AsString::rd,  AsString::rs,  AsString::rt),

    /**
     * Shift left logical. Shift register rt left by the distance indicated
     * by immediate shamt and put the result in register rd.
     */
    SLL(0x00, 0x00,  AsString::rd,  AsString::rt,  AsString::shamt),

    /**
     * Shift left logical variable. Shift register rt left by the distance indicated
     * by immediate shamt or register rs and put the result in register rd.
     */
    // TODO: Validate that shamt is 0
    SLLV(0x00, 4,  AsString::rd,  AsString::rt,  AsString::rs),

    /**
     * Shift right arithmetic. Shift register rt left by the distance indicated
     * by immediate shamt and put the result in register rd.
     */
    SRA(0x00, 0x03,  AsString::rd,  AsString::rt,  AsString::shamt),

    /**
     * Shift right arithmetic variable. Shift register rt left by the distance indicated
     * by immediate shamt or register rs and put the result in register rd.
     */
    // TODO: Validate that shamt is 0
    SRAV(0x00, 7,  AsString::rd,  AsString::rt,  AsString::rs),

    /**
     * Shift right logical. Shift register rt left by the distance indicated
     * by immediate shamt and put the result in register rd.
     */
    SRL(0x00, 0x02,  AsString::rd,  AsString::rt,  AsString::shamt),

    /**
     * Shift right logical variable. Shift register rt left by the distance indicated
     * by immediate shamt or register rs and put the result in register rd.
     */
    // TODO: Validate that shamt is 0
    SRLV(0x00, 0x06,  AsString::rd,  AsString::rt,  AsString::rs),

    /**
     * Subtract (with overflow). Put the difference of registers rs and rt
     * into register rd.
     */
    // TODO: Validate that shamt is 0
    SUB(0x00, 0x22,  AsString::rd,  AsString::rs,  AsString::rt),

    /**
     * Subtract (without overflow). Put the difference of registers rs and rt
     * into register rd.
     */
    // TODO: Validate that shamt is 0
    SUBU(0x00, 0x23,  AsString::rd,  AsString::rs,  AsString::rt),

    /** Put the logical XOR of registers rs and rt into register rd. */
    // TODO: Validate that shamt is 0
    XOR(0x00, 0x26,  AsString::rd,  AsString::rs,  AsString::rt),

    /**
     * Trap if equal. If register rs is equal to register rt, raise a
     * Trap exception.
     */
    // TODO: Validate that shamt and rd is 0
    TEQ(0x00, 0x52,  AsString::rs,  AsString::rt),

    /**
     * Trap if greater equal. If register rs is greater than or equal to
     * register rt, raise a Trap exception.
     */
    // TODO: Validate that shamt and rd is 0
    TGE(0x00, 0x48,  AsString::rs,  AsString::rt),

    /**
     * Unsigned trap if greater equal. If register rs is greater than or equal
     * to register rt, raise a Trap exception.
     */
    // TODO: Validate that shamt and rd is 0
    TGEU(0x00, 0x49,  AsString::rs,  AsString::rt),

    /**
     * Trap if less than. If register rs is less than register rt, raise a
     * Trap exception.
     */
    // TODO: Validate that shamt and rd is 0
    TLT(0x00, 0x50,  AsString::rs,  AsString::rt),

    /**
     * Trap if less than unsigned. If register rs is less than register rt,
     * raise a Trap exception.
     */
    // TODO: Validate that shamt and rd is 0
    TLTU(0x00, 0x51,  AsString::rs,  AsString::rt),

    /**
     * Move from hi
     * The multiply and divide unit produces its result in two additional
     * registers, hi and lo. This instruction moves the hi register
     * to rd.
     */
    // TODO: Verify that rs and rt and shamt is 0
    MFHI(0x00, 0x10,  AsString::rd),

    /**
     * Move from lo
     * The multiply and divide unit produces its result in two additional
     * registers, hi and lo. This instruction moves values from the lo
     * register to rd.
     */
    // TODO: Verify that rs and rt and shamt is 0
    MFLO(0x00, 0x10,  AsString::rd),

    /**
     * Move to hi, move register rs to the hi register.
     */
    // TODO: Validate that rt, rd, and shamt = 0
    MTHI(0x00, 0x11,  AsString::rs),


    /**
     * Move to lo, move register rs to the lo register.
     */
    // TODO: Validate that rt, rd, and shamt = 0
    MTLO(0x00, 0x13,  AsString::rs),

    /**
     * Move from coprocessor 0. Move register rd in a coprocessor (register
     * fs in the FPU) to CPU register rt. The floating-point unit is
     * coprocessor 1.
     */
    // TODO: Validate that rs, shamt, and funct is 0
    MFC0(0x10, 0x00,  AsString::rt,  AsString::rd),

    /**
     * Move from coprocessor 1. Move register rd in a coprocessor (register
     * fs in the FPU) to CPU register rt. The floating-point unit is
     * coprocessor 1. Note that R::fs occupies the rd field
     */
    // TODO: Validate that rs, shamt and funct is 0
    MFC1(0x11, 0x00,  AsString::rt,  AsString::fs),

    /**
     * Move to coprocessor 0, move CPU register rt to register
     * rd in a coprocessor
     */
    // TODO: Validate that rs = 4 and that shamt and funct is 0
    MTC0(0x10, 0x00,  AsString::rd,  AsString::rt),

    /**
     * Move to coprocessor 0, move CPU register rt to register
     * fs in the FPU. fs occupies the rd field. Note the pattern that
     * the MTC and MTF operations share the same opcode and funct
     * field. The rs field distinguishes them.
     */
    // TODO: Validate that rs = 4 and funct and shamt = 0
    MTC1(0x11, 0x00,  AsString::rt,  AsString::fs),

    /**
     * Move conditional not zero. Move register rs to register rd if
     * register rt is not zero.
     */
    MOVN(0x00, 0x11,  AsString::rd,  AsString::rs,  AsString::rt),

    /**
     * Move conditional zero. Move register rs to register rd if
     * register rt is zero.
     */
    MOVZ(0x00, 0x10,  AsString::rd,  AsString::rs,  AsString::rt),

    /**
     * Set less than. Set register rd to 1 if register rs is less than rt,
     * otherwise set register rd to 0.
     */
    SLT(0x00, 0x42,  AsString::rd,  AsString::rs,  AsString::rt),

    /**
     * Set less than unsigned. Set register rd to 1 if register rs is
     * less than rt, otherwise set register rd to 0.
     */
    SLTU(0x00, 0x43,  AsString::rd,  AsString::rs,  AsString::rt),

    /**
     * Unconditionally jump to the instruction whose address is in register
     * rs. Save the address of the next instruction in register rd.
     */
    // TODO Verify that rs and shamt is 0
    JALR(0x00, 0x09,  AsString::rs,  AsString::rd),

    /**
     * Unconditionally jump to the instruction whose address is in
     * register rs.
     */
    // TODO: Validate that rt, rd, and shamt is 0
    JR(0x00, 0x08,  AsString::rs),

    /** Do nothing */
    // TODO: Validate that all fields are 0
    NOP(0x00, 0x00);
    ;

    /**
     * An R-type instruction is uniquely identified by its opcode and
     * its funct field, viz. that the pair uniquely identifies a particular
     * kind of instruction. This map is populated during the instantiation
     * of each enum value so that we can later look-up the correct
     * instruction instance given a particular number.
     */
    private static final Map<OpcodeFunctPair, RTypeInstruction> map
            = new HashMap<>();
    /**
     * Maintain a set of all R-type instruction opcodes so that
     * {@link Format} may map opcodes with the R-format.
     */
    private static final Set<Opcode> opcodeSet = new HashSet<>();
    static {
        /*
         * Each RTypeInstruction sets its own OpcodeFunctPair during
         * its instantiation in the field pair. The .values() method
         * allows us to iterate across all the enum values of this
         * enum class. Hence we can put all OpCodeFunct pairs into
         * our map, and associate them with the instruction that
         * the pair identifies.
         */
        Arrays.stream(RTypeInstruction.values()).forEach(e -> {
            map.put(e.pair, e);
        });

        /*
         * With the map populated we can now iterate across all the
         * OpcodeFunct pairs and yank out the opcodes, constructing a
         * set of all the Opcodes where each opcode is correlated with
         * an R-type instruction.
         */
        RTypeInstruction.map.keySet().forEach(e -> {
            opcodeSet.add(e.getOpcode());
        });
    }

    private final List<BitField2> list = new ArrayList<>();

    /**
     * The OpcodeFunctPair uniquely identifies this instruction.
     */
    private final OpcodeFunctPair pair;

    /** Set this upon use in fromNumericalRepresentation */
    private DecomposedRepresentation decomposedRepresentation;

    /** Make ~all~ fields accessible */
    private int shamt;
    private int rt;
    private int rs;
    private int rd;

    /**
     * All R-format instructions are decomposed into fields of the
     * same length.
     */
    private final static int[] decomposedPattern = {6, 5, 5, 5, 5, 6};

    RTypeInstruction(int opcode, int funct, BitField2... bitFields) {
        list.addAll(Arrays.asList(bitFields));
        pair = new OpcodeFunctPair(opcode, funct);
    }

    private Condition<RTypeInstruction, Integer> c;

    @FunctionalInterface
    interface BitField2 extends Function<RTypeInstruction, String> {

    }

    RTypeInstruction(int opcode, int funct,
                     Condition<RTypeInstruction, Integer> c,
                     BitField2... bitFields) {
        list.addAll(Arrays.asList(bitFields));
        pair = new OpcodeFunctPair(opcode, funct);
        this.c = c;
    }


    public void validate() {
        if (c != null) {
            System.out.println(c.validate(this));
        }
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

        MnemonicRepresentation m = rTypeInstruction.composeMnemonic();
        rTypeInstruction.validate();

        return new Instruction(
                instruction,
                Format.R,
                rTypeInstruction.decomposedRepresentation,
                m);
    }

    private MnemonicRepresentation composeMnemonic() {
        List<String> strings = new ArrayList<>();
        list.forEach(e -> {
            strings.add(e.apply(this));
        });
        return new MnemonicRepresentation(this.name().toLowerCase(),
                strings.toArray(new String[strings.size()]));
    }

    private static RTypeInstruction identifyInstruction(int instruction) {
        DecomposedRepresentation d = DecomposedRepresentation.
                fromNumber(instruction, decomposedPattern);
        int[] decomposition = d.toIntArray();
        int funct = Int.funct(decomposition);
        OpcodeFunctPair key = new OpcodeFunctPair(d.opcode(), funct);

        RTypeInstruction r = map.get(key);
        r.decomposedRepresentation = d;
        r.rs = decomposition[1];
        r.rt = decomposition[2];
        r.rd = decomposition[3];
        r.shamt = decomposition[4];
        return r;
    }
    
    private static class AsString {
        static int op(int[] decomposition) {
            return decomposition[0];
        }

        static int funct(int[] decomposition) {
            return decomposition[5];
        }

        static  String rd(RTypeInstruction r) {
            return Register.toString(r.rd);
        }

        static  String rs(RTypeInstruction r) {
            return Register.toString(r.rs);
        }

        static  String rt(RTypeInstruction r) {
            return Register.toString(r.rt);
        }

        static  String shamt(RTypeInstruction r) {
            return Register.toString(r.shamt);
        }

        static  String fs(RTypeInstruction r) {
            return Register.toString(r.rd);
        }
    }
    
    private static class Int {
        static int op(int[] decomposition) {
            return decomposition[0];
        }

        static int funct(int[] decomposition) {
            return decomposition[5];
        }

        static Integer rd(RTypeInstruction r) {
            return r.rd;
        }

        static Integer rs(RTypeInstruction r) {
            return r.rs;
        }

        static Integer rt(RTypeInstruction r) {
            return r.rt;
        }

        static Integer shamt(RTypeInstruction r) {
            return r.shamt;
        }

        static Integer fs(RTypeInstruction r) {
            return r.rd;
        }
    }


    /** Returns true if the instruction has the R-format */
    private static boolean hasCorrectFormat(int instruction) {
        int op = Opcode.toNumericalRepresentation(instruction);
        Opcode opcode = Opcode.fromNumericalRepresentation(op);

        return Format.fromOpcode(opcode) == Format.R;
    }
}
