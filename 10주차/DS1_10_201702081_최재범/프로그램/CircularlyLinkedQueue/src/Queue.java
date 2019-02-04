
public interface Queue<T> 
{
	
	public boolean isEmpty();
	public boolean isFull();
	public boolean enQueue(T anElement);
	public T deQueue();
	public T frontElement();
	
}
