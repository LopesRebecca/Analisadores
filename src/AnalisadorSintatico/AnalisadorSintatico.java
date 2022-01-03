package AnalisadorSintatico;

import Exeptions.ExceptionSintatico;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import AnalisadorLexico.AnalisadorLexico;
import AnalisadorLexico.Token;

public class AnalisadorSintatico {

	private AnalisadorLexico aLexico;
	private static Token token;
	private Token tokenAnterior;
	private Token tokenProximo;
//	private String formula;

	public AnalisadorSintatico(AnalisadorLexico aLexico) {
		this.aLexico = aLexico;
	}

	public void juntandoGeral() {
		primeiro();
		verificarToken();
	}
	
	public void primeiro() {
		token = aLexico.proximoToken();
		System.out.print(token);
		if (token.getTipo() != Token.TK_LETRA && token.getTipo() != Token.TK_NEGATION && token.getTipo() != Token.TK_PARENTHESIS
				&& token.getTipo() != Token.TK_SPACE)
			throw new ExceptionSintatico("Sintaxe invalida, encontrada: " + token.getTexto() + "\n do tipo:" + token.getTipo());
		
		tokenAnterior = token;
	}

	// verificar se e o ultimo token
	public void verificarToken() {
		token = aLexico.proximoToken();
		System.out.print(token);
		if (token != null) {

			switch (tokenAnterior.getTipo()) {
			case 0:
				Letra();
				verificarToken();
				break;
			case 1:
				operador();
				verificarToken();
				break;
			case 2:
				negacao();
				verificarToken();
				break;
			case 3:
				parenteses();
				verificarToken();
				verificarParenteses();
				break;
			case 4:
				espaco();
				verificarToken();
				break;
			default:
				throw new ExceptionSintatico("Formula invalida");
			}

		}
	}

//Julgando a identidade
	public void Letra() {
		if (token.getTipo() != Token.TK_NEGATION && token.getTipo() != Token.TK_OPERATION
			&& token.getTipo() != Token.TK_PARENTHESIS && token.getTipo() != Token.TK_SPACE){
			throw new ExceptionSintatico("Sintaxe invalida, encontrada: " + token.getTexto() + "\n do tipo:" + token.getTipo());
		}
		tokenAnterior = token;
	}

	public void operador() {
		if (token.getTipo() != Token.TK_LETRA && token.getTipo() != Token.TK_PARENTHESIS && token.getTipo() != Token.TK_SPACE) {
			throw new ExceptionSintatico("Sintaxe invalida, encontrada: " + token.getTexto() + "\n do tipo:" + token.getTipo());
		}
		tokenAnterior = token;	
	}
	
	public void negacao() {
		if (token.getTipo() != Token.TK_LETRA && token.getTipo() != Token.TK_PARENTHESIS && token.getTipo() != Token.TK_NEGATION){
			throw new ExceptionSintatico("Sintaxe invalida, encontrada: " + token.getTexto() + "\n do tipo:" + token.getTipo());
		}
		tokenAnterior = token;
	}
	public void parenteses() {
		if (token.getTipo() != Token.TK_LETRA && token.getTipo() != Token.TK_PARENTHESIS && token.getTipo() != Token.TK_NEGATION
				&& token.getTipo() != Token.TK_OPERATION && token.getTipo() != Token.TK_SPACE){
			throw new ExceptionSintatico("Sintaxe invalida, encontrada: " + token.getTexto() + "\n do tipo:" + token.getTipo());
		}
		tokenAnterior = token;
	}
	
	public void espaco() {
		if (token.getTipo() != Token.TK_LETRA && token.getTipo() != Token.TK_PARENTHESIS && token.getTipo() != Token.TK_NEGATION
				&& token.getTipo() != Token.TK_OPERATION && token.getTipo() != Token.TK_SPACE){
			throw new ExceptionSintatico("Sintaxe invalida, encontrada: " + token.getTexto() + "\n do tipo:" + token.getTipo());
		}
		tokenAnterior = token;
	}

	/*
	public void proximoElementoOperadores() {
		AnalisadorLexico analisarProximo = new AnalisadorLexico(aLexico.getFormula(),aLexico.getEstado(), aLexico.getPosicao());
		tokenProximo = analisarProximo.proximoToken();

		if(tokenProximo.getTipo() == Token.TK_SPACE){
			proximoElementoOperadores();
			System.out.println("aaaaa");
			if (tokenProximo.getTipo() != Token.TK_LETRA && token.getTipo() != Token.TK_PARENTHESIS && token.getTipo() != Token.TK_SPACE) {
				throw new ExceptionSintatico("Sintaxe invalida, encontrada: " + token.getTexto() + "\n do tipo:" + token.getTipo());
			}
			tokenProximo = token;
		}

	}

	 */




public static void verificarParenteses(){
	Stack<Parentese> s = new Stack<Parentese>();
		int aberto = 0;
		int fechado = 0;
		if ( Token.TK_PARENTHESIS == token.getTipo()){
			if (token.getTexto() == '(' ) { //String.valueOf -> transformar em string
				System.out.println("aaaaaaaa");
				aberto +=1;
			}else{
				fechado+=1;
			}
		}

		if(aberto == fechado){
			System.out.println("parentese certos");
		}else{
			System.out.println("Erro parentese não fechado");
		}
	}


}

