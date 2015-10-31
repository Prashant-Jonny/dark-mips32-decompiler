package io.github.gleipner.dark.mips32decomposer.instruction;

import io.github.gleipner.dark.mips32decomposer.instruction.parselet.Decomposer;
import org.junit.Test;

import static io.github.gleipner.dark.mips32decomposer.instruction.TestInstructions.MUL_INSTRUCTION;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class DecomposerTest {
    @Test
    public void shouldDecomposeMulInstructionCorrectly() {
        int[] expected = {0x1c, 8, 1, 9, 0, 2};
        int[] actual = Decomposer.decompose(MUL_INSTRUCTION, 6, 5, 5, 5, 5, 6);

        assertThat(actual, is(equalTo(expected)));
    }
}
