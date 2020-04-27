package parsser;

import Analisador.Lexico;
import Analisador.PalavReserv;
import Analisador.Tipo;
import Analisador.Token;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class AnalisadorSintatico {

    boolean dowhile;
    int cont;
    Token lkAhead;
    BufferedReader br;
    Stack<Token> pilha = new Stack<>();
    public static Lexico lexico;

    public AnalisadorSintatico(File arquivo) throws FileNotFoundException, IOException {
        br = new BufferedReader(new FileReader(arquivo));
        lexico = new Lexico(arquivo);
        lkAhead = new Token();
    }

    public void LookAhead() throws IOException {
        lkAhead = lexico.scanner();
        if (lkAhead == null) {
            lkAhead = lexico.scanner();
        }

    }

    public void programa() throws IOException {//1° linha do programa
        LookAhead();
        if (lkAhead.getTipo() == PalavReserv.INT) {
            LookAhead();
            if (lkAhead.getTipo() == PalavReserv.MAIN) {
                LookAhead();

                if (lkAhead.getTipo() == Tipo.ABRE_PARENTESES) {

                    LookAhead();
                    if (lkAhead.getTipo() == Tipo.FECHA_PARENTESES) {
                        LookAhead();
                        if (lkAhead.getTipo() == Tipo.ABRE_CHAVE) {

                            bloco();

                            if (!pilha.empty() && lkAhead.getTipo() == Tipo.EOF) {

                                System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + " : pilha incompleta");
                                System.out.println("Ultimo token lido: " + lkAhead);
                                System.exit(0);
                            }
                            if (lkAhead.getTipo() != Tipo.EOF) {

                                System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + " :  token mal posicionado");
                                System.out.println("Ultimo token lido: " + lkAhead);
                                System.exit(0);
                            }
                        } else {
                            System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + " : Falta do token {");
                            System.out.println("Ultimo token lido: " + lkAhead);
                            System.out.println("Ultimo token lido: " + lkAhead);
                            System.exit(0);
                        }
                    } else {
                        System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + " : Falta do token )");
                        System.out.println("Ultimo token lido: " + lkAhead);
                        System.exit(0);
                    }
                } else {
                    System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + " :Falta do token (");
                    System.out.println("Ultimo token lido: " + lkAhead);
                    System.exit(0);
                }
            } else {
                System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + " : Falta Palavra reservada MAIN");
                System.out.println("Ultimo token lido: " + lkAhead);
                System.exit(0);
            }

        } else {
            System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + " : Falta Palavra reservada INT");
            System.out.println("Ultimo token lido: " + lkAhead);
            System.exit(0);
        }
    }

    public void bloco() throws IOException {//segunda linha e pula pra bloco vontantdo no final com o '}'
        if (lkAhead.getTipo() == Tipo.ABRE_CHAVE) {
            pilha.push(lkAhead);
            LookAhead();

            while (lkAhead.getTipo() == PalavReserv.INT || lkAhead.getTipo() == PalavReserv.FLOAT || lkAhead.getTipo() == PalavReserv.CHAR) {

                Declaracao();

            }
            while (lkAhead.getTipo() == Tipo.ABRE_CHAVE || lkAhead.getTipo() == PalavReserv.WHILE || lkAhead.getTipo() == PalavReserv.IF || lkAhead.getTipo() == PalavReserv.DO || lkAhead.getTipo() == Tipo.IDENTIFICADOR) {
                comando();
                if (lkAhead.getTipo() == PalavReserv.INT || lkAhead.getTipo() == PalavReserv.FLOAT || lkAhead.getTipo() == PalavReserv.CHAR) {
                    System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + " : Declaração fora de ordem");
                    System.out.println("Ultimo token lido: " + lkAhead);
                    System.exit(0);
                }
            }

        } else {

            System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + " : Falta do token {");
            System.out.println("Ultimo token lido: " + lkAhead);
            System.exit(0);
        }

        if (lkAhead.getTipo() == Tipo.FECHA_CHAVE) {

            if (pilha.pop().getTipo() != Tipo.ABRE_CHAVE) {

                System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + " : Falta de uma {");
                System.out.println("Ultimo token lido: " + lkAhead);
                System.exit(0);
            } else {

                LookAhead();

            }
        } else {

            System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + " : Falta de uma }");

            System.out.println("Ultimo token lido: " + lkAhead);
            System.exit(0);
        }

    }

    public void Declaracao() throws IOException {
        if (lkAhead.getTipo() == PalavReserv.INT || lkAhead.getTipo() == PalavReserv.FLOAT || lkAhead.getTipo() == PalavReserv.CHAR) {
            LookAhead();
            if (lkAhead.getTipo() == Tipo.IDENTIFICADOR) {
                while (lkAhead.getTipo() == Tipo.IDENTIFICADOR) {
                    LookAhead();
                    if (lkAhead.getTipo() == Tipo.VIRGULA) {
                        LookAhead();
                        if (lkAhead.getTipo() != Tipo.IDENTIFICADOR) {

                            System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + " : Falta do token identificador");
                            System.out.println("Ultimo token lido: " + lkAhead);
                            System.exit(0);
                        }

                    } else if (lkAhead.getTipo() == Tipo.PONTO_VIRGULA) {
                        LookAhead();
                        return;
                    } else {

                        System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + " : Falta do token ;");
                        System.out.println("Ultimo token lido: " + lkAhead);
                        System.exit(0);
                    }

                }
            } else {
                System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + " : Falta do token identificador");
                System.out.println("Ultimo token lido: " + lkAhead);
                System.exit(0);
            }
        }
    }

    public void comando() throws IOException {
        if (lkAhead.getTipo() == Tipo.IDENTIFICADOR || lkAhead.getTipo() == Tipo.ABRE_CHAVE) {

            comandoBasico();

        } else if (lkAhead.getTipo() == PalavReserv.WHILE || lkAhead.getTipo() == PalavReserv.DO) {

            iteracao();
        } else if (lkAhead.getTipo() == PalavReserv.IF) {

            LookAhead();
            if (lkAhead.getTipo() == Tipo.ABRE_PARENTESES) {
                expressaoRELACIONAL();
                comando();
                if (lkAhead.getTipo() == PalavReserv.ELSE) {
                    LookAhead();
                    comando();

                }

            } else {
                System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + " : Falta do token (");
                System.out.println("Ultimo token lido: " + lkAhead);
                System.exit(0);
            }

        } else {

        }

    }

    public void comandoBasico() throws IOException {
        if (lkAhead.getTipo() == Tipo.IDENTIFICADOR) {
            atribuicao();
        } else if (lkAhead.getTipo() == Tipo.ABRE_CHAVE) {

            bloco();
        }

    }

    public void iteracao() throws IOException {
        if (lkAhead.getTipo() == PalavReserv.WHILE) {

            LookAhead();
            if (lkAhead.getTipo() == Tipo.ABRE_PARENTESES) {
                expressaoRELACIONAL();

                comando();
            } else {
                System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + " : Falta do token (");
                System.out.println("Ultimo token lido: " + lkAhead);
                System.exit(0);
            }
        } else if (lkAhead.getTipo() == PalavReserv.DO) {

            LookAhead();
            comando();

            if (lkAhead.getTipo() == PalavReserv.WHILE) {
                LookAhead();

                if (lkAhead.getTipo() == Tipo.ABRE_PARENTESES) {
                    dowhile = true;
                    expressaoRELACIONAL();

                    return;

                } else {
                    System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + " : Falta do token (");
                    System.out.println("Ultimo token lido: " + lkAhead);
                    System.exit(0);
                }

            } else {
                System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + " : Falta do token while");
                System.out.println("Ultimo token lido: " + lkAhead);
                System.exit(0);
            }

        }//erros
    }

    public void atribuicao() throws IOException {

        if (lkAhead.getTipo() == Tipo.IDENTIFICADOR) {
            LookAhead();

            if (lkAhead.getTipo() == Tipo.ATRIBUICAO) {
                LookAhead();
                if (lkAhead.getTipo() == Tipo.ABRE_PARENTESES || lkAhead.getTipo() == Tipo.INT || lkAhead.getTipo() == Tipo.FLOAT || lkAhead.getTipo() == Tipo.CHAR || lkAhead.getTipo() == Tipo.IDENTIFICADOR) {

                    expressaoaArit();
                    if (lkAhead.getTipo() == Tipo.PONTO_VIRGULA) {
                        LookAhead();
                        return;
                    } else {
                        System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + " : Falta o token ;");
                        System.out.println("Ultimo token lido: " + lkAhead);
                        System.exit(0);

                    }

                } else {
                    System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + " : Falta de identificador");
                    System.out.println("Ultimo token lido: " + lkAhead);
                    System.exit(0);
                }

            } else {
                System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + ": Falta do token para atribuição");
                System.out.println("Ultimo token lido: " + lkAhead);
                System.exit(0);
            }
        }
    }//erros

    public void expressaoRELACIONAL() throws IOException {
        if (lkAhead.getTipo() == Tipo.ABRE_PARENTESES || lkAhead.getTipo() == Tipo.INT || lkAhead.getTipo() == Tipo.FLOAT || lkAhead.getTipo() == Tipo.CHAR || lkAhead.getTipo() == Tipo.IDENTIFICADOR) {

            expressaoaArit();
            if (lkAhead.getTipo() == Tipo.DIFERENCA || lkAhead.getTipo() == Tipo.MAIOR || lkAhead.getTipo() == Tipo.MAIOR_IGUAL || lkAhead.getTipo() == Tipo.MENOR || lkAhead.getTipo() == Tipo.MENOR_IGUAL || lkAhead.getTipo() == Tipo.IGUALDADE) {//ajeitar

                LookAhead();
                expressaoaArit();
                if (lkAhead.getTipo() == Tipo.PONTO_VIRGULA && !dowhile) {
                    System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + ": expressao com ;");
                    System.out.println("Ultimo token lido: " + lkAhead);
                    System.exit(0);
                } else if (lkAhead.getTipo() == Tipo.PONTO_VIRGULA && dowhile) {
                    dowhile = false;
                    LookAhead();
                } else {

                }
            } else {

                System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + ": Falta do operador relacional");
                System.out.println("Ultimo token lido: " + lkAhead);
                System.exit(0);
            }

        }
    }

    public void expressaoaArit() throws IOException {

        while (lkAhead.getTipo() == Tipo.ABRE_PARENTESES) {
            termo();
            if (lkAhead.getTipo() == Tipo.FECHA_PARENTESES) {
                System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + ": Falta do token operador aritmetrico");
                System.out.println("Ultimo token lido: " + lkAhead);
                System.exit(0);
            }
        }

        if (lkAhead.getTipo() == Tipo.INT || lkAhead.getTipo() == Tipo.FLOAT || lkAhead.getTipo() == Tipo.CHAR || lkAhead.getTipo() == Tipo.IDENTIFICADOR) {//||  lkAhead.getTipo() == Tipo.FECHA_PARENTESES

            termo();

        } else {

            System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + ": Falta do token identificador");
            System.out.println("Ultimo token lido: " + lkAhead);
            System.exit(0);
        }

        if (lkAhead.getTipo() == Tipo.SOMA || lkAhead.getTipo() == Tipo.SUBTRACAO) {
            LookAhead();
            if (lkAhead.getTipo() == Tipo.INT || lkAhead.getTipo() == Tipo.FLOAT || lkAhead.getTipo() == Tipo.CHAR || lkAhead.getTipo() == Tipo.IDENTIFICADOR) {
                termo();
            } else {

                System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + ": Falta do token identificador");
                System.out.println("Ultimo token lido: " + lkAhead);
                System.exit(0);
            }
        } else if (lkAhead.getTipo() == Tipo.MULTIPLICACAO || lkAhead.getTipo() == Tipo.DIVISAO) {
            termo();

        }
        if (lkAhead.getTipo() == Tipo.FECHA_PARENTESES) {
            while (lkAhead.getTipo() == Tipo.FECHA_PARENTESES) {

                if (pilha.pop().getTipo() != Tipo.ABRE_PARENTESES) {
                    System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + ": Falta do token (");
                    System.exit(0);
                }
                LookAhead();
            }

        }

        if (lkAhead.getTipo() == Tipo.IDENTIFICADOR || lkAhead.getTipo() == Tipo.PONTO_VIRGULA || lkAhead.getTipo() == Tipo.ABRE_CHAVE || lkAhead.getTipo() == PalavReserv.INT || lkAhead.getTipo() == PalavReserv.IF || lkAhead.getTipo() == PalavReserv.WHILE || lkAhead.getTipo() == PalavReserv.DO) {

            return;
        } else if (lkAhead.getTipo() == Tipo.MULTIPLICACAO || lkAhead.getTipo() == Tipo.DIVISAO || lkAhead.getTipo() == Tipo.SOMA || lkAhead.getTipo() == Tipo.SUBTRACAO) {
            LookAhead();
            expressaoaArit();
        } else if (lkAhead.getTipo() == Tipo.DIFERENCA || lkAhead.getTipo() == Tipo.MAIOR || lkAhead.getTipo() == Tipo.MAIOR_IGUAL || lkAhead.getTipo() == Tipo.MENOR || lkAhead.getTipo() == Tipo.MENOR_IGUAL || lkAhead.getTipo() == Tipo.IGUALDADE) {

            return;
        } else if (lkAhead.getTipo() == Tipo.FECHA_CHAVE) {

            return;

        } else if (lkAhead.getTipo() == PalavReserv.INT || lkAhead.getTipo() == PalavReserv.CHAR || lkAhead.getTipo() == PalavReserv.FLOAT) {
            System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + ": Falta do token {");
            System.out.println("Ultimo token lido: " + lkAhead);
            System.exit(0);
        } else {

            System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + ": Falta do token operador");
            System.out.println("Ultimo token lido: " + lkAhead);
            System.exit(0);
        }

    }

    public void termo() throws IOException {
        if (lkAhead.getTipo() == Tipo.ABRE_PARENTESES || lkAhead.getTipo() == Tipo.INT || lkAhead.getTipo() == Tipo.FLOAT || lkAhead.getTipo() == Tipo.CHAR || lkAhead.getTipo() == Tipo.IDENTIFICADOR) {
            fator();
        } else if (lkAhead.getTipo() == Tipo.MULTIPLICACAO) {
            LookAhead();
            if (lkAhead.getTipo() == Tipo.ABRE_PARENTESES || lkAhead.getTipo() == Tipo.INT || lkAhead.getTipo() == Tipo.FLOAT || lkAhead.getTipo() == Tipo.CHAR || lkAhead.getTipo() == Tipo.IDENTIFICADOR) {
                fator();

            } else {

                System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + ": Falta do token identificador");
                System.out.println("Ultimo token lido: " + lkAhead);
                System.exit(0);
            }

        } else if (lkAhead.getTipo() == Tipo.DIVISAO) {
            LookAhead();
            if (lkAhead.getTipo() == Tipo.ABRE_COLCHETE || lkAhead.getTipo() == Tipo.INT || lkAhead.getTipo() == Tipo.FLOAT || lkAhead.getTipo() == Tipo.CHAR || lkAhead.getTipo() == Tipo.IDENTIFICADOR) {
                fator();
                return;
            } else {

                System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + ": Falta do token identificador");
                System.out.println("Ultimo token lido: " + lkAhead);
                System.exit(0);
            }

        } else {
            System.out.println("2");
            System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + ": Falta do token operador");
            System.out.println("Ultimo token lido: " + lkAhead);
            System.exit(0);
        }
    }

    public void fator() throws IOException {
        if (lkAhead.getTipo() == Tipo.ABRE_PARENTESES) {
            pilha.push(lkAhead);
            LookAhead();
            return;

        } else if (lkAhead.getTipo() == Tipo.IDENTIFICADOR) {
            LookAhead();
            return;
        } else if (lkAhead.getTipo() == Tipo.INT) {
            LookAhead();
            return;
        } else if (lkAhead.getTipo() == Tipo.FLOAT) {
            LookAhead();
            return;
        } else if (lkAhead.getTipo() == Tipo.CHAR) {
            LookAhead();
            return;
        } else {

            System.out.println("ERRO na linha " + Lexico.linha + ", coluna" + Lexico.coluna + ": Falta do token identificador");
            System.out.println("Ultimo token lido: " + lkAhead);
            System.exit(0);
        }
    }
}
