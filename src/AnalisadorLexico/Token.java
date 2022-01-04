package AnalisadorLexico;

import java.nio.charset.Charset;

public class Token {

    //codigos pra identificar os negocinhos
    public static final int TK_LETRA = 0;
    public static final int TK_OPERATION = 1;
    public static final int TK_NEGATION = 2;
    public static final int TK_PARENTHESIS = 3;
    public static final int TK_SPACE = 4;


    public static final String TK_TEXT[] = {
            "LETRA", 
            "OPERATOR", 
            "NEGATION", 
            "PARENTHESIS",
            "SPACE"
    };

    private int tipo;
    private char valor;
    private String texto;
    private int linha;
    private int coluna;

    public Token() {
        this.tipo = tipo;
        this.valor = valor;
        this.linha = linha;
        this.coluna = coluna;
        this.texto = texto;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public char getvalor() {
        return valor;
    }

    public void setValor(char valor) {
        this.valor = valor;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    public int getTexto() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    @Override
    public String toString() {
        return "Token[" +
                "tipo = " + tipo +
                " valor = " + valor + "]\n";
    }
}
