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
		System.out.print("\n- 문자를 입력하시오 : ");
		element = this.inputString().charAt(0);
		return element;
	}
	
		
	// 출력 관련
	// 추가된 원소 출력
	public void outputAdd(char anElement) {
		System.out.printf("[EnQueue] 삽입된 원소는 '%c' 입니다. \n", anElement);
	}
	
	// Queue 원소 하나 출력
	public void outputElement(char anElement) {
		System.out.printf("%3c", anElement);
	}
	
	// Front 원소 출력
	public void outputFrontElement(char anElement) {
		System.out.printf("[Front] Front 원소는 '%c' 입니다. \n", anElement);
	}
	
	// Queue의 현재 크기 출력
	public void outputQueueSize(int aQueueSize) {
		System.out.printf("[Size] 큐에는 현재 %d 개의 원소가 있습니다. \n", aQueueSize);
	}
	
	// 제거된 원소 출력
	public void outputRemove(char anElement) {
		System.out.printf("[DeQueue] 삭제된 원소는 '%c' 입니다. \n", anElement);
	}
	
	// 제거될 원소의 수 출력
	public void outputRemoveN(int numberOfCharsToBeRemoved) {
		System.out.printf("[RemoveN] %d 개의 원소를 삭제하려고 합니다. \n", numberOfCharsToBeRemoved);
	}
	
	// 입력 처리 결과 출력
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
