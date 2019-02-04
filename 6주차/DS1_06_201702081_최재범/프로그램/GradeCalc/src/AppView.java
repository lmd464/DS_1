
import java.util.Scanner;

public class AppView {
	
	private Scanner _scanner;
	
	// 생성자
	public AppView() {
		this._scanner = new Scanner(System.in);
	}
	
	
	// 입력
	// 정수 입력받아 반환
	public int inputInt() {
		return Integer.parseInt(this._scanner.nextLine());
	}
	
	
	// 문자열 입력받아 반환
	public String inputString() {
		return this._scanner.nextLine();
	}
	
	
	// 다음 점수 입력 여부 확인 후 반환
	public boolean inputDoesContinueToInputNextStudent() {
		char answer;
		
		System.out.print("[*] 성적을 입력하려면 'Y' 또는 'y'를, 종료하려면 다른 아무 키나 치시오 : ");
		answer = this.inputString().charAt(0);
		
		if(answer == 'Y' || (answer == 'y')) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	// 점수 입력받아 반환
	public int inputScore() {
		int score;
		System.out.print("- 점수를 입력하시오 : ");
		score = this.inputInt();
		return score;
	}
	
	
	// 출력
	// 평균값 출력
	public void outputAverageScore(float anAverageScore) {
		System.out.println("평균 점수 : " + anAverageScore);
	}
	
	
	// 평균 이상인 학생 수 출력
	public void outputNumberOfStudentsAboveAverage(int aNumber) {
		System.out.println("평균 이상 학생들의 수는 " + aNumber + " 명 입니다.");
	}
	
	
	// 최고점 출력
	public void outputMaxScore(int aMaxScore) {
		System.out.println("최고 점수는 " + aMaxScore + " 점 입니다.");
	}
	
	
	// 최저점 출력
	public void outputMinScore(int aMinScore) {
		System.out.println("최저 점수는 " + aMinScore + " 점 입니다.");
	}
	
	
	// 각 학점에 대한 학생 수 출력
	public void outputGradeCountFor(char aGrade, int aCount) {
		System.out.println(aGrade + " 학점 학생의 수는 " + aCount + " 명 입니다.");
	}
	
	
	// 학생들의 점수 출력 
	public void outputStudentInfo(int aScore) {
		System.out.println("학생의 점수는 " + aScore + " 점 입니다.");
	}
	
	
	// 문자열 출력
	public void outputMessage(String aMessageString) {
		System.out.print(aMessageString);
	}
	
}
