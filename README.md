# Compiler

This project implements a compiler for a new language with a small alphabet. It includes lexical and syntactic analysis functionalities to process source code, validate its structure, and produce necessary constructs for subsequent compilation stages.

## Project Structure

### Lexical Analysis

The lexical analysis stage identifies tokens, the smallest meaningful units of the language. This stage classifies the input code components as identifiers, operators, delimiters, keywords, etc.

**Key Components:**
- **`Tipo.java`**: Defines the types of tokens recognized by the compiler.
- **`PalavReserv.java`**: Contains the language's reserved keywords and functions to identify these keywords in the source code.

### Syntactic Analysis

Syntactic analysis verifies if tokens are organized according to the language's grammar. It uses a lookahead token and stacks to structure commands and expressions.

**Key Components:**
- **`AnalisadorSintatico.java`**: Implements the parser, with methods such as:
  - `programa()`: Defines the initial structure of a valid program.
  - `bloco()`: Manages grouping of declarations and commands.
  - `Declaracao()`: Processes variable declarations.
  - `comando()`: Interprets conditional statements, loops, and assignments.
  - `expressaoRELACIONAL()` and `expressaoaArit()`: Evaluate relational and arithmetic expressions.

## Features

1. **Syntactic Validation**:
   - Checks if the code adheres to the specified grammar.
   - Displays error messages with line and column information in case of failures.

2. **Supported Tokens**:
   - Basic types such as `int`, `float`, `char`.
   - Arithmetic operators (`+`, `-`, `*`, `/`), relational operators (`==`, `<`, `>`).
   - Delimiters (`{`, `}`, `(`, `)`).
   - Control structures like `if`, `while`, `do-while`.

3. **Analysis Stack**:
   - Manages tokens during the verification of blocks and expressions.

## How to Use

1. **Setup**:
   - Ensure Java is installed (version 8 or higher).

2. **Execution**:
   - Compile the code using `javac`.
   - Run the syntactic analyzer by providing a code file as input:
     ```bash
     java parsser.AnalisadorSintatico <code_file>
     ```

3. **Output**:
   - If the code is valid, the program will terminate silently.
   - Errors will be reported with the respective line and column numbers.

## Language Structure

- **Syntax of a Valid Program**:
  - Must start with `int main()` followed by a block `{ ... }`.
  - Variable declarations must appear at the beginning of the block.
  - Commands should adhere to the grammar.

**Example**:
```plaintext
int main() {
    int a, b;
    a = 5;
    if (a > b) {
        a = a + 1;
    }
}
