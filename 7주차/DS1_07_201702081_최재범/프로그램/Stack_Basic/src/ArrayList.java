
public class ArrayList<T> implements Stack<T> {
	
	private static final int DEFAULT_CAPACITY = 5;
	private int _capacity;
	private int _top;
	private T[] _elements;
	
	
	// 생성자
	@SuppressWarnings("unchecked")
	public ArrayList() {
		this._capacity = ArrayList.DEFAULT_CAPACITY;
		this._top = -1;
		this._elements = (T[])new Object[ArrayList.DEFAULT_CAPACITY];
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList(int givenCapacity) {
		this._capacity = givenCapacity;
		this._top = -1;
		this._elements = (T[])new Object[this._capacity];
	}
	
	
	// 공개 함수
	
	// 비어있는지 확인
	public boolean isEmpty() {
		return (this._top == -1);
	}
	
	// 꽉 찼는지 확인
	public boolean isFull() {
		return (this.size() == this._capacity);
	}
	
	// 총 길이 확인 (top의 인덱스 번호를 통함)
	public int size() {
		return (this._top + 1);
	}
	
	// 원소 추가 (Top)
	public boolean push(T anElement) {
		if(this.isFull()) {
			return false;
		}
		else {
			this._top++;
			this._elements[this._top] = anElement;
			return true;
		}
	}
	
	// 원소 제거 (Top)
	public T pop() {
		if(this.isEmpty()) {
			return null;
		}
		else {
			this._top--;
			return this._elements[this._top + 1];	// 삭제된 원소 반환

		}
	}
	
	// Top 의 원소 확인
	public T peek() {
		if(this.isEmpty()) {
			return null;
		}
		else {
			return this._elements[this._top];
		}
	}
	
	// Stack 초기화
	public void clear() {
		this._top = -1;
		for(int index = 0; index < this.size(); index++) {
			this._elements[index] = null;
		}
	}
	
	// 주어진 순서의 원소 반환
	public T elementAt(int anOrder) {
		if(anOrder >= 0 && anOrder < this.size()) {
			return this._elements[anOrder];
		}
		else {
			return null;
		}
	}
	
}
