package io.github.gleipner.dark.mips32decomposer.util;

public class BitField {
    public static int getNBits(int number, int start, int numberOfBits) {
        String s = asBitPattern(number);
        String requestedBits = s.substring(start, start + numberOfBits - 1);
        return Integer.parseInt(requestedBits, 2);
    }

    private static String asBitPattern(int number) {
        return Integer.toBinaryString(number);
    }
}
