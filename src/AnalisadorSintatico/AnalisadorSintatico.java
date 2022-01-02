package AnalisadorSintatico;

import AnalisadorLexico.analisador.AnalisadorLexico;
import AnalisadorLexico.analisador.Token;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AnalisadorSintatico {

    private AnalisadorLexico al;
    private Token token;
    private String formula;

    //recebendo o analisador lexico como parametro
    public  AnalisadorSintatico(AnalisadorLexico al){
        this.al = al;
        this.token = token;
    }

    public void JuntandoGeral() {
        TipoDeIdentidade();
        VerificarToken();
        Operador();

    }

    //verificar se é o ultimo token
    public void VerificarToken() {
        token = al.proximoToken();
        if (token != null) {
            Operador(); //verificar se é uma operador
            TipoDeIdentidade();
            VerificarToken();
        }
    }

    //julgando a identidade
    public void TipoDeIdentidade() {
        token = al.proximoToken();
        if (token.getTipo() != Token.TK_IDENT && token.getTipo() != Token.TK_NUMBER) {
            throw new ExceptionSintatico("ID ou Numero não esperado");
        }

    }

    public void Operador() {
        if (token.getTipo() != Token.TK_OPERATION) {
            throw new ExceptionSintatico("Operator Expected, found "+Token.TK_TEXT[token.getTipo()]+" ("+token.getTexto()+")  at LINE "+token.getLinha()+" and COLUMN "+token.getColuna());
        }
    }
}
