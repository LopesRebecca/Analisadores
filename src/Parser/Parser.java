package Parser;

import Exeptions.ExceptionPaser;

import java.util.Stack;

import LexicalAnalyzer.LexicalAnalyzer;
import LexicalAnalyzer.Token;

public class Parser {

	private LexicalAnalyzer aLexico;
	private Token token;
	private Token previousToken;
	private Token nextToken;
//	private String formula;

	public Parser(LexicalAnalyzer aLexico) {
		this.aLexico = aLexico;
	}

	public void checker() {
		fist();
		verifyToken();
	}

	public void fist() {
		token = aLexico.nextToken();
		System.out.print(token);
		if (token.getType() != Token.TK_LETTER && token.getType() != Token.TK_NEGATION && token.getType() != Token.TK_PARENTHESIS
				&& token.getType() != Token.TK_SPACE)
			throw new ExceptionPaser("Sintaxe invalida, encontrada: " + token.getText() + "\n do tipo:" + token.getType());

		previousToken = token;
	}

	// verificar se e o ultimo token
	public void verifyToken() {
		System.out.print(token);
		token = aLexico.nextToken();
		if (token != null) {

			switch (previousToken.getType()) {
			case 0:
				letter();
				verifyToken();
				break;
			case 1:
				operator();
				verifyToken();
				break;
			case 2:
				negative();
				verifyToken();
				break;
			case 3:
				parentheses();
				verifyToken();
				break;
			case 4:
				space();
				verifyToken();
				break;
			default:
				throw new ExceptionPaser("Formula invalida");
			}
		}else {
			checkParentheses();
			checkOperators();
		}
	}



//Julgando a identidade
	public void letter() {
		if (token.getType() != Token.TK_NEGATION && token.getType() != Token.TK_OPERATION
			&& token.getType() != Token.TK_PARENTHESIS && token.getType() != Token.TK_SPACE){
			throw new ExceptionPaser("Sintaxe invalida, encontrada: " + token.getText() + "\n do tipo:" + token.getType());
		}
		previousToken = token;
	}

	public void operator() {
		if (token.getType() != Token.TK_LETTER && token.getType() != Token.TK_PARENTHESIS && token.getType() != Token.TK_SPACE) {
			throw new ExceptionPaser("Sintaxe invalida, encontrada: " + token.getText() + "\n do tipo:" + token.getType());
		}
		previousToken = token;
	}

	public void negative() {
		if (token.getType() != Token.TK_LETTER && token.getType() != Token.TK_PARENTHESIS && token.getType() != Token.TK_NEGATION){
			throw new ExceptionPaser("Sintaxe invalida, encontrada: " + token.getText() + "\n do tipo:" + token.getType());
		}
		previousToken = token;
	}
	public void parentheses() {
		if (token.getType() != Token.TK_LETTER && token.getType() != Token.TK_PARENTHESIS && token.getType() != Token.TK_NEGATION
				&& token.getType() != Token.TK_OPERATION && token.getType() != Token.TK_SPACE){
			throw new ExceptionPaser("Sintaxe invalida, encontrada: " + token.getText() + "\n do tipo:" + token.getType());
		}
		previousToken = token;
	}

	public void space() {
		if (token.getType() != Token.TK_LETTER && token.getType() != Token.TK_PARENTHESIS && token.getType() != Token.TK_NEGATION
				&& token.getType() != Token.TK_OPERATION && token.getType() != Token.TK_SPACE){
			throw new ExceptionPaser("Sintaxe invalida, encontrada: " + token.getText() + "\n do tipo:" + token.getType());
		}
		previousToken = token;
	}

	public void checkParentheses() {
		char[] listParentheses = aLexico.getFormula();

		String formula ="";

		for( char c : listParentheses) {
			if(c == '(' || c == ')') {
				formula += c;
			}
		}

		Stack<Character> parentheses = new Stack<Character>();

		for (char checker : formula.toCharArray()) {

			if(checker == '(') {
				parentheses.push(checker);
			}else {
				if (parentheses.isEmpty()) {
					throw new ExceptionPaser("Parentese invalido");
				}else {
					char closure = (Character) parentheses.peek();

					if (checker == ')' && closure == '(') {
						parentheses.pop();

					} else {
						throw new ExceptionPaser("Parentese invalido");
					}
				}
			}
		}if(parentheses.isEmpty()) {

		}else {
			throw new ExceptionPaser("Parentese invalido");
		}
	}

	public void checkOperators() {
		char[] listParentheses = aLexico.getFormula();

		String formula ="";

		for(char letra : listParentheses) {
			if(caracter(letra)) {
				formula += letra;
			}
			if(operators(letra)){
				formula += letra;
			}
		}

		int operator =1;
		int letter = 0;

		for(char checker : formula.toCharArray()) {

			if(caracter(checker)) {
				letter++;
			}
			else {
				operator++;
			}
		}
		if(!(letter%operator == 0)) {
			throw new ExceptionPaser("Sintaxe do operator invalida");
		}


	}

	boolean caracter(char c) {
		return (c >= 'a' && c <= 'z');
	}

	boolean operators(char c) {
		return (c == '#' || c == '&' || c == '>');
	}

	public void nextElement() {
	LexicalAnalyzer analyzeNext = new LexicalAnalyzer(aLexico.getFormula(),aLexico.getState(), aLexico.getPosition());
	nextToken = analyzeNext.nextToken();
		if(nextToken.getType() == Token.TK_SPACE){
			nextElement();
		}
	}
}

