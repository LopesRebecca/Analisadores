package AnalisadorSintatico;

import AnalisadorLexico.analisador.AnalisadorLexico;
import AnalisadorLexico.analisador.Token;

public class AnalisadorSintatico {

    private AnalisadorLexico al;
    private Token token;

    public  AnalisadorSintatico(AnalisadorLexico al){
        this.al = al;
        this.token = token;
    }

    public void E() {
        T();
        El();

    }

    public void El() {
        token = al.proximoToken();
        if (token != null) {
            OP();
            T();
            El();
        }
    }

    public void T() {
        token = al.proximoToken();
        if (token.getTipo() != Token.TK_IDENT && token.getTipo() != Token.TK_NUMBER) {
            throw new ExceptionSintatico("ID or NUMBER Expected!, found "+Token.TK_TEXT[token.getTipo()]+" ("+token.getTexto()+") at LINE "+token.getLinha()+" and COLUMN "+token.getColuna());
        }

    }

    public void OP() {
        if (token.getTipo() != Token.TK_OPERATION) {
            throw new ExceptionSintatico("Operator Expected, found "+Token.TK_TEXT[token.getTipo()]+" ("+token.getTexto()+")  at LINE "+token.getLinha()+" and COLUMN "+token.getColuna());
        }
    }
}
