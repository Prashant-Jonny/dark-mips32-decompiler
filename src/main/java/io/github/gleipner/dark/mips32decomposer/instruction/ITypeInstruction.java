package io.github.gleipner.dark.mips32decomposer.instruction;

import java.io.IOException;
import java.io.InputStream;

public class ITypeInstruction extends Instruction {
    @Override
    public Format getFormat() {
        return Format.I;
    }

    public ITypeInstruction(InputStream inputStream) throws IOException {

    }
}
