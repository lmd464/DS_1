// 정렬의 기본 베이스
// 추상 클래스 : 인스턴스 생성할 때 반드시 상속이 필요

public abstract class Sort<E extends Comparable<E>> {
	
	protected void swap(E[] aList, int i, int j) {
		E tempElement = aList[i];
		aList[i] = aList[j];
		aList[j] = tempElement;
	}
	
	
	// 생성자
	protected Sort() {}
	
	
	// 정렬
	public abstract boolean sort(E[] aList, int aSize);

}
