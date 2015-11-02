package io.github.gleipner.dark.mips32decomposer;

import io.github.gleipner.dark.mips32decomposer.instruction.Instruction;
import io.github.gleipner.dark.mips32decomposer.instruction.parser
        .InstructionParser;

import java.io.*;
import java.util.*;

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
            System.out.println(parse(getNumberFromString(args[1])));
            print(parse(getNumberFromString(args[1])));
            return;
        }

        Iterator<Instruction> i = parse(new FileInputStream(args[0]));
        int lineno = 1;
        while (i.hasNext()) {
            print(lineno++, i.next());
        }
    }

    public static Iterator<Instruction> parse(InputStream is) throws
            IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        List<Instruction> instructions = new ArrayList<>();
        String line;
        while (isNotNull(line = br.readLine())) {
            instructions.add(parse(getNumberFromString(line)));
        }

        return instructions.iterator();
    }

    private static Instruction parse(int instruction) {
        return InstructionParser.parse(instruction);
    }

    private static void print(Instruction instruction) {
        add("Mnemonic: " + instruction.toMnemonic())
                .add("Decimal: " + instruction.toDecimalString())
                .add("Hex: " + instruction.toHexadecimalString())
                .add("Format: " + instruction.getFormat());
        System.out.println(sj.toString());
    }

    private static void print(int lineno, Instruction instruction) {
        add("Mnemonic: " + instruction.toMnemonic())
                .add("Decimal: " + instruction.toDecimalString())
                .add("Hex: " + instruction.toHexadecimalString())
                .add("Format: " + instruction.getFormat());
        System.out.println(lineno + sj.toString());
    }

    private static StringJoiner add(Object o) {
        return sj.add(o.toString());
    }

    private static boolean isNotNull(Object o) {
        return !Objects.isNull(o);
    }

    public static int getNumberFromString(String s) {
        if (s.startsWith("0x")) {
            return Integer.parseInt(s.substring(2), 16);
        }

        return Integer.parseInt(s);
    }
}
