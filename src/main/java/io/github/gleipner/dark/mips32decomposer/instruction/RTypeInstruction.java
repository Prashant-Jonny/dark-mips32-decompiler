package io.github.gleipner.dark.mips32decomposer.instruction;

import java.io.IOException;
import java.io.InputStream;

public class RTypeInstruction extends Instruction {
    @Override
    public Format getFormat() {
        return Format.R;
    }

    public RTypeInstruction(InputStream inputStream) throws IOException {

    }
}
