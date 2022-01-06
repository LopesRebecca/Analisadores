import AnalisadorLexico.AnalisadorLexico;
import AnalisadorLexico.Token;
import AnalisadorSintatico.AnalisadorSintatico;
import Exeptions.ExceptionSintatico;
import Exeptions.ExpectionLexico;
import conversor.Conversor;

public class App {
    public static void main(String[] args) {
        try{
            AnalisadorLexico aLexico = new AnalisadorLexico("src/input.txt");
            AnalisadorSintatico aSintatico = new AnalisadorSintatico(aLexico);

            aSintatico.verificador();
            System.out.println("\nVerificação foi um sucesso!");
            
            System.out.println("\n\n Iniciando conversão");
            
            Conversor conversor = new Conversor();
            conversor.getClauses("(a # b) & (a #(a # b))");

        }catch (ExpectionLexico e) {
            System.out.println("Erro Lexico: " +e.getMessage());
            e.printStackTrace();
        }catch (ExceptionSintatico e) {
            System.out.println("\n Erro Sintatico: " +e.getMessage());
            e.printStackTrace();
        }catch (Exception e) {
            System.out.println("Erro generico!!");
            System.out.println(e.getClass().getName());
            e.printStackTrace();
        }
    }
}
