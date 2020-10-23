

public class SingleLinkedList <V>{

	private Node head;
	public SingleLinkedList() {
		head=null;
	}
	
	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

	public void add(V data ) {
		Node newNode;
		if(head==null) {
			newNode= new Node(data,1);
			head= newNode;
		}
		else {
			Node temp = head;
			Node prev =null;
			while(temp != null) {
				if(temp.getData() == data) {
					temp.setCount(temp.getCount()+1);
					break;
				}
				prev=temp;
				temp = temp.getNext();			
				}
			if (temp == null) {
				newNode= new Node(data,1);
				prev.setNext(newNode);
			}
			
		}
		
	}
	
//		public Boolean search(int data) {
//			boolean flag=false;
//			if(head==null) {
//				System.out.println("List is empty");
//				
//			}
//			else {
//				Node temp=head;
//				while(temp!=null) {
//					if((int)temp.getData()==data) {
//						flag=true;break;
//					}
//					temp=temp.getNext();
//				}
//			}
//			return flag;
//		}
		
		public void display() {
			if(head==null) {
				System.out.println("List is empty!'!");
			}
			else {
				Node temp=head;
				while(temp != null) {
					System.out.println(temp.getData()+" "+ temp.getCount());
					temp=temp.getNext();
				}
				
			}
		}
}
