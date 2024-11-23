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
        stack.addLast(value); // Adds to the end of the list, treating it as the "top" of the stack
    }

    @Override
    public V pop() throws EmptyCollectionException {
        if (stack.isEmpty()) {
            throw new EmptyCollectionException();
        }
        return stack.removeLast(); // Removes the last item, as it represents the "top"
    }

    @Override
    public V peek() throws EmptyCollectionException {
        if (stack.isEmpty()) {
            throw new EmptyCollectionException();
        }
        return stack.get(stack.size() - 1); // Returns the last item without removing it
    }

    @Override
    public String graphViz(String name) {
        return stack.graphViz(name); // Assuming your list implementation supports GraphViz output
    }
}