package se.filipallberg.dark.mips32decompiler;

public class Instruction {
    private final int instruction;
    private final Format format;
    private final InstructionName iname;
    private final DecomposedRepresentation decomposedRepresentation;
    private final MnemonicRepresentation mnemonicRepresentation;

    public Instruction(int instruction) {
        this.instruction = instruction;

        /* Get the opcode of this instruction */
        Opcode op = Opcode.fromInstruction(instruction);

        /* Get the format from the opcode */
        format = Format.fromOpcode(op);

        /* Get the name of this instruction */
        iname = Format.getName(op, instruction);

        decomposedRepresentation = Format.getDecomposedRepresentation
                (format, instruction);

        MnemonicPattern rule = iname.getMnemonicPattern();

        mnemonicRepresentation = MnemonicPattern
                .getMnemonicRepresentation(iname, rule, decomposedRepresentation);
    }
}
