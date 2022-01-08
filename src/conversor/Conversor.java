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

	public void translate(String[] formula) {
		String auxiliar = "";

		for(int i = formula.length-1; i > -1; i--) {
				auxiliar = formula[i].replaceAll("\\s","");
				auxiliar = auxiliar.replaceAll("[()]","");
						
				if(!(i == 0)) {
					formula[i-1] += chageType(auxiliar);
				}
		}
		formula[0] = formula[0].replace("--", "");
		
		System.out.print("\n\n"+formula[0]+"\n\n\n");
	}
	private String chageType(String auxiliar) {


		Pattern implica = Pattern.compile("[^[a-z][>][a-z]]");
		Pattern disjuncao = Pattern.compile("[^[-][a-z][#][a-z]]");
		Pattern conjucao = Pattern.compile("[^[-][a-z][&][a-z]]");
		Pattern negacao = Pattern.compile("[^[-][-][a-z]]");
		Pattern morgan = Pattern.compile("[^[a-z][#][a-z][&][a-z]]");

		Matcher i = implica.matcher(auxiliar);
		Matcher d = disjuncao.matcher(auxiliar);
		Matcher c = conjucao.matcher(auxiliar);
		Matcher n = negacao.matcher(auxiliar);
		Matcher m = morgan.matcher(auxiliar);

		String a = auxiliar;
		
		if (i.lookingAt()) {
			//(x > y)  = (-x # y)
			a =  "(-" +  auxiliar.replaceAll("\\>", "#")+ ")";
			//out.println(nova);
		}
		else if(d.lookingAt()) {
			//-x#y = (-x & -y)
			a =  "(-" +  auxiliar.replaceAll("\\#", "& -") + ")";
			out.println(d);
		}
		else if (c.lookingAt()) {
			//-x&y = (-x # -y)
			a =  "(" +  auxiliar.replaceAll("\\&", "#") + ")";
		}
		else if (n.lookingAt()) {
			//--x  = x
			//System.out.println(i.matches());
			a =  "(" +  auxiliar.replaceAll("\\-\\-", " ") + ")";
		}
		else if (m.lookingAt()) {
			//x # y & z
			// x # y & x # y
			a =  "(" +  auxiliar.replaceAll("\\&", " )&(x #")+ ")";
			//String b = a + "(-" +  auxiliar.replaceAll("\\&", "#")+ ")";
		}
		return a;
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