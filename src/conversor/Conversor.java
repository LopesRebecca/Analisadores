package conversor;

import java.util.Stack;

public class Conversor {

	private int clause = 0;

	public Conversor() {}

	public void getClauses(String formula) {

		System.out.print(formula+ "\n");
		
		String[] clauseForm = formula.split("[\\(||\\)]");
		
		for(int i =0; i < clauseForm.length;i++) {
			System.out.print(clauseForm[i]);
		}

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