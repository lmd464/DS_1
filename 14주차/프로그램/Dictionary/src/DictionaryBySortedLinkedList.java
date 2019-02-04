
public class DictionaryBySortedLinkedList <Key extends Comparable<Key>, Obj>
extends Dictionary <Key, Obj>
{
	
	// 인스턴스 변수
	private LinkedNode<DictionaryElement <Key, Obj>> _head;
	
	
	// Getter / Setter
	private LinkedNode<DictionaryElement<Key, Obj>> head() { return this._head; }
	private void setHead(LinkedNode<DictionaryElement<Key, Obj>> newHead) { this._head = newHead; }
	
	
	
	// 생성자
	public DictionaryBySortedLinkedList() { 
		this.clear(); 
	}
	

	
	// 다 찼는지 확인
	@Override
	public boolean isFull() {
		return false;
	}

	
	
	// Key 의 존재 여부 확인
	@Override
	public boolean keyDoesExist(Key aKey) {
		LinkedNode<DictionaryElement<Key, Obj>> current = this.head();
		
		while(current != null) {
			
			// 오름차순 리스트이므로, 순차적으로 탐색하면서 대상 Key를 만나기 전에 더 큰 것을 만난다면 없는거임
			switch( current.element().key().compareTo(aKey) ) {
			
			// 대상보다 더 작음
			case -1:
				current = current.next();
				break;
				
			// 대상 찾음
			case 0:
				return true;
				
			// 대상보다 더 큼 : 리스트에 없음
			case 1:
				return false;
			}
		}
		
		// 끝까지 가도 못찾음
		return false;
	}
	
	
	
	// Key 에 대응하는 Object 탐색
	@Override
	public Obj objectForKey(Key aKey) {
		LinkedNode<DictionaryElement<Key, Obj>> current = this.head();
		
		// 오름차순이므로, 순차적으로 탐색하면서 대상 Key를 만나기 전에 더 큰 것을 만난다면 없는거임
		while( (current != null) && (current.element().key().compareTo(aKey)) < 0 ) 
			current = current.next();
		
		// 찾음
		if( (current != null) && (current.element().key().compareTo(aKey) == 0) )
			return current.element().object();
		
		// 못찾음
		else
			return null;
	}

	
	
	// Key 와 Object 쌍 추가
	@Override
	public boolean addKeyAndObject(Key aKey, Obj anObject) {
		LinkedNode<DictionaryElement<Key, Obj>> previous = null;
		LinkedNode<DictionaryElement<Key, Obj>> current = this.head();
		
		// 현재 Key가 추가할 Key보다 더 작음 -> 이동
		while( (current != null) && (current.element().key().compareTo(aKey) < 0) ) {
			previous = current; 
			current = current.next();
		}
		
		
		// 현재 Key가 추가할 Key와 같음 -> 이미 있으므로 추가 못함
		if( (current != null) && (current.element().key().compareTo(aKey) == 0) )
			return false;
		
		
		// 추가할 노드 생성
		DictionaryElement<Key, Obj> addedElement = new DictionaryElement<Key,Obj> (aKey, anObject);
		LinkedNode< DictionaryElement<Key, Obj> > addedNode = new LinkedNode< DictionaryElement<Key, Obj> >();
		addedNode.setElement(addedElement);
		
		
		// 추가 (연결)
		addedNode.setNext(current);
		if(previous == null)				// 맨 앞에 추가
			this.setHead(addedNode);
		else								// 중간에 추가
			previous.setNext(addedNode);
		
		// size 증가
		this.setSize(this.size() + 1);		// Getter 를 통한 크기 증가 : 부모 클래스의 private 필드이기 때문
		
		return true;
	}

	
	
	// Key 에 대응하는 Object 제거
	@Override
	public Obj removeObjectForKey(Key aKey) {
		LinkedNode< DictionaryElement<Key, Obj> > previous = null;
		LinkedNode< DictionaryElement<Key, Obj> > current = this.head();
		
		
		// 현재 Key가 제거할 Key보다 더 작음 -> 이동
		while( (current != null) && (current.element().key().compareTo(aKey) < 0) ) {
			previous = current;
			current = current.next();
		}
		
		
		// 현재 Key가 제거할 Key와 같음 -> 제거
		if( (current != null) && (current.element().key().compareTo(aKey) == 0) ) {
			
			// 맨 앞 제거
			if(current == this.head())
				this.setHead(current.next());
			
			// 중간 제거
			else
				previous.setNext(current.next());
			
			// size 증가
			this.setSize(this.size() - 1);
			
			// 제거된 Object 반환
			return current.element().object();
		}
		
		// 없음
		else 
			return null;
	}

	
	
	// 사전 초기화
	@Override
	public void clear() {
		this.setSize(0);
		this.setHead(null);
	}
	
}
