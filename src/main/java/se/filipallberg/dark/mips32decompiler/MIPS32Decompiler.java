package se.filipallberg.dark.mips32decompiler;

import se.filipallberg.dark.mips32decompiler.instruction.Instruction;
import se.filipallberg.dark.mips32decompiler.instruction.PartiallyLegalInstructionException;

import java.io.*;
import java.util.*;

public class MIPS32Decompiler {
    private static StringJoiner sj = new StringJoiner(" ");

    public static void main(String[] args) throws IOException {
        if (args.length == 0 || "-h".equals(args[0])) {
            System.err.println("Usage: MIPS32Decompiler [OPTION] " +
                    "<number|file>...");
            System.err.println("OPTIONS:");
            System.err.println("    -n Specifies that input should be read from ");
            System.err.println("       from the command-line. The following ");
            System.err.println("       argument(s) may either be a " +
                    "number in hexadecimal form");
            System.err.println("       or decimal form. Hexadecimal numbers must be");
            System.err.println("       preceeded by the 0x prefix.");
            System.err.println("    -h Shows this help message.");
            System.err.println("If no option is passed, the argument(s) " +
                    "passed is");
            System.err.println("assumed to be path to a filename");
            return;
        }

        List<Integer> numbers = new ArrayList<>();
        if ("-n".equals(args[0])) {
            for (int i = 1; i < args.length; i++) {
                numbers.add(numberFromString(args[i]));
            }
            outputTable(numbers);
        } else {
            for (String arg : args) {
                BufferedReader br = new BufferedReader(new FileReader
                        (arg));

                String line;
                while (isNotNull(line = br.readLine())) {
                    if (line.isEmpty()) {
                        continue;
                    }
                    numbers.add(numberFromString(line));
                }
                outputTable(numbers);
            }
        }
    }

    public static void outputTable(List<Integer> numbers)
            throws IOException {
        String instruction = "Instruction";
        String format = "Fmt";
        String decomposition = "Decomposition";
        String hex = "Decomp hex";
        String decompiled = "Source";
        Object[] header = {instruction, format, decomposition, hex,
                decompiled};

        System.out.format("%-15s %-2s %-15s %-22s %-18s\n", header);

        numbers.forEach(e -> {
            try {
                Instruction i;
                i = Instruction.fromInteger(e);
                Object[] row = {
                        Instruction.asPaddedHexString(i.toNumericalRepresentation()),
                        i.getFormat(), i.asDecimalString(), i
                        .asHexadecimalString(),
                        i.mnemonic()};
                System.out.format("%-15s %-2s %-15s %-22s %-18s\n", row);
            } catch (PartiallyLegalInstructionException exception) {
                Object[] row = exception.getPartialOutput();

                System.out.format("%-15s %-2s %-15s %-22s %-18s\n", row);
                System.out.format("  Errors: %-15s\n", row[row.length -
                        1]);
            }
        });
    }

    public static Iterable<String> parse(InputStream is) throws
            IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        List<String> disassembledCode = new ArrayList<>();
        String line;
        while (isNotNull(line = br.readLine())) {
            if (line.isEmpty()) { continue; }
            try {
                Instruction instruction;
                instruction = Instruction.fromInteger(numberFromString(line));
                disassembledCode.add(instruction.toString());
            } catch (PartiallyLegalInstructionException e) {
                disassembledCode.add(e.getMessage());
            }
        }

        return disassembledCode;
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
