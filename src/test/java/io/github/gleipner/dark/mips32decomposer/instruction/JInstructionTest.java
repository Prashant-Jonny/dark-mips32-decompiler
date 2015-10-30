package io.github.gleipner.dark.mips32decomposer.instruction;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JInstructionTest {
    @Test
    public void hasCorrectFormat() throws IOException {
        InputStream is = new ByteArrayInputStream(new byte[] {OpCode.J.value});
        Instruction instruction = Instruction.fromInputStream(is);
        assertThat(instruction.getFormat(), is(equalTo(Format.J)));
    }
}
