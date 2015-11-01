package io.github.gleipner.dark.mips32decomposer.instruction;

import io.github.gleipner.dark.mips32decomposer.instruction.parselet.RTypeInstructionOpcodeSet;
import org.junit.Test;

import static io.github.gleipner.dark.mips32decomposer.instruction.Format.R;
import static io.github.gleipner.dark.mips32decomposer.instruction.TestInstructions.MUL_INSTRUCTION;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class OpCodeTest {
    OpCode mulInstructionOpCode = OpCode.fromInstruction(MUL_INSTRUCTION);

    @Test
    public void shouldHaveTheCorrectOpcodeForMulInstruction() {
        int opcode = OpCode.toInteger(MUL_INSTRUCTION);
        assertThat(opcode, is(equalTo(0x1c)));
    }

    @Test
    public void shouldHaveCorrectFormatForMulInstruction() {
        Format actual = mulInstructionOpCode.getFormat();
        assertThat(actual, is(equalTo(R)));
    }

    @Test
    public void formatInvariantShouldHold() {
        Format f1 = OpCode.getFormat(MUL_INSTRUCTION);
        Format f2 = mulInstructionOpCode.getFormat();
        assertThat(f1, is(equalTo(f2)));
    }

    @Test
    public void shouldHaveCorrectFormatForAllRTypeInstructions() {
        RTypeInstructionOpcodeSet.all().forEach(e -> {
            Format f = OpCode.fromNumericalRepresentation(e).getFormat();
            assertThat(f, is(equalTo(R)));
        });
    }
}
