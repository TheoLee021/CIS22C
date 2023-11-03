/**
 * Stack class - singly-linked list version
 * @author Theo Lee
 * CIS 22C, Lab 6
 */
import java.util.NoSuchElementException;

public class Stack<T> implements LIFO<T> {
    private class Node {
        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top;
    private int size;

    /****CONSTRUCTORS****/

    /**
     * Default constructor for the Stack class
     * @postcondition a new Stack object with all fields
     * assigned default values
     */
    public Stack() {
    	top = null;
    	size = 0;
    }

    /**
     * Constructor for the Stack class
     * Converts an array into a Stack in the same order
     * @param an array of elements to copy
     * e.g. [1,2,3] becomes 1->2->3->null
     */
    public Stack(T[] array) {
    	this();
    	if(array != null) {
    		for(int i = array.length - 1; i >= 0; i--) {
    			this.push(array[i]);
    		}
    	}
    }

    /**
     * Copy constructor for the Stack class
     * @param original the Stack to copy
     * @postcondition a new Stack object which is
     * an identical, but distinct, copy of original
     * REQUIRED: THIS METHOD MUST BE IMPLEMENTED
     * IN O(N) TIME
     */
    public Stack(Stack<T> original) {
    	if(original == null || original.size == 0) {
    		return;
    	}
    	this.size = original.size;
    	this.top = new Node(original.top.data);
    	Node temp1 = original.top.next;
    	Node temp2 = this.top;
    	
    	while(temp1 != null) {
    		temp2.next = new Node(temp1.data);
    		temp1 = temp1.next;
    		temp2 = temp2.next;
    	}
    }

    /****ACCESSORS

	/**
	 * Returns the value stored in the top node
	 * @precondition The list is not empty
	 * @return the value stored at node top
	 * @throws NoSuchElementException when precondition is violated
	 */
    public T peek() throws NoSuchElementException {
    	if(this.size == 0) {
    		throw new NoSuchElementException("peek:The list is empty");
    	}
    	return this.top.data;
    }
    
	/**
	 * Returns the current size of the Stack
	 * @return the size of the Stack from 0 to n
	 */
    public int getSize() {
    	return this.size;
    }
    
	/**
	 * Returns whether the Stack is currently empty
	 * @return whether the Stack is empty
	 */
    public boolean isEmpty() {
    	return this.size == 0;
    }

    /****MUTATORS****/

    
	/**
	 * Creates a new top element
	 * @param data the data to insert at the top of the Stack
	 * @postcondition Creates a new top element, increase size
	 */
    public void push(T data) {
    	Node newNode = new Node(data);
    	if(this.size == 0) {
    		top = newNode;
    	}
    	else {
    		newNode.next = top;
    		top = newNode;
    	}
    	size++;
    }
    
	/**
	 * removes the element at the top of the Stack
	 * @precondition The list is not empty or length != 0 or ! isEmpty()
	 * @postcondition Removes the element at the top of the Stack, and updates
	 *                size if necessary, second to top Node becomes the top
	 *                Node.
	 * @throws NoSuchElementException when precondition is violated
	 */
    public void pop() throws NoSuchElementException {
    	if(this.size == 0) {
    		throw new NoSuchElementException("pop:The list is empty");
    	}
    	if(this.size == 1) {
    		this.top = null;
    		this.size = 0;
    	}
    	else {
    		top = top.next;
    		size--;
    	}
    }

    /****ADDITONAL OPERATIONS****/

    /**
     * Returns the values stored in the Stack
     * as a String, separated by a blank space
     * with a new line character at the end
     * @return a String of Stack values
     */
    public String toString() {
       StringBuilder result = new StringBuilder();
       Node temp = top;
       while (temp != null) {
    	   result.append(temp.data + " ");
    	   temp = temp.next;
       }
       return result.toString() + "\n";
    }

    /**
     * Determines whether two Stacks contain
     * the same values in the same order
     * @param obj the Object to compare to this Stack
     * @return whether obj and this Stack are equal
     */
    @SuppressWarnings("unchecked")
    @Override public boolean equals(Object obj) {
    	if (this == obj) {
    		return true;
    	}
    	else if (!(obj instanceof Stack)) {
            return false;
    	}
    	else {
    		Stack<T> newStack = (Stack<T>) obj;
    		if (this.size != newStack.size) {
    			return false;
    		}
    		else {
    			Node temp1 = this.top;
    			Node temp2 = newStack.top;
    			while (temp1 != null) {
    				if(temp1.data == null || temp2.data == null) {
    					if(temp1.data != temp2.data) {
    						return false;
    					}
    				}
    				else if(!(temp1.data.equals(temp2.data))) {
    					return false;
    				}
    				temp1 = temp1.next;
    				temp2 = temp2.next;
    			}
    			return true;
    		}
    	}
    }
}
