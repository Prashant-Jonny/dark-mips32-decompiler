package se.filipallberg.dark.mips32decompiler.instruction;

import se.filipallberg.dark.mips32decompiler.instruction.mnemonic.MnemonicPattern;
import se.filipallberg.dark.mips32decompiler.instruction.mnemonic.MnemonicRepresentation;

import java.util.Arrays;
import java.util.StringJoiner;

public enum InstructionName {
    /**
     * Addition immediate (with overflow). Put the sum of register rs and
     * the sign-extended immediate into register rt.
     */
    ADDI(InstructionName::RT_RS_IMM),

    /**
     * Addition immediate (without overflow). Put the sum of register rs and
     * the sign-extended immediate into register rt.
     */
    ADDIU(InstructionName::RT_RS_IMM),


    /**
     * Put the logical AND of register rs and the zero-extended immediate
     * into register rt.
     */
    ANDI(InstructionName::RT_RS_IMM),







    /**
     * Put the logical OR of register rs and the zero-extended immediate
     * register into rt.
     */
    ORI(InstructionName::RT_RS_IMM),





    /**
     * Put the logical XOR of register rs and the zero.extended immediate
     * into register rt.
     */
    XORI(InstructionName::RT_RS_IMM),

    /**
     * Load upper immediate. Load the lower halfword of the immediate imm
     * into the upper halfword of register rt. The lower bits of the
     * register are set to 0.
     */
    LUI(InstructionName::RT_IMM),

    /**
     * Set less than. Set register rd to 1 if register rs is less than rt,
     * otherwise set register rd to 0.
     */
    SLT(InstructionName::RD_RS_RT),

    /**
     * Set less than unsigned. Set register rd to 1 if register rs is
     * less than rt, otherwise set register rd to 0.
     */
    SLTU(InstructionName::RD_RS_RT),

    /**
     * Set less than immediate. Set register rt to 1 if register rs is
     * less than the sign-extended immediate, and to 0 otherwise.
     */
    SLTI(InstructionName::RT_RS_IMM),

    /**
     * Set less than unsigned immediate. Set register rt to 1 if register rs is
     * less than the sign-extended immediate, and to 0 otherwise.
     */
    SLTIU(InstructionName::RT_RS_IMM),

    /**
     * Branch on equal. Conditionally branch the number of instructions
     * specified by the offset if register rs equals rt.
     */
    BEQ(InstructionName::RS_RT_LABEL),

    /**
     * Branch on greater than equal zero. Conditionally branch the number
     * of instructions specified by the offset if register rs is greater
     * than or equal to 0.
     */
    BGEZ(InstructionName::RS_LABEL),

    /**
     * Branch on greater than equal zero and link.
     * Conditionally branch the number of instructions specified by the offset
     * if register rs is greater than or equal to 0. Save the address of
     * the next instruction in register 31.
     */
    BGEZAL(InstructionName::RS_LABEL),

    /**
     * Branch on greater than zero. Conditionally branch the number
     * of instructions specified by the offset if register rs is greater
     * than 0.
     */
    BGTZ(InstructionName::RS_LABEL),

    /**
     * Branch on less than equal zero. Conditionally branch the number
     * of instructions specified by the offset if register rs is less than
     * or equal to 0.
     */
    BLEZ(InstructionName::RS_LABEL),

    /**
     * Branch on less than zero. Conditionally branch the number
     * of instructions specified by the offset if register rs is less than 0.
     */
    BLTZ(InstructionName::RS_LABEL),

    /**
     * Conditionally branch the number of instructions specified by the offset
     * if register rs is not equal to rt.
     */
    BNE(InstructionName::RS_RT_LABEL),

    /** Unconditionally jump to the instruction at target. */
    J(InstructionName::J),

    /**
     * Unconditionally jump to the instruction at target. Save the address
     * of the next instruction in register $ra.
     */
    JAL(InstructionName::J),

