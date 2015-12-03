#!/bin/bash


include_file () {
    FILE_NAME=$(basename "$0")
    FILE_NAME=${FILE_NAME%%.*}
    INCLUDE_STRING="
    \\begin{landscape}
    \\section{\texttt{$FILE_NAME}}\label{code:$FILE_NAME}
    \\begin{multicols}{2} % <-- Intended for longer programs
    \\inputminted[mathescape,
        linenos,
        numbersep=5pt,
        frame=none,
        framesep=2mm,
        fontsize=\\footnotesize]{java}{"$0"}
    \\end{multicols}
    \\end{landscape}
    "
    echo "$INCLUDE_STRING"
}

# Export the include_file() function.
export -f include_file

find ../src -name "*.java" | awk '{print system("/usr/bin/env bash -c include_file "$0"")}' > javalistings.tex


