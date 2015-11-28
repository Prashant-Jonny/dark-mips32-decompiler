package se.filipallberg.dark.mips32decompiler.instruction.type.ITypeInstruction;

import se.filipallberg.dark.mips32decompiler.instruction.Condition;
import se.filipallberg.dark.mips32decompiler.instruction.PartiallyLegalInstructionException;
import se.filipallberg.dark.mips32decompiler.instruction.mnemonic.MnemonicRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.type.BitField;
import se.filipallberg.dark.mips32decompiler.instruction.util.DecomposedRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.util.Format;
import se.filipallberg.dark.mips32decompiler.instruction.util.Opcode;
import se.filipallberg.dark.mips32decompiler.instruction.Instruction;
import se.filipallberg.dark.mips32decompiler.instruction.util.Register;

import java.util.*;

public enum ITypeInstruction {
    /**
     * Addition immediate (with overflow). Put the sum of register rs and
     * the sign-extended immediate into register rt.
     */
    ADDI(0x08, I::rt, I::rs, I::imm),

    /**
     * Addition immediate (without overflow). Put the sum of register rs and
     * the sign-extended immediate into register rt.
     */
    ADDIU(0x09, I::rt, I::rs, I::imm),


    /**
     * Put the logical AND of register rs and the zero-extended immediate
     * into register rt.
     */
    ANDI(0xc, I::rt, I::rs, I::imm),

    /**
     * Put the logical OR of register rs and the zero-extended immediate
     * register into rt.
     */
    ORI(0xd, I::rt, I::rs, I::imm),

    /**
     * Put the logical XOR of register rs and the zero.extended immediate
     * into register rt.
     */
    XORI(0xe, I::rt, I::rs, I::imm),

    /**
     * Load upper immediate. Load the lower halfword of the immediate imm
     * into the upper halfword of register rt. The lower bits of the
     * register are set to 0.
     */
    LUI(0xf, new Condition<ITypeInstruction, Integer>()
            .checkThat(Int::rs).is(0x00), I::rt, I::imm),

    /**
     * Trap if equal immediate. If register rs is equal to
     * the sign-extended value imm, I::raise a Trap Exception.
     */
    TEQI(0x01, 0xc, I::rs, I::imm),

    /**
     * Trap if less than immediate. If register rs is less than
     * the sign-extended value imm, I::raise a Trap Exception.
     */
    TLTI(0x01, 0x10, I::rs, I::imm),

    /**
     * Unsigned trap if less than immediate. If register rs is less than
     * the sign-extended value imm, I::raise a Trap Exception.
     */
    TLTIU(0x01, 0x11, I::rs, I::imm),

    /**
     * Trap if not equal immediate. If register rs is equal to the
     * sign-extended value imm, I::raise a Trap exception.
     */
    TNEI(0x01, 0xe, I::rs, I::imm),

    /**
     * Trap if greater equal immediate. If register rs is greater than
     * or equal to the sign-extended value imm, I::raise a Trap exception.
     */
    TGEI(0x01, 0x08, I::rs, I::imm),

    /**
     * Unsigned trap if greater equal immediate. If register rs is greater
     * than
     * or equal to the sign-extended value imm, I::raise a Trap exception.
     */
    TGEIU(0x01, 0x09, I::rs, I::imm),

    /**
     * Load byte. Load the byte at "address" into register rt. The byte is
     * sign-extended.
     */
    LB(0x20, I::rt, I::offset, I::rs),

    /**
     * Load byte. Load the byte at "address" into register rt. The byte is
     * not sign-extended.
     */
    LBU(0x24, I::rt, I::offset, I::rs),

    /**
     * Load halfword. Load the 16-bit quantity (halfword) at "address" into
     * register rt. The halfword is sign-extended.
     */
    LH(0x21, I::rt, I::offset, I::rs),

    /**
     * Load halfword. Load the 16-bit quantity (halfword) at "address" into
     * register rt. The halfword is not sign-extended.
     */
    LHU(0x25, I::rt, I::offset, I::rs),

    /**
     * Load word. Load the 32-bit quantity (word) at address into register rt.
     */
    LW(0x23, I::rt, I::offset, I::rs),

    /**
     * Load word left. Load the left bytes from the word at the possibly
     * unaligned "address" into rt.
     */
    LWL(0x22, I::rt, I::offset, I::rs),

    /**
     * Load word right. Load the right bytes from the word at the possibly
     * unaligned "address" into rt.
     */
    LWR(0x26, I::rt, I::offset, I::rs),

    /**
     * Load linked. Load the 32-bit quantity (word) at "address" into register
     * rt and start an atomic read-modify-write operation. This operation
     * is completed by a store conditional (sc) instruction, I::which will fail if
     * another processor writes into the block containing the loaded word.
     */
    LL(0x30, I::rt, I::offset, I::rs),

    /**
     * Store byte. Store the low byte from register rt at "address".
     */
    SB(0x28, I::rt, I::offset, I::rs),

    /**
     * Store halfword. Store the low halfword from register rt at "address".
     */
    SH(0x29, I::rt, I::offset, I::rs),

    /**
     * Store word. Store the word from register rt at "address".
     */
    SW(0x2b, I::rt, I::offset, I::rs),

    /**
     * Store the left bytes from register rt at the possibly unaligned address.
     */
    SWL(0x2a, I::rt, I::addr),

