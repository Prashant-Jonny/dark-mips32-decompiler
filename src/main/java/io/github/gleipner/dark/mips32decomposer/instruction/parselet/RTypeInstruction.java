package io.github.gleipner.dark.mips32decomposer.instruction.parselet;

public enum RTypeInstruction {
    MUL(0x02);


    private final int identifier;

    RTypeInstruction(int identifier) {
        this.identifier = identifier;
    }
}
