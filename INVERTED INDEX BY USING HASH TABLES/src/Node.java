
public class Node <V> {
	
	private V data;
	private int count;
	private Node next;
	public Node (V data, int count ) {
		this.data=data;
		this.count = count;
		
	}
	
	public V getData() {
		return data;
	}
	public void setData(V data) {
		this.data = data;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}

	
}

