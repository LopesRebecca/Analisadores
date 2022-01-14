package LexicalAnalyzer;

public class Token {

    //codigos pra identificar os negocinhos
    public static final int TK_LETTER = 0;
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

    private int type;
    private char value;
    private String text;
    private int line;
    private int column;

    public Token() {
        this.type = type;
        this.value = value;
        this.line = line;
        this.column = column;
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public char getvalor() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public int getText() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "Token[" +
                "tipo = " + type +
                " valor = " + value + "]\n";
    }
}
