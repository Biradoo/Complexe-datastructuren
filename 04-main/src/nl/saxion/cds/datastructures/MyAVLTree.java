package nl.saxion.cds.datastructures;

import nl.saxion.cds.collection.DuplicateKeyException;
import nl.saxion.cds.collection.EmptyCollectionException;
import nl.saxion.cds.collection.KeyNotFoundException;
import nl.saxion.cds.collection.SaxBinaryTree;

public class MyAVLTree<K extends Comparable<K>, V> implements SaxBinaryTree<K, V> {
    private Node root;
    private int size;

    private class Node {
        K key;
        V value;
        Node left, right;
        int height;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }

    public MyAVLTree() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String graphViz(String name) {
        return null;
    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    private Node getNode(Node node, K key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            return getNode(node.left, key);
        } else if (cmp > 0) {
            return getNode(node.right, key);
        } else {
            return node;
        }
    }
    @Override
    public void add(K key, V value) throws DuplicateKeyException {
        if (contains(key)) throw new DuplicateKeyException("Duplicate key: " + key);
        root = insert(root, key, value);
        size++;
    }

    private Node insert(Node node, K key, V value) {
        if (node == null) return new Node(key, value);

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = insert(node.left, key, value);
        } else if (cmp > 0) {
            node.right = insert(node.right, key, value);
        }

        //Update the height of this ancestor node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        //Balance the node if needed
        return balance(node);
    }

    @Override
    public V remove(K key) throws KeyNotFoundException {
        if (isEmpty()) throw new EmptyCollectionException("Cannot remove from an empty tree.");
        if (!contains(key)) throw new KeyNotFoundException("Key not found: " + key);

        NodeWrapper removedNode = new NodeWrapper();
        root = remove(root, key, removedNode);
        size--;
        return removedNode.node.value;
    }

    private Node remove(Node node, K key, NodeWrapper removedNode) {
        if (isEmpty()) throw new EmptyCollectionException("Cannot remove from an empty tree.");

        if (node == null) return null;

        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = remove(node.left, key, removedNode);
        } else if (compare > 0) {
            node.right = remove(node.right, key, removedNode);
        } else {
            removedNode.node = node;
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                Node minNode = getMin(node.right);
                node.key = minNode.key;
                node.value = minNode.value;
                node.right = remove(node.right, minNode.key, new NodeWrapper());
            }
        }

        if (node == null) return null;

        //Update height and balance
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node);
    }

    private Node getMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private Node balance(Node node) {
        int balanceFactor = getBalanceFactor(node);

        //Left heavy
        if (balanceFactor > 1) {
            if (getBalanceFactor(node.left) < 0) node.left = rotateLeft(node.left); //Left-Right case
            return rotateRight(node); //Left-Left case
        }

        //Right heavy
        if (balanceFactor < -1) {
            if (getBalanceFactor(node.right) > 0) node.right = rotateRight(node.right); //Right-Left case
            return rotateLeft(node); //Right-Right case
        }

        return node; //No imbalance
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    public int getBalanceFactor(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));

        return y;
    }

    @Override
    public MyLinkedList<K> getKeys() {
        MyLinkedList<K> keys = new MyLinkedList<>();
        inOrderTraversal(root, keys);
        return keys;
    }

    private void inOrderTraversal(Node node, MyLinkedList<K> keys) {
        if (node != null) {
            inOrderTraversal(node.left, keys);
            keys.addLast(node.key);
            inOrderTraversal(node.right, keys);
        }
    }

    //Wrapper class to hold a reference to the removed node during recursive deletion in the AVL tree.
    private class NodeWrapper {
        Node node;
    }
}