package se.filipallberg.dark.mips32decompiler.instruction.type.RTypeInstruction;

import se.filipallberg.dark.mips32decompiler.instruction.Condition;
import se.filipallberg.dark.mips32decompiler.instruction.PartiallyLegalInstructionException;
import se.filipallberg.dark.mips32decompiler.instruction.mnemonic.MnemonicPattern;
import se.filipallberg.dark.mips32decompiler.instruction.mnemonic.MnemonicRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.type.InstructionType;
import se.filipallberg.dark.mips32decompiler.instruction.util.DecomposedRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.util.Format;
import se.filipallberg.dark.mips32decompiler.instruction.util.Opcode;
import se.filipallberg.dark.mips32decompiler.instruction.Instruction;
import se.filipallberg.dark.mips32decompiler.instruction.util.Register;

import java.util.*;

/**
 * Describes a stateless representation of all the known R-type
 * instructions. Pass in an numerical representation of an instruction
 * and get the instruction instance.
 */
public enum RTypeInstruction implements InstructionType {
    /**
     * Addition (with overflow). Put the sum of registers rs and rt into
     * register rd. Is only valid if shamt is 0.
     */
    ADD(0x00, 0x20,
            new Condition<RTypeInstruction, Integer>()
                    .checkThat("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>(
                    Str::iname, Str::rd,  Str::rs,  Str::rt)),

    /**
     * Addition (without overflow). Put the sum of registers rs and rt into
     * register rd. Is only valid if shamt is 0
     */
    ADDU(0, 0x21,
            new Condition<RTypeInstruction, Integer>()
                    .checkThat("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>(
                    Str::iname, Str::rd,  Str::rs,  Str::rt)),

    /**
     * Put the logical AND of registers rs and rt into register rd.
     * Is only valid if shamt is 0
     */
    AND(0x00, 0x24,
            new Condition<RTypeInstruction, Integer>()
                    .checkThat("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>(
                    Str::iname, Str::rd,  Str::rs,  Str::rt)),

    /**
     * Count leading ones in the word in register rs and put the result
     * into register rd. If a word is all ones, the result is 32.
     * For the instruction to be valid shamt has to be 0 and rt has
     * to be 0.
     */
    CLO(0x1c, 0x21,
            new Condition<RTypeInstruction, Integer>()
                    .checkThat("shamt", Int::shamt).
                    and("rt", Int::rt).is(0x00),
            new MnemonicPattern<>
                    (Str::iname, Str::rd, Str::rs)),

    /**
     * Count leading zeroes in the word in register rs and put the result
     * into register rd. If a word is all zeroes, the result is 32.
     * Is only valid if shamt is 0 and rt is 0
     */
    CLZ(0x1c, 0x20,
            new Condition<RTypeInstruction, Integer>()
                    .checkThat("shamt", Int::shamt).
                    and("rt", Int::rt).is(0x00),
            new MnemonicPattern<>
                    (Str::iname, Str::rd, Str::rs)),
    
