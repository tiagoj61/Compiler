package Analisador;

public class Token {

    String token;
    int tipo;

    public Token(String token, int tipo) {
        this.token = token;
        this.tipo = tipo;
    }

    public Token() {
        this.token = "";
        this.tipo = 0;
    }
    @Override
    public String toString(){
        return "token: "+token+" tipo: "+tipo;
    }
    public String getToken(){
        return token;
    }
    public int getTipo(){
        return this.tipo;
    }
}
