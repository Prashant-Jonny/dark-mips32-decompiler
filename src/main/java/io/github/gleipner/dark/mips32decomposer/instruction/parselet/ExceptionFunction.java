package io.github.gleipner.dark.mips32decomposer.instruction.parselet;

/**
 * Interface describing a function with a certain return value of type R
 * accepting a single argument of type A.
 *
 * @param <R> the return type of the function
 * @param <A> the argument type of the functionc
 */
@FunctionalInterface
public interface ExceptionFunction<R, A> {
    R apply(A arg);
}

