package se.filipallberg.dark.mips32decompiler.instruction.type;

import se.filipallberg.dark.mips32decompiler.instruction.DecomposedRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.InstructionName;
import se.filipallberg.dark.mips32decompiler.instruction.mnemonic.MnemonicPattern;
import se.filipallberg.dark.mips32decompiler.instruction.mnemonic.MnemonicRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.opcode.Opcode;

import java.util.*;

public enum RTypeInstruction {
    MUL(0x1c, 2, RTypeInstruction::RD_RS_RT)
    ;

    private static final Map<OpcodeFunctPair, RTypeInstruction> map = new
            HashMap<>();
    static {
        Arrays.stream(RTypeInstruction.values()).forEach(e -> {
            map.put(e.pair, e);
        });
    }

    private final MnemonicPattern pattern;
    private final OpcodeFunctPair pair;
    private final static int[] decomposedPattern = {6, 5, 5, 5, 5, 6};

    RTypeInstruction(int opcode, int funct,
                     MnemonicPattern pattern) {
        this.pattern = pattern;
        pair = new OpcodeFunctPair(opcode, funct);
    }

    public static String getInstructionName(int instruction) {
        DecomposedRepresentation d = DecomposedRepresentation.fromNumber
                (instruction, decomposedPattern);
        return map.get(new OpcodeFunctPair(d.opcode(), d.funct())).name
                ().toLowerCase();
    }

    private static MnemonicRepresentation RD_RS_RT(InstructionName iname,
                                                   DecomposedRepresentation d) {
        return new MnemonicRepresentation(iname, d.rd(), d.rs(), d.rt());
    }

    private static class OpcodeFunctPair {
        private final int opcode;
        private final int funct;
        OpcodeFunctPair(int opcode, int funct) {
            this.opcode = opcode;
            this.funct = funct;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            OpcodeFunctPair that = (OpcodeFunctPair) o;

            if (opcode != that.opcode) return false;
            return funct == that.funct;

        }

        @Override
        public int hashCode() {
            int result = opcode;
            result = 31 * result + funct;
            return result;
        }
    }
}
