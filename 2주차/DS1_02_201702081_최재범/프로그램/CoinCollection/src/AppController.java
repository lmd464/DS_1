
public class AppController {
	
	// 상수
	private static final int MENU_ADD = 1;
	private static final int MENU_REMOVE = 2;
	private static final int MENU_SEARCH = 3;
	private static final int MENU_FREQUENCY = 4;
	private static final int MENU_EXIT = 9;
	
	
	// 인스턴스 변수
	private AppView _appView;
	private ArrayBag<Coin> _coinBag;
	
	
	
	// 생성자
	public AppController() {
		this._appView = new AppView();
	}
	
	
	// Coin 추가
	private void addCoin() {
		int coinValue = this._appView.inputCoinValue();
		if(this._coinBag.add(new Coin(coinValue))) {
			this._appView.outputLine("주어진 값의 동전을 가방에 성공적으로 넣었습니다.");
		}
		else {
			this._appView.outputLine("주어진 값의 동전을 가방에 넣는데 실패하였습니다.");
		}
	}
	
	
	// Coin 제거
	private void removeCoin() {
		int coinValue = this._appView.inputCoinValue();
		
		if(!this._coinBag.remove(new Coin(coinValue))) {
			this._appView.outputLine("주어진 값을 갖는 동전은 가방 안에 존재하지 않습니다.");
		}
		else {
			this._appView.outputLine("주어진 값을 갖는 동전 하나가 가방에서 정상적으로 삭제되었습니다.");
		}
	}
	
	
	// Coin 존재 유무 확인
	private void searchForCoin() {
		int coinValue = this._appView.inputCoinValue();
		if(this._coinBag.doesContain(new Coin(coinValue))) {
			this._appView.outputLine("주어진 값을 갖는 동전이 가방 안에 존재합니다.");
		}
		else {
			this._appView.outputLine("주어진 값을 갖는 동전은 가방 안에 존재하지 않습니다.");
		}		
	}
	
	
	// Coin 개수 출력
	private void frequencyOfCoin() {
		int coinValue = this._appView.inputCoinValue();
		int frequency = this._coinBag.frequencyOf(new Coin(coinValue));
		this._appView.outputLine("주어진 값을 갖는 동전의 개수는 " + frequency + " 개 입니다.");
	}
	
	
	// 번호 잘못 입력 시 오류 출력
	private void undefinedMenuNumber(int menuNumber_p) {
		this._appView.outputLine("선택된 메뉴 번호 " + menuNumber_p + "는 잘못된 번호입니다.");
	}
	
	
	// 전체 Coin의 값 합계 반환
	private int sumOfCoinValues() {
		int sum = 0;
		for(int index = 0; index < this._coinBag.size(); index++) {
			sum += this._coinBag.elementAt(index).value();
		}
		return sum;
	}
	
	
	// 가장 큰 Coin의 값 반환
	private int maxCoinValue() {
		int maxCoinValue = 0;
		for(int index = 0; index < this._coinBag.size(); index++) {
			if(maxCoinValue < this._coinBag.elementAt(index).value()) {
				maxCoinValue = this._coinBag.elementAt(index).value();
			}
		}
		return maxCoinValue;
	}
	
	
	// 가방 내용 정보 출력
	private void showStatistics() {
			this._appView.outputLine("");
			this._appView.outputLine("총 코인의 개수 : " + this._coinBag.size());
			this._appView.outputLine("가장 큰 코인 금액 : " + this.maxCoinValue());
			this._appView.outputLine("전체 금액 : " + this.sumOfCoinValues());
	}

	
	// 시작
	public void run() {
		
		// 시작 메시지
		this._appView.outputLine("<<< 동전 가방 프로그램을 시작합니다. >>>");
		
		// 가방에 넣을 동전 개수 입력
		int capacityOfCoinBag = this._appView.inputCapacityOfCoinBag();
		this._coinBag = new ArrayBag<Coin>(capacityOfCoinBag);
		
		
		// 메뉴 번호 입력
		int menuNumber = this._appView.inputMenuNumber();
		while(menuNumber != MENU_EXIT) {
			
			switch(menuNumber) {
			
			case MENU_ADD:
				this.addCoin();
				break;
				
			case MENU_REMOVE:
				this.removeCoin();
				break;
			
			case MENU_SEARCH:
				this.searchForCoin();
				break;
				
			case MENU_FREQUENCY:
				this.frequencyOfCoin();
				break;
				
			default:
				this.undefinedMenuNumber(menuNumber);
			}
	
			// 새로운 메뉴 번호 입력
			menuNumber = this._appView.inputMenuNumber();
		}
		
		// 가방 안의 동전 정보 출력
		this.showStatistics();
		
		// 종료 메시지
		this._appView.outputLine("<<< 동전 가방 프로그램을 종료합니다. >>>");
	}
}

