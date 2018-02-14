package xTreme;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Scorecard {

	private String team1;
	private String team2;
	private int noOfSupporters;
	private int noOfReporters;
	Map<String, List<String>> supporters = new HashMap<>();
	Map<String, String> reporters = new HashMap<>();
	Map<String, Integer> score = new HashMap<>();

	public PrintStream printStream;
	public Scorecard(PrintStream printStream) {
		super();
		this.printStream = printStream;
	}
	String getTeam1() {
		return team1;
	}
	void setTeam1(String team1) {
		this.team1 = team1;
		initTeamStructures(team1);
	}
	private void initTeamStructures(String team) {
		supporters.put(team, new ArrayList<>());
		score.put(team, 0);
	}
	String getTeam2() {
		return team2;
	}
	void setTeam2(String team2) {
		this.team2 = team2;
		initTeamStructures(team2);
	}
	int getNoOfSupporters() {
		return noOfSupporters;
	}
	void setNoOfSupporters(int noOfSupporters) {
		this.noOfSupporters = noOfSupporters;
	}
	int getNoOfReporters() {
		return noOfReporters;
	}
	void setNoOfReporters(int noOfReporters) {
		this.noOfReporters = noOfReporters;
	}
	public void addSupporter(String team, String supporter) {
		supporters.get(team).add(supporter);
	}
	public void addGoal(String goalTeam) {
		score.put(goalTeam, score.get(goalTeam)+1);
	}
	public int countActualSupporters() {
		int count = 0;
		for(List<String> teamSupportList: supporters.values()){
			count += teamSupportList.size();
		}
		return count;
	}
	public String getLeader() {
		return (score.get(team1)>score.get(team2))? team1: team2;
	}
	
	public String getFollower() {
		return (score.get(team1)<score.get(team2))? team1: team2;
	}
	public int getScore(String team) {
		return score.get(team);
	}
	public boolean isValidTeam(String team) {
		return supporters.containsKey(team);
	}
	public boolean isDrawn() {
		return score.get(team1)==score.get(team2);
	}
	
	
	
	
}
