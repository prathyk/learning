package example;

import java.util.HashMap;
import java.util.Map;


public class CurrencyConverter {
	private static CurrencyConverter instance;

	private Map<String, Double> conversionRates = new HashMap<String, Double>();
	public int convertCurrency(String from, String to, int value) {
		if(from.equals(to)){
			return value;
		}
		return (int) (value*conversionRates.get(from+to));
	}

	public void addExchangeRate(String from, String to, double multiplier){
		conversionRates.put(from+to, multiplier);
		conversionRates.put(to+from, 1/multiplier);
	}
	
	public static CurrencyConverter getInstance() {
		if(instance == null){
			instance = new CurrencyConverter();
			instance.addExchangeRate("USD", "CHF", 2);
		}
		return instance;
	}
}
/**
 * prime factors kata
 * 
 */
