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
					formula[i-1] = chageType(auxiliar);
				}else {
					formula[i] = chageType(auxiliar);
				}
		}
		formula[0] = formula[0].replace("--", "");
		
		System.out.print("\n\n"+formula[0]+"\n\n\n");
	}
	
	private String chageType(String clause) {

		Pattern implica = Pattern.compile("([a-z][>][a-z]){1}");

		Matcher i = implica.matcher(clause);

		String result = clause;
		String aux;
		int start,end;
		
		if (i.lookingAt()) {
			start = i.start();
			end = i.end();
			
			aux = result.substring(start,end);
			result = result.substring(0,start) + "(-" + aux.replaceAll("\\>", "#") + ")" + result.substring(end,result.length());
		}
		
		return result;
	}
	
	public String move(String clause) {

		Pattern disjuncao = Pattern.compile("([-][a-z][#][a-z]){1}");
		Pattern conjucao = Pattern.compile("([-][a-z][&][a-z]){1}");
		
		Matcher d = disjuncao.matcher(clause);
		Matcher c = conjucao.matcher(clause);
		
		String result = clause;
		String aux;
		int start,end;
		
		if(d.lookingAt()) {
			//-x#y = (-x & -y)
			start = d.start();
			end = d.end();

			aux = result.substring(start,end);
			result = result.substring(0,start) + "(-" + aux.replaceAll("\\#", "& -") + ")" + result.substring(end,result.length());
		}
		else if (c.lookingAt()) {
			//-x&y = (-x # -y)
			start = c.start();
			end = c.end();

			aux = result.substring(start,end);
			result = result.substring(0,start) + "(-" + aux.replaceAll("\\&", "#" + ")" ) + result.substring(end,result.length());

		}
		
		
		return null;
	}
	
	public String doubleNegation(String clause) {
		Pattern negacao = Pattern.compile("([-][-][a-z]){1}");

		Matcher n = negacao.matcher(clause);
		
		String result = clause;
		String aux;
		int start,end;
		
		if (n.lookingAt()) {
			//--x  = x
			//System.out.println(i.matches());
			start = n.start();
			end = n.end();

			aux = result.substring(start,end);
			result = result.substring(0,start) + "(-" + aux.replaceAll("\\-\\-", " ") + ")" + result.substring(end,result.length());
			aux =  "(" +  clause.replaceAll("\\-\\-", " ") + ")";
		}
		
		return null;
	}
	
	public String distribute(String clause) {
		Pattern morgan = Pattern.compile("([a-z][#][a-z][&][a-z]){1}");
		
		Matcher m = morgan.matcher(clause);

		String result = clause;
		String aux;
		int start,end;
		
		if (m.lookingAt()) {
			//x # y & z
			// x # y & x # y
			start = m.start();
			end = m.end();

			aux = result.substring(start,end);
			result = result.substring(0,start) + "(-" + aux.replaceAll("\\&", ") & ( x ") + ")" + result.substring(end,result.length());
			//result =  "(" +  clause.replaceAll("\\&", " )&(x #")+ ")";
			//capturar letra na conversão
			//String b = a + "(-" +  auxiliar.replaceAll("\\&", "#")+ ")";
		}
		
		return null;
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
	x # (y & z) = (x # y) & (x # y)
	 */
}