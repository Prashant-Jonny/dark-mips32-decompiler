package se.filipallberg.dark.mips32decompiler.instruction.type.RTypeInstruction;

import se.filipallberg.dark.mips32decompiler.instruction.util.DecomposedRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.mnemonic.MnemonicRepresentation;

public class RTypeMnemonicPattern {
    public static MnemonicRepresentation RD_RS_RT(String iname,
                                           DecomposedRepresentation d) {
        RDecomposition di =
                RDecomposition.fromDecomposedRepresentation(d);

        return new MnemonicRepresentation(iname, di.rd(), di.rs(), di.rt());
    }

    public static MnemonicRepresentation RS_RT(String iname,
                                        DecomposedRepresentation d) {
        RDecomposition di =
                RDecomposition.fromDecomposedRepresentation(d);
        return new MnemonicRepresentation(iname, di.rs(), di.rt());
    }

    public static MnemonicRepresentation RD_RT_SHAMT(String iname,
                                                     DecomposedRepresentation d) {
        RDecomposition di =
                RDecomposition.fromDecomposedRepresentation(d);
        return new MnemonicRepresentation(
                iname, di.rd(), di.rt(), di.shamt());
    }

    public static MnemonicRepresentation RD_RT_RS(String iname,
                                                  DecomposedRepresentation d) {
        RDecomposition di =
                RDecomposition.fromDecomposedRepresentation(d);
        return new MnemonicRepresentation(
                iname, di.rd(), di.rt(), di.rs());
    }

    public static MnemonicRepresentation RD_RS(String iname,
                                                DecomposedRepresentation d) {
        RDecomposition di =
                RDecomposition.fromDecomposedRepresentation(d);
        return new MnemonicRepresentation(iname, di.rd(), di.rs());
    }

    public static MnemonicRepresentation RS_RD(String iname,
                                               DecomposedRepresentation
                                                       d) {
        RDecomposition di =
                RDecomposition.fromDecomposedRepresentation(d);
        return new MnemonicRepresentation(iname, di.rs(), di.rd());
    }



    public static MnemonicRepresentation RS(String iname,
                                            DecomposedRepresentation d) {
        RDecomposition di =
                RDecomposition.fromDecomposedRepresentation(d);
        return new MnemonicRepresentation(iname, di.rs());
    }

    public static MnemonicRepresentation RD(String iname,
                                            DecomposedRepresentation d) {
        RDecomposition di =
                RDecomposition.fromDecomposedRepresentation(d);
        return new MnemonicRepresentation(iname, di.rd());
    }

    public static MnemonicRepresentation RT_RD(String iname,
                                               DecomposedRepresentation
                                                       d) {
        RDecomposition di =
                RDecomposition.fromDecomposedRepresentation(d);
        return new MnemonicRepresentation(iname, di.rt(), di.rd());
    }

    public static MnemonicRepresentation RT_FS(String iname,
                                               DecomposedRepresentation
                                                       d) {
        return RT_RD(iname, d);
    }

    public static MnemonicRepresentation RD_RT(String iname,
                                               DecomposedRepresentation
                                                       d) {
        RDecomposition di =
                RDecomposition.fromDecomposedRepresentation(d);
        return new MnemonicRepresentation(iname, di.rt(), di.rd());
    }

    public static MnemonicRepresentation NOP(String iname,
                                             DecomposedRepresentation d) {
        return new MnemonicRepresentation(iname);
    }
}
