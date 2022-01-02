package AnalisadorSintatico;

import Exeptions.ExceptionSintatico;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import AnalisadorLexico.AnalisadorLexico;
import AnalisadorLexico.Token;

public class AnalisadorSintatico {

    private AnalisadorLexico aLexico;
    private Token token;
    private String formula;

    //recebendo o analisador lexico como parametro
    public  AnalisadorSintatico(AnalisadorLexico aLexico){
        this.aLexico = aLexico;
//        this.token = token;
    }

    public void JuntandoGeral() {
        TipoDeIdentidade();
        VerificarToken();
        Operador();

    }

    //verificar se e o ultimo token
    public void VerificarToken() {
        token = aLexico.proximoToken();
        if (token != null) {
            Operador(); //verificar se há uma operador
            TipoDeIdentidade();
            VerificarToken();
        }
    }

    //julgando a identidade
    public void TipoDeIdentidade() {
        token = aLexico.proximoToken();
        if (token.getTipo() != Token.TK_IDENT && token.getTipo() != Token.TK_NUMBER && token.getTipo() != Token.TK_ASSIGNMENT) {
            throw new ExceptionSintatico("ID ou Numero não esperado");
        }

    }

    public void Operador() {
        if (token.getTipo() != Token.TK_OPERATION) {
            throw new ExceptionSintatico("Operator Expected, found "+Token.TK_TEXT[token.getTipo()]+" ("+token.getTexto()+")  at LINE "+token.getLinha()+" and COLUMN "+token.getColuna());
        }
    }
}
