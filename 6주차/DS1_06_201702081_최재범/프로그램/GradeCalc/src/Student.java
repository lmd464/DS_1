
public class Student {
	
	private int _score;
	
	// 생성자
	public Student() {
		
	}
	public Student(int givenScore) {
		this._score = givenScore;
	}
	
	
	// Getter
	public int score() {
		return this._score;
	}
	
	
	// Setter
	public void setScore(int newScore) {
		this._score = newScore;
	}
	
}
