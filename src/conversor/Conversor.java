package conversor;


import java.util.Stack;

public class Conversor {
	
	private int clause = 0;	

	public Conversor() {
		
	}

	public void getClauses(String formula) {
		
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
		String[] clauseForm = new String[clause];
		clauseForm = notNullString(clauseForm, clause);
		
		int indexClause =0;

		Stack<Character> stackClause = new Stack<Character>();
		
		for (char element : formula.toCharArray()){
			clauseForm[indexClause] += element;
			
			if(element == '(') {
				stackClause.push(element);
			}else {
				if (element == ')'){
					char close = (Character) stackClause.peek();
					if (element==')' && close == '('){
						stackClause.pop();
						if (stackClause.isEmpty()){
							indexClause++;							
						}
					}
				}
			}
		}
		stackClause.isEmpty();
	}

	public int getClause() {
		return clause;
	}

	public void setClause(int clause) {
		this.clause = clause;
	}
	
	public String[] notNullString(String[] list,int lenght) {
		for(int i = 0; i < lenght; i++) {
			list[i] = "";
		}
		return list;
	}
}
