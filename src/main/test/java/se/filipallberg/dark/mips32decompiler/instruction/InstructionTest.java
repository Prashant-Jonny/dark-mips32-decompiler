package se.filipallberg.dark.mips32decompiler.instruction;

import org.junit.Test;
import se.filipallberg.dark.mips32decompiler.instruction.type.Instruction;
import se.filipallberg.dark.mips32decompiler.instruction.util.Format;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InstructionTest {
    private static final Set<TestCase> testData = new HashSet<TestCase>
            () {{
        add(new TestCase(0x71014802, Format.R,
                "[28 8 1 9 0 2]", "[0x1c 8 1 9 0 2]",
                "mul $t1, $t0, $at"));
        add(new TestCase(0x23bdfff8, Format.I,
                "[8 29 29 65528]", "[8 0x1d 0x1d 0xfff8]",
                "addi $sp, $sp, -8"));
        add(new TestCase(0xafbf0004, Format.I,
                "[43 29 31 4]", "[0x2b 0x1d 0x1f 4]",
                "sw $ra, 4($sp)"));

    }};

    @Test
    public void testAllInstructions() {
        testData.forEach(InstructionTest::testDecompilation);
    }

    private static void testDecompilation(TestCase testCase) {
        testDecompilation(testCase.numericalRepresentation,
                testCase.format,
                testCase.decimal,
                testCase.hex,
                testCase.mnemonic);
    }

    private static void testDecompilation(int numericalRepresentation,
                                   Format format,
                                   String decimal,
                                   String hex,
                                   String mnemonic) {
        Instruction instruction = Instruction.fromInteger(numericalRepresentation);

        /* Check that the format is okay */
        assertThat(instruction.getFormat(), is(equalTo(format)));

        /* Check that the numerical representation is intact */
        assertThat(instruction.toNumericalRepresentation(), is(equalTo
                (numericalRepresentation)));

        /* Check that the representations are correct */
        assertThat(instruction.asDecimalString(), is(equalTo(decimal)));
        assertThat(instruction.asHexadecimalString(), is(equalTo(hex)));
        assertThat(instruction.mnemonic(), is(equalTo(mnemonic)));
    }

    private static class TestCase {
        public final int numericalRepresentation;
        public final Format format;
        public final String mnemonic;
        public final String decimal;
        public final String hex;

        TestCase(int numericalRepresentation,
                 Format format,
                 String decimal,
                 String hex,
                 String mnemonic) {
            this.numericalRepresentation = numericalRepresentation;
            this.format = format;
            this.decimal = decimal;
            this.hex = hex;
            this.mnemonic = mnemonic;
        }
    }
}
