public abstract class BinaryOperation {
	
	public static BinaryOperation getOperator(char operator){
		BinaryOperation op;
		switch (operator) {
		case '+':
			op = new Add();
			break;
		case '-':
			op = new Subtract();
			break;
		case '*':
			op = new Multiply();
			break;
		default:
			op = new NoOp();
			break;
		}
		return op;
	}

	public abstract int execute(int left, int right);

}