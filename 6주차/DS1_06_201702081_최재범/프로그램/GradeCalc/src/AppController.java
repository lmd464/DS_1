
public class AppController {
	
	private AppView _appView;
	private Ban _ban;
	
	
	// 생성자
	public AppController() {
		this._appView = new AppView();
	}
	
	
	
	
	
	public void run() {
		
		this.showMessage(MessageID.Notice_StartProgram);
		
		// 1. 성적 입력
		this.inputAndStoreStudents();
		
		if(this._ban.isEmpty()) {
			this.showMessage(MessageID.Error_NoInputScores);
		}
		
		else {			
			// 2. 정보 출력
			this.showStatistics();
			
			// 3. 성적순 정렬 후 출력
			this._ban.sortStudentsByScore();
			this.showStudentsSortedByScore();
		}
		
		this.showMessage(MessageID.Notice_EndProgram);
	}
	
	
	
	
	
	
	
	// 비공개 함수
	// 학생들의 정보 입력받아  Ban에 저장
	private boolean inputAndStoreStudents() {
		
		int score;
		boolean storingAStudentWasSuccessful = true;
	
		this._ban = new Ban();
		
		// 새로운 입력을 받을 지 확인 후 진행
		while(storingAStudentWasSuccessful && 
			  this._appView.inputDoesContinueToInputNextStudent()) {
			
			score = this._appView.inputScore();
			
			if(score < 0 || score > 100) {
				this.showMessage(MessageID.Error_InvalidScore);
			}
			else {
				Student aStudent = new Student(score);
				this._ban.add(aStudent);
			}			
		}
		
		this.showMessage(MessageID.Notice_EndMenu);
		return storingAStudentWasSuccessful;
	}
	
	
	// 상태 정보 출력
	private void showStatistics() {
		
		// 평균, 평균 이상 학생수, 최고/최저 출력
		this._appView.outputAverageScore(this._ban.averageScore());
		this._appView.outputNumberOfStudentsAboveAverage
			(this._ban.numberOfStudentsAboveAverage());
		this._appView.outputMaxScore(this._ban.maxScore());
		this._appView.outputMinScore(this._ban.minScore());
		
		// 학점 별 학생 수 출력
		GradeCounter gradeCounter = this._ban.countGrades();
		this._appView.outputGradeCountFor('A', gradeCounter.numberOfA());
		this._appView.outputGradeCountFor('B', gradeCounter.numberOfB());
		this._appView.outputGradeCountFor('C', gradeCounter.numberOfC());
		this._appView.outputGradeCountFor('D', gradeCounter.numberOfD());
		this._appView.outputGradeCountFor('F', gradeCounter.numberOfF());
		
	}
	
	
	// 성적 순 정렬한 학생 정보 출력
	private void showStudentsSortedByScore() {
		this.showMessage(MessageID.Show_SortedStudentList);
		
		for(int position = 0; position < this._ban.size(); position++) {
			this._appView.outputStudentInfo
			(this._ban.elementAt(position).score());
		}
	}
	
	
	// 상황에 맞는 메시지 출력
	private void showMessage(MessageID aMessageID) {
		switch(aMessageID) {	
		
		case Notice_StartProgram:
			this._appView.outputMessage("<< 성적 처리를 시작합니다. >> \n");
			break;
		case Notice_EndProgram:
			this._appView.outputMessage("<< 성적 처리를 종료합니다. >> \n");
			break;
		case Notice_StartMenu:
			this._appView.outputMessage("\n[*] 성적을 입력하려면 'Y' 또는 'y'를, 종료하려면 다른 아무 키나 치시오 : ");
			break;
		case Notice_EndMenu:
			this._appView.outputMessage("[*] 성적 입력을 종료합니다. \n\n");
			break;
		
		case Show_SortedStudentList:
			this._appView.outputMessage("\n[*] 학생들의 성적 순 목록 \n");
			break;
			
		case Error_WrongMenu:
			this._appView.outputMessage("");
			break;
		case Error_InvalidScore:
			this._appView.outputMessage("[!] ERROR : 0 보다 작거나 100 보다 커서, 정상적인 점수가 아닙니다.\n");
			break;
		case Error_NoInputScores:
			this._appView.outputMessage("[!] 성적이 입력되지 않았습니다.\n");
			break;

		}
	}
	
	
}
