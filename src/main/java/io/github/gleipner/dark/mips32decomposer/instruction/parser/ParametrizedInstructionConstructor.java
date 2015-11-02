package io.github.gleipner.dark.mips32decomposer.instruction.parser;

import io.github.gleipner.dark.mips32decomposer.instruction.Instruction;
import io.github.gleipner.dark.mips32decomposer.instruction.InstructionName;
import io.github.gleipner.dark.mips32decomposer.mnemonic.Mnemonic;
import io.github.gleipner.dark.mips32decomposer.mnemonic.MnemonicYielder;
import io.github.gleipner.dark.mips32decomposer.util.DecomposedRepresentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Provides the means to create a parametrized routine for Instruction
 * construction.
 *
 * Instructions in the MIPS32 instruction set has a tendency to follow
 * a certain pattern, these patterns are dependent upon the format of
 * the particular instruction.
 *
 * For an example, the instructions
 *
 * <pre>
 * {@code add, addu, sub, mul, and, or, nor, xor, movn} and {@code movn}
 * </pre>
 *
 * among possibly others have the common pattern <i>iname rd, rs, rt</i> with
 * <i>iname</i> the instruction name.
 *
 * Other common patterns include <i>iname rd, rs</i>, <i>iname rd, rt, rs</i>,
 * <i>iname, rd, rt, shamt</i> for R-format instructions and
 * <i>iname, rt, rs, imm</i>, <i>iname rt, imm</i>, and
 * <i>iname, rt, rs(addr)</i> for I-format instructions.
 *
 * Specifically the pattern describes the mnemonic representation of the
 * instruction, which implies that the <i>decomposed representation</i> is
 * specified also. Ostensibly, the <i>decomposed representation</i> of an
 * instruction may be determined by its format, but the ostensibly qualifier
 * is there for a reason.
 *
 * To create an instruction using a pattern we must know the name of the
 * instruction. Hence the name must <i>always</i> be passed as a parameter.
 *
 * Additionally the length of the bit-fields the pattern decomposes into must
 * be specified, for an example the pattern <i>iname rd, rs, rt</i> corresponds
 * to a decomposition into bitfields of length (6, 5, 5, 5, 5, 6) where
 * the leftmost 6 bits is the opcode, register s, register t, register d,
 * shamt (shift amount) and the funct (function) field.
 *
 * The sum of the bit-fields must <b>always!</b> be 32. Lastly the order
 * in which to display these fields must be specified.
 */
public class ParametrizedInstructionConstructor {
    public static InstructionConstructor createPatternizedConstructor(InstructionName iname, int[] bitfieldLengths, int[] inOrderBitFieldIndexes) {
        return instruction -> new Instruction(instruction, iname) {
            DecomposedRepresentation decomposed =
                    DecomposedRepresentation.fromNumber(instruction, bitfieldLengths);

            /** {@inheritDoc} */
            public DecomposedRepresentation getDecomposedRepresentation() {
                return decomposed;
            }

            /** {@inheritDoc} */
            public Mnemonic toMnemonic() {
                /**
                 * Get the representation in its int array form so that
                 * we may retrieve the specific bitfields in the specified order.
                 */
                int[] decomposedAsArray = decomposed.toIntArray();

                /** The mnemonic constructor accepts an Iterable */
                List<MnemonicYielder> yielders = new ArrayList<>();
                Arrays.stream(inOrderBitFieldIndexes).forEach(i -> {
                    /*
                     * TODO: not all entries in the array are registers, although this works its confusing.
                     */
                    yielders.add(new Register(decomposedAsArray[i])::toMnemonic);
                });
                yielders.add(0, iname::toMnemonic);
                return new Mnemonic(yielders);
            }
        };
    }
}
