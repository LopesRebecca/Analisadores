package Converter;

import java.nio.charset.StandardCharsets;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Converter {

	private int clause = 0;
	private String formula;
	
	@SuppressWarnings("unused")
	private String formulaConvertida;

	public Converter() {}

	public void toConvert(String filename) {

		try{
			this.formula = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
			System.out.println(formula);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		formulaConvertida = "";
//
//		Stack<Character> stack = new Stack<Character>();
//
//		for (char element : formula.toCharArray()) {
//			if(element == '(') {
//				stack.push(element);
//			}else {
//				if(element ==')') {
//					char close = (Character) stack.peek();
//					if(element == ')' && close == '('){
//						stack.pop();
//						this.clause++;
//					}					
//				}
//			}
//		}
		translate(this.formula);
	}

	public void runOrNot(String formula){
		for (char element: formula.toCharArray()) {
			if(element == '>'){
				translate(formula);
			}else{
				System.exit(0);
			}
		}
	}

	//juntando os passos a passos
	public void translate(String formula) {
		String auxiliar = formula;
		auxiliar = auxiliar.replaceAll("\\s","");
		

			auxiliar = chageType(auxiliar);
			auxiliar = move(auxiliar);
			auxiliar = doubleNegation(auxiliar);
			auxiliar = distribute(auxiliar);

		System.out.print("\n\n"+auxiliar+"\n\n\n");
	}

	//removendo a implica��o
	private String chageType(String clause) {
			
		Pattern implica = Pattern.compile("([a-z][>][a-z]){1}");
		Matcher i = implica.matcher(clause);

		String result = clause;
		String aux;
		int start,end;
//		(x > y)     = (-x # y)
		while(i.find()) {
			start = i.start();
			end = i.end();
			
			aux = result.substring(start,end);
			result = result.substring(0,start) + "-" + result.substring(start,start+1) + "#" + result.substring(end-1,end)
			+ result.substring(end,result.length());
			result = move(result);
			result = doubleNegation(result);
			result = distribute(result);
			
			i= implica.matcher(result);
		}
		
		return result;
	}

	//internalizando as negações
	public String move(String clause) {
		Pattern disjuncao = Pattern.compile("([-][(][a-z][#][a-z][)]){1}");
		Pattern conjucao = Pattern.compile("([-][(][a-z][&][a-z][)]){1}");
		
		Matcher d = disjuncao.matcher(clause);
		Matcher c = conjucao.matcher(clause);
		
		String result = clause;
		String aux;
		int start,end;
		
		while(d.find()) {
			start = d.start();
			end = d.end();
//			-(x#y)    = (-x&-y) 
			aux = result.substring(start,end);
			result = result.substring(0,start) + "(-" + result.substring(start+2, start+3) + "&-" + result.substring(end-2, end)+  result.substring(end,result.length());
			result = doubleNegation(result);
			result = distribute(result);
			
			d= disjuncao.matcher(result);
		}
		while(c.find()) {
			start = c.start();
			end = c.end();
//			-(x & y)    = (-x # -y)
			aux = result.substring(start,end);
			result = result.substring(0,start) + "(-" + result.substring(start+2, start+3) + "#-" + result.substring(end-2, end)+  result.substring(end,result.length());
			result = doubleNegation(result);
			result = distribute(result);
			
			c= conjucao.matcher(result);
		}
		
		
		return result;
	}

	//eleminando as duplas negações

	public String doubleNegation(String clause) {
		Pattern negacao = Pattern.compile("([(]?[-][(]?[-][a-z]){1}");

		Matcher n = negacao.matcher(clause);

		String result = clause;
		String aux;
		int start,end;
		
		if (n.find()) {
			start = n.start();
			end = n.end();

			aux = result.substring(start,end);
			result = ("(" + result.substring(0,start) + result.substring(end-1, end) + result.substring(end,result.length()) + ")" );
			result = distribute(result);
			
			n= negacao.matcher(result);
		}
		
		return result;
	}

	//distribuitiva
	public String distribute(String clause) {
		Pattern morgan = Pattern.compile("([a-z][#][(]?[a-z][&][a-z]){1}");
//			x # (y & z) = (y # x) & (z # x)
//		 "x#y&z" = (x#y)&(x#z)
		Matcher m = morgan.matcher(clause);

		String result = clause;
		String aux;
		int start,end;
		
		while(m.find()) {
			start = m.start();
			end = m.end();

			aux = result.substring(start,end);
			result = result.substring(0,start) + "(" + result.substring(start, start+2) + result.substring(end-3,end-2) + ")&(" + 
					result.substring(start,start+2) + result.substring(end-1,end)+ ")" +  result.substring(end,result.length());
		}
//		-(a#b)&(a#c
		return result;
	}
	
	public String[] notNullString(String[] list) {
		for (int i = 0; i < list.length; i++) {
			list[i] = "";
		}
		return list;
	}

	public boolean isEmpty(String[] list) {
		for (int i = 0; i < list.length; i++) {
			if (list[i] == "") {
				return true;
			}
		}
		return false;
	}

	public int getClause() {
		return clause;
	}

	public void setClause(int clause) {
		this.clause = clause;
	}
	
	/*
	(x > y)     = (-x # y)
	-(x # y)    = (-x & -y) 
	-(x & y)    = (-x # -y)
	--x         = x
	x # (y & z) = (y # x) & (z # x)
	 */
}