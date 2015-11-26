package se.filipallberg.dark.mips32decompiler.instruction;

import org.junit.Test;
import se.filipallberg.dark.mips32decompiler.instruction.type.RTypeInstruction.RTypeInstruction;

import static junit.framework.TestCase.fail;

public class ValidationTest {
    @Test
    public void mulShouldValidate() {
        Instruction r = RTypeInstruction
                .fromNumericalRepresentation(0x71014802);
    }
}
