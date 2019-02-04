
public class AppController {
	
	
	// Private instance variables
	private AppView				 _appView;
	private ExperimentManager	 _manager;
	
	
	
	// Constructor
	public AppController() {
		this._appView = new AppView();
		this._manager = new ExperimentManager();
	}
	
	
	
	// ***** RUN *****
	public void run() {
		this._appView.outputLine("<<< 정렬 성능 비교 프로그램을 시작합니다. >>>");
		this._appView.outputLine("");
		
		{
			this._appView.outputLine(">> 2 가지 정렬의 성능 비교 : 삽입, 퀵 <<");
			this._manager.prepareExperiment(null);		// Use Default ParameterSet
			this.measureAndShowResultFor(ListOrder.Ascending);
			this.measureAndShowResultFor(ListOrder.Descending);
			this.measureAndShowResultFor(ListOrder.Random);
		}
		
		this._appView.outputLine("<<< 정렬 성능 비교 프로그램을 종료합니다. >>>");
	}
	
	
	
	
	
	// Private Methods
	
	private void showTableTitle(ListOrder anOrder) {
		this._appView.outputLine(
				"> " + anOrder.orderName() + " 데이터를 사용하여 실행한 측정 : "
				);
	}
	
	
	
	private void showTableHead() {
		this._appView.outputLine(
			String.format("%8s", " ") + 
			String.format("%16s", "<Insertion Sort>") + 
			String.format("%16s", "<Quick Sort>")
		);
	}
	
	
	
	// Show Results when measurement is finished
	private void showMeasuredResult() {
		
		int startingSize = this._manager.parameterSet().startingSize();
		int incrementSize = this._manager.parameterSet().incrementSize();
		int numberOfSteps = this._manager.parameterSet().numberOfSizeIncreasingSteps();
		
		
		for(int step = 0; step < numberOfSteps; step++) {
			int sortingSize = startingSize + (incrementSize * step);
			this._appView.outputLine(
				"[" + String.format("%5d", sortingSize) + "]" + 
				String.format("%16d", this._manager.measuredResultForInsertionSortAt(step)) +
				String.format("%16d", this._manager.measuredResultForQuickSortAt(step))
			);
		}
		
	}
	
	
	
	// Start measurement when Data is prepared
	private void measureAndShowResultFor(ListOrder anOrder) {
		this.showTableTitle(anOrder);
		this.showTableHead();
		this._manager.performExperiment(anOrder);
		this.showMeasuredResult();
		this._appView.outputLine("");
	}
	
	
}
