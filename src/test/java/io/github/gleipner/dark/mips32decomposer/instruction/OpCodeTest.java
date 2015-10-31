package io.github.gleipner.dark.mips32decomposer.instruction;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class OpCodeTest {
    @Test
    public void shouldHaveTheCorrectOpcodeForMulInstruction() {
        int instruction = 0x71014802;
        int opcode = OpCode.fromNumericalRepresentation(instruction).toInteger();
        assertThat(opcode, is(equalTo(0x1c)));
    }
}
