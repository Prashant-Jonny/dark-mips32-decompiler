package io.github.gleipner.dark.mips32decomposer.instruction;

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
