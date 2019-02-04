// Manages Experiment with Required Data

public class ExperimentManager {
	
	
	// Constants for the Parameters :
	private static final int DEFAULT_NUMBER_OF_SIZE_INCREASING_STEPS = 10;
	private static final int DEFAULT_INCREMENT_SIZE = 1000;
	private static final int DEFAULT_STARTING_SIZE = DEFAULT_INCREMENT_SIZE;
	
	
	
	// Constants for Sorting Methods :
	private static final InsertionSort<Integer> INSERTION_SORT = new InsertionSort<Integer>();
	private static final QuickSort<Integer>     QUICK_SORT     = new QuickSort<Integer>();
	
	
	
	// Private instance variables :
	private Experiment		_experiment;							
	private ParameterSet	_parameterSet;
	
	private Integer[] 		_ascendingList;			// Ascending Data List to Sort
	private Integer[] 		_descendingList;		// Descending Data List to Sort
	private Integer[] 		_randomList;			// Random Data List to Sort
	
	private long[] 			_measuredResultForInsertionSort;	// To Store Measurement Results for Insertion Sort
	private long[] 			_measuredResultForQuickSort;		// To Store Measurement Results for Quick Sort
	
	
	
	// Constructor
	public ExperimentManager() {
		this.setParameterSetWithDefaults();		// Set Parameter Set to Default Value
	}		
	
	
	
	// Public Getters
	public ParameterSet parameterSet() {
		return this._parameterSet;
	}

	public long measuredResultForInsertionSortAt(int sizeStep) {	// Return Measured Results
		return this._measuredResultForInsertionSort[sizeStep];
	}
	
	public long measuredResultForQuickSortAt(int sizeStep) {
		return this._measuredResultForQuickSort[sizeStep];
	}
	
	
	
	// Prepare All Data (Fields) For Experiment
	public void prepareExperiment(ParameterSet aParameterSet) {
		
		if(aParameterSet != null) {
			this._parameterSet = aParameterSet;		// Set New ParameterSet
		}
		this._experiment = new Experiment(this._parameterSet);
		
		this.prepareExperimentLists();				// Set Lists to Experiment
		
	}
	
	
	
	// TEST START : List Order should be specified
	public void performExperiment(ListOrder anOrder) {
		
		// 1. Set List order
		Integer[] experimentList = this.experimentListOfOrder(anOrder);
		
		// 2. Measure
		this._measuredResultForInsertionSort = this._experiment.durationsOfSort(INSERTION_SORT, experimentList);
		this._measuredResultForQuickSort = this._experiment.durationsOfSort(QUICK_SORT, experimentList);
		
	}
	
	
	
	
	
	// Private Methods
	
	// Generate Lists for Experiment and Set
	private void prepareExperimentLists() {
		int maxDataSize = this._parameterSet.maxDataSize();
		
		this._ascendingList = DataGenerator.ascendingOrderList(maxDataSize);
		this._descendingList = DataGenerator.descendingOrderList(maxDataSize);
		this._randomList = DataGenerator.randomOrderList(maxDataSize);
	}
	
	
	
	// Set ParameterSet to default : called by constructor
	private void setParameterSetWithDefaults() {
		this._parameterSet = new ParameterSet(DEFAULT_STARTING_SIZE, 
											  DEFAULT_NUMBER_OF_SIZE_INCREASING_STEPS, 
											  DEFAULT_INCREMENT_SIZE);
	}
	
	
	
	// Return List matches with Parameter anOrder
	private Integer[] experimentListOfOrder(ListOrder anOrder) {
		switch(anOrder) {
		case Ascending:
			return this._ascendingList;
		case Descending:
			return this._descendingList;
		default:
			return this._randomList;
		}
	}
	
	
}
