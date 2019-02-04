// 별에 대한 정보

public class Star {
	
	// 인스턴스 변수
	
	// 금액
	private int _xCoordinate;			// X 좌표
	private int _yCoordinate;			// Y 좌표
	private String _starName;			// 별의 이름
	
	
	
	// 생성	
	
	// 디폴트
	public Star() {	}
	
	// 좌표만 받아 생성
	public Star(int givenX, int givenY) {
		this._xCoordinate = givenX;
		this._yCoordinate = givenY;
	}
	
	// 이름만 받아 생성
	public Star(String givenStarName) {
		this._starName = givenStarName;
	}
	
	// 좌표, 이름 받아 생성
	public Star(int givenX, int givenY, String givenStarName) {
		this._xCoordinate = givenX;
		this._yCoordinate = givenY;
		this._starName = givenStarName;
	}
	
	
	
	// 공개 함수
	
	
	// Getter
	
	// 별의 X 좌표 반환
	public int xCoordinate() {
		return this._xCoordinate;
	}
	
	// 별의 Y 좌표 반환
	public int yCoordinate() {
		return this._yCoordinate;
	}
	
	// 별의 이름 반환
	public String starName() {
		return this._starName;
	}
	
	
	// Setter
	
	// X 좌표 설정
	public void setXCoordinate(int newX) {
		this._xCoordinate = newX;
	}
	
	// Y 좌표 설정
	public void setYCoordinate(int newY) {
		this._yCoordinate = newY;
	}
	
	// 별의 이름 설정
	public void setStarName(String newStarName) {
		this._starName = newStarName;
	}
	
	
	
	// Star 객체끼리의 비교
	@Override
	public boolean equals(Object aStar) {
		
		// 인자로 받은 값이 Star 형이 아닐 경우
		if(aStar.getClass() != Star.class) {
			return false;
		}
		
		
		// Star형을 받았을 경우, 동일한지 비교
		else {
			
			// 좌표 같으면 동일
			if(this._xCoordinate == ((Star)aStar)._xCoordinate && 
			   this._yCoordinate == ((Star)aStar)._yCoordinate) {
				return true;
			}
			
			// 이름 같으면 동일
			if(((Star)aStar)._starName != null && 
			   ((Star)aStar)._starName.equals(this._starName)) {
				return true;
			}
			
			// 둘 다 아니면 다름
			else {
				return false;
			}
			
		}
			
	}
	
}
