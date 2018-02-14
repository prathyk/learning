package xTreme;

public class AcceptNoOfParties extends State {

	public AcceptNoOfParties(Scorecard data) {
		super(data);
	}

	@Override
	protected boolean acceptable(String input) {
		return input.trim().matches("\\d+\\s+\\d+");
	}

	@Override
	protected void acceptInput(String input) {
		String[] tokens = input.trim().split("\\s+");
		scoreCard.setNoOfSupporters(Integer.parseInt(tokens[0]));
		scoreCard.setNoOfReporters(Integer.parseInt(tokens[1]));
	}

	@Override
	protected State nextState() {
		if(scoreCard.getNoOfSupporters()>0){
			return new AcceptSupporter(scoreCard);
		}else{
			return new AcceptReporter(scoreCard);
		}
	}

	@Override
	protected String validInputFormat() {
		return "NoOfSupporters NoOfReporters";
	}

}
