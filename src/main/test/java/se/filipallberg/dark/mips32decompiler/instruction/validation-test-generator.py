#!/usr/bin/env python3
#from types import SimpleNamespace as Record
import types # Get access to SimpleNamespace
import typing # Enable type hinting and type synonyms
import os # Get the current directory

# Create a type synonym, letting us refer to
# SimpleNamespace as Record. This could be
# accomplished by
#
# from types import SimpleNamespace as Record
#
# but since we are already using the typing
# module we might as well do this
Record = types.SimpleNamespace

# Want to be able to open a file and get back a list of all enum declarations
# this means matching the [A-Z]\( pattern. Either the next match will mark
# the completion of an enum or an empty ; on a single line marks the end
# of all the declarations

class InstructionTest:
    def __init__(self, name):
        self.name = name
        self.fields = []

    def add_field(self, field):
        self.fields.append(field)

def get_instruction_name(enum: str) -> str:
    """
    Expects that the name of the enum starts at the first
    character of the supplied string. Will return the name
    of that particular enum.

    >>> get_instruction_name("ADDU(0, 0x21,")
    'ADDU'

    Comments and whitespace are ignored, this is important as
    all declarations are indented.
    >>> enum = (''
    ... '    /**'
    ... '     * Put the logical AND of registers rs and rt into register rd.'
    ... '     * Is only valid if shamt is 0'
    ... '     */'
    ... '    AND(0x00, 0x24,'
    ... '        new Condition<RTypeInstruction, Integer>()'
    ... '               .checkThat(Int::shamt).is(0x00),'
    ... '        new MnemonicPattern<>('
    ... '               Str::iname, Str::rd,  Str::rs,  Str::rt)),')
    >>> get_instruction_name(enum)
    'AND'

    Parentheses in Javadoc are ignored
    >>> enum = (''
    ... '    /**'
    ... '     * Move from coprocessor 0. Move register rd in a coprocessor (register'
    ... '     * fs in the FPU) to CPU register rt. The floating-point unit is'
    ... '     * coprocessor 1.'
    ... '     */'
    ... '    // TODO: Validate that rs, shamt, and funct is 0'
    ... '    MFC0(0x10, 0x00, new Condition<RTypeInstruction, Integer>()'
    ... '            .checkThat(Int::rs).is(0x00).'
    ... '                    andThat(Int::shamt).is(0x00).'
    ... '                    andThat(Int::funct).is(0x00),'
    ... '            new MnemonicPattern<>(Str::iname, Str::rt,  Str::rd)),')
    >>> get_instruction_name(enum)
    'MFC0'
    """
    # The first character in the supplied string is the
    # first character in the enum name. Then the end
    # of the name is marked by the first parentheses
    # in the string.
    read_from_index = -2
    if '*/' in enum:
        # The enum is Javadoc'ed :) Ignore all characters above and
        # including the comment end marker
        read_from_index = enum.index('*/')

    lines = enum[read_from_index+2:].split()
    for line in lines:
        if '(' in line:
            # We've found the start of the declaration
            #x = line.strip()
            return line[:line.find('(')]

    return 'AND'

def get_condition_constructor(enum: str) -> str:
    """
    Yanks out the conditional constructor from an enum
    declaration

    >>> enum = ("ADDU(0, 0x21,"
    ...     "new Condition<RTypeInstruction, Integer>()"
    ...             ".checkThat(Int::shamt).is(0x00),"
    ...     "new MnemonicPattern<>("
    ...             "Str::iname, Str::rd,  Str::rs,  Str::rt))")
    >>> get_condition_constructor(enum)
    'new Condition<RTypeInstruction, Integer>().checkThat(Int::shamt).is(0x00)'
    """
    # Match against the condition constructor, and get the starting index.
    # Since we want to retrieve the substring describing the entire
    # construction of the conditional, all that remains is identifying
    # when the construction ends. 

    # There are instructions that do not have conditions
    if not 'new Condition' in enum:
        return ''

    start_index = enum.index('new Condition')

    # We know that the builder-pattern
    # that the Condition class employs will terminate with an is
    # statement. Searching backwards we can find the index of
    # that particular method call. Obviously, the method call
    # terminates on the closing parentheses
    is_index = enum.rindex('.is(')

    # The +1 is there to include the closing parentheses as well
    stop_index = enum[is_index::].index(')') + 1

    return enum[start_index:is_index+stop_index]

