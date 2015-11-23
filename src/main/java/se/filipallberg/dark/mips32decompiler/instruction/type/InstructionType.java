package se.filipallberg.dark.mips32decompiler.instruction.type;

import se.filipallberg.dark.mips32decompiler.instruction.InstructionName;
import se.filipallberg.dark.mips32decompiler.instruction.format.Format;

public interface InstructionType {
    Format getFormat();
    InstructionName getName(int instruction);

}
