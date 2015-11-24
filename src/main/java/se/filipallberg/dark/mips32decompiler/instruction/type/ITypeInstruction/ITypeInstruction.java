package se.filipallberg.dark.mips32decompiler.instruction.type.ITypeInstruction;

import se.filipallberg.dark.mips32decompiler.instruction.util.DecomposedRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.util.Format;
import se.filipallberg.dark.mips32decompiler.instruction.mnemonic.MnemonicPattern;
import se.filipallberg.dark.mips32decompiler.instruction.util.Opcode;
import se.filipallberg.dark.mips32decompiler.instruction.Instruction;

import java.util.*;

public enum ITypeInstruction {
    /**
     * Addition immediate (with overflow). Put the sum of register rs and
     * the sign-extended immediate into register rt.
     */
    ADDI(0x08, ITypeMnemonicPattern::RT_RS_IMM),

    /**
     * Addition immediate (without overflow). Put the sum of register rs and
     * the sign-extended immediate into register rt.
     */
    ADDIU(0x09, ITypeMnemonicPattern::RT_RS_IMM),


    /**
     * Put the logical AND of register rs and the zero-extended immediate
     * into register rt.
     */
    ANDI(0xc, ITypeMnemonicPattern::RT_RS_IMM),

    /**
     * Put the logical OR of register rs and the zero-extended immediate
     * register into rt.
     */
    ORI(0xd, ITypeMnemonicPattern::RT_RS_IMM),

    /**
     * Put the logical XOR of register rs and the zero.extended immediate
     * into register rt.
     */
    XORI(0xe, ITypeMnemonicPattern::RT_RS_IMM),

    /**
     * Load upper immediate. Load the lower halfword of the immediate imm
     * into the upper halfword of register rt. The lower bits of the
     * register are set to 0.
     */
    LUI(0xf, ITypeMnemonicPattern::RT_IMM),

    /**
     * Trap if equal immediate. If register rs is equal to
     * the sign-extended value imm, raise a Trap Exception.
     */
    TEQI(0x01, 0xc, ITypeMnemonicPattern::RS_IMM),

    /**
     * Trap if less than immediate. If register rs is less than
     * the sign-extended value imm, raise a Trap Exception.
     */
    TLTI(0x00, 0x10, ITypeMnemonicPattern::RS_IMM),

    /**
     * Unsigned trap if less than immediate. If register rs is less than
     * the sign-extended value imm, raise a Trap Exception.
     */
    TLTIU(0x00, 0x11, ITypeMnemonicPattern::RS_IMM),

    /**
     * Trap if not equal immediate. If register rs is equal to the
     * sign-extended value imm, raise a Trap exception.
     */
    TNEI(0x01, 0xe, ITypeMnemonicPattern::RS_IMM),

    /**
     * Trap if greater equal immediate. If register rs is greater than
     * or equal to the sign-extended value imm, raise a Trap exception.
     */
    TGEI(0x01, 0x08, ITypeMnemonicPattern::RS_IMM),

    /**
     * Unsigned trap if greater equal immediate. If register rs is greater
     * than
     * or equal to the sign-extended value imm, raise a Trap exception.
     */
    TGEIU(0x01, 0x09, ITypeMnemonicPattern::RS_IMM),

    /**
     * Load byte. Load the byte at "address" into register rt. The byte is
     * sign-extended.
     */
    LB(0x20, ITypeMnemonicPattern::RT_OFFSET_RS),

    /**
     * Load byte. Load the byte at "address" into register rt. The byte is
     * not sign-extended.
     */
    LBU(0x24, ITypeMnemonicPattern::RT_OFFSET_RS),

    /**
     * Load halfword. Load the 16-bit quantity (halfword) at "address" into
     * register rt. The halfword is sign-extended.
     */
    LH(0x21, ITypeMnemonicPattern::RT_OFFSET_RS),

    /**
     * Load halfword. Load the 16-bit quantity (halfword) at "address" into
     * register rt. The halfword is not sign-extended.
     */
    LHU(0x25, ITypeMnemonicPattern::RT_OFFSET_RS),

    /**
     * Load word. Load the 32-bit quantity (word) at address into register rt.
     */
    LW(0x23, ITypeMnemonicPattern::RT_OFFSET_RS),

    /**
     * Load word left. Load the left bytes from the word at the possibly
     * unaligned "address" into rt.
     */
    LWL(0x22, ITypeMnemonicPattern::RT_OFFSET_RS),

    /**
     * Load word right. Load the right bytes from the word at the possibly
     * unaligned "address" into rt.
     */
    LWR(0x26, ITypeMnemonicPattern::RT_OFFSET_RS),

    /**
     * Load linked. Load the 32-bit quantity (word) at "address" into register
     * rt and start an atomic read-modify-write operation. This operation
     * is completed by a store conditional (sc) instruction, which will fail if
     * another processor writes into the block containing the loaded word.
     */
    LL(0x30, ITypeMnemonicPattern::RT_OFFSET_RS),

    /**
     * Store byte. Store the low byte from register rt at "address".
     */
    SB(0x28, ITypeMnemonicPattern::RT_OFFSET_RS),

    /**
     * Store halfword. Store the low halfword from register rt at "address".
     */
    SH(0x29, ITypeMnemonicPattern::RT_OFFSET_RS),

    /**
     * Store word. Store the word from register rt at "address".
     */
    SW(0x2b, ITypeMnemonicPattern::RT_OFFSET_RS),

