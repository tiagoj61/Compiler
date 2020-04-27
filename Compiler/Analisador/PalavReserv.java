package Analisador;

public class PalavReserv {

    public static final int PRIVATE = 0;

    public static final int PROTECTED = 1;

    public static final int PUBLIC = 2;

    public static final int ABSTRACT = 3;

    public static final int CLASS = 4;

    public static final int EXTENDS = 5;

    public static final int FINAL = 6;

    public static final int IMPLEMENTS = 7;

    public static final int INTERFACE = 8;

    public static final int NATIVE = 9;

    public static final int NEW = 10;

    public static final int STATIC = 11;

    public static final int STRICTFP = 12;

    public static final int SYNCHRONIZED = 13;

    public static final int TRANSIENT = 14;

    public static final int VOLATILE = 15;

    public static final int BREAK = 16;

    public static final int CASE = 17;

    public static final int CONTINUE = 18;

    public static final int DEFAULT = 19;

    public static final int DO = 20;

    public static final int ELSE = 21;

    public static final int FOR = 22;

    public static final int IF = 23;

    public static final int INSTANCEOF = 24;

    public static final int RETURN = 25;

    public static final int SWITCH = 26;

    public static final int WHILE = 27;

    public static final int ASSERT = 28;

    public static final int CATCH = 29;

    public static final int FINALLY = 30;

    public static final int THROW = 31;

    public static final int THROWS = 32;

    public static final int TRY = 33;

    public static final int IMPORT = 34;

    public static final int PACKAGE = 35;

    public static final int BOOLEAN = 36;

    public static final int BYTE = 37;

    public static final int CHAR = 38;

    public static final int DOUBLE = 39;

    public static final int FLOAT = 40;

    public static final int INT = 41;

    public static final int LONG = 42;

    public static final int SHORT = 43;

    public static final int SUPER = 44;

    public static final int THIS = 45;

    public static final int VOID = 46;

    public static final int CONST = 47;

    public static final int GOTO = 48;

    public static final int MAIN = 79;

    public PalavReserv() {

    }

    public static int buscaPlavraResev(String str) {
        switch (str) {
            case "private":
                return PRIVATE;
            case "protected":
                return PROTECTED;
            case "public":
                return PUBLIC;
            case "abstract":
                return ABSTRACT;
            case "class":
                return CLASS;
            case "extends":
                return EXTENDS;
            case "final":
                return FINAL;
            case "implements":
                return IMPLEMENTS;
            case "interface":
                return INTERFACE;
            case "native":
                return NATIVE;
            case "new":
                return NEW;
            case "static":
                return STATIC;
            case "strictfp":
                return STRICTFP;
            case "synchronized":
                return SYNCHRONIZED;
            case "transient":
                return TRANSIENT;
            case "volatile":
                return VOLATILE;
            case "break":
                return BREAK;
            case "case":
                return CASE;
            case "continue":
                return CONTINUE;
            case "defauLt":
                return DEFAULT;
            case "do":
                return DO;
            case "else":
                return ELSE;
            case "for":
                return FOR;
            case "if":
                return IF;
            case "intenceof":
                return INSTANCEOF;
            case "return":
                return RETURN;
            case "switch":
                return SWITCH;
            case "while":
                return WHILE;
            case "assert":
                return ASSERT;
            case "catch":
                return CATCH;
            case "finally":
                return FINALLY;
            case "throw":
                return THROW;
            case "throws":
                return THROWS;
            case "try":
                return TRY;
            case "import":
                return IMPORT;
            case "package":
                return PACKAGE;
            case "boolean":
                return BOOLEAN;
            case "byte":
                return BYTE;
            case "char":
                return CHAR;
            case "double":
                return DOUBLE;
            case "float":
                return FLOAT;
            case "int":
                return INT;
            case "long":
                return LONG;
            case "short":
                return SHORT;
            case "super":
                return SUPER;
            case "this":
                return THIS;
            case "void":
                return VOID;
            case "const":
                return CONST;
            case "goto":
                return GOTO;
            case "main":
                return MAIN;

        }
        return -1;
    }
}
