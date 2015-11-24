package se.filipallberg.dark.mips32decompiler.instruction.type.RTypeInstruction;

import se.filipallberg.dark.mips32decompiler.instruction.util.DecomposedRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.util.Register;
import se.filipallberg.dark.mips32decompiler.instruction.util.Format;
import se.filipallberg.dark.mips32decompiler.instruction.util.Opcode;

/**
 * A wrapper class around {@link DecomposedRepresentation} so that
 * we can retrieve bitfields from the decomposition using named methods
 * as opposed to the less human legible array indexes.
 */
class RDecomposition {
    private final int[] decomposition;

    private RDecomposition(int[] decomposition) {
        this.decomposition = decomposition;
    }

    public static RDecomposition fromDecomposedRepresentation
            (DecomposedRepresentation d) {
        Opcode op = Opcode.fromNumericalRepresentation(d.opcode());
        Format f = Format.fromOpcode(op);
        assert(f == Format.R);
        return new RDecomposition(d.toIntArray());
    }

    public int op() {
        return decomposition[0];
    }

    public int funct() {
        return decomposition[5];
    }

    public String rd() {
        return Register.toString(decomposition[3]);
    }

    public String rs() {
        return Register.toString(decomposition[1]);
    }

    public String rt() {
        return Register.toString(decomposition[2]);
    }

    public String shamt() {
        return Integer.toString(decomposition[4]);
    }
}
