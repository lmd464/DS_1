
public class AppController {
	
	private AppView _appView;
	private List<Integer> _sortedList;
	
	
	public AppController() {
		this._appView = new AppView();
	}
	
	
	public void run() {
		
		this._sortedList = new SortedArrayList<Integer>();
		char command = 0;
		int input;
		
		this.showMessage(MessageID.Notice_StartProgram);
		while(command != '!') {
			
			command = this._appView.inputCharacter();
			
			if(command == '%') {
				input = this._appView.inputNumber();
				this.add(input);
			}
			
			else if(command == '~')
				this.reset();
			
			else if(command == '-')
				this.removeMin();
			
			else if(command == '+')
				this.removeMax();
			
			else if(command == '#')
				this.showSize();
			
			else if(command == '?') {
				input = this._appView.inputNumber();
				this.removeFrom(input);
			}
			
			else if(command == '/')
				this.showAll();
			
			else if(command == '!')
				break;
			
			else
				this.showMessage(MessageID.Error_WrongMenu);
			
		}
		
		this.showMessage(MessageID.Notice_EndProgram);
		
	}
	
	
	
	
	
	// 비공개 함수
	private void showSize() {
		this._appView.outputSize(this._sortedList.size());
	}
	
	
	
	private void reset() {
		this.showMessage(MessageID.Notice_Reset);
		this._sortedList.clear();
	}
	
	
	
	private void showAll() {
		this.showMessage(MessageID.Notice_ShowStartList);
		
		/*
		for(int i = 0; i < this._sortedList.size(); i++) {
			this._appView.outputElement(this._sortedList.elementAt(i));
		}
		*/
		
		Iterator<Integer> iterator = this._sortedList.listIterator();
		while(iterator.hasNext()) {
			this._appView.outputElement( (int)iterator.next() );
		}
		
		this.showMessage(MessageID.Notice_ShowEndList);
	}
	
	
	
	private void add(int inputValue) {
		Integer inputElement = inputValue;
		boolean isAdded = this._sortedList.add(inputElement);
		
		if(!isAdded) {
			this.showMessage(MessageID.Error_InputFull);
		}
		else {
			this._appView.outputAdd(inputElement);
		}
		
	}
	
	
	
	private void removeMin() {
		if(this._sortedList.isEmpty()) {
			this.showMessage(MessageID.Error_Empty);
		}
		else {
			int removedMin = this._sortedList.removeMin();
			this._appView.outputRemove(removedMin);
		}
	}
	
	
	
	private void removeMax() {
		if(this._sortedList.isEmpty()) {
			this.showMessage(MessageID.Error_Empty);
		}
		else {
			int removedMax = this._sortedList.removeMax();
			this._appView.outputRemove(removedMax);
		}
	}
	
	
	
	private void removeFrom(int aPosition) {
		if(this._sortedList.isEmpty()) {
			this.showMessage(MessageID.Error_Empty);
		}
		else {
			int removedElement = this._sortedList.removeFrom(aPosition);
			this._appView.outputRemove(removedElement);
		}
	}
	
	
	
	private void showMessage(MessageID aMessageID) {
		switch(aMessageID) {
		
		case Notice_StartProgram:
			this._appView.outputMessage("< 리스트를 시작합니다. > \n");
			break;
			
		case Notice_EndProgram:
			this._appView.outputMessage("\n< 리스트를 종료합니다. > \n");
			break;
			
		case Notice_Reset:
			this._appView.outputMessage("- 리스트를 초기화 합니다. \n");
			break;
			
		case Notice_ShowStartList:
			this._appView.outputMessage("[LIST] ");
			break;
			
		case Notice_ShowEndList:
			this._appView.outputMessage("\n");
			break;
			
		case Error_WrongMenu:
			this._appView.outputMessage("! 잘못된 메뉴를 입력하였습니다. \n");
			break;
			
		case Error_InputFull:
			this._appView.outputMessage("! 리스트가 꽉 찼습니다. \n");
			break;
			
		case Error_Empty:
			this._appView.outputMessage("! 리스트가 비어 있습니다. \n");
			break;
			
		}
		
		
		
		
		
		
	}
	
}
