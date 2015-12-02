package se.filipallberg.dark.mips32decompiler.instruction.type.ITypeInstruction;

import se.filipallberg.dark.mips32decompiler.instruction.Condition;
import se.filipallberg.dark.mips32decompiler.instruction.PartiallyLegalInstructionException;
import se.filipallberg.dark.mips32decompiler.instruction.mnemonic.MnemonicPattern;
import se.filipallberg.dark.mips32decompiler.instruction.mnemonic.MnemonicRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.util.DecomposedRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.util.Format;
import se.filipallberg.dark.mips32decompiler.instruction.util.Opcode;
import se.filipallberg.dark.mips32decompiler.instruction.Instruction;
import se.filipallberg.dark.mips32decompiler.instruction.util.Register;

import java.util.*;
import java.util.function.Function;

public enum ITypeInstruction {
    /**
     * Addition immediate (with overflow). Put the sum of register rs and
     * the sign-extended immediate into register rt.
     */
    ADDI(0x08, new MnemonicPattern<>(Str::iname, Str::rt, Str::rs,
            Str::imm)),

    /**
     * Addition immediate (without overflow). Put the sum of register rs and
     * the sign-extended immediate into register rt.
     */
    ADDIU(0x09, new MnemonicPattern<>(Str::iname, Str::rt, Str::rs, Str::imm)),


    /**
     * Put the logical AND of register rs and the zero-extended immediate
     * into register rt.
     */
    ANDI(0xc, new MnemonicPattern<>(Str::iname, Str::rt, Str::rs, Str::imm)),

    /**
     * Put the logical OR of register rs and the zero-extended immediate
     * register into rt.
     */
    ORI(0xd, new MnemonicPattern<>(Str::iname, Str::rt, Str::rs, Str::imm)),

    /**
     * Put the logical XOR of register rs and the zero.extended immediate
     * into register rt.
     */
    XORI(0xe, new MnemonicPattern<>(Str::iname, Str::rt, Str::rs, Str::imm)),

    /**
     * Load upper immediate. Load the lower halfword of the immediate imm
     * into the upper halfword of register rt. The lower bits of the
     * register are set to 0.
     */
    LUI(0xf, new Condition<ITypeInstruction, Integer>()
            .checkThat("rs", Int::rs).is(0x00),
            new MnemonicPattern<>(Str::iname, Str::rt, Str::imm)),

    /**
     * Trap if equal immediate. If register rs is equal to
     * the sign-extended value imm, Str::raise a Trap Exception.
     */
    TEQI(0x01, 0xc, new MnemonicPattern<>(Str::iname, Str::rs, Str::imm)),

    /**
     * Trap if less than immediate. If register rs is less than
     * the sign-extended value imm, Str::raise a Trap Exception.
     */
    TLTI(0x01, 0x10, new MnemonicPattern<>(Str::iname, Str::rs, Str::imm)),

    /**
     * Unsigned trap if less than immediate. If register rs is less than
     * the sign-extended value imm, Str::raise a Trap Exception.
     */
    TLTIU(0x01, 0x11, new MnemonicPattern<>(Str::iname, Str::rs, Str::imm)),

    /**
     * Trap if not equal immediate. If register rs is equal to the
     * sign-extended value imm, Str::raise a Trap exception.
     */
    TNEI(0x01, 0xe, new MnemonicPattern<>(Str::iname, Str::rs, Str::imm)),

    /**
     * Trap if greater equal immediate. If register rs is greater than
     * or equal to the sign-extended value imm, Str::raise a Trap exception.
     */
    TGEI(0x01, 0x08, new MnemonicPattern<>(Str::iname, Str::rs, Str::imm)),

    /**
     * Unsigned trap if greater equal immediate. If register rs is greater
     * than
     * or equal to the sign-extended value imm, Str::raise a Trap exception.
     */
    TGEIU(0x01, 0x09, new MnemonicPattern<>(Str::iname, Str::rs, Str::imm)),

    /**
     * Load byte. Load the byte at "address" into register rt. The byte is
     * sign-extended.
     */
    LB(0x20, new OffsetMnemonicPattern<>(Str::iname, Str::rt,
            Str::offset, Str::rs)),

