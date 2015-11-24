package se.filipallberg.dark.mips32decompiler.instruction.type.ITypeInstruction;

import se.filipallberg.dark.mips32decompiler.instruction.util.DecomposedRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.util.Register;
import se.filipallberg.dark.mips32decompiler.instruction.util.Format;
import se.filipallberg.dark.mips32decompiler.instruction.util.Opcode;

public class IDecomposition {
    private final int[] decomposition;

    private IDecomposition(int[] decomposition) {
        this.decomposition = decomposition;
    }

    public static IDecomposition fromDecomposedRepresentation
            (DecomposedRepresentation d) {
        Opcode op = Opcode.fromNumericalRepresentation(d.opcode());
        Format f = Format.fromOpcode(op);
        assert(f == Format.I);
        return new IDecomposition(d.toIntArray());
    }

    public String imm() {
        return Short.toString((short) decomposition[3]);
    }

    public String addr() {
        return Integer.toString(decomposition[3]);
    }

    public String offset() {
        return addr();
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

    public String label() { return Integer.toString(decomposition[3]); }
}
