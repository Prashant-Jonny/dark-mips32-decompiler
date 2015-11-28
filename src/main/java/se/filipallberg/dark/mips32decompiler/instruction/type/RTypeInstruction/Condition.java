package se.filipallberg.dark.mips32decompiler.instruction.type.RTypeInstruction;

import java.util.*;
import java.util.function.Function;

public class Condition<T, R> {
    private final Set<List<SubCondition>> subConditions = new
            HashSet<>();
    private List<SubCondition> currentConditions = new ArrayList<>();
    private final Set<String> errors = new HashSet<>();

    public Condition<T, R> checkThat(Function<T, R> actual) {
        return checkThat("", actual);
    }

    public Condition<T, R> checkThat(String info, Function<T, R> actual) {
        currentConditions.add(new SubCondition(info, actual));
        return this;
    }

    public Condition<T, R> is(R expected) {
        currentConditions.forEach(e -> {
            e.expected = expected;
        });

        subConditions.add(new ArrayList<>(currentConditions));
        currentConditions.clear();

        return this;
    }

    public Condition<T, R> and(String info, Function<T, R> actual) {
        currentConditions.add(new SubCondition(info, actual));
        return this;
    }

    public Condition<T, R> andThat(Function<T, R> actual) {
        return andThat("", actual);
    }

    public Condition<T, R> andThat(String info, Function<T, R> actual) {
        currentConditions.add(new SubCondition(info, actual));
        return this;
    }

    public boolean evaluate(T arg) {
        /*
         * If any sub condition is false then the entire
         * expression is false.
         */

        /* Variable used inside lambda has to be effectively final */
        final int[] valid = {1};
        subConditions.forEach(e -> {
            e.forEach(j -> {
                if (!j.validate(arg)) {
                    valid[0] *= 0;
                    errors.add(j.errorInfo);
                }
            });
        });
        return 1 == valid[0];
    }

    public Collection<String> getErrors() {
        return errors;
    }

    private class SubCondition {
        Function<T, R> actual;
        R got;
        R expected;
        String errorInfo;

        public SubCondition(String errorInfo, Function<T, R> actual) {
            this.actual = actual;
            this.errorInfo = errorInfo;
        }

        public boolean validate(T t) {
            got = actual.apply(t);
            if (!got.equals(expected)) {
                errorInfo += ": Got: " + got + " Expected: " + expected;
                return false;
            }
            return true;
        }
    }
}
