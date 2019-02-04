// 가방에 대한 정보

public class ArrayBag<E> {
	
	// 상수
	private static final int DEFAULT_CAPACITY = 100;
	
	
	// 인스턴스 변수
	
	// 가방 최대 용량
	private int _capacity;
	
	// 현재 가방 용량
	private int _size;
	
	// 가방은 E의 배열로 구성됨
	private E[] _elements;
	
	
	
	// 생성자
	public ArrayBag() {
		this._capacity = ArrayBag.DEFAULT_CAPACITY;
		this._elements = (E[])new Object[this._capacity];
		this._size = 0;
	}
	
	public ArrayBag(int givenCapacity) {
		this._capacity = givenCapacity;
		this._elements = (E[])new Object[this._capacity];
		this._size = 0;
	}
	
	
	// 현재 크기 반환 : getter
	public int size() {
		return this._size;
	}
	
	
	// 가방 비어있는지 여부 반환
	public boolean isEmpty() {
		return (this._size == 0);
	}
	
	
	// 가방 다 찼는지 여부 반환
	public boolean isFull() {
		return (this._size == _capacity);
	}
	
	
	// 주어진 order에 있는 원소 반환
	public E elementAt(int order_p) {
		if((0 <= order_p) && (order_p < this.size())) {
			return this._elements[order_p];
		}
		else {
			return null;
		}
	}
	
	
	// 주어진 element과 같은 값의 element이 있는지 여부 반환
	public boolean doesContain(E element_p) {
		
		boolean found = false;
		
		for(int index = 0; index < this._size; index++) {
			if(element_p.equals(this._elements[index])) {
				found = true;
				break;
			}
		}
		return found;
	}
	
	// 주어진 element과 같은 값의 element이 몇 개 있는지 반환
	public int frequencyOf(E element_p) {
		
		int frequencyCount = 0;
		
		for(int index = 0; index < this._size; index++) {
			if(element_p.equals(this._elements[index])) {
				frequencyCount++;
			}
		}
		return frequencyCount;
	}
	
	// 가방에 element 추가
	public boolean add(E element_p) {
		
		if(this.isFull()) {
			return false;
		}
		else {
			this._elements[this._size] = element_p;
			this._size++;
			return true;
		}
	}
	
	// 가방에서 주어진 값의 element 삭제
	public boolean remove(E element_p) {
		
		boolean found = false;
		
		if(this.isEmpty()) {
			return found;
		}
		
		
		// 삭제 시
		else {
			
			// 가방에 element 1개뿐일 때, 그냥 비우기
			if(this._size == 1) {
				found = this._elements[0].equals(element_p);
				this._elements[0] = null;
				this._size--;
			}
			
			// 가방에 element가 2개 이상일 때
			for(int index = 0; index < this._size - 1 && !found; index++) {	
				
				found = this._elements[index].equals(element_p);
				
				// 마지막 원소는 순회되지 않으므로 따로 확인
				found = this._elements[this._size - 1].equals(element_p);
				
				// 같은 것을 찾으면, 그 인덱스부터 배열 요소 번호들을 1씩 앞으로 당기고 마지막 원소는 null로 초기화
				if(found == true) {
					for(int j = index; j < this._size - 1; j++) {
						this._elements[j] = this._elements[j + 1];
					}
					this._elements[this._size - 1] = null;
					this._size--;
				}
			}
			
			return found;
		}
	}
	

	// 가방 초기화
	public void clear() {
		this._size = 0;
	}

}
