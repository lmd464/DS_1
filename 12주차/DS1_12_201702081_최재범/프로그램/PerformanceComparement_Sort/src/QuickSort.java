
public class QuickSort<E extends Comparable<E>> extends Sort<E> {
	
	
	// 생성자
	public QuickSort() {}
	
	
	public boolean sort(E[] aList, int aSize) {
		if(aSize < 1 || aSize > aList.length) {
			return false;
		}
		else {
			
			// 최댓값 위치 탐색 후 맨 끝 인덱스로 이동
			int maxLoc = 0;
			for(int i = 0; i < aSize - 1; i++) {
				if( aList[i].compareTo(aList[maxLoc]) > 0 ) {
					maxLoc = i;
				}
			}
			
			if( aList[maxLoc].compareTo(aList[aSize - 1]) > 0 ) {
				this.swap(aList, aSize - 1, maxLoc);
			}
			this.quickSortRecursively(aList, 0, aSize - 2);
			return true;
			
		}
	}
	
	
	
	
	
	// 비공개 함수
	
	// pivot 위치 결정 : 왼쪽으로
	private int pivot(E[] aList, int left, int right) {
		return left;
	}
	
	
	// partition 수행 후 pivot 위치 반환
	private int partition(E[] aList, int left, int right) {

		int pivot = this.pivot(aList, left, right);
		int toRightIndex = left + 1;
		int toLeftIndex = right;
		
		while(toRightIndex <= toLeftIndex) {
			
			// toRightIndex : pivot 보다 작을 시 맞는 위치 (오름차순)
			while( aList[toRightIndex].compareTo(aList[pivot]) < 0 ) {
				toRightIndex++;
			}
		
			// toLeftIndex : pivot 보다 클 시 맞는 위치 (오름차순)
			while( aList[toLeftIndex].compareTo(aList[pivot]) > 0 ) {
				toLeftIndex--;
			}
		
			if(toRightIndex < toLeftIndex) {
				this.swap(aList, toRightIndex, toLeftIndex);
			}
		}
		
		this.swap(aList, toLeftIndex, pivot);
		
		// pivot 위치 반환
		return toLeftIndex;
	}
		
	
	// 재귀정렬
	private void quickSortRecursively(E[] aList, int left, int right) {
		if(left < right) {
			int mid = this.partition(aList, left, right);
			this.quickSortRecursively(aList, left, mid - 1);
			this.quickSortRecursively(aList, mid + 1, right);
		}
		// 정렬이 끝난 상태라면 아무것도 하지 않음
	}
	
		
}
