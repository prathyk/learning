package xTreme;

public class AcceptReporter extends State {

	public AcceptReporter(Scorecard data) {
		super(data);
	}

	@Override
	protected boolean acceptable(String input) {
		if(input.trim().matches("\\w+,\\s+\\w+.*")){
			String[] tokens = input.trim().split(",\\s+");
			if(!scoreCard.isValidTeam(tokens[1])){
				return true;
			}
		}
		return false;
	}

	@Override
	protected void acceptInput(String input) {
		String[] tokens = input.trim().split(",\\s+");
		scoreCard.reporters.put(tokens[0], tokens[1]);
	}

	@Override
	protected State nextState() {
		if(scoreCard.getNoOfReporters()>scoreCard.reporters.size()){
			return this;
		}else{
			return new AcceptGoals(scoreCard);
		}
	}

	@Override
	protected String validInputFormat() {
		return "ReporterName, ChannelName";
	}

}
