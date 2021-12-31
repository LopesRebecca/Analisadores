import AnalisadorLexico.analisador.AnalisadorLexico;
import AnalisadorLexico.execeçoes.ExpectionLexico;
import AnalisadorLexico.analisador.Token;

public class App {
    public static void main(String[] args) {
        try{
            AnalisadorLexico al = new AnalisadorLexico("input.txt");
            Token token = null;

            do{
                token = al.proximoToken();
                if(token!=null){
                    System.out.println(token);
                }
            }while (token!=null);
        }catch (ExpectionLexico e){
            System.out.println("Analisador Lexico com erro:" + e.getMessage());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
