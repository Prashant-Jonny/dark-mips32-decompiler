package io.github.gleipner.dark.mips32decomposer.instruction;

/**
 * An enum containing the names of some of the instructions in the MIPS32
 * instruction set.
 */
public enum InstructionName {
    /**
     * Addition (with overflow). Put the sum of registers rs and rt into
     * register rd.
     */
    ADD,

    /**
     * Addition (without overflow). Put the sum of registers rs and rt into
     * register rd.
     */
    ADDU,

    /**
     * Addition immediate (with overflow). Put the sum of register rs and
     * the sign-extended immediate into register rt.
     */
    ADDI,

    /**
     * Addition immediate (without overflow). Put the sum of register rs and
     * the sign-extended immediate into register rt.
     */
    ADDIU,

    /** Put the logical AND of registers rs and rt into register rd */
    AND,

    /**
     * Put the logical AND of register rs and the zero-extended immediate
     * into register rt.
     */
    ANDI,

    /**
     * Count leading ones in the word in register rs and put the result
     * into register rd. If a word is all ones, the result is 32.
     */
    CLO,

    /**
     * Count leading zeroes in the word in register rs and put the result
     * into register rd. If a word is all zeroes, the result is 32.
     */
    CLZ,

    /** Divide (with overflow). Divide register rs by register rt. */
    DIV,

    /** Divide (without overflow). Divide register rs by register rt. */
    DIVU,

    /**
     * Multiply. Multiply registers rs and rt. Leave the low-order word
     * of the product in the register lo and the high-order word in
     * register hi
     */
    MULT,

    /**
     * Unsigned multiply. Multiply registers rs and rt. Leave the low-order word
     * of the product in the register lo and the high-order word in
     * register hi
     */
    MULTU,

    /**
     * Multiply (without overflow). Put the low-order 32 bits of the product
     * of rs and rt into register rd.
     */
    MUL,

    /**
     * Multiply add. Multiply registers rs and rt (5 and 5 bits, respectively)
     * and add the resulting 64-bit product to the 64-bit value in the
     * concatenated registers lo and hi. */
    MADD,

    /**
     * Unsigned multiply add. Multiply registers rs and rt (5 and 5 bits, respectively)
     * and add the resulting 64-bit product to the 64-bit value in the
     * concatenated registers lo and hi. */
    MADDU,

    /**
     * Multiply subtract. Multiply registers rs and rt and subtract the
     * resulting 64-bit product from the 64-bit value in the
     * concatenated registers lo and hi.
     */
    MSUB,

    /**
     * Unsigned multiply subtract. Multiply registers rs and rt and subtract
     * the resulting 64-bit product from the 64-bit value in the
     * concatenated registers lo and hi.
     */
    MSUBU,

    /** Put the logical NOR of registers rs and rt into register rd. */
    NOR,

    /** Put the logical OR of registers rs and rt into register rd. */
    OR,

    /**
     * Put the logical OR of register rs and the zero-extended immediate
     * register into rt.
     */
    ORI,

    /**
     * Shift left logical. Shift register rt left by the distance indicated
     * by immediate shamt and put the result in register rd.
     */
    SLL,

    /**
     * Shift left logical variable. Shift register rt left by the distance indicated
     * by immediate shamt or register rs and put the result in register rd.
     */
    SLLV,

    /**
     * Shift right arithmetic. Shift register rt left by the distance indicated
     * by immediate shamt and put the result in register rd.
     */
    SRA,

    /**
     * Shift right arithmetic variable. Shift register rt left by the distance indicated
     * by immediate shamt or register rs and put the result in register rd.
     */
    SRAV,

    /**
     * Shift right logical. Shift register rt left by the distance indicated
     * by immediate shamt and put the result in register rd.
     */
    SRL,

    /**
     * Shift right logical variable. Shift register rt left by the distance indicated
     * by immediate shamt or register rs and put the result in register rd.
     */
    SRLV,

    /**
     * Subtract (with overflow). Put the difference of registers rs and rt
     * into register rd.
     */
    SUB,

    /**
     * Subtract (without overflow). Put the difference of registers rs and rt
     * into register rd.
     */
    SUBU,

    /** Put the logical XOR of registers rs and rt into register rd. */
    XOR,

    /**
     * Put the logical XOR of register rs and the zero.extended immediate
     * into register rt.
     */
    XORI,

    /**
     * Load upper immediate. Load the lower halfword of the immediate imm
     * into the upper halfword of register rt. The lower bits of the
     * register are set to 0.
     */
    LUI,

    /**
     * Set less than. Set register rd to 1 if register rs is less than rt,
     * otherwise set register rd to 0.
     */
    SLT,

    /**
     * Set less than unsigned. Set register rd to 1 if register rs is
     * less than rt, otherwise set register rd to 0.
     */
    SLTU,

    /**
     * Set less than immediate. Set register rt to 1 if register rs is
     * less than the sign-extended immediate, and to 0 otherwise.
     */
    SLTI,

    /**
     * Set less than unsigned immediate. Set register rt to 1 if register rs is
     * less than the sign-extended immediate, and to 0 otherwise.
     */
    SLTIU,

    /**
     * Branch on equal. Conditionally branch the number of instructions
     * specified by the offset if register rs equals rt.
     */
    BEQ,

