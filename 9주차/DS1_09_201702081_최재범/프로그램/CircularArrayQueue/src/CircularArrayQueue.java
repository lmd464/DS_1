
public class CircularArrayQueue<T> {
	
	private static final int DEFAULT_CAPACITY = 100;
	private int _capacity;
	private int _front;		// 맨 앞 원소의 앞쪽 인덱스
	private int _rear;		// 맨 뒤 원소의 인덱스
	private T[] _elements;
	
	
	// 생성자
	@SuppressWarnings("unchecked")
	public CircularArrayQueue() {
		this._capacity = CircularArrayQueue.DEFAULT_CAPACITY;
		this._front = 0;
		this._rear = 0;
		this._elements = (T[])new Object[this._capacity];
	}
	
	@SuppressWarnings("unchecked")
	public CircularArrayQueue(int givenCapacity) {
		this._capacity = givenCapacity;
		this._front = 0;
		this._rear = 0;
		this._elements = (T[])new Object[this._capacity];
	}
	
	
	// 공개 함수
	
	// 배열의 크기 반환 : Getter
	public int capacity() {
		return this._capacity;
	}
	
	// 현재 크기 반환 : Getter
	public int size() {
		if(this._front <= this._rear) {
			return this._rear - this._front;
		}
		else {
			return this._rear + this._capacity - this._front;
		}
	}
	
	// 비어있는지 확인
	public boolean isEmpty() {
		return (this._front == this._rear);
	}
	
	// 꽉 찼는지 확인
	public boolean isFull() {
		int nextIndex = this._rear + 1;
		if(nextIndex >= this._capacity) {
			nextIndex = nextIndex - this._capacity;
		}
		if(nextIndex == this._front) {
			return true;
		}
		return false;
	}
	

	
	// 원소 추가
	public boolean enQueue(T anElement) {
		if(this.isFull()) {
			return false;
		}
		else {
			this._rear++;
			
			// 다음 원소를 넣을 인덱스가 최대 인덱스를 초과할 시 : 앞쪽으로 돌아감
			if(this._rear >= this._capacity) {
				this._rear = this._rear - this._capacity;
			}
			this._elements[this._rear] = anElement;
			return true;
		}
	}
	
	// 원소 제거 후 반환
	public T deQueue() {
		if(this.isEmpty()) {
			return null;
		}
		else {
			this._front++;
			
			// 다음 원소를 넣을 인덱스가 최대 인덱스를 초과할 시 : 앞쪽으로 돌아감
			if(this._front >= this._capacity) {
				this._front = this._rear - this._capacity;
			}
			T removedFrontElement = this._elements[this._front];
			this._elements[this._front] = null;
			
			return removedFrontElement;
		}
	}
	
	// front 의 원소 확인 후 반환
	public T frontElement() {
		if(this.isEmpty()) {
			return null;
		}
		else {
			return this._elements[this._front + 1];
		}
	}
	
	// Queue 초기화
	public void clear() {
		this._front = 0;
		this._rear = 0;
		for(int i = 0; i < this._capacity; i++) {
			this._elements[i] = null;
		}
	}
	
	// 주어진 순서의 원소 반환
	public T elementAt(int anOrder) {
		int circularOrder = ((this._front + 1) + anOrder) % this._capacity;
		return this._elements[circularOrder];
	}
	
}
