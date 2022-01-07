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

        do{
            //falando o que é
            System.out.println("Sistema de Analise de fórmula proposicional e Conversor de qualquer formla proposicional em " +
                    "uma equivalente na forma normal conjuntiva");

            System.out.print("##--Teste Estrutura de Menu--##\n\n");
            System.out.print("|-----------------------------|\n");
            System.out.print("| Opção 1 - Verificar Formula |\n");
            System.out.print("| Opção 2 - Converter Formula |\n");
            System.out.print("| Opção 3 - Sair              |\n");
            System.out.print("|-----------------------------|\n");
            System.out.print("Digite um numero equivalente a sua escolha: ");

            escolha = input.nextInt(); // gravando a escolha

            switch (escolha){
                case 1:
                    try{
                        //instaciando os analisadores
                        AnalisadorLexico aLexico = new AnalisadorLexico("src/input.txt");
                        AnalisadorSintatico aSintatico = new AnalisadorSintatico(aLexico);

                        //chamando a função
                        aSintatico.verificador();

                        System.out.println("\nAnalise foi um sucesso!");

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

                        /*
                        //instaciando os analisadores
                        AnalisadorLexico aLexico = new AnalisadorLexico("src/input.txt");
                        AnalisadorSintatico aSintatico = new AnalisadorSintatico(aLexico);
                        Conversor conversor = new Conversor();

                        //chamando a função
                        aSintatico.verificador();

                        System.out.println("\nAnalise de formula completa!");

                         */
                        System.out.println("\n\n Iniciando equilavencia de formula");

                        Conversor conversor = new Conversor();
                        conversor.converter("src/input.txt");

                    }catch (ExceptionLexico e) {
                        System.out.println("Erro Lexico: " +e.getMessage());
                        e.printStackTrace();
                    }catch (ExceptionSintatico e) {
                        System.out.println("\n Erro Sintatico: " +e.getMessage());
                        e.printStackTrace();
                    }catch (ExceptionConversor e){
                        System.out.println("Erro de equivalencia " + e.getMessage());
                        e.printStackTrace();
                    }catch (Exception e) {
                        System.out.println("Erro generico!!");
                        System.out.println(e.getClass().getName());
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    System.out.println("Só passamos raiva nessa merda!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Numero invalido!");
                    break;
            }
        }while (escolha != 3);




    }
}