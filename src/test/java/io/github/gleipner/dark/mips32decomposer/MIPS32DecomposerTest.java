package io.github.gleipner.dark.mips32decomposer;

import io.github.gleipner.dark.mips32decomposer.instruction.Instruction;
import io.github.gleipner.dark.mips32decomposer.instruction.parser.InstructionParser;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static io.github.gleipner.dark.mips32decomposer.MIPS32Decomposer.getNumberFromString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MIPS32DecomposerTest {
    @Test
    public void shouldHandleHexadecimalStrings() {
        assertThat(0x71014802, is(equalTo(getNumberFromString("0x71014802"))));
    }

    @Test
    public void shouldHandleDecimalStrings() {
        assertThat(1895909378, is(equalTo(getNumberFromString("1895909378"))));
    }

    @Test
    public void shouldYieldSingleCorrectInstructionFromStream() throws IOException {
        int instruction = 1895909378;

        InputStream is = new ByteArrayInputStream(
                Integer.toString(instruction).getBytes());
        Instruction expected = InstructionParser.parse(instruction);
        Instruction actual = MIPS32Decomposer.parse(is).next();
        assertThat(actual, is(equalTo(expected)));
    }
}
