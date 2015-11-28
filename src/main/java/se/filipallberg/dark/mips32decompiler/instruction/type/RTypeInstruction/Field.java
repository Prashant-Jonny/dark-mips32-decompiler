package se.filipallberg.dark.mips32decompiler.instruction.type.RTypeInstruction;

import se.filipallberg.dark.mips32decompiler.instruction.type.InstructionType;

public interface Field<E extends Field, T extends InstructionType> {
    E ofTypedInstruction(T i);
    E of(int instruction);
}
