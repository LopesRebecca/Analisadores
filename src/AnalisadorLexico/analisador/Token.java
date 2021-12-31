package AnalisadorLexico.analisador;

public class Token {

    //codigos pra identificar os negocinhos
    public static final int TK_IDENT = 0;
    public  static final int TK_NUMBER = 1;
    public static final int TK_OPERATION = 2;
    public static  final int TK_PONCTUATION = 3;
    public static final int TK_ASSIGN      = 4;
    public static final int TK_OTHER = 5;

    public static final String TK_TEXT[] = {
            "IDENTIFIER", "NUMBER", "OPERATOR", "PONCTUACTION", "ASSIGNMENT", "OTHER"
    };

    private int tipo;
    private String texto;
    private int linha;
    private int coluna;

    public Token() {
        this.tipo = tipo;
        this.texto = texto;
        this.linha = linha;
        this.coluna = coluna;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
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

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    @Override
    public String toString() {
        return "Token{" +
                "tipo=" + tipo +
                ", texto='" + texto + '\'' +
                '}';
    }
}
