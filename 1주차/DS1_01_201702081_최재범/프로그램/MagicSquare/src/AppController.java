// 전체적 제어 정보

public class AppController {
	
	private AppView _appView;
	private MagicSquare _magicSquare;
	
	// 생성자
	// 기능 사용을 위한 객체 생성
	public AppController() {
		this._appView = new AppView();
		this._magicSquare = new MagicSquare();
	}
	
	
	// 시작
	public void run() {
		
		this._appView.outputLine("<<< 마방진 풀이를 시작합니다. >>>");
		this._appView.outputLine("");
	
		
		// 마방진 크기 입력
		int order = this._appView.inputOrder();
		
		while(order >= 0) {
			
			// 입력값 판단
			OrderValidity currentOrderValidity = this._magicSquare.checkOrderValidity(order);
			
			if(currentOrderValidity == OrderValidity.Valid) {
				
				// 마방진 크기 표시
				this._appView.outputTitleWithOrder(order);
				
				// 계산 후 출력
				Board solvedBoard = this._magicSquare.solve(order);
				this.showBoard(solvedBoard);
			}
			
			else {
				this.showOrderValidityErrorMessage(currentOrderValidity);
			}
			
			// 새로운 크기 입력
			order = this._appView.inputOrder();
		}
		
		this._appView.outputLine("");
		this._appView.outputLine("<<< 마방진 풀이를 종료합니다. >>>");
	}
	
	
	// 에러 출력
	private void showOrderValidityErrorMessage(OrderValidity anOrderValidity) {
		
		switch(anOrderValidity) {
			case TooSmall :
				this._appView.outputLine("오류 : 차수가 너무 작습니다. 3 보다 크거나 같아야 합니다.");
				break;
			case TooLarge :
				this._appView.outputLine("오류 : 차수가 너무 큽니다. 99 보다 작거나 같아야 합니다.");
				break;
			case NotOddNumber :
				this._appView.outputLine("오류 : 차수가 짝수입니다. 홀수이어야 합니다.");
				break;
			default :
				break;
		}	
	}
	
	
	// 마방진의 열 인덱스 출력 (showBoard()에 사용)
	private void showTitleForColumnIndexes(int anOrder) {
		
		this._appView.output("      ");
		for(int col = 0; col < anOrder; col++) {
			this._appView.output(String.format(" [%3d]", col));
		}
		this._appView.outputLine("");
	}
	
	
	// 마방진 전체 출력
	private void showBoard(Board aBoard) {
		
		CellLocation currentLoc = new CellLocation();
		
		// 열 인덱스 출력
		this.showTitleForColumnIndexes(aBoard.order());
		
		// 행 인덱스 출력
		for(int row = 0; row < aBoard.order(); row++) {
			this._appView.outputRowNumber(row);
			
			// 행 원소 출력
			for(int col = 0; col < aBoard.order(); col++) {
				currentLoc.setRow(row);
				currentLoc.setCol(col);
				this._appView.outputCell(aBoard.cell(currentLoc));
			}	
		this._appView.outputLine("");
		}
	}

}