def create_valid_test_title(r: Record) -> str:
    """
    Generates a title given the result from get_condition

    The return value from the get_condition method is a namespace.
    A self-reliant unit-test for this function then becomes:
    >>> r = Record()
    >>> r.iname = 'MUL'
    >>> r.rd = '0x01'
    >>> r.shamt = '0x00'
    >>> create_valid_test_title(r)
    'mulIsValidIfRdIs0x01AndShamtIs0x00'

    Similarily, we can test the function using the input generated
    from get_condition which should be valid
    >>> enum = ("ADDU(0, 0x21,"
    ...     "new Condition<RTypeInstruction, Integer>()"
    ...             ".checkThat(Int::shamt).and(Int::rd).is(0x00),"
    ...     "new MnemonicPattern<>("
    ...             "Str::iname, Str::rd,  Str::rs,  Str::rt))")
    >>> create_valid_test_title(create_assignment_statements(enum))
    'adduIsValidIfRdIs0x00AndShamtIs0x00'
    """
    title = r.iname.lower() + "IsValidIf"
    conditions = []

    # Iterate over all the fields in r.
    # Sort them so we can make assertions about the output
    #
    # the Record r specifies fieldnames as keys and their
    # corresponding values specifies the expected value of the field.
    for (field_name, expected_value) in sorted(vars(r).items()):
        if (field_name is not 'iname'):
            # Capitalize so we get CamelCase-output
            field = field_name.capitalize()

            conditions.append(field + "Is" + expected_value)

    return title + "And".join(conditions)

def create_assignment_statements_output(r: Record) -> [str]:
    """
    Generate a series of assignment statements from a
    record describing the names in the record and
    their expected values.

    >>> enum = ("ADDU(0, 0x21,"
    ...     "new Condition<RTypeInstruction, Integer>()"
    ...             ".checkThat(Int::shamt).and(Int::rd).is(0x00),"
    ...     "new MnemonicPattern<>("
    ...             "Str::iname, Str::rd,  Str::rs,  Str::rt))")
    >>> create_assignment_statements_output(create_assignment_statements(enum))
    ['instruction.rd = 0x00', 'instruction.shamt = 0x00']
    """
    assignments = []
    for (field_name, expected_value) in sorted(vars(r).items()):
        if (field_name is not 'iname'):
            assignments.append('instruction.' + field_name + ' = ' + expected_value)

    return assignments

def create_assignment_statements(enum: str) -> Record:
    """
    Expects to retrieve a string describing a particular enum
    with all of the arguments passed to its constructor.
    Will then construct a record structure containing the
    name of the enum and the expected values of each field.

    >>> enum = ("ADDU(0, 0x21,"
    ...     "new Condition<RTypeInstruction, Integer>()"
    ...             ".checkThat(Int::shamt).and(Int::rd).is(0x00),"
    ...     "new MnemonicPattern<>("
    ...             "Str::iname, Str::rd,  Str::rs,  Str::rt))")
    >>> create_assignment_statements(enum)
    namespace(iname='ADDU', rd='0x00', shamt='0x00')

    >>> enum = (''
    ... '    /**'
    ... '     * Move from coprocessor 0. Move register rd in a coprocessor (register'
    ... '     * fs in the FPU) to CPU register rt. The floating-point unit is'
    ... '     * coprocessor 1.'
    ... '     */'
    ... '    // TODO: Validate that rs, shamt, and funct is 0'
    ... '    MFC0(0x10, 0x00, new Condition<RTypeInstruction, Integer>()'
    ... '            .checkThat(Int::rs).is(0x00).'
    ... '                    andThat(Int::shamt).is(0x00).'
    ... '                    andThat(Int::funct).is(0x00),'
    ... '            new MnemonicPattern<>(Str::iname, Str::rt,  Str::rd)),')
    >>> create_assignment_statements(enum)
    namespace(funct='0x00', iname='MFC0', rs='0x00', shamt='0x00')
    """

    r = Record()

    r.iname = get_instruction_name(enum)
    conditions = get_conditions(get_condition_constructor(enum))

    for condition in conditions:
        expected_value = condition[-1]
        [setattr(r, field, expected_value) for field in condition[:-1]]

    return r

