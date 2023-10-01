/**
 * Defines a doubly-linked list class
 * @author Theo Lee
 */
import java.util.NoSuchElementException;

public class LinkedList<T> {
	private class Node {
		private T data;
		private Node next;
		private Node prev;
		
		public Node(T data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
	}
	
	private int length;
	private Node first;
	private Node last;
	private Node iterator;
	
	/**** CONSTRUCTORS ****/
	
	/**
	 * Instantiates a new LinkedList with default values
	 * @postcondition The LinkedList is setup and empty
	 */
	public LinkedList() {
		this.length = 0;
		this.first = null;
		this.last = null;
		this.iterator = null;
	}
	
    /**** ACCESSORS ****/
    
    /**
     * Returns the value stored in the first node
     * @precondition The list is not empty
     * @return the value stored at node first
     * @throws NoSuchElementException when precondition is violated
     */
	public T getFirst() throws NoSuchElementException {
		if(length == 0) {
			throw new NoSuchElementException("getFirst: List is empty"); // add a descriptive error message
		}
		return first.data;
	}
	
    /**
     * Returns the value stored in the last node
     * @precondition The list is not empty
     * @return the value stored in the node last
     * @throws NoSuchElementException when precondition is violated
     */
    public T getLast() throws NoSuchElementException {
    	if(length == 0) {
    		throw new NoSuchElementException("getLast: List is empty"); // add a descriptive error message
    	}
        return last.data;
    }
    
    /**
     * Returns the data stored in the iterator node
     * @precondition 
     * @return the data stored in the iterator node
     * @throws NullPointerException <fill in here>
     */
    public T getIterator() throws NullPointerException {
    	//precondition
    	return iterator.data; //general case
    }
    
    /**
     * Returns the current length of the LinkedList
     * @return the length of the LinkedList from 0 to n
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns whether the LinkedList is currently empty
     * @return whether the LinkedList is empty
     */
    public boolean isEmpty() {
       return length == 0;
    }
    
    /**
     * Returns whether the iterator is offEnd, i.e. null
     * @return whether the iterator is null
     */
    public boolean offEnd() {
    	return iterator == null;
    }
	
    /**** MUTATORS ****/

    /**
     * Creates a new first element
     * @param data the data to insert at the front of the LinkedList
     * @postcondition Creates a new first element, updates length
     */
    public void addFirst(T data) {
    	Node newNode = new Node(data);
    	if (length == 0) {
    		first = last = newNode;
    	}
    	else {
    		newNode.next = first;
    		first.prev = newNode;
    		first = newNode;
    	}
    	length++;
    }
	
    /**
     * Creates a new last element
     * @param data the data to insert at the end of the LinkedList
     * @postcondition Creates a new last element, updates length
     */
    public void addLast(T data) {
    	Node newNode = new Node(data);
    	if (length == 0) {
    		first = last = newNode;
    	}
    	else {
    		last.next = newNode;
    		newNode.prev = last;
    		last = newNode;
    	}
        length++;
    }
    
    /**
     * Inserts a new element after the iterator
     * @param data the data to insert
     * @precondition <fill in here>
     * @throws NullPointerException <fill in here>
     */
    public void addIterator(T data) throws NullPointerException{
        return;
    }
    
    /**
     * removes the element at the front of the LinkedList
     * @precondition The list is not empty
     * @postcondition Removes the element at the front of the LinkedList, and updates length if necessary
     * @throws NoSuchElementException when precondition is violated
     */
    public void removeFirst() throws NoSuchElementException {
    	if(length == 0) {
    		throw new NoSuchElementException("removeFirst: List is empty"); // add a descriptive error message
    	}
    	if(length == 1) {
    		first = last = iterator = null;
    		length = 0;
    	}
    	else { 
    		if(iterator == first) {
    			iterator = null;
    		}
    		first = first.next;
    		first.prev = null;
    		length--;
    	}
    }
    
    /**
     * removes the element at the end of the LinkedList
     * @precondition The list is not empty or length != 0 or ! isEmpty()
     * @postcondition Removes the element at the end of the LinkedList, and updates length if necessary, second to last Node becomes the last Node.
     * @throws NoSuchElementException when precondition is violated(when there is no last Node) 
     */
    public void removeLast() throws NoSuchElementException {
    	if(length == 0) {
    		throw new NoSuchElementException("removeLast: List is empty"); // add a descriptive error message
    	}
    	if(length == 1) {	// This is a edge case, only one Node
    		first = last = iterator = null;
    		length = 0;
    	}
    	else {	// general case
    		if(iterator == last) {
    			iterator = null;
    		}
    		last = last.prev;
    		last.next = null;
    		length--;
    	}
    }
    
    /**
     * removes the element referenced by the iterator
     * @precondition <fill in here>
     * @postcondition <fill in here>
     * @throws NullPointerException <fill in here>
     */
    public void removeIterator() throws NullPointerException {
         return;
    }

    /**
     * places the iterator at the first node
     * @postcondition the iterator becomes the first node 
     */
    public void positionIterator(){
    	iterator = first;
    }

    /**
     * Moves the iterator one node towards the last
     * @precondition <fill in here>
     * @postcondition <fill in here>
     * @throws NullPointerException <fill in here>
     */
    public void advanceIterator() throws NullPointerException {
    	//precondition
    	iterator = iterator.next; //general case
    }
    
    /**
     * Moves the iterator one node towards the first
     * @precondition <fill in here>
     * @postcondition <fill in here>
     * @throws NullPointerException <fill in here>
     */
    public void reverseIterator() throws NullPointerException {
         return;
    }
    
    /**** ADDITIONAL OPERATIONS ****/
    
	/**
	 * Converts the linkedList to a String, with each value separated by a blank
	 * line At the end of the String, place a new line character
	 * @return the linkedList as a String
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		Node temp = first;
		while(temp != null) {
			result.append(temp.data + " ");
			temp = temp.next;
		}
		return result.toString() + "\n";
	}
	
}
    