import LexicalAnalyzer.LexicalAnalyzer;
import Parser.Parser;
import Exeptions.ExceptionConverter;
import Exeptions.ExceptionPaser;
import Exeptions.ExceptionLexical;
import Converter.Converter;

import java.util.Scanner;

public class App {


    public static void main(String[] args) {

        int choice = 0;
        Scanner input = new Scanner(System.in);

        do{
            System.out.println("Sistema de Analise de formula proposicional e Conversor de qualquer formla proposicional em " +
                    "uma equivalente na forma normal conjuntiva");

            System.out.print("##--Teste Estrutura de Menu--##\n\n");
            System.out.print("|------------------------------|\n");
            System.out.print("| Escolha 1 - Verificar Formula|\n");
            System.out.print("| Escolha 2 - Converter Formula|\n");
            System.out.print("| Escolha 3 - Sair             |\n");
            System.out.print("|------------------------------|\n");
            System.out.print("Digite um numero equivalente a sua escolha: ");

            choice = input.nextInt();

            switch (choice){
                case 1:
                    try{
                        //instaciando os analisadores
                        LexicalAnalyzer aLexico = new LexicalAnalyzer("src/input.txt");
                        Parser aSintatico = new Parser(aLexico);

                        //chamando a function
                        aSintatico.checker();

                        System.out.println("\n\nAnalise foi um sucesso!\n");

                    }catch (ExceptionLexical e) {
                        System.out.println("Erro Lexico: " +e.getMessage());
                        e.printStackTrace();
                    }catch (ExceptionPaser e) {
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
                        LexicalAnalyzer aLexico = new LexicalAnalyzer("src/input.txt");
                        Parser aSintatico = new Parser(aLexico);

                        //chamando a function
                        aSintatico.checker();

                        System.out.println("\n\n Iniciando equilavencia de formula");

                        Converter conversor = new Converter();
                        conversor.toConvert("src/input.txt");

                    }catch (ExceptionLexical e) {
                        System.out.println("Erro Lexico: " +e.getMessage());
                        e.printStackTrace();
                    }catch (ExceptionPaser e) {
                        System.out.println("\n Erro Sintatico: " +e.getMessage());
                        e.printStackTrace();
                    }catch (ExceptionConverter e){
                        System.out.println("Erro de equivalencia " + e.getMessage());
                        e.printStackTrace();
                    }catch (Exception e) {
                        System.out.println("Erro generico!!");
                        System.out.println(e.getClass().getName());
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Numero invalido!");
                    break;
            }
        }while (choice != 3);


    }
}