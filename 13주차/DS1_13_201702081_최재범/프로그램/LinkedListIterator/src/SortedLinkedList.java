
public class SortedLinkedList<T extends Comparable<T>> implements List<T> {
	
	
	private int _size;
	private Node<T> _head;
	
	
	
	// 생성자
	@SuppressWarnings("unchecked")
	public SortedLinkedList() {
		this._size = 0;
		this._head = null;
	}

	
	
	@Override
	public int size() {
		return this._size;
	}

	
	
	@Override
	public boolean contains(T anElement) {
		boolean found = false;
		Node<T> nodeForSearch = this._head;
		
		while(nodeForSearch != null) {
			if(nodeForSearch.element().compareTo(anElement) == 0)
				found = true;
			nodeForSearch = nodeForSearch.next();
		}
		
		return found;
	}

	
	
	@Override
	public boolean isFull() {
		return false;
	}

	
	
	@Override
	public boolean isEmpty() {
		return this._size == 0;
	}
	
	
	
	@Override
	public boolean add(T anElement) {
		
		// 비어있을 때
		if(this.isEmpty()) {
			Node<T> newNode = new Node<T>(anElement);
			this._head = newNode;
			
			this._size++;
			return true;
		}
		
		// 1개 이상 있을 때
		else {			
			Node<T> newNode = new Node<T>(anElement);
			
			
			// 위치 찾기 : 이전 노드를 찾아야 삽입이 가능
			Node<T> previousPosition = new Node<T>(null, this._head);
			
			while( previousPosition.next() != null &&
					anElement.compareTo(previousPosition.next().element()) >= 0 ) 
			{
				previousPosition = previousPosition.next();
			}
			
			
			// 삽입 위치가 맨 앞
			if(previousPosition.next() == this._head) {
				newNode.setNext(this._head);
				this._head = newNode;
			}
			
			// 삽입 위치가 맨 끝
			else if(previousPosition.next() == null) {
				previousPosition.setNext(newNode);
			}
			
			// 삽입 위치가 중간
			else {
				newNode.setNext(previousPosition.next());
				previousPosition.setNext(newNode);
			}
			
			this._size++;
			return true;
		}
		
	}
	
	
	
	@Override
	public T removeFrom(int aPosition) {
		
		// 비어있을 때
		if(this.isEmpty()) {
			return null;
		} 
		
		// 1 개 이상 들어있을 때
		else {
			
			// 유효하지 않은 범위
			if(aPosition < 0 || aPosition >= this._size) {
				return null;
			}
			
			// 유효 범위
			else {
				
				T removedElement;
				
				// 맨 앞 원소 제거
				if(aPosition == 0) {
					removedElement = this._head.element();
					this._head = this._head.next();
				}
				
				// 맨 앞이 아닌 원소 제거
				else {
					
					// 직전까지 이동
					Node<T> previousNode = this._head;
					for(int i = 0; i < aPosition - 1; i++) 
						previousNode = previousNode.next();
					
					
					// 맨 끝의 원소일 경우
					if(aPosition == this._size - 1) {
						removedElement = previousNode.next().element();
						previousNode.setNext(null);
					}
					
					
					// 맨 끝이 아닌 원소일 경우
					else {
						removedElement = previousNode.next().element();
						previousNode.setNext(previousNode.next().next());
					}
					
				}
				
				this._size--;
				return removedElement;
			}
			
		}
	}
	
	
	
	@Override
	public T removeMin() {
		int minPosition = 0;
		for(int i = 1; i < this._size; i++) {
			if( this.elementAt(i).compareTo(this.elementAt(minPosition)) < 0 )
				minPosition = i;
		}
		
		T removedElement = this.removeFrom(minPosition);
		return removedElement;
	}



	@Override
	public T removeMax() {
		int maxPosition = 0;
		for(int i = 1; i < this._size; i++) {
			if( this.elementAt(i).compareTo(this.elementAt(maxPosition)) > 0 )
				maxPosition = i;
		}
		
		T removedElement = this.removeFrom(maxPosition);
		return removedElement;
	}

	

	@Override
	public void clear() {
		this._size = 0;
		this._head = null;
	}

	
	
	public T elementAt(int anOrder) {
		if(anOrder < 0 || anOrder >= this._size) {
			return null;
		}
		else {
			Node<T> searchNode = this._head;
			for(int i = 1; i <= anOrder; i++)
				searchNode = searchNode.next();
			
			return searchNode.element();
		}
	}
	
	
	
	
	
	// Iterator
	
	public ListIterator<T> listIterator() {
		return new ListIterator();
	}
	
	
	public class ListIterator<T> implements Iterator<T> {
		
		private Node<T> _next;
		
		
		@SuppressWarnings("unchecked")
		private ListIterator() {
			this._next = (Node<T>)SortedLinkedList.this._head;
		}
		
		
		public boolean hasNext() {
			return ( this._next != null );
		}
		
		
		// next에 들은 값 반환하면서 next 이동
		public T next() {
			if(this._next == null) {
				return null;
			}
			else {
				T element = this._next.element();
				this._next = this._next.next();
				return element;
			}
		}
		
	}
	
	
	
	
	
}
