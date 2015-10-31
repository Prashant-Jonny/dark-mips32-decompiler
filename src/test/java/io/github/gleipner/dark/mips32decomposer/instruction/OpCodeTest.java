package io.github.gleipner.dark.mips32decomposer.instruction;

import org.junit.Test;

import static io.github.gleipner.dark.mips32decomposer.instruction.TestInstructions.MUL_INSTRUCTION;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class OpCodeTest {
    @Test
    public void shouldHaveTheCorrectOpcodeForMulInstruction() {
        int instruction = MUL_INSTRUCTION;
        int opcode = OpCode.toInteger(instruction);
        assertThat(opcode, is(equalTo(0x1c)));
    }
}