    /**
     * Branch on greater than equal zero. Conditionally branch the number
     * of instructions specified by the offset if register rs is greater
     * than or equal to 0.
     */
    BGEZ,

    /**
     * Branch on greater than equal zero and link.
     * Conditionally branch the number of instructions specified by the offset
     * if register rs is greater than or equal to 0. Save the address of
     * the next instruction in register 31.
     */
    BGEZAL,

    /**
     * Branch on greater than zero. Conditionally branch the number
     * of instructions specified by the offset if register rs is greater
     * than 0.
     */
    BGTZ,

    /**
     * Branch on less than equal zero. Conditionally branch the number
     * of instructions specified by the offset if register rs is less than
     * or equal to 0.
     */
    BLEZ,

    /**
     * Branch on less than zero. Conditionally branch the number
     * of instructions specified by the offset if register rs is less than 0.
     */
    BLTZ,

    /**
     * Conditionally branch the number of instructions specified by the offset
     * if register rs is not equal to rt.
     */
    BNE,

    /** Unconditionally jump to the instruction at target. */
    J,

    /**
     * Unconditionally jump to the instruction at target. Save the address
     * of the next instruction in register $ra.
     */
    JAL,

    /**
     * Unconditionally jump to the instruction whose address is in register
     * rs. Save the address of the next instruction in register rd.
     */
    JALR,

    /**
     * Trap if equal. If register rs is equal to register rt, raise a
     * Trap exception.
     */
    TEQ,

    /**
     * Trap if equal immediate. If register rs is equal to the sign-extended
     * value imm, raise a Trap exception.
     */
    TEQI,

    // TODO, there are TEQ and TEQI instructions for not equal as well,
    // TODO it is weird if they share the same names.

    /**
     * Trap if greater equal. If register rs is greater than or equal to
     * register rt, raise a Trap exception.
     */
    TGE,

    /**
     * Unsigned trap if greater equal. If register rs is greater than or equal
     * to register rt, raise a Trap exception.
     */
    TGEU,

    /**
     * Trap if greater equal immediate. If register rs is greater than
     * or equal to the sign-extended value imm, raise a Trap Exception.
     */
    TGEI,

    /**
     * Unsigned trap if greater equal immediate. If register rs is greater than
     * or equal to the sign-extended value imm, raise a Trap Exception.
     */
    TGEIU,

    /**
     * Trap if less than. If register rs is less than register rt, raise a
     * Trap exception.
     */
    TLT,

    /**
     * Trap if less than unsigned. If register rs is less than register rt,
     * raise a Trap exception.
     */
    TLTU,

    /**
     * Trap if less than. If register rs is less than
     * the sign-extended value imm, raise a Trap Exception.
     */
    TLTI,

    /**
     * Unsigned trap if less than immediate. If register rs is less than
     * the sign-extended value imm, raise a Trap Exception.
     */
    TLTIU,

    /**
     * Load byte. Load the byte at "address" into register rt. The byte is
     * sign-extended.
     */
    LB,

    /**
     * Load byte. Load the byte at "address" into register rt. The byte is
     * not sign-extended.
     */
    LBU,

    /**
     * Load halfword. Load the 16-bit quantity (halfword) at "address" into
     * register rt. The halfword is sign-extended.
     */
    LH,

    /**
     * Load halfword. Load the 16-bit quantity (halfword) at "address" into
     * register rt. The halfword is not sign-extended.
     */
    LHU,

    /**
     * Load word. Load the 32-bit quantity (word) at address into register rt.
     */
    LW,

    /**
     * Load word left. Load the left bytes from the word at the possibly
     * unaligned "address" into rt.
     */
    LWL,

    /**
     * Load word right. Load the right bytes from the word at the possibly
     * unaligned "address" into rt.
     */
    LWR,

    /**
     * Load linked. Load the 32-bit quantity (word) at "address" into register
     * rt and start an atomic read-modify-write operation. This operation
     * is completed by a store conditional (sc) instruction, which will fail if
     * another processor writes into the block containing the loaded word.
     */
    LL,

    /**
     * Store byte. Store the low byte from register rt at "address".
     */
    SB,

    /**
     * Store halfword. Store the low halfword from register rt at "address".
     */
    SH,

    /**
     * Store word. Store the word from register rt at "address".
     */
    SW,

    /**
     * Store the left bytes from register rt at the possibly unaligned address.
     */
    SWL,

    /**
     * Store the right bytes from register rt at the possibly unaligned address.
     */
    SWR,

    /**
     * Store conditional.  Load the 32-bit quantity (word) in register rt into
     * memory at "address" and complete an atomic read-modify-write operation.
     * If this atomic operation is successful, the memory word is modified and
     * register rt is set to 1. If the atomic operation fails because another
     * processor wrote to a location in the block containing the addressed word,
     * this instruction does not modify memory and writes 0 into register rt.
     */
    SC,

    /**
     * Move conditional not zero. Move register rs to register rd if
     * register rt is not zero.
     */
    MOVN,

    /**
     * Move conditional zero. Move register rs to register rd if
     * register rt is zero.
     */
    MOVZ,

    /** No operation. Do nothing */
    NOP;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
