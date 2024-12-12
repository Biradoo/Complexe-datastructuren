package nl.saxion.cds.collection;

import java.util.Comparator;

public interface SaxSortable<V> {
    /**
     * Determine if the collection is sorted in ascending order or not.
     * @param comparator sorting comparator
     * @return if it is sorted in ascending order
     */
    boolean isSorted(Comparator<V> comparator);

    /**
     * Sort the collection in place in ascending order, using a simple O(N2) sorting algorithm.
     *
     * @param comparator sorting comparator
     */
    void simpleSort(Comparator<V> comparator);

    /**
     * Sort the collection in place in ascending order, using the QuickSort sorting algorithm.
     *
     * @param comparator sorting comparator
     */
    void quickSort(Comparator<V> comparator);
}
