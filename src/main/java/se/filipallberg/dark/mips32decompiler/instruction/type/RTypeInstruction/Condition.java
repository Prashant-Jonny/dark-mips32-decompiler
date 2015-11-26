package se.filipallberg.dark.mips32decompiler.instruction.type.RTypeInstruction;

import java.util.*;
import java.util.function.Function;

public class Condition<T, R> {
    private final Map<Function<T, R>, R> conditions = new
            HashMap<>();
    private Function<T, R> actual;

    public Condition<T, R> checkThat(Function<T, R> actual) {
        this.actual = actual;
        return this;
    }

    public Condition<T, R> is(R expected) {
        conditions.put(actual, expected);
        return this;
    }

    public Condition<T, R> andThat(Function<T, R> actual) {
        this.actual = actual;
        return this;
    }

    public boolean validate(T arg) {
        final int[] valid = {1};
        conditions.entrySet().forEach(e -> {
             if (!Objects.equals(e.getKey().apply(arg), e.getValue())) {
                 valid[0] *= 0;
             };
        });
        return 1 == valid[0];
    }
}
