
public class AppController {
	
	// 상수
	private static final int MENU_ADD = 1;
	private static final int MENU_REMOVE_INPUT = 2;
	private static final int MENU_REMOVE_RANDOM = 3;
	private static final int MENU_PRINT = 4;
	private static final int MENU_SEARCH_NAME = 5;
	private static final int MENU_SEARCH_COORDINATE = 6;
	private static final int MENU_EXIT = 9;
	
	
	// 변수
	private AppView _appView;
	private LinkedSet<Star> _starCollector;
	
	
	
	// 생성자
	public AppController() {
		this._appView = new AppView();
		this._starCollector = null;
	}
	
	
	
	
	// Star 추가
	private void inputStar() {
		
		int xCoordinate, yCoordinate;	// 좌표
		String starName;	
		
		
		this.showMessage(MessageID.Notice_InputStar);
		
		
		// 좌표, 이름 입력
		this.showMessage(MessageID.Notice_InputStarXCoordinate);
		xCoordinate = this._appView.inputInt();
		
		this.showMessage(MessageID.Notice_InputStarYCoordinate);
		yCoordinate = this._appView.inputInt();
		
		this.showMessage(MessageID.Notice_InputStarName);
		starName = this._appView.inputString();
		
		
		// Star를 starCollector에 추가, 실패 시 에러
		if(!this._starCollector.add(new Star(xCoordinate, yCoordinate, starName))) {
			this.showMessage(MessageID.Error_Input);
		}
		
	}
	
	
	// 이름 검색하여 삭제
	private void removeByName() {
		
		String starName;
		
		this.showMessage(MessageID.Notice_RemoveStar);
		
		this.showMessage(MessageID.Notice_InputStarName);
		starName = this._appView.inputString();
		
		// starName을 이름으로 가지는 Star 하나 삭제, 실패 시 에러
		if(!this._starCollector.remove(new Star(starName))) {
			this.showMessage(MessageID.Error_Remove);
		}
		
	}
	
	
	// 맨 뒤의 Star 삭제
	private void removeAnyStar() {
		
		this.showMessage(MessageID.Notice_RemoveRandomStar);
		Star removedStar = this._starCollector.removeAny();
		
		// 제거된 Star 정보 출력, 싪패 시 에러
		if(removedStar != null) {
			this._appView.outputStar(removedStar.starName(), 
									removedStar.xCoordinate(), 
									removedStar.yCoordinate());
		}
		else {
			this.showMessage(MessageID.Error_Remove);
		}
		
	}
	
	
	// 이름으로 검색하여 존재여부 확인
	private void searchByName() {
		
		String starName;
		
		this.showMessage(MessageID.Notice_SearchByName);
		
		this.showMessage(MessageID.Notice_InputStarName);
		starName = this._appView.inputString();
		
		// 이름으로 검색하여 존재할 시 출력, 실패 시 에러
		if(!this._starCollector.doesContain(new Star(starName))) {
			this._appView.outputMessage("원하는 별이 존재하지 않습니다.");
		}
		else {
			this._appView.outputStarExistence(starName);
		}
	 }
	
	
	// 좌표로 검색하여 존재여부 확인
	private void searchByCoordinate() {
		
		int xCoordinate, yCoordinate;
		
		this.showMessage(MessageID.Notice_SearchByCoordinate);
		
		this.showMessage(MessageID.Notice_InputStarXCoordinate);
		xCoordinate = this._appView.inputInt();
		
		this.showMessage(MessageID.Notice_InputStarYCoordinate);
		yCoordinate = this._appView.inputInt();
		
		// 좌표로 검색하여 존재할 시 출력, 실패 시 에러
		if(!this._starCollector.doesContain(new Star(xCoordinate, yCoordinate))) {
			this._appView.outputMessage("원하는 별이 존재하지 않습니다.");
		}
		else {
			this._appView.outputStarExistence(xCoordinate, yCoordinate);
		}
	}
	
	

	
	