    /**
     * Store the left bytes from register rt at the possibly unaligned address.
     */
    SWL(0x2a, ITypeMnemonicPattern::RT_ADDRESS),

    /**
     * Store the right bytes from register rt at the possibly unaligned address.
     */
    SWR(0x2e, ITypeMnemonicPattern::RT_ADDRESS),

    /**
     * Store conditional.  Load the 32-bit quantity (word) in register rt into
     * memory at "address" and complete an atomic read-modify-write operation.
     * If this atomic operation is successful, the memory word is modified and
     * register rt is set to 1. If the atomic operation fails because another
     * processor wrote to a location in the block containing the addressed word,
     * this instruction does not modify memory and writes 0 into register rt.
     */
    SC(0x38, ITypeMnemonicPattern::RT_OFFSET_RS),

    /**
     * Branch on equal. Conditionally branch the number of instructions
     * specified by the offset if register rs equals rt.
     */
    BEQ(0x04, ITypeMnemonicPattern::RS_RT_LABEL),

    /**
     * Branch on greater than equal zero. Conditionally branch the number
     * of instructions specified by the offset if register rs is greater
     * than or equal to 0.
     */
    BGEZ(0x01, 0x01, ITypeMnemonicPattern::RS_LABEL),

    /**
     * Branch on greater than equal zero and link.
     * Conditionally branch the number of instructions specified by the offset
     * if register rs is greater than or equal to 0. Save the address of
     * the next instruction in register 31.
     */
    BGEZAL(0x01, 0x11, ITypeMnemonicPattern::RS_LABEL),

    /**
     * Branch on greater than zero. Conditionally branch the number
     * of instructions specified by the offset if register rs is greater
     * than 0.
     */
    BGTZ(0x07, 0x00, ITypeMnemonicPattern::RS_LABEL),

    /**
     * Branch on less than equal zero. Conditionally branch the number
     * of instructions specified by the offset if register rs is less than
     * or equal to 0.
     */
    BLEZ(0x06, 0x00, ITypeMnemonicPattern::RS_LABEL),

    /**
     * Branch on less than zero. Conditionally branch the number
     * of instructions specified by the offset if register rs is less than 0.
     */
    BLTZ(0x01, 0x00, ITypeMnemonicPattern::RS_LABEL),

    /**
     * Conditionally branch the number of instructions specified by the offset
     * if register rs is not equal to rt.
     */
    BNE(0x05, ITypeMnemonicPattern::RS_RT_LABEL),

    /**
     * Set less than immediate. Set register rt to 1 if register rs is
     * less than the sign-extended immediate, and to 0 otherwise.
     */
    SLTI(0xa, ITypeMnemonicPattern::RT_RS_IMM),

    /**
     * Set less than unsigned immediate. Set register rt to 1 if register rs is
     * less than the sign-extended immediate, and to 0 otherwise.
     */
    SLTIU(0xb, ITypeMnemonicPattern::RT_RS_IMM),
    ;

    /**
     * Map the unique identifier instruction identifier with its
     * corresponding instruction.
     */
    private static final Map<Opcode, ITypeInstruction> map
            = new HashMap<>();
    /**
     * Maintain a set of all I-type instruction opcodes so that
     * {@link Format}
     * may map opcodes with the I-format.
     */
    private static final Set<Opcode> opcodeSet = new HashSet<>();
    static {
        Arrays.stream(ITypeInstruction.values()).forEach(e -> {
            map.put(e.opcode, e);
        });

        ITypeInstruction.map.keySet().forEach(opcodeSet::add);
    }

    private final MnemonicPattern pattern;

    /** All I-format instructions follow the same pattern */
    private final static int[] decomposedPattern = {6, 5, 5, 16};

    private final Opcode opcode;

    ITypeInstruction(int opcode, MnemonicPattern pattern) {
        this.pattern = pattern;
        this.opcode = Opcode.fromNumericalRepresentation(opcode);
    }

    /**
     * Sometimes the rt field must be consulted when more than
     * one instruction shares the same opcode. This is the case
     * when dealing with a trap or a branch instruction.
     */
    ITypeInstruction(int opcode, int rt, MnemonicPattern
            pattern) {
        this.opcode = Opcode.fromNumericalRepresentation(opcode);
        this.pattern = pattern;
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
                DecomposedRepresentation.fromNumber(instruction, decomposedPattern);

        ITypeInstruction iTypeInstruction = map.get(Opcode
                .fromInstruction(instruction));
        String iname = getITypeMnemonicPattern(instruction);

        return new Instruction(instruction,
                Format.I,
                d,
                iTypeInstruction.pattern.apply(iname, d));
    }

    private static String getITypeMnemonicPattern(int instruction) {
        return map.get(Opcode.fromInstruction(instruction))
                .name().toLowerCase();
    }

    /** Returns true if the instruction has the R-format */
    private static boolean hasCorrectFormat(int instruction) {
        int op = Opcode.toNumericalRepresentation(instruction);
        Opcode opcode = Opcode.fromNumericalRepresentation(op);

        return Format.fromOpcode(opcode) == Format.I;
    }

    private static IDecomposition getInterpreter(int instruction) {
        DecomposedRepresentation d =
                DecomposedRepresentation.fromNumber(
                        instruction,
                        decomposedPattern);

        return IDecomposition.fromDecomposedRepresentation(d);
    }
}
