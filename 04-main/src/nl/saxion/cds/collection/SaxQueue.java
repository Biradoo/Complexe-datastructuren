package nl.saxion.cds.collection;

/**
 * A SaxQueue is a queue which only allows data to add at one end (enqueue) and to remove data
 * from the other end (dequeue): FIFO
 *
 * @param <V> Type of objects to be contained in this collection type.
 */
public interface SaxQueue<V> extends SaxCollection<V> {
    /**
     * Add the value to list.
     *
     * @param value the value to push
     */
    void enqueue(V value);

    /**
     * Remove the value from the list.
     *
     * @return the popped value
     */
    V dequeue() throws EmptyCollectionException;

    /**
     * Return the value of the list, without removing it.
     *
     * @return the value or null if the list is empty
     */
    V peek() throws EmptyCollectionException;
}
