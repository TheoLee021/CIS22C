package Week10_Lab19;

/**
 * Heap.java
 * @author Theo Lee
 * CIS 22C, Lab 18
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class Heap<T> {
    private int heapSize;
    private ArrayList<T> heap;
    private Comparator<T> cmp;

    /**Constructors/

    /**
     * Constructor for the Heap class.
     * Sets heapSize to data size, stores parameters, inserts null at heap
     * element 0, and calls buildHeap().
     * @param data an unordered ArrayList, where element 0 is not used.
     * @param comparator that determines organization of heap
     * based on priority.
     */
    public Heap(ArrayList<T> data, Comparator<T> cmp) {
    	this.heap = new ArrayList<>(data);
    	this.heap.add(0, null);
    	this.cmp = cmp;
    	this.heapSize = this.heap.size() - 1;
    	buildHeap();
    }

    /**Mutators*/

    /**
     * Converts an ArrayList into a valid max heap. Called by constructor.
     * Calls helper method heapify.
     */
    public void buildHeap() {
    	for(int i = heapSize / 2; i >= 1; i--) {
    		heapify(i);
    	}
    }

    /**
     * Helper method to buildHeap, remove, and sort.
     * Bubbles an element down to its proper location within the heap.
     * @param index an index in the heap
     */
    private void heapify(int index) {
    	int largest = index;
    	int left = 2 * index;
    	int right = 2 * index + 1;
    	
    	if(left <= heapSize && cmp.compare(heap.get(left), heap.get(largest)) > 0) {
    		largest = left;
    	}
    	
    	if(right <= heapSize && cmp.compare(heap.get(right), heap.get(largest)) > 0) {
    		largest = right;
    	}
    	
    	if(largest != index) {
    		T temp = heap.get(index);
    		heap.set(index, heap.get(largest));
    		heap.set(largest, temp);
    		heapify(largest);
    	}
    }

    /**
     * Inserts the given data into heap.
     * Calls helper method heapIncreaseKey.
     * @param key the data to insert
     */
    public void insert(T key) {
        heap.add(key);
        heapSize++;
        heapIncreaseKey(heapSize, key);
    }

    /**
     * Helper method for insert.
     * Bubbles an element up to its proper location
     * @param index the current index of the key
     * @param key the data
     */
    private void heapIncreaseKey(int index, T key) {
        while (index > 1 && cmp.compare(heap.get(getParent(index)), key) < 0) {
            heap.set(index, heap.get(getParent(index)));
            index = getParent(index);
        }
        heap.set(index, key);
    }

    /**
     * Removes the element at the specified index.
     * Calls helper method heapify
     * @param index the index of the element to remove
     */
    public void remove(int index) {
        if (index < 1 || index > heapSize) {
            throw new NoSuchElementException("Index out of bounds");
        }
        T lastElement = heap.remove(heapSize--);
        if (index <= heapSize) {
            heap.set(index, lastElement);
            heapify(index);
        }
    }

    /**Accessors*/

    /**
     * Returns the heap size (current number of elements)
     * @return the size of the heap
     */
    public int getHeapSize() {
        return heapSize;
    }

    /**
     * Returns the location (index) of the
     * left child of the element stored at index.
     * @param index the current index
     * @return the index of the left child.
     * @precondition 0 < index <= heap_size
     * @throws IndexOutOfBoundsException when precondition is violated.
     */
    public int getLeft(int index) throws IndexOutOfBoundsException {
    	int leftIndex = 2 * index;
    	if(index < 1 || index > heapSize) {
        	throw new IndexOutOfBoundsException ("getLeft: Index is out of the bounds.");
        }
        return leftIndex;
    }

    /**
     * Returns the location (index) of the right child of the element
     * stored at index.
     * @param index the current index
     * @return the index of the right child
     * @precondition 0 < i <= heap_size
     * @throws IndexOutOfBoundsException when precondition is violated.
     */
    public int getRight(int index) throws IndexOutOfBoundsException {
        int rightIndex = 2 * index + 1;
    	if(index < 1 || index > heapSize) {
        	throw new IndexOutOfBoundsException ("getRight: Index is out of the bounds.");
        }
        return rightIndex;
    }

    /**
     * Returns the location (index) of the
     * parent of the element stored at index.
     * @param index the current index
     * @return the index of the parent
     * @precondition 1 < i <= heap_size
     * @throws IndexOutOfBoundsException when precondition is violated.
     */
    public int getParent(int index) throws IndexOutOfBoundsException {
    	if(index <= 1 || index > heapSize) {
        	throw new IndexOutOfBoundsException ("getParent: Index is out of the bounds");
        }
    	return index / 2;
    }

    /**
     * Returns the maximum element (highest priority)
     * @return the max value
     */
    public T getMax() {
    	return heap.get(1);
    }

    /**
     * Returns the element at a specific index.
     * @param index an index in the heap.
     * @return the data at the index.
     * @precondition 0 < i <= heap_size
     * @throws IndexOutOfBoundsException when precondition is violated.
     */
    public T getElement(int index) throws IndexOutOfBoundsException {
    	if(index < 1 || index > heapSize) {
    		throw new IndexOutOfBoundsException("getElement: Index is out of the bounds");
    	}
    	return heap.get(index);
    }

    /**Additional Operations*/

    /**
     * Creates a String of all elements in the heap, separated by ", ".
     * @return a String of all elements in the heap, separated by ", ".
     */
    @Override
    public String toString() {
       StringBuilder result = new StringBuilder();
       for(int i = 1; i < heap.size(); i++) {
    	   result.append(heap.get(i));
    	   if(i < heap.size() - 1) {
    		   result.append(", ");
    	   }
       }
       return result.toString();
    }

    /**
     * Uses the heap sort algorithm to sort the heap into ascending order.
     * Calls helper method heapify.
     * @return an ArrayList of sorted elements
     * @postcondition heap remains a valid heap
     */
    public ArrayList<T> sort() {
        ArrayList<T> originalHeap = new ArrayList<>(heap);
        int originalHeapSize = heapSize;
        ArrayList<T> sorted = new ArrayList<>();
        
        for (int i = heapSize; i > 1; i--) {
            T temp = heap.get(1);
            heap.set(1, heap.get(i));
            heap.set(i, temp);

            heapSize--;

            heapify(1);
        }

        for (int i = 1; i <= originalHeapSize; i++) {
            sorted.add(heap.get(i));
        }

        heap = originalHeap;
        heapSize = originalHeapSize;

        return sorted;
    }
}