    /**
     * Unconditionally jump to the instruction whose address is in register
     * rs. Save the address of the next instruction in register rd.
     */
    JALR(InstructionName::RS_RD),

    /**
     * Trap if equal. If register rs is equal to register rt, raise a
     * Trap exception.
     */
    TEQ(InstructionName::RS_RT),

    /**
     * Trap if equal immediate. If register rs is equal to the sign-extended
     * value imm, raise a Trap exception.
     */
    TEQI(InstructionName::RS_IMM),

    // TODO, there are TEQ and TEQI instructions for not equal as well,
    // TODO it is weird if they share the same names.

    /**
     * Trap if greater equal. If register rs is greater than or equal to
     * register rt, raise a Trap exception.
     */
    TGE(InstructionName::RS_RT),

    /**
     * Unsigned trap if greater equal. If register rs is greater than or equal
     * to register rt, raise a Trap exception.
     */
    TGEU(InstructionName::RS_RT),

    /**
     * Trap if greater equal immediate. If register rs is greater than
     * or equal to the sign-extended value imm, raise a Trap Exception.
     */
    TGEI(InstructionName::RS_IMM),

    /**
     * Unsigned trap if greater equal immediate. If register rs is greater than
     * or equal to the sign-extended value imm, raise a Trap Exception.
     */
    TGEIU(InstructionName::RS_IMM),

    /**
     * Trap if less than. If register rs is less than register rt, raise a
     * Trap exception.
     */
    TLT(InstructionName::RS_RT),

    /**
     * Trap if less than unsigned. If register rs is less than register rt,
     * raise a Trap exception.
     */
    TLTU(InstructionName::RS_RT),

    /**
     * Trap if less than. If register rs is less than
     * the sign-extended value imm, raise a Trap Exception.
     */
    TLTI(InstructionName::RS_IMM),

    /**
     * Unsigned trap if less than immediate. If register rs is less than
     * the sign-extended value imm, raise a Trap Exception.
     */
    TLTIU(InstructionName::RS_IMM),

    /**
     * Load byte. Load the byte at "address" into register rt. The byte is
     * sign-extended.
     */
    LB(InstructionName::RT_OFFSET_RS),

    /**
     * Load byte. Load the byte at "address" into register rt. The byte is
     * not sign-extended.
     */
    LBU(InstructionName::RT_OFFSET_RS),

    /**
     * Load halfword. Load the 16-bit quantity (halfword) at "address" into
     * register rt. The halfword is sign-extended.
     */
    LH(InstructionName::RT_OFFSET_RS),

    /**
     * Load halfword. Load the 16-bit quantity (halfword) at "address" into
     * register rt. The halfword is not sign-extended.
     */
    LHU(InstructionName::RT_OFFSET_RS),

    /**
     * Load word. Load the 32-bit quantity (word) at address into register rt.
     */
    LW(InstructionName::RT_OFFSET_RS),

    /**
     * Load word left. Load the left bytes from the word at the possibly
     * unaligned "address" into rt.
     */
    LWL(InstructionName::RT_OFFSET_RS),

    /**
     * Load word right. Load the right bytes from the word at the possibly
     * unaligned "address" into rt.
     */
    LWR(InstructionName::RT_OFFSET_RS),

    /**
     * Load linked. Load the 32-bit quantity (word) at "address" into register
     * rt and start an atomic read-modify-write operation. This operation
     * is completed by a store conditional (sc) instruction, which will fail if
     * another processor writes into the block containing the loaded word.
     */
    LL(InstructionName::RT_OFFSET_RS),

    /**
     * Store byte. Store the low byte from register rt at "address".
     */
    SB(InstructionName::RT_OFFSET_RS),

    /**
     * Store halfword. Store the low halfword from register rt at "address".
     */
    SH(InstructionName::RT_OFFSET_RS),

    /**
     * Store word. Store the word from register rt at "address".
     */
    SW(InstructionName::RT_OFFSET_RS),

