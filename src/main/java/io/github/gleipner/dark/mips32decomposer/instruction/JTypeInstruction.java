package io.github.gleipner.dark.mips32decomposer.instruction;

import java.io.IOException;
import java.io.InputStream;

public class JTypeInstruction extends Instruction {
    @Override
    public Format getFormat() {
        return Format.J;
    }

    public JTypeInstruction(InputStream inputStream) throws IOException {

    }
}
