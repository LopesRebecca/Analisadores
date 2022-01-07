package AnalisadorSintatico;

import Exeptions.ExceptionSintatico;

import java.util.Iterator;
import java.util.Stack;

import AnalisadorLexico.AnalisadorLexico;
import AnalisadorLexico.Token;

public class AnalisadorSintatico {

	private AnalisadorLexico aLexico;
	private Token token;
	private Token tokenAnterior;
	private Token tokenProximo;
//	private String formula;

	public AnalisadorSintatico(AnalisadorLexico aLexico) {
		this.aLexico = aLexico;
	}

	public void verificador() {
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

	public void verificarOperadores() {
		char[] listaParentese = aLexico.getFormula();

		String formula ="";

		for(char letra : listaParentese) {
			if(caracter(letra)) {
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

	boolean caracter(char c) {
		return (c >= 'a' && c <= 'z');
	}

	boolean operadores(char c) {
		return (c == '#' || c == '&' || c == '>');
	}
public void proximoElemento() {
	AnalisadorLexico analisarProximo = new AnalisadorLexico(aLexico.getFormula(),aLexico.getEstado(), aLexico.getPosicao());		tokenProximo = analisarProximo.proximoToken();
		if(tokenProximo.getTipo() == Token.TK_SPACE){
			proximoElemento();
		}

	}
}

