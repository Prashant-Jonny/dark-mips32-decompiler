package se.filipallberg.dark.mips32decompiler.instruction.util;

import se.filipallberg.util.Bit;

import java.util.Arrays;
import java.util.StringJoiner;

public class DecomposedRepresentation {
    private final int[] decomposition;

    private DecomposedRepresentation(int[] decomposition) {
        this.decomposition = decomposition;
    }

    /**
     * Decomposes the given number into chunks that have the specified lengths.
     * The sum of the supplied lengths must be equal to 32.
     *
     * For an example: The number 0x71014802 when decomposed into chunks
     * of lengths (6,5,5,5,5,6) yields the representation [0x1c, 8, 1, 9, 0, 2]
     *
     * To get the aforementioned decomposition call the method like so:
     * <pre>{@code fromNumber(0x71014802, 6, 5, 5, 5, 5, 6)</pre>
     *
     * @param number the numerical representation of the number to decompose.
     * @param lengths the length of each chunk if {@code number} was in base 2.
     * @return the decomposed representation of {@code number} into chunks
     * where each chunk matches (in order) the supplied lengths.
     * @throws InvalidParameterException if the sum of {@code lengths} is not 32.
     */
    public static DecomposedRepresentation fromNumber(int number, int... lengths) {
        if (Arrays.stream(lengths).sum() != 32) {
            String err = "Expected the sum of \"lengths\" to be 32. Got: ";
            err += Arrays.stream(lengths).sum();
            throw new IllegalArgumentException(err);
        }
        assert(Arrays.stream(lengths).sum() == 32);

        int[] decomposition = new int[lengths.length];
        int start = 0;
        for (int i = 0; i < lengths.length; i++) {
            decomposition[i] = Bit.getNBits(number, start, lengths[i]);
            start += lengths[i];
        }

        return new DecomposedRepresentation(decomposition);
    }

    /**
     * Get the decomposed representation as an int array.
     *
     * For an example, decomposing the number 0x71014802 into (6, 5, 5
     * For an example, the decomposition
     *
     * <pre>{@code d = fromNumber(0x71014802, 6, 5, 5, 5, 5, 6)}</pre>
     *
     * satisfies
     *
     * <pre>{@code Arrays.equals(d, new int[] {0x1c, 8, 1, 9, 0, 2})</pre>
     */
    public int[] toIntArray() {
        return decomposition;
    }

    /**
     * Returns a string representation where each chunk is represented in
     * its hexadecimal form.
     *
     * For an example, the decomposition
     *
     * <pre>{@code d = fromNumber(0x71014802, 6, 5, 5, 5, 5, 6)}</pre>
     *
     * is represented as the string "[0x1c 8 1 9 0 2]".
     *
     * @return the composition represented as a string where each field is
     * in hexadecimal form.
     */
    public String asHexadecimalString() {
        StringJoiner sj = new StringJoiner(" ", "[", "]");
        Arrays.stream(decomposition).forEach(e -> {
            String prefix = "";
            if (e > 9) {
                prefix = "0x";
            }

            sj.add(prefix + Integer.toHexString(e));
        });
        return sj.toString();
    }

    /**
     * Returns a string representation where each chunk is represented in
     * its decimal form.
     *
     * For an example, the decomposition
     *
     * <pre>{@code d = fromNumber(0x71014802, 6, 5, 5, 5, 5, 6)}</pre>
     *
     * is represented as the string "[28 8 1 9 0 2]".
     *
     * @return the composition represented as a string where each field is
     * in decimal form.
     */
    public String asDecimalString() {
        StringJoiner sj = new StringJoiner(" ", "[", "]");
        Arrays.stream(decomposition).forEach(e -> sj.add(Integer.toString(e)));
        return sj.toString();
    }

    public int opcode() { return decomposition[0]; }
}