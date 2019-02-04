
public final class Timer {
	
	
	// Private instance variables
	private long _startTime;
	private long _stopTime;
	
	
	
	// Constructor
	public Timer() {};
	
	
	
	// Public Methods
	public void start() {
		this._startTime = System.currentTimeMillis();
	}
	public void stop() {
		this._stopTime = System.currentTimeMillis();
	}
	public long duration() {
		return ( this._stopTime - this._startTime );
	}	
	
	
}
