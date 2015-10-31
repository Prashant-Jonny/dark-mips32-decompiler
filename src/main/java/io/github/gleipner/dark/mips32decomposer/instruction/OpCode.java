package io.github.gleipner.dark.mips32decomposer.instruction;

import io.github.gleipner.dark.mips32decomposer.ExceptionFunction;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Maps a 6-bit bit-sequence with an appropriate constructor that either
 * corresponds to the specific instruction denoted by the opcode or defers
 * to a constructor that can perform additional inspection of an
 * {@link InputStream} to eventually construct the correct {@link Instruction}.
 */
public enum OpCode {
    /**
     * Addition (with overflow). Put the sum of registers rs and rt into
     * register rd.
     */
    ADD(0x00, RTypeInstruction::new),

    /**
     * Addition (without overflow). Put the sum of registers rs and rt into
     * register rd.
     */
    ADDU(0x00, RTypeInstruction::new),

    /**
     * Addition immediate (with overflow). Put the sum of register rs and
     * the sign-extended immediate into register rt.
     */
    ADDI(0x08, ITypeInstruction::new),

    /**
     * Addition immediate (without overflow). Put the sum of register rs and
     * the sign-extended immediate into register rt.
     */
    ADDIU(0x09, ITypeInstruction::new),

    /** Put the logical AND of registers rs and rt into register rd */
    AND(0x00, RTypeInstruction::new),

    /**
     * Put the logical AND of register rs and the zero-extended immediate
     * into register rt.
     */
    ANDI(0x0c, ITypeInstruction::new),

    /**
     * Count leading ones in the word in register rs and put the result
     * into register rd. If a word is all ones, the result is 32.
     */
    CLO(0x1c, RTypeInstruction::new),

    /**
     * Count leading zeroes in the word in register rs and put the result
     * into register rd. If a word is all zeroes, the result is 32.
     */
    CLZ(0x1c, RTypeInstruction::new),

    /** Divide (with overflow). Divide register rs by register rt. */
    DIV(0x00, RTypeInstruction::new),

    /** Divide (without overflow). Divide register rs by register rt. */
    DIVU(0x00, RTypeInstruction::new),

    /**
     * Multiply. Multiply registers rs and rt. Leave the low-order word
     * of the product in the register lo and the high-order word in
     * register hi
     */
    MULT(0x00, RTypeInstruction::new),

    /**
     * Unsigned multiply. Multiply registers rs and rt. Leave the low-order word
     * of the product in the register lo and the high-order word in
     * register hi
     */
    MULTU(0x00, RTypeInstruction::new),

    /**
     * Multiply (without overflow). Put the low-order 32 bits of the product
     * of rs and rt into register rd.
     */
    MUL(0x1c, RTypeInstruction::new),

    /**
     * Multiply add. Multiply registers rs and rt (5 and 5 bits, respectively)
     * and add the resulting 64-bit product to the 64-bit value in the
     * concatenated registers lo and hi. */
    MADD(0x1c, RTypeInstruction::new),

    /**
     * Unsigned multiply add. Multiply registers rs and rt (5 and 5 bits, respectively)
     * and add the resulting 64-bit product to the 64-bit value in the
     * concatenated registers lo and hi. */
    MADDU(0x1c, RTypeInstruction::new),

    /**
     * Multiply subtract. Multiply registers rs and rt and subtract the
     * resulting 64-bit product from the 64-bit value in the
     * concatenated registers lo and hi.
     */
    MSUB(0x1c, RTypeInstruction::new),

    /**
     * Unsigned multiply subtract. Multiply registers rs and rt and subtract
     * the resulting 64-bit product from the 64-bit value in the
     * concatenated registers lo and hi.
     */
    MSUBU(0x1c, RTypeInstruction::new),

    /** Put the logical NOR of registers rs and rt into register rd. */
    NOR(0x00, RTypeInstruction::new),

    /** Put the logical OR of registers rs and rt into register rd. */
    OR(0x00, RTypeInstruction::new),

    /**
     * Put the logical OR of register rs and the zero-extended immediate
     * register into rt.
     */
    ORI(0x0d, ITypeInstruction::new),

    /**
     * Shift left logical. Shift register rt left by the distance indicated
     * by immediate shamt and put the result in register rd.
     */
    SLL(0x00, RTypeInstruction::new),

    /**
     * Shift left logical variable. Shift register rt left by the distance indicated
     * by immediate shamt or register rs and put the result in register rd.
     */
    SLLV(0x00, RTypeInstruction::new),

    /**
     * Shift right arithmetic. Shift register rt left by the distance indicated
     * by immediate shamt and put the result in register rd.
     */
    SRA(0x00, RTypeInstruction::new),

