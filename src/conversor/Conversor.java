package conversor;

import java.nio.charset.StandardCharsets;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.lang.System.*;

public class Conversor {

	private int clause = 0;
	private String formula;
	
	@SuppressWarnings("unused")
	private String formulaConvertida;

	public Conversor() {}

	public void converter(String filename) {

		try{
			this.formula = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
			System.out.println(formula);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		formulaConvertida = "";

		Stack<Character> stack = new Stack<Character>();

		for (char element : formula.toCharArray()) {
			if(element == '(') {
				stack.push(element);
			}else {
				if(element ==')') {
					char close = (Character) stack.peek();
					if(element == ')' && close == '('){
						stack.pop();
						this.clause++;
					}					
				}
			}
		}
		translate(this.formula);
	}

	public void translate(String formula) {
		String auxiliar = "";

		for(int i = 0; i <clause+1; i++){
			formula = formula.replaceAll("\\s","");
						
				if((i == 0)) {
					formula = chageType(formula);
					formula = move(formula);
					formula = doubleNegation(formula);
					formula = distribute(formula);
				}else {
					formula = chageType(formula);
					formula = move(formula);
					formula = doubleNegation(formula);
					formula = distribute(formula);
				}
		}
		
		System.out.print("\n\n"+formula+"\n\n\n");
	}
	
	private String chageType(String clause) {

		Pattern implica = Pattern.compile("([a-z][>][a-z]){1}");

		Matcher i = implica.matcher(clause);

		String result = clause;
		String aux;
		int start,end;
		
		if (i.find()) {
			start = i.start();
			end = i.end();
			
			aux = result.substring(start,end);
			result = result.substring(0,start) + "-" + result.replaceAll("\\>", "#") + result.substring(end,result.length());
		}
		return result;
	}
	
	public String move(String clause) {
		Pattern disjuncao = Pattern.compile("([-][(][a-z][#][a-z][)]){1}");
		Pattern conjucao = Pattern.compile("([-][(][a-z][&][a-z][)]){1}");
		
		Matcher d = disjuncao.matcher(clause);
		Matcher c = conjucao.matcher(clause);
		
		String result = clause;
		String aux;
		int start,end;
		
		if(d.find()) {
			start = d.start();
			end = d.end();
//			-(x#y)    = (-x&-y) 
			aux = result.substring(start,end);
			result = result.substring(0,start) + "(-" + result.substring(start+2, start+3) + "&-" + result.substring(end-2, end)+  result.substring(end,result.length());
		}
		else if (c.find()) {
			start = c.start();
			end = c.end();
//			-(x & y)    = (-x # -y)
			aux = result.substring(start,end);
			result = result.substring(0,start) + "(-" + result.substring(start+2, start+3) + "#-" + result.substring(end-2, end)+  result.substring(end,result.length());
		}
		
		
		return result;
	}
	
	public String doubleNegation(String clause) {
		Pattern negacao = Pattern.compile("(([-][-])+[a-z])");

		Matcher n = negacao.matcher(clause);
		
		String result = clause;
		String aux;
		int start,end;
		
		if (n.find()) {
			start = n.start();
			end = n.end();

			aux = result.substring(start,end);
			result = result.substring(0,start) + result.substring(end-1, end) + result.substring(end,result.length());
		}
		
		return result;
	}
	
	public String distribute(String clause) {
		Pattern morgan = Pattern.compile("([a-z][#][(]?[a-z][&][a-z][)]?){1}");
//			x # (y & z) = (y # x) & (z # x)
//		 "x#y&z" = (x#y)&(x#z)
		Matcher m = morgan.matcher(clause);

		String result = clause;
		String aux;
		int start,end;
		
		if (m.find()) {
			start = m.start();
			end = m.end();

			aux = result.substring(start,end);
			result = result.substring(0,start) + "(" + result.substring(start, start+2) + result.substring(start+3, start+4) + ")&(" + 
					result.substring(start,start+2) + result.substring(end-2,end) +  result.substring(end,result.length());
		}
		
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