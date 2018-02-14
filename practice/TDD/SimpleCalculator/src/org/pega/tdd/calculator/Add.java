package org.pega.tdd.calculator;

public class Add extends BinaryOperation {

	@Override
	public int execute(int lhs, int rhs) {
		return lhs + rhs;
	}

	@Override
	public int precedenceValue() {
		return 2;
	}

}
