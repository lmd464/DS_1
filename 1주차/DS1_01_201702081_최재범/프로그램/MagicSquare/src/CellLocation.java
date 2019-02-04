// 마방진 원소의 좌표

public class CellLocation {
	
	private int _row;
	private int _col;
	
	// 생성자 : 좌표 설정
	// 디폴트 : (-1, -1)
	public CellLocation() {
		this._row = -1;
		this._row = -1;
	}
	public CellLocation(int givenRow, int givenCol) {
		this._row = givenRow;
		this._col = givenCol;
	}
	
	
	// Getter
	public int row() {
		return this._row;
	}
	public int col() {
		return this._col;
	}
	
	// Setter
	public void setRow(int newRow) {
		this._row = newRow;
	}
	public void setCol(int newCol) {
		this._col = newCol;
	}
	
}