	// 상황에 맞는 메시지 출력
	private void showMessage(MessageID aMessageID) {
		
		switch(aMessageID) {
		
		// Notices
		case Notice_StartProgram:
			this._appView.outputMessage("< 별의 집합을 시작합니다. > \n\n");
			break;
			
		case Notice_EndProgram:
			this._appView.outputMessage("< 별의 집합을 종료합니다. > \n\n");
			break;
			
		case Notice_Menu:
			this._appView.outputMessage("\n");
			this._appView.outputMessage("1: 입력	2: 주어진 별 삭제	3: 임의의 별 삭제 \n");
			this._appView.outputMessage("4: 출력	5: 이름으로 검색	6: 좌표로 검색		9: 종료 \n");
			this._appView.outputMessage("원하는 메뉴를 입력하세요 : ");
			break;
			
		case Notice_EndMenu:
			this._appView.outputMessage("\n-    [종료]    - \n");
			break;
			
		case Notice_InputStar:
			this._appView.outputMessage("\n-    [별 추가]    - \n");
			break;
		
		case Notice_InputStarName: 
			this._appView.outputMessage("- 별의 이름을 입력하시오 : ");
			break;
		
		case Notice_InputStarXCoordinate:
			this._appView.outputMessage("- x 좌표를 입력하시오 : ");
			break;
		
		case Notice_InputStarYCoordinate: 
			this._appView.outputMessage("- y 좌표를 입력하시오 : ");
			break;
		
		case Notice_RemoveStar:
			this._appView.outputMessage("\n-    [주어진 별 삭제]    - \n");
			break;
		
		case Notice_RemoveRandomStar:
			this._appView.outputMessage("\n-    [임의의 별 삭제]    - \n");
			break;
			
		case Notice_Show:
			this._appView.outputMessage("\n-    [개수 출력]    - \n");
			break;
			
		case Notice_SearchByName: 
			this._appView.outputMessage("\n-    [이름으로 검색]    - \n");
			break;
		
		case Notice_SearchByCoordinate: 
			this._appView.outputMessage("\n-    [좌표로 검색]    - \n");
			break;
			
			
		// Errors
		case Error_WrongMenu:
			this._appView.outputMessage("ERROR : 잘못된 메뉴 번호 \n");
			break;
		
		case Error_Input:
			this._appView.outputMessage("ERROR : 추가 실패 \n");
			break;
		
		case Error_Remove:
			this._appView.outputMessage("ERROR : 삭제 실패 \n");
			break;
		}
		
	}
	
	
	
	// 시작
	public void run() {
		this._starCollector = new LinkedSet<Star>();
		
		this.showMessage(MessageID.Notice_StartProgram);
		int command = 0;
		
		while(command != MENU_EXIT) {
			try {
				this.showMessage(MessageID.Notice_Menu);
				command = this._appView.inputInt();
				
				switch(command) {
				
				case MENU_ADD:
					this.inputStar();
					break;
				
				case MENU_REMOVE_INPUT:
					this.removeByName();
					break;
				
				case MENU_REMOVE_RANDOM:
					this.removeAnyStar();
					break;
				
				case MENU_PRINT:
					this.showMessage(MessageID.Notice_Show);
					this._appView.outputNumberOfStars(this._starCollector.size());
					break;
					
				case MENU_SEARCH_NAME:
					this.searchByName();
					break;
					
				case MENU_SEARCH_COORDINATE:
					this.searchByCoordinate();
					break;
				
				}	
			}
			
			// Exception catch
			catch(Exception ex) {
				System.out.println("Error Message : " + ex.getMessage());
				continue;
			}
		}
		
		this.showMessage(MessageID.Notice_EndMenu);
		this._appView.outputNumberOfStars(this._starCollector.size());
		
		this.showMessage(MessageID.Notice_EndProgram);
	}
	
}





