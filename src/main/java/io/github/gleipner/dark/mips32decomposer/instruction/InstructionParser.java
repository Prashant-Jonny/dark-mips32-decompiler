package io.github.gleipner.dark.mips32decomposer.instruction;

import io.github.gleipner.dark.mips32decomposer.instruction.parselet.ITypeInstructionParselet;
import io.github.gleipner.dark.mips32decomposer.instruction.parselet.JTypeInstructionParselet;
import io.github.gleipner.dark.mips32decomposer.instruction.parselet.RTypeInstructionParselet;

public class InstructionParser extends Parser {
    public InstructionParser() {
        registerRType(0x00);
        registerRType(0x1c);
        registerIType(0x01);
        registerJType(0x02);

        /** We can for-loop over the remaining I-type instructions */
    }

    private void registerRType(int opcode) {
        register(opcode, new RTypeInstructionParselet());
    }

    private void registerIType(int opcode) {
        register(opcode, new ITypeInstructionParselet());
    }

    private void registerJType(int opcode) {
        register(opcode, new JTypeInstructionParselet());
    }
}
