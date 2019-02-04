// 마방진의 유효성 판단, 계산에 관함

public class MagicSquare {
	
	private static final int DEFAULT_MAX_ORDER = 99;
	private int _maxOrder;
	

	// Getter : 가능한 최대 차수 반환
	public int maxOrder() {
		return this._maxOrder;
	}
	
	
	// 생성자 : 최대 차수 설정
	public MagicSquare() {
		this._maxOrder = DEFAULT_MAX_ORDER;
	}
	public MagicSquare(int givenMaxOrder) {
		this._maxOrder = givenMaxOrder;
	}

	
	// 차수를 전달받아 유효성 검사 후 반환
	public OrderValidity checkOrderValidity(int anOrder) {
		if(anOrder < 3) {
			return OrderValidity.TooSmall;
		}
		else if(anOrder > 99) {
			return OrderValidity.TooLarge;
		}
		else if((anOrder % 2) == 0) {
			return OrderValidity.NotOddNumber;
		}
		else {
			return OrderValidity.Valid;
		}
	}
	
	
	// 마방진 풀기
	public Board solve(int anOrder) {
		
		if(this.checkOrderValidity(anOrder) != OrderValidity.Valid) {
			return null;
		}
		else {
			Board board = new Board(anOrder);
			
			// 처음 위치 생성 : 맨 윗줄 중앙
			CellLocation currentLoc = new CellLocation(0, anOrder / 2);
			
			// 다음 위치 생성
			CellLocation nextLoc = new CellLocation();
			
			// 출발 위치에 1 채움
			board.setCell(currentLoc, 1);
			
			// 다음 위치 찾아 채우기
			int lastValue = anOrder * anOrder;
			for(int cellValue = 2; cellValue <= lastValue; cellValue++) {
				
				// 오른쪽 위로 이동
				int destinationRow = currentLoc.row() - 1;
				int destinationCol = currentLoc.col() + 1;
				
				// 끝일 시
				if(currentLoc.row() == 0) {
					destinationRow = anOrder - 1;
				}
				if(currentLoc.col() == anOrder - 1) {
					destinationCol = 0;
				}
				
				// 다음 위치 설정
				nextLoc.setRow(destinationRow);
				nextLoc.setCol(destinationCol);
				
				// 다음 위치에 값이 들어있을 시 한칸 내려감
				if(!board.cellIsEmpty(nextLoc)) {
					destinationRow = currentLoc.row() + 1;
					destinationCol = currentLoc.col();
					nextLoc.setRow(destinationRow);
					nextLoc.setCol(destinationCol);
				}
				
				// 이동
				currentLoc.setRow(nextLoc.row());
				currentLoc.setCol(nextLoc.col());
				
				// 이동한 위치에 값 설정
				board.setCell(currentLoc, cellValue);	
			}
			return board;
		}
	}

}
