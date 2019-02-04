// 리스트의 종류 구분

public enum ListOrder {
	Ascending, 		// 오름차순 리스트 유형
	Descending, 		// 내림차순 리스트 유형
	Random;			// 무작위 리스트 유형
	
	public static final String[] ORDER_NAMES = { "오름차순", "내림차순", "무작위" };
	
	
	// ListOrder 인스턴스가 가진 리스트 유형의 한글명 반환
	public String orderName() {
		return ListOrder.ORDER_NAMES[this.ordinal()];
	}
	
}
