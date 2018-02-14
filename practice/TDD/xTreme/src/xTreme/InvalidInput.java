package xTreme;

public class InvalidInput extends Exception {

	private String validFormat;

	public InvalidInput(String validFormat) {
		this.validFormat = validFormat;
	}
	
	@Override
	public String toString() {
		return "Error Input. Valid Format:"+validFormat;
	}
	
}
