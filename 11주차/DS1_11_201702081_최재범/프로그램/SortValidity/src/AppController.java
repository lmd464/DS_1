
public class AppController {
	
	
	private static final int DEFAULT_TEST_SIZE = 10000;
	private static final int DEFAULT_FIRST_PART_SIZE = 5;
	private static final InsertionSort<Integer> 	INSERTION_SORT = new InsertionSort<Integer>();
	private static final QuickSort<Integer>		QUICK_SORT = new QuickSort<Integer>();
	
	private AppView _appView;
	private Integer[] _list;
	private ListOrder _listOrder;	// 리스트 종류 저장하는 임시 변수
	
	
	public AppController() {
		this._appView = new AppView();
	}

		
	public void run() {
		this._appView.outputLine("<<< 정렬 알고리즘의 정렬 결과를 검증하는 프로그램을 시작합니다. >>>");
		this._appView.outputLine("");
		
		this._appView.outputLine("> 정렬 결과의 검증 : ");
		this._appView.outputLine("");
		
		this.validateWithAscendingOrderList();
		this.validateWithDescendingOrderList();
		this.validateWithRandomOrderList();
		
		this._appView.outputLine("<<< 정렬 알고리즘의 정렬 결과를 검증하는 프로그램을 종료합니다. >>>");
	}
	
	
	
	
	
	// 비공개 함수
	
	// 오름차순 리스트 생성하여 보여준 후 정렬, 결과 검증
	private void validateWithAscendingOrderList() {
		this._listOrder = ListOrder.Ascending;
		this._list = DataGenerator.ascendingOrderList(DEFAULT_TEST_SIZE);
		this.showFirstPartOfDataList();
		this.validateSortsAndShowResult();
	}
	
	
	// 내림차순 리스트 생성하여 보여준 후 정렬, 결과 검증
	private void validateWithDescendingOrderList() {
		this._listOrder = ListOrder.Descending;
		this._list = DataGenerator.descendingOrderList(DEFAULT_TEST_SIZE);
		this.showFirstPartOfDataList();
		this.validateSortsAndShowResult();
	}
	
	
	// 랜덤 리스트 생성하여 보여준 후 정렬, 결과 검증
	private void validateWithRandomOrderList() {
		this._listOrder = ListOrder.Random;
		this._list = DataGenerator.randomOrderList(DEFAULT_TEST_SIZE);
		this.showFirstPartOfDataList();
		this.validateSortsAndShowResult();
	}
	
	
	// 정렬할 데이터 표본 출력
	private void showFirstPartOfDataList() {
		this._appView.output(
				"[" + this._listOrder.orderName() + " 리스트] 의 앞 부분 : ");
		
		for(int i = 0; i < DEFAULT_FIRST_PART_SIZE; i++) {
			this._appView.output(" " + this._list[i].toString() + "  ");
		}
		
		this._appView.outputLine("");
	}
	
	
	// 두 가지 방법으로 정렬한 후 검증하여 결과 출력 명령
	private void validateSortsAndShowResult() {
		this.validateSort(AppController.INSERTION_SORT);
		this.validateSort(AppController.QUICK_SORT);
		this._appView.outputLine("");
	}
	
	
	// 매개변수로 받은 방법으로 정렬 후 검증하여 결과 출력
	private void validateSort(Sort<Integer> aSort) {
		Integer[] list = this.copyList(this._list);	// 복사 : 원본 리스트는 유지되어야함 (여러 번 사용)
		aSort.sort(list, list.length);
		this.showValidationMessage(aSort, list);
	}
	
	
	// Integer[] 복사
	private Integer[] copyList(Integer[] aList) {
		Integer[] copiedList = new Integer[aList.length];
		for(int i = 0; i < aList.length; i++) {
			copiedList[i] = aList[i];
		}
		return copiedList;
	}
	
	
	private void showValidationMessage(Sort<Integer> aSort, Integer[] aList) {
		this._appView.output(
				"[" + this._listOrder.orderName() + " 리스트] 를 [" +
				aSort.getClass().getSimpleName() + "] 한 결과는 ");
		if(this.sortedListIsValid(aList)) {
			this._appView.outputLine("올바릅니다.");
		}
		else {
			this._appView.outputLine("올바르지 않습니다.");
		}
	}
	
	
	// 오름차순인지 검증
	private boolean sortedListIsValid(Integer[] aList) {
		for(int i = 0; i < (aList.length - 1); i++) {
			if( aList[i].compareTo(aList[i + 1]) > 0 ) {
				return false;
			}
		}
		return true;
	}
	
	
}
