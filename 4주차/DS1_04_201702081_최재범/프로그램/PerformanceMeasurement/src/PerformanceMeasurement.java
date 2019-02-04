// 성능 측정 및 데이터 생성
// 처음 데이터 크기를 정해놓고, 크기를 일정하게 증가시키며 특정 횟수 시행

import java.util.Random;

public class PerformanceMeasurement {
	
	public static final int DEFAULT_NUMBER_OF_TESTS = 5;
	public static final int DEFAULT_FIRST_TEST_SIZE = 10000; 
	public static final int DEFAULT_SIZE_INCREMENT = 10000; 
	
	private int _numberOfTests;		// 시행 횟수
	private int _firstTestSize;		// 처음 시행 시 데이터 크기
	private int _sizeIncrement;		// 시행 시마다 크기 증가량
	
	private int[] _data;					// 난수를 저장할 데이터
	private TestResult[] _testResults;		// 시행 결과
	
	
	// 생성자
	public PerformanceMeasurement() {
		this._numberOfTests = PerformanceMeasurement.DEFAULT_NUMBER_OF_TESTS;
		this._firstTestSize = PerformanceMeasurement.DEFAULT_FIRST_TEST_SIZE;
		this._sizeIncrement = PerformanceMeasurement.DEFAULT_SIZE_INCREMENT;
		
		// 필요한 최대 크기만큼 미리 데이터 틀 생성
		this._data = new int[this.maxDataSize()];
		
		// 시행 횟수만큼 결과 생성
		this._testResults = new TestResult[PerformanceMeasurement.DEFAULT_NUMBER_OF_TESTS];
	}
	public PerformanceMeasurement(int givenNumberOfTests, int givenFirstTestSize, int givenSizeIncrement) {
		this._numberOfTests = givenNumberOfTests;
		this._firstTestSize = givenFirstTestSize;
		this._sizeIncrement = givenSizeIncrement;
		
		this._data = new int[this.maxDataSize()];
		this._testResults = new TestResult[givenNumberOfTests];
	}
	
	
	// Getter
	public int numberOfTests() {
		return this._numberOfTests;
	}
	public TestResult[] testResults() {
		return this._testResults;
	}
	
	
	// 실험 데이터 최대 크기
	public int maxDataSize() {
		return this._firstTestSize + this._sizeIncrement * (this._numberOfTests - 1);
	}
	
	
	// 실험 데이터 생성
	public void generateData() {
		Random random = new Random();
		
		for(int i = 0; i < this.maxDataSize(); i++) {
			this._data[i] = random.nextInt(this.maxDataSize());
		}
	}
	
	

	// UnsortedArrayBag 테스트 수행
	public void testUnsortedArrayBag() {
		
		UnsortedArrayBag<Coin> bag;
		int maxCoin;					// 코인 최댓값
		
		int testSize;					// 데이터 크기
		
		long timeForAdd, timeForMax;	// 걸린 시간
		long start, stop;				// 시작시간, 정지시간
		
		testSize = this._firstTestSize;
		
		for(int testCount = 0; testCount < this._numberOfTests; testCount++) {
			
			bag = new UnsortedArrayBag<Coin>(testSize);		// 데이터 크기 : 가방 크기
			timeForAdd = 0;
			timeForMax = 0;
			
			for(int testDataCount = 0; testDataCount < testSize; testDataCount++) {
				Coin coin = new Coin(this._data[testDataCount]);	// 코인의 값에 난수 지정
				
				// 삽입 시간 측정
				start = System.nanoTime();
				bag.add(coin);
				stop = System.nanoTime();
				timeForAdd += (stop - start);
				
				// 최댓값 계산 시간 측정
				start = System.nanoTime();
				maxCoin = this.maxCoinValue(bag);
				stop = System.nanoTime();
				timeForMax += (stop - start);
			}
			
			this._testResults[testCount] = new TestResult(testSize, timeForAdd, timeForMax);
			testSize += this._sizeIncrement;
		
		}
		
	}
	
	private int maxCoinValue(UnsortedArrayBag<Coin> aCoinBag) {
		int maxValue = 0;
		for(int i = 0; i < aCoinBag.size(); i++) {
			if(maxValue < aCoinBag.elementAt(i).value()) {
				maxValue = aCoinBag.elementAt(i).value();
			}
		}
		return maxValue;
	}
	
	
}
