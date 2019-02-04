// 결과 정보

public class TestResult {
	
	private int _testSize;
	private long _testInsertTime;
	private long _testFindMaxTime;
	
	
	// 생성자
	public TestResult() {
		
	}
	public TestResult(int aTestSize, long aTestInsertTime, long aTestFindMaxTime) {
		this._testSize = aTestSize;
		this._testInsertTime = aTestInsertTime;
		this._testFindMaxTime = aTestFindMaxTime;
	}
	
	
	// Getter
	public int testSize() {
		return this._testSize;
	}
	public long testInsertTime() {
		return this._testInsertTime;
	}
	public long testFindMaxTime() {
		return this._testFindMaxTime;
	}
	
	
	// Setter
	public void setTestSize(int aTestSize) {
		this._testSize = aTestSize;
	}
	public void setTestInsertTime(int aTestInsertTime) {
		this._testInsertTime = aTestInsertTime;
	}
	public void setTestFindMaxTime(int aTestFindMaxTime) {
		this._testFindMaxTime = aTestFindMaxTime;
	}
	
	
	
}
