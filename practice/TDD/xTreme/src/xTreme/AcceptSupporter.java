package xTreme;

public class AcceptSupporter extends State {

	public AcceptSupporter(Scorecard data) {
		super(data);
	}

	@Override
	protected boolean acceptable(String input) {
		if(input.trim().matches("\\w+,\\s+\\w+")){
			String[] tokens = input.trim().split(",\\s+");
			if(scoreCard.isValidTeam(tokens[1])){
				return true;
			}
		}
		return false;
	}

	@Override
	protected void acceptInput(String input) {
		String[] tokens = input.trim().split(",\\s+");
		scoreCard.addSupporter(tokens[1], tokens[0]);
	}

	@Override
	protected State nextState() {
		if(scoreCard.getNoOfSupporters()>scoreCard.countActualSupporters()){
			return this;
		}else if(scoreCard.getNoOfReporters()>0){
			return new AcceptReporter(scoreCard);
		}else{
			return new AcceptGoals(scoreCard);
		}
	}

	@Override
	protected String validInputFormat() {
		return "SupporterName, TeamName";
	}

}
