package Analisador;

public class Tipo {

    public static final int INT = 49;

    public static final int FLOAT = 50;

    public static final int IDENTIFICADOR = 51;

    public static final int CHAR = 52;

    public static final int SOMA = 53;

    public static final int SUBTRACAO = 54;

    public static final int MULTIPLICACAO = 55;

    public static final int DIVISAO = 56;

    public static final int ABRE_PARENTESES = 57;

    public static final int FECHA_PARENTESES = 58;

    public static final int ABRE_CHAVE = 59;

    public static final int FECHA_CHAVE = 60;

    public static final int ABRE_COLCHETE = 61;

    public static final int FECHA_COLCHETE = 62;

    public static final int PONTO_VIRGULA = 63;

    public static final int VIRGULA = 64;

    public static final int IGUALDADE = 65;

    public static final int ATRIBUICAO = 66;

    public static final int DIFERENCA = 67;

    public static final int MENOR = 68;

    public static final int MENOR_IGUAL = 69;

    public static final int MAIOR = 70;

    public static final int MAIOR_IGUAL = 71;
    public static final int OU_SIMPLES = 72;
    public static final int OU_COMPOSTO = 73;
    public static final int E_SIMPLES = 74;
    public static final int E_COMPOSTO = 75;
    public static final int COMENTARIO_SIMPLES = 76;
    public static final int COMENTARIO_COMPOSTO = 77;
    public static final int EOF = 78;

    public static int buscarTipo(String s) {
        switch (s) {
            case "int":
                return INT;
            case "float":
                return FLOAT;
            case "identificador":
                return IDENTIFICADOR;
            case "char":
                return CHAR;
            case "soma":
                return SOMA;
            case "subtracao":
                return SUBTRACAO;
            case "multiplicacao":
                return MULTIPLICACAO;
            case "divisao":
                return DIVISAO;
            case "abreParenteses":
                return ABRE_PARENTESES;
            case "fechaParenteses":
                return FECHA_PARENTESES;
            case "abreChaves":
                return ABRE_CHAVE;
            case "fechaChaves":
                return FECHA_CHAVE;
            case "abreColchetes":
                return ABRE_COLCHETE;
            case "fechaColchetes":
                return FECHA_COLCHETE;
            case "pontoVirgula":
                return PONTO_VIRGULA;
            case "virgula":
                return VIRGULA;
            case "igualdade":
                return IGUALDADE;
            case "atribuicao":
                return ATRIBUICAO;
            case "diferenca":
                return DIFERENCA;
            case "menor":
                return MENOR;
            case "menorIgual":
                return MENOR_IGUAL;
            case "maior":
                return MAIOR;
            case "maiorIgual":
                return MAIOR_IGUAL;
            case "ouSimples":
                return OU_SIMPLES;
            case "ouComposto":
                return OU_COMPOSTO;
            case "eSimples":
                return E_SIMPLES;
            case "eComposto":
                return E_COMPOSTO;
            case "comentarioS":
                return COMENTARIO_SIMPLES;
            case "comentarioC":
                return COMENTARIO_COMPOSTO;
            case "eof":
                return EOF;

        }
        return 0;

    }

}
