package collection;

import nl.saxion.cds.collection.DuplicateKeyException;
import nl.saxion.cds.collection.EmptyCollectionException;
import nl.saxion.cds.collection.KeyNotFoundException;
import nl.saxion.cds.datastructures.MyAVLTree;
import nl.saxion.cds.datastructures.MyLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestMyAVLTree {
    private MyAVLTree<Integer, String> avlTree;

    @BeforeEach
    void setUp() {
        avlTree = new MyAVLTree<>();
    }

    @Test
    void GivenEmptyTree_WhenCheckingIfEmpty_ThenReturnsTrue() {
        assertTrue(avlTree.isEmpty());
    }

    @Test
    void GivenEmptyTree_WhenGettingSize_ThenReturnsZero() {
        assertEquals(0, avlTree.size());
    }

    @Test
    void GivenEmptyTree_WhenAddingElement_ThenSizeIncreases() throws DuplicateKeyException {
        avlTree.add(10, "ten");
        assertEquals(1, avlTree.size());
        assertFalse(avlTree.isEmpty());
    }

    @Test
    void GivenNonEmptyTree_WhenAddingDuplicateKey_ThenThrowsException() throws DuplicateKeyException {
        avlTree.add(10, "ten");
        assertThrows(DuplicateKeyException.class, () -> avlTree.add(10, "duplicate"));
    }

    @Test
    void GivenNonEmptyTree_WhenRetrievingExistingKey_ThenReturnsCorrectValue() throws DuplicateKeyException {
        avlTree.add(10, "ten");
        assertEquals("ten", avlTree.get(10));
    }

    @Test
    void GivenNonEmptyTree_WhenRetrievingNonExistentKey_ThenReturnsNull() throws DuplicateKeyException {
        avlTree.add(10, "ten");
        assertNull(avlTree.get(20));
    }

    @Test
    void GivenEmptyTree_WhenRemovingElement_ThenThrowsException() {
        assertThrows(EmptyCollectionException.class, () -> avlTree.remove(10));
    }

    @Test
    void GivenNonEmptyTree_WhenRemovingNonExistentKey_ThenThrowsException() throws DuplicateKeyException {
        avlTree.add(10, "ten");
        assertThrows(KeyNotFoundException.class, () -> avlTree.remove(20));
    }

    @Test
    void GivenNonEmptyTree_WhenRemovingExistingKey_ThenSizeDecreases() throws DuplicateKeyException, KeyNotFoundException {
        avlTree.add(10, "ten");
        avlTree.add(20, "twenty");
        assertEquals("ten", avlTree.remove(10));
        assertEquals(1, avlTree.size());
        assertNull(avlTree.get(10));
    }

    @Test
    void GivenNonEmptyTree_WhenCheckingIfContainsExistingKey_ThenReturnsTrue() throws DuplicateKeyException {
        avlTree.add(10, "ten");
        assertTrue(avlTree.contains(10));
    }

    @Test
    void GivenNonEmptyTree_WhenCheckingIfContainsNonExistentKey_ThenReturnsFalse() throws DuplicateKeyException {
        avlTree.add(10, "ten");
        assertFalse(avlTree.contains(20));
    }

    @Test
    void GivenEmptyTree_WhenGettingKeys_ThenReturnsEmptyList() {
        MyLinkedList<Integer> keys = avlTree.getKeys();
        assertTrue(keys.isEmpty());
    }

    @Test
    void GivenNonEmptyTree_WhenGettingKeys_ThenReturnsSortedKeys() throws DuplicateKeyException {
        avlTree.add(20, "twenty");
        avlTree.add(10, "ten");
        avlTree.add(30, "thirty");
        MyLinkedList<Integer> keys = avlTree.getKeys();

        assertEquals(3, keys.size());
        assertEquals(10, keys.get(0));
        assertEquals(20, keys.get(1));
        assertEquals(30, keys.get(2));
    }

    @Test
    void GivenBalancedTree_WhenAddingElements_ThenTreeRemainsBalanced() throws DuplicateKeyException {
        avlTree.add(10, "ten");
        avlTree.add(20, "twenty");
        avlTree.add(30, "thirty");

        assertEquals("twenty", avlTree.get(20));
    }

    @Test
    void GivenBalancedTree_WhenRemovingElements_ThenTreeRemainsBalanced() throws DuplicateKeyException, KeyNotFoundException {
        avlTree.add(10, "ten");
        avlTree.add(20, "twenty");
        avlTree.add(30, "thirty");
        avlTree.remove(10);

        assertEquals("twenty", avlTree.get(20));
        assertEquals(2, avlTree.size());
    }

    @Test
    void GivenGraphViz_WhenDrawing_ThenReturnsNull() {
        assertNull(avlTree.graphViz());
    }

    @Test
    void GivenLeftHeavyTree_WhenInsertingElement_ThenRightRotationOccurs() {
        MyAVLTree<Integer, String> tree = new MyAVLTree<>();
        tree.add(30, "thirty");
        tree.add(20, "twenty");
        tree.add(10, "ten"); //Adding in descending order causes right rotation

        assertEquals("twenty", tree.get(20)); // New root should be 20 after rotation
        assertEquals("ten", tree.get(10));
        assertEquals("thirty", tree.get(30));
    }

    @Test
    void GivenLeftRightCase_WhenInsertingElement_ThenLeftRightRotationOccurs() {
        MyAVLTree<Integer, String> tree = new MyAVLTree<>();
        tree.add(30, "thirty");
        tree.add(10, "ten");
        tree.add(20, "twenty"); //Left-right case occurs here

        assertEquals("twenty", tree.get(20)); //New root should be 20 after left-right rotation
        assertEquals("ten", tree.get(10));
        assertEquals("thirty", tree.get(30));
    }

    @Test
    void GivenNodeWithTwoChildren_WhenRemovingNode_ThenReplaceWithMinNodeFromRightSubtree() {
        MyAVLTree<Integer, String> tree = new MyAVLTree<>();
        tree.add(20, "twenty");
        tree.add(10, "ten");
        tree.add(30, "thirty");

        tree.remove(20); //Removing node with key 20 should replace it with 30 (min from right subtree)

        assertNull(tree.get(20));
        assertEquals("ten", tree.get(10));
        assertEquals("thirty", tree.get(30));
    }

    @Test
    void GivenTreeWithRightSubtree_WhenRemovingFromRightSubtree_ThenRightSubtreeUpdated() {
        MyAVLTree<Integer, String> tree = new MyAVLTree<>();
        tree.add(10, "ten");
        tree.add(5, "five");
        tree.add(20, "twenty");
        tree.add(30, "thirty");

        tree.remove(20); //Removing 20 from right subtree
        assertNull(tree.get(20));
        assertEquals("ten", tree.get(10));
        assertEquals("five", tree.get(5));
        assertEquals("thirty", tree.get(30));
    }

    @Test
    void GivenEmptyTree_WhenRemoving_ThenThrowsException(){
        assertThrows(EmptyCollectionException.class, () -> avlTree.remove(1));
    }


}
