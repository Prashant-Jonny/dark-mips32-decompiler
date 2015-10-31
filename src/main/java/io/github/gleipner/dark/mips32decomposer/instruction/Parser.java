package io.github.gleipner.dark.mips32decomposer.instruction;

import io.github.gleipner.dark.mips32decomposer.instruction.parselet.Parselet;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Parser {
    protected static Map<Integer, Parselet> map = new HashMap<>();

    public final void register(int opcode, Parselet parselet) {
        map.put(opcode, parselet);
    }
}
