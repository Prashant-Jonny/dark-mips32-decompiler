package io.github.gleipner.dark.mips32decomposer.instruction;

import io.github.gleipner.dark.mips32decomposer.util.DecomposedRepresentation;

/**
 * An instruction is an abstraction representing <i>a</i> instruction
 * from the MIPS32 instruction set. In the MIPS32 instruction set all
 * instructions are represented by 32-bit numbers. <b>All</b>
 * instructions have an opcode which is represented numerically by the 6
 * leftmost bits of the aforementioned 32-bits.
 *
 * The opcode alone is not necessarily adequate to specify which specific
 * instruction the class represents. Although it sometimes is. The opcode
 * is always sufficient to represent the format of the instruction. In the
 * context of this assignment there are three formats to take into account,
 * R-, I-, and J-format type instructions.
 *
 * Each instruction has a unique name. An instance of an instruction is
 * one with values set for its parameters, although what these parameters
 * are depend on the specific instruction but they may be, for instance a
 * source- and destination register, a label although those are but a
 * few of numerous possibilities.
 *
 * All instruction instances know their own name, and into what bit-fields
 * they decompose into. We call this the *decomposed representation*. The
 * decomposition depends on the format of the instruction.
 *
 * For an example, let us consider the <pre>{@code mul}</pre> instruction.
 * It is an R-type instruction, and its 32-bits are decomposed into fields
 * with lengths (6, 5, 5, 5, 5, 6) where the first 6-bits denote that the
 * instruction is in the R-type format and where the last 6-bits identify
 * the instruction as the <pre>{@code mul}</pre> instruction. All other
 * bit-fields represents the operands of the instruction.
 */
public abstract class Instruction {
    /** Always maintain the numerical representation of the instruction */
    private final long numericalRepresentation;
    private final OpCode opcode;
    private final InstructionName name;

    public Instruction(int instruction, InstructionName name) {
        this.numericalRepresentation = Integer.toUnsignedLong(instruction);
        opcode = OpCode.fromInstruction(instruction);
        this.name = name;
    }

    public OpCode getOpcode() {
        return opcode;
    }

    public Format getFormat() {
        return opcode.getFormat();
    }

    public InstructionName getName() {
        return name;
    }

    public abstract DecomposedRepresentation getDecomposedRepresentation();
}
