/*
Sı́mbolos proposicional (ou atômicos) = {a, b, c, . . . , x, y, z}
Conectivo unário: − (Negação)
Conectivos Binários: & (conjunção), # (disjunção) e > (implicação)
Sı́mbolos auxiliares: ) e ( - parênteses.
*/
package AnalisadorLexico.analisador;
import AnalisadorLexico.execesoes.ExpectionLexico;
import AnalisadorLexico.analisador.Token;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AnalisadorLexico {

    private char[] formula;
    private int estado;
    private int posicao;
    private int linha;
    private  int coluna;

    public AnalisadorLexico() {
    }

    public AnalisadorLexico(char[] formula, int estado, int pos, int linha, int coluna) {
        this.formula = formula;
        this.estado = estado;
        this.posicao = posicao;
        this.linha = linha;
        this.coluna = coluna;


    }

    public char[] getFormula() {
        return formula;
    }

    public void setFormula(char[] formula) {
        this.formula = formula;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public AnalisadorLexico(String filename){
        try {
            //ler cada byte do arquivo
            String txtformula;
            txtformula = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
            System.out.println(txtformula);
            formula = txtformula.toCharArray();
            posicao =0;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //analisar a formula e definir tokens
    public Token proximoToken(){
        char formulaDaVez;
        Token token = new Token();
        String termo=" ";

        if(finalDoArquivo()) {
            return null;
        }
        estado=0;

        while(true){
            formulaDaVez = proximo();
            coluna++;
            //token = new Token();

            switch(estado) {
                case 0:
                    if (letra(formulaDaVez)) {
                        termo += formulaDaVez;
                        estado = 1;
                    }else if (numerico(formulaDaVez)) {
                        termo+=formulaDaVez;
                        estado=2;
                    }else if (espacamento(formulaDaVez)) {
                        estado = 0;
                    }else if (operadores(formulaDaVez)) {
                        termo +=formulaDaVez;
                        token.setTipo(Token.TK_OPERATION);
                        token.setTexto(termo);
                        token.setLinha(linha);
                        token.setColuna(coluna - termo.length());
                        return token;

                    }else if(auxiliares(formulaDaVez)){
                        estado = 5;
                    }else {
                        throw new ExpectionLexico("Erro simbolo incorreto");
                    }
                    break;
                case 1:
                    if (letra(formulaDaVez) || numerico(formulaDaVez) || auxiliares(formulaDaVez)){
                        estado=1;
                        termo += formulaDaVez;
                    }else if (espacamento(formulaDaVez) || operadores(formulaDaVez) || auxiliares(formulaDaVez)){
                        if (!finalarquivo(formulaDaVez)) {
                            back();
                        }
                        token.setTipo(Token.TK_IDENT);
                        token.setTexto(termo);
                        token.setLinha(linha);
                        token.setColuna(coluna - termo.length());
                        return token;
                    }else {
                        throw new ExpectionLexico("Erro identificador mal formulado");
                    }
                    break;
                case 2:
                    if (numerico(formulaDaVez) || formulaDaVez == '.') {
                        estado = 2;
                        termo += formulaDaVez;
                    }
                    else if (!letra(formulaDaVez) || finalarquivo(formulaDaVez)) {
                        if (!finalarquivo(formulaDaVez))
                            back();
                        token = new Token();
                        token.setTipo(Token.TK_NUMBER);
                        token.setTexto(termo);
                        token.setLinha(linha);
                        token.setColuna(coluna - termo.length());
                        return token;
                    }
                    else {
                        throw new ExpectionLexico("Unrecognized Number");
                    }
                    break;
            }
        }
    }


    //definir os negocinhos da formula
    private boolean numerico(char c){
        return (c>= '0' && c <= '9');
    }

    private boolean letra(char c){
        return (c>= 'a' && c <= 'z');
    }

    private boolean operadores(char c){
        return c == '-' || c == '#' || c == '&' || c == '>';
    }

    private boolean espacamento(char c){
        return c=='\n'|| c=='\t' || c==' ' || c=='\r';
    }

    private boolean auxiliares(char c){
        return c==')' || c== '(';
    }
    //negocinhos definidos


    //percorrer essa pra ngm ficar abandonado!
    private char proximo(){
        if(finalDoArquivo()){
            return '\0';
        }
        return formula[posicao++];
    }

    //ja acabou jessica?
    private boolean finalDoArquivo(){
        return posicao >= formula.length;
    }

    //volta pra tras!
    private void back(){
        posicao--;
        coluna --;
    }

    private boolean finalarquivo(char c){
        return  c == '\0';
    }
}
