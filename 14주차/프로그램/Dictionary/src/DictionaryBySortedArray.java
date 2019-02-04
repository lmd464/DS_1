
public class DictionaryBySortedArray <Key extends Comparable<Key>, Obj> 
extends Dictionary <Key, Obj> 
{
	
	// 상수
	private static final int DEFAULT_CAPACITY = 100000;
	
	// 인스턴스 변수
	private int 								_capacity;
	private DictionaryElement<Key, Obj>[] 	_elements;
	
	
	// Getter / Setter
	public int capacity() 						{ return this._capacity; }
	private void setCapacity(int newCapacity) 	{ this._capacity = newCapacity; }
	
	
	
	// 생성자
	public DictionaryBySortedArray() { 
		this(DictionaryBySortedArray.DEFAULT_CAPACITY); 
	}
	
	@SuppressWarnings("unchecked")
	public DictionaryBySortedArray(int givenCapacity) {
		this.setCapacity(givenCapacity);
		this._elements = new DictionaryElement[this.capacity()];
	}
	
	
	
	// 다 찼는지 확인
	@Override
	public boolean isFull() {
		return ( this.size() == this.capacity() );
	}

	
	
	// Key 의 존재 여부 확인
	@Override
	public boolean keyDoesExist(Key aKey) { 
		int keyPosition = this.positionFor(aKey);
		if(keyPosition < 0)
			return false;
		else
			return true;
	}

	
	
	// Key 에 대응하는 Object 탐색
	@Override
	public Obj objectForKey(Key aKey) {
		int positionWithKey = this.positionFor(aKey);
		
		// Key에 대응하는 원소가 존재하지 않음
		if(positionWithKey < 0) 
			return null;
		
		// Key에 대응하는 원소가 존재함
		return this._elements[positionWithKey].object();
	}

	
	
	// Key 와 Object 쌍 추가
	@Override
	public boolean addKeyAndObject(Key aKey, Obj anObject) {
		int positionForAdd = this.positionFor(aKey);
		
		// 이미 존재 : positionFor() 가 양수 위치를 반환
		if(positionForAdd >= 0) 
			return false;
		
		// 존재 x : positionFor() 가 음수 위치를 반환
		// positionFor()에서 음수화하였던 위치를 원래대로 되돌려주면 정상적인 추가 위치 나옴
		positionForAdd = -(positionForAdd + 1);
		this.makeRoomAt(positionForAdd);
		this._elements[positionForAdd] = new DictionaryElement<Key, Obj>(aKey, anObject);
		this.setSize(this.size() + 1);		// Getter 를 통한 크기 증가 : 부모 클래스의 private 필드이기 때문
		
		return true;
	}

	
	
	// Key 에 대응하는 Object 제거
	@Override
	public Obj removeObjectForKey(Key aKey) {
		int positionForRemove = this.positionFor(aKey);
		
		// Key에 대응하는 원소가 존재하지 않음
		if(positionForRemove < 0) {
			return null;
		}
		
		// Key에 대응하는 원소가 존재함
		Obj removedObject = this._elements[positionForRemove].object();
		this.removeGapAt(positionForRemove);
		this.setSize(this.size() - 1);
		return removedObject;
	}

	
	
	// 사전 초기화
	@Override
	public void clear() {
		this.setSize(0);
	}

	
	
	
	
	// 비공개 함수
	
	
	// 이진 탐색으로 Key가 존재하는 위치 탐색, 없으면 음수화하여 반환
	private int positionFor(Key aKey) {
		
		int left = 0;
		int right = this.size() - 1;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			
			switch( aKey.compareTo(this._elements[mid].key()) ) {
			case -1:
				right = mid - 1;
				break;
			case 0:
				return mid;
			case 1:
				left = mid + 1;
				break;
			}	
		}
		
		// 못 찾음
		return -(left + 1);		// left가 0 일 수도 있기 때문에 1을 더한 후 음수화
								// 역계산하면 Key에 알맞는 위치 계산 가능 : 추가할 때 사용
	}
	
	
	
	// 배열의 특정 위치에 공간 생성
	private void makeRoomAt(int aPosition) {
		for(int i = this.size(); i > aPosition; i--) 
			this._elements[i] = this._elements[i - 1];
	}
	
	
	
	// 배열의 특정 위치의 공간 제거
	private void removeGapAt(int aPosition) {
		for(int i = aPosition; i < this.size() - 1; i++) 
			this._elements[i] = this._elements[i + 1];
	}
	
	
	
}
