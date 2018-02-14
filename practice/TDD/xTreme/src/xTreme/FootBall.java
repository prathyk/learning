package xTreme;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class FootBall {
	
	private PrintStream printStream;
	Scorecard data;
	State currentState;

	public FootBall(PrintStream ps) {
		printStream = ps;
		data = new Scorecard(printStream);
		currentState = new AcceptTeams(data);
	}
	
	public FootBall() {
		this(System.out);
	}

	public void input(String input) {
		try {
			currentState = currentState.processInput(input);
		} catch (InvalidInput e) {
			data.printStream.print(e.toString());
		}
	}
	
	public static void main(String[] args) {
		FootBall game = new FootBall();
		while(true){
			try {
				game.input(new BufferedReader(new InputStreamReader(System.in)).readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
