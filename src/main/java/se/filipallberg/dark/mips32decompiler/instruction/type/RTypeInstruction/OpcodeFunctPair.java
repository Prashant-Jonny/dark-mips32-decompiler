package se.filipallberg.dark.mips32decompiler.instruction.type.RTypeInstruction;

import se.filipallberg.dark.mips32decompiler.instruction.util.Opcode;

/**
 * An R-type instruction is determined uniquely by the combination
 * of its opcode and its <i>funct </i>(function) field. The opcode is
 * the leftmost 6-bits of the instruction when represented as a 32-bit
 * number and the rightmost 6-bits compose the <i>funct</i> field.
 *
 * A common decomposition of an R-type instruction is into bitfields
 * of lengths (6, 5, 5, 5, 5, 6). The bit-fields then represent the
 * following units of effect
 *
 * | 6 bits  | 5 bits | 5 bits | 5 bits | 5 bits | 6 bits |
 * |:-------:|:------:|:------:|:------:|:------:|:------:|
 * | op      | rs     | rt     | rd     | shamt  | funct  |
 *
 * Hence, the unique identifier (key) for an R-type instruction
 * is the tuple (opcode, funct) which has a one-to-one relation
 * with the specific instruction.
 */
class OpcodeFunctPair {
    private final int opcode;
    private final int funct;

    public OpcodeFunctPair(int opcode, int funct) {
        this.opcode = opcode;
        this.funct = funct;
    }

    public Opcode getOpcode() {
        return Opcode.fromNumericalRepresentation(opcode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OpcodeFunctPair that = (OpcodeFunctPair) o;

        return opcode == that.opcode && funct == that.funct;

    }

    @Override
    public int hashCode() {
        int result = opcode;
        result = 31 * result + funct;
        return result;
    }
}
