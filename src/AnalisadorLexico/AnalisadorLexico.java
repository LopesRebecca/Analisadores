package AnalisadorLexico;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import Exeptions.ExpectionLexico;

public class AnalisadorLexico {

	private char[] formula;
	private int estado;
	private int posicao;

	public AnalisadorLexico() {
	}

	public AnalisadorLexico(char[] formula, int estado, int pos) {
		this.formula = formula;
		this.estado = estado;
		this.posicao = pos;
	}

	public char[] getFormula() {
		return formula;
	}

	public void setFormula(char[] formula) {
		this.formula = formula;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public AnalisadorLexico(String filename) {
		try {
			// ler cada byte do arquivo
			String txtformula;
			txtformula = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
			System.out.println(txtformula);
			formula = txtformula.toCharArray();
			posicao = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// analisar a formula e definir tokens
	public Token proximoToken() {
		char formulaDaVez;
		Token token;
		String termo = " ";
		
		if (finalDoArquivo()) {
			return null;
		}
		
		formulaDaVez = proximo();

		token = new Token();

			if (letra(formulaDaVez)) {
				termo += formulaDaVez;
				token.setValor(formulaDaVez);
				token.setTipo(Token.TK_LETRA);
				token.setTexto(termo);
				return token;
				
			} else if (espacamento(formulaDaVez)) {
				termo += formulaDaVez;
				token.setValor(formulaDaVez);
				token.setTipo(Token.TK_SPACE);
				token.setTexto(termo);
				
				return token;
				
			} else if (operadores(formulaDaVez)) {
				termo += formulaDaVez;
				token.setValor(formulaDaVez);
				token.setTipo(Token.TK_OPERATION);
				token.setTexto(termo);
				
				return token;				
			} else if (negacao(formulaDaVez)) {
				estado = 4;
				token.setValor(formulaDaVez);
				token.setTipo(Token.TK_NEGATION);
				token.setTexto(termo);

				return token;
			} else if (auxiliares(formulaDaVez)) {
				estado = 5;
				token.setValor(formulaDaVez);
				token.setTipo(Token.TK_PARENTHESIS);
				token.setTexto(termo);

				return token;	
			} else {
				throw new ExpectionLexico("Erro simbolo incorreto");
			}
		}

	// definir os negocinhos da formula
	private boolean letra(char c) {
		return (c >= 'a' && c <= 'z');
	}

	private boolean operadores(char c) {
		return c == '#' || c == '&' || c == '>';
	}
	
	private boolean negacao(char c) {
		return c =='-';
	}
	
	private boolean espacamento(char c) {
		return c == '\n' || c == '\t' || c == ' ' || c == '\r';
	}

	private boolean auxiliares(char c) {
		return c == ')' || c == '(';
	}
	// negocinhos definidos

	private char proximo() {
		return formula[posicao++];
	}

	private boolean finalDoArquivo() {
		return posicao == formula.length;
	}

	// volta pra tras!
	private void back() {
		posicao--;
	}
}
