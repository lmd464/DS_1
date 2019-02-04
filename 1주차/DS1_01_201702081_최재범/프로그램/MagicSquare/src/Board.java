// 마방진 틀

public class Board {
	
	 private static int EMPTY_CELL = -1;
	
	 private int _order;
	 private int[][] _cell;
	 
	 // 생성자 : 마방진 틀 생성 후 초기화
	 public Board(int givenOrder) {
		 this._order = givenOrder;
		 this._cell = new int[givenOrder][givenOrder];	// 마방진 틀 생성
		 
		 for(int row = 0; row < givenOrder; row++) {
			 for(int col = 0; col < givenOrder; col++) {
				 this._cell[row][col] = Board.EMPTY_CELL;
			 }
		 }
	 } 
	 
	 // Getter
	 public int order() {
		 return this._order;
	 }
	 public int cell(CellLocation aLocation) {
		 return this._cell[aLocation.row()][aLocation.col()];
	 }
	 public int cell(int aRow, int aCol) {
		 return this._cell[aRow][aCol];
	 }
	 
	 
	 // Setter
	 public void setCell(CellLocation aLocation, int aNumber) {
		 this._cell[aLocation.row()][aLocation.col()] = aNumber;
	 }
	 public void setCell(int aRow, int aCol, int aNumber) {
		 this._cell[aRow][aCol] = aNumber;
	 }
	 
	 
	 // 값이 비어있는지 확인
	 public boolean cellIsEmpty(CellLocation aLocation) {
		 if(this._cell[aLocation.row()][aLocation.col()] == -1) {
			 return true;
		 }
		 else {
			 return false;
		 }
	 }
	 
}
