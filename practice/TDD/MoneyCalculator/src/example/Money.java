package example;

public class Money extends Expression{

	private int amount;
	private String currency;
	
	public Money(int i, String currency) {
		amount = i;
		this.currency = currency;
	}

	public int getValue() {
		return amount;
	}

	@Override
	public boolean equals(Object arg0) {
		Money money = (Money) arg0;
		return (currency.equals(money.getCurrency())) && (amount == money.getValue());
	}

	public Expression times(int i){
		return new Money(amount * i,currency);
	}

	public String getCurrency() {
		return currency;
	}

	public Expression plus(Expression money) {
		return new Sum(this,money);
	}

	@Override
	public Money reduceTo(String currencysym) {
		if(currencysym.equals(getCurrency())){
			return this;
		}else{
			int newValue = currencyConverter.convertCurrency(getCurrency(), currencysym, amount);
			return new Money(newValue, currencysym);
		}
	}

}