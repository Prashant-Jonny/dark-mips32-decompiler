package io.github.gleipner.dark.mips32decomposer.instruction.parselet;

import io.github.gleipner.dark.mips32decomposer.instruction.Instruction;

import java.util.HashMap;
import java.util.Map;

public class RTypeInstructionParselet implements InstructionParselet {
    private static final Map<Shamt, String> map = new HashMap<>();

    public Instruction parse(int instruction) {

        return null;
    }

    /**
     * Associates the shamt with the corresponding instruction.
     */
    private static void put(int shamt, String name) {
        map.put(Shamt.fromInstruction(shamt), name);
    }

    private static String get(int shamt) {
        return map.get(Shamt.fromNumber(shamt));
    }

    static {
       put(0x02, "mul");
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
}
