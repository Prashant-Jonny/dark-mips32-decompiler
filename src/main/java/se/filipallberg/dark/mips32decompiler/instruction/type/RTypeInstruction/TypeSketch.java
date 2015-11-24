package se.filipallberg.dark.mips32decompiler.instruction.type.RTypeInstruction;

import se.filipallberg.dark.mips32decompiler.instruction.Instruction;
import se.filipallberg.dark.mips32decompiler.instruction.mnemonic.MnemonicRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.util.DecomposedRepresentation;
import se.filipallberg.dark.mips32decompiler.instruction.util.Register;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public enum TypeSketch {
    MUL(0x1c, 2, TypeSketch::rd, TypeSketch::rs, TypeSketch::rt)
    ;
    List<BitField> list = new ArrayList<>();

    TypeSketch(int opcode, int funct, BitField... fields) {
        list.addAll(Arrays.asList(fields));
    }

    public static Instruction fromInteger(int instruction) {
        DecomposedRepresentation d = DecomposedRepresentation.fromNumber
                (instruction, 6, 5, 5, 5, 5, 6);
        int[] decomposed = d.toIntArray();
        List<String> slist = new ArrayList<>();
        MUL.list.stream().forEach(e -> slist.add(e.apply(decomposed)));
        MnemonicRepresentation m = new MnemonicRepresentation("mul",
                slist.toArray(new String[slist.size()]));
    }

    @FunctionalInterface
    private interface BitField extends Function<int[], String> {

    }

    private static String rd(int[] decomposition) {
        return Register.toString(decomposition[3]);
    }

    private static String rt(int[] decomposition) {
        return Register.toString(decomposition[2]);
    }

    private static String rs(int[] decomposition) {
        return Register.toString(decomposition[1]);
    }
}
