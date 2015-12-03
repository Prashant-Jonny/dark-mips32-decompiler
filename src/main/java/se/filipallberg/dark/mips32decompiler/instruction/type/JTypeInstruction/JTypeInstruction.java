package se.filipallberg.dark.mips32decompiler.instruction.type.JTypeInstruction;

import se.filipallberg.dark.mips32decompiler.instruction.util.DecomposedRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.util.Format;
import se.filipallberg.dark.mips32decompiler.instruction.mnemonic.MnemonicRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.util.Opcode;
import se.filipallberg.dark.mips32decompiler.instruction.Instruction;

import java.util.*;

public enum JTypeInstruction {
    /** Unconditionally jump to the instruction at target. */
    J(0x02),

    /**
     * Unconditionally jump to the instruction at target. Save the address
     * of the next instruction in register ra.
     */
    JAL(0x03),
    ;

    private static Map<Opcode, JTypeInstruction> map = new HashMap<>();
    private final Opcode opcode;

    static {
        Arrays.stream(JTypeInstruction.values()).forEach(e -> {
            map.put(e.opcode, e);
        });
    }

    JTypeInstruction(int opcode) {
        this.opcode = Opcode.fromNumericalRepresentation(opcode);
    }
    public static Set<Opcode> getOpcodeSet() {
        return map.keySet();
    }

    public static Instruction fromNumericalRepresentation(int instruction) {
        /* Validate input */
        Opcode opcode = Opcode.fromInstruction(instruction);

        if (Format.fromOpcode(opcode) != Format.J) {
            String err = "Expected an instruction in the J-format. The" +
                    " passed instruction is: " + instruction +
                    " which has the opcode: " +
                    Opcode.toNumericalRepresentation(instruction);

            throw new IllegalArgumentException(err);
        }

        /** Get the correct J-type instruction */
        DecomposedRepresentation d =
                DecomposedRepresentation.fromNumber(instruction, 6, 26);
        String iname = map.get(opcode).name().toLowerCase();

        return new Instruction(instruction,
                Format.J,
                d,
                J(iname, d));
    }

    private static MnemonicRepresentation J(String iname,
                                            DecomposedRepresentation d) {
        String target = "0x" + Integer.toHexString(d.toIntArray()[1]);
        return MnemonicRepresentation.fromString(iname + " " + target);
    }
}
