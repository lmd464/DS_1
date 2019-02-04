
public class Node<Element> {
	
	private Element _element;			// 현재 노드의 원소
	private Node<Element> _next; 		// 다음 노드
	
	
	// 생성자
	
	// 디폴트
	public Node() {
		this._element = null;
		this._next = null;
	}
	
	// 원소만 제공
	public Node(Element givenElement) {
		this._element = givenElement;
		this._next = null;
	}
	
	// 원소와 다음 노드 제공
	public Node(Element givenElement, Node<Element> givenNext) {
		this._element = givenElement;
		this._next = givenNext;
	}
	
	
	// Getter
	public Element element() {
		return this._element;
	}
	public Node<Element> next() {
		return this._next;
	}
	
	// Setter
	public void setElement(Element givenElement) {
		this._element = givenElement;
	}
	public void setNext(Node<Element> givenNext) {
		this._next = givenNext;
	}
	
	
}
