// 삽입 정렬

public class InsertionSort<E extends Comparable<E>> extends Sort<E> {
	
	// 생성자
	public InsertionSort() {}
	
	
	// 삽입 정렬
	public boolean sort(E[] aList, int aSize) {
		
		if( (aSize < 1) || (aSize > aList.length) ) {
			return false;
		}
		
		
		// 최솟값 위치 탐색 후 0 번 인덱스로 이동
		int minLoc = 0;
		for(int i = 1; i < aSize; i++) {
			if( aList[i].compareTo(aList[minLoc]) < 0 ) {
				minLoc = i;
			}
		}
		this.swap(aList, 0, minLoc);
		
		
		// 리스트 탐색해가며 맞는 위치에 삽입
		for(int i = 2; i < aSize; i++) {
			E insertedElement = aList[i];	// 대상 원소를 미리 저장해놓음 (원래 위치에서 덮어씌워짐)
			int insertionLoc = i - 1;
			
			// 삽입할 원소보다 더 크다면 거기서부터 원소 한칸씩 뒤로 당김 (앞쪽의 공간 생성)
			while(aList[insertionLoc].compareTo(insertedElement) > 0) {
				aList[insertionLoc + 1] = aList[insertionLoc];
				insertionLoc--;
			}
			
			// 삽입할 원소보다 더 작은 원소를 가리키게됨
			// 최솟값은 0번 인덱스에 존재하기 때문에 끝까지 가도 벗어남
			aList[insertionLoc + 1] = insertedElement;
		}
		
		return true;
		
	}
	
}
