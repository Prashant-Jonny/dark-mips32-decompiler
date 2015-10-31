package io.github.gleipner.dark.mips32decomposer.instruction.parselet;

import io.github.gleipner.dark.mips32decomposer.instruction.Instruction;

import java.util.HashMap;
import java.util.Map;

public class RTypeInstructionParselet implements Parselet {
    private long instruction;

    private static final Map<Shamt, String> map = new HashMap<>();

    @Override
    public Instruction parse(int instruction) {
        this.instruction = Integer.toUnsignedLong(instruction);

        return null;
    }

    /**
     * Associates the shamt with the corresponding instruction.
     */
    private static void put(int shamt, String name) {
        map.put(Shamt.fromInstruction(shamt), name);
    }

    private static String get(int shamt) {
        return map.get(shamt);
    }

    static {
       put(0x02, "mul");
    }

    private static class Shamt {
        private int numericRepresentation;

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
    }
}
