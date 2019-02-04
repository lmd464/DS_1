// 출력

import java.util.Scanner;

public class AppView {
	
	private Scanner _scanner;
	
	
	// 생성자
	public AppView() {
		this._scanner = new Scanner(System.in);
	}
	
	
	// 문자열 출력
	public void outputMessage(String aMessageString) {
		System.out.print(aMessageString);
	}
	
	
	// 결과 출력
	public void outputResult(int aTestSize, long aTestInsertTime, long aTestFindMaxTime) {
		System.out.printf("크기 : %7d", aTestSize);
		System.out.printf(" 삽입하기 : %10d", aTestInsertTime);
		System.out.printf(" 최대값찾기 : %10d", aTestFindMaxTime);
		System.out.println();
	}
	
	
}
