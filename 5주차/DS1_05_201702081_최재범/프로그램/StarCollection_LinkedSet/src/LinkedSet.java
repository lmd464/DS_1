// 집합에 대한 정보

public class LinkedSet<Element> {
	
	private int _size;				// 현재 집이 가지고 있는 원소의 개수
	private Node<Element> _head;		// 맨 앞 노드
	
	
	// 생성자
	public LinkedSet() {
		this._size = 0;
		this._head = null;
	}
	
	
	// 현재 크기 반환 : getter
	public int size() {
		return this._size;
	}
	
	
	// 집합 비어있는지 여부 반환
	public boolean isEmpty() {
		return (this._size == 0 && this._head == null);
	}
	
	
	// 집합 다 찼는지 여부 반환 : LinkedSet이라 꽉 차지 않음
	public boolean isFull() {
		return false;
	}
	
	
	// 주어진 order에 있는 원소 반환
	public Element elementAt(int givenOrder) {
		
		Node<Element> currentNode = this._head;
		
		// order만큼 뒤에 있는 원소의 정보를 currentNode에 저장
		for(int index = 0; index < givenOrder; index++) {
			currentNode = currentNode.next();
		}
		
		return currentNode.element();
	}
	
	
	
	// 주어진 원소와 같은 값의 원소가 있는지 여부 반환
	public boolean doesContain(Element givenElement) {
		
		boolean found = false;
		Node<Element> currentNode = this._head;		// 탐색을 위한 임시 노드, head부터 시작
		
		// 아직 찾지 못했고, 현재 노드가 존재할 때
		while(!found && (currentNode != null)) {
			if(currentNode.element().equals(givenElement))
				found = true;
			currentNode = currentNode.next();
		}
		
		return found;
	}
	
	
	// 주어진 원소와 같은 값의 원소가 몇 개 있는지 반환
	public int frequencyOf(Element givenElement) {
		
		int count = 0;
		Node<Element> currentNode = this._head;		// 탐색을 위한 임시 노드, head부터 시작
		
		// 현재 노드가 존재하고, 원소 또한 존재할 때
		while(currentNode != null && currentNode.element() != null) {
			if(currentNode.element().equals(givenElement))
				count++;
			currentNode = currentNode.next();
		}
		
		return count;
	}
	
	
	// 집합에 원소 추가
	public boolean add(Element givenElement) {
		
		// 중복 x
		if(this.doesContain(givenElement)) {
			return false;
		}
		
		Node<Element> nodeToAdd = new Node<Element>(givenElement, this._head);
		
		this._head = nodeToAdd;
		this._size++;
		
		return true;
	}
	
	
	// 집합에서 주어진 값의 원소 하나 삭제 후 양옆의 노드 연결
	public boolean remove(Element givenElement) {
		
		// 비어 있을 때
		if(this.isEmpty()) 
			return false;
		
		
		// 노드가 존재
		else {
			boolean found = false;
			Node<Element> previousNode = null;
			Node<Element> currentNode = this._head;	// 탐색을 위한 임시 노드, head부터 시작
			
			
			// 1. 탐색
			
			// 아직 찾지 못했고, 현재 노드가 존재할 때
			while(!found && (currentNode != null)) {
				
				if(currentNode.element().equals(givenElement)) {
					found = true;
					break;		// 찾으면 이동 중지
				}
				
				previousNode = currentNode;
				currentNode = currentNode.next();
				
			}
			
			
			// 2. 삭제
			
			// 찾지 못했을 때
			if(found == false) {
				return false;
			}
			
			// 찾은 위치가 처음일 때 삭제
			else if(currentNode == this._head)
				this._head = this._head.next();
			
			// 찾은 위치가 끝일 때 삭제
			else if(currentNode.next() == null)
				previousNode.setNext(null);
				
			
			// 찾은 위치가 중간일 때 삭제
			else {
				previousNode.setNext(currentNode.next());
			}
			
			this._size--;
				
			return true;
			
		}
		
	}
	
	// 맨 앞 원소 삭제 후, 삭제한 원소 반환
	public Element removeAny() {
		if(this.isEmpty()) {
			return null;
		}
		else {
			Element removedElement = this._head.element();
			this._head = this._head.next();
			this._size--;
			return removedElement;
		}
	}

	// 집합 초기화
	public void clear() {
		this._size = 0;
		this._head = null;
	}

}
