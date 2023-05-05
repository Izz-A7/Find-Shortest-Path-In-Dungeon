
public class DLPriorityQueue<T> implements PriorityQueueADT<T> {
	// Initializing instance variables
	private DLinkedNode<T> front;
	private DLinkedNode<T> rear;
	private int count;
	
	
	// Create empty priority (constructor)
	public DLPriorityQueue() {
		front = null;
		rear = null;
		count = 0;
	}
	
	
	// Adds a new node
	public void add (T dataItem, double priority) {
		
		// Create new node
		DLinkedNode<T> newNode = new DLinkedNode<>(dataItem, priority);
		
		// If isEmpty is true set front and rear equal to the new node
		if (isEmpty()) { 
			front = newNode; 
			rear = newNode;
		} 
		
		// If front priority is bigger than the priority we insert node in between front and second element of queue
		else if (front.getPriority() > priority) { 
			newNode.setNext(front); 
			front.setPrev(newNode); 
			front = newNode;
		} 
		
		// If rear priority is smaller than or equal to priority we add the node to the end of the queue
		else if (rear.getPriority() <= priority) { 
			newNode.setPrev(rear);
			rear.setNext(newNode); 
			rear = newNode;
		} 
		
		// Otherwise, we insert the node at its rightful position
		else {
			DLinkedNode<T> curr = front.getNext();
			
			while (curr.getPriority() <= priority) {
				curr = curr.getNext(); 
			}
			
			// Inserts new element between two existing ones
			newNode.setNext(curr); 
			newNode.setPrev(curr.getPrev()); 
			curr.getPrev().setNext(newNode); 
			curr.setPrev(newNode); 
		} 
		
	    // Increment count
		count++; 
	}

		
	// Updates priority
	public void updatePriority (T dataItem, double newPriority) throws InvalidElementException {
		// If isEmpty is true then throw an exception
		if (isEmpty()) {
            throw new InvalidElementException("The priority queue is empty.");
        }
		
		// Set current equal to front
        DLinkedNode<T> curr = front;
        
        // Iterate through to find the dataItem
        for (int  i = 0; i < count; i++) {
        	// If dataItem is found this will break and curr will point the node containing the dataItem
        	if (curr.getDataItem().equals(dataItem)) {
        		break;
        	}
        	
        	// Set current equal to node after current
        	curr = curr.getNext();
        }
        
        // If current equals null throw new exception
        if (curr == null) {
            throw new InvalidElementException("The data item was not found in the priority queue.");
        }
        
        // Remove the node and then re-add it with the updated priority
        remove(curr);
        add(dataItem, newPriority);
	}
	
	
	// Removes and returns data with smallest priority
	public T removeMin() throws EmptyPriorityQueueException {
		// If queue is empty then throw new exception
		if (isEmpty()) {
			throw new EmptyPriorityQueueException("Priority queue is empty.");
	    }
			
		// Get data item of front node and then remove it using the helper method
	    T result = front.getDataItem();
	    remove(front);
	        
	    return result; // Return result

	}

	
	// Returns true if the priority queue is empty and false otherwise
	public boolean isEmpty() {
		// If size of queue equals 0, return true
		if (size() == 0) {
			return true;
		}
		
		// Otherwise, return false
		else {
			return false;
		}
	}
	
	
	// Returns the size of the queue
	public int size() {
		return count;
	}
	
	
	// Building the string
	public String toString() {
		// Create new string builder and set current to front
	    StringBuilder sb = new StringBuilder();
	    DLinkedNode<T> curr = front;
	    
	    // Iterate through each node and then append the dataItem
	    while (curr != null) {
	        sb.append(curr.getDataItem().toString());
	        curr = curr.getNext();
	    }
	    
	    return sb.toString(); // Return the string
	}
	
	
	// Returns the rear/end of the queue
	public DLinkedNode<T> getRear() {
		return rear;
	}
	
	
	// Private helper method to remove nodes
    private void remove(DLinkedNode<T> node) {
    	// If node is null return nothing
        if (node == null) {
            return;
        } 
        
        // If node equals front, we return front as the next node
        if (node == front) {
            front = node.getNext();
        }
        
        // If the node equals rear, we return rear as the previous node
        if (node == rear) {
            rear = node.getPrev();
        } 
        
        // If the previous node does not equal null, we set the previous nodes next equal to the next node
        if (node.getPrev() != null) {
            node.getPrev().setNext(node.getNext());
        }
        
        // If the next node does not equal null, we set the next nodes previous equal to the previous node
        if (node.getNext() != null) {
        	node.getNext().setPrev(node.getPrev());
        }
        
        // Decrement count
        count--;
    }
}
