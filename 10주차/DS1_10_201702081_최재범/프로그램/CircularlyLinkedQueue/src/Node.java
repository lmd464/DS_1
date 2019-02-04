
public class Node<T> 
{
	
	private T _element;
	private Node<T> _next;
	
	
	// Constructor
	public Node() {}
	public Node(T givenElement) {
		this._element = givenElement;
		this._next = null;
	}
	public Node(T givenElement, Node<T> givenNode) {
		this._element = givenElement;
		this._next = givenNode;
	}
	
	
	// Getter
	public T element() {
		return this._element;
	}
	public Node<T> next() {
		return this._next;
	}
	
	
	// Setter
	public void setElement(T newElement) {
		this._element = newElement;
	}
	public void setNext(Node<T> newNext) {
		this._next = newNext;
	}
	
}
