package se.filipallberg.dark.mips32decompiler.instruction;

import org.junit.Test;

public class RTypeInstructionParserTest {
    @Test
    public void mulTest() {
        Instruction instruction = RTypeInstructionParser.parse(0x71014802);
        System.out.println(instruction);
    }
}
