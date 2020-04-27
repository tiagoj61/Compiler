

import Analisador.Lexico;
import Analisador.Token;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import parsser.AnalisadorSintatico;

public class ProjetoCompiladores {

    public static void main(String[] args) throws FileNotFoundException, IOException, Throwable {
        AnalisadorSintatico sintatico=new AnalisadorSintatico(new File(args[0]));
        sintatico.programa();
    }

}
