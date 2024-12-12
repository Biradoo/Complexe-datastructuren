package nl.saxion.cds.datastructures;

import nl.saxion.cds.collection.EmptyCollectionException;
import nl.saxion.cds.collection.SaxHeap;

public class MyMinHeap<V extends Comparable<V>> implements SaxHeap<V> {
    private V[] heap;
    private int size;
    private static final int INITIAL_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public MyMinHeap() {
        this.heap = (V[]) new Comparable[INITIAL_CAPACITY];
        this.size = 0;
    }

    public MyMinHeap(V[] input) {
        this.size = input.length;
        this.heap = input;
        buildHeap();  // Efficiently build the heap in O(n) time
    }

    // Enqueue (insert) a new item and maintain the heap property
    @Override
    public void enqueue(V value) {
        ensureCapacity();
        heap[size] = value;
        int i = size;
        size++;

        // Bubble up to maintain the heap property
        while (i > 0 && heap[parent(i)].compareTo(heap[i]) > 0) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    // Dequeue (extract) the minimum element from the heap
    @Override
    public V dequeue() throws EmptyCollectionException {
        if (size == 0) throw new EmptyCollectionException();

        V min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        minHeapify(0);  // Bubble down to maintain the heap property

        return min;
    }

    // Decreases the key of a specific element at index i to a new key value
    public void decreaseKey(int i, V key) {
        if (heap[i].compareTo(key) < 0) throw new IllegalArgumentException("New key is larger than the original key");

        heap[i] = key;
        // Bubble up to maintain the heap property
        while (i > 0 && heap[parent(i)].compareTo(heap[i]) > 0) {
            swap(i, parent(i));
            i = parent(i);
        }
    }
    @Override
    public V peek() throws EmptyCollectionException {
        if (size == 0) throw new EmptyCollectionException();
        return heap[0];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String graphViz(String name) {
        return null;
    }

    // Efficiently builds the heap from the initial array in O(n) time
    private void buildHeap() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            minHeapify(i);
        }
    }

    //Ensures the min-heap property for the subtree rooted at index i
    private void minHeapify(int i) {
        int left = left(i);
        int right = right(i);
        int smallest = i;

        if (left < size && heap[left].compareTo(heap[smallest]) < 0) {
            smallest = left;
        }
        if (right < size && heap[right].compareTo(heap[smallest]) < 0) {
            smallest = right;
        }
        if (smallest != i) {
            swap(i, smallest);
            minHeapify(smallest);
        }
    }

    private void ensureCapacity() {
        if (size == heap.length) {
            V[] newHeap = (V[]) new Comparable[heap.length * 2];
            System.arraycopy(heap, 0, newHeap, 0, heap.length);
            heap = newHeap;
        }
    }

    //Helper method to swap two elements in the heap array
    private void swap(int i, int j) {
        V temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    //Returns the index of the parent node
    private int parent(int i) {
        return (i - 1) / 2;
    }

    //Returns the index of the left child node
    private int left(int i) {
        return 2 * i + 1;
    }

    //Returns the index of the right child node
    private int right(int i) {
        return 2 * i + 2;
    }
}