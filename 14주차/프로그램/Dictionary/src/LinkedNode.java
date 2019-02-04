
public class LinkedNode<E> {
	
	private E _element;
	private LinkedNode<E> _next;
	
	
	// Getter / Setter
	public E element() { return this._element; }
	public void setElement(E newElement) { this._element = newElement; }
	
	public LinkedNode<E> next() { return this._next; }
	public void setNext(LinkedNode<E> newNext) { this._next = newNext; }

	
	
	// 생성자
	public LinkedNode() { }
	public LinkedNode(E givenElement, LinkedNode<E> givenNext) {
		this._element = givenElement;
		this._next = givenNext;
	}
	
}
