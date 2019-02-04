import java.util.Scanner;

public class AppView {
	
	private Scanner _scanner;
	
	// 생성자
	public AppView() {
		this._scanner = new Scanner(System.in);
	}
	
	
	// 입력
	
	// 문자열 입력
	public String inputString() {
		return this._scanner.nextLine();
	}
	
	// 수식 입력
	public String inputExpression() {
		System.out.print("> 수식을 입력하시오 : ");
		return this.inputString();
	}
	
	
	// 출력
	
	// 최종 값 출력
	public void outputResult(double aValue) {
		System.out.println("\n[최종값] : " + aValue);
	}
	
	// Postfix 출력
	public void outputPostfix(String aPostfix) {
		System.out.println("\n[Postfix] : " + aPostfix);
	}
	
	// 문자열 출력
	public void outputMessage(String aMessage) {
		System.out.print(aMessage);
	}
}
