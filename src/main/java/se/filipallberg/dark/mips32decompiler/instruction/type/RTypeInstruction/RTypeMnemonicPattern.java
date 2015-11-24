package se.filipallberg.dark.mips32decompiler.instruction.type.RTypeInstruction;

import se.filipallberg.dark.mips32decompiler.instruction.DecomposedRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.InstructionName;
import se.filipallberg.dark.mips32decompiler.instruction.mnemonic.MnemonicRepresentation;

public class RTypeMnemonicPattern {
    static MnemonicRepresentation RD_RS_RT(InstructionName iname,
                                           DecomposedRepresentation d) {
        DecomposedInterpreter di = DecomposedInterpreter
                .fromDecomposedRepresentation(d);

        return new MnemonicRepresentation(
                iname, di.rd(), di.rs(), di.rt());
    }
}
