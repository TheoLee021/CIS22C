package Week4_Lab8;

/**
 * Stack class - Array Version
 * @author Theo Lee
 * CIS 22C, Lab 8.1
 */
import java.util.NoSuchElementException;

public class Stack<T> implements LIFO<T> {
    private T[] stack;
    private int currSize;
    private final int SIZE = 10;

    /****CONSTRUCTORS****/

    /**
     * Default constructor for the Queue class
     * with an initial length of 10 and
     * no elements
     */
    @SuppressWarnings("unchecked")
    public Stack() {
        stack = (T[]) new Object[SIZE];
        currSize = 0;
    }

   /**
     * Converts an array into a Stack in the same order
     * @param array the array to copy
     */
    @SuppressWarnings("unchecked")
    public Stack(T[] array) {
    	this();
    	if(array != null) {
    		stack = (T[]) new Object[array.length + SIZE];
    		for(int i = 0; i < array.length; i ++) {
    			stack[i] = array[i];
    			}
    		currSize = array.length;
    		}
    	}

    /**
     * Copy constructor for the Stack class
     * @param original the Stack to copy
     * @postcondition a new Stack object which is
     * an identical, but distinct, copy of original
     */
    @SuppressWarnings("unchecked")
    public Stack(Stack<T> original) {
    	if(original == null) {
    		return;
    	}
    	stack = (T[]) new Object[original.currSize + SIZE];
    	for (int i = 0; i < original.currSize; i++) {
    		stack[i] = original.stack[i];
    	}
    	currSize = original.currSize;
    }

    /****ACCESSORS****/

    /**
     * Returns the value stored at the front
     * of the Stack
     * @return the value at the front of the Stack
     * @precondition !isEmpty()
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    public T peek() throws NoSuchElementException {
    	if(currSize == 0) {
    		throw new NoSuchElementException("peek:The stack is empty");
    	}
    	return stack[currSize - 1];
    }
    
    /**
     * Returns the size of the Stack
     * @return the size from 0 to n
     */
    public int getSize() {
    	return currSize;
    }
    
    /**
     * Determines whether a Stack is empty
     * @return whether the Stack contains
     * no elements
     */
    public boolean isEmpty() {
    	return currSize == 0;
    }


    /****MUTATORS****/

    /**
     * Inserts a new value in the Stack
     * @param data the new data to insert
     * @postcondition a new node in the Stack
     */
    public void push(T data) {
    	if(currSize == stack.length) {
    		resize();
    	}
    	stack[currSize++] = data;
    }
    
    /**
     * Removes the front element in the Stack
     * @precondition !isEmpty()
     * @throws NoSuchElementException when
     * the precondition is violated
     * @postcondition the front element has
     * been removed
     */
    public void pop() throws NoSuchElementException {
    	if(currSize == 0) {
    		throw new NoSuchElementException("pop:The list is empty");
    	}
    	stack[--currSize] = null;
    }


    /****ADDITONAL OPERATIONS****/

    /**
     * Returns the values stored in the Stack
     * as a String, separated by a blank space
     * with a new line character at the end
     * @return a String of Stack values
     */
    @Override public String toString() {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < currSize; i++) {
        	result.append(stack[i]).append(" ");
        }
        return result.toString() + "\n";
    }

    /**
     * Determines whether two obects are Stacks and
     * contain the same values in the same order
     * @param obj the Object to compare to this Stack
     * @return whether obj and this are equal
     */
    @Override public boolean equals(Object obj)  {
    	if(this == obj) {
    		return true;
    	}
    	if(!(obj instanceof Stack)) {
    		return false;
    	}
    	Stack<T> newStack = (Stack<T>) obj;
    	if(this.currSize != newStack.currSize) {
    		return false;
    	}
    	for (int i = 0; i < currSize; i++) {
    		if(!this.stack[i].equals(newStack.stack[i])) {
    			return false;
    		}
    	}
    	return true;
    }

    /**
     * Creates a String of the Stack in reverse order by calling the
     * recursive helper method.
     * @return a Stack in reverse order
     */
    public String reverseStack() {
    	return reverseStack(currSize - 1) + "\n";
    }

    /**PRIVATE HELPER METHODS*/

    /**
     * Recursively creates a String where the data is in reverse order.
     * @param index the current index
     * @return a String of this Stack in reverse order
     */
    private String reverseStack(int index) {
    	if(index < 0) {
    		return "";
    	}
    	return stack[index] + " " + reverseStack(index - 1);
    }

    /**
     * Increases the current array
     * size by 10
     */
    @SuppressWarnings("unchecked")
    private void resize() {
       T[] newStack = (T[]) new Object[stack.length + SIZE];
       System.arraycopy(stack, 0, newStack, 0, currSize);
       stack = newStack;
    }
}