def get_conditions(condition_constructor):
    """
    Get all of the conditions that the condition constructor specifies.

    A very simple example showcasing that we expect combined actual
    values to have their common expected value.

    >>> condition_constructor = ("new Condition<RTypeInstruction, Integer>()"
    ...                          ".checkThat(Int::shamt).and(Int::rd).is(0x00)")
    >>> get_conditions(condition_constructor)
    [['shamt', 'rd', '0x00']]

    A more extensive example shows that the function supports parsing
    of more complex expressions

    >>> enum = ("new Condition<RTypeInstruction, Integer>()"
    ... ".checkThat(Int::rt).is(0x00)."
    ... "checkThat(Int::rs).and(Int::shamt).and(Int::funct).is(0x00)"
    ... "andThat(Int::rd).is(0x11).")
    >>> get_conditions(enum)
    [['rt', '0x00'], ['rs', 'shamt', 'funct', '0x00'], ['rd', '0x11']]
    """
    # By replacing all parentheses with whitespace, we can get a list
    # of where we can access the arguments passed to the method calls,
    # as there is no nesting inside of the parentheses of the
    # expression and we know a prefix that each the same for each
    # argument we can iterate over all opening parens and using its
    # index get the argument as it will be the item immediately after.
    args = condition_constructor.replace('(', ' ').replace(')', ' ').split()

    # If an item starts with "Int::" it is a metod reference with the
    # same name as the field which it retrieves. We need the name of
    # that field to generate the appropriate code.
    #
    # For an example, if we parse the following line:
    #
    # andThat(Int::shamt).is(0x00)
    #
    # we want to yank out "shamt" specifically so that we can use it
    # to compose a test. Furthermore we want to pair it with what
    # it should be so that we can generate both valid and invalid
    # expressions.
    #
    # This would be trivial, but the is method call is not always
    # directly following an "andThat" expression as there is a
    # secondary method called "and" which is used for composing a
    # succession of conditons that all have to be the same value.
    #
    # An extensive example would be the line
    #
    # checkThat(Int::rs).andThat(Int::shamt).and(Int::funct).is(0x00)
    #
    # is essentially the following condition:
    #
    # assert((rs == 0) && (shamt == 0) && (funct == 0))
    #
    # Sidenote: Do not write conditions like that, instead write
    #
    # checkThat(Int::rs).and(Int::shamt).and(Int::funct).is(0x00)
    #
    # "andThat" is best to indicate the start of a new equality
    # although it is not required. Which is to say that either line
    # will work work just fine, its poor use of the Condition API.
    #
    # Either line when parsed should yield
    #
    # ['rs', 'shamt', 'funct', 0]
    #
    # although the caller will receive the above list nested inside
    # another list, viz.
    #
    # [['rs', 'shamt', 'funct', 0]]

    # Iteratively, we compound all Int:: expressions until
    # an "is" method is called.
    prefix = "Int::"
    conditions = []
    fields = []
    i = 0
    while i < len(args):
        arg = args[i]
        if arg.startswith(prefix):
            fields.append(arg[len(prefix)::])
        if arg.startswith(".is"):
            fields.append(args[i+1])
            conditions.append(fields)
            fields = []
        i += 1

    return conditions

def create_invalid_test_case(enum: str, instruction_type: str) -> str:
    pass

def create_invalid_test_title(r: Record, name: str) -> str:
    """
    Generates a title for a test when the value of the given field
    name is something other than the expected value
    
    >>> enum = ("ADDU(0, 0x21,"
    ...     "new Condition<RTypeInstruction, Integer>()"
    ...             ".checkThat(Int::shamt).and(Int::rd).is(0x00),"
    ...     "new MnemonicPattern<>("
    ...             "Str::iname, Str::rd,  Str::rs,  Str::rt))")
    >>> create_invalid_test_title(create_assignment_statements(enum), 'shamt')
    'adduShouldNotValidateIfShamtIsNot0x00'
    """
    # Iterate over all the fields in r so that we can get the
    # expected value
    title = [r.iname.lower(), 'ShouldNotValidateIf', name.capitalize(), 'IsNot']
    title = "".join(title)

    for (field_name, expected_value) in sorted(vars(r).items()):
        if (field_name is not 'iname'):
            # Capitalize so we get CamelCase-output
            if field_name == name:
                title += expected_value
                break

    return title



