# dark-mips32-decompiler [![Build Status](https://travis-ci.org/leksak/dark-mips32-decompiler.svg?branch=master)](https://travis-ci.org/leksak/dark-mips32-decompiler)

This is by far the worst code I have ever written!

Assignment 1 for [Computer Organization and Architecture}(http://www8.cs.umu.se/~hegner/Courses/TDBC06/H15/index.html).

# Compilation and usage

In this section the compilation of the software is described as
is the usage of the software

## Compilation

The software may be compiled from the root-directory of the project,
i.e. the top-level directory containing all the files pertaining to
this project.

### Prerequisites

You will require Gradle 2.9, which is available for
[download here](http://gradle.org/gradle-download/). 

You can inspect your version of Gradle by executing it with
the `-v` flag,

```bash
$ gradle -v
```

### Compiling with Gradle

Begin by navigating to the root directory of the project in a
terminal. When you have done so you may execute the following command,

```bash
$ gradle jar
```

This will create a `build` directory that has a sub-directory, `libs`.

```bash
$ tree
.
|-- ...
|-- libs
|   |-- MIPS32Decompiler.jar
|-- ...
```

In the `build/libs` directory you will now find a `jar` file,
`MIPS32Decompiler.jar`.

Confirm that your version of Java is Java 8 by running the command
`java -version`, if the version number is in the format `1.8.xxx` then
you may now run the software by executing the following command,

$ java -jar MIPS32Decompiler.jar

### Compiling and running tests

In the root directory of the project execute the following command to
both compile the software and run its associated test suite,

```bash
$ gradle test
```

The results of the tests will be placed in the
`build/reports/tests` directory.

To review the results of the tests open, in a browser, the
`index.html` in the aforementioned directory.

On the aforementioned page, `index.html`, you may navigate through
the different packages and view the results for specific tests.

## Usage

The program supplies two means of interfacing with it, by calling
the program with 0 arguments or the `-h` flag we can get the
user guide supplied with the software

```bash
$ java -jar build/libs/MIPS32Decompiler.jar -h
Usage: MIPS32Decompiler [OPTION] <number|file>...
OPTIONS:
    -n Specifies that input should be read from 
       from the command-line. The following 
       argument(s) may either be a number in hexadecimal form
       or decimal form. Hexadecimal numbers must be
       preceded by the 0x prefix.
    -h Shows this help message.
If no option is passed, the argument(s) passed is
assumed to be path to a filename
```

## Decompiling source code from files

The default setting is that the software interprets its given input
arguments as a filenames, relative to the directory from which you
execute the `.jar` file. In the root directory of the project three
example files are included.

`sample-program.txt` showcases the tabulated output for several
instructions, as well as how error messages for partially legal
instructions are written out.

`sample-decimal.txt` showcases that the program is capable
of handling instructions in base 10. The file ends with
a trailing blank line, which is not a problem for the software.

`sample-mingled.txt` demonstrates that the program does not expect
the file to be written consistently in either base 10 or base 16 but
evaluates this on a per-line basis. A number is interpreted
to be in base 16 if it is prefixed by `0x`. The two numbers
are separated by a blank-line which does not pose an issue.

We can inspect the contents of these files using `cat´
as shown below:

```bash
$ cat sample-decimal.txt 
599654392

$ cat sample-mingled.txt 
599654392

0xafbf0004
$ cat sample-program.txt 
0x23bdfff8
0xafbf0004
0xafa40000
0x28880001
0x11000003
0x20020001
0x23bd0008
0x03e00008
0x2084ffff
0x0c100000
0x8fa40000
0x8fbf0004
0x23bd0008
0x70821002
0x03e00008
0x00012122
```

Similarly we may observe the output of our decompiler where the files
are passed as a series of arguments.

```bash
$ java -jar build/libs/MIPS32Decompiler.jar \
sample-mingled.txt sample-decimal.txt sample-program.txt
Instruction     Fmt Decomposition   Decomp hex             Source            
0x23bdfff8      I  [8 29 29 65528] [8 0x1d 0x1d 0xfff8]   addi $sp, $sp, -8 
0xafbf0004      I  [43 29 31 4]    [0x2b 0x1d 0x1f 4]     sw $ra, 4($sp)    
Instruction     Fmt Decomposition   Decomp hex             Source            
0x23bdfff8      I  [8 29 29 65528] [8 0x1d 0x1d 0xfff8]   addi $sp, $sp, -8 
0xafbf0004      I  [43 29 31 4]    [0x2b 0x1d 0x1f 4]     sw $ra, 4($sp)    
0x23bdfff8      I  [8 29 29 65528] [8 0x1d 0x1d 0xfff8]   addi $sp, $sp, -8 
Instruction     Fmt Decomposition   Decomp hex             Source            
0x23bdfff8      I  [8 29 29 65528] [8 0x1d 0x1d 0xfff8]   addi $sp, $sp, -8 
0xafbf0004      I  [43 29 31 4]    [0x2b 0x1d 0x1f 4]     sw $ra, 4($sp)    
0x23bdfff8      I  [8 29 29 65528] [8 0x1d 0x1d 0xfff8]   addi $sp, $sp, -8 
0x23bdfff8      I  [8 29 29 65528] [8 0x1d 0x1d 0xfff8]   addi $sp, $sp, -8 
0xafbf0004      I  [43 29 31 4]    [0x2b 0x1d 0x1f 4]     sw $ra, 4($sp)    
0xafa40000      I  [43 29 4 0]     [0x2b 0x1d 4 0]        sw $a0, 0($sp)    
0x28880001      I  [10 4 8 1]      [0xa 4 8 1]            slti $t0, $a0, 1  
0x11000003      I  [4 8 0 3]       [4 8 0 3]              beq $t0, $zero, 3 
0x20020001      I  [8 0 2 1]       [8 0 2 1]              addi $v0, $zero, 1
0x23bd0008      I  [8 29 29 8]     [8 0x1d 0x1d 8]        addi $sp, $sp, 8  
0x03e00008      R  [0 31 0 0 0 8]  [0 0x1f 0 0 0 8]       jr $ra            
0x2084ffff      I  [8 4 4 65535]   [8 4 4 0xffff]         addi $a0, $a0, -1 
0x0c100000      J  [3 1048576]     [3 0x100000]           jal 0x100000      
0x8fa40000      I  [35 29 4 0]     [0x23 0x1d 4 0]        lw $a0, 0($sp)    
0x8fbf0004      I  [35 29 31 4]    [0x23 0x1d 0x1f 4]     lw $ra, 4($sp)    
0x23bd0008      I  [8 29 29 8]     [8 0x1d 0x1d 8]        addi $sp, $sp, 8  
0x70821002      R  [28 4 2 2 0 2]  [0x1c 4 2 2 0 2]       mul $v0, $a0, $v0 
0x03e00008      R  [0 31 0 0 0 8]  [0 0x1f 0 0 0 8]       jr $ra            
0x00012122      R  [0 0 1 4 4 34]  [0 0 1 4 4 0x22]       sub $a0, $zero, $at
  Errors: shamt: Got: 4 Expected: 0
```

The table headers, 

```bash
Instruction     Fmt Decomposition   Decomp hex             Source
```

are written out before each file decompilation, refer to the previous
call where multiple files were supplied to see that this is true.

## Decompiling input numbers

Additionally, the software provides a secondary means of use, through
the `-n` flag which stands for *number* so that the arguments
immediately following the invocation of the program are interpreted
as if they were contents in a file. Notice below that it does not matter
whether or not the number is in base 10 or base 16 (as long as numbers in
base 16 are prefixed by `0x`).

```bash
$ java -jar build/libs/MIPS32Decompiler.jar -n \
> 599654392 0xafbf0004
Instruction     Fmt Decomposition   Decomp hex             Source            
0x23bdfff8      I  [8 29 29 65528] [8 0x1d 0x1d 0xfff8]   addi $sp, $sp, -8 
0xafbf0004      I  [43 29 31 4]    [0x2b 0x1d 0x1f 4]     sw $ra, 4($sp)    
```

Lastly, note that the software handles partially valid instructions,
i.e. instructions that may be correctly identified but validates some
condition. For instance, the number `0x00012122` corresponds to the
`sub` instruction. The number decomposes into bitfields according to
the format of the instruction (viz. the R-format), and the opcode and
funct field hold the correct values to identify the instruction as a
`sub` instruction *but* the `shamt` field is not set to 4, which it
has to be according to the instruction specification.

When an instruction is partially legal the violating fields are outputted
on the following line, see below:

```bash
$ java -jar build/libs/MIPS32Decompiler.jar -n 0x00012122
Instruction     Fmt Decomposition   Decomp hex             Source            
0x00012122      R  [0 0 1 4 4 34]  [0 0 1 4 4 0x22]       sub $a0, $zero, $at
  Errors: shamt: Got: 4 Expected: 0
```

If there are multiple conditions that are violated, they are all
specified in the error column, 

```bash
java -jar build/libs/MIPS32Decompiler.jar -n 34603008
Instruction     Fmt Decomposition   Decomp hex          Source
0x02100000      R  [0 16 16 0 0 0] [0 0x10 0x10 0 0 0]    nop
  Errors: rt: Got: 16 Expected: 0 rs: Got: 16 Expected: 0
```