    /**
     * Load byte. Load the byte at "address" into register rt. The byte is
     * not sign-extended.
     */
    LBU(0x24, new OffsetMnemonicPattern<>(Str::iname, Str::rt,
            Str::offset, Str::rs)),

    /**
     * Load halfword. Load the 16-bit quantity (halfword) at "address" into
     * register rt. The halfword is sign-extended.
     */
    LH(0x21, new OffsetMnemonicPattern<>(Str::iname, Str::rt,
            Str::offset, Str::rs)),

    /**
     * Load halfword. Load the 16-bit quantity (halfword) at "address" into
     * register rt. The halfword is not sign-extended.
     */
    LHU(0x25, new OffsetMnemonicPattern<>(Str::iname, Str::rt,
            Str::offset, Str::rs)),

    /**
     * Load word. Load the 32-bit quantity (word) at address into register rt.
     */
    LW(0x23, new OffsetMnemonicPattern<>(Str::iname, Str::rt,
            Str::offset, Str::rs)),

    /**
     * Load word left. Load the left bytes from the word at the possibly
     * unaligned "address" into rt.
     */
    LWL(0x22, new OffsetMnemonicPattern<>(Str::iname, Str::rt,
            Str::offset, Str::rs)),

    /**
     * Load word right. Load the right bytes from the word at the possibly
     * unaligned "address" into rt.
     */
    LWR(0x26, new OffsetMnemonicPattern<>(Str::iname, Str::rt, Str::offset, Str::rs)),

    /**
     * Load linked. Load the 32-bit quantity (word) at "address" into register
     * rt and start an atomic read-modify-write operation. This operation
     * is completed by a store conditional (sc) instruction, Str::which will fail if
     * another processor writes into the block containing the loaded word.
     */
    LL(0x30, new OffsetMnemonicPattern<>(Str::iname, Str::rt, Str::offset, Str::rs)),

    /**
     * Store byte. Store the low byte from register rt at "address".
     */
    SB(0x28, new OffsetMnemonicPattern<>(Str::iname, Str::rt, Str::offset, Str::rs)),

    /**
     * Store halfword. Store the low halfword from register rt at "address".
     */
    SH(0x29, new OffsetMnemonicPattern<>(Str::iname, Str::rt, Str::offset, Str::rs)),

    /**
     * Store word. Store the word from register rt at "address".
     */
    SW(0x2b, new OffsetMnemonicPattern<>(Str::iname, Str::rt, Str::offset, Str::rs)),

    /**
     * Store the left bytes from register rt at the possibly unaligned address.
     */
    SWL(0x2a, new OffsetMnemonicPattern<>(Str::iname, Str::rt,
            Str::offset, Str::rs)),

    /**
     * Store the right bytes from register rt at the possibly unaligned address.
     */
    SWR(0x2e, new OffsetMnemonicPattern<>(Str::iname, Str::rt,
            Str::offset, Str::rs)),

    /**
     * Store conditional.  Load the 32-bit quantity (word) in register rt into
     * memory at "address" and complete an atomic read-modify-write operation.
     * If this atomic operation is successful, Str::the memory word is modified and
     * register rt is set to 1. If the atomic operation fails because another
     * processor wrote to a location in the block containing the addressed word,
     * this instruction does not modify memory and writes 0 into register rt.
     */
    SC(0x38, new OffsetMnemonicPattern<>(Str::iname, Str::rt, Str::offset, Str::rs)),

    /**
     * Branch on equal. Conditionally branch the number of instructions
     * specified by the offset if register rs equals rt.
     */
    BEQ(0x04, new MnemonicPattern<>(Str::iname, Str::rs, Str::rt, Str::label)),

    /**
     * Branch on greater than equal zero. Conditionally branch the number
     * of instructions specified by the offset if register rs is greater
     * than or equal to 0.
     */
    BGEZ(0x01, 0x01, new MnemonicPattern<>(Str::iname, Str::rs, Str::label)),

