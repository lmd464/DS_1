import java.util.Scanner;

public class AppView {
	
	private Scanner _scanner;
	
	
	// 생성자
	public AppView() {
		this._scanner = new Scanner(System.in);
	}
	
	
	// 문자열 출력
	public void output(String aString) {
		System.out.print(aString);
	}
	
	// 문자열 
	public void outputLine(String aString) {
		System.out.println(aString);
	}
	
}
