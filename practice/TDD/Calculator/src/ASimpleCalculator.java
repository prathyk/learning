import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;


public class ASimpleCalculator {

	private Calculator calculator;

	@Before
	public void setup(){
		calculator = new Calculator();
	}
	
	private void assertThatAnswerIs(int value) {
		assertThat(calculator.answer(), is(equalTo(value)));
	}
	
	private void parseExpression(String expression) {
		String[] tokens = expression.split(" ");
		
		for(String token: tokens){
			if(token.matches("\\d+")){
				calculator.enter(Integer.parseInt(token));
			}else{
				calculator.enterOperator(token.charAt(0));
			}
		}
	}
	
	@Test
	public void ShouldAnswer0WhenNoInput() {
		assertThatAnswerIs(0);
	}
	
	@Test
	public void ShouldAnswer5WhenIEnter5() throws Exception {
		calculator.enter(5);
		assertThatAnswerIs(5);
	}

	@Test
	public void ShouldAnswer5WhenIGive2Plus3() throws Exception {
		parseExpression("2 + 3");
		assertThatAnswerIs(5);
	}

	
	
	@Test
	public void ShouldAnswer6whenIgive1Plus2Plus3() throws Exception {
		parseExpression("1 + 2 + 3");
		assertThatAnswerIs(6);
	}
	
	@Test
	public void ShouldAnswer3WhenIGive5Minus2() throws Exception {
		parseExpression("5 - 2");
		assertThatAnswerIs(3);
	}
	
	@Test
	public void ShouldAnswer6WhenIGive2Times3() throws Exception {
		parseExpression("2 * 3");
		assertThatAnswerIs(6);
	}

	

}