    /**
     * Branch on greater than equal zero and link.
     * Conditionally branch the number of instructions specified by the offset
     * if register rs is greater than or equal to 0. Save the address of
     * the next instruction in register 31.
     */
    BGEZAL(0x01, 0x11, new MnemonicPattern<>(Str::iname, Str::rs, Str::label)),

    /**
     * Branch on greater than zero. Conditionally branch the number
     * of instructions specified by the offset if register rs is greater
     * than 0.
     */
    BGTZ(0x07, 0x00, new MnemonicPattern<>(Str::iname, Str::rs, Str::label)),

    /**
     * Branch on less than equal zero. Conditionally branch the number
     * of instructions specified by the offset if register rs is less than
     * or equal to 0.
     */
    BLEZ(0x06, 0x00, new MnemonicPattern<>(Str::iname, Str::rs, Str::label)),

    /**
     * Branch on less than zero. Conditionally branch the number
     * of instructions specified by the offset if register rs is less than 0.
     */
    BLTZ(0x01, 0x00, new MnemonicPattern<>(Str::iname, Str::rs, Str::label)),

    /**
     * Conditionally branch the number of instructions specified by the offset
     * if register rs is not equal to rt.
     */
    BNE(0x05, new MnemonicPattern<>(Str::iname, Str::rs, Str::rt, Str::label)),

    /**
     * Set less than immediate. Set register rt to 1 if register rs is
     * less than the sign-extended immediate, Str::and to 0 otherwise.
     */
    SLTI(0xa, new MnemonicPattern<>(Str::iname, Str::rt, Str::rs, Str::imm)),

    /**
     * Set less than unsigned immediate. Set register rt to 1 if register rs is
     * less than the sign-extended immediate, Str::and to 0 otherwise.
     */
    SLTIU(0xb, new MnemonicPattern<>(Str::iname, Str::rt, Str::rs, Str::imm)),
    ;

    /**
     * Map the unique identifier instruction identifier with its
     * corresponding instruction. This only applies to those instructions
     * that are not a trap or branch instruction.
     */
    private static final Map<Opcode, ITypeInstruction> opcodeIdentifiable
            = new HashMap<>();

    /**
     * If the opcode is 0x01 the rt field has to be consulted.
     */
    private static final Map<Integer, ITypeInstruction>
            rtIdentifiableMap = new HashMap<>();

    /**
     * Maintain a set of all I-type instruction opcodes so that
     * {@link Format}
     * may opcodeIdentifiable opcodes with the I-format.
     */
    private static final Set<Opcode> opcodeSet = new HashSet<>();
    static {
        Arrays.stream(ITypeInstruction.values()).forEach(e -> {
            /** Ignore branch and trap instructions */
            if (e.opcode.toNumericalRepresentation() != 0x01) {
                opcodeIdentifiable.put(e.opcode, e);
            } else {
                rtIdentifiableMap.put(e.rt, e);
            }
        });

        ITypeInstruction.opcodeIdentifiable.keySet().forEach(opcodeSet::add);
        opcodeSet.add(Opcode.fromNumericalRepresentation(0x01)); // Branch
        // and trap instructions.
    }
    
    /** All I-format instructions follow the same pattern */
    private final static int[] decomposedPattern = {6, 5, 5, 16};

    private final Opcode opcode;
    private DecomposedRepresentation decomposedRepresentation;
    private MnemonicPattern<ITypeInstruction> mnemonicPattern;
    private MnemonicRepresentation mnemonicRepresentation;

    ITypeInstruction(int opcode, MnemonicPattern<ITypeInstruction> mnemonicPattern) {
        this.mnemonicPattern = mnemonicPattern;
        this.opcode = Opcode.fromNumericalRepresentation(opcode);
    }

    private Condition<ITypeInstruction, Integer> condition;

    ITypeInstruction(int opcode, Condition<ITypeInstruction,
            Integer> condition, MnemonicPattern<ITypeInstruction> mnemonicPattern) {
        this.mnemonicPattern = mnemonicPattern;
        this.opcode = Opcode.fromNumericalRepresentation(opcode);
        this.condition = condition;
    }

