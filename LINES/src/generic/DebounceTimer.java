package generic;

public class DebounceTimer {

	private double START_TIME_SEC;
	private double TIMER_LENGTH_SEC;
	private double COUNTDOWN_SEC;
	
	public DebounceTimer() {
		setDebounceTime_sec(0.25);
		reset();
	}
	
	public void setDebounceTime_sec(double seconds) { 
		TIMER_LENGTH_SEC = seconds;
	}
	
	public void reset() { 
		COUNTDOWN_SEC  = TIMER_LENGTH_SEC; 
		START_TIME_SEC = currentTimeSeconds();
	}
	
	public boolean isDebounceComplete() {
		updateCountdown();
		System.out.println(COUNTDOWN_SEC);
		return COUNTDOWN_SEC >= TIMER_LENGTH_SEC;
	}
	
	private void updateCountdown() {
		COUNTDOWN_SEC = passedTimeSinceReset();
	}
	
	private double passedTimeSinceReset() {
		return currentTimeSeconds() - START_TIME_SEC;
	}
	
	private static double currentTimeSeconds() {
		return (System.currentTimeMillis() / 1000.0);
	}
}