package io.github.gleipner.dark.mips32decomposer.instruction;

import io.github.gleipner.dark.mips32decomposer.instruction.parser
        .InstructionParser;
import io.github.gleipner.dark.mips32decomposer.mnemonic.Mnemonic;
import org.junit.Test;

import static io.github.gleipner.dark.mips32decomposer.instruction.InstructionName.MUL;
import static io.github.gleipner.dark.mips32decomposer.instruction.TestInstructions.MUL_INSTRUCTION;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class RTypeInstructionParseletTest {
    private final InstructionParser parser = new InstructionParser();
    private final Instruction parsedInstruction = parser.parse(MUL_INSTRUCTION);

    @Test
    public void decomposingMulInstructionShouldHaveCorrectOpcode() {
        int expectedOpcode = 0x00;
        int actualOpcode = parsedInstruction.getOpcode().toInteger();

        assertThat(actualOpcode, is(equalTo(expectedOpcode)));
    }

    @Test
    public void decomposingMulInstructionShouldHaveCorrectFormat() {
        assertThat(parsedInstruction.getFormat(), is(equalTo(Format.R)));
    }

    @Test
    public void decomposingMulInstructionShouldHaveCorrectName() {
        assertThat(parsedInstruction.getName(), is(equalTo(MUL)));
    }

    @Test
    public void decomposingMulInstructionShouldHaveCorrectMnemonicRepresentation() {
        Mnemonic m = new Mnemonic("mul $t1 $t0 $at");
        assertThat(parsedInstruction.toMnemonic(), is(equalTo(m)));
    }
}
