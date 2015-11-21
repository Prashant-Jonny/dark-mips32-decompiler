package se.filipallberg.dark.mips32decompiler.instruction;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BitTest {
    @Test
    public void shouldRetrieveSixMostLeftBitsAppropriately() {
        int n = 0b1110001000000010100100000000010;
        int leftMostSixBits = Bit.getNBits(n, 0, 6);
        assertThat(leftMostSixBits, is(equalTo(0b11100)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionForStartAbove31() {
        Bit.getNBits(0, 33, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionForNegativeStartIndex() {
        Bit.getNBits(0, -1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionForOutOfRange() {
        Bit.getNBits(0, 28, 5);
    }
}
