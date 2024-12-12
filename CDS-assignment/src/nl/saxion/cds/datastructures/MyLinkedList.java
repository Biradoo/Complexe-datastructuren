package nl.saxion.cds.datastructures;

import nl.saxion.cds.collection.EmptyCollectionException;
import nl.saxion.cds.collection.SaxList;
import nl.saxion.cds.collection.ValueNotFoundException;

import java.util.Iterator;

public class MyLinkedList<T> implements SaxList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public MyLinkedList() { //Initially head and tail are null
        this.head = null;
        this.tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clear() { //Clears the list
        head = tail = null;
        size = 0;
    }

    public void addFirst(T data) { //Adds a new node to the front of the list
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = tail = newNode; //While size is 1 head and tail are the value of the created Node
        } else { //newNode will be the head --> changing the pointers
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }
        size++;
    }

    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = tail = newNode; //While size is 1 head and tail are the value of the created Node
        } else { //newNode will be the tail --> changing the pointers
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        size++;
    }

    public void addAt(int index, T data) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index out of bounds");

        if (index == 0) {
            addFirst(data); //If list is empty
        } else if (index == size) {
            addLast(data);
        } else {
            Node<T> newNode = new Node<>(data);
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) { //Traverse list to find node at index - 1;
                current = current.next;
            }
            //Insert newNode in between
            newNode.next = current.next;
            current.next.previous = newNode;
            newNode.previous = current;
            current.next = newNode;
            size++;
        }
    }

    @Override
    public void set(int index, T value) {
        if (isEmpty()) throw new EmptyCollectionException();

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.data = value;
    }

    public T removeFirst() { //Removes and returns first node's data
        if (isEmpty()) throw new EmptyCollectionException();

        T data = head.data; //Store the data of the head node

        if (head == tail) { //If there is only one node
            clear();
        } else { //new head is next in the list --> change pointers
            head = head.next;
            head.previous = null;
        }
        size--;
        return data; //Return the removed data
    }

    public T removeLast() { //Removes and returns last node's data
        if (isEmpty()) throw new EmptyCollectionException();

        T data = tail.data; //Store the data of the tail node

        if (head == tail) { //If there is only one node
            clear();
        } else { //New head is the tail.previous in the list --> change pointers
            tail = tail.previous;
            tail.next = null;
        }
        size--;
        return data; //Return the removed data
    }

    public T removeAt(int index) { //Remove node at given index and return its data
        if (isEmpty()) throw new EmptyCollectionException();

        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds");

        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<T> current = head;
            for (int i = 0; i < index; i++) { //Traverse to the node at the specified index
                current = current.next;
            }
            //Remove the node by changing pointers
            current.previous.next = current.next;
            current.next.previous = current.previous;

            size--;
            return current.data; //Return the removed data
        }
    }

    public void remove(T data) { //Remove given data from the list (only first occurrence!!!)
        if (isEmpty()) throw new EmptyCollectionException();

        Node<T> current = head;

        while (current != null && !current.data.equals(data)) { //Traverse the list to find the node containing the data
            current = current.next;
        }
        if (current == null) {
            throw new ValueNotFoundException(data + " not found");
        }
        if (current == head) {
            removeFirst();
        } else if (current == tail) {
            removeLast();
        } else {
            //Remove the node by changing pointers
            current.previous.next = current.next;
            current.next.previous = current.previous;
            size--;
        }
    }

    public T getFirst() { //Returns data of first Node
        if (isEmpty()) throw new EmptyCollectionException();
        return head.data;
    }

    public T getLast() { //Returns data of last Node
        if (isEmpty()) throw new EmptyCollectionException();
        return tail.data;
    }

    public T get(int index) {
        if (isEmpty()) throw new EmptyCollectionException();

        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        Node<T> current = head;
        for (int i = 0; i < index; i++) { //Traverse to the node at the specified index
            current = current.next;
        }
        return current.data;
    }

    public boolean contains(T value) { //Checks if list contains given value
        if (isEmpty()) throw new EmptyCollectionException();

        Node<T> current = head;
        while (current != null) { //Traverse list
            if (current.data.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int indexOf(T data) { //Returns index of given data (only first occurrence!!!)
        if (isEmpty()) throw new EmptyCollectionException();

        Node<T> current = head;
        int index = 0;
        while (current != null) { //Traverse list
            if (current.data.equals(data)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1; //Returns negative number, because data not found
    }

    @Override
    public String graphViz(String name) { //Inspiration from stackoverflow: https://shorturl.at/htXIU and converted with ChatGPT with few customizations
        if (isEmpty()) throw new EmptyCollectionException();
        StringBuilder sb = new StringBuilder();

        sb.append("digraph \"").append(name).append("\" {\n");
        sb.append("  node [shape=plaintext];\n"); //For correct display of table
        sb.append("  {rank=same\n"); //All nodes on same rank

        //Add null node to represent the start of the list
        sb.append("    e [label=\"null\" shape=circle];\n");

        //Traverse the doubly linked list and create nodes dynamically
        Node<T> current = head; //Start from the head of the list
        int index = 0; //To keep track of node identifiers

        //Generate nodes for each element in the doubly linked list
        while (current != null) {
            sb.append("    node")
                    .append(index)
                    .append(" [label=<<table border=\"0\" cellspacing=\"0\" cellborder=\"1\"><tr>\n")
                    .append("      <td port=\"ref1\" width=\"28\" height=\"36\" fixedsize=\"true\"></td>\n")
                    .append("      <td port=\"data\" width=\"28\" height=\"36\" fixedsize=\"true\">")
                    .append(current.data) // Append the data of the node
                    .append("</td>\n")
                    .append("      <td port=\"ref2\" width=\"28\" height=\"36\" fixedsize=\"true\"></td>\n")
                    .append("    </tr></table>>];\n");
            current = current.next;
            index++;
        }

        //Add null node to represent the end of the list
        sb.append("    d [label=\"null\" shape=circle];\n");
        sb.append("  }\n");

        //Define edges between nodes
        sb.append("  e -> node0:ref1:c [arrowhead=dot, arrowtail=vee, dir=both, headclip=false];\n");
        for (int i = 0; i < index - 1; i++) {
            sb.append("  node")
                    .append(i)
                    .append(":ref2:c -> node")
                    .append(i + 1)
                    .append(":data:n [arrowhead=vee, arrowtail=dot, dir=both, tailclip=false];\n");
        }
        sb.append("  node")
                .append(index - 1)
                .append(":ref2:c -> d:w [arrowhead=vee, arrowtail=dot, dir=both, tailclip=false];\n");

        //Add backward links for the doubly linked list
        for (int i = index - 1; i > 0; i--) {
            sb.append("  node")
                    .append(i)
                    .append(":ref1:c -> node")
                    .append(i - 1)
                    .append(":data:s [arrowhead=vee, arrowtail=dot, dir=both, tailclip=false];\n");
        }

        sb.append("}\n");

        return sb.toString();
    }

    @Override
    public String graphViz() {
        return graphViz(this.getClass().getSimpleName());
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;  //There is a next element if current is not null
        }

        @Override
        public T next() {
            if (current == null) { //If list is empty
                throw new EmptyCollectionException();
            }
            T data = current.data;  //Retrieve the data of the current node
            current = current.next; //Move to the next node
            return data;
        }
    }

    private class Node<T> {
        T data;
        Node<T> next;
        Node<T> previous;

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.previous = null;
        }
    }


}
