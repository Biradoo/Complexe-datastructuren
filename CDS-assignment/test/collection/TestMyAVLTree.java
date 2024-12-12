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
    void GivenTree_WhenLeftRightCaseOccurs_ThenTreeRotatesCorrectly() throws DuplicateKeyException {
        avlTree.add(30, "thirty");
        avlTree.add(10, "ten");
        avlTree.add(20, "twenty"); // Left-Right case

        assertEquals("twenty", avlTree.get(20)); // Root should now be 20
        assertTrue(avlTree.isBalanced());
    }

    @Test
    void GivenTree_WhenRightLeftCaseOccurs_ThenTreeRotatesCorrectly() throws DuplicateKeyException {
        avlTree.add(10, "ten");
        avlTree.add(30, "thirty");
        avlTree.add(20, "twenty"); // Right-Left case

        assertEquals("twenty", avlTree.get(20)); // Root should now be 20
        assertTrue(avlTree.isBalanced());
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

    @Test
    void GivenTree_WhenAddingAndClearing_ThenReturnsCorrectState() {
        assertTrue(avlTree.isEmpty());
        assertEquals(0, avlTree.size());

        avlTree.add(10, "ten");
        avlTree.add(20, "twenty");
        avlTree.add(30, "thirty");
        avlTree.add(3, "three");

        assertFalse(avlTree.isEmpty());
        assertEquals(4, avlTree.size());

        avlTree.remove(10);
        assertEquals(3, avlTree.size());
        avlTree.remove(20);
        assertEquals(2, avlTree.size());
        avlTree.remove(30);
        assertEquals(1, avlTree.size());
        avlTree.remove(3);
        assertEquals(0, avlTree.size());
        assertTrue(avlTree.isEmpty());
    }

    @Test
    void GivenTree_WhenCheckingBalance_ThenReturnsCorrectResults() throws DuplicateKeyException {
        MyAVLTree<Integer, String> avlTree = new MyAVLTree<>();

        // Insert values to form a balanced tree
        avlTree.add(10, "ten");
        avlTree.add(20, "twenty");
        avlTree.add(30, "thirty"); // Right rotation
        assertTrue(avlTree.isBalanced());

        // Remove a node and check balance
        avlTree.remove(30);
        assertTrue(avlTree.isBalanced());

        // Insert values to create imbalance and then balance
        avlTree.add(40, "forty");
        avlTree.add(50, "fifty");
        assertTrue(avlTree.isBalanced());
    }

    @Test
    void GivenLargeTree_WhenCheckingBalance_ThenTreeRemainsBalanced() throws DuplicateKeyException {
        for (int i = 1; i <= 1000; i++) {
            avlTree.add(i, "Value" + i);
        }

        assertTrue(avlTree.isBalanced());

        for (int i = 1; i <= 500; i++) {
            avlTree.remove(i);
        }

        assertTrue(avlTree.isBalanced());
    }

    @Test
    void GivenTree_WhenRemovingNodeWithOneChild_ThenTreeRemainsBalanced() throws DuplicateKeyException, KeyNotFoundException {
        avlTree.add(10, "ten");
        avlTree.add(20, "twenty");
        avlTree.add(15, "fifteen");

        assertEquals("twenty", avlTree.remove(20));
        assertFalse(avlTree.contains(20));
        assertTrue(avlTree.contains(15));
        assertTrue(avlTree.isBalanced());
    }

    @Test
    void GivenTree_WhenRemovingNodeWithTwoChildren_ThenTreeRemainsBalanced() throws DuplicateKeyException, KeyNotFoundException {
        avlTree.add(20, "twenty");
        avlTree.add(10, "ten");
        avlTree.add(30, "thirty");
        avlTree.add(25, "twenty-five");
        avlTree.add(40, "forty");

        System.out.println(avlTree.graphViz("TestTree")); //Testline

        avlTree.remove(20);
        assertFalse(avlTree.contains(20));
        assertTrue(avlTree.contains(10));
        assertTrue(avlTree.contains(30));
    }

    @Test
    void GivenTree_WhenCalculatingBalanceFactor_ThenHandlesNullAndNonNullNodes() throws DuplicateKeyException {
        assertEquals(0, avlTree.getBalanceFactor(null));

        avlTree.add(10, "ten"); // Root
        avlTree.add(20, "twenty"); // Right child

        MyAVLTree<Integer, String>.Node rootNode = avlTree.getNode(avlTree.root, 10); // Access root node
        assertNotNull(rootNode);
        assertEquals(-1, avlTree.getBalanceFactor(rootNode));
    }

    @Test
    void GivenAVLTree_WhenGeneratingGraphViz_ThenContainsRightElements() {
        avlTree.add(10, "ten");
        avlTree.add(20, "twenty");
        avlTree.add(5, "five");

        String graphVizOutput = avlTree.graphViz("TestTree");
        assertTrue(graphVizOutput.contains("10"));
        assertTrue(graphVizOutput.contains("20"));
        assertTrue(graphVizOutput.contains("5"));
        assertTrue(graphVizOutput.contains("TestTree"));

        System.out.println(graphVizOutput); //Testline
    }

    @Test
    void GivenAVLTree_WhenAddingRandomValues_ThenReturnsBalancedTreeAndGraphVizProof() {
        avlTree.add(237, "");
        avlTree.add(3532, "");
        avlTree.add(1234, "");
        avlTree.add(12, "");
        avlTree.add(434, "");
        avlTree.add(3234, "");
        avlTree.add(987, "");
        avlTree.add(76, "");
        avlTree.add(54, "");
        avlTree.add(23, "");
        avlTree.add(2, "");
        avlTree.add(11, "");
        avlTree.add(73, "");
        avlTree.add(99, "");
        avlTree.add(35689, "");

        assertTrue(avlTree.isBalanced());
        MyAVLTree<Integer, String>.Node rootNode = avlTree.getNode(avlTree.root, 237); // Access root node
        assertNotNull(rootNode);
        assertEquals(1, avlTree.getBalanceFactor(rootNode));

        System.out.println(avlTree.graphViz("TestTree"));
    }
}
