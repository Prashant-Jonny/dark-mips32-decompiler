package io.github.gleipner.dark.mips32decomposer.instruction;

import io.github.gleipner.dark.mips32decomposer.instruction.parselet.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InstructionParser {
    public InstructionParser() {
        registerRType(0x00);
        registerRType(0x1c);
        registerIType(0x01);
        registerJType(0x02);

        /** We can for-loop over the remaining I-type instructions */
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

    private void registerRType(int opcode) {
        register(opcode, RTypeInstructionParselet::parse);
    }

    private static void registerIType(int opcode) {
        register(opcode, ITypeInstructionParselet::parse);
    }

    private static void registerJType(int opcode) {
        register(opcode, JTypeInstructionParselet::parse);
    }
}
