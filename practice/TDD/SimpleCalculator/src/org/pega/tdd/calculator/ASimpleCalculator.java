package org.pega.tdd.calculator;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;

public class ASimpleCalculator {

	private Calculator calculator;
	
	@Before
	public void before() {
		calculator = new Calculator();
	}

	private void assertThatAnswerIs(int answer) {
		assertThat(calculator.answer(), is(equalTo(answer)));
	}
	
	@Test
	public void shouldGiveZeroAnswerForNoInput() {
		assertThatAnswerIs(0);
	}

	@Test
	public void shouldReturnSameNumberForSingleNumberInput() throws Exception {
		calculator.enterValue(3);
		assertThatAnswerIs(3);
	}
	
	@Test
	public void shouldGive5WhenIAdd2And3() throws Exception {
		calculator.enterValue(2);
		calculator.enterOperator('+');
		calculator.enterValue(3);
		assertThatAnswerIs(5);
	}
	
	@Test
	public void shouldGive2WhenISubtract3From5() throws Exception {
		calculator.enterValue(5);
		calculator.enterOperator('-');
		calculator.enterValue(3);
		assertThatAnswerIs(2);
	}
	
	@Test
	public void shouldGive3WhenIAdd3() throws Exception {
		calculator.enterOperator('+');
		calculator.enterValue(3);
		assertThatAnswerIs(3);
	}
	
	@Test
	public void shouldGive7WhenIAdd1And2And4() throws Exception {
		calculator.enterValue(1);
		calculator.enterOperator('+');
		calculator.enterValue(2);
		calculator.enterOperator('+');
		calculator.enterValue(4);
		assertThatAnswerIs(7);
	}
	
	@Test
	public void shouldGive15WhenIMultiply3And5() throws Exception {
		calculator.enterValue(3);
		calculator.enterOperator('*');
		calculator.enterValue(5);
		assertThatAnswerIs(15);
	}

	@Test
	public void shouldGive14For2Plus3Times4() throws Exception {
		calculator.enterValue(2);
		calculator.enterOperator('+');
		calculator.enterValue(3);
		calculator.enterOperator('*');
		calculator.enterValue(4);
		assertThatAnswerIs(14);
	}
	
	@Test
	public void shouldGive14For3Times4Plus2() throws Exception {
		calculator.enterValue(3);
		calculator.enterOperator('*');
		calculator.enterValue(4);
		calculator.enterOperator('+');
		calculator.enterValue(2);
		assertThatAnswerIs(14);
	}
	
	@Test
	public void shouldGive8For2Times3Plus1Times2() throws Exception {
		calculator.enterValue(2);
		calculator.enterOperator('*');
		calculator.enterValue(3);
		calculator.enterOperator('+');
		calculator.enterValue(1);
		calculator.enterOperator('*');
		calculator.enterValue(2);
		assertThatAnswerIs(8);
	}
}
