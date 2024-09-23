package nl.saxion.cds.datastructures;

import nl.saxion.cds.collection.EmptyCollectionException;
import nl.saxion.cds.collection.SaxList;
import nl.saxion.cds.collection.SaxStack;

public class MyStack<V extends Comparable<V>> implements SaxStack {
    private final SaxList<V> stack;

    public MyStack(SaxList<V> stack) {
        this.stack = stack;
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
    public String graphViz(String name) {
        return stack.graphViz(name);
    }

    @Override
    public String graphViz() {
        return stack.graphViz();
    }

    @Override
    public void push(Comparable value) {
        stack.addLast((V) value);
    }

    @Override
    public Comparable pop() throws EmptyCollectionException {
        if (stack.isEmpty()) {
            throw new EmptyCollectionException();
        }
        return stack.removeLast();
    }

    @Override
    public Comparable peek() throws EmptyCollectionException {
        if (stack.isEmpty()) {
            throw new EmptyCollectionException();
        }
        return stack.get(stack.size() - 1);
    }
}
