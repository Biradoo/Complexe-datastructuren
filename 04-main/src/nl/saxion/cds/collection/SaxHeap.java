package nl.saxion.cds.collection;

/**
 * A heap where each parent node is either bigger (max-heap) or smaller (min-heap) than all its
 * children.
 * @param <V> objects to store in the heap, uses V.compareTo() to compare for equality.
 */
public interface SaxHeap<V extends Comparable<V>> extends SaxQueue<V> {
}
