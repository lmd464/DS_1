
public class AppController_TreeTraversal 
implements VisitEventSourceForTreeTraversal<Integer, Integer> 
{
	
	private static final int DEFAULT_DATA_SIZE = 10;
	
	private AppView _appView;
	private DictionaryByBinarySearchTree<Integer, Integer> _dictionary;
	private int[] _list;
	private int dataSize;
	
	
	
	public AppController_TreeTraversal() {
		this._appView = new AppView();
	}
	
	
	
	public void run() {
		
		{
		this.dataSize = DEFAULT_DATA_SIZE;
		
		this._list = new int[this.dataSize];
		this._list = DataGenerator.randomList(this.dataSize);
		this._dictionary = new DictionaryByBinarySearchTree<Integer, Integer> ();
		
		this._dictionary.setVisitEvent(this);
		
		this.addToBinarySearchTreeAndShowShape();
		this.showInOrderOfBinarySearchTree();
		this.removeFromBinarySearchTreeAndShowShape();
		}
		
		
		
		{
		this.dataSize += 10;
		
		this._list = new int[this.dataSize];
		this._list = DataGenerator.randomList(this.dataSize);
		this._dictionary = new DictionaryByBinarySearchTree<Integer, Integer> ();
		
		this._dictionary.setVisitEvent(this);
		
		this.addToBinarySearchTreeAndShowShape();
		this.showInOrderOfBinarySearchTree();
		this.removeFromBinarySearchTreeAndShowShape();
		}
		
		
		this._appView.output("\n-----------------------------\n\n");
	}
	
	
	
	// Model 에서 Call Back 할 함수 : Key 와 Object 출력
	@Override
	public void visitByCallBack(DictionaryElement<Integer, Integer> anElement, int aLevel) {
		this._appView.outputLine(anElement.key() + "( " + anElement.object() + ")");
	}
	
	
	// Model 에서 Call Back 할 함수 : Key 와 Object 를 Tree 형태로 역순 출력
	@Override
	public void visitInReverseOrder(DictionaryElement<Integer, Integer> anElement, int aLevel) {
		
		for(int i = 0; i < aLevel; i++) {
			this._appView.output("      ");
		}
		this._appView.outputLine(anElement.key() + "( " + anElement.object() + ")");
	}
	
	
	
	
	
	
	
	// 비공개 함수
	
	// 사전에 무작위 원소 추가하며 트리 형태로 역순 출력
	private void addToBinarySearchTreeAndShowShape() {
		
		this._appView.outputLine("<<< 삽입 과정에서의 이진검색트리의 변화 >>>\n");
		this._list = DataGenerator.randomList(this.dataSize);
		
		for(int i = 0; i < this.dataSize; i++) {
			this._appView.outputLine(this._list[i] + "( " + i + ") 원소를 삽입한 후의 이진검색트리 : ");
			
			this._dictionary.addKeyAndObject(this._list[i], i);
			this._dictionary.scanReverseOfSortedOrder();
			this._appView.outputLine("");
		}
		
	}
	
	
	
	// 사전을 중위 탐색하며 원소 출력
	private void showInOrderOfBinarySearchTree() {
		this._appView.outputLine("\n<<< Inorder Traversal >>>");
		this._dictionary.scanInSortedorder();
		this._appView.outputLine("");
	}
	
	
	
	// 사전에서 무작위 원소를 제거하며 트리 형태로 역순 출력
	private void removeFromBinarySearchTreeAndShowShape() {
		this._appView.outputLine("\n<<< 삭제 과정에서의 이진검색트리의 변화 >>>\n");
		
		for(int i = 0; i < this.dataSize; i++) {
			this._appView.outputLine("Key 값이 " + this._list[i] + " 인 원소를 삭제한 후의 이진검색트리 : ");
			
			this._dictionary.removeObjectForKey(this._list[i]);
			this._dictionary.scanReverseOfSortedOrder();
		}
	}
	
}

