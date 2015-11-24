package se.filipallberg.dark.mips32decompiler.instruction.type.ITypeInstruction;

import se.filipallberg.dark.mips32decompiler.instruction.util.DecomposedRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.mnemonic.MnemonicRepresentation;

public class ITypeMnemonicPattern {
    public static MnemonicRepresentation RT_RS_IMM(String iname,
                                                    DecomposedRepresentation dr) {
        IDecomposition d = fromDecomposedRepresentation(dr);
        return new MnemonicRepresentation(iname, d.rt(), d.rs(), d.imm());
    }

    public static MnemonicRepresentation RT_IMM(String iname,
                                                 DecomposedRepresentation dr) {
        IDecomposition d = fromDecomposedRepresentation(dr);
        return new MnemonicRepresentation(iname, d.rt(), d.imm());
    }

    public static MnemonicRepresentation RS_IMM(String iname,
                                                 DecomposedRepresentation dr) {
        IDecomposition d = fromDecomposedRepresentation(dr);
        return new MnemonicRepresentation(iname, d.rs(), d.imm());
    }

    public static MnemonicRepresentation RD_RS_RT(String iname,
                                                   DecomposedRepresentation dr) {
        IDecomposition d = fromDecomposedRepresentation(dr);
        return new MnemonicRepresentation(iname, d.rd(), d.rs(), d.rt());
    }
    
    public static MnemonicRepresentation RS_RD(String iname,
                                                DecomposedRepresentation
                                                        dr) {
        IDecomposition d = fromDecomposedRepresentation(dr);
        return new MnemonicRepresentation(iname, d.rs(), d.rd());
    }

    public static MnemonicRepresentation RS_LABEL(String iname,
                                                   DecomposedRepresentation dr) {
        IDecomposition d = fromDecomposedRepresentation(dr);
        return new MnemonicRepresentation(iname, d.rt(), d.label());
    }

    public static MnemonicRepresentation RS(String iname,
                                             DecomposedRepresentation dr) {
        IDecomposition d = fromDecomposedRepresentation(dr);
        return MnemonicRepresentation.fromString(iname + " " + d.rs());
    }

    public static MnemonicRepresentation RS_RT_LABEL(String iname,
                                                      DecomposedRepresentation dr) {
        IDecomposition d = fromDecomposedRepresentation(dr);
        return new MnemonicRepresentation(iname, d.rs(), d.rt(), d.label
                ());
    }

    /* Output on the form: iname rt, rs(addr) */
    public static MnemonicRepresentation RT_OFFSET_RS(String iname,
                                                       DecomposedRepresentation dr) {
        IDecomposition d = fromDecomposedRepresentation(dr);
        return MnemonicRepresentation.fromString(iname + " " + d.rt() +
                ", " + d.offset() + "(" + d.rs() + ")");
    }

    public static MnemonicRepresentation RT_ADDRESS(String iname,
                                                     DecomposedRepresentation dr) {
        IDecomposition d = fromDecomposedRepresentation(dr);
        return new MnemonicRepresentation(iname, d.rt(), d.addr());
    }
    
    public static IDecomposition fromDecomposedRepresentation
            (DecomposedRepresentation d) {
        return IDecomposition.fromDecomposedRepresentation(d);
    }
}
