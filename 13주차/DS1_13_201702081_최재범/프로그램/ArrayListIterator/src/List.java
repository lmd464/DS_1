
public interface List<T> {
	
	
	// % : 정수 입력 관련
	public boolean add(T anElement);
	public boolean contains(T anElement);
	public boolean isFull();
	
	
	
	// ~ : 초기화 관련
	public void clear();
	
	
	
	// - : 최솟값 삭제 관련
	public T removeMin();
	public boolean isEmpty();
	
	
	
	// + : 최댓값 삭제 관련
	public T removeMax();
/*	public boolean isEmpty();  */
	
	
	
	// # : 리스트 길이 출력 관련
	public int size();
	
	
	
	// ? : 특정 순서의 값 삭제 관련
	public T removeFrom(int aPosition);
	
	
	
	// / : 리스트의 원소 전부 출력 관련
	
	/* public T elementAt(int anOrder); */
	
	public Iterator<T> listIterator();
	
}
