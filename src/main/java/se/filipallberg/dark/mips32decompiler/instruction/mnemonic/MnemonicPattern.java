package se.filipallberg.dark.mips32decompiler.instruction.mnemonic;

import se.filipallberg.dark.mips32decompiler.instruction.type.InstructionType;
import se.filipallberg.dark.mips32decompiler.instruction.type.RTypeInstruction.RTypeInstruction;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.function.Supplier;

public class MnemonicPattern<T> {
    private final Function<T, String> iname;
    private final Function<T, String>[] params;

    @SafeVarargs
    public MnemonicPattern(Function<T, String> iname,
                            Function<T, String>... params) {
        this.iname = iname;
        this.params = params;
    }

    public MnemonicRepresentation compose(T e) {
        String[] strings = new String[params.length];
        for (int i = 0 ; i < params.length; i++) {
            strings[i] = params[i].apply(e);
        }

        return new MnemonicRepresentation(iname.apply(e), strings);
    }
}
