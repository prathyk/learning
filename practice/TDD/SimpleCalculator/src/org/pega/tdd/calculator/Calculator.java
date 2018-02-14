package org.pega.tdd.calculator;

import java.util.Stack;


public class Calculator {
	
	Stack<Integer> values = new Stack<Integer>();
	Stack<BinaryOperation> operators = new Stack<BinaryOperation>();
	
	public int answer() {
		while(!operators.isEmpty()){
			int result = performOperation();
			values.push(result);
		}
		return (values.isEmpty()) ? 0 : values.pop();
	}

	private int performOperation() {
		BinaryOperation op = (operators.isEmpty())? BinaryOperation.get('z') :operators.pop();
		int rhs = (values.isEmpty())? 0 : values.pop();
		int lhs = (values.isEmpty())?0:values.pop();
		
		BinaryOperation nextOp = (operators.isEmpty())? null :operators.peek();
		if(nextOp != null && nextOp.compareTo(op) > 0){
			values.push(lhs);
			lhs = performOperation();
		}
		int result = op.execute(lhs, rhs);
		return result;
	}

	public void enterValue(int number) {
		values.push(number);
	}

	public void enterOperator(char operator) {
		operators.push(BinaryOperation.get(operator));
	}

}
