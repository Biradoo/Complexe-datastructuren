package nl.saxion.cds.datastructures;

import nl.saxion.cds.collection.EmptyCollectionException;
import nl.saxion.cds.collection.SaxStack;
import nl.saxion.cds.collection.SaxList;

public class MyStack<V> implements SaxStack<V> {
    private SaxList<V> stack;

    public MyStack(SaxList<V> stack) {
        this.stack = stack;
    }

    public MyStack() {
        this.stack = new MyArrayList<>();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public void push(V value) {
        stack.addLast(value);
    }

    @Override
    public V pop() throws EmptyCollectionException {
        if (stack.isEmpty()) {
            throw new EmptyCollectionException();
        }
        return stack.removeLast();
    }

    @Override
    public V peek() throws EmptyCollectionException {
        if (stack.isEmpty()) {
            throw new EmptyCollectionException();
        }
        return stack.get(stack.size() - 1);
    }

    @Override
    public String graphViz(String name) {
        return null;
    }
}