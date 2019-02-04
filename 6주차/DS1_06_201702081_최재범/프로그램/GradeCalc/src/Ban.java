
public class Ban {
	
	public static final int DEFAULT_CAPACITY = 100;		// 기본 최대 학생 수
	
	private int _capacity;
	private int _size;
	private Student[] _elements;
	
	
	// 생성자
	public Ban() {
		this._capacity = Ban.DEFAULT_CAPACITY;
		this._size = 0;
		this._elements = new Student[this._capacity];
	}
	public Ban(int givenCapacity) {
		this._capacity = givenCapacity;
		this._size = 0;
		this._elements = new Student[this._capacity];
	}
	
	
	// Getter
	public int capacity() {
		return this._capacity;
	}
	public int size() {
		return this._size;
	}
	
	
	// 학급이 비어있거나 꽉 찼는지 확인
	public boolean isEmpty() {
		return this._size == 0;
	}
	public boolean isFull() {
		return this._size >= this._capacity;
	}
	
	
	// 학생 추가
	public boolean add(Student aStudent) {
		if( this.isFull() ) {
			return false;
		}
		else {
			this._elements[this._size] = aStudent;
			this._size++;
			return true;
		}
	}
	
	
	// 주어진 위치의 학생 객체 반환
	public Student elementAt(int anOrder) {
		return this._elements[anOrder];
	}
	
	
	// 학생들의 성적 정렬
	public void sortStudentsByScore() {
		int size = this._size;	// 정렬 : [0] ~ [size - 1]
		
		
		// 2 개부터 정렬 가능
		if(size >= 2) {
			
			// 최솟값 위치 찾기
			int minLoc = 0;
			for(int i = 1; i < size; i++) {
				if(this._elements[i].score() < this._elements[minLoc].score()) {
					minLoc = i;
				}
			}
			
			// 최솟값을 구간의 맨 오른쪽으로
			swap(minLoc, size - 1);
			
			// 정렬
			this.quickSortRecursively(0, size - 2);
		}
	}
	
	// 최저점 반환
	public int minScore() {
		return this.minScoreRecursively(0, this._size - 1);
	}
	
	
	// 최고점 반환
	public int maxScore() {
		return this.maxScoreRecursively(0, this._size - 1);
	}
	
	
	// 평균점 반환
	public float averageScore() {
		return (float)this.sumOfScoresRecursively(0, this._size - 1) / (float)this._size;
	}
	
	
	// 평균 이상 학생 수 반환
	public int numberOfStudentsAboveAverage() {
		float average = this.averageScore();
		float score;
		int numberOfStudentsAboveAverage = 0;
		
		for(int i = 0; i < this._size; i++) {
			score = this._elements[i].score();
			if(score >= average) {
				numberOfStudentsAboveAverage++;
			}
		}
		return numberOfStudentsAboveAverage;
	}
	
	
	// 학점 별 학생 수 세서 반환
	public GradeCounter countGrades() {
		char currentGrade;
		GradeCounter gradeCounter = new GradeCounter();		// 학점에 따라 분류하여 카운트하는 객체
		
		for(int i = 0; i < this._size; i++) {
			currentGrade = this.scoreToGrade((this._elements[i].score()));
			gradeCounter.count(currentGrade);
		}
		
		return gradeCounter;
	}
	
	
	
	
	
	
	
	// 비공개 함수	
	// 두 학생 위치 교환
	private void swap(int positionA, int positionB) {
		Student temp = this._elements[positionA];
		this._elements[positionA] = this._elements[positionB];
		this._elements[positionB] = temp;
	}
	
	
	// QuickSort
	private void quickSortRecursively (int left, int right) {
		if(left < right) {
			int mid = this.partition(left, right);			// pivot 정렬
			this.quickSortRecursively(left, mid - 1);		// pivot 왼쪽 정렬
			this.quickSortRecursively(mid + 1, right);		// pivot 오른쪽 정렬
		}
	}
	
	
	// pivot 기준으로 정렬 : 내림차순
	private int partition(int left, int right) {

		int pivotIndex = left;					// 맨 왼쪽 원소를 pivot으로 설정
		int moveToRightIndex = left + 1;		// pivot 이후부터 탐색
		int moveToLeftIndex = right;
		
		// 탐색 사이클이 다 돌지 않았을 때
		while(moveToRightIndex <= moveToLeftIndex) {
			
			// 왼쪽 탐색 인덱스에 있는 원소가 pivot 보다 클 때 : 가만히 두고 인덱스 이동
			while(this._elements[moveToRightIndex].score()
					  > this._elements[pivotIndex].score()) { 
				moveToRightIndex++;
			}
			
			// 오른쪽 탐색 인덱스에 있는 원소가 pivot 보다 작을 때 : 가만히 두고 인덱스 이동
			while(this._elements[moveToLeftIndex].score() 
					 < this._elements[pivotIndex].score()) {
				moveToLeftIndex--;
			}
			
			// 찾은 인덱스에 존재하는 원소 끼리의 교환 : 정렬
			if(moveToRightIndex < moveToLeftIndex) {
				this.swap(moveToRightIndex, moveToLeftIndex);
			}
			
		}
		
		// pivot 기준 정렬 완료 후, pivot을 맞는 위치로 이동
		// moveToLeftIndex에 있는 원소는  pivot에 있는 원소보다 작으므로, 
		// 원래 pivot이 있던 위치인 맨 왼쪽으로 옮겨도 상관 x
		this.swap(moveToLeftIndex, pivotIndex);
		
		return moveToLeftIndex;		// swap 후, pivot이 있는 위치
		
	}
	
	
	// 모든 점수들의 합 반환
	private float sumOfScoresRecursively(int left, int right) {
		if(left > right) {
			return 0;
		}
		return this._elements[left].score() + this.sumOfScoresRecursively(left + 1, right);
	}
	
	
	// 최고점 반환
	private int maxScoreRecursively(int left, int right) {
		if(left == right) {
			return this._elements[left].score();
		}
		else {
			return this._elements[left].score() > this.maxScoreRecursively(left + 1, right) ?
				   this._elements[left].score() : this.maxScoreRecursively(left + 1, right);
		}
	}
	
	
	// 최저점 반환
	private int minScoreRecursively(int left, int right) {
		if(left == right) {
			return this._elements[left].score();
		}
		else {
			return this._elements[left].score() < this.minScoreRecursively(left + 1, right) ?
				   this._elements[left].score() : this.minScoreRecursively(left + 1, right);
		}
	}
	
	
	// 성적을 학점으로 반환
	private char scoreToGrade(int aScore) {
		if(aScore >= 90) {
			return 'A';
		}
		else if(aScore >= 80) {
			return 'B';
		}
		else if(aScore >= 70) {
			return 'C';
		}
		else if(aScore >= 60) {
			return 'D';
		}
		else {
			return 'F';
		}
	}
	
}
