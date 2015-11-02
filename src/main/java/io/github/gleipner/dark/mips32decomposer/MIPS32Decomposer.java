package io.github.gleipner.dark.mips32decomposer;

import io.github.gleipner.dark.mips32decomposer.instruction.Instruction;
import io.github.gleipner.dark.mips32decomposer.instruction.parser
        .InstructionParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.StringJoiner;

public class MIPS32Decomposer {
    private static StringJoiner sj = new StringJoiner(" ");

    public static void main(String[] args) throws IOException {
        if (args.length == 0 || args.length > 2) {
            System.err.println("Usage: MIPS32Decomposer [OPTIONS] <number|file>");
            System.err.println("OPTIONS:");
            System.err.println("    -n Specifies that input should be read from ");
            System.err.println("       from the command-line. The following ");
            System.err.println("       argument may either be a number in hexadecimal form");
            System.err.println("       or decimal form. Hexadecimal numbers must be");
            System.err.println("       preceeded by the 0x prefix.");
            System.err.println("If no option is passed, the argument passed is");
            System.err.println("assumed to be path to a filename");
        }

        if ("-n".equals(args[0])) {
            System.out.println(decompose(getNumberFromString(args[1])));
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(args[0]));

        int lineNo = 1;
        String line;
        while (isNotNull(line = br.readLine())) {
            System.out.println(lineNo + decompose(getNumberFromString(line)));
            lineNo++;
        }
    }

    private static String decompose(int instruction) {
        Instruction inst = InstructionParser.parse(instruction);
        add("Mnemonic: " + inst.toMnemonic())
                .add("Decimal: " + inst.toDecimalString())
                .add("Hex: " + inst.toHexadecimalString())
                .add("Format: " + inst.getFormat());
        return sj.toString();
    }

    private static StringJoiner add(Object o) {
        return sj.add(o.toString());
    }

    private static boolean isNotNull(Object o) {
        return !Objects.isNull(o);
    }

    private static int getNumberFromString(String s) {
        if (s.startsWith("0x")) {
            return Integer.parseInt(s.substring(2), 16);
        }

        return Integer.parseInt(s);
    }
}
