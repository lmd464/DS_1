// 데이터 생성
// static class를 구현하기 위하여, 모든 멤버는 static으로 선언하고 클래스는 final로

import java.util.Random;


public final class DataGenerator {
	
	
	// 인스턴스 생성 불가
	private DataGenerator() {}
	
	
	// 오름차순 리스트 생성
	public static Integer[] ascendingOrderList(int aSize) {
		Integer[] list = null;
		
		if(aSize > 0) {
			list = new Integer[aSize];
			for(int i = 0; i < aSize; i++) {
				list[i] = i;
			}
		}
		
		return list;
	}
	
	
	// 내림차순 리스트 생성
	public static Integer[] descendingOrderList(int aSize) {
		Integer[] list = null;
		
		if(aSize > 0) {
			list = new Integer[aSize];
			for(int i = 0; i < aSize; i++) {
				list[i] = aSize - 1 - i;
			}
		}
		
		return list;
	}
	
	
	// 무작위 리스트 생성
	public static Integer[] randomOrderList(int aSize) {
		Integer[] list = null;
		
		if(aSize > 0) {
			list = new Integer[aSize];
			for(int i = 0;  i < aSize; i++) {
				list[i] = i;
			}
			
			Random random = new Random();
			for(int i = 0; i < aSize; i++) {
				int j = random.nextInt(aSize);
				Integer temp = list[i];
				list[i] = list[j];
				list[j] = temp;
			}		
		}
		
		return list;
	}
	
	
}
