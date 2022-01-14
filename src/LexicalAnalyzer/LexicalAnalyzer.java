package LexicalAnalyzer;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import Exeptions.ExceptionLexical;

public class LexicalAnalyzer {

	private char[] formula;
	private int state;
	private int position;

	public LexicalAnalyzer() {
	}

	public LexicalAnalyzer(char[] formula, int estado, int pos) {
		this.formula = formula;
		this.state = estado;
		this.position = pos;
	}

	public char[] getFormula() {
		return formula;
	}

	public void setFormula(char[] formula) {
		this.formula = formula;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public LexicalAnalyzer(String filename) {
		try {
			// ler cada byte do arquivo
			String txtformula;
			txtformula = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
			System.out.println(txtformula);
			formula = txtformula.toCharArray();
			position = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// analisar a formula e definir tokens
	public Token nextToken() {
		char currentForm;
		Token token;
		String termo = " ";

		if (endOfFile()) {
			return null;
		}

		currentForm = next();

		token = new Token();

		if (letter(currentForm)) {
			termo += currentForm;
			token.setValue(currentForm);
			token.setType(Token.TK_LETTER);
			token.setText(termo);
			return token;

		} else if (spacing(currentForm)) {
			termo += currentForm;
			token.setValue(currentForm);
			token.setType(Token.TK_SPACE);
			token.setText(termo);

			return token;

		} else if (operators(currentForm)) {
			termo += currentForm;
			token.setValue(currentForm);
			token.setType(Token.TK_OPERATION);
			token.setText(termo);

			return token;
		} else if (negative(currentForm)) {
			state = 4;
			token.setValue(currentForm);
			token.setType(Token.TK_NEGATION);
			token.setText(termo);

			return token;
		} else if (auxiliaries(currentForm)) {
			state = 5;
			token.setValue(currentForm);
			token.setType(Token.TK_PARENTHESIS);
			token.setText(termo);

			return token;
		} else {
			throw new ExceptionLexical("Erro simbolo incorreto");
		}
	}

	// definir os negocinhos da formula
	private boolean letter(char c) {
		return (c >= 'a' && c <= 'z');
	}

	private boolean operators(char c) {
		return c == '#' || c == '&' || c == '>';
	}

	private boolean negative(char c) {
		return c =='-';
	}

	private boolean spacing(char c) {
		return c == '\n' || c == '\t' || c == ' ' || c == '\r';
	}

	private boolean auxiliaries(char c) {
		return c == ')' || c == '(';
	}
	// negocinhos definidos

	private char next() {
		return formula[position++];
	}

	private boolean endOfFile() {
		return position == formula.length;
	}

	// volta pra tras!
	private void back() {
		position--;
	}
}
