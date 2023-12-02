package Week8_Lab15;

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
	 * 
	 * @postcondition The LinkedList is setup and empty
	 */
	public LinkedList() {
		this.length = 0;
		this.first = null;
		this.last = null;
		this.iterator = null;
	}

	/**
	 * Converts the given array into a LinkedList
	 * 
	 * @param array the array of values to insert into this LinkedList
	 * @postcondition Creates a LinkedList with elements of the array
	 */
	public LinkedList(T[] array) {
		this();
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				addLast(array[i]);
			}
		}
	}

	/**
	 * Instantiates a new LinkedList by copying another List
	 * 
	 * @param original the LinkedList to copy
	 * @postcondition a new List object, which is an identical, but separate, copy
	 *                of the LinkedList original
	 */
	public LinkedList(LinkedList<T> original) {
		if (original == null || original.length == 0) {
			return;
		} else {
			Node temp = original.first;
			while (temp != null) {
				this.addLast(temp.data);
				temp = temp.next;
			}
			this.iterator = null;
		}
	}

	/**** ACCESSORS ****/

	/**
	 * Returns the value stored in the first node
	 * 
	 * @precondition The list is not empty
	 * @return the value stored at node first
	 * @throws NoSuchElementException when precondition is violated
	 */
	public T getFirst() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("getFirst: List is empty"); // add a descriptive error message
		}
		return first.data;
	}

	/**
	 * Returns the value stored in the last node
	 * 
	 * @precondition The list is not empty
	 * @return the value stored in the node last
	 * @throws NoSuchElementException when precondition is violated
	 */
	public T getLast() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("getLast: List is empty"); // add a descriptive error message
		}
		return last.data;
	}

	/**
	 * Returns the data stored in the iterator node
	 * 
	 * @precondition The iterator is not null
	 * @return the data stored in the iterator node
	 * @throws NullPointerException when precondition is violated
	 */
	public T getIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("getIterator: Iterator is null");
		}
		return iterator.data; // general case
	}

	/**
	 * Returns the current length of the LinkedList
	 * 
	 * @return the length of the LinkedList from 0 to n
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Returns whether the LinkedList is currently empty
	 * 
	 * @return whether the LinkedList is empty
	 */
	public boolean isEmpty() {
		return length == 0;
	}

	/**
	 * Returns whether the iterator is offEnd, i.e. null
	 * 
	 * @return whether the iterator is null
	 */
	public boolean offEnd() {
		return iterator == null;
	}

	/**** MUTATORS ****/

	/**
	 * Creates a new first element
	 * 
	 * @param data the data to insert at the front of the LinkedList
	 * @postcondition Creates a new first element, updates length
	 */
	public void addFirst(T data) {
		Node newNode = new Node(data);
		if (length == 0) {
			first = last = newNode;
		} else {
			newNode.next = first;
			first.prev = newNode;
			first = newNode;
		}
		length++;
	}

	/**
	 * Creates a new last element
	 * 
	 * @param data the data to insert at the end of the LinkedList
	 * @postcondition Creates a new last element, updates length
	 */
	public void addLast(T data) {
		Node newNode = new Node(data);
		if (length == 0) {
			first = last = newNode;
		} else {
			last.next = newNode;
			newNode.prev = last;
			last = newNode;
		}
		length++;
	}

	/**
	 * Inserts a new element after the iterator
	 * 
	 * @param data the data to insert
	 * @precondition The iterator is not null
	 * @throws NullPointerException when precondition is violated
	 */
	public void addIterator(T data) throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("addIterator: Iterator is null");
		} else if (iterator == last) { // edge case: insert at the last
			addLast(data);
		} else {
			Node newNode = new Node(data);
			newNode.next = iterator.next;
			newNode.prev = iterator;
			iterator.next.prev = newNode;
			iterator.next = newNode;
			length++;
		}
	}

	/**
	 * removes the element at the front of the LinkedList
	 * 
	 * @precondition The list is not empty
	 * @postcondition Removes the element at the front of the LinkedList, and
	 *                updates length if necessary
	 * @throws NoSuchElementException when precondition is violated
	 */
	public void removeFirst() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("removeFirst: List is empty"); // add a descriptive error message
		}
		if (length == 1) {
			first = last = iterator = null;
			length = 0;
		} else {
			if (iterator == first) {
				iterator = null;
			}
			first = first.next;
			first.prev = null;
			length--;
		}
	}

	/**
	 * removes the element at the end of the LinkedList
	 * 
	 * @precondition The list is not empty or length != 0 or ! isEmpty()
	 * @postcondition Removes the element at the end of the LinkedList, and updates
	 *                length if necessary, second to last Node becomes the last
	 *                Node.
	 * @throws NoSuchElementException when precondition is violated(when there is no
	 *                                last Node)
	 */
	public void removeLast() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("removeLast: List is empty"); // add a descriptive error message
		}
		if (length == 1) { // This is an edge case, only one Node
			first = last = iterator = null;
			length = 0;
		} else { // general case
			if (iterator == last) {
				iterator = null;
			}
			last = last.prev;
			last.next = null;
			length--;
		}
	}

	/**
	 * removes the element referenced by the iterator
	 * 
	 * @precondition The iterator is not null
	 * @postcondition One node referenced by the iterator is removed
	 * @throws NullPointerException when precondition is violated
	 */
	public void removeIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("removeIterator: iterator is null");
		} else if (iterator == first) {
			removeFirst();
		} else if (iterator == last) {
			removeLast();
		} else {
			iterator.prev.next = iterator.next;
			iterator.next.prev = iterator.prev;
			iterator = null;
			length--;
		}
	}

	/**
	 * places the iterator at the first node
	 * 
	 * @postcondition The iterator becomes the first node
	 */
	public void positionIterator() {
		iterator = first;
	}

	/**
	 * Moves the iterator one node towards the last
	 * 
	 * @precondition The iterator is not null
	 * @postcondition The iterator becomes the next node
	 * @throws NullPointerException when precondition is violated
	 */
	public void advanceIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("advanceIterator: The iterator is null");
		} else {
			iterator = iterator.next; // general case
		}
	}

	/**
	 * Moves the iterator one node towards the first
	 * 
	 * @precondition The iterator is not null
	 * @postcondition The iterator becomes the previous node
	 * @throws NullPointerException when precondition is violated
	 */
	public void reverseIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("reverseIterator: The iterator is null");
		} else {
			iterator = iterator.prev;
		}
	}

	/**** ADDITIONAL OPERATIONS ****/

	/**
	 * Re-sets LinkedList to empty as if the default constructor had just been
	 * called
	 */
	public void clear() {
		length = 0;
		first = last = iterator = null;
	}

	/**
	 * Converts the linkedList to a String, with each value separated by a blank
	 * line At the end of the String, place a new line character
	 * 
	 * @return the linkedList as a String
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		Node temp = first;
		while (temp != null) {
			result.append(temp.data + " ");
			temp = temp.next;
		}
		return result.toString() + "\n";
	}

	/**
	 * Determines whether the given Object is another LinkedList, containing the
	 * same data in the same order
	 * 
	 * @param obj another Object
	 * @return whether there is equality
	 */
	@SuppressWarnings("unchecked") // good practice to remove warning here
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (!(obj instanceof LinkedList)) {
			return false;
		} else {
			LinkedList<T> newList = (LinkedList<T>) obj;
			if (length != newList.length) {
				return false;
			} else {
				Node temp1 = this.first;
				Node temp2 = newList.first;
				while (temp1 != null) {
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

	/** CHALLENGE METHODS */

	/**
	 * Moves all nodes in the list towards the end of the list the number of times
	 * specified Any node that falls off the end of the list as it moves forward
	 * will be placed the front of the list For example: [1, 2, 3, 4, 5], numMoves =
	 * 2 -> [4, 5, 1, 2 ,3] For example: [1, 2, 3, 4, 5], numMoves = 4 -> [2, 3, 4,
	 * 5, 1] For example: [1, 2, 3, 4, 5], numMoves = 7 -> [4, 5, 1, 2 ,3]
	 * 
	 * @param numMoves the number of times to move each node.
	 * @precondition numMoves >= 0
	 * @postcondition iterator position unchanged (i.e. still referencing the same
	 *                node in the list, regardless of new location of Node)
	 * @throws IllegalArgumentException when numMoves < 0
	 */
	public void spinList(int numMoves) throws IllegalArgumentException {
		if (numMoves < 0) {
			throw new IllegalArgumentException("spinList: numMoves cannot be negative");
		}

		if (numMoves == 0 || length == 0 || numMoves % length == 0) {
			return;
		}

		int effMoves = numMoves % length;
		Node current = last;

		for (int i = 1; i < effMoves; i++) {
			current = current.prev;
		}

		Node newFirst = current;
		Node newLast = current.prev;

		last.next = first;
		first.prev = last;
		first = newFirst;
		last = newLast;
		last.next = null;
		first.prev = null;
	}

	/**
	 * Splices together two LinkedLists to create a third List which contains
	 * alternating values from this list and the given parameter For example:
	 * [1,2,3] and [4,5,6] -> [1,4,2,5,3,6] For example: [1, 2, 3, 4] and [5, 6] ->
	 * [1, 5, 2, 6, 3, 4] For example: [1, 2] and [3, 4, 5, 6] -> [1, 3, 2, 4, 5, 6]
	 * 
	 * @param list the second LinkedList
	 * @return a new LinkedList, which is the result of interlocking this and list
	 * @postcondition this and list are unchanged
	 */
	public LinkedList<T> altLists(LinkedList<T> list) {
		if (list == null) {
			return this;
		}

		LinkedList<T> thirdList = new LinkedList<>();

		Node currentThis = this.first;
		Node currentList = list.first;

		while (currentThis != null || currentList != null) {
			if (currentThis != null) {
				thirdList.addLast(currentThis.data);
				currentThis = currentThis.next;
			}

			if (currentList != null) {
				thirdList.addLast(currentList.data);
				currentList = currentList.next;
			}
		}
		return thirdList;
	}
	
    /**
     * @return Returns each element in the LinkedList along with its
     * numerical position from 1 to n, followed by a newline.
     */
    public String numberedListString() {
    	StringBuilder result = new StringBuilder();
    	Node temp = first;
    	int counter = 1;
    	while (temp != null) {
    		result.append(counter).append(". ").append(temp.data).append("\n");
    		temp = temp.next;
    		counter++;
    	}
    	result.append("\n");
        return result.toString();
    }

    /**
     * Searches the LinkedList for a given element's index.
     * @param data the data whose index to locate.
     * @return the index of the data or -1 if the data is not contained
     * in the LinkedList.
     */
    public int findIndex(T data) {
        Node current = first;
        int index = 0;
        while (current != null) {
        	if((data == null && current.data == null) || (data != null && data.equals(current.data))) {
        		return index;
        	}
        	index++;
        	current = current.next;
        }
    	return -1;
    }

    /**
     * Advances the iterator to location within the LinkedList
     * specified by the given index.
     * @param index the index at which to place the iterator.
     * @precondition Must position iterator before calling this method.
     * @throws NullPointerException when the precondition is violated.
     */
    public void advanceIteratorToIndex(int index) throws NullPointerException {
    	if(iterator == null) {
    		throw new NullPointerException("advanceIteratorToIndex: Iterator is not positoined.");
    	}
    	if(index < 0 || index >= length) {
    		throw new NullPointerException("advanceIteratorToIndex: Index is out of bounds.");
    	}
    	iterator = first;
    	for(int i = 0; i < index; i++) {
    		iterator = iterator.next;
    	}
    }
}