
public class ParameterSet {
	
	
	// Private instance variables
	private int _startingSize;
	private int _numberOfSizeIncreasingSteps;
	private int _incrementSize;
	
	
	
	// Constructor
	public ParameterSet( int givenStartingSize, 
						 int givenNumberOfSizeIncreasingSteps, 
						 int givenIncrementSize )
	{
		this._startingSize = givenStartingSize;
		this._numberOfSizeIncreasingSteps = givenNumberOfSizeIncreasingSteps;
		this._incrementSize = givenIncrementSize;
	}
	
	
	
	// Getters & Setters
	public int startingSize() {	return this._startingSize; }
	public int numberOfSizeIncreasingSteps() { return this._numberOfSizeIncreasingSteps; }
	public int incrementSize() { return this._incrementSize; }
	
	public void setStartingSize(int aStartingSize) { 
		this._startingSize = aStartingSize;
	}
	public void setNumberOfSizeIncreasingSteps(int aNumberOfSizeIncreasingSteps) { 
		this._numberOfSizeIncreasingSteps = aNumberOfSizeIncreasingSteps;
	}
	public void setIncrementSize(int aSetIncrementSize) {
		this._incrementSize = aSetIncrementSize;
	}
	
	
	
	// return Maximum Data Size
	public int maxDataSize() {
		return this.startingSize() + ( this.incrementSize() * ( this.numberOfSizeIncreasingSteps() - 1 ) );
	}
	
	
}
