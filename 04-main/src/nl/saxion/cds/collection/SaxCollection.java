package nl.saxion.cds.collection;

public interface SaxCollection<V> {
    /**
     * Determines if the collection has no elements
     *
     * @return if the collection has no elements
     */
    boolean isEmpty();

    /**
     * Determines the number of elements in this collection
     *
     * @return size of this collection
     */
    int size();

    /**
     * Create a String representation of the data in GraphViz (see <a href="https://graphviz.org">GraphViz</a>)
     * format, which you can print-copy-paste on the site see <a href="https://dreampuf.github.io/GraphvizOnline">GraphViz online</a>.
     *
     * @param name name of the produced graph
     * @return a GraphViz string representation of this collection
     */
    String graphViz(String name);

    /**
     * Create a String representation of the data in GraphViz (see <a href="https://graphviz.org">GraphViz</a>)
     * format, which you can print-copy-paste on the site see <a href="https://dreampuf.github.io/GraphvizOnline">GraphViz online</a>.
     *
     * @return a GraphViz string representation of this collection
     */
    default String graphViz() {
        return graphViz(getClass().getSimpleName());
    }
}
