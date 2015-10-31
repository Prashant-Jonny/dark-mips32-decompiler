package io.github.gleipner.dark.mips32decomposer.instruction.parselet;

import io.github.gleipner.dark.mips32decomposer.instruction.Instruction;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class RTypeInstructionParselet implements Parselet {
    private long instruction;

    private static final Map<Shamt,
            Function<Integer, Instruction>> map = new HashMap<>();

    static {
    }

    @Override
    public Instruction parse(int instruction) {
        this.instruction = Integer.toUnsignedLong(instruction);

        return null;
    }

    private static int getLastNBits(int bits, int n) {
        return bits & (1 << (n - 1));
    }

    private class Shamt {
        private int shamt;

        Shamt(int instruction) {
            shamt = getLastNBits(instruction, 6);
        }
    }
}
