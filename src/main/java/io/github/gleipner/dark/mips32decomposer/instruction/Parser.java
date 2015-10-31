package io.github.gleipner.dark.mips32decomposer.instruction;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Parser {
    protected static Map<Integer, Parselet> map = new HashMap<>();

    public final void register(int opcode, Parselet parselet) {
        map.put(opcode, parselet);
    }

    public Instruction parse(int instruction) {
        OpCode op = OpCode.fromNumericalRepresentation(instruction);
        Parselet parselet = map.get(op.toInteger());

        if (Objects.isNull(parselet)) {
            throw new UnknownInstructionException(op.toInteger());
        }

        return parselet.parse(instruction);
    }
}
