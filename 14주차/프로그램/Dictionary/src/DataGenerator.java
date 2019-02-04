// 데이터 생성
// static class를 구현하기 위하여, 모든 멤버는 static으로 선언하고 클래스는 final로

import java.util.Random;


public final class DataGenerator {
	
	
	// 인스턴스 생성 불가
	private DataGenerator() {}
	
	
	// 오름차순 리스트 생성
	public static int[] ascendingList(int aSize) {
		
		if(aSize > 0) {
			int[] list = new int[aSize];
			for(int i = 0; i < aSize; i++) {
				list[i] = i;
			}
			return list;
		}
		
		return null;
	}
	
	
	// 내림차순 리스트 생성
	public static int[] descendingList(int aSize) {
		
		if(aSize > 0) {
			int[] list = new int[aSize];
			for(int i = 0; i < aSize; i++) {
				list[i] = aSize - 1 - i;
			}
			return list;
		}
		
		return null;
	}
	
	
	// 무작위 리스트 생성
	public static int[] randomList(int aSize) {
		
		if(aSize > 0) {
			int[] list = new int[aSize];
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
			return list;
		}
		
		return null;
	}
	
	
}
