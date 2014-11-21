package generic;

public class DebounceTimer {

	private double START_TIME_SEC;
	private double TIMER_LENGTH_SEC;
	private double COUNTDOWN_SEC;
	
	public DebounceTimer() {
		TIMER_LENGTH_SEC = 0.25;
		COUNTDOWN_SEC    = 0;
	}
	
	public void setDebounceTime(double seconds) { 
		TIMER_LENGTH_SEC = seconds;
	}
	
	public void reset() { 
		COUNTDOWN_SEC  = TIMER_LENGTH_SEC; 
		START_TIME_SEC = currentTimeSeconds();
	}
	
	public boolean isDebounceComplete() {
		updateCountdown();
		return COUNTDOWN_SEC <= 0;
	}
	
	private void updateCountdown() {
		COUNTDOWN_SEC = TIMER_LENGTH_SEC - passedTimeSinceReset();
	}
	
	private double passedTimeSinceReset() {
		return currentTimeSeconds() - START_TIME_SEC;
	}
	
	private double currentTimeSeconds() {
		return System.currentTimeMillis() / 1000;
	}
}