
public class DictionaryByBinarySearchTree <Key extends Comparable<Key>, Obj> 
extends Dictionary <Key, Obj>
{

	// 인스턴스 변수
	private BinaryNode<DictionaryElement<Key, Obj>> _root;
	private VisitEventSourceForTreeTraversal<Key, Obj> _visitEvent;		// 전달받은 Visit Event 객체를 저장할 곳
	
	
	// Getter / Setter
	private BinaryNode<DictionaryElement<Key, Obj>> root() { return this._root; }
	private void setRoot(BinaryNode<DictionaryElement<Key, Obj>> newRoot) { this._root = newRoot; }
	
	public VisitEventSourceForTreeTraversal<Key, Obj> visitEvent() { return this._visitEvent; }
	public void setVisitEvent(VisitEventSourceForTreeTraversal<Key, Obj> newVisitEvent) { this._visitEvent = newVisitEvent; }
	
	
	// 생성자
	public DictionaryByBinarySearchTree() {
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
		return ( this.elementForKey(aKey) != null );
	}

	
	
	// Key 에 대응하는 Object 탐색
	@Override
	public Obj objectForKey(Key aKey) {
		DictionaryElement<Key, Obj> element = this.elementForKey(aKey);
		if(element != null)
			return element.object();
		else
			return null;
	}

	
	
	// Key 와 Object 쌍 추가
	@Override
	public boolean addKeyAndObject(Key aKey, Obj anObject) {
		
		DictionaryElement<Key, Obj> elementForAdd = 
				new DictionaryElement<Key, Obj> (aKey, anObject);
		BinaryNode<DictionaryElement<Key, Obj>> nodeForAdd = 
				new BinaryNode<DictionaryElement<Key, Obj>> (elementForAdd, null, null);
		
		// 비어있을 때 추가
		if(this.root() == null) {
			this.setRoot(nodeForAdd);
			this.setSize(1);
			return true;
		}
		
		
		// 중간에 추가
		BinaryNode<DictionaryElement<Key, Obj>> current = this.root();
		
		while(aKey.compareTo(current.element().key()) != 0) {	// Key 가 이미 존재하면 false
			
			// 현재 Key보다 더 작으므로 왼쪽 트리에 추가
			if(aKey.compareTo(current.element().key()) < 0) {
				
				// Left 없으면 그냥 추가
				if(current.left() == null) {
					current.setLeft(nodeForAdd);
					this.setSize(this.size() + 1);
					return true;
				}
				
				// Left 있으면, 그 곳을 기준으로 하여 다시 따지기
				else
					current = current.left();
			}
			
			
			// 현재 Key 보다 더 크므로 오른쪽 트리에 추가
			else {
				
				// Right 없으면 그냥 추가
				if(current.right() == null) {
					current.setRight(nodeForAdd);
					this.setSize(this.size() + 1);
					return true;
				}
				
				// Right 있으면, 그 곳을 기준으로 하여 다시 따지기
				else
					current = current.right();
			}
		}
		
		// Key 가 이미 존재하여 while 벗어남
		return false;
	}

	
	
	// Key 에 대응하는 Object 제거
	@Override
	public Obj removeObjectForKey(Key aKey) {
		
		// 비었음
		if(this.root() == null)
			return null;
		
		
		// 현재 root 에서 찾음
		if( aKey.compareTo(this.root().element().key()) == 0 ) {
			Obj objectForRemove = this.root().element().object();
			
			// left, right 모두 없음
			if( this.root().left() == null && this.root().right() == null )
				this.setRoot(null);
			
			// left 없고 right 있음 : right 로 대체
			else if( this.root().left() == null )
				this.setRoot(this.root().right());
			
			// left 있고 right 없음 : left 로 대체
			else if( this.root().right() == null )
				this.setRoot(this.root().left());
			
			// left, right 둘 다 있음 : Left tree 의 RightMost 삭제하여 대체
			else
				this.root().setElement( this.removeRightMostElementOfLeftSubTree(this.root()) );
			
			this.setSize(this.size() - 1);
			return objectForRemove;
		}
		
		
		
		
		// root 에서 못찾음 -> 하위로 탐색
		BinaryNode<DictionaryElement<Key, Obj>> current = this.root();
		BinaryNode<DictionaryElement<Key, Obj>> child = null;
		
		do {
			// 찾는 Key 가 현재 Key 보다 더 작음
			if(aKey.compareTo(current.element().key()) < 0) {
				child = current.left();
				
				if(child == null) {
					return null;
				}
				
				// 왼쪽 서브트리의 root 에 찾는 Key 가 존재
				if(aKey.compareTo(child.element().key()) == 0) {
					Obj objectForRemove = child.element().object();
					
					// left, right 모두 없음
					if(child.left() == null && child.right() == null)
						current.setLeft(null);
					
					// left 없고 right 있음
					else if(child.left() == null)
						current.setLeft(child.right());
					
					// left 있고 right 없음
					else if(child.right() == null)
						current.setRight(child.left());
					
					// left, right 둘 다 있음 : child 의 Left tree 의 RightMost 삭제하여 대체
					else 
						child.setElement(this.removeRightMostElementOfLeftSubTree(child));
						
					this.setSize(this.size() - 1);
					
					return objectForRemove;
				}
			}
			
			
			// 찾는 Key 가 현재 Key 보다 더 큼
			else {
				child = current.right();
				
				if(child == null) {
					return null;
				}
				
				// 오른쪽 서브트리의 root 에 찾는 Key 가 존재
				if(aKey.compareTo(child.element().key()) == 0) {
					Obj objectForRemove = child.element().object();
					
					// left, right 모두 없음
					if(child.left() == null && child.right() == null)
						current.setRight(null);
					
					// left 없고 right 있음
					else if(child.left() == null)
						current.setRight(child.right());
					
					// left 있고 right 없음
					else if(child.right() == null)
						current.setRight(child.left());
					
					// left, right 둘 다 있음 : child 의 Left tree 의 RightMost 삭제하여 대체
					else 
						child.setElement(this.removeRightMostElementOfLeftSubTree(child));
						
					this.setSize(this.size() - 1);
					
					return objectForRemove;
				}
			}
			
			// 못찾음 -> 계속 하위로 이동
			current = child;
			
		} while(true);
		
	}

	
	
	// 사전 초기화
	@Override
	public void clear() {
		this.setSize(0);
		this.setRoot(null);
	}
	
	
	
	// 중위 탐색
	public void scanInSortedorder() {
		this.inorderRecursively(this.root(), 1);
	}
	
	
	
	// 중위 탐색 : 거꾸로
	public void scanReverseOfSortedOrder() {
		this.reverseOfInorderRecursively(this.root(), 1);
	}
	
	
	
	
	
	// 비공개 함수
	
	// Key 에 대응하는 DictionaryElement 반환
	private DictionaryElement<Key, Obj> elementForKey(Key aKey) {
		BinaryNode <DictionaryElement <Key, Obj>> current = this.root();
		while(current != null) {
			
			// 현재 노드에서 Key 찾음
			if(current.element().key().compareTo(aKey) == 0)
				return current.element();
			
			// 현재 노드의 Key 가 찾는 Key 보다 더 큼 -> 더 작은 곳 찾기
			else if(current.element().key().compareTo(aKey) > 0)
				current = current.left();
			
			// 현재 노드의 Key 가 찾는 Key 보다 더 작음 -> 더 큰 곳 찾기
			else
				current = current.right();
			
		}
		
		// 못 찾음
		return null;
	}
	
	
	
	// 왼쪽 서브트리의 가장 오른쪽 Element 반환
	// 왼쪽 서브트리가 존재할 때만 call됨
	private DictionaryElement<Key, Obj> removeRightMostElementOfLeftSubTree
	(BinaryNode<DictionaryElement<Key, Obj>> root)
	{
		BinaryNode<DictionaryElement<Key, Obj>> leftOfRoot = root.left();
		
		// 왼쪽 서브트리의 right 에 아무 것도 없음 : 왼쪽 서브트리의 루트가 rightMost
		if(leftOfRoot.right() == null) {
			root.setLeft(leftOfRoot.left());		// 삭제
			return leftOfRoot.element();
		}
		
		// 왼쪽 서브트리의 right 가 존재 -> 탐색
		else {
			BinaryNode<DictionaryElement<Key, Obj>> parentOfRightMost = leftOfRoot;
			BinaryNode<DictionaryElement<Key, Obj>> rightMost = parentOfRightMost.right();
			
			// RightMost 까지 이동
			while(rightMost.right() != null) {
				parentOfRightMost = rightMost;
				rightMost = rightMost.right();
			}
			
			// 다음 rightMost : rightMost의 왼쪽 자식
			parentOfRightMost.setRight(rightMost.left());

			return rightMost.element();
		}
	}
	

	
	// 중위 탐색 : Call Back 함수 이용
	private void inorderRecursively( BinaryNode<DictionaryElement<Key, Obj>> aRootOfSubtree, int aLevel ) {
		if(aRootOfSubtree != null) {
			this.inorderRecursively(aRootOfSubtree.left(), aLevel + 1);
			this.visitEvent().visitByCallBack(aRootOfSubtree.element(), aLevel);
			this.inorderRecursively(aRootOfSubtree.right(), aLevel + 1);
		}
	}
	
	
	
	// 거꾸로 중위 탐색 : Call Back 함수 이용
	private void reverseOfInorderRecursively( BinaryNode<DictionaryElement<Key, Obj>> aRootOfSubtree, int aLevel ) {
		if(aRootOfSubtree != null) {
			this.reverseOfInorderRecursively(aRootOfSubtree.right(), aLevel + 1);
			this.visitEvent().visitInReverseOrder(aRootOfSubtree.element(), aLevel);
			this.reverseOfInorderRecursively(aRootOfSubtree.left(), aLevel + 1);
		}
	}
	
	
	
}
