
public class LinkList {

	//DATA MEMBERS 
	private Node head; 
	private Node tail; 
	private int sum=0; 
	
	
	public LinkList () {
		setHead(null); 
		tail = null; 
	}
	
	public void add(Integer number) {
		Node temp = new Node (number);
		
		//if the list is empty 
		if (getHead() ==null) {
			setHead(temp); 
			tail = temp; 
		}
		//if the list is not empty 
		else {
			tail.right = temp; 
			temp.left= tail;
			tail = temp;
		}
	}
	
	/*
	 * This is method will computer the mean of each number node in the link list 
	 * @param sum will hold the mean recursively 
	 */
	
	public int sumRecursive(Node head){
		  if (head == null) {
		    return 0;
		  }
		  sum =  head.myInt + sumRecursive(head.right);
		  return sum;
		}
	/*
	 * this method will return the length of a linklist 
	 * 
	 */
	
	public int length() {
		Node temp = this.getHead(); 
		int count = 0; 
		
		while (temp != null) {
			temp = temp.right; 
			count +=1; 
		}
		return count;
	}

	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}
}