package collection;

import nl.saxion.cds.collection.EmptyCollectionException;
import nl.saxion.cds.collection.ValueNotFoundException;
import nl.saxion.cds.datastructures.MyLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class TestMyLinkedList {
    private MyLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new MyLinkedList<>();
    }

    @Test
    void GivenEmptyList_WhenEmpty_ThenReturnsTrue() {
        assertTrue(list.isEmpty());
    }

    @Test
    void GivenNonEmptyList_WhenCheckingIfEmpty_ThenReturnsFalse() {
        list.addFirst(1);
        assertFalse(list.isEmpty());
    }

    @Test
    void GivenEmptyList_WhenCheckingSize_ThenReturns0() {
        assertEquals(0, list.size());
    }

    @Test
    void GivenNonEmptyList_WhenCheckingSize_ThenReturnsRightSize() {
        list.addFirst(1);
        assertEquals(1, list.size());
    }

    @Test
    void GivenNonEmptyList_WhenClearing_ThenIsEmpty() {
        list.addFirst(1);
        list.clear();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void GivenEmptyList_WhenAddingFirst_ThenHeadAndTailPointToSameNode() {
        list.addFirst(1);
        assertEquals(1, list.getFirst());
        assertEquals(1, list.getLast());
    }

    @Test
    void GivenNonEmptyList_WhenAddingFirst_ThenHeadShouldUpdate() {
        list.addFirst(1);
        list.addFirst(2);
        assertEquals(2, list.getFirst());
        assertEquals(1, list.getLast());
    }

    @Test
    void GivenEmptyList_WhenAddingLast_ThenHeadAndTailPointToSameNode() {
        list.addLast(1);
        assertEquals(1, list.getFirst());
        assertEquals(1, list.getLast());
    }

    @Test
    void GivenNonEmptyList_WhenAddingLast_ThenTailShouldUpdate() {
        list.addFirst(1);
        list.addLast(2);
        assertEquals(2, list.getLast());
        assertEquals(1, list.getFirst());
    }

    @Test
    void GivenNonEmptyList_WhenAddingAtMiddle_ThenElementShouldBePlacedInMiddle() {
        list.addFirst(1);
        list.addLast(3);
        list.addAt(1, 2);
        assertEquals(2, list.get(1));
    }

    @Test
    void GivenEmptyList_WhenAddAtInvalidIndex_ThenThrowException() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.addAt(1, 10));
        assertThrows(IndexOutOfBoundsException.class, () -> list.addAt(-1, 10));
    }

    @Test
    void GivenEmptyList_WhenAddingWithIndex0_ThenIsAddedCorrectly() {
        list.addAt(0, 1);
        assertEquals(1, list.get(0));
    }

    @Test
    void GivenNonEmptyList_WhenAddingWithIndexSameAsSize_ThenIsAddedAsTail() {
        list.addFirst(1);
        list.addAt(1, 2);
        assertEquals(2, list.getLast());
    }

    @Test
    void GivenNonEmptyList_WhenAddingAtValidIndex_ThenIsAddedCorrectly() {
        list.addAt(0, 1);
        list.addAt(1, 2);
        list.addAt(2, 3);
        list.addAt(2, 3);
        list.addAt(2, 3);
        assertEquals(3, list.get(2));
        assertEquals(3, list.get(3));
    }

    @Test
    void GivenNonEmptyList_WhenSettingValueAtInvalidIndex_ThenThrowsException() {
        list.addFirst(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, 1));
    }

    @Test
    void GivenEmptyList_WhenSettingValueAtIndex0_ThenThrowsException() {
        assertThrows(EmptyCollectionException.class, () -> list.set(1, 1));
    }

    @Test
    void GivenNonEmptyList_WhenSettingValue_ThenValueIsSetCorrect() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        list.addLast(6);
        assertEquals(3, list.get(2));

        list.set(2, 5);
        assertEquals(5, list.get(2));
    }

    @Test
    void GivenNonEmptyList_WhenRemoveFirst_ThenHeadShouldUpdate() {
        list.addLast(1);
        list.addLast(2);
        assertEquals(1, list.removeFirst());
        assertEquals(2, list.getFirst());
    }

    @Test
    void GivenEmptyList_WhenRemoveFirst_ThenThrowException() {
        assertThrows(EmptyCollectionException.class, () -> list.removeFirst());
    }

    @Test
    void GivenListWith1Element_WhenRemoveFirst_ThenListShouldBeEmpty() {
        list.addFirst(1);
        list.removeFirst();
        assertTrue(list.isEmpty());
    }

    @Test
    void GivenEmptyList_WhenRemoveLast_ThenThrowException() {
        assertThrows(EmptyCollectionException.class, () -> list.removeLast());
    }

    @Test
    void GivenListWith1Element_WhenRemoveLast_ThenListShouldBeEmpty() {
        list.addFirst(1);
        list.removeLast();
        assertTrue(list.isEmpty());
    }

    @Test
    void GivenNonEmptyList_WhenRemoveLast_ThenHeadShouldUpdate() {
        list.addLast(1);
        list.addLast(2);
        assertEquals(2, list.removeLast());
        assertEquals(1, list.getFirst());
    }

    @Test
    void GivenNonEmptyList_WhenRemoveAtInvalidIndex_ThenThrowException() {
        list.addFirst(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAt(50));
        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAt(-1));
    }

    @Test
    void GivenNonEmptyList_WhenRemoveAtIndex0_ThenReturnFirstAndLast() {
        list.addFirst(1);
        assertEquals(1, list.getFirst());
        assertTrue(list.removeAt(0) == 1);
    }

    @Test
    void GivenNonEmptyList_WhenRemoveAtValidIndex_ThenElementShouldBeRemoved() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        assertEquals(2, list.removeAt(1));
        assertEquals(3, list.get(1));
    }

    @Test
    void GivenNonEmptyList_WhenRemovingLastIndex_ThenLastElementShouldBeRemoved() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        assertEquals(3, list.removeAt(2));
        assertEquals(2, list.get(1));
    }

    @Test
    void GivenEmptyList_WhenRemovingAt_ThenThrowsException() {
        assertThrows(EmptyCollectionException.class, () -> list.removeAt(0));
    }

    @Test
    void GivenNonEmptyList_WhenRemovingData_ThenRemovesRightIndex() {
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);
        assertEquals(3, list.getLast());
        assertEquals(1, list.getFirst());

        list.remove(3);
        assertEquals(2, list.getLast());

        list.remove(1);
        assertEquals(2, list.getFirst());
        assertEquals(2, list.getLast());
    }

    @Test
    void GivenNonEmptyList_WhenRemovingNonExistentData_ThenThrowsException() {
        list.addFirst(1);
        assertThrows(ValueNotFoundException.class, () -> list.remove(2));
    }

    @Test
    void GivenNonEmptyList_WhenRemoving_ThenReturnsRightsSize() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        list.addLast(6);
        assertEquals(6, list.size());

        list.remove(4);
        assertEquals(5, list.size());
    }

    @Test
    void GivenEmptyList_WhenRemoving_ThenThrowsException() {
        assertThrows(EmptyCollectionException.class, () -> list.remove(0));
    }

    @Test
    void GivenEmptyList_WhenGetFirst_ThrowsException() {
        assertThrows(EmptyCollectionException.class, () -> list.getFirst());
    }

    @Test
    void GivenEmptyList_WhenGetLast_ThrowsException() {
        assertThrows(EmptyCollectionException.class, () -> list.getLast());
    }

    @Test
    void GivenNonEmptyList_WhenGetAtInvalidIndex_ThrowsException() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
    }

    @Test
    void GivenEmptyList_WhenGet_ThenThrowsException() {
        assertThrows(EmptyCollectionException.class, () -> list.get(0));
    }

    @Test
    void GivenEmptyList_WhenCheckingForData_ThrowsException() {
        assertThrows(EmptyCollectionException.class, () -> list.contains(1));
    }

    @Test
    void GivenNonEmptyList_WhenCheckingForNonExistentData_ThenReturnsFalse() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        assertFalse(list.contains(6));
    }

    @Test
    void GivenNonEmptyList_WhenCheckingForExistentData_ThenReturnsTrue() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        assertTrue(list.contains(4));
    }

    @Test
    void GivenEmptyList_WhenCheckingForDataWithIndex_ThrowsException() {
        assertThrows(EmptyCollectionException.class, () -> list.indexOf(1));
    }

    @Test
    void GivenNonEmptyList_WhenCheckingForNonExistentData_ThenReturnsMinusOne() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        assertEquals(-1, list.indexOf(6));
    }

    @Test
    void GivenNonEmptyList_WhenCheckingForExistentData_ThenReturnsRightIndex() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        assertEquals(2, list.indexOf(3));
    }

    @Test
    void GivenEmptyList_WhenIteratingList_ThrowsException() {
        Iterator<Integer> iterator = list.iterator();
        assertFalse(iterator.hasNext());

        assertThrows(EmptyCollectionException.class, iterator::next);
    }

    @Test
    void GivenNonEmptyList_WhenIterating_ThenReturnsNext() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        Iterator<Integer> iterator = list.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(10, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(20, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(30, iterator.next());

        assertFalse(iterator.hasNext());
        assertThrows(EmptyCollectionException.class, iterator::next);
    }

    @Test
    void GivenNonEmptyList_WhenMakingGraphViz_ThenGivesRightOutput() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);

        String graphVizOutput = list.graphViz("TestGraph");

        //Expected GraphViz output
        String expectedOutput = "digraph \"TestGraph\" {\n" +
                "  node [shape=plaintext];\n" +
                "  {rank=same\n" +
                "    e [label=\"null\" shape=circle];\n" +
                "    node0 [label=<<table border=\"0\" cellspacing=\"0\" cellborder=\"1\"><tr>\n" +
                "      <td port=\"ref1\" width=\"28\" height=\"36\" fixedsize=\"true\"></td>\n" +
                "      <td port=\"data\" width=\"28\" height=\"36\" fixedsize=\"true\">1</td>\n" +
                "      <td port=\"ref2\" width=\"28\" height=\"36\" fixedsize=\"true\"></td>\n" +
                "    </tr></table>>];\n" +
                "    node1 [label=<<table border=\"0\" cellspacing=\"0\" cellborder=\"1\"><tr>\n" +
                "      <td port=\"ref1\" width=\"28\" height=\"36\" fixedsize=\"true\"></td>\n" +
                "      <td port=\"data\" width=\"28\" height=\"36\" fixedsize=\"true\">2</td>\n" +
                "      <td port=\"ref2\" width=\"28\" height=\"36\" fixedsize=\"true\"></td>\n" +
                "    </tr></table>>];\n" +
                "    node2 [label=<<table border=\"0\" cellspacing=\"0\" cellborder=\"1\"><tr>\n" +
                "      <td port=\"ref1\" width=\"28\" height=\"36\" fixedsize=\"true\"></td>\n" +
                "      <td port=\"data\" width=\"28\" height=\"36\" fixedsize=\"true\">3</td>\n" +
                "      <td port=\"ref2\" width=\"28\" height=\"36\" fixedsize=\"true\"></td>\n" +
                "    </tr></table>>];\n" +
                "    node3 [label=<<table border=\"0\" cellspacing=\"0\" cellborder=\"1\"><tr>\n" +
                "      <td port=\"ref1\" width=\"28\" height=\"36\" fixedsize=\"true\"></td>\n" +
                "      <td port=\"data\" width=\"28\" height=\"36\" fixedsize=\"true\">4</td>\n" +
                "      <td port=\"ref2\" width=\"28\" height=\"36\" fixedsize=\"true\"></td>\n" +
                "    </tr></table>>];\n" +
                "    node4 [label=<<table border=\"0\" cellspacing=\"0\" cellborder=\"1\"><tr>\n" +
                "      <td port=\"ref1\" width=\"28\" height=\"36\" fixedsize=\"true\"></td>\n" +
                "      <td port=\"data\" width=\"28\" height=\"36\" fixedsize=\"true\">5</td>\n" +
                "      <td port=\"ref2\" width=\"28\" height=\"36\" fixedsize=\"true\"></td>\n" +
                "    </tr></table>>];\n" +
                "    d [label=\"null\" shape=circle];\n" +
                "  }\n" +
                "  e -> node0:ref1:c [arrowhead=dot, arrowtail=vee, dir=both, headclip=false];\n" +
                "  node0:ref2:c -> node1:data:n [arrowhead=vee, arrowtail=dot, dir=both, tailclip=false];\n" +
                "  node1:ref2:c -> node2:data:n [arrowhead=vee, arrowtail=dot, dir=both, tailclip=false];\n" +
                "  node2:ref2:c -> node3:data:n [arrowhead=vee, arrowtail=dot, dir=both, tailclip=false];\n" +
                "  node3:ref2:c -> node4:data:n [arrowhead=vee, arrowtail=dot, dir=both, tailclip=false];\n" +
                "  node4:ref2:c -> d:w [arrowhead=vee, arrowtail=dot, dir=both, tailclip=false];\n" +
                "  node4:ref1:c -> node3:data:s [arrowhead=vee, arrowtail=dot, dir=both, tailclip=false];\n" +
                "  node3:ref1:c -> node2:data:s [arrowhead=vee, arrowtail=dot, dir=both, tailclip=false];\n" +
                "  node2:ref1:c -> node1:data:s [arrowhead=vee, arrowtail=dot, dir=both, tailclip=false];\n" +
                "  node1:ref1:c -> node0:data:s [arrowhead=vee, arrowtail=dot, dir=both, tailclip=false];\n" +
                "}\n";

        assertEquals(expectedOutput, graphVizOutput);
    }


    @Test
    void GivenEmptyList_WhenDrawingGraphViz_ThenThrowsException() {
        assertThrows(EmptyCollectionException.class, () -> list.graphViz());
    }

}
