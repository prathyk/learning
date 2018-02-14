
public class NoOp extends BinaryOperation {

	@Override
	public int execute(int left, int right) {
		return right;
	}

}