    /**
     * Shift right arithmetic variable. Shift register rt left by the distance indicated
     * by immediate shamt or register rs and put the result in register rd.
     */
    SRAV(0x00, RTypeInstruction::new),

    /**
     * Shift right logical. Shift register rt left by the distance indicated
     * by immediate shamt and put the result in register rd.
     */
    SRL(0x00, RTypeInstruction::new),

    /**
     * Shift right logical variable. Shift register rt left by the distance indicated
     * by immediate shamt or register rs and put the result in register rd.
     */
    SRLV(0x00, RTypeInstruction::new),

    /**
     * Subtract (with overflow). Put the difference of registers rs and rt
     * into register rd.
     */
    SUB(0x00, RTypeInstruction::new),

    /**
     * Subtract (without overflow). Put the difference of registers rs and rt
     * into register rd.
     */
    SUBU(0x00, RTypeInstruction::new),

    /** Put the logical XOR of registers rs and rt into register rd. */
    XOR(0x00, RTypeInstruction::new),

    /**
     * Put the logical XOR of register rs and the zero.extended immediate
     * into register rt.
     */
    XORI(0xe, ITypeInstruction::new),

    /**
     * Load upper immediate. Load the lower halfword of the immediate imm
     * into the upper halfword of register rt. The lower bits of the
     * register are set to 0.
     */
    LUI(0xf, ITypeInstruction::new),

    /**
     * Set less than. Set register rd to 1 if register rs is less than rt,
     * otherwise set register rd to 0.
     */
    SLT(0x00, RTypeInstruction::new),

    /**
     * Set less than unsigned. Set register rd to 1 if register rs is
     * less than rt, otherwise set register rd to 0.
     */
    SLTU(0x00, RTypeInstruction::new),

    /**
     * Set less than immediate. Set register rt to 1 if register rs is
     * less than the sign-extended immediate, and to 0 otherwise.
     */
    SLTI(0xa, ITypeInstruction::new),

    /**
     * Set less than unsigned immediate. Set register rt to 1 if register rs is
     * less than the sign-extended immediate, and to 0 otherwise.
     */
    SLTIU(0xb, RTypeInstruction::new),

    /**
     * Branch on equal. Conditionally branch the number of instructions
     * specified by the offset if register rs equals rt.
     */
    BEQ(0x04, ITypeInstruction::new),

    /**
     * Branch on greater than equal zero. Conditionally branch the number
     * of instructions specified by the offset if register rs is greater
     * than or equal to 0.
     */
    BGEZ(0x01, ITypeInstruction::new),

    /**
     * Branch on greater than equal zero and link.
     * Conditionally branch the number of instructions specified by the offset
     * if register rs is greater than or equal to 0. Save the address of
     * the next instruction in register 31.
     */
    BGEZAL(0x01, ITypeInstruction::new),

    /**
     * Branch on greater than zero. Conditionally branch the number
     * of instructions specified by the offset if register rs is greater
     * than 0.
     */
    BGTZ(0x07, ITypeInstruction::new),

    /**
     * Branch on less than equal zero. Conditionally branch the number
     * of instructions specified by the offset if register rs is less than
     * or equal to 0.
     */
    BLEZ(0x06, ITypeInstruction::new),

    /**
     * Branch on less than zero. Conditionally branch the number
     * of instructions specified by the offset if register rs is less than 0.
     */
    BLTZ(0x01, ITypeInstruction::new),

    /**
     * Conditionally branch the number of instructions specified by the offset
     * if register rs is not equal to rt.
     */
    BNE(0x05, ITypeInstruction::new),

    /** Unconditionally jump to the instruction at target. */
    J(0x02, JTypeInstruction::new),

    /**
     * Unconditionally jump to the instruction at target. Save the address
     * of the next instruction in register $ra.
     */
    JAL(0x03, JTypeInstruction::new),

    /**
     * Unconditionally jump to the instruction whose address is in register
     * rs. Save the address of the next instruction in register rd.
     */
    JALR(0x03, RTypeInstruction::new),

    /**
     * Trap if equal. If register rs is equal to register rt, raise a
     * Trap exception.
     */
    TEQ(0x00, RTypeInstruction::new),

    /**
     * Trap if equal immediate. If register rs is equal to the sign-extended
     * value imm, raise a Trap exception.
     */
    TEQI(0x00, ITypeInstruction::new),

    // TODO, there are TEQ and TEQI instructions for not equal as well,
    // TODO it is weird if they share the same names.

    /**
     * Trap if greater equal. If register rs is greater than or equal to
     * register rt, raise a Trap exception.
     */
    TGE(0x00, RTypeInstruction::new),

    /**
     * Unsigned trap if greater equal. If register rs is greater than or equal
     * to register rt, raise a Trap exception.
     */
    TGEU(0x00, RTypeInstruction::new),

