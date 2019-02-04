
public class UnitExperimentResult {
	
	private int _experimentSize;
	private long _timeForAdd;
	private long _timeForSearch;
	private long _timeForRemove;
	
	
	
	// 생성자
	public UnitExperimentResult() {}
	public UnitExperimentResult( int givenExperimentSize, 
								long givenTimeForAdd, 
								long givenTimeForSearch, 
								long givenTimeForRemove ) 
	{
		this._experimentSize = givenExperimentSize;
		this._timeForAdd = givenTimeForAdd;
		this._timeForSearch = givenTimeForSearch;
		this._timeForRemove = givenTimeForRemove;
	}
	
	
	
	// Getter / Setter
	
	public int experimentSize() { return this._experimentSize; }
	public void setExperimentSize(int newExperimentSize) { this._experimentSize = newExperimentSize; }
	
	public long timeForAdd() { return this._timeForAdd; }
	public void setTimeForAdd(long newTimeForAdd) { this._timeForAdd = newTimeForAdd; }
	
	public long timeForSearch() { return this._timeForSearch; }
	public void setTimeForSearch(long newTimeForSearch) { this._timeForSearch = newTimeForSearch; }
	
	public long timeForRemove() { return this._timeForRemove; }
	public void setTimeForRemove(long newTimeForRemove) { this._timeForRemove = newTimeForRemove; }
	
	
}
