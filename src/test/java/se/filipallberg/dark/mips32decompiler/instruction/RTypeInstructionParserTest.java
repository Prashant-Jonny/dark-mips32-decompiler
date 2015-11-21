package se.filipallberg.dark.mips32decompiler.instruction;

import org.junit.Test;
import se.filipallberg.dark.mips32decompiler.instruction.opcode.Format;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static se.filipallberg.dark.mips32decompiler.instruction.InstructionName.*;

public class RTypeInstructionParserTest {
    @Test
    public void mulTest() {
        int mulInstruction = 0x71014802;
        Instruction instruction = RTypeInstructionParser.parse(mulInstruction);

        /* Check that the format is okay */
        assertThat(instruction.getFormat(), is(equalTo(Format.R)));

        /* Check that the name matches */
        assertThat(instruction.getInstructionName(), is(equalTo(MUL)));

        /* Check that the numerical representation is intact */
        assertThat(instruction.toNumericalRepresentation(), is(equalTo
                (mulInstruction)));

        /* Check that the representation is correct */
        MnemonicRepresentation expected = MnemonicRepresentation.fromString("mul " +
                "$t1, $t0, $at");
        assertThat(instruction.getMnemonicRepresentation(), is(equalTo
                (expected)));
    }

    @Test
    public void subTest() {
        int subInstruction = 0x00012122;

        Instruction instruction = RTypeInstructionParser.parse(subInstruction);

        /* Check that the format is okay */
        assertThat(instruction.getFormat(), is(equalTo(Format.R)));

        /* Check that the name matches */
        assertThat(instruction.getInstructionName(), is(equalTo(SUB)));

        /* Check that the numerical representation is intact */
        assertThat(instruction.toNumericalRepresentation(), is(equalTo
                (subInstruction)));

        /* Check that the actual parameters are in the correct order */
        MnemonicRepresentation expected = MnemonicRepresentation
                .fromString("sub $a0, $zero, $at");
        assertThat(instruction.getMnemonicRepresentation(), is(equalTo(expected)));
    }

}
