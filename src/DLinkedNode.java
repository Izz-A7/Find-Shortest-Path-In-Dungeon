
public class DLinkedNode<T> {
	// Initializing the instance variables
	private T dataItem;
	private double priority;
	private DLinkedNode<T> next;
	private DLinkedNode<T> prev;
	
	
	// Constructing new node with the given data
	public DLinkedNode (T data, double prio) {
		dataItem = data;
		priority = prio;
		next = null;
		prev = null;
	}
	
	
	// Constructor creates a new node with null data and 0 priority
	public DLinkedNode() {
		this(null, 0);
	}
	
	
	// Returns priority value
	public double getPriority() {
		return priority;
	}
	
	
	// Returns data item
	public T getDataItem() {
		return dataItem;
	}
	
	
	// Returns next node
	public DLinkedNode<T>  getNext() {
		return next;
	}
	
	
	// Returns previous node
	public DLinkedNode<T> getPrev() {
		return prev;
	}
	
	
	// Sets the data item
	public void setDataItem(T data) {
		dataItem = data;
	}
	
	
	// Sets the next node
	public void setNext(DLinkedNode<T> node) {
		next = node;
	}
	
	
	// Sets the previous node
	public void setPrev(DLinkedNode<T> node) {
		prev = node;
	}
	
	
	// Sets the value of the priority
	public void setPriority(double prio) {
		priority = prio;
	}
}
