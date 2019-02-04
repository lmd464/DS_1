// Manages Experiment with Required Data

public class PerformanceMeasurement {
	
	
	// Constants for the Parameters :
	private static final int DEFAULT_NUMBER_OF_EXPERIMENTS = 5;
	private static final int DEFAULT_SIZE_INCREMENT = 10000;
	private static final int DEFAULT_FIRST_DATA_SIZE = 10000;
	
	
	
	// Instance variables :				
	private ParameterSet		_parameterSet;		// Parameter Set for test settings
	
	private int[] 		_ascendingList;			// Ascending Data List to Sort
	private int[] 		_descendingList;			// Descending Data List to Sort
	private int[] 		_randomList;				// Random Data List to Sort
	
	
	
	// Getters / Setters
	public ParameterSet parameterSet() { return this._parameterSet; }
	public void setParameterSet(ParameterSet newParameterSet) { this._parameterSet = newParameterSet; }
	
	public int[] ascendingList() 	{ return this._ascendingList; }
	public int[] descendingList() 	{ return this._descendingList; }
	public int[] randomList() 		{ return this._randomList; }
	
	
	
	// Constructors
	public PerformanceMeasurement() {
		this.setParameterSet( new ParameterSet( PerformanceMeasurement.DEFAULT_NUMBER_OF_EXPERIMENTS, 
												PerformanceMeasurement.DEFAULT_FIRST_DATA_SIZE, 
												PerformanceMeasurement.DEFAULT_SIZE_INCREMENT ) );
		this.generateData();
	}	
	
	public PerformanceMeasurement( int givenNumberOfExperiments, 
									int givenFirstDataSize, 
									int givenSizeIncrement ) 
	{
		this.setParameterSet( new ParameterSet( givenNumberOfExperiments, 
												givenFirstDataSize, 
												givenSizeIncrement ) );
		this.generateData();
	}
	
	
	
	// 데이터 생성
	public void generateData() {
		this._ascendingList = DataGenerator.ascendingList(this.parameterSet().maxDataSize());
		this._descendingList = DataGenerator.descendingList(this.parameterSet().maxDataSize());
		this._randomList = DataGenerator.randomList(this.parameterSet().maxDataSize());
	}
	
	
	
	// 주어진 List Order에 맞는 데이터 리스트 반환
	public int[] experimentList(ListOrder anOrder) {
		if(anOrder == ListOrder.Ascending)
			return this._ascendingList;

		else if(anOrder == ListOrder.Descending)
			return this._descendingList;

		else if(anOrder == ListOrder.Random)
			return this._randomList;
		
		else 
			return null;
 	}
	
	
	
	// 데이터 크기 변화시키며 측정 실험 실행
	// Dictionary 종류, 데이터 종류 설정
	public UnitExperimentResult[] experimentByDictionaryAndListOrderType
	(Dictionary<Integer, String> aDictionary, 
	ListOrder anOrder)
	
	{
		UnitExperimentResult[] experimentResults = 
				new UnitExperimentResult[this.parameterSet().numberOfExperiments()];
	
		
		// 측정 결과의 안정화를 위한 테스트, 측정과는 무관
		this.unitExperiment(aDictionary, anOrder, this.parameterSet().maxDataSize());
		System.gc();		// Garbage Collection 강제 실시 : 측정 실행 동안에 쓸데없이 발생하지 않도록
		
		
		// 측정
		int dataSize = this.parameterSet().firstDataSize();
		for(int iteration = 0; iteration < this.parameterSet().numberOfExperiments(); iteration++) {
			aDictionary.clear();
			experimentResults[iteration] = this.unitExperiment(aDictionary, anOrder, dataSize);
			dataSize += this.parameterSet().sizeIncrement();
		}
		
		return experimentResults;
	}
	
	
	
	
	
	
	
	
	
	
	// 비공개 함수
	
	// 측정 실험 후 결과 반환
	// Dictionary 종류, 데이터 종류, 데이터 크기 설정
	private UnitExperimentResult unitExperiment(Dictionary<Integer, String> aDictionary, 
												ListOrder anOrder, 
												int dataSize)
	{
		long startTime;
		long stopTime;
		int[] experimentList = this.experimentList(anOrder);		// 데이터 종류 설정
		
		
		// 주어진 데이터 크기만큼 추가하는 데 걸리는 시간 측정
		long timeForAdd = 0;
		for(int i = 0; i < dataSize; i++) {
			startTime = System.nanoTime();
			aDictionary.addKeyAndObject(experimentList[i], null);		// 사전 종류에 맞는 추가
			stopTime = System.nanoTime();
			timeForAdd += (stopTime - startTime);
		}
		
		
		// 주어진 데이터 크기와 같은 횟수 탐색하는 데 걸리는 시간 측정
		long timeForSearch = 0;
		for(int i = 0; i < dataSize; i++) {
			startTime = System.nanoTime();
			aDictionary.objectForKey(experimentList[i]);
			stopTime = System.nanoTime();
			timeForSearch += (stopTime - startTime);
		}
		
		
		// 주어진 데이터 크기만큼 제거하는 데 걸리는 시간 측정
		long timeForRemove = 0;
		for(int i = 0; i < dataSize; i++) {
			startTime = System.nanoTime();
			aDictionary.removeObjectForKey(experimentList[i]);
			stopTime = System.nanoTime();
			timeForRemove += (stopTime - startTime);
		}
		
		
		return ( new UnitExperimentResult (dataSize, timeForAdd, timeForSearch, timeForRemove) );
	}
	
	
	
	
}

