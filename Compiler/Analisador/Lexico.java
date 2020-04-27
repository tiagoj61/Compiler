package Analisador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import parsser.AnalisadorSintatico;

public class Lexico {
    
    private static char c;
   
    private static String s, result;
    public static int coluna;
    public static int linha;
    private static File arq;
    private static BufferedReader br;

    public Lexico(File arquivo) throws FileNotFoundException, IOException {
        arq = arquivo;
        br = new BufferedReader(new FileReader(arq));
        c = (char) br.read();
        coluna = 1;
        linha = 1;
        result = "";
        String s = "";
        char c = ' ';
    }

    public Token scanner() throws IOException {

        while (Character.isWhitespace(c) || c == '\n') {//white space

            c = (char) br.read();
            coluna++;
            if (c == '\n') {
                c = (char) br.read();
                coluna = 1;
                linha++;
            }
        }
        if (Character.isDigit(c)) {//começa com digito

            while (Character.isDigit(c)) {

                result = result + c;
                c = (char) br.read();
                coluna++;
                //c = s.charAt(posi);
                // posi++;
            }
            if (c == '.') {//pega primeiro ponto
                result = result + c;
                c = (char) br.read();
                coluna++;

                if (Character.isDigit(c)) {
                    while (Character.isDigit(c)) {
                        result = result + c;
                        c = (char) br.read();
                        coluna++;

                    }
                    if (c != '.') {//qlq != digito e .
                        Token novo = new Token(result, Tipo.buscarTipo("float"));
                        result = "";
                        return novo;
                    } else {
                        System.out.println("Erro float mal formado");
                        System.out.println("Erro na linha: " + linha + " e na coluna: " + coluna);
                         System.exit(0);
                    }
                } else {
                    System.out.println("Erro float mal formado");
                    System.out.println("Erro na linha: " + linha + " e na coluna: " + coluna);
                     System.exit(0);
                }

            } else {
                Token novo = new Token(result, Tipo.buscarTipo("int"));
                result = "";
                return novo;
            }

        } else if (c == '.') {//começa com ponto
            result = result + c;
            c = (char) br.read();
            coluna++;

            if (Character.isDigit(c)) {
                while (Character.isDigit(c)) {//ponto ou digito
                    result = result + c;
                    c = (char) br.read();
                    coluna++;

                }
                if (c != '.') {
                    Token novo = new Token(result, Tipo.buscarTipo("float"));
                    result = "";
                    return novo;
                } else {
                    System.out.println("Erro float mal formado");
                    System.out.println("Erro na linha: " + linha + " e na coluna: " + coluna);
                     System.exit(0);
                }
            } else {
                System.out.println("Erro float mal formado");
                System.out.println("Erro na linha: " + linha + " e na coluna: " + coluna);
                 System.exit(0);
            }

        } else if (c == '_' || Character.isAlphabetic(c)) {
            result = result + c;
            c = (char) br.read();
            coluna++;

            if (Character.compare(c, '_') == 0 || Character.isAlphabetic(c) || Character.isDigit(c)) {
                while (Character.compare(c, '_') == 0 || Character.isAlphabetic(c) || Character.isDigit(c)) {
                    result = result + c;
                    c = (char) br.read();
                    coluna++;

                }

                int aux = PalavReserv.buscaPlavraResev(result);

                if (aux != -1) {
                    Token novo = new Token(result, aux);
                    result = "";
                    return novo;
                } else {
                    Token novo = new Token(result, Tipo.buscarTipo("identificador"));
                    result = "";
                    return novo;
                }

            } else {
                Token novo = new Token(result, Tipo.buscarTipo("identificador"));
                result = "";
                return novo;
            }

        } else if (c == "'".charAt(0)) { //char
            result = result + c;
            c = (char) br.read();
            coluna++;

            if (Character.isAlphabetic(c) || Character.isDigit(c)) {
                result = result + c;
                c = (char) br.read();
                coluna++;

                if (c == "'".charAt(0)) {
                    result = result + c;
                    c = (char) br.read();
                    coluna++;

                    Token novo = new Token(result, Tipo.buscarTipo("char"));
                    result = "";
                    return novo;
                } else {
                    System.out.println("Erro char mal formado");
                    System.out.println("Erro na linha: " + linha + " e na coluna: " + coluna);
                     System.exit(0);
                }
            } else {
                System.out.println("Erro char mal formado");
                System.out.println("Erro na linha: " + linha + " e na coluna: " + coluna);
                 System.exit(0);
            }
        } else if (c == '+') {
            result = result + c;
            c = (char) br.read();
            coluna++;

            Token novo = new Token(result, Tipo.buscarTipo("soma"));
            result = "";
            return novo;

        } else if (c == '*') {
            result = result + c;
            c = (char) br.read();
            coluna++;

            Token novo = new Token(result, Tipo.buscarTipo("multiplicacao"));
            result = "";
            return novo;

        } else if (c == '-') {
            result = result + c;
            c = (char) br.read();
            coluna++;

            Token novo = new Token(result, Tipo.buscarTipo("subtracao"));
            result = "";
            return novo;

        } else if (c == '<' || c == '>') {
            if (c == '<') {
                result = result + c;
                c = (char) br.read();
                coluna++;

                if (c == '=') {
                    result = result + c;
                    c = (char) br.read();
                    coluna++;

                    Token novo = new Token(result, Tipo.buscarTipo("menorIgual"));
                    result = "";
                    return novo;
                } else {
                    Token novo = new Token(result, Tipo.buscarTipo("menor"));
                    result = "";
                    return novo;
                }
            }
            if (c == '>') {
                result = result + c;
                c = (char) br.read();
                coluna++;

                if (c == '=') {
                    result = result + c;
                    c = (char) br.read();
                    coluna++;

                    Token novo = new Token(result, Tipo.buscarTipo("maiorIgual"));
                    result = "";
                    return novo;
                } else {
                    Token novo = new Token(result, Tipo.buscarTipo("maior"));
                    result = "";
                    return novo;
                }
            }
        } else if (c == '=') {

            result = result + c;
            c = (char) br.read();
            coluna++;

            if (c == '=') {
                result = result + c;
                c = (char) br.read();
                coluna++;

                Token novo = new Token(result, Tipo.buscarTipo("igualdade"));
                result = "";
                return novo;

            } else {
                Token novo = new Token(result, Tipo.buscarTipo("atribuicao"));
                result = "";
                return novo;

            }
        } else if (c == '!') {
            result = result + c;
            c = (char) br.read();
            coluna++;
            if (c == '=') {
                result = result + c;
                c = (char) br.read();
                coluna++;
                Token novo = new Token(result, Tipo.buscarTipo("diferenca"));
                result = "";
                return novo;
            }else{
                return null;
            }
        } else if (c == '(') {
            result = result + c;
            c = (char) br.read();
            coluna++;

            Token novo = new Token(result, Tipo.buscarTipo("abreParenteses"));
            result = "";
            return novo;

        } else if (c == ')') {
            result = result + c;
            c = (char) br.read();
            coluna++;

            Token novo = new Token(result, Tipo.buscarTipo("fechaParenteses"));
            result = "";
            return novo;

        } else if (c == '{') {
            result = result + c;
            c = (char) br.read();
            coluna++;

            Token novo = new Token(result, Tipo.buscarTipo("abreChaves"));
            result = "";
            return novo;

        } else if (c == '}') {
            result = result + c;
            c = (char) br.read();
            coluna++;

            Token novo = new Token(result, Tipo.buscarTipo("fechaChaves"));
            result = "";
            return novo;

        } else if (c == '[') {
            result = result + c;
            c = (char) br.read();
            coluna++;

            Token novo = new Token(result, Tipo.buscarTipo("abreColchetes"));
            result = "";
            return novo;

        } else if (c == ']') {
            result = result + c;
            c = (char) br.read();
            coluna++;

            Token novo = new Token(result, Tipo.buscarTipo("fechaColchetes"));
            result = "";
            return novo;

        } else if (c == ';') {
            result = result + c;
            c = (char) br.read();
            coluna++;

            Token novo = new Token(result, Tipo.buscarTipo("pontoVirgula"));
            result = "";
            return novo;

        } else if (c == ',') {
            result = result + c;
            c = (char) br.read();
            coluna++;

            Token novo = new Token(result, Tipo.buscarTipo("virgula"));
            result = "";
            return novo;

        } else if (c == '/') {
            c = (char) br.read();
            coluna++;

            if (c == '/') {
                result = result + c;
                c = (char) br.read();
                coluna++;

                while (c != '\n' && c != 65535) {
                    c = (char) br.read();

                    coluna++;

                }
                if (c == '\n') {
                    c = (char) br.read();
                    coluna = 1;
                    linha++;
                }
                Token novo = new Token("comentario", Tipo.buscarTipo("comentarioS"));
                result = "";
                return null;

            } else if (c == '*') {

                c = (char) br.read();
                coluna++;
                while (true) {
                    while (c != '*') {
                        if (c == 65535) {
                            System.out.println("Erro eof em comentario inacabado");
                            System.out.println("Erro na linha: " + linha + " e na coluna: " + coluna);
                            System.exit(0);
                            
                        }

                        if (c == '\n') {
                            c = (char) br.read();
                            coluna = 1;
                            linha++;
                        }
                        if (c != '*') {
                            c = (char) br.read();
                            coluna++;
                        }
                    }

                    c = (char) br.read();
                    coluna++;
                    if (c == '/') {

                        c = (char) br.read();
                        coluna++;

                        Token novo = new Token("comentario", Tipo.buscarTipo("comentarioC"));
                        result = "";
                        return null;

                    }
                }
            } else {
                Token novo = new Token(result, Tipo.buscarTipo("divisao"));
                result = "";
                return novo;
            }

        } else if (c == '&') {
            result = result + c;
            c = (char) br.read();
            coluna++;
            if (c == '&') {
                result = result + c;
                c = (char) br.read();
                coluna++;
                Token novo = new Token(result, Tipo.buscarTipo("eComposto"));
                result = "";
                return novo;
            } else {
                Token novo = new Token(result, Tipo.buscarTipo("eSimples"));
                result = "";
                return novo;
            }

        } else if (c == '|') {
            result = result + c;
            c = (char) br.read();
            coluna++;
            if (c == '|') {
                result = result + c;
                c = (char) br.read();
                coluna++;

                Token novo = new Token(result, Tipo.buscarTipo("ouComposto"));
                result = "";
                return novo;
            } else {

                Token novo = new Token(result, Tipo.buscarTipo("ouSimples"));
                result = "";
                return novo;
            }

        } else if (c == 65535) {
            Token novo = new Token("eof", Tipo.buscarTipo("eof"));
            result = "";
            return novo;
        } else {
            System.out.println("Erro Character invalido ");
            System.out.println("Erro na linha: " + linha + " e na coluna: " + coluna);
             System.exit(0);
            
        }
        return null;
    }

}
