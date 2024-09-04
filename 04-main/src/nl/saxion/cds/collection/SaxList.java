package nl.saxion.cds.collection;

/**
 * A SaxList is a list, a linear data structure, which allows data to be accessed at every position.
 *
 * @param <V> Type of objects to be contained.
 */
public interface SaxList<V> extends SaxCollection<V>, Iterable<V> {
    /**
     * Check if a given value is in the collection
     * Uses V.equals() to check for equality.
     *
     * @param value the value to search for
     * @return if the value is in the collection
     */
    boolean contains(V value);

    /**
     * Get the value in the list at the specific index.
     *
     * @param index the index of the element to retrieve
     * @return value at the given index
     * @throws IndexOutOfBoundsException invalid index
     */
    V get(int index) throws IndexOutOfBoundsException;

    /**
     * Add the given value at the end of the list
     *
     * @param value the value to add
     */
    void addLast(V value);

    /**
     * Add the given value at the beginning of the list
     *
     * @param value the value to add
     */
    void addFirst(V value);

    /**
     * Add (insert) the given value at the "index"th position in the list
     * Throws an IndexOutOfBoundsException if the index < 0 or >= size
     *
     * @param index index where the value is to be added
     * @param value the value to add
     * @throws IndexOutOfBoundsException invalid index
     */
    void addAt(int index, V value) throws IndexOutOfBoundsException;

    /**
     * Sets the given value at the "index"th position in the list
     * Throws an IndexOutOfBoundsException if the index < 0 or >= size
     *
     * @param index index where the value is to be set
     * @param value the value to set
     * @throws IndexOutOfBoundsException invalid index
     */
    void set(int index, V value) throws IndexOutOfBoundsException;

    /**
     * Removes the last element of the list.
     * Throws an EmptyCollectionException if the list is empty
     *
     * @return the removed value
     * @throws EmptyCollectionException nothing to remove
     */
    V removeLast() throws EmptyCollectionException;

    /**
     * Removes the first element of the list.
     * Throws an EmptyCollectionException if the list is empty
     *
     * @return the removed value
     * @throws EmptyCollectionException nothing to remove
     */
    V removeFirst() throws EmptyCollectionException;

    /**
     * Removes the element at position index of the list.
     * Throws an EmptyCollectionException if the list is empty
     *
     * @param index index where the value is to be removed
     * @return the removed value
     * @throws IndexOutOfBoundsException invalid index
     */
    V removeAt(int index) throws IndexOutOfBoundsException;

    /**
     * Removes the element on the "index"th position in the list.
     * Throws an ValueNotFoundException if the value is not in the list
     *
     * @param value value to remove
     * @throws ValueNotFoundException value not found
     */
    void remove(V value) throws ValueNotFoundException;
}
