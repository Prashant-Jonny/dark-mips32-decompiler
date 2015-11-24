package se.filipallberg.dark.mips32decompiler.instruction.type.RTypeInstruction;

import se.filipallberg.dark.mips32decompiler.instruction.DecomposedRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.Register;

/**
 * A wrapper class around {@link DecomposedRepresentation} so that
 * we can retrieve bitfields from the decomposition using named methods
 * as opposed to the less human legible array indexes.
 */
class DecomposedInterpreter {
    int[] decomposition;

    private DecomposedInterpreter(int[] decomposition) {
        this.decomposition = decomposition;
    }

    static DecomposedInterpreter fromDecomposedRepresentation
            (DecomposedRepresentation d) {
        return new DecomposedInterpreter(d.toIntArray());
    }

    int op() {
        return decomposition[1];
    }

    int funct() {
        return decomposition[5];
    }

    String rd() {
        return Register.toString(decomposition[3]);
    }

    String rs() {
        return Register.toString(decomposition[1]);
    }

    String rt() {
        return Register.toString(decomposition[2]);
    }
}
