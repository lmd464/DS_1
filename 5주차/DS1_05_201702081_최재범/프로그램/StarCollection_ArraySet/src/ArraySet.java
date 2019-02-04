// 집합에 대한 정보

public class ArraySet<E> {
	
	// 상수
	private static final int DEFAULT_CAPACITY = 100;
	
	
	// 인스턴스 변수
	
	// 집합 최대 용량
	private int _capacity;
	
	// 현재 집합 용량
	private int _size;
	
	// 집합은 E의 배열로 구성됨
	private E[] _elements;
	
	
	
	// 생성자
	
	// 디폴트 : 용량 100
	public ArraySet() {
		this._capacity = ArraySet.DEFAULT_CAPACITY;
		this._elements = (E[])new Object[this._capacity];
		this._size = 0;
	}
	
	// 용량 직접 설정
	public ArraySet(int givenCapacity) {
		this._capacity = givenCapacity;
		this._elements = (E[])new Object[this._capacity];
		this._size = 0;
	}
	
	
	
	// Getter : 현재 집합의 크기 반환
	public int size() {
		return this._size;
	}
	
	
	// 집합 비어있는지 여부 반환
	public boolean isEmpty() {
		return (this._size == 0);
	}
	
	
	// 집합 다 찼는지 여부 반환
	public boolean isFull() {
		return (this._size == _capacity);
	}
	
	
	// 주어진 order에 있는 원소 반환
	public E elementAt(int anOrder) {
		if((0 <= anOrder) && (anOrder < this.size())) {
			return this._elements[anOrder];
		}
		else {
			return null;
		}
	}
	
	
	// 주어진 element과 같은 값의 element이 있는지 여부 반환
	public boolean doesContain(E anElement) {
		
		boolean found = false;
		
		for(int index = 0; index < this._size; index++) {
			if(anElement.equals(this._elements[index])) {
				found = true;
				break;
			}
		}
		
		return found;
	}
	
	
	
	// 집합에 element 추가
	public boolean add(E anElement) {
		
		if(this.isFull()) {
			return false;
		}
		
		// 중복 원소 추가 x
		else if(this.doesContain(anElement)) {
			return false;
		}
		
		else {
			this._elements[this._size] = anElement;
			this._size++;
			return true;
		}
	}
	
	// 집합에서 주어진 값의 element 삭제
	public boolean remove(E anElement) {
		
		boolean found = false;
		
		if(this.isEmpty()) {
			return found;
		}
		
		// 삭제 시
		else {
			
			// 집합에 element 1개뿐일 때, 그냥 비우기
			if(this._size == 1) {
				found = this._elements[0].equals(anElement);
				this._elements[0] = null;
				this._size--;
			}
			
			// 집합에 element가 2개 이상일 때
			for(int index = 0; index <= this._size - 1 && !found; index++) {	
				
				found = this._elements[index].equals(anElement);

				// 같은 것을 찾으면, 그 인덱스부터 배열 요소 번호들을 1씩 앞으로 당기고 마지막 원소는 null로 초기화
				if(found == true) {
					for(int j = index; j <= this._size - 2; j++) {
						this._elements[j] = this._elements[j + 1];
					}
					this._elements[this._size - 1] = null;
					this._size--;
				}
			}
			
			return found;
		}
	}
	
	
	// 맨 뒤의 element 하나 삭제하고 삭제한 element 반환
	public E removeAny() {
		E removedElement = this._elements[this._size - 1];
		this._elements[this._size - 1] = null;
		this._size--;
		return removedElement;
	}

	// 집합 초기화
	public void clear() {
		this._size = 0;
	}

}
