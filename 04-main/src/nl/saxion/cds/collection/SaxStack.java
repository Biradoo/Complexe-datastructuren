package nl.saxion.cds.collection;

/**
 * A SaxStack is a collection which acts as a stack; pushing values on top and popping from that
 * same top; LIFO
 *
 * @param <V> Type of objects to be contained in this stack collection.
 */
public interface SaxStack<V> extends SaxCollection<V> {
    /**
     * Add the value to the stack (on top).
     *
     * @param value the value to push
     */
    void push(V value);

    /**
     * Remove the value from the stack (from top).
     *
     * @return the popped value
     */
    V pop() throws EmptyCollectionException;

    /**
     * Return the value on top of the stack, without removing it.
     *
     * @return the value or null if the stack is empty
     */
    V peek() throws EmptyCollectionException;
}
