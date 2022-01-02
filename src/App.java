import AnalisadorLexico.AnalisadorLexico;
import AnalisadorLexico.Token;
import AnalisadorSintatico.AnalisadorSintatico;
import Exeptions.ExceptionSintatico;
import Exeptions.ExpectionLexico;

public class App {
    public static void main(String[] args) {
        try{
            AnalisadorLexico aLexico = new AnalisadorLexico("src/input.txt");
            AnalisadorSintatico aSintatico = new AnalisadorSintatico(aLexico);
            Token token = null;

            aSintatico.JuntandoGeral();
            System.out.println("Compilation Successful!");
            do {
                token = aLexico.proximoToken();
                if(token != null) {
                    System.out.println(token);
                }
            }while(token != null);

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
