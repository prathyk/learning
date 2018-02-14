package example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCurrencyConverter {

	@Test
	public void testCurrencyConversionTwoWays() {
		String from = "ABC";
		String to = "DEF";
		CurrencyConverter currencyConverter = new CurrencyConverter();
		currencyConverter.addExchangeRate(from, to, 2);
		
		assertEquals(8, currencyConverter.convertCurrency(from, to, 4));
		assertEquals(4, currencyConverter.convertCurrency(to, from, 8));
	}

}
