
public class AppController {
	
	private AppView _appView;
	private ArrayList <Character> _arrayStack;
	private int _inputChars;		// 입력된 문자의 개수
	private int _ignoredChars;	// 무시된 문자의 개수
	private int _addedChars;		// 삽입된 문자의 개수
	
	
	// 생성자
	public AppController() {
		this._appView = new AppView();
		this._inputChars = 0;
		this._ignoredChars = 0;
		this._addedChars = 0;
	}
	
	
	// 비공개 함수
	
	// 출력 관련
	// Bottom 부터 전체 출력
	private void showAllFromBottom() {
		this.showMessage(MessageID.Notice_ShowStack);
		this.showMessage(MessageID.Show_StartBottom);
		
		for(int index = 0; index < this._arrayStack.size(); index++) {
			this._appView.outputStackElement
						 ((Character)this._arrayStack.elementAt(index).charValue());
		}
		this.showMessage(MessageID.Show_EndTop);
	}
	
	
	// Top 부터 전체 출력
	private void showAllFromTop() {
		this.showMessage(MessageID.Notice_ShowStack);
		this.showMessage(MessageID.Show_StartTop);
		
		for(int index = this._arrayStack.size() - 1; index >= 0; index--) {
			this._appView.outputStackElement((Character)(this._arrayStack.elementAt(index)).charValue());
		}
		this.showMessage(MessageID.Show_EndBottom);
	}
	
	
	// 맨 위 원소 출력
	private void showTopElement() {
		if(this._arrayStack.isEmpty()) {
			this._appView.outputMessage("[Empty] 스택에 원소가 존재하지 않습니다. \n");
		}
		else {
			Character topElement = this._arrayStack.peek();
			this._appView.outputTopElement(topElement.charValue());
		}
	}
	
	// 스택 크기 출력
	private void showStackSize() {
		this._appView.outputStackSize(this._arrayStack.size());
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
			this._appView.outputMessage("[ 스택 사용을 시작합니다. ] \n");
			break;
			
		case Notice_EndMenu :
			this._appView.outputMessage("[ 스택 입력을 종료합니다. ] \n");
			break;
			
		case Notice_InputStack :
			this._appView.outputMessage("[Push] 스택에 원소를 삽입합니다. \n");
			break; 
			
		case Notice_DelStack :
			this._appView.outputMessage("[Pop] 스택의 원소를 제거합니다. \n");
			break; 
			
		case Notice_ShowStack :
			this._appView.outputMessage("[Stack]   ");
			break;
			
		case Show_StartBottom :
			this._appView.outputMessage("<Bottom>   ");
			break;
			
		case Show_StartTop :
			this._appView.outputMessage("<Top>   ");
			break;
			
		case Show_EndTop :
			this._appView.outputMessage("   <Top> \n");
			break;
			
		case Show_EndBottom :
			this._appView.outputMessage("   <Bottom> \n");
			break;
		
		case Error_WrongMenu :
			this._appView.outputMessage("[Error] 의미 없는 문자가 입력되었습니다. \n");
			break;
			
		case Error_InputFull :
			this._appView.outputMessage("[Full] 스택이 꽉 차서 삽입이 불가능합니다. \n");
			break;
			
		case Error_RemoveEmpty :
			this._appView.outputMessage("[Empty] 스택에 삭제할 원소가 없습니다. \n");
			break;
		
		}
	}
	
	
	// 횟수 계산 관련
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
	private void addToStack(char inputChar) {
		if(this._arrayStack.push(inputChar)) {
			this._appView.outputAddedElement(inputChar);
			this.countAdded();
		}
		else {
			this.showMessage(MessageID.Error_InputFull);
			this.countIgnored();
		}
	}
	
	
	// 1개 제거 후 출력
	private void removeOne() {
		if(this._arrayStack.isEmpty()) {
			this.showMessage(MessageID.Error_RemoveEmpty);
			this.countIgnored();
		}
		else {
			this._appView.outputRemove(this._arrayStack.pop().charValue());
		}
	}
	
	
	// 입력된 개수만큼 제거 후 출력
	private void removeN(int numberOfCharsToBeRemoved) {
		for(int i = 0; i < numberOfCharsToBeRemoved; i++) {
			this.removeOne();
		}
	}
	
	// 전부 제거 후 출력
	private void conclusion() {
		int size = this._arrayStack.size();
		for(int i = 0; i < size; i++) {
			this.removeOne();
		}
		this._appView.outputResult(this._inputChars, this._ignoredChars, this._addedChars);
	}
	
	
	// 공개 함수
	public void run() {
		
		this._arrayStack = new ArrayList<Character>();
		char input;		// 메뉴 입력 저장
		
		this.showMessage(MessageID.Notice_StartProgram);
		this.showMessage(MessageID.Notice_StartMenu);
		
		input = this._appView.inputCharacter();
		
		while(input != '!') {
			
			this.countInputChar();
			if( (input >= 'a' && input <= 'z') || 
				(input >= 'A' && input <= 'Z') ) {
				this.addToStack(input);
			}
			else if(input >= '0' && input <= '9') {
				this.removeN(input - '0');
			}
			else if(input == '-') {
				this.removeOne();
			}
			else if(input == '#') {
				this.showStackSize();
			}
			else if(input == '/') {
				this.showAllFromBottom();
			}
			else if(input == '\\') {
				this.showAllFromTop();
			}
			else if(input == '^') {
				this.showTopElement();
			}
			else {
				this.showMessage(MessageID.Error_WrongMenu);
				this.countIgnored();
			}
			
			input = this._appView.inputCharacter();		// 새로운 입력
		}
		
		this.showMessage(MessageID.Notice_EndMenu);
		this.conclusion();
		this.showMessage(MessageID.Notice_EndProgram);
		
	}
	
}
