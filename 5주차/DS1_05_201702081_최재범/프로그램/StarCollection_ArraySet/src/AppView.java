// 입출력

import java.util.Scanner;

public class AppView {

	// 인스턴스 변수
	private Scanner _scanner;


	// 생성자
	public AppView() {
		this._scanner = new Scanner(System.in);
	}



	// 입력 메소드

	// 숫자 하나 입력받아 반환
	public int inputInt() {
		return Integer.parseInt(this._scanner.nextLine());
	}

	// 문자열 입력받아 반환
	public String inputString() {
		return this._scanner.nextLine();
	}




	// 출력 메소드

	// 메시지 출력
	public void outputMessage(String aMessage) {
		System.out.print(aMessage);
	}

	// Star 정보 출력
	public void outputStar(String aStarName, int aX, int aY) {
		System.out.println("별 이름 : " + aStarName);
		System.out.println("X 좌표 : " + aX);
		System.out.println("Y 좌표 : " + aY);
	}

	// Star 존재 여부 출력 (이름 or 좌표 출력)
	public void outputStarExistence(String aStarName) {
		System.out.println(aStarName + " 별이 존재합니다.");
	}
	public void outputStarExistence(int aX, int aY) {
		System.out.printf("(%d, %d) 좌표에 별이 존재합니다. \n", aX, aY);
	}



	// 저장된 Star 개수 출력
	public void outputNumberOfStars(int aStarCollectorSize) {
		System.out.println(aStarCollectorSize + " 개의 별이 존재합니다.");
	}


}
