
public class ParameterSet {
	
	// Private instance variables
	private int _firstDataSize;
	private int _numberOfExperiments;
	private int _sizeIncrement;
	
	
	
	// Getters & Setters
	public int firstDataSize() {	return this._firstDataSize; }
	public void setFirstDataSize(int newFirstDataSize) { this._firstDataSize = newFirstDataSize; }
	
	public int numberOfExperiments() { return this._numberOfExperiments; }
	public void setNumberOfExperiments(int newNumberOfExperiments) { 
		this._numberOfExperiments = newNumberOfExperiments;
	}
	
	public int sizeIncrement() { return this._sizeIncrement; }
	public void setSizeIncrement(int newSizeIncrement) { this._sizeIncrement = newSizeIncrement; }
	
	
	
	// Constructor
	public ParameterSet( int givenNumberOfExperiments, 
						int givenFirstDataSize, 
						 int givenSizeIncrement )
	{
		this._numberOfExperiments = givenNumberOfExperiments;
		this._firstDataSize = givenFirstDataSize;
		this._sizeIncrement = givenSizeIncrement;
	}
	
	
	
	// return Maximum Data Size
	public int maxDataSize() {
		return this.firstDataSize() + ( this.sizeIncrement() * ( this.numberOfExperiments() - 1 ) );
	}
	
}
