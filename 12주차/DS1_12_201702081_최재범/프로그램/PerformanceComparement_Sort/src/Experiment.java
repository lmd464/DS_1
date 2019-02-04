// Start experiment when given ParameterSet, Data to Sort, Sorting Method

public class Experiment {
	
	
	// Private instance variables
	private final ParameterSet _parameterSet;
	
	
	
	// Constructor
	public Experiment(ParameterSet givenParameterSet) {
		this._parameterSet = givenParameterSet;
	}
	
	
	
	// Repeat sorting given list as much as steps (copy list to test several times)
	public long[] durationsOfSort(Sort<Integer> aSort, Integer[] experimentList) {
		
		int numberOfSteps = this._parameterSet.numberOfSizeIncreasingSteps();
		long[] durations = new long[numberOfSteps];
		
		int sortingSize = this._parameterSet.startingSize();
		int incrementSize = this._parameterSet.incrementSize();
		
		for(int step = 0; step < numberOfSteps; step++) {
			
			// Copy as much as size to sort : from experimentList
			Integer[] listForSorting = this.copyListOfGivenSize(experimentList, sortingSize);
			
			// Sort, Measure, Store
			durations[step] = this.durationOfSingleSort(aSort, listForSorting);
			
			sortingSize += incrementSize;
		}
		
		return durations;
		
	}
	
	
	
	
	
	// Private Methods
	
	// Copy Integer List as much as you want to copy
	private Integer[] copyListOfGivenSize(Integer[] aList, int copiedSize) {
		Integer[] copiedList = null;
		if(copiedSize <= aList.length) {
			copiedList = new Integer[copiedSize];
			for(int i = 0; i < copiedSize; i++) {
				copiedList[i] = aList[i];
			}
		}
		return copiedList;
	}
	
	
	
	// Sort given List and return duration
	private long durationOfSingleSort(Sort<Integer> aSort, Integer[] aList) {
		Timer timer = new Timer();
		timer.start();
		{
			aSort.sort(aList, aList.length);
		}
		timer.stop();
		return timer.duration();
	}
	
}
