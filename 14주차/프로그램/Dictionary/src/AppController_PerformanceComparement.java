
public class AppController_PerformanceComparement {
	
	// 성능 측정 설정에 사용되는 상수
	private static final int APP_NUMBER_OF_EXPERIMENTS = 5;
	private static final int APP_FIRST_DATA_SIZE = 10000;
	private static final int APP_SIZE_INCREMENT = 10000;
	
	
	// 인스턴스 변수
	private AppView _appView;
	private PerformanceMeasurement _measurement;
	
	
	// 생성자
	public AppController_PerformanceComparement() {
		this._appView = new AppView();
	}
	
	
	public void run() {
		this._appView.outputLine("<< \"Dictionary\" 의 성능 측정을 시작합니다. >>> \n");
		this._measurement = new PerformanceMeasurement(APP_NUMBER_OF_EXPERIMENTS, 
														APP_FIRST_DATA_SIZE, 
														APP_SIZE_INCREMENT);

		this.showExperimentByListOrderType(ListOrder.Ascending);
		this.showExperimentByListOrderType(ListOrder.Descending);
		this.showExperimentByListOrderType(ListOrder.Random);
		
		
		this._appView.outputLine("<< \"Dictionary\" 의 성능 측정을 종료합니다. >>> \n");
	}
	
	
	
	
	
	// 비공개 함수
	
	
	// 데이터 종류 설정 후 측정, 출력
	private void showExperimentByListOrderType(ListOrder anOrder) {
		this._appView.outputLine("< " + anOrder.toStringInKorean() + " 데이터를 사용한 측정 (단위 : mili second) >");
		
		this.showExperimentByDictionaryAndListOrderType (
		  new DictionaryBySortedArray<Integer, String> (this._measurement.parameterSet().maxDataSize()), 
		  anOrder );
		
		this.showExperimentByDictionaryAndListOrderType (
		  new DictionaryBySortedLinkedList<Integer, String> (), 
		  anOrder );
		
		this.showExperimentByDictionaryAndListOrderType (
		  new DictionaryByBinarySearchTree<Integer, String> (), 
		  anOrder );
		
		
		this._appView.outputLine("");
	}
	
	
	
	// 사전 종류, 데이터 종류 설정 후 측정, 출력
	private void showExperimentByDictionaryAndListOrderType( Dictionary<Integer, String> aDictionary, 
															ListOrder anOrder )
	{
		this._appView.outputLine("\"" + aDictionary.getClass().getName() + "\" 로 구현된 사전의 성능 : ");
		this._appView.output(String.format("%6s", "크기 "));
		this._appView.output(String.format("%11s", "삽입"));
		this._appView.output(String.format("%11s", "검색"));
		this._appView.outputLine(String.format("%11s", "삭제"));
		
		UnitExperimentResult[] results = this._measurement.experimentByDictionaryAndListOrderType
										(aDictionary, anOrder);
		
		// 반복 회차별 결과 출력
		for( int iteration = 0; 
				 iteration < this._measurement.parameterSet().numberOfExperiments(); 
				 iteration++ ) 
		{
			this.showUnitExperimentResult(results[iteration]);
		}
	}
	
	
	
	// 측정 완료된 결과를 출력
	private void showUnitExperimentResult(UnitExperimentResult aResult) {
		this._appView.output( String.format( "[%5d]", aResult.experimentSize() 	  	 )   );
		this._appView.output( String.format( "%12d",  aResult.timeForAdd() 	/ 1000   )   );
		this._appView.output( String.format( "%12d",  aResult.timeForSearch() / 1000   )   );
		this._appView.outputLine( String.format( "%12d",  aResult.timeForRemove() / 1000)	 );
	}
	
	
	
}
