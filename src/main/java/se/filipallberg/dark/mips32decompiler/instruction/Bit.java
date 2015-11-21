package se.filipallberg.dark.mips32decompiler.instruction;


/**
 * A utility class for yanking a sequence of bits from an integer and
 * retrieving the value of that bit sequence.
 */
public class Bit {
    /**
     * Given a 32-bit {@code number} and an index, {@code start}, specifying at what
     * bit position in the 32-bit {@code number}, to yank {@code numberOfBits}
     * bits from the supplied {@code number}. {@code start = 0} starts yanking
     * bits from the 32nd bit. Valid ranges of {@code start} ranges from
     * 0-31.
     *
     * For instance, consider the number
     *
     * n = 0b1110001000000010100100000000010 (= 0x71014802)
     *
     * Then, retrieving the leftmost six bits may be done by calling,
     *
     * leftMostSixBits = getNBits(n, 0, 6) => leftMostSixBits = 28 = 0x1c
     *
     * @param number the number to yank bits from.
     * @param start the starting bit index from which to retrieve bits.
     * @param numberOfBits the amount of bits to retrieve.
     * @return a numeric representation of the retrieved bits.
     * @throws IllegalArgumentException if
     * {@code start} does not satisfy {@code 0 <= start <= 31} or if
     * {@code start + numberOfBits > 32}.
     */
    public static int getNBits(int number, int start, int numberOfBits) {
        if (start > 31 || start < 0) {
            throw new IllegalArgumentException(start > 31 ?
                    "The supplied index \"start\" must satisfy start <= 31" :
                    "The supplied index \\\"start\\\" must satisfy start >= 0");
        }
        if (start + numberOfBits > 32) {
            throw new IllegalArgumentException(
                    "The argument pair \"start\" and \"numberOfBits\" must " +
                            "satisfy the condition (start + numberOfBits <= 32). " +
                            "Got start: " + start + " numberOfBits: " + numberOfBits);
        }

        String s = asBitPattern(number);
        String requestedBits = s.substring(start, start + numberOfBits);
        return Integer.parseInt(requestedBits, 2);
    }

    public static String asBitPattern(int number) {
        String binaryString = Integer.toBinaryString(number);
        int length = binaryString.length();
        String pad = "";
        if (length <= 32) {
            pad = new String(new char[32-length]).replace("\0",
                    "0");
        }
        return pad + binaryString;
    }
}