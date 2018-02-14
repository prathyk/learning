package org.pega.tdd.calculator;

public class Multiply extends BinaryOperation{

	public int execute(int lhs, int rhs) {
		return lhs * rhs;
	}

	@Override
	public int precedenceValue() {
		return 1;
	}
}
