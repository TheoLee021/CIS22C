package Week4_Lab8_2;

/**
 * Stack class - Two Queue Version
 * @author Theo Lee
 * CIS 22C, Lab 8.2
 */
import java.util.NoSuchElementException;

public class Stack<T extends Comparable<T>> implements LIFO<T> {
    private Queue<T> queue1;
    private Queue<T> queue2;

    /**** CONSTRUCTORS ****/

    /**
     * Default constructor for the Stack class
     */
    public Stack() {
        queue1 = new Queue<>();
        queue2 = new Queue<>();
    }

   /**
     * Converts an array into a Stack in the same order.
     * @param array the array to copy
     */
    public Stack(T[] array) {
        this.queue1 = new Queue<>();
        this.queue2 = new Queue<>();
        
        if (array != null) {
            for (int i = array.length - 1; i >= 0; i--) {
                push(array[i]);
            }
        }
    }

    /**
     * Copy constructor for the Stack class
     * @param original the Stack to copy
     * @postcondition a new Stack object which is an identical,
     * but distinct, copy of original
     */
    public Stack(Stack<T> original) {
        this.queue1 = new Queue<>();
        this.queue2 = new Queue<>();
        
        if (original == null || original.isEmpty()) {
            return;  // If the original stack is null or empty, create an empty stack
        }
        
        Queue<T> tempQueue1 = new Queue<>();
        Queue<T> tempQueue2 = new Queue<>();
        
        while (!original.isEmpty()) {
            T data = original.peek();  // Using peek() instead of pop()
            tempQueue1.enqueue(data);
            this.push(data);
            original.pop();  // Now, remove the element from original after using it
        }
        
        while (!tempQueue1.isEmpty()) {
            T data = tempQueue1.getFront();
            tempQueue2.enqueue(data);
            tempQueue1.dequeue();
        }
        
        while (!tempQueue2.isEmpty()) {
            T data = tempQueue2.getFront();
            original.push(data);
            tempQueue2.dequeue();
        }
    }

    /**** ACCESSORS ****/

	/**
	 * Returns the value stored in the top node
	 * @precondition The list is not empty
	 * @return the value stored at node top
	 * @throws NoSuchElementException when precondition is violated
	 */
    public T peek() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty!");
        }
        return queue1.getFront();
    }
    
    /**
     * Returns the number of elements in the Stack.
     * @return Size of the Stack.
     */
    public int getSize() {
        return queue1.getSize();
    }
    
	/**
	 * Returns whether the Stack is currently empty
	 * @return whether the Stack is empty
	 */
    public boolean isEmpty() {
        return queue1.isEmpty();
    }
    
    /**** MUTATORS ****/

    /**
     * Adds an element to the top of the Stack.
     * @param data The element to be added to the Stack.
     */
    public void push(T data) {
        
        // Move all elements from queue1 to queue2
        while (!queue1.isEmpty()) {
            queue2.enqueue(queue1.getFront());
            queue1.dequeue();
        }
        
        // Enqueue the new data to queue1
        queue1.enqueue(data);
        
        // Move all elements back from queue2 to queue1
        while (!queue2.isEmpty()) {
            queue1.enqueue(queue2.getFront());
            queue2.dequeue();
        }
    }
    
    /**
     * Removes the top element of the Stack.
     * @throws NoSuchElementException if the Stack is empty.
     */
    public void pop() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty!");
        }
        queue1.dequeue();
    }

    /**** ADDITONAL OPERATIONS ****/

    /**
     * Returns the values stored in the Stack
     * as a String, separated by a blank space
     * with a new line character at the end
     * @return a String of Stack values
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Queue<T> tempQueue = new Queue<>(queue1);
        while (!tempQueue.isEmpty()) {
            result.append(tempQueue.getFront()).append(" ");
            tempQueue.dequeue();
        }
        return result.toString() + "\n";
    }

    /**
     * Determines whether two objects are Stacks and
     * contain the same values in the same order
     * @param obj the Object to compare to this Stack
     * @return whether obj and this are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Stack)) {
            return false;
        }
        Stack<T> other = (Stack<T>) obj;
        if (this.getSize() != other.getSize()) {
            return false;
        }
        
        while (!this.isEmpty() && !other.isEmpty()) {
            if (!this.peek().equals(other.peek())) {
                return false;
            }
            T thisData = this.peek();
            T otherData = other.peek();
            this.pop();
            other.pop();
            if (!thisData.equals(otherData)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Creates a String of the Stack in reverse order.
     * @return a Stack in reverse order
     */
    public reverseStack() {
        if (isEmpty()) {
            return;
        }
        T data = peek();
        reverseStack();
        insertAtBottom(data);
    }
    
    /**
     * Helper method to insert an element at the bottom of the stack using recursion.
     * @param data The element to be inserted.
     */
    private void insertAtBottom(T data) {
        if (isEmpty()) {
            push(data);
            return;
        }
        T temp = peek();
        insertAtBottom(data);
        push(temp);
    }
}
