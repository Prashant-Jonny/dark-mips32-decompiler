package io.github.gleipner.dark.mips32decomposer.instruction.parser;

import io.github.gleipner.dark.mips32decomposer.instruction.Instruction;
import io.github.gleipner.dark.mips32decomposer.instruction
        .UnknownInstructionException;
import io.github.gleipner.dark.mips32decomposer.instruction.opcode.OpCode;
import io.github.gleipner.dark.mips32decomposer.instruction.opcode
        .RTypeInstructionOpcodeSet;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the top level parser for parsing the numerical representation
 * of a MIPS32 instruction and is capable of generating an instruction instance
 * that corresponds to said instruction.
 */
public class InstructionParser {
    /**
     * Parses the numerical representation of a MIPS32 instruction instance
     * and returns the corresponding {@link Instruction} instance.
     *
     * @param instruction the numerical representation of the instruction.
     * @return the corresponding {@link Instruction} instance.
     */
    public static Instruction parse(int instruction) {
        int op = OpCode.fromNumericalRepresentation(instruction).toInteger();
        InstructionParseFunction constructor = map.get(op);

        if (Objects.isNull(constructor)) {
            throw new UnknownInstructionException(op);
        }

        return constructor.apply(instruction);
    }

    /** Pairs integer opcodes with a corresponding parser function */
    private static Map<Integer, InstructionParseFunction> map = new HashMap<>();

    private static void register(int opcode, InstructionParseFunction constructor) {
        map.put(opcode, constructor);
    }

    static {
        /**
         * Pair all numerical representations of an opcode for an R-type
         * instruction with their associated parsing function.
         */
        RTypeInstructionOpcodeSet.all().forEach(e -> {
            register(e, RTypeInstructionParser::parse);
        });
    }
}
