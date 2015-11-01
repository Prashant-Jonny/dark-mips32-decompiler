package io.github.gleipner.dark.mips32decomposer.util;


public class BitField {
    /**
     * Gets the numeric value of yanking {@code numberOfBits} bits
     * (starting at index {@code start} with 0 being the start
     * of the string) from {@code number}.
     *
     * @param number the number to withdraw bits from.
     * @param start the starting bit index from which to retrieve bits.
     * @param numberOfBits the amount of bits to retrieve.
     * @return a numeric representation of the retrieved bits.
     */
    public static int getNBits(int number, int start, int numberOfBits) {
        String s = asBitPattern(number);
        String requestedBits = s.substring(start, start + numberOfBits - 1);
        return Integer.parseInt(requestedBits, 2);
    }

    private static String asBitPattern(int number) {
        return Integer.toBinaryString(number);
    }
}
