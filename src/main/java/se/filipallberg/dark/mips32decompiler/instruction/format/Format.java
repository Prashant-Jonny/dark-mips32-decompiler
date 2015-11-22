package se.filipallberg.dark.mips32decompiler.instruction.format;

import se.filipallberg.dark.mips32decompiler.instruction.DecomposedRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.InstructionName;
import se.filipallberg.dark.mips32decompiler.instruction.opcode.ITypeInstructionOpcodeSet;
import se.filipallberg.dark.mips32decompiler.instruction.opcode.Opcode;
import se.filipallberg.dark.mips32decompiler.instruction.opcode.RTypeInstructionOpcodeSet;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum Format {
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
     */
    R {
        private final Map<Integer, InstructionName> map = new
                HashMap<Integer, InstructionName>() {{
                    put(0x02, InstructionName.MUL);
                }};

        @Override
        public InstructionName getName(Opcode op, int instruction) {
            DecomposedRepresentation d = getDecomposedRepresentation
                    (instruction);

            /* Consult the funct field */
            int funct = d.toIntArray()[5];
            return map.get(funct);
        }

        @Override
        public DecomposedRepresentation getDecomposedRepresentation(
                int instruction) {
            return DecomposedRepresentation.fromNumber(instruction, 6,
                    5, 5, 5, 5, 6);
        }
    },
    I {
        private final Map<Integer, InstructionName> inameMap = new
                HashMap<Integer, InstructionName>() {{
                    put(8, InstructionName.ADDI);
                    put(43, InstructionName.SW);
                }};

        @Override
        public InstructionName getName(Opcode op, int instruction) {
            return inameMap.get(op.toNumericalRepresentation());
        }

        @Override
        public DecomposedRepresentation getDecomposedRepresentation(int instruction) {
            return DecomposedRepresentation.fromNumber(instruction, 6,
                    5, 5, 16);
        }
    },
    J {
        private final Map<Opcode, InstructionName> inameMap = new
                HashMap<>();

        @Override
        public InstructionName getName(Opcode op, int instruction) {
            return inameMap.get(op);
        }

        @Override
        public DecomposedRepresentation getDecomposedRepresentation(int instruction) {
            return DecomposedRepresentation.fromNumber(instruction, 6, 26);
        }
    };

    private static final Map<Opcode, Format> formatMap = new HashMap<>();

    public static Format fromOpcode(Opcode op) {
        if (formatMap.containsKey(op)) {
            return formatMap.get(op);
        }
        String err = "The opcode: " + op.toNumericalRepresentation()
                + " is not associated with any known format.";

        throw new IllegalStateException(err);
    }

    public abstract InstructionName getName(Opcode op, int instruction);
    public abstract DecomposedRepresentation getDecomposedRepresentation
            (int instruction);

    static {
        new RTypeInstructionOpcodeSet().all().forEach(op -> {
            formatMap.put(op, Format.R);
        });

        new ITypeInstructionOpcodeSet().all().forEach(op -> {
            formatMap.put(op, Format.I);
        });
    }
}
