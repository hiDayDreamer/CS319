import javax.swing.*;
public class GameTimer {

	private Timer clock;
	private boolean timerOn;
	private boolean outOfTime;
	private double timeSpent;
	private Engine engine;
	final private int easyLevTime = 120000;
	final private int medLevTime = 240000; 
	final private int hardLevTime = 360000;

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public Timer getClock() {
		return this.clock;
	}

	/**
	 * 
	 * @param clock
	 */
	public void setClock(Timer clock) {
		this.clock = clock;
	}

	public boolean getTimerOn() {
		return this.timerOn;
	}

	/**
	 * 
	 * @param timerOn
	 */
	public void setTimerOn(boolean timerOn) {
		this.timerOn = timerOn;
	}

	public boolean getOutOfTime() {
		return this.outOfTime;
	}

	/**
	 * 
	 * @param outOfTime
	 */
	public void setOutOfTime(boolean outOfTime) {
		this.outOfTime = outOfTime;
	}

	public int getTimeSpent() {
		return (int)this.timeSpent;
	}

	public int getEasyLevTime() {
		return easyLevTime;
	}

	public int getMedLevTime() {
		return medLevTime;
	}

	public int getHardLevTime() {
		return hardLevTime;
	}

	/**
	 * 
	 * @param timeSpent
	 */
	public void setTimeSpent(int timeSpent) {
		this.timeSpent = timeSpent;
	}

	public Engine getEngine() {
		return this.engine;
	}

	public void startClock() {
		// TODO - implement Timer.startClock
		throw new UnsupportedOperationException();
	}

	public void stopClock() {
		// TODO - implement Timer.stopClock
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param timeSpent
	 */
	public void reset(int timeSpent) {
		// TODO - implement Timer.reset
		throw new UnsupportedOperationException();
	}

}