    /**
     * Store the right bytes from register rt at the possibly unaligned address.
     */
    SWR(0x2e, I::rt, I::addr),

    /**
     * Store conditional.  Load the 32-bit quantity (word) in register rt into
     * memory at "address" and complete an atomic read-modify-write operation.
     * If this atomic operation is successful, I::the memory word is modified and
     * register rt is set to 1. If the atomic operation fails because another
     * processor wrote to a location in the block containing the addressed word,
     * this instruction does not modify memory and writes 0 into register rt.
     */
    SC(0x38, I::rt, I::offset, I::rs),

    /**
     * Branch on equal. Conditionally branch the number of instructions
     * specified by the offset if register rs equals rt.
     */
    BEQ(0x04, I::rs, I::rt, I::label),

    /**
     * Branch on greater than equal zero. Conditionally branch the number
     * of instructions specified by the offset if register rs is greater
     * than or equal to 0.
     */
    BGEZ(0x01, 0x01, I::rs, I::label),

    /**
     * Branch on greater than equal zero and link.
     * Conditionally branch the number of instructions specified by the offset
     * if register rs is greater than or equal to 0. Save the address of
     * the next instruction in register 31.
     */
    BGEZAL(0x01, 0x11, I::rs, I::label),

    /**
     * Branch on greater than zero. Conditionally branch the number
     * of instructions specified by the offset if register rs is greater
     * than 0.
     */
    BGTZ(0x07, 0x00, I::rs, I::label),

    /**
     * Branch on less than equal zero. Conditionally branch the number
     * of instructions specified by the offset if register rs is less than
     * or equal to 0.
     */
    BLEZ(0x06, 0x00, I::rs, I::label),

    /**
     * Branch on less than zero. Conditionally branch the number
     * of instructions specified by the offset if register rs is less than 0.
     */
    BLTZ(0x01, 0x00, I::rs, I::label),

    /**
     * Conditionally branch the number of instructions specified by the offset
     * if register rs is not equal to rt.
     */
    BNE(0x05, I::rs, I::rt, I::label),

    /**
     * Set less than immediate. Set register rt to 1 if register rs is
     * less than the sign-extended immediate, I::and to 0 otherwise.
     */
    SLTI(0xa, I::rt, I::rs, I::imm),

    /**
     * Set less than unsigned immediate. Set register rt to 1 if register rs is
     * less than the sign-extended immediate, I::and to 0 otherwise.
     */
    SLTIU(0xb, I::rt, I::rs, I::imm),
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
    private int rt;
    private final List<BitField> list = new ArrayList<>();
    private DecomposedRepresentation decomposedRepresentation;

    ITypeInstruction(int opcode, BitField... bitFields) {
        list.addAll(Arrays.asList(bitFields));
        this.opcode = Opcode.fromNumericalRepresentation(opcode);
    }

    private Condition<ITypeInstruction, Integer> condition;

    ITypeInstruction(int opcode, Condition<ITypeInstruction,
            Integer> condition, BitField... bitFields) {
        list.addAll(Arrays.asList(bitFields));
        this.opcode = Opcode.fromNumericalRepresentation(opcode);
        this.condition = condition;
    }

    /**
     * Sometimes the rt field must be consulted when more than
     * one instruction shares the same opcode. This is the case
     * when dealing with a trap or a branch instruction.
     */
    ITypeInstruction(int opcode, int rt, BitField... bitFields) {
        list.addAll(Arrays.asList(bitFields));
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
        MnemonicRepresentation m = composeMnemonic(iTypeInstruction);

        return new Instruction(
                instruction,
                Format.I,
                iTypeInstruction.decomposedRepresentation,
                m);
    }

    private int instruction;
    public boolean validate() {
        if (condition != null) {
            if (!condition.evaluate(this)) {
                StringJoiner sj = new StringJoiner("\n\t", "{(0x" +
                        Integer.toHexString(instruction) + ") " +
                        "Errors:\n\t", "}");

                condition.getErrors().forEach(sj::add);
                throw new PartiallyLegalInstructionException(sj.toString());
            }
        }
        return true;
    }

    private static MnemonicRepresentation composeMnemonic
            (ITypeInstruction instruction) {
        int[] decomposition = instruction.decomposedRepresentation.toIntArray();
        List<String> strings = new ArrayList<>();
        instruction.list.forEach(e -> {
            strings.add(e.apply(decomposition));
        });
        // TODO: Deal with parantheses around address.
        return new MnemonicRepresentation
                (instruction.name().toLowerCase(),
                        strings.toArray(new String[strings.size()]));
    }
    public int rs;
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
        static Integer rs(ITypeInstruction r) {
            return r.rs;
        }
    }

    static class I {
        static String rt(int[] decomposition) {
            return Register.toString(decomposition[2]);
        }

        static String rs(int[] decomposition) {
            return Register.toString(decomposition[1]);
        }

        static String imm(int[] decomposition) {
            return Short.toString((short) decomposition[3]);
        }

        static String label(int[] decomposition) {
            return Integer.toString((short) decomposition[3]);
        }

        static String addr(int[] decomposition) {
            return Short.toString((short) decomposition[3]);
        }

        static String offset(int[] decomposition) {
            return Short.toString((short) decomposition[3]);
        }
    }
}