    /**
     * Sometimes the rt field must be consulted when more than
     * one instruction shares the same opcode. This is the case
     * when dealing with a trap or a branch instruction.
     */
    ITypeInstruction(int opcode, int rt, MnemonicPattern<ITypeInstruction>
            mnemonicPattern) {
        this.mnemonicPattern = mnemonicPattern;
        this.opcode = Opcode.fromNumericalRepresentation(opcode);
        this.rt = rt;
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

        /** Get the correct I-type instruction */
        ITypeInstruction iTypeInstruction = identifyInstruction(instruction);
        iTypeInstruction.validate();

        return new Instruction(
                instruction,
                Format.I,
                iTypeInstruction.decomposedRepresentation,
                iTypeInstruction.mnemonicRepresentation);
    }

    private int instruction;
    public boolean validate() {
        if (condition != null) {
            if (!condition.evaluate(this)) {
                StringJoiner sj = new StringJoiner(" ");
                condition.getErrors().forEach(sj::add);
                throw new PartiallyLegalInstructionException(
                        instruction,
                        Format.fromOpcode(opcode),
                        DecomposedRepresentation.fromNumber(instruction,
                                decomposedPattern),
                        mnemonicPattern.compose(this),
                        sj.toString());
            }
        }
        return true;
    }

    public int rs;
    public int offset;
    public int label;
    public int rt;
    public int imm;

    private static ITypeInstruction identifyInstruction(int instruction) {
        DecomposedRepresentation d = DecomposedRepresentation.
                fromNumber(instruction, decomposedPattern);
        int[] decomposition = d.toIntArray();

        ITypeInstruction i;
        if (d.opcode() == 0x01) {
            i = rtIdentifiableMap.get(decomposition[2]);
        } else {
            i = opcodeIdentifiable.get(Opcode.fromInstruction(instruction));
        }
        i.instruction = instruction;
        i.rs = decomposition[1];
        i.rt = decomposition[2];
        i.imm = i.label = i.offset = decomposition[3];
        i.mnemonicRepresentation = i.mnemonicPattern.compose(i);
        i.decomposedRepresentation = d;
        return i;
    }

    /** Returns true if the instruction has the R-format */
    private static boolean hasCorrectFormat(int instruction) {
        int op = Opcode.toNumericalRepresentation(instruction);
        Opcode opcode = Opcode.fromNumericalRepresentation(op);

        return Format.fromOpcode(opcode) == Format.I;
    }

    private static class Int {
        static Integer rs(ITypeInstruction i) {
            return i.rs;
        }
    }
    
    private static class Str {
        static String iname(ITypeInstruction instruction) {
            return instruction.name().toLowerCase();
        }
        
        static String rt(ITypeInstruction instruction) {
            return Register.toString(instruction.rt);
        }

        static String rs(ITypeInstruction instruction) {
            return Register.toString(instruction.rs);
        }

        static String imm(ITypeInstruction instruction) {
            return Short.toString((short) instruction.imm);
        }

        static String label(ITypeInstruction instruction) {
            return Short.toString((short) instruction.label);
        }

        static String offset(ITypeInstruction instruction) {
            return Short.toString((short) instruction.offset);
        }
    }

    private static class OffsetMnemonicPattern<T> extends
            MnemonicPattern<T> {
        Function<T, String> iname;
        Function<T, String> rt;
        Function<T, String> offset;
        Function<T, String> rs;

        /**
         * Expects to be called with the parameters
         * Str::iname, Str::rt, Str::offset, Str::rs
         */
        public OffsetMnemonicPattern(Function<T, String> iname,
                                     Function<T, String>... params) {
            super(iname, params);
            assert(params.length == 3);
            this.iname = iname;
            rt = params[0];
            offset = params[1];
            rs = params[2];
        }

        /**
         * Yields output on the form <iname, rt, offset(rs)>
         */
        @Override
        public MnemonicRepresentation compose(T e) {
            StringBuilder sb = new StringBuilder();
            sb.append(iname.apply(e)).append(" ")
                    .append(rt.apply(e))
                    .append(", ")
                    .append(offset.apply(e))
                    .append("(")
                    .append(rs.apply(e))
                    .append(")");
            return MnemonicRepresentation.fromString(sb.toString());
        }
    }
}
