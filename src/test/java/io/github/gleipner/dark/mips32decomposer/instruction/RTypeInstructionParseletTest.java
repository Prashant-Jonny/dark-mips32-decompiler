package io.github.gleipner.dark.mips32decomposer.instruction;

import org.junit.Test;

import static io.github.gleipner.dark.mips32decomposer.instruction.TestInstructions.MUL_INSTRUCTION;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class RTypeInstructionParseletTest {
    private final InstructionParser parser = new InstructionParser();
    private final Instruction parsedInstruction = parser.parse(MUL_INSTRUCTION);

    @Test
    public void decomposingMulInstructionShouldHaveCorrectOpcode() {
        int expectedOpcode = 0x1c;
        int actualOpcode = parsedInstruction.getOpcode().toInteger();

        assertThat(actualOpcode, is(equalTo(expectedOpcode)));
    }

    @Test
    public void decomposingMulInstructionShouldHaveCorrectFormat() {
        assertThat(parsedInstruction.getFormat(), is(equalTo(Format.R)));
    }

    @Test
    public void decomposingMulInstructionShouldHaveCorrectMnemonic() {
        assertThat(parsedInstruction.getMnemonic().asString(), is(equalTo("mul")));
    }
}
