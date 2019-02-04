
public interface VisitEventSourceForTreeTraversal<Key, Obj> {
	public void visitByCallBack(DictionaryElement<Key, Obj> anElement, int aLevel);
	public void visitInReverseOrder(DictionaryElement<Key, Obj> anElement, int aLevel);
}
