package example.bowling;

public class BowlingGame {
	
	private int pinsDown = 0;
	private int previousRollPins = 0;
	private int doubleNext;
	private int rollCount = 0;
	private double frameCount = 0;

	public int getScore() {
		return pinsDown;
	}

	public void roll(int pins) {
		handleCurrentRoll(pins);
		setupForNextRoll(pins);
	}

	private void handleCurrentRoll(int pins) {
		handleDoubleNext(pins);
		addRegularPinValue(pins);
	}

	private void setupForNextRoll(int pins) {
		adjustDoubleNext(pins);
		advanceFrameCount(pins);
		previousRollPins = pins;
	}

	private void adjustDoubleNext(int pins) {
		if(isStrike(pins)){
			doubleNext += 2;
		}else if(isSpare(pins)){
			doubleNext++;
		}
	}

	private void advanceFrameCount(int pins) {
		if(isStrike(pins)){
			frameCount += 1;
		}else {
			frameCount += 0.5;
		}
	}

	private boolean isSpare(int pins) {
		return frameCount<=9.5 && previousRollPins + pins == 10 && frameCount%1!=0;
	}

	private boolean isStrike(int pins) {
		return pins==10 && frameCount%1==0 && frameCount<=9;
	}

	private void addRegularPinValue(int pins) {
		if(frameCount<10){
			pinsDown += pins;
		}
	}

	private void handleDoubleNext(int pins) {
		if(doubleNext>1){
			while(doubleNext > 1){
				pinsDown += pins;
				doubleNext--;
			}
		}else if(doubleNext==1){
			pinsDown += pins;
			doubleNext--;
		}
	}

}
