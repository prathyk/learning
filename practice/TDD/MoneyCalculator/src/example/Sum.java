package example;

public class Sum extends Expression {
	
	private Expression leftOperand, rightOperand;

	public Sum(Expression left, Expression right) {
		leftOperand = left;
		rightOperand = right;
	}

	@Override
	public Money reduceTo(String toCurrency) {
		return new Money(leftOperand.reduceTo(toCurrency).getValue()+rightOperand.reduceTo(toCurrency).getValue(), toCurrency);
	}

}
