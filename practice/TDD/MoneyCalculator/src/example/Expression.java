package example;

public abstract class Expression {

	protected CurrencyConverter currencyConverter = CurrencyConverter.getInstance();

	public abstract Money reduceTo(String string);

}
