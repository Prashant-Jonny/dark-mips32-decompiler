#!/usr/bin/env python3
from types import SimpleNamespace as Record
import re

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

def get_name(enum):
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

def get_condition_constructor(enum):
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

def get_condition(enum):
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
    >>> get_condition(enum)
    namespace(iname='ADDU', shamt=0)
    """

    r = Record()

    r.iname = get_name(enum)
    condition = get_condition_constructor(enum)

    r.shamt = 0
    return r



def generate_test(instruction_enum):
    new_condition_marker = "new Condtion<RTypeInstruction, Integer>()"
    end_of_condition_marker = "new MnemonicPattern<>"

def generate_output(condition_set):
    assignments = []
    for condition in condition_set:
        assignments.append(" = ".join(['instruction.' + x for x in condition[:-1]]))
        assignments[-1] += " = " + condition[-1]
    return assignments


def get_condition_set(condition_constructor):
    """
    Get all of the conditions that the condition constructor specifies.

    A very simple example showcasing that we expect combined actual
    values to have their common expected value.

    >>> condition_constructor = ("new Condition<RTypeInstruction, Integer>()"
    ...                          ".checkThat(Int::shamt).and(Int::rd).is(0x00)")
    >>> get_condition_set(condition_constructor)
    [['shamt', 'rd', '0x00']]

    A more extensive example shows that the function supports parsing
    of more complex expressions

    >>> test_case = ("new Condition<RTypeInstruction, Integer>()"
    ... ".checkThat(Int::rt).is(0x00)."
    ... "checkThat(Int::rs).and(Int::shamt).and(Int::funct).is(0x00)"
    ... "andThat(Int::rd).is(0x11).")
    >>> get_condition_set(test_case)
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
    conditionSet = []
    fields = []
    i = 0
    while i < len(args):
        arg = args[i]
        if arg.startswith(prefix):
            fields.append(arg[len(prefix)::])
        if arg.startswith(".is"):
            fields.append(args[i+1])
            conditionSet.append(fields)
            fields = []
        i += 1

    return conditionSet


test_case = ("MFC1(0x11, 0x00, "
                 "new Condition<RTypeInstruction, Integer>()"
                     ".checkThat(Int::rt).is(0x00)."
                     "checkThat(Int::rs).and(Int::shamt).and(Int::funct).is(0x00)"
                     "andThat(Int::rd).is(0x00)."
                     "andThat(Int::opcode).is(0x11),"
                 "new MnemonicPattern<>(Str::iname, Str::rt,  Str::fs))")
#print(get_condition(cond))
#print(get_condition(cond2))
print(generate_output(get_condition_set(test_case)))

"""@Test
public void xShouldValidateIfABandCisYandIfHIsP() {
    RTypeInstruction r = RTypeInstruction.x
    r.a = r.b = r.c = Y;
    r.H = P;
    assertThat(r.validate(), is(equalTo(true)));
}"""

#get_enums("ValidationTest.java")

if __name__=="__main__":
    import doctest
    doctest.testmod()
