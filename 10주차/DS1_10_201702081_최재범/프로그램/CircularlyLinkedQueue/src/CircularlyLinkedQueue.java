
public class CircularlyLinkedQueue<T> implements Queue<T> 
{
	
	private int _size;		
	private Node<T> _rear;
	
	
	// 생성자
	public CircularlyLinkedQueue() {
		this._size = 0;
		this._rear = null;
	}
	
	
	// 공개 함수
	
	// 최대 크기 반환 : Getter
	public int capacity() {
		return Integer.MAX_VALUE;
	}
	
	
	// 현재 크기 반환 : Getter
	public int size() {
		return this._size;
	}
	
	
	// 비어있는지 확인
	public boolean isEmpty() {
		return this._rear == null;
	}
	
	
	// 꽉 찼는지 확인
	public boolean isFull() {
		return false;
	}
	

	// 원소 추가 : 연결 체인 이므로 가득 찰 경우 없음
	public boolean enQueue(T anElement) {
		if(this.isEmpty()) {
			Node<T> newNode = new Node<T>(anElement);
			newNode.setNext(newNode);	// 맨 처음에 추가하는 원소는 next가 자기 자신을 가리키게됨 (환형)
			this._rear = newNode;
		}	
		else {
			// rear에 추가할 노드는 front의 노드를 가리키게 됨
			Node<T> newNode = new Node<T>(anElement, this._rear.next());		
			this._rear.setNext(newNode);
			this._rear = newNode;
		}
		this._size++;
		return true;
	}
	
	
	// 원소 제거 후 반환
	public T deQueue() {
		
		// 노드가 없을 때
		if(this.isEmpty()) {
			return null;
		}
		
		// 노드 수가 1 개일 때
		else if(this.size() == 1) {
			T removedElement = this._rear.element();
			this._rear = null;
			this._size--;
			return removedElement;
		}
		
		// 노드 수가 2 개 이상일 때
		else {
			T removedElement = this._rear.next().element();
			this._rear.setNext(this._rear.next().next());
			this._size--;
			return removedElement;
		}
		
	}
	
	
	// front 의 원소 확인 후 반환 : 환형이므로 rear의 다음 노드가 front
	public T frontElement() {
		if(this.isEmpty()) {
			return null;
		}
		else {
			return this._rear.next().element();
		}
	}
	
	
	// Queue 초기화
	public void clear() {
		this._size = 0;
		this._rear = null;
	}
	
	
	// 주어진 순서의 원소 반환
	public T elementAt(int anOrder) {
		if(0 <= anOrder && anOrder <= this._size - 1) {
			Node<T> front = this._rear.next();
			
			Node<T> orderNode = front;
			for(int order = 0; order < anOrder; order++) {
				orderNode = orderNode.next();
			}
			
			return orderNode.element();
		}
		else {
			return null;
		}
	}
	
}
