import AnalisadorLexico.AnalisadorLexico;
import AnalisadorSintatico.AnalisadorSintatico;
import Exeptions.ExceptionConversor;
import Exeptions.ExceptionSintatico;
import Exeptions.ExceptionLexico;
import conversor.Conversor;

import java.util.Scanner;

public class App {


    public static void main(String[] args) {

        int escolha = 0;
        Scanner input = new Scanner(System.in);

        //falando o que é
        System.out.println("Sistema de Verificação de fórmula proposicional e Conversor de qualquer formla proposicional em " +
                "uma equivalente na forma normal conjuntiva  ");

        //deixando escolher
        System.out.println("Digite 1 para Verificar Formula" +
                "\nDigite 2 para Coverter Formula");

        escolha = input.nextInt(); // gravando a escolha


        switch (escolha){
            case 1:
                try{
                    //instaciando os analisadores
                    AnalisadorLexico aLexico = new AnalisadorLexico("src/input.txt");
                    AnalisadorSintatico aSintatico = new AnalisadorSintatico(aLexico);

                    //chamando a função
                    aSintatico.verificador();

                    System.out.println("\nVerifição foi um sucesso!");

                }catch (ExceptionLexico e) {
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
                break;

            case 2:
                try{

                    //instaciando os analisadores
                    AnalisadorLexico aLexico = new AnalisadorLexico("src/input.txt");
                    AnalisadorSintatico aSintatico = new AnalisadorSintatico(aLexico);
                    Conversor conversor = new Conversor();

                    //chamando a função
                    aSintatico.verificador();

                    System.out.println("\nVerificação de formula completa!");
                    System.out.println("\n\n Iniciando conversão");

                    conversor.getClauses("(a # b) & (a #(a # b))");

                }catch (ExceptionLexico e) {
                    System.out.println("Erro Lexico: " +e.getMessage());
                    e.printStackTrace();
                }catch (ExceptionSintatico e) {
                    System.out.println("\n Erro Sintatico: " +e.getMessage());
                    e.printStackTrace();
                }catch (ExceptionConversor e){
                    System.out.println("Erro de conversão " + e.getMessage());
                    e.printStackTrace();
                }catch (Exception e) {
                    System.out.println("Erro generico!!");
                    System.out.println(e.getClass().getName());
                    e.printStackTrace();
                }
                break;


        }

    }
}