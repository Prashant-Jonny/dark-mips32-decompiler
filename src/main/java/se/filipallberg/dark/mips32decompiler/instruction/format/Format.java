package se.filipallberg.dark.mips32decompiler.instruction.format;

import se.filipallberg.dark.mips32decompiler.instruction.opcode.ITypeInstructionOpcodeSet;
import se.filipallberg.dark.mips32decompiler.instruction.opcode.JTypeInstructionOpcodeSet;
import se.filipallberg.dark.mips32decompiler.instruction.opcode.Opcode;
import se.filipallberg.dark.mips32decompiler.instruction.type.RTypeInstruction.RTypeInstruction;

import java.util.HashMap;
import java.util.Map;

public enum Format {
    R,
    I,
    J;
    private static final Map<Opcode, Format> formatMap = new HashMap<>();

    public static Format fromOpcode(Opcode op) {
        if (formatMap.containsKey(op)) {
            return formatMap.get(op);
        }
        String err = "The opcode: " + op.toNumericalRepresentation()
                + " is not associated with any known format.";

        throw new IllegalStateException(err);
    }

    static {
        RTypeInstruction.getOpcodeSet().forEach(op -> {
            formatMap.put(op, Format.R);
        });

        new ITypeInstructionOpcodeSet().all().forEach(op -> {
            formatMap.put(op, Format.I);
        });

        new JTypeInstructionOpcodeSet().all().forEach(op -> {
            formatMap.put(op, Format.J);
        });
    }
}
