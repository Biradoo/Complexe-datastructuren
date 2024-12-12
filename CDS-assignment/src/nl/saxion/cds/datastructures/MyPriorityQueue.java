package nl.saxion.cds.datastructures;

import nl.saxion.cds.collection.*;

import java.util.Comparator;

/**
 * Een implementatie van de SaxQueue interface die gebruik maakt van MyMinHeap om een priority queue voor te stellen.
 *
 * @param <V> Het type object dat in de queue zit. Moet Comparable zijn voor natuurlijke ordening.
 */
public class MyPriorityQueue<V extends Comparable<V>> implements SaxQueue<V> {
    private MyMinHeap<V> heap;
    private Comparator<V> comparator;


    public MyPriorityQueue(Comparator<V> comparator) {
        this.heap = new MyMinHeap<>();
        this.comparator = comparator;
    }

    public MyPriorityQueue() {
        this.heap = new MyMinHeap<>();

    }

    @Override
    public void enqueue(V value) {
        heap.enqueue(value); //Add element to heap
    }

    @Override
    public V dequeue() throws EmptyCollectionException {
        if (heap.isEmpty()) {
            throw new EmptyCollectionException("The queue is empty");
        }
        return heap.dequeue(); //Retrieve lowest element in queue
    }

    @Override
    public V peek() throws EmptyCollectionException {
        if (heap.isEmpty()) {
            throw new EmptyCollectionException("The queue is empty");
        }
        return heap.peek(); //Return lowest element in queue
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public String graphViz(String name) {
        return null;
    }


}