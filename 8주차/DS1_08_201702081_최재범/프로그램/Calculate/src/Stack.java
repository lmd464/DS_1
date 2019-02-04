
public interface Stack<T> {
	
	public boolean isEmpty();			// 비어있는지 확인
	public boolean isFull();				// 꽉 찼는지 확인
	public boolean push(T anElement);	// 추가
	public T pop();						// 제거
	public T peek();						// Top 의 원소 확인
	
}