def create_valid_test_case(enum: str, instruction_type: str) -> str:
    """
    Creates a JUnitTestCase as a list of strings for
    each line from an enum. This creates a test
    that asserts that the enum is valid if it
    meets all its conditions

    >>> enum = ("ADDU(0, 0x21,"
    ...     "new Condition<RTypeInstruction, Integer>()"
    ...             ".checkThat(Int::shamt).and(Int::rd).is(0x00),"
    ...     "new MnemonicPattern<>("
    ...             "Str::iname, Str::rd,  Str::rs,  Str::rt))")
    >>> print(create_valid_test_case(enum, 'RTypeInstruction'))
    @Test
    public void adduIsValidIfRdIs0x00AndShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.ADDU;
        instruction.rd = 0x00;
        instruction.shamt = 0x00;
        assertThat(instruction.validate(), is(equalTo(true)));
    }

    If there are no conditions to satisfy the empty string is returned.
    Whitespaces are ignored.

    >>> enum = (
    ... '    /**'
    ... '     * Move conditional zero. Move register rs to register rd if'
    ... '     * register rt is zero.'
    ... '     */'
    ... '    MOVZ(0x00, 0x10, new MnemonicPattern<>(Str::iname, '
    ... '        Str::rd,  Str::rs,  Str::rt)),')
    >>> create_valid_test_case(enum, 'RTypeInstruction')
    ''
    """
    JUnit_test_marker = '@Test'

    assignments = create_assignment_statements(enum)
    assignments_output = create_assignment_statements_output(assignments)
    if len(assignments_output) == 0:
        # The enum is not subject to any conditions. Do not test it
        return ''

    title = create_valid_test_title(assignments)
    test_declaration = 'public void ' + title + '() {'

    variable_name = 'instruction'
    variable_initializer = " ".join([instruction_type, variable_name, '=',
                                     instruction_type + '.' + assignments.iname.upper()])

    body_statement = [variable_initializer]    
    body_statement.extend(assignments_output)

    # Indent the lines, add a trailing ;
    indent = '    ' # 4 spaces

    # Strings inside ('...', '...') are implicitly joined
    body_statement.append(('assertThat(instruction.validate(), '
                           'is(equalTo(true)))'))

    body_statement = [indent + s + ';' for s in body_statement]

    lines = [JUnit_test_marker, test_declaration]
    lines.extend(body_statement)
    lines.append('}')
    return "\n".join(lines)

def create_valid_test_cases(filename: str):
    test_cases = []

    with open(filename, 'r') as f:
        enum_declarations_flag = False
        current_enum = []
        for line in f:
            # Do nothing until the start of the enum is read
            # We want to ignore imports and JavaDoc.
            if line.strip().startswith('public enum'):
                enum_declarations_flag = True

            # We are parsing declarations at this point
            if enum_declarations_flag:
                # The enum declarations are seperated like paragraphs,
                # i.e. there is a blank line between them. Store lines
                # until a blank line is detected
                if is_empty(line): # Detect empty line
                    # Concatenate all the strings making up the current enum
                    enum = "".join(current_enum)
                    current_enum = []
                    test_case = create_valid_test_case(enum, 'RTypeInstruction')
                    if test_case != '': # Is the empty string if no conditions apply
                        test_cases.append(test_case)
                else:
                    current_enum.append(line)

            # Enum declarations end with a ; on a single line
            if line.strip() == ';':
                break

    return test_cases

def is_empty(string: str) -> bool:
    return not string.strip()

"""@Test
public void xShouldValidateIfABandCisYandIfHIsP() {
    RTypeInstruction r = RTypeInstruction.x
    r.a = r.b = r.c = Y;
    r.H = P;
    assertThat(r.validate(), is(equalTo(true)));
}"""

#cwd = os.getcwd()
#print(cwd)
#print(os.path.dirname(os.path.realpath('RTypeInstruction.java')))
#test_cases = create_valid_test_cases('/home/spock/Dropbox/github/dark-mips32-decompiler/src/main/java/se/filipallberg/dark/mips32decompiler/instruction/type/RTypeInstruction/RTypeInstruction.java')

#for test_case in test_cases:
#    print(test_case)


if __name__=="__main__":
    import doctest
    doctest.testmod()
