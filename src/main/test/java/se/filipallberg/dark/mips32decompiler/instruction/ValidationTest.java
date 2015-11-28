package se.filipallberg.dark.mips32decompiler.instruction;

import org.junit.Test;
import se.filipallberg.dark.mips32decompiler.instruction.type.RTypeInstruction.RTypeInstruction;

public class ValidationTest {
    @Test (expected = PartiallyLegalInstructionException.class)
    public void mulShouldNotValidateIfShamtIsNonZero() {
        /*
         * This is originally a mul instruction, 0x71014802 which in binary
         * is represented as
         *
         * 0b1110001000000010100100000000010
         *
         * The mul instruction is invalid if its shamt field is anything
         * other than zero, with the shamt field being the 5 bits preceding
         * the right-most 6 bits.
         *
         * Here we have a 1 in the shamt field.
         */
        int partiallyValidMulInstruction =
                0b1110001000000010100100001000010;

        Instruction r = RTypeInstruction
                .fromNumericalRepresentation(partiallyValidMulInstruction);

    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void subShouldNotValidateIfShamtIsNonZero() {
        RTypeInstruction r = RTypeInstruction.SUB;
        r.shamt = 0x01;
        r.validate();
    }
}
