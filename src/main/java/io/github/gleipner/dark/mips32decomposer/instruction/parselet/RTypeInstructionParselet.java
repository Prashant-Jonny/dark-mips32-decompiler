package io.github.gleipner.dark.mips32decomposer.instruction.parselet;

import io.github.gleipner.dark.mips32decomposer.instruction.Format;
import io.github.gleipner.dark.mips32decomposer.instruction.Instruction;

import java.util.HashMap;
import java.util.Map;

public class RTypeInstructionParselet {
    private static final Map<Shamt, InstructionConstructor> map = new HashMap<>();

    public static Instruction parse(int instruction) {
        return map.get(Shamt.fromInstruction(instruction)).apply(instruction);
    }

    /**
     * Associates the shamt with the corresponding instruction constructor.
     */
    private static void put(int shamt, InstructionConstructor constructor) {
        map.put(Shamt.fromNumber(shamt), constructor);
    }

    static {
       put(0x02, fromPattern_INAME_RD_RS_RT("mul"));
    }

    private static final class Shamt {
        private final int numericRepresentation;

        private Shamt(int numericRepresentation) {
            this.numericRepresentation = numericRepresentation;
        }

        static Shamt fromInstruction(int instruction) {
            int shamtNo = DecomposedRepresentation.getNBits(instruction, 26, 6);
            return new Shamt(shamtNo);
        }

        static Shamt fromNumber(int number) {
            return new Shamt(number);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Shamt shamt = (Shamt) o;

            return numericRepresentation == shamt.numericRepresentation;

        }

        @Override
        public int hashCode() {
            return numericRepresentation;
        }
    }

    public static InstructionConstructor fromPattern_INAME_RD_RS_RT(String name) {
        return instruction -> new Instruction(instruction, Format.R, name) {
            @Override
            public DecomposedRepresentation getDecomposedRepresentation() {
                return DecomposedRepresentation.fromNumber(instruction,
                        6, 5, 5, 5, 6);
            }
        };
    }
}
