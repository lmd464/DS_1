
public abstract class Dictionary<Key extends Comparable<Key>, Obj> {
	
	private int _size;	// 상속받는 클래스에서 Getter / Setter 로만 접근 가능
	
	
	// Getter / Setter
	public int size() { return this._size; }
	protected void setSize(int newSize) { this._size = newSize; } 	// 삽입/삭제할 때 크기 변경시킬 때 사용
	
	
	// 생성자
	public Dictionary() { this.setSize(0); }
	
	
	// 비었는 지 확인
	public boolean isEmpty() {
		return ( this.size() == 0 );
	}
	
	
	// 추상 메소드
	public abstract boolean 	isFull();
	public abstract boolean 	keyDoesExist(Key aKey);
	public abstract Obj 		objectForKey(Key aKey);
	public abstract boolean 	addKeyAndObject(Key aKey, Obj anObject);
	public abstract Obj 		removeObjectForKey(Key aKey);
	public abstract void 	clear();
	
}
