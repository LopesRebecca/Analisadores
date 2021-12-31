import AnalisadorLexico.analisador.AnalisadorLexico;
import AnalisadorLexico.execesoes.ExpectionLexico;
import AnalisadorLexico.analisador.Token;
import AnalisadorSintatico.AnalisadorSintatico;
import AnalisadorSintatico.ExceptionSintatico;

public class App {
    public static void main(String[] args) {
        try{
            AnalisadorLexico al = new AnalisadorLexico("src/input.txt");
            AnalisadorSintatico as = new AnalisadorSintatico(al);
            Token token = null;

            as.E();
            System.out.println("Compilation Successful!");
        } catch (ExpectionLexico e) {
            System.out.println("Erro Lexico"+e.getMessage());
        }catch (ExceptionSintatico e) {
            System.out.println("Erro Sintatico"+e.getMessage());
        }catch (Exception e) {
            System.out.println("Erro generico!!");
            System.out.println(e.getClass().getName());
        }
    }
}
