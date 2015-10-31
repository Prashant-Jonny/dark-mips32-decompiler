package io.github.gleipner.dark.mips32decomposer.instruction;

public class InstructionParser extends Parser {
    public InstructionParser() {
        registerRType(0x00);
        registerRType(0x1c);
        registerIType(0x01);
        registerJType(0x02);

        /** We can for-loop over the remaining I-type instructions */
    }

    private void registerRType(int opcode) {
        map.put(opcode, new RTypeInstruction());
    }

    private void registerIType(int opcode) {
        map.put(opcode, new ITypeInstruction());
    }

    private void registerJType(int opcode) {
        map.put(opcode, new JTypeInstruction());
    }
}
