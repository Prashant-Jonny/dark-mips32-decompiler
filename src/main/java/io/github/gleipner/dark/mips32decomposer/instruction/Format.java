package io.github.gleipner.dark.mips32decomposer.instruction;

/**
 * Represents the formats for some of the different types of instructions that
 * make up the MIPS32 instruction set.
 */
public enum Format {
    R {
        @Override
        public String toString() {
            return "R-format";
        }
    },
    I {
        @Override
        public String toString() {
            return "I-format";
        }
    },
    J {
        @Override
        public String toString() {
            return "J-format";
        }
    }
}
