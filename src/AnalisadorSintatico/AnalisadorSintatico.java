package AnalisadorSintatico;

import Exeptions.ExceptionSintatico;

import java.util.Stack;
import java.io.File;


import AnalisadorLexico.AnalisadorLexico;
import AnalisadorLexico.Token;


public class AnalisadorSintatico {

	private AnalisadorLexico aLexico;
	private Token token;
	private Token tokenAnterior;
//	private Token tokenProximo;
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
				break;
			case 4:
				espaco();
				verificarToken();
				break;
			default:
				throw new ExceptionSintatico("Formula invalida");
			}
		}else {
			verificarParenteses();
			verificarOperadores();
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
	//acabando de julgando e quendo ir pro ceu

	//verificar parentese
	public void verificarParenteses() {
		char[] listaParentese = aLexico.getFormula();
				
		String formula ="";
		
		for( char c : listaParentese) {
			if(c == '(' || c == ')') {
				formula += c;
			}
		}

		Stack<Character> parenteses = new Stack<Character>();
		
		for (char verificador : formula.toCharArray()) {

			if(verificador == '(') {
				parenteses.push(verificador);
			}else {
				if (parenteses.isEmpty()) {
					throw new ExceptionSintatico("Parentese invalido");
				}else {
					char fechamento = (Character) parenteses.peek();
					
					if (verificador == ')' && fechamento == '(') {
						parenteses.pop();
					} else {
						throw new ExceptionSintatico("Parentese invalido");
					}
				}
			}
		}if(parenteses.isEmpty()) {
			
		}else {
			throw new ExceptionSintatico("Parentese invalido");
		}
	}

	//verificar seguencia de operadores e letras
	public void verificarOperadores() {
		char[] listaParentese = aLexico.getFormula(); //pega formula
		
		String formula ="";
		
		for(char letra : listaParentese) { //percorre a formula
			if(caracter(letra)) {          // letra -> variavel da formula da vez
				formula += letra;
			}
			if(operadores(letra)){
				formula += letra;
			}
		}
		
		int operador =1;
		int letra = 0;
		
		for(char verificador : formula.toCharArray()) {
	
			if(caracter(verificador)) {
				letra++;
			}
			else {
				operador++;
			}
		}
		if(!(letra%operador == 0)) {
			throw new ExceptionSintatico("Sintaxe do operador invalida");
		}
			

	}

	//verificar negação
	public void vericadorNegacao(){
		char [] expressao = aLexico.getFormula();
		String formula = "";

		for(char letra : expressao) {
			if(caracter(letra)) {
				formula += letra;
			}
			if(operadores(letra)){
				formula += letra;
			}
			if(negacao(letra)){
				formula += letra;
			}
		}

		char[] expressao1 = formula.toCharArray();

		for(int pos = 1; pos < expressao1.length; pos++){
			if(negacao(expressao1[pos]) && operadores(expressao1[pos+1])){
				throw new ExceptionSintatico("Sintaxe da negação invalida");
			}
		}

	}

	//metodos dos negocinhos da formula
	boolean caracter(char c) {
		return (c >= 'a' && c <= 'z');
	}

	boolean operadores(char c) {
		return (c == '#' || c == '&' || c == '>');
	}

	private boolean negacao(char c) {
		return c =='-';
	}

}

