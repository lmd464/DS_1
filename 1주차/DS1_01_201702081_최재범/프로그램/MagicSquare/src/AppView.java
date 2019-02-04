// 입출력 관련

import java.util.Scanner;

public class AppView {
	
	private Scanner _scanner;
	
	// 생성자
	public AppView() {
		this._scanner = new Scanner(System.in);
	}
	
	// 마방진 차수 입력
	public int inputOrder() {
		int input;
		this.output("마방진 차수를 입력하십시오. (음수를 입력하면 종료합니다) : ");
		input = _scanner.nextInt();
		
		return input;
	}
	
	
	// 출력 메소드
	
	// 일반 출력
	public void output(String outString) {
		System.out.print(outString);
	}
	
	// 줄바꿈 출력
	public void outputLine(String outString) {
		System.out.println(outString);
	}
	
	// 마방진 크기 출력
	public void outputTitleWithOrder(int order) {
		System.out.println("Magic Square Board : Order " + order);
	}
	
	// [] 안에 인덱스 넣어 출력 (행 인덱스에 사용)
	public void outputRowNumber(int rowNumber) {
		System.out.printf("[%3d] ", rowNumber);
	}
	
	// 요소 값 출력
	public void outputCell(int cellValue) {
		System.out.printf("  %3d ", cellValue);
	}
	
}

