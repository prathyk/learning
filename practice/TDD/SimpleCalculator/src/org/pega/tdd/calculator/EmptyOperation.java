package org.pega.tdd.calculator;

public class EmptyOperation extends BinaryOperation {

	@Override
	public int execute(int lhs, int rhs) {
		return rhs;
	}

	@Override
	public int precedenceValue() {
		return 0;
	}

}
