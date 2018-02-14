package xTreme;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

public class AcceptGoals extends State {

	private String goalTeam;
	private boolean gameOver;

	public AcceptGoals(Scorecard data) {
		super(data);
	}

	@Override
	protected void sendResponse() {
		if(gameOver){
			processGameover();
		}else{
			processGoal();
		}
	}

	private void processGoal() {
		supportersShoutOnGoal(goalTeam);
		reportersShoutOnGoal(goalTeam);
	}

	private void processGameover() {
		if(scoreCard.isDrawn()){
			supportersShout(scoreCard.getTeam1(), "Yes! Match Drawn.", "Yes! Match Drawn.");
			reportDrawn();
		}else{
			supportersShout(scoreCard.getLeader(), "Alas! "+scoreCard.getFollower()+" lost.", "Yes! "+scoreCard.getLeader()+" won.");
			reportGameOver(scoreCard.getLeader(), scoreCard.getFollower(), scoreCard.getScore(scoreCard.getLeader()), scoreCard.getScore(scoreCard.getFollower()));
		}
	}

	private void reportDrawn() {
		for(Entry<String, String> reporter: scoreCard.reporters.entrySet()){
			scoreCard.printStream.print(reporter.getKey()+" reports: Match Drawn. Brought to you by "+reporter.getValue()+".\n");
		}
	}

	private void reportGameOver(String wonTeam, String lostTeam, int winScore, int lostScore) {
		for(Entry<String, String> reporter: scoreCard.reporters.entrySet()){
			scoreCard.printStream.print(reporter.getKey()+" reports: "+wonTeam+" has won the game against "+lostTeam+" by "+winScore+"-"+lostScore+". Brought to you by "+reporter.getValue()+".\n");
		}
	}

	private void reportersShoutOnGoal(String goalTeam) {
		for(Entry<String, String> reporter: scoreCard.reporters.entrySet()){
			scoreCard.printStream.print(reporter.getKey()+" reports: "+goalTeam+" has scored a goal at "+getTimeNow()+". Brought to you by "+reporter.getValue()+".\n");
		}
	}

	private String getTimeNow() {
		return new SimpleDateFormat("h:mm a").format(new Date()).toLowerCase();
	}

	private void supportersShoutOnGoal(String winners) {
		String looserMesg = "Alas!";
		String winnerMesg = "Hurrah!";
		supportersShout(winners, looserMesg, winnerMesg);
	}

	private void supportersShout(String scorers, String looserMesg, String winnerMesg) {
		String others = (scorers.equals(scoreCard.getTeam1())? scoreCard.getTeam2(): scoreCard.getTeam1());
		for(String supporter: scoreCard.supporters.get(scorers)){
			scoreCard.printStream.print(supporter+" says: "+winnerMesg+"\n");
		}
		for(String supporter: scoreCard.supporters.get(others)){
			scoreCard.printStream.print(supporter+" says: "+looserMesg+"\n");
		}
	}

	@Override
	protected boolean acceptable(String input) {
		if(input.trim().matches("Goal:\\s+\\w+")){
			String goalTeam = input.trim().split(":\\s+")[1];
			if(scoreCard.isValidTeam(goalTeam)){
				return true;
			}
		}else if(input.trim().equals("Game over.")){
			return true;
		}
		return false;
	}

	@Override
	protected void acceptInput(String input) {
		if(input.trim().startsWith("Goal:")){
			goalTeam = input.trim().split(":\\s+")[1];
			scoreCard.addGoal(goalTeam);
		}else if(input.trim().equals("Game over.")){
			gameOver = true;
		}
	}

	@Override
	protected State nextState() {
		if(gameOver){
			return new GameOver(scoreCard);
		}else{
			return this;
		}
	}

	@Override
	protected String validInputFormat() {
		return "Game over. | Goal: TeamName";
	}
	
}
