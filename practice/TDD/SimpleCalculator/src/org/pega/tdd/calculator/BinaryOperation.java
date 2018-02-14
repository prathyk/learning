package org.pega.tdd.calculator;

public abstract class BinaryOperation implements Comparable<BinaryOperation>{

	public abstract int execute(int lhs, int rhs);
	
	public abstract int precedenceValue();

	public static BinaryOperation get(char op) {
		switch (op) {
		case '+':
			return new Add();
		case '-':
			return new Subtract();
		case '*':
			return new Multiply();
		default:
			return new EmptyOperation();
		}
	}

	public int compareTo(BinaryOperation op) {
		return Integer.compare(op.precedenceValue(), precedenceValue());
	}

}