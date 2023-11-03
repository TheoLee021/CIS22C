package Week6;

/**
 * The Queue class definition
 * @author Theo Lee
 * CIS 22C, Lab 5
 * @param <T> the generic data stored in the Queue
 */

import java.util.NoSuchElementException;

public class Queue<T> implements Q<T> {
    private class Node {
        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private int size;
    private Node front;
    private Node end;

    /****CONSTRUCTORS****/

    /**
     * Default constructor for the Queue class
     * @postcondition a new Queue object with all fields
     * assigned default values
     */
    public Queue() {
        front = end = null;
        size = 0;
    }

    /**
     * Converts an array into a Queue
     * @param array the array to copy into
     * the Queue
     */
    public Queue(T[] array) {
		this();
    	if (array != null) {
    		for(int i = 0; i < array.length; i++) {
    			this.enqueue(array[i]);
    		}
    	}

    }

    /**
     * Copy constructor for the Queue class
     * Makes a deep copy of the parameter
     * @param original the Queue to copy
     * @postcondition a new Queue object, copy of the original queue
     */
    public Queue(Queue<T> original) {
    	if(original == null || original.size == 0) {
    		return;
    	} else {
			Node temp = original.front;
    		while(temp != null) {
    			this.enqueue(temp.data);
    			temp = temp.next;
    		}
    	}
    }

    /****ACCESSORS****/

    /**
     * Returns the value stored at the front
     * of the Queue
     * @return the value at the front of the queue
     * @precondition !isEmpty()
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    public T getFront() throws NoSuchElementException {
    	if (isEmpty()) {
    		throw new NoSuchElementException("getFront: The list is empty");
    	}
    	return front.data;
    }

    /**
     * Returns the size of the Queue
     * @return the size from 0 to n
     */
    public int getSize() {
        return size;
    }

    /**
     * Determines whether a Queue is empty
     * @return whether the Queue contains no elements
     */
    public boolean isEmpty() {
        if (size == 0) {
        	return true;
        }
    	return false;
    }

    /****MUTATORS****/

    /**
     * Inserts a new value at the end of the Queue
     * @param data the new data to insert
     * @postcondition a new node at the end
     */
    public void enqueue(T data) {
        Node newNode = new Node(data); 
    	if (size == 0) {
        	front = end = newNode; 
        }
    	else {
    		end.next = newNode;
    		end = newNode;
    	}
    	size++;
    }

    /**
     * Removes the front element in the Queue
     * @precondition <You fill in here>
     * @throws NoSuchElementException when
     * the precondition is violated
     * @postcondition <You fill in here>
     */
    public void dequeue() throws NoSuchElementException {
        if(isEmpty()) {
        	throw new NoSuchElementException ("dequeue: The list is empty");
        }
        else if(size == 1) {
        	front = end = null;
        }
        else {
        	front = front.next;
        }
        size --;
    }

    /****ADDITONAL OPERATIONS****/

    /**
     * Returns the values stored in the Queue
     * as a String, separated by a blank space
     * with a new line character at the end
     * @return a String of Queue values
     */
    @Override public String toString() {
        StringBuilder result = new StringBuilder();
        Node temp = front;
        while(temp != null) {
        	result.append(temp.data + " ");
        	temp = temp.next;
        }
        return result.toString() + "\n";
    }

    /**
     * Determines whether two Queues contain
     * the same values in the same order
     * @param obj the Object to compare to this
     * @return whether obj and this are equal
     */
    @SuppressWarnings("unchecked") // good practice to remove warning here
    @Override public boolean equals(Object obj)  {
        if(obj == this) {
        	return true;
        } else if(!(obj instanceof Queue)) {
        	return false;
        } else {
        	Queue<T> newList = (Queue<T>) obj;
        	if(size != newList.size) {
        		return false;
        	} else {
        		Node temp1 = this.front;
        		Node temp2 = newList.front;
        		while(temp1 != null) {
        			if (temp1.data == null || temp2.data == null) {
        				if (temp1.data != temp2.data) {
        					return false;
        					}
        			} else if (!(temp1.data.equals(temp2.data))) {
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
