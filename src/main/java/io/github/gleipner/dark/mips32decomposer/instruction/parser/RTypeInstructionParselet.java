package io.github.gleipner.dark.mips32decomposer.instruction.parser;

import io.github.gleipner.dark.mips32decomposer.instruction.Instruction;
import io.github.gleipner.dark.mips32decomposer.instruction.InstructionName;
import io.github.gleipner.dark.mips32decomposer.instruction.opcode.OpCode;
import io.github.gleipner.dark.mips32decomposer.instruction.opcode.UnexpectedOpCodeException;
import io.github.gleipner.dark.mips32decomposer.util.BitField;

import java.util.HashMap;
import java.util.Map;

import static io.github.gleipner.dark.mips32decomposer.instruction.Format.R;

public class RTypeInstructionParselet {
    private static final Map<Funct, InstructionConstructor> map = new HashMap<>();

    /**
     * Yields an {@link Instruction} instance corresponding to the
     * supplied numerical representation of the MIPS32 instruction.
     *
     * @param instruction the numerical representation of a MIPS32 instruction.
     * @return the corresponding {@link Instruction} instance.
     * @throws UnexpectedOpCodeException if the instruction is not in
     * the R-format.
     */
    public static Instruction parse(int instruction) {
        OpCode op = OpCode.fromInstruction(instruction);
        if (op.getFormat() != R) {
            throw new UnexpectedOpCodeException(op, R);
        }
        return map.get(Funct.fromInstruction(instruction)).apply(instruction);
    }

    /**
     * Associates the shamt with the corresponding instruction constructor.
     */
    private static void put(int shamt, InstructionConstructor constructor) {
        map.put(Funct.fromNumber(shamt), constructor);
    }

    static {
       put(0x02, fromPattern_INAME_RD_RS_RT(InstructionName.MUL));
    }

    private static final class Funct {
        private final int numericRepresentation;

        private Funct(int numericRepresentation) {
            this.numericRepresentation = numericRepresentation;
        }

        static Funct fromInstruction(int instruction) {
            int shamtNo = BitField.getNBits(instruction, 26, 6);
            return new Funct(shamtNo);
        }

        static Funct fromNumber(int number) {
            return new Funct(number);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Funct funct = (Funct) o;

            return numericRepresentation == funct.numericRepresentation;
        }

        @Override
        public int hashCode() {
            return numericRepresentation;
        }
    }

    private static InstructionConstructor fromPattern_INAME_RD_RS_RT(InstructionName name) {
        return ParametrizedInstructionConstructor.createPatternizedConstructor(
                name, new int[] {6, 5, 5, 5, 5, 6}, new int[] {3, 1, 2});
    }
}
