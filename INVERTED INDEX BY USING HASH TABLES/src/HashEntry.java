
public class HashEntry <K,V>{
	private K key;
	private SingleLinkedList<V> sll;
	HashEntry(K key, V value) {
		this.key = key;
		this.sll = new SingleLinkedList();
		sll.add(value);
	}

	public K getKey() {
		return key;
	}

	
	public SingleLinkedList<V> getValue() {
		return sll;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public void add(V data) {
		sll.add(data);
	}

	public void removeValue() {

	
		
	}
	
	
}
