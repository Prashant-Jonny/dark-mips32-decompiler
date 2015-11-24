package se.filipallberg.dark.mips32decompiler.instruction.type.RTypeInstruction;

import se.filipallberg.dark.mips32decompiler.instruction.DecomposedRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.mnemonic.MnemonicRepresentation;

public class RTypeMnemonicPattern {
    public static MnemonicRepresentation RD_RS_RT(String iname,
                                           DecomposedRepresentation d) {
        DecomposedInterpreter di =
                DecomposedInterpreter.fromDecomposedRepresentation(d);

        return new MnemonicRepresentation(iname, di.rd(), di.rs(), di.rt());
    }

    public static MnemonicRepresentation RS_RT(String iname,
                                        DecomposedRepresentation d) {
        DecomposedInterpreter di =
                DecomposedInterpreter.fromDecomposedRepresentation(d);
        return new MnemonicRepresentation(iname, di.rs(), di.rt());
    }

    public static MnemonicRepresentation RD_RT_SHAMT(String iname,
                                                     DecomposedRepresentation d) {
        DecomposedInterpreter di =
                DecomposedInterpreter.fromDecomposedRepresentation(d);
        return new MnemonicRepresentation(
                iname, di.rd(), di.rt(), di.shamt());
    }

    public static MnemonicRepresentation RD_RT_RS(String iname,
                                                  DecomposedRepresentation d) {
        DecomposedInterpreter di =
                DecomposedInterpreter.fromDecomposedRepresentation(d);
        return new MnemonicRepresentation(
                iname, di.rd(), di.rt(), di.rs());
    }

}
