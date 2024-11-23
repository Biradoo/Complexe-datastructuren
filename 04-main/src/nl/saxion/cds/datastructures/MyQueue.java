package nl.saxion.cds.datastructures;

import nl.saxion.cds.collection.EmptyCollectionException;
import nl.saxion.cds.collection.SaxQueue;

public class MyQueue<V> implements SaxQueue<V> {
    private final MyLinkedList<V> list = new MyLinkedList<>();

    public MyQueue() {
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public String graphViz(String name) {
        return null;
    }

    @Override
    public void enqueue(V value) {
        list.addLast(value); //Add value to the end of the queue (FIFO)
    }

    @Override
    public V dequeue() throws EmptyCollectionException {
        if (list.isEmpty()) throw new EmptyCollectionException();
        return list.removeLast();
    }

    @Override
    public V peek() throws EmptyCollectionException {
        if (list.isEmpty()) throw new EmptyCollectionException();
        return list.getFirst();
    }
}
