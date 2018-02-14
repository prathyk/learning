package xTreme;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestFootBall {

	private FootBall footBall;
	private String output="";

	@Before
	public void before() {
		OutputStream out = System.out;
		footBall = new FootBall(new PrintStream(out){
			@Override
			public void print(String s) {
				output += s;
			}
		});
	}

	@Test
	public void gameTakesInput() {
		footBall.input("");
	}
	
	@Test
	public void gameTakesTeamsInput() {
		acceptsInput("India Brazil");
	}
	
	@Test
	public void gameRejectAnyOtherInputFirst() {
		rejectsInput("India Br azil", "Team1 Team2");
	}

	private void rejectsInput(String input, String error) {
		takesInputAndPrints(input,"Error Input. Valid Format:"+error);
	}
	
	private void takesInputAndPrints(String input, String prints){
		footBall.input(input);
		assertThat(output, is(endsWith(prints)));
	}

	
	@Test
	public void gameTakesTeamsInputOnlyOnce(){
		acceptsInput("India Brazil");
		rejectsInput("India Brazil","NoOfSupporters NoOfReporters");
	}
	
	@Test
	public void gameTakesNextInputTwoNumbers(){
		acceptsInput("India Brazil");
		acceptsInput("2 1");
	}
	
	@Test
	public void testGameInputForZeroSupporters(){
		acceptsInput("India Brazil");
		acceptsInput("0 1");
		rejectsInput("Ravi, India", "ReporterName, ChannelName");
	}
	
	@Test
	public void testGameInputForOneSupporter(){
		acceptsInput("India Brazil");
		acceptsInput("1 1");
		acceptsInput("Ravi, India");
		rejectsInput("Rahul, Brazil", "ReporterName, ChannelName");
	}
	
	@Test
	public void testGameInputForTwoSupporters(){
		acceptsInput("India Brazil");
		acceptsInput("2 1");
		acceptsInput("Ravi, India");
		acceptsInput("Rahul, Brazil");
		rejectsInput("Ramu, Brazil", "ReporterName, ChannelName");
	}

	@Test
	public void testGameInputForThreeSupporters(){
		acceptsInput("India Brazil");
		acceptsInput("3 1");
		acceptsInput("Ravi, India");
		acceptsInput("Rahul, Brazil");
		acceptsInput("Ramu, Brazil");
		rejectsInput("Ramesh, Brazil", "ReporterName, ChannelName");
	}
	
	private void acceptsInput(String input) {
		takesInputAndPrints(input, "");
	}
	
	@Test
	public void testGameInputForZeroReporters(){
		acceptsInput("India Brazil");
		acceptsInput("1 0");
		acceptsInput("Ravi, India");
		rejectsInput("Ajay, CNN", "Game over. | Goal: TeamName");
	}
	
	@Test
	public void testGameInputForOneReporter(){
		acceptsInput("India Brazil");
		acceptsInput("1 1");
		acceptsInput("Ravi, India");
		acceptsInput("Ravi, ETV");
		rejectsInput("Ajay, CNN", "Game over. | Goal: TeamName");
	}
	
	@Test
	public void testGameInputForTwoReporter(){
		acceptsInput("India Brazil");
		acceptsInput("1 2");
		acceptsInput("Ravi, India");
		acceptsInput("Ravi, ETV");
		acceptsInput("Ajay, CNN");
		rejectsInput("Rakesh, BNN", "Game over. | Goal: TeamName");
	}
	
	@Test
	public void testGameInputForThreeReporters(){
		acceptsInput("India Brazil");
		acceptsInput("1 3");
		acceptsInput("Ravi, India");
		acceptsInput("Ravi, ETV");
		acceptsInput("Ajay, CNN");
		acceptsInput("Girish, 9TV");
		rejectsInput("Rakesh, BNN", "Game over. | Goal: TeamName");
	}
	
	@Test
	public void testGameInputForThreeSupportersAndThreeReporters(){
		acceptsInput("India Brazil");
		acceptsInput("3 3");
		acceptsInput("Ravi, India");
		acceptsInput("Rahul, Brazil");
		acceptsInput("Ramu, Brazil");
		rejectsInput("Ramesh, Brazil", "ReporterName, ChannelName");
		acceptsInput("Ravi, ETV");
		acceptsInput("Ajay, CNN");
		acceptsInput("Girish, 9TV");
		rejectsInput("Rakesh, BNN", "Game over. | Goal: TeamName");
	}
	
	@Test
	public void gameAcceptsOneGoalWith1Supporter(){
		acceptsInput("India Brazil");
		acceptsInput("1 0");
		acceptsInput("Ravi, India");
		takesInputAndPrints("Goal: India", "Ravi says: Hurrah!\n");
	}
	
	@Test
	public void gameAcceptsOneGoalWith2SupportersOnOneTeam(){
		acceptsInput("India Brazil");
		acceptsInput("2 0");
		acceptsInput("Ravi, India");
		acceptsInput("Rahul, India");
		takesInputAndPrints("Goal: India", "Ravi says: Hurrah!\n"+
											"Rahul says: Hurrah!\n");
		takesInputAndPrints("Goal: Brazil", "Ravi says: Alas!\n"+
											"Rahul says: Alas!\n");
	}
	
	@Test
	public void gameAcceptsOneGoalWith1SupporterOnEachTeam(){
		acceptsInput("India Brazil");
		acceptsInput("2 0");
		acceptsInput("Ravi, India");
		acceptsInput("Rahul, Brazil");
		takesInputAndPrints("Goal: India", "Ravi says: Hurrah!\n"+
											"Rahul says: Alas!\n");
		takesInputAndPrints("Goal: Brazil", "Rahul says: Hurrah!\n"+
											"Ravi says: Alas!\n");
	}
	
	@Test
	public void gameAcceptsOneGoalWith1ReporterOnly(){
		acceptsInput("India Brazil");
		acceptsInput("0 1");
		acceptsInput("Vijay, CNN news");
		takesInputAndPrints("Goal: India", "Vijay reports: India has scored a goal at "+getTime()+". Brought to you by CNN news.\n");
	}
	
	private String getTime() {
		return new SimpleDateFormat("h:mm a").format(new Date()).toLowerCase();
	}

	@Test
	public void testProblemStatementExample(){
		acceptsInput("India Brazil");
		acceptsInput("2 1");
		acceptsInput("Ravi, India");
		acceptsInput("Rahul, Brazil");
		acceptsInput("Vijay, CNN news");
		takesInputAndPrints("Goal: India", "Ravi says: Hurrah!\n"+
											"Rahul says: Alas!\n"+
											"Vijay reports: India has scored a goal at "+getTime()+". Brought to you by CNN news.\n");
		takesInputAndPrints("Game over.", "Ravi says: Yes! India won.\n"+
															"Rahul says: Alas! Brazil lost.\n"+
															"Vijay reports: India has won the game against Brazil by 1-0. Brought to you by CNN news.\n");
	}
	
	@Test
	public void testDrawMatch(){
		acceptsInput("India Brazil");
		acceptsInput("2 1");
		acceptsInput("Ravi, India");
		acceptsInput("Rahul, Brazil");
		acceptsInput("Vijay, CNN news");
		takesInputAndPrints("Game over.", "Ravi says: Yes! Match Drawn.\n"+
															"Rahul says: Yes! Match Drawn.\n"+
															"Vijay reports: Match Drawn. Brought to you by CNN news.\n");
	}

}
