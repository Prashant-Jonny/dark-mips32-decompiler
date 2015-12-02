package se.filipallberg.dark.mips32decompiler.instruction;

import com.google.common.collect.Iterables;
import org.junit.Test;
import se.filipallberg.dark.mips32decompiler.MIPS32Decompiler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class MIPS32DecompilerTest {
    @Test
    public void testFactorial() throws IOException {
        String expected = String.join("\n",
        "0x23bdfff8 I [8 29 29 65528] [8 0x1d 0x1d 0xfff8] addi $sp, $sp, -8",
        "0xafbf0004 I [43 29 31 4] [0x2b 0x1d 0x1f 4] sw $ra, 4($sp)",
        "0xafa40000 I [43 29 4 0] [0x2b 0x1d 4 0] sw $a0, 0($sp)",
        "0x28880001 I [10 4 8 1] [0xa 4 8 1] slti $t0, $a0, 1",
        "0x11000003 I [4 8 0 3] [4 8 0 3] beq $t0, $zero, 3",
        "0x20020001 I [8 0 2 1] [8 0 2 1] addi $v0, $zero, 1",
        "0x23bd0008 I [8 29 29 8] [8 0x1d 0x1d 8] addi $sp, $sp, 8",
        "0x03e00008 R [0 31 0 0 0 8] [0 0x1f 0 0 0 8] jr $ra",
        "0x2084ffff I [8 4 4 65535] [8 4 4 0xffff] addi $a0, $a0, -1",
        "0x0c100000 J [3 1048576] [3 0x100000] jal 0x100000",
        "0x8fa40000 I [35 29 4 0] [0x23 0x1d 4 0] lw $a0, 0($sp)",
        "0x8fbf0004 I [35 29 31 4] [0x23 0x1d 0x1f 4] lw $ra, 4($sp)",
        "0x23bd0008 I [8 29 29 8] [8 0x1d 0x1d 8] addi $sp, $sp, 8",
        "0x70821002 R [28 4 2 2 0 2] [0x1c 4 2 2 0 2] mul $v0, $a0, $v0",
        "0x03e00008 R [0 31 0 0 0 8] [0 0x1f 0 0 0 8] jr $ra");

        /* MIPS assembler code for calculating a factorial */
        String input = String.join("\n",
                "0x23bdfff8",
                "0xafbf0004",
                "0xafa40000",
                "0x28880001",
                "0x11000003",
                "0x20020001",
                "0x23bd0008",
                "0x03e00008",
                "0x2084ffff",
                "0x0c100000",
                "0x8fa40000",
                "0x8fbf0004",
                "0x23bd0008",
                "0x70821002",
                "0x03e00008");
        InputStream inputStream = new ByteArrayInputStream(input.getBytes
                (UTF_8));
        Iterable<String> it = MIPS32Decompiler.parse(inputStream);
        String actual = String.join("\n", it);
        assertThat(actual, is(equalTo(expected)));
    }
}
