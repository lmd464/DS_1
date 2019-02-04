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
	
	/*
	// 코인 개수 입력받아 반환
	public int inputInt() {
		
		int coinAmount;
		this.outputLine("");
		this.output("가방에 들어갈 총 코인 개수 입력 : ");
		coinAmount = _scanner.nextInt();
		
		return coinAmount;
	}
	*/
	
	// 메뉴 번호 입력받아 반환
	public int inputMenuNumber() {

		int menuNumber;
		this.outputLine("");
		this.outputLine("수행하려는 메뉴 번호 입력");
		this.output("(add : 1, remove : 2, search : 3, frequency : 4, exit : 9) : ");
		menuNumber = this._scanner.nextInt();
		
		return menuNumber;
	}
	
	// 코인 액수 입력받아 반환
	public int inputCoinValue() {
		
		int coinValue;
		this.output("코인 액수 입력 : ");
		coinValue = this._scanner.nextInt();
		
		return coinValue;
	}
	
	
	
	/* 가방에 들어갈 최대 코인 개수 입력받아 반환 : 필요 x
	public int inputCapacityOfCoinBag() {
		
		int capacityOfCoinBag;
		this.output("가방에 들어갈 코인의 최대 개수 입력 : ");
		capacityOfCoinBag = _scanner.nextInt();
		
		return capacityOfCoinBag;
	}*/
	
	
	
	// 출력 메소드
	
	// 출력
	public void output(String string_p) {
		System.out.print(string_p);
	}
	
	// 줄바꿈 출력
	public void outputLine(String string_p) {
		System.out.println(string_p);
	}

}
