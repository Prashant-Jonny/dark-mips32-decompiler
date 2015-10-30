package io.github.gleipner.dark.mips32decomposer;

/**
 * Interface describing a function with a certain return value of type R
 * accepting a single argument of type A.
 *
 * The function may throw an exception of type E.
 *
 * @param <R> the return type of the function
 * @param <A> the argument type of the functionc
 * @param <E> the exception type thrown by the function
 */
@FunctionalInterface
public interface ExceptionFunction<R, A, E extends Throwable> {
    R apply(A arg) throws E;
}