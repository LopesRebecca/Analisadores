package conversor;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.lang.System.*;

public class Conversor {

	private int clause = 0;
	private String formula;
	private String formulaConvertida;

	public Conversor() {}

	public void converter(String filename) {

		try{
			this.formula = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
			System.out.println(formula);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String[] formulaArray = formula.split("[\\(||\\)]");
		formulaConvertida = "";

		for (int i = 0; i < formulaArray.length; i++) {
			String auxiliar = "";
			if (formulaArray[i].matches("[-]") && formulaArray[i + 1].matches("[-][a-z]")) {
				auxiliar = formulaArray[i].replaceAll("[-]", "[-][-][a-z]");
				out.println(auxiliar);
			}
		}

		translate(formulaArray);
	}

	String translate(String[] formula) {
		String auxiliar = "";

		for(int i = formula.length-1; i > -1; i--) {
			if (i == formula.length-1){
				auxiliar = formula[i].replaceAll("\\s","");
				formula[i] = chageType(auxiliar);
			}else {

			}
		}

		return null;
	}
	private String chageType(String auxiliar) {

		String nova = auxiliar;

		out.println(auxiliar);

		Pattern implica = Pattern.compile("[a-z][>][a-z]");
		Pattern disjuncao = Pattern.compile("[-][a-z][#][a-z]");
		Pattern conjucao = Pattern.compile("[-][a-z][&][a-z]");
		Pattern negacao = Pattern.compile("[-][-][a-z]");
		Pattern morgan = Pattern.compile("[a-z][#][a-z][&][a-z]");

		Matcher i = implica.matcher(auxiliar);
		Matcher d = disjuncao.matcher(auxiliar);
		Matcher c = conjucao.matcher(auxiliar);
		Matcher n = negacao.matcher(auxiliar);
		Matcher m = morgan.matcher(auxiliar);

		/*
		boolean a = i.matches();
		boolean b = d.matches();
		boolean e = c.matches();
		boolean f = m.matches();
		 */

		if (i.matches() != false) {
			//(x > y)  = (-x # y)
			out.println(i.matches());
			String a =  "(-" +  auxiliar.replaceAll("\\>", "#")+ ")";
			out.println(a);
			nova += a;
			//out.println(nova);
		}
		if(d.matches() != false) {
			//-x#y = (-x & -y)
			out.println(i.matches());
			String a =  "(-" +  auxiliar.replaceAll("\\#", "& -") + ")";
			out.println(d);

		}
		if (c.matches() != false) {
			//-x&y = (-x # -y)
			out.println(i.matches());
			String a =  "(" +  auxiliar.replaceAll("\\&", "#") + ")";
			out.println(c);

		}
		if (n.matches() != false) {
			//--x  = x
			//System.out.println(i.matches());
			String a =  "(" +  auxiliar.replaceAll("\\-\\-", " ") + ")";
			out.println(a);
			auxiliar += a;
		}
		if (m.matches() != false) {
			//x # y & z
			// x # y & x # y
			out.println(i.matches());
			String a =  "(" +  auxiliar.replaceAll("\\&", " )&(x #")+ ")";
			out.println(a);
			//String b = a + "(-" +  auxiliar.replaceAll("\\&", "#")+ ")";

		}

		return auxiliar;
	}

	/*
	(x > y)     = (-x # y)
	-(x # y)    = (-x & -y)
	-(x & y)    = (-x # -y)
	--x         = x
	x # (y & z) = (x # y) & (x # y)
	 */


//		Stack<Character> stack = new Stack<Character>();
//
//		for (char element : formula.toCharArray()) {
//			if (element == '(') {
//				stack.push(element);
//			} else {
//				if (element == ')') {
//					char close = (Character) stack.peek();
//					if (element == ')' && close == '(') {
//						stack.pop();
//						this.clause++;
//					}
//				}
//			}
//		}
//		String[] clauseForm = new String[clause];
//		clauseForm = notNullString(clauseForm);
//
//		int indexClause = 0;
//
//		Stack<Character> stackClause = new Stack<Character>();
//
//		for (char element : formula.toCharArray()) {
//			clauseForm[indexClause] += element;
//
//			if (element == '(') {
//				stackClause.push(element);
//			} else {
//				if (element == ')') {
//					char close = (Character) stackClause.peek();
//					if (element == ')' && close == '(') {
//						stackClause.pop();
//						if (stackClause.isEmpty()) {
//							indexClause++;
//						}
//					}
//				}
//			}
//		}
//
//		while (isEmpty(clauseForm)) {
//			for (int i = 0; i < clauseForm.length; i++) {
//				if (haveClauses(clauseForm[i])) {
//				} else {
//					int startIndex = clauseForm[i].indexOf('(', clauseForm[i].indexOf('(') + 1);
//					int endIndex = clauseForm[i].indexOf(')') + 1;
//
//					String removeClause = clauseForm[i].substring(startIndex, endIndex);
//					clauseForm[i + 1] += clauseForm[i].substring(startIndex, endIndex);
//					clauseForm[i] = clauseForm[i].replace(removeClause, "");
//				}
//			}
//		}
//		for (int i = 0; i < clauseForm.length; i++) {
//			System.out.print(clauseForm[i]);
//		}
//
//	public  String[] conversion(String[] list){
//		for(int i = 0; i < list.length; i--){
//			System.out.println("aaa");
//			if(list[i] == ">"){
//				System.out.println("não entra na condição");
//			}
//		}
//		return list;
//	}

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

	public boolean haveClauses(String clause) {
		long count = clause.chars().filter(ocorrencias -> ocorrencias == '(').count();

		if (count > 1) {
			return false;
		} else
			return true;
	}

	public int getClause() {
		return clause;
	}

	public void setClause(int clause) {
		this.clause = clause;
	}
}