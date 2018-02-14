package example.bowling;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;

public class TestBowlingGame {

	private BowlingGame game;

	private void rollMany(int noOfRolls, int pins) {
		for (int i = 0; i < noOfRolls; i++) {
			game.roll(pins);
		}
	}
	
	@Before
	public void before(){
		game = new BowlingGame();
	}

	private void rollSpare() {
		game.roll(4);
		game.roll(6);
	}
	
	private void rollStrike() {
		game.roll(10);
	}
	
	private void assertThatScoreShouldBe(int i) {
		assertThat(game.getScore(), is(equalTo(i)));
	}
	
	@Test
	public void shouldIs0WhenNoThrows() {
		assertThatScoreShouldBe(0);
	}
	
	@Test
	public void scoreIs0WhenAllZeros(){
		rollMany(20, 0);
		assertThatScoreShouldBe(0);
	}
	
	@Test
	public void scoreShouldBe60WhenAll3(){
		rollMany(20, 3);
		assertThatScoreShouldBe(20*3);
	}

	@Test
	public void scoreShouldBe67WhenFirstIsSpareAndRestAll3(){
		rollSpare();
		rollMany(18, 3);
		assertThatScoreShouldBe(67);
	}
	
	@Test
	public void scoreShouldBe67WhenLastIsSpareAndRestAll3(){
		rollMany(18, 3);
		rollSpare();
		game.roll(3);
		assertThatScoreShouldBe(67);
	}
	
	@Test
	public void scoreShouldBe150WhenAllRolls5(){
		rollMany(21, 5);
		assertThatScoreShouldBe(150);	
	}
	
	@Test
	public void scoreShouldBe70WhenFirstStrikeAndRest3(){
		rollStrike();
		rollMany(18, 3);
		assertThatScoreShouldBe(70);
	}
	
	@Test
	public void scoreShouldBe70WhenLastStrikeAndRest3(){
		rollMany(18, 3);
		rollStrike();
		game.roll(3);
		game.roll(3);
		assertThatScoreShouldBe(70);
	}
	
	@Test
	public void scoreShouldBe300ForPerfectGame(){
		rollMany(12, 10);
		assertThatScoreShouldBe(300);
	}

	@Test
	public void scoreShouldBe12WhenOnly4RollsAre3s() throws Exception {
		rollMany(4, 3);
		assertThatScoreShouldBe(12);
	}
	
	@Test
	public void scoreIs60WhenAll3AndExtrathrow() throws Exception {
		rollMany(21, 3);
		assertThatScoreShouldBe(60);
	}
	
	@Test
	public void scoreIs67WhenAll3AndLastSpareAndExtraThrow() throws Exception {
		rollMany(18, 3);
		rollSpare();
		rollMany(2, 3);
		assertThatScoreShouldBe(67);
	}
	
	@Test
	public void scoreIs70WhenAll3AndLastStrikeAndExtrathrow() throws Exception {
		rollMany(18, 3);
		rollStrike();
		rollMany(3, 3);
		assertThatScoreShouldBe(70);
	}
	
	@Test
	public void scoreIs82ForSpecificInputSequence() throws Exception {
		int[] rolls = {2,3,5,5,3,3,1,2,10,2,2,4,5,5,5,5,4,2,2};
		for(int roll:rolls){
			game.roll(roll);
		}
		assertThatScoreShouldBe(82);
	}

	@Test
	public void scoreIs155WhenFirstStrikeRestAllSpares() throws Exception {
		rollStrike();
		for(int i=1;i<=9;i++){
			rollSpare();
		}
		assertThatScoreShouldBe(142);
	}
	
	@Test
	public void first0Next10Rest3AndScoreShouldBe67() throws Exception {
		game.roll(0);
		game.roll(10);
		rollMany(18, 3);
		assertThatScoreShouldBe(67);
	}
}
