package example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestMoneyCalculator {

	@Test
	public void testSimpleMultiply() {
		assertEquals(new Money(10,"USD"), new Money(5,"USD").times(2));
		assertEquals(new Money(10,"CHF"), new Money(5,"CHF").times(2));
	}
	
	@Test
	public void testSimpleMultiplyNoSideEffects() {
		Money five = new Money(5,"USD");
		assertEquals(new Money(10,"USD"), five.times(2));
		assertEquals(new Money(5,"USD"), five);
		Money fiveF = new Money(5,"CHF");
		assertEquals(new Money(10,"CHF"), fiveF.times(2));
		assertEquals(new Money(5,"CHF"), fiveF);
		assertNotEquals(five, fiveF);
	}
	
	@Test
	public void test5ShouldEqual5() throws Exception {
		assertEquals(new Money(5,"USD"),new Money(5,"USD"));
		assertNotEquals(new Money(2,"USD"),new Money(3,"USD"));
		assertEquals(new Money(5,"CHF"),new Money(5,"CHF"));
		assertNotEquals(new Money(2,"CHF"),new Money(3,"CHF"));
		assertNotEquals(new Money(2,"CHF"),new Money(2,"USD"));
	}
	
	@Test
	public void testCurrencies() throws Exception {
		assertEquals("USD", new Money(3,"USD").getCurrency());
		assertEquals("CHF", new Money(3,"CHF").getCurrency());
	}
	
	@Test
	public void testSimpleAddition() throws Exception {
		Expression plus = new Money(3,"USD").plus(new Money(7,"USD"));
		assertEquals(new Money(10,"USD"), plus.reduceTo("USD"));
	}
	
	@Test
	public void testAddingDifferentCurrencies(){
		Expression sum = new Money(5, "USD").plus(new Money(10,"CHF"));
		Expression reduced = sum.reduceTo("USD");
		assertEquals(new Money(10,"USD"), reduced);
	}
	
	@Test
	public void testSimpleComplexExpression(){
		Expression times = new Money(10,"USD").times(3);
		Expression sum = new Money(20, "CHF").plus(times);
		assertEquals(new Money(40, "USD"), sum.reduceTo("USD"));
	}

}