    /**
     * Divide (with overflow). Divide register rs by register rt.
     * Is only valid if rd and shamt is 0.
     */
    DIV(0x00, 0x1a, new Condition<RTypeInstruction, Integer>()
            .checkThat("rd", Int::rd).and("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>
                    (Str::iname, Str::rs, Str::rt)),

    /**
     * Divide (without overflow). Divide register rs by register rt.
     * Is only valid if rd and shamt is 0.
     */
    DIVU(0x00, 0x1b, new Condition<RTypeInstruction, Integer>()
            .checkThat("rd", Int::rd).and("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>
                    (Str::iname, Str::rs, Str::rt)),

    /**
     * Multiply. Multiply registers rs and rt. Leave the low-order word
     * of the product in the register lo and the high-order word in
     * register hi. Is only valid if rd and shamt is 0
     */
    MULT(0x00, 0x18, new Condition<RTypeInstruction, Integer>()
            .checkThat("rd", Int::rd).and("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>
                    (Str::iname, Str::rs, Str::rt)),


    /**
     * Unsigned multiply. Multiply registers rs and rt. Leave the low-order word
     * of the product in the register lo and the high-order word in
     * register hi. Is only valid if rd and shamt is 0.
     */
    MULTU(0x00, 0x19, new Condition<RTypeInstruction, Integer>()
            .checkThat("rd", Int::rd).is(0x00).andThat("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>
                    (Str::iname, Str::rs, Str::rt)),


    /**
     * Multiply (without overflow). Put the low-order 32 bits of the product
     * of rs and rt into register rd. Is valid iff the shamt field is 0.
     */
    MUL(0x1c, 2, new Condition<RTypeInstruction, Integer>()
            .checkThat("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>(
                    Str::iname, Str::rd, Str::rs, Str::rt)),

    /**
     * Multiply add. Multiply registers rs and rt (5 and 5 bits, respectively)
     * and add the resulting 64-bit product to the 64-bit value in the
     * concatenated registers lo and hi. Is only valid if rd and shamt
     * are both zero
     */
    MADD(0x1c, 0, new Condition<RTypeInstruction, Integer>()
            .checkThat("rd", Int::rd).and("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>
                    (Str::iname, Str::rs, Str::rt)),

    /**
     * Unsigned multiply add. Multiply registers rs and rt (5 and 5
     * bits, respectively)
     * and add the resulting 64-bit product to the 64-bit value in the
     * concatenated registers lo and hi. Is only valid if rd and shamt
     * are both zero
     */
    MADDU(0x1c, 1, new Condition<RTypeInstruction, Integer>()
            .checkThat("rd", Int::rd).is(0x00).andThat("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>
                    (Str::iname, Str::rs, Str::rt)),

    /**
     * Multiply subtract. Multiply registers rs and rt and subtract the
     * resulting 64-bit product from the 64-bit value in the
     * concatenated registers lo and hi. Is only valid if both rd
     * and shamt are 0. Is only valid if rd and shamt are both 0.
     */
    MSUB(0x1c, 4, new Condition<RTypeInstruction, Integer>()
            .checkThat("rd", Int::rd).is(0x00).andThat("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>
                    (Str::iname, Str::rs, Str::rt)),

    /**
     * Unsigned multiply subtract. Multiply registers rs and rt and subtract
     * the resulting 64-bit product from the 64-bit value in the
     * concatenated registers lo and hi. Is only valid if both rd
     * and shamt are 0.
     */
    MSUBU(0x1c, 5, new Condition<RTypeInstruction, Integer>()
            .checkThat("rd", Int::rd).is(0x00).andThat("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>
                    (Str::iname, Str::rs, Str::rt)),
    
    /**
     * Put the logical NOR of registers rs and rt into register rd.
     * Is only valid if shamt is 0.
     */
    NOR(0x00, 0x27, new Condition<RTypeInstruction, Integer>()
            .checkThat("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>(Str::iname, Str::rd, Str::rs, Str::rt)),

    /**
     * Put the logical OR of registers rs and rt into register rd.
     * Is only valid if shamt is 0.
     */
    OR(0x00, 0x25, new Condition<RTypeInstruction, Integer>()
            .checkThat("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>(Str::iname, Str::rd, Str::rs, Str::rt)),

    /**
     * Shift left logical. Shift register rt left by the distance indicated
     * by immediate shamt and put the result in register rd.
     */
    SLL(0x00, 0x00, new MnemonicPattern<>(
            Str::iname, Str::rd,  Str::rt,  Str::shamt)),

    /**
     * Shift left logical variable. Shift register rt left by the distance indicated
     * by immediate shamt or register rs and put the result in register rd.
     * Is only valid if shamt is 0.
     */
    SLLV(0x00, 4, new Condition<RTypeInstruction, Integer>()
            .checkThat("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>(
                    Str::iname, Str::rd, Str::rt, Str::rs)),

    /**
     * Shift right arithmetic. Shift register rt left by the distance indicated
     * by immediate shamt and put the result in register rd.
     */
    SRA(0x00, 0x03,
            new MnemonicPattern<>(
                    Str::iname, Str::rd, Str::rt, Str::shamt)),

    /**
     * Shift right arithmetic variable. Shift register rt left by the distance indicated
     * by immediate shamt or register rs and put the result in register rd.
     * Is only valid if shamt is 0.
     */
    SRAV(0x00, 7, new Condition<RTypeInstruction, Integer>()
            .checkThat("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>(
                    Str::iname, Str::rd,  Str::rt,  Str::rs)),

    /**
     * Shift right logical. Shift register rt left by the distance indicated
     * by immediate shamt and put the result in register rd.
     */
    SRL(0x00, 0x02, new MnemonicPattern<>(
            Str::iname, Str::rd,  Str::rt,  Str::shamt)),

    /**
     * Shift right logical variable. Shift register rt left by the distance indicated
     * by immediate shamt or register rs and put the result in register rd.
     * Is only valid if shamt is 0.
     */
    SRLV(0x00, 0x06, new Condition<RTypeInstruction, Integer>()
            .checkThat("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>(Str::iname, Str::rd,  Str::rt,  Str::rs)),

    /**
     * Subtract (with overflow). Put the difference of registers rs and rt
     * into register rd.
     */
    SUB(0x00, 0x22, new Condition<RTypeInstruction, Integer>()
            .checkThat("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>(
                    Str::iname, Str::rd,  Str::rs,  Str::rt)),

    /**
     * Subtract (without overflow). Put the difference of registers rs and rt
     * into register rd.
     */
    SUBU(0x00, 0x23, new Condition<RTypeInstruction, Integer>()
            .checkThat("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>(
                    Str::iname, Str::rd,  Str::rs,  Str::rt)),

    /** Put the logical XOR of registers rs and rt into register rd. */
    XOR(0x00, 0x26, new Condition<RTypeInstruction, Integer>()
            .checkThat("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>(
                    Str::iname, Str::rd,  Str::rs,  Str::rt)),

    /**
     * Trap if equal. If register rs is equal to register rt, raise a
     * Trap exception.
     */
    TEQ(0x00, 0x52, new Condition<RTypeInstruction, Integer>()
            .checkThat("shamt", Int::shamt).is(0x00).andThat("rd", Int::rd).is(0x00),
            new MnemonicPattern<>(
                    Str::iname, Str::rs,  Str::rt)),

    /**
     * Trap if greater equal. If register rs is greater than or equal to
     * register rt, raise a Trap exception.
     */
    TGE(0x00, 0x48, new Condition<RTypeInstruction, Integer>()
            .checkThat("shamt", Int::shamt).is(0x00).andThat("rd", Int::rd).is(0x00),
            new MnemonicPattern<>(
                    Str::iname, Str::rs,  Str::rt)),

    /**
     * Unsigned trap if greater equal. If register rs is greater than or equal
     * to register rt, raise a Trap exception.
     */
    TGEU(0x00, 0x49, new Condition<RTypeInstruction, Integer>()
            .checkThat("shamt", Int::shamt).is(0x00).andThat("rd", Int::rd).is(0x00),
            new MnemonicPattern<>(
                    Str::iname, Str::rs,  Str::rt)),

    /**
     * Trap if less than. If register rs is less than register rt, raise a
     * Trap exception.
     */
    TLT(0x00, 0x50, new Condition<RTypeInstruction, Integer>()
            .checkThat("shamt", Int::shamt).is(0x00).andThat("rd", Int::rd).is(0x00),
            new MnemonicPattern<>(
                    Str::iname, Str::rs,  Str::rt)),

    /**
     * Trap if less than unsigned. If register rs is less than register rt,
     * raise a Trap exception.
     */
    TLTU(0x00, 0x51, new Condition<RTypeInstruction, Integer>()
            .checkThat("shamt", Int::shamt).is(0x00).andThat("rd", Int::rd).is(0x00),
            new MnemonicPattern<>(
                    Str::iname, Str::rs,  Str::rt)),

    /**
     * Move from hi
     * The multiply and divide unit produces its result in two additional
     * registers, hi and lo. This instruction moves the hi register
     * to rd.
     */
    MFHI(0x00, 0x10, new Condition<RTypeInstruction, Integer>()
            .checkThat("rs", Int::rs).is(0x00).
                    andThat("rt", Int::rt).is(0x00).
                    andThat("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>(
                    Str::iname, Str::rd)),

    /**
     * Move from lo
     * The multiply and divide unit produces its result in two additional
     * registers, hi and lo. This instruction moves values from the lo
     * register to rd.
     */
    MFLO(0x00, 0x10, new Condition<RTypeInstruction, Integer>()
            .checkThat("rs", Int::rs).is(0x00).
                    andThat("rt", Int::rt).is(0x00).
                    andThat("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>(
                    Str::iname, Str::rd)),

    /**
     * Move to hi, move register rs to the hi register.
     */
    MTHI(0x00, 0x11, new Condition<RTypeInstruction, Integer>()
            .checkThat("rt", Int::rt).is(0x00).
                    andThat("rd", Int::rd).is(0x00).
                    andThat("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>(
                    Str::iname, Str::rs)),


    /**
     * Move to lo, move register rs to the lo register.
     */
    MTLO(0x00, 0x13, new Condition<RTypeInstruction, Integer>()
            .checkThat("rt", Int::rt).is(0x00).
                    andThat("rd", Int::rd).is(0x00).
                    andThat("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>(
                    Str::iname, Str::rs)),

    /**
     * Move from coprocessor 0. Move register rd in a coprocessor (register
     * fs in the FPU) to CPU register rt. The floating-point unit is
     * coprocessor 1.
     */
    MFC0(0x10, 0x00, new Condition<RTypeInstruction, Integer>()
            .checkThat("rs", Int::rs).and("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>(Str::iname, Str::rt,  Str::rd)),

    /**
     * Move from coprocessor 1. Move register rd in a coprocessor (register
     * fs in the FPU) to CPU register rt. The floating-point unit is
     * coprocessor 1. Note that fs occupies the rd field
     */
    MFC1(0x11, 0x00, new Condition<RTypeInstruction, Integer>()
            .checkThat("rs", Int::rs).and("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>(Str::iname, Str::rt,  Str::fs)),

    /**
     * Move to coprocessor 0, move CPU register rt to register
     * rd in a coprocessor
     */
    MTC0(0x10, 0x00, new Condition<RTypeInstruction, Integer>()
            .checkThat("rs", Int::rs).is(0x04).
                    andThat("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>(Str::iname, Str::rd,  Str::rt)),

    /**
     * Move to coprocessor 0, move CPU register rt to register
     * fs in the FPU. fs occupies the rd field. Note the pattern that
     * the MTC and MTF operations share the same opcode and funct
     * field. The rs field distinguishes them.
     */
    MTC1(0x11, 0x00, new Condition<RTypeInstruction, Integer>()
            .checkThat("rs", Int::rs).is(0x04).
                    andThat("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>(Str::iname, Str::rt,  Str::fs)),

    /**
     * Move conditional not zero. Move register rs to register rd if
     * register rt is not zero.
     */
    MOVN(0x00, 0x11, new MnemonicPattern<>(Str::iname, Str::rd,  Str::rs,  Str::rt)),

    /**
     * Move conditional zero. Move register rs to register rd if
     * register rt is zero.
     */
    MOVZ(0x00, 0x10, new MnemonicPattern<>(Str::iname, Str::rd,  Str::rs,  Str::rt)),

    /**
     * Set less than. Set register rd to 1 if register rs is less than rt,
     * otherwise set register rd to 0.
     */
    SLT(0x00, 0x42, new MnemonicPattern<>(Str::iname, Str::rd,  Str::rs,  Str::rt)),

    /**
     * Set less than unsigned. Set register rd to 1 if register rs is
     * less than rt, otherwise set register rd to 0.
     */
    SLTU(0x00, 0x43, new MnemonicPattern<>(Str::iname, Str::rd,  Str::rs,  Str::rt)),

    /**
     * Unconditionally jump to the instruction whose address is in register
     * rs. Save the address of the next instruction in register rd.
     */
    JALR(0x00, 0x09, new Condition<RTypeInstruction, Integer>()
            .checkThat("rs", Int::rs).and("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>(Str::iname, Str::rs,  Str::rd)),

    /**
     * Unconditionally jump to the instruction whose address is in
     * register rs.
     */
    JR(0x00, 0x08, new Condition<RTypeInstruction, Integer>()
            .checkThat("rt", Int::rt).is(0x00).
                    andThat("rd", Int::rd).is(0x00).
                    andThat("shamt", Int::shamt).is(0x00),
            new MnemonicPattern<>(Str::iname, Str::rs)),

    /** Do nothing */
    NOP(0x00, 0x00, new Condition<RTypeInstruction, Integer>()
            .checkThat("rt", Int::rt).and("rs", Int::rs).
                    and("rd", Int::rd).and("shamt", Int::shamt).is(0x00)
            ,
            new MnemonicPattern<>(Str::iname))
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

    /**
     * The OpcodeFunctPair uniquely identifies this instruction.
     */
    private final OpcodeFunctPair pair;

    /** Set this upon use in fromNumericalRepresentation */
    private DecomposedRepresentation decomposedRepresentation;

    public int instruction;
    public int opcode;
    public int rs;
    public int rt;
    public int rd;
    public int shamt;
    public int funct;

    /**
     * All R-format instructions are decomposed into fields of the
     * same length.
     */
    private final static int[] decomposedPattern = {6, 5, 5, 5, 5, 6};

    /**
     * Describes a condition object that takes as arguments
     * RTypeInstruction instances that return an integer these return
     * values are then compared for equality against the specified
     * values given during construction.
     *
     * For an example, let
     *
     * <code>
     * Condition c = new Condition<RTypeInstruction, Integer>()
     * .checkThat("rd", Int::rd).is(0x00).andThat("shamt", Int::shamt).is(0x00)
     * </code>
     *
     * Then, when we call {@code c.evaluate(this)} in a non-static
     * method we will call each specified method (here
     * {@code Int:rd} and {@code "shamt", Int::shamt}) with the {@code this}
     * reference and check that the return value of {@code "rd", Int::rd} is
     * 0x00, and similarily that {@code "shamt", Int::shamt} returns 0x00.
     */
    private Condition<RTypeInstruction, Integer> validationConditions;
    private MnemonicPattern<RTypeInstruction> pattern;
    private MnemonicRepresentation mnemonicRepresentation;

    RTypeInstruction(int opcode, int funct,
                     Condition<RTypeInstruction, Integer>
                             validationConditions,
                     MnemonicPattern<RTypeInstruction> pattern) {
        this.pattern = pattern;
        this.opcode = opcode;
        this.funct = funct;
        pair = new OpcodeFunctPair(opcode, funct);
        this.validationConditions = validationConditions;
    }

    /** Not all instructions need satisfy a particular condition */
    RTypeInstruction(int opcode, int funct,
                     MnemonicPattern<RTypeInstruction> pattern) {
        this.pattern = pattern;
        pair = new OpcodeFunctPair(opcode, funct);
    }

    public boolean validate() {
        if (validationConditions != null) {
            if (!validationConditions.evaluate(this)) {
                StringJoiner sj = new StringJoiner(" ");
                sj.add("Errors:");
                validationConditions.getErrors().forEach(sj::add);
                throw new PartiallyLegalInstructionException(
                        instruction,
                        getFormat(instruction),
                        DecomposedRepresentation.fromNumber(instruction,
                                decomposedPattern),
                        pattern.compose(this),
                        sj.toString()
                );
            }
        }
        return true;
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

        rTypeInstruction.validate();
        return new Instruction(
                instruction,
                Format.R,
                rTypeInstruction.decomposedRepresentation,
                rTypeInstruction.mnemonicRepresentation);
    }

    private static RTypeInstruction identifyInstruction(int instruction) {
        DecomposedRepresentation d = DecomposedRepresentation.
                fromNumber(instruction, decomposedPattern);
        int[] decomposition = d.toIntArray();
        int funct = decomposition[5];
        OpcodeFunctPair key = new OpcodeFunctPair(d.opcode(), funct);

        RTypeInstruction r = map.get(key);

        if (Objects.isNull(r)) {
            /*
             * The opcode field was legal, otherwise we would not be here.
             * But, since we were unable to find an instruction matching
             * the pairing of the opcode and the funct field of the
             * supplied instruction then the funct field must have been
             * erroneous.
             */
            String err = "The supplied instruction: " + instruction
                    + " has an opcode associated with the R-format: " +
                    d.opcode() +
                    " but the pairing of the opcode and the funct field" +
                    " does not match any known instruction. funct: " +
                    funct;
            throw new PartiallyLegalInstructionException(err);
        }

        r.instruction = instruction;
        r.decomposedRepresentation = d;
        r.opcode = decomposition[0];
        r.rs = decomposition[1];
        r.rt = decomposition[2];
        r.rd = decomposition[3];
        r.shamt = decomposition[4];
        r.funct = decomposition[5];
        r.mnemonicRepresentation = r.pattern.compose(r);

        return r;
    }
    
    private static class Str {
        static String iname(RTypeInstruction instruction) {
            return instruction.name().toLowerCase();
        }

        static String rd(RTypeInstruction instruction) {
            return Register.toString(instruction.rd);
        }

        static String rs(RTypeInstruction instruction) {
            return Register.toString(instruction.rs);
        }

        static String rt(RTypeInstruction instruction) {
            return Register.toString(instruction.rt);
        }

        static String shamt(RTypeInstruction instruction) {
            return Register.toString(instruction.shamt);
        }

        static String fs(RTypeInstruction instruction) {
            return Register.toString(instruction.rd);
        }
    }

    private static class Int {
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
    }


    public static Format getFormat(int instruction) {
        int op = Opcode.toNumericalRepresentation(instruction);
        Opcode opcode = Opcode.fromNumericalRepresentation(op);

        return Format.fromOpcode(opcode);
    }

    /** Returns true if the instruction has the R-format */
    private static boolean hasCorrectFormat(int instruction) {
        return getFormat(instruction) == Format.R;
    }
}
