package nl.saxion.cds.collection;

/**
 * A binary tree interface for implementing a map (dictionary) based on a binary search tree,
 * in which a value is connected to a key, used to store and retrieve that value.
 *
 * @param <K> the key, which implements Comparable
 * @param <V> the value to store
 */
public interface SaxBinaryTree<K extends Comparable<K>, V> extends SaxCollection<V> {
    /**
     * Check if the key is part of this map.
     * Uses K.equals() to check for equality.
     *
     * @param key key to search for
     * @return if the key is in this collection
     */
    boolean contains(K key);

    /**
     * Get a value which is mapped to the key.
     *
     * @param key key which is mapped to value to be found
     * @return the value mapped to the key or null if the key is not found
     */
    V get(K key);

    /**
     * Add the value which will be mapped to the key.
     * A duplicate key will throw a DuplicateKeyException.
     *
     * @param key   key which is mapped to value
     * @param value the value to add
     * @throws DuplicateKeyException if the key is already part of the collection
     */
    void add(K key, V value) throws DuplicateKeyException;

    /**
     * Remove the value which is mapped with the key from the collection
     *
     * @param key key which is mapped to value
     * @return the value which is removed from the collection
     * @throws KeyNotFoundException if the key is not part oif the collection
     */
    V remove(K key) throws KeyNotFoundException;

    SaxList<K> getKeys();
}
