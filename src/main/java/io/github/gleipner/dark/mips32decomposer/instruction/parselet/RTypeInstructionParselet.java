package io.github.gleipner.dark.mips32decomposer.instruction.parselet;

import io.github.gleipner.dark.mips32decomposer.instruction.*;
import io.github.gleipner.dark.mips32decomposer.util.BitField;
import io.github.gleipner.dark.mips32decomposer.util.DecomposedRepresentation;

import java.util.HashMap;
import java.util.Map;

import static io.github.gleipner.dark.mips32decomposer.instruction.Format.R;

public class RTypeInstructionParselet {
    private static final Map<Shamt, InstructionConstructor> map = new HashMap<>();

    /**
     * Yields an {@link Instruction} instance corresponding to the
     * supplied numerical representation of the MIPS32 instruction.
     *
     * Note that the passed instruction must be in the R-format.
     *
     * @param instruction the numerical representation of a MIPS32 instruction.
     * @return the corresponding {@link Instruction} instance.
     */
    public static Instruction parse(int instruction) {
        OpCode op = OpCode.fromInstruction(instruction);
        if (op.getFormat() != R) {
            throw new UnexpectedOpCodeException(op, R);
        }
        return map.get(Shamt.fromInstruction(instruction)).apply(instruction);
    }

    /**
     * Associates the shamt with the corresponding instruction constructor.
     */
    private static void put(int shamt, InstructionConstructor constructor) {
        map.put(Shamt.fromNumber(shamt), constructor);
    }

    static {
       put(0x02, fromPattern_INAME_RD_RS_RT(InstructionName.MUL));
    }

    private static final class Shamt {
        private final int numericRepresentation;

        private Shamt(int numericRepresentation) {
            this.numericRepresentation = numericRepresentation;
        }

        static Shamt fromInstruction(int instruction) {
            int shamtNo = BitField.getNBits(instruction, 26, 6);
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

    public static InstructionConstructor fromPattern_INAME_RD_RS_RT(InstructionName name) {
        return instruction -> new Instruction(instruction, name) {
            DecomposedRepresentation decomposed =
                    DecomposedRepresentation.fromNumber(instruction, 6,
                            5, 5, 5, 5, 6);

            @Override
            public DecomposedRepresentation getDecomposedRepresentation() {
                return decomposed;
            }

            @Override
            public Mnemonic getMnemonicRepresentation() {
                int[] decomposedAsArray = decomposed.toIntArray();
                Register rd = new Register(decomposedAsArray[3]);
                Register rs = new Register(decomposedAsArray[1]);
                Register rt = new Register(decomposedAsArray[2]);
                return new Mnemonic(name::toMnemonic,
                        rd::toMnemonic,
                        rs::toMnemonic,
                        rt::toMnemonic);
            }
        };
    }
}
