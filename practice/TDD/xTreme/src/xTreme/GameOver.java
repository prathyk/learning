package xTreme;

public class GameOver extends State {

	public GameOver(Scorecard data) {
		super(data);
	}

	@Override
	protected boolean acceptable(String input) {
		return true;
	}

	@Override
	protected void acceptInput(String input) {
	}

	@Override
	protected State nextState() {
		return this;
	}

	@Override
	protected String validInputFormat() {
		return "";
	}

}
