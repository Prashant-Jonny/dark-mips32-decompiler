package io.github.gleipner.dark.mips32decomposer.instruction.parser;

import io.github.gleipner.dark.mips32decomposer.instruction.Instruction;
import io.github.gleipner.dark.mips32decomposer.instruction.UnknownInstructionException;
import io.github.gleipner.dark.mips32decomposer.instruction.opcode.OpCode;
import io.github.gleipner.dark.mips32decomposer.instruction.opcode.RTypeInstructionOpcodeSet;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InstructionParser {
    public InstructionParser() {
        RTypeInstructionOpcodeSet.all().forEach(e -> {
            register(e, RTypeInstructionParselet::parse);
        });
    }

    public Instruction parse(int instruction) {
        int op = OpCode.fromNumericalRepresentation(instruction).toInteger();
        InstructionParseFunction constructor = map.get(op);

        if (Objects.isNull(constructor)) {
            throw new UnknownInstructionException(op);
        }

        return constructor.apply(instruction);
    }

    protected static Map<Integer, InstructionParseFunction> map = new HashMap<>();

    public static void register(int opcode, InstructionParseFunction constructor) {
        map.put(opcode, constructor);
    }
}
