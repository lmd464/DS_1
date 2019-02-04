import java.util.Scanner;

public class AppView {

	private Scanner _scanner;
	
	
	// 생성자 
	public AppView() {
		this._scanner = new Scanner(System.in);
	}
	
	
	// 공개 함수
	
	// 입력 관련
	// 정수 입력받아 반환
	public int inputInt() {
		return Integer.parseInt(this._scanner.nextLine());
	}
	
	// String 입력받아 반환
	public String inputString() {
		return this._scanner.nextLine();
	}
	
	// char 입력받아 반환
	public char inputCharacter() {
		char element;
		System.out.print("- 문자를 입력하시오 : ");
		element = this.inputString().charAt(0);
		return element;
	}
	
		
	// 출력 관련
	// 추가된 원소 출력
	public void outputAddedElement(char anElement) {
		System.out.printf("[Push] 삽입된 원소는 '%c' 입니다. \n", anElement);
	}
	
	// Stack 원소 하나 출력
	public void outputStackElement(char anElement) {
		System.out.printf("%3c", anElement);
	}
	
	// Top 원소 출력
	public void outputTopElement(char anElement) {
		System.out.printf("[Top] Top 원소는 '%c' 입니다. \n", anElement);
	}
	
	// Stack의 현재 크기 출력
	public void outputStackSize(int aStackSize) {
		System.out.printf("[Size] 스택에는 현재 %d 개의 원소가 있습니다. \n", aStackSize);
	}
	
	// 제거된 원소 출력
	public void outputRemove(char anElement) {
		System.out.printf("[Pop] 삭제된 원소는 '%c' 입니다. \n", anElement);
	}
	
	
	// 결과 출력
	public void outputResult(int numberOfInputChars, 
							int numberOfIgnoredChars, 
							int numberOfAddedChars) {
		System.out.printf("입력된 문자는 모두 %d 개 입니다. \n", numberOfInputChars);
		System.out.printf("추가된 문자는 모두 %d 개 입니다. \n", numberOfAddedChars);
		System.out.printf("무시된 문자는 모두 %d 개 입니다. \n", numberOfIgnoredChars);
	}
	
	// String 출력
	public void outputMessage(String aMessageString) {
		System.out.print(aMessageString);
	}
	
}
