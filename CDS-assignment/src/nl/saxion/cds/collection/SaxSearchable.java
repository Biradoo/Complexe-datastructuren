package nl.saxion.cds.collection;

import java.util.Comparator;

public interface SaxSearchable<V> {
    int NOT_FOUND = -1;

    /**
     * Search a collection in a linear way, using element.equals() to find an element.
     *
     * @param element element to search for
     * @return index in the collection or NOT_FOUND if not found
     */
    int linearSearch(V element);

    /**
     * Search a collection in a binary way, using element.compareTo() to find an element.
     * The collection MUST BE SORTED IN ASCENDING ORDER, according to the given comparator.
     *
     * @param comparator method to compare two V objects
     * @param element    element to search for
     * @return index in the collection or NOT_FOUND if not found
     */
    int binarySearch(Comparator<V> comparator, V element);
}
