package se.filipallberg.dark.mips32decompiler.instruction;

import org.junit.Test;
import se.filipallberg.dark.mips32decompiler.instruction.type.ITypeInstruction.ITypeInstruction;

import static org.junit.Assert.assertTrue;

public class ITypeInstructionValidationTest {
    @Test (expected = PartiallyLegalInstructionException.class)
    public void luiShouldNotValidateIfRsIsNot0x00() {
        ITypeInstruction instruction = ITypeInstruction.LUI;
        instruction.rs = 1;
        instruction.validate();
    }

    @Test
    public void luiIsValidIfRsIs0x00() {
        ITypeInstruction instruction = ITypeInstruction.LUI;
        instruction.rs = 0x00;
        assertTrue(instruction.validate());
    }

}