package xTreme;

public abstract class State {
	
	Scorecard scoreCard;
	
	public State(Scorecard data) {
		this.scoreCard = data;
	}

	
	public State processInput(String input) throws InvalidInput {
		if(acceptable(input)){
			acceptInput(input);
			sendResponse();
			return nextState();
		}
		//else throw
		throw new InvalidInput(validInputFormat());
	}

	protected abstract boolean acceptable(String input);
	protected abstract void acceptInput(String input);
	
	protected void sendResponse() {
		scoreCard.printStream.print("");
	}
	
	protected abstract State nextState();
	protected abstract String validInputFormat();
	
	

}