    /**
     * Trap if greater equal immediate. If register rs is greater than
     * or equal to the sign-extended value imm, raise a Trap Exception.
     */
    TGEI(0x01, ITypeInstruction::new),

    /**
     * Unsigned trap if greater equal immediate. If register rs is greater than
     * or equal to the sign-extended value imm, raise a Trap Exception.
     */
    TGEIU(0x01, ITypeInstruction::new),

    /**
     * Trap if less than. If register rs is less than register rt, raise a
     * Trap exception.
     */
    TLT(0x00, RTypeInstruction::new),

    /**
     * Trap if less than unsigned. If register rs is less than register rt,
     * raise a Trap exception.
     */
    TLTU(0x00, RTypeInstruction::new),

    /**
     * Trap if less than. If register rs is less than
     * the sign-extended value imm, raise a Trap Exception.
     */
    TLTI(0x01, ITypeInstruction::new),

    /**
     * Unsigned trap if less than immediate. If register rs is less than
     * the sign-extended value imm, raise a Trap Exception.
     */
    TLTIU(0x01, ITypeInstruction::new),

    /**
     * Load byte. Load the byte at "address" into register rt. The byte is
     * sign-extended.
     */
    LB(0x20, ITypeInstruction::new),

    /**
     * Load byte. Load the byte at "address" into register rt. The byte is
     * not sign-extended.
     */
    LBU(0x24, ITypeInstruction::new),

    /**
     * Load halfword. Load the 16-bit quantity (halfword) at "address" into
     * register rt. The halfword is sign-extended.
     */
    LH(0x21, ITypeInstruction::new),

    /**
     * Load halfword. Load the 16-bit quantity (halfword) at "address" into
     * register rt. The halfword is not sign-extended.
     */
    LHU(0x25, ITypeInstruction::new),

    /**
     * Load word. Load the 32-bit quantity (word) at address into register rt.
     */
    LW(0x23, ITypeInstruction::new),

    /**
     * Load word left. Load the left bytes from the word at the possibly
     * unaligned "address" into rt.
     */
    LWL(0x22, ITypeInstruction::new),

    /**
     * Load word right. Load the right bytes from the word at the possibly
     * unaligned "address" into rt.
     */
    LWR(0x26, ITypeInstruction::new),

    /**
     * Load linked. Load the 32-bit quantity (word) at "address" into register
     * rt and start an atomic read-modify-write operation. This operation
     * is completed by a store conditional (sc) instruction, which will fail if
     * another processor writes into the block containing the loaded word.
     */
    LL(0x30, ITypeInstruction::new),

    /**
     * Store byte. Store the low byte from register rt at "address".
     */
    SB(0x28, ITypeInstruction::new),

    /**
     * Store halfword. Store the low halfword from register rt at "address".
     */
    SH(0x28, ITypeInstruction::new),

    /**
     * Store word. Store the word from register rt at "address".
     */
    SW(0x2b, ITypeInstruction::new),

    /**
     * Store the left bytes from register rt at the possibly unaligned address.
     */
    SWL(0x2a, ITypeInstruction::new),

    /**
     * Store the right bytes from register rt at the possibly unaligned address.
     */
    SWR(0x2e, ITypeInstruction::new),

    /**
     * Store conditional.  Load the 32-bit quantity (word) in register rt into
     * memory at "address" and complete an atomic read-modify-write operation.
     * If this atomic operation is successful, the memory word is modified and
     * register rt is set to 1. If the atomic operation fails because another
     * processor wrote to a location in the block containing the addressed word,
     * this instruction does not modify memory and writes 0 into register rt.
     */
    SC(0x38, ITypeInstruction::new),

    /**
     * Move conditional not zero. Move register rs to register rd if
     * register rt is not zero.
     */
    MOVN(0x00, RTypeInstruction::new),

    /**
     * Move conditional zero. Move register rs to register rd if
     * register rt is zero.
     */
    MOVZ(0x00, RTypeInstruction::new),

    /** No operation. Do nothing */
    NOP(0x00, RTypeInstruction::new);

    /** The numerical representation of the opcode */
    public final byte value;

    /**
     * The function corresponding to the constructor of the instruction
     * or another constructor that may continue analysis of the passed
     * input stream to correctly determine the correct instruction.
     */
    public final ExceptionFunction<Instruction, InputStream, IOException>
            constructor;

    OpCode(int value, ExceptionFunction<Instruction,
            InputStream, IOException> constructor) {
        this.value = (byte) value;
        this.constructor = constructor;
    }

    private final static Map<Byte, OpCode> map = new HashMap<>();

    static {
        for (OpCode opCode : OpCode.values()) {
            map.put(opCode.value, opCode);
        }
    }

    public static OpCode getOpcode(final byte op) {
        return map.get(op);
    }
}