    /**
     * Store the left bytes from register rt at the possibly unaligned address.
     */
    SWL(InstructionName::RT_ADDRESS),

    /**
     * Store the right bytes from register rt at the possibly unaligned address.
     */
    SWR(InstructionName::RT_ADDRESS),

    /**
     * Store conditional.  Load the 32-bit quantity (word) in register rt into
     * memory at "address" and complete an atomic read-modify-write operation.
     * If this atomic operation is successful, the memory word is modified and
     * register rt is set to 1. If the atomic operation fails because another
     * processor wrote to a location in the block containing the addressed word,
     * this instruction does not modify memory and writes 0 into register rt.
     */
    SC(InstructionName::RT_OFFSET_RS),

    /**
     * Move conditional not zero. Move register rs to register rd if
     * register rt is not zero.
     */
    MOVN(InstructionName::RD_RS_RT),

    /**
     * Move conditional zero. Move register rs to register rd if
     * register rt is zero.
     */
    MOVZ(InstructionName::RD_RS_RT),

    /** No operation. Do nothing */
    NOP((instructionName, decomposedRepresentation) ->
            MnemonicRepresentation.fromString(instructionName.toString()))
    ;

    private final MnemonicPattern pattern;

    InstructionName(MnemonicPattern pattern) {
        this.pattern = pattern;
    }

    public MnemonicPattern getMnemonicPattern() {
        return pattern;
    }

    private static MnemonicRepresentation RD_RS_RT(InstructionName iname,
                                    DecomposedRepresentation d) {
        return new MnemonicRepresentation(iname, d.rd(), d.rs(), d.rt());
    }

    

    private static MnemonicRepresentation RD_RS(InstructionName iname,
                                                   DecomposedRepresentation d) {
        return new MnemonicRepresentation(iname, d.rd(), d.rs());
    }


    private static MnemonicRepresentation RS_RD(InstructionName iname,
                                                DecomposedRepresentation
                                                        d) {
        return new MnemonicRepresentation(iname, d.rs(), d.rd());
    }



    private static MnemonicRepresentation RT_RS_IMM(InstructionName iname,
                                                    DecomposedRepresentation d) {
        return new MnemonicRepresentation(iname, d.rt(), d.rs(), d.imm());
    }

    private static MnemonicRepresentation RT_IMM(InstructionName iname,
                                                 DecomposedRepresentation d) {
        return new MnemonicRepresentation(iname, d.rt(), d.imm());
    }

    private static MnemonicRepresentation RS_IMM(InstructionName iname,
                                                 DecomposedRepresentation d) {
        return new MnemonicRepresentation(iname, d.rs(), d.imm());
    }

    private static MnemonicRepresentation RS_LABEL(InstructionName iname,
                                                 DecomposedRepresentation d) {
        return new MnemonicRepresentation(iname, d.rt(), d.label());
    }

    private static MnemonicRepresentation RS(InstructionName iname,
                                             DecomposedRepresentation d) {
        return MnemonicRepresentation.fromString(iname + " " + d.rs());
    }

    private static MnemonicRepresentation RS_RT_LABEL(InstructionName
                                                              iname,
                                                   DecomposedRepresentation d) {
        return new MnemonicRepresentation(iname, d.rs(), d.rt(), d.label
                ());
    }

    /* Output on the form: iname rt, rs(addr) */
    private static MnemonicRepresentation RT_OFFSET_RS(InstructionName
                                                               iname,
                                                       DecomposedRepresentation d) {
        return MnemonicRepresentation.fromString(iname + " " + d.rt() +
                ", " + d.offset() + "(" + d.rs() + ")");
    }

    private static MnemonicRepresentation RT_ADDRESS(InstructionName iname,
                                                        DecomposedRepresentation d) {
        return new MnemonicRepresentation(iname, d.rt(), d.addr());
    }

    private static MnemonicRepresentation J(InstructionName iname,
                                            DecomposedRepresentation d) {
        return MnemonicRepresentation.fromString(iname + " " + d.target());
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
