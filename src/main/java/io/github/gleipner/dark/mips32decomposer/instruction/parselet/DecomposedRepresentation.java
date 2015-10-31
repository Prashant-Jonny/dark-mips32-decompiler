package io.github.gleipner.dark.mips32decomposer.instruction.parselet;

import java.util.Arrays;


public final class DecomposedRepresentation {
    private final int[] decomposition;

    private DecomposedRepresentation(int[] decomposition) {
        this.decomposition = decomposition;
    }

    /**
     * Decomposes the given number into chunks that have the specified lengths.
     *
     * For an example: The number 0x71014802 when decomposed into chunks
     * of lengths (6,5,5,5,5,6) yields the representation [0x1c, 8, 1, 9, 0, 2]
     *
     * @param number the numerical representation
     * @param lengths the length of each chunk if the number was in base 2.
     * @return the decomposed representation of the number.
     */
    public static DecomposedRepresentation fromNumber(int number, int... lengths) {
        assert(Arrays.stream(lengths).sum() == 32);

        int[] decomposition = new int[lengths.length];
        int start = 0;
        for (int i = 0; i < lengths.length; i++) {
            decomposition[i] = getNBits(number, start, lengths[i]);
            start += lengths[i];
        }

        return new DecomposedRepresentation(decomposition);
    }

    public int[] toIntArray() {
        return decomposition;
    }

    private static int getNBits(int number, int start, int numberOfBits) {
        String s = asBitPattern(number);
        String requestedBits = s.substring(start, start + numberOfBits - 1);
        return Integer.parseInt(requestedBits, 2);
    }

    private static String asBitPattern(int number) {
        return Integer.toBinaryString(number);
    }
}
