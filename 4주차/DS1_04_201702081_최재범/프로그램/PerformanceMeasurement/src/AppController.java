// 총체적인 제어 담당

public class AppController {
	
	private AppView _appView;
	private PerformanceMeasurement _pm;
	
	// 생성자
	public AppController() {
		this._appView = new AppView();
	}
	
	
	
	
	
	// 실행
	public void run() {
		
		this._pm = new PerformanceMeasurement();
		
		this.showMessage(MessageID.Notice_StartProgram);
		this._pm.generateData();
		
		this.showMessage(MessageID.Notice_UnsortedArrayStart);		// 자료구조 이름 명시
		this._pm.testUnsortedArrayBag();
		
		this.showTestResults();
		
		this.showMessage(MessageID.Notice_EndProgram);
		
	}
	
	
	// 상황에 맞는 메시지 출력
	private void showMessage(MessageID aMessageID) {
		switch(aMessageID) {
		case Notice_StartProgram:
			this._appView.outputMessage("<<< 실행 성능 차이 알아보기 >>> \n");
			break;
			
		case Notice_EndProgram:
			this._appView.outputMessage("<<< 성능 측정을 종료합니다. >>> \n");
			break;
			
		case Notice_UnsortedArrayStart:
			this._appView.outputMessage("[Unsorted Array List] \n");
			break;
			
		case Error_WrongMenu:
			this._appView.outputMessage("<<< Error : 잘못된 메뉴입니다. >>> \n");
			break;
			
		default:
			break;
		}
	}
	
	
	// 결과 출력
	private void showTestResults() {
		TestResult testResults[] = this._pm.testResults();
		for(int index = 0; index < this._pm.numberOfTests(); index++) {
			this._appView.outputResult(testResults[index].testSize(), 
									   testResults[index].testInsertTime(), 
									   testResults[index].testFindMaxTime());
		}
	}
	
}
