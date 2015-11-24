package se.filipallberg.dark.mips32decompiler.instruction.type;

import java.util.function.Function;

@FunctionalInterface
public interface BitField extends Function<int[], String> {

}
