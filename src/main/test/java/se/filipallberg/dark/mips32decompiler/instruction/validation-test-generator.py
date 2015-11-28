#!/usr/bin/env python3
#from types import SimpleNamespace as Record
import types # Get access to SimpleNamespace
import typing # Enable type hinting and type synonyms

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

def get_name(enum: str) -> str:
    """
    Expects that the name of the enum starts at the first
    character of the supplied string. Will return the name
    of that particular enum.

    >>> get_name("ADDU(0, 0x21,")
    'ADDU'
    """
    # The first character in the supplied string is the
    # first character in the enum name. Then the end
    # of the name is marked by the first parentheses
    # in the string.
    return enum[:enum.find('(')]

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

def generate_title(r: Record) -> str:
    """
    Generates a title given the result from get_condition

    The return value from the get_condition method is a namespace.
    A self-reliant unit-test for this function then becomes:
    >>> r = Record()
    >>> r.iname = 'MUL'
    >>> r.rd = '0x01'
    >>> r.shamt = '0x00'
    >>> generate_title(r)
    'mulIsValidIfRdIs0x01AndShamtIs0x00'

    Similarily, we can test the function using the input generated
    from get_condition which should be valid
    >>> enum = ("ADDU(0, 0x21,"
    ...     "new Condition<RTypeInstruction, Integer>()"
    ...             ".checkThat(Int::shamt).and(Int::rd).is(0x00),"
    ...     "new MnemonicPattern<>("
    ...             "Str::iname, Str::rd,  Str::rs,  Str::rt))")
    >>> generate_title(get_assignment_statements(enum))
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

def generate_assignment_statement_output(r: Record) -> [str]:
    """
    Generate a series of assignment statements from a
    record describing the names in the record and
    their expected values.

    >>> enum = ("ADDU(0, 0x21,"
    ...     "new Condition<RTypeInstruction, Integer>()"
    ...             ".checkThat(Int::shamt).and(Int::rd).is(0x00),"
    ...     "new MnemonicPattern<>("
    ...             "Str::iname, Str::rd,  Str::rs,  Str::rt))")
    >>> generate_assignment_statement_output(get_assignment_statements(enum))
    ['instruction.rd = 0x00', 'instruction.shamt = 0x00']
    """
    assignments = []
    for (field_name, expected_value) in sorted(vars(r).items()):
        if (field_name is not 'iname'):
            assignments.append('instruction.' + field_name + ' = ' + expected_value)

    return assignments

def get_assignment_statements(enum: str) -> Record:
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
    >>> get_assignment_statements(enum)
    namespace(iname='ADDU', rd='0x00', shamt='0x00')
    """

    r = Record()

    r.iname = get_name(enum)
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

    >>> test_case = ("new Condition<RTypeInstruction, Integer>()"
    ... ".checkThat(Int::rt).is(0x00)."
    ... "checkThat(Int::rs).and(Int::shamt).and(Int::funct).is(0x00)"
    ... "andThat(Int::rd).is(0x11).")
    >>> get_conditions(test_case)
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


"""@Test
public void xShouldValidateIfABandCisYandIfHIsP() {
    RTypeInstruction r = RTypeInstruction.x
    r.a = r.b = r.c = Y;
    r.H = P;
    assertThat(r.validate(), is(equalTo(true)));
}"""

enum = ("ADDU(0, 0x21,"
         "new Condition<RTypeInstruction, Integer>()"
                 ".checkThat(Int::shamt).and(Int::rd).is(0x00),"
         "new MnemonicPattern<>("
                 "Str::iname, Str::rd,  Str::rs,  Str::rt))")
#print(generate_validation_test(get_condition(enum)))



if __name__=="__main__":
    import doctest
    doctest.testmod()
