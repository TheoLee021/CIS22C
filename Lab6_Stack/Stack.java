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
    		for(int i = 0; i < array.length; i++) {
    			this.push(array[i]);
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

    }

    /****ACCESSORS****/

    /**FILL IN HERE*/

    /****MUTATORS****/

    
    /**
     * Push
     */
    public void push(T data) {
    	Node newNode = new Node(data);
    	if(length == 0) {
    		first = last = newNode;
    	}
    	else {
    		newNode.next = first;
    		first.prev = newNode;
    		first = newNode;
    	}
    	length ++;
    	
    }
    
    public void removeFirst(T data) {
    	
    }

    /****ADDITONAL OPERATIONS****/

    /**
     * Returns the values stored in the Stack
     * as a String, separated by a blank space
     * with a new line character at the end
     * @return a String of Stack values
     */
    public String toString() {
        return "";
    }

    /**
     * Determines whether two Stacks contain
     * the same values in the same order
     * @param obj the Object to compare to this Stack
     * @return whether obj and this Stack are equal
     */
    @SuppressWarnings("unchecked")
    @Override public boolean equals(Object obj) {
            return false;
        }
    }
}
