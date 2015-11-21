package se.filipallberg.dark.mips32decompiler.instruction;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DecomposedRepresentationTest {
    private final static DecomposedRepresentation d =
            DecomposedRepresentation.fromNumber(0x71014802, 6, 5, 5, 5, 5, 6);

    @Test
    public void shouldDecomposeJavadocExampleCorrectly() {
        int[] expected = {0x1c, 8, 1, 9, 0, 2};
        int[] actual = d.toIntArray();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldDecomposeJavadocHexExampleCorrectly() {
        assertThat(d.toHexadecimalString(), is(equalTo("[0x1c 8 1 9 0 2]")));
    }

    @Test
    public void shouldDecomposeJavadocDecimalExampleCorrectly() {
        assertThat(d.toDecimalString(), is(equalTo("[28 8 1 9 0 2]")));
    }
}