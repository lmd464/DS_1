
public class AppController 
{
	
	private AppView _appView;
	private CircularlyLinkedQueue <Character> _queue;
	private int _inputChars;		// 입력된 문자의 개수
	private int _ignoredChars;	// 무시된 문자의 개수
	private int _addedChars;		// 삽입된 문자의 개수
	
	
	// 생성자
	public AppController() {
		this._appView = new AppView();
		this.initCharCounts();
	}
	
	
	
	// 비공개 함수
	// 출력 관련
	
	// 맨 위 원소 출력
	private void showFrontElement() {
		if(this._queue.isEmpty()) {
			this._appView.outputMessage("[Empty] 큐가 비어있습니다. \n");
		}
		else {
			char frontElement = (char)this._queue.frontElement();
			this._appView.outputFrontElement(frontElement);
		}
	}
	
	
	// Queue 크기 출력
	private void showQueueSize() {
		int size = this._queue.size();
		this._appView.outputQueueSize(size);
	}
	
	
	// Front 부터 전체 출력
	private void showAll() {
		
		this._appView.outputMessage("[Queue]  <Front>");
		
		int size = this._queue.size();
		for(int order = 0; order < size; order++) {
			this._appView.outputElement(this._queue.elementAt(order));
		}
		
		this._appView.outputMessage("  <Rear>\n");
	}
	
	
	
	// 횟수 계산 관련
	// 횟수 초기화
	private void initCharCounts() {
		this._inputChars = 0;
		this._ignoredChars = 0;
		this._addedChars = 0;
	}
	
	// 추가 / 무시 / 입력된 원소 수 셈
	private void countAdded() {
		this._addedChars++;
	}
	private void countIgnored() {
		this._ignoredChars++;
	}
	private void countInputChar() {
		this._inputChars++;
	}
	
	
	// 스택 조작 관련
	// 추가
	private void add(Character anElement) {
		if(this._queue.enQueue(anElement)) {
			this._appView.outputAdd((char)anElement);
			this.countAdded();
		}
		else {
			this.showMessage(MessageID.Error_InputFull);
		}
	}
	
	
	// 1개 제거 후 출력
	private void removeOne() {
		if(this._queue.isEmpty()) {
			this.showMessage(MessageID.Error_Empty);
		}
		else {
			Character removedElement = this._queue.deQueue();
			this._appView.outputRemove(removedElement);
		}
	}
	
	
	// 입력된 개수만큼 제거 후 출력
	private void removeN(int numberOfCharsToBeRemoved) { 
		this._appView.outputRemoveN(numberOfCharsToBeRemoved);
		for(int i = 0; i < numberOfCharsToBeRemoved; i++) {
			this.removeOne();
		}
	}
	
	
	// 전부 제거 후 출력
	private void conclusion() {
		int size = this._queue.size();
		for(int i = 0; i < size; i++) {
			this.removeOne();
		}
		this._appView.outputResult(this._inputChars, this._ignoredChars, this._addedChars);
	}
	
	
	// 상황에 맞는 메시지 출력
	private void showMessage(MessageID aMessageID) {
		switch(aMessageID) {
		
		case Notice_StartProgram : 
			this._appView.outputMessage("> 프로그램을 시작합니다. \n");
			break;
			
		case Notice_EndProgram : 
			this._appView.outputMessage("> 프로그램을 종료합니다. \n");
			break;
			
		case Notice_StartMenu :
			this._appView.outputMessage("[ 큐 사용을 시작합니다. ] \n");
			break;
			
		case Notice_EndMenu :
			this._appView.outputMessage("[ 큐 입력을 종료합니다. ] \n");
			break;
		
		case Error_WrongMenu :
			this._appView.outputMessage("[Error] 의미 없는 문자가 입력되었습니다. \n");
			break;
			
		case Error_InputFull :
			this._appView.outputMessage("[Full] 큐가 꽉 차서 삽입이 불가능합니다. \n");
			break;
			
		case Error_Empty :
			this._appView.outputMessage("[Empty] 큐에 삭제할 원소가 없습니다. \n");
			break;
		
		}
	}
	
	
	
	// 공개 함수
	public void run() {
		
		this._queue = new CircularlyLinkedQueue<Character>();
		char inputChar;		// 메뉴 입력 저장
		
		this.showMessage(MessageID.Notice_StartProgram);
		this.showMessage(MessageID.Notice_StartMenu);
		
		inputChar = this._appView.inputCharacter();
		while(inputChar != '!') {
			
			this.countInputChar();
			if( (inputChar >= 'a' && inputChar <= 'z') || 
				(inputChar >= 'A' && inputChar <= 'Z') ) 
			{
				this.add(inputChar);
			}
			
			else if(inputChar >= '0' && inputChar <= '9') {
				this.removeN( Integer.parseInt( String.valueOf(inputChar) ) );
			}
			else if(inputChar == '-') {
				this.removeOne();
			}
			else if(inputChar == '#') {
				this.showQueueSize();
			}
			else if(inputChar == '/') {
				this.showAll();
			}
			else if(inputChar == '^') {
				this.showFrontElement();
			}
			else {
				this.showMessage(MessageID.Error_WrongMenu);
				this.countIgnored();
			}
			
			inputChar = this._appView.inputCharacter();		// 새로운 입력
		}
		
		this.showMessage(MessageID.Notice_EndMenu);
		this.conclusion();
		this.showMessage(MessageID.Notice_EndProgram);
		
	}
	
}
