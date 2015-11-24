package se.filipallberg.dark.mips32decompiler;

import se.filipallberg.dark.mips32decompiler.instruction.Instruction;

import java.io.*;
import java.util.*;

public class MIPS32Decompiler {
    private static StringJoiner sj = new StringJoiner(" ");

    public static void main(String[] args) throws IOException {
        if (args.length == 0 || args.length > 2) {
            System.err.println("Usage: MIPS32Decompiler [OPTION] " +
                    "<number|file>");
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
            int instruction = numberFromString(args[1]);
            System.out.println(Instruction.fromInteger(instruction));
            return;
        }

        Iterator<Instruction> i = parse(new FileInputStream(args[0]));
        i.forEachRemaining(System.out::println);
    }

    public static Iterator<Instruction> parse(InputStream is) throws
            IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        List<Instruction> instructions = new ArrayList<>();
        String line;
        while (isNotNull(line = br.readLine())) {
            Instruction instruction;
            instruction = Instruction.fromInteger(numberFromString(line));
            instructions.add(instruction);
        }

        return instructions.iterator();
    }

    private static int numberFromString(String s) {
        if (s.startsWith("0x")) {
            return (int) Long.parseLong(s.substring(2), 16);
        }
        return (int) Long.parseLong(s);
    }

    private static boolean isNotNull(Object o) {
        return !Objects.isNull(o);
    }
}
