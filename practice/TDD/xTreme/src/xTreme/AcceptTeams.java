package xTreme;

public class AcceptTeams extends State {

	public AcceptTeams() {
		this(new Scorecard(System.out));
	}
	public AcceptTeams(Scorecard data) {
		super(data);
	}

	@Override
	protected String validInputFormat() {
		return "Team1 Team2";
	}
	
	@Override
	protected AcceptNoOfParties nextState() {
		return new AcceptNoOfParties(scoreCard);
	}
	
	@Override
	protected void acceptInput(String input) {
		String[] teams = input.trim().split("\\s+");
		scoreCard.setTeam1(teams[0]);
		scoreCard.setTeam2(teams[1]);
	}
	
	@Override
	protected boolean acceptable(String input) {
		return input.trim().matches("(\\w+)\\s+\\w+");
	}

}
