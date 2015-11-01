package io.github.gleipner.dark.mips32decomposer.instruction;

import io.github.gleipner.dark.mips32decomposer.util.DecomposedRepresentation;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

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
