
public class Calculator {

	private int value;
	private BinaryOperation operator = new NoOp();

	public int answer() {
		return value;
	}

	public void enter(int value) {
		this.value = operator.execute(this.value, value);
	}

	public void enterOperator(char operator) {
		this.operator = BinaryOperation.getOperator(operator);
	}

}
