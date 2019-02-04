# NYCL
Simple Parser - Interpreter
NYCL is a simple programming language (with great ambitions though :) ) with a reduced set of functionality.

Variables can only keep integer values and they need not be declared.

For example:

a = 3 + 5

creates a variable named "a" and assigns the value 8.

Variable names (identifiers) can consist of any number of latin characters, upper or lower case, digits, and the underscore (_). 
The first letter of an identifier cannot be a digit.

The language supports only the following operations: addition ( + ), subtraction ( - ), multiplication ( * ), and division ( / ).

There are 2 keywords:

show a

will display the value of the variable a, and

write "message"

will display the message enclosed in double quotes.

Comments are indicated with the # character and can only be single line comments.

TASKS

1. Build a parser to read a file (with extension .nycl) and display any errors found.

2. Build an interpreter to execute the code after successful parsing.
