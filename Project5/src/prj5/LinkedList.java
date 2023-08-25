// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Cristian Valencia (cristianv)
// -- Connor Marks (connorm20)
// -- Josh Cole (joshcole137)

package prj5;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author <Cristian Valencia> <cristianv>
 * @author <Connor Marks> <connorm20>
 * @author <Josh Cole> <joshcole137>
 *
 * @version <2021.04.20>
 *
 *
 *
 * @param <T>
 */

public class LinkedList<T> implements LList<T>, Iterable<T> {
    // Fields~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private Node<T> head;
    private int size;

    /**
     * Creates a new LinkedList
     */
    public LinkedList() {
        head = null;
        size = 0;
    }


    /**
     * Gets the size of the list
     *
     * @return the size of the list
     */
    public int size() {
        return size;
    }


    /**
     * Add an entry to a specified location
     *
     * @param index
     *            the location
     *
     * @param newEntry
     *            the entry being added
     * @throws IllegalArgumentException
     *             if the provided entry is null
     * @throws IndexOutOfBoundsException
     *             if the index is out of bounds
     */
    public void add(int index, T newEntry) {
        // check if the object is null
        if (newEntry == null) {
            throw new IllegalArgumentException("Object is null");
        }

        // check if the index is out of bounds
        if ((index < 0) || (index > size())) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Node<T> current = head;

        // If list is empty
        if (isEmpty()) {
            head = new Node<T>(newEntry);
        }
        // all other cases
        else if (index == 0) {
            Node<T> newNode = new Node<T>(newEntry);
            head = newNode;
            newNode.setNext(current);
        }
        else {
            int currentIndex = 0;
            while (current != null) {
                if ((currentIndex + 1) == index) {
                    Node<T> nextNext = current.next;
                    Node<T> newNode = new Node<T>(newEntry);
                    current.setNext(newNode);
                    newNode.setNext(nextNext);

                }
                current = current.next();
                currentIndex++;
            }
        }
        size++;
    }


    /**
     * Checks if a list is empty
     *
     * @return true/false whether a list is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * Adds an entry to the end of the list
     *
     * @param anEntry
     *            the entry being added
     * @throws IllegalArgumentException
     *             if the entry is null
     */
    public void add(T anEntry) {
        // check if the object is null
        if (anEntry == null) {
            throw new IllegalArgumentException("Object is null");
        }
        Node<T> current = head;
        // empty stack case
        if (isEmpty()) {
            head = new Node<T>(anEntry);
        }
        // other cases
        else {
            while (current.next != null) {
                current = current.next;
            }
            current.setNext(new Node<T>(anEntry));
        }
        size++;
    }


    /**
     * Removes a specified object from the list
     *
     * @param obj
     *            the object being removed
     *
     * @return true/false whether the object is removed
     *
     */
    public boolean remove(T obj) {
        Node<T> current = head;
        // account for matching head
        if ((head != null) && (obj.equals(current.data))) {
            head = head.next;
            size--;
            return true;
        }

        // account for 2+ size
        while (size() >= 2 && (current.next != null)) {
            if ((obj.equals(current.next.data))) {
                if (current.next.next != null) {
                    current.setNext(current.next.next);
                }
                else { // maybe remove this statement
                    current.setNext(null);
                }
                size--;
                return true;
            }
            current = current.next;
        }

        // this accounts for the isEmpty case or the object does not exist
        return false;
    }


    /**
     * Removes the object at the given position
     *
     * @param index
     *            the position of the object
     * @return true if the removal was successful
     * @throws IndexOutOfBoundsException
     *             if there is not an element at the index
     */
    public boolean remove(int index) {
        // if the index is invalid
        if (index < 0 || head == null) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        else {
            Node<T> current = head;
            int currentIndex = 0;

            while (current.next != null) {
                if ((currentIndex + 1) == index) {
                    Node<T> newNext = current.next.next;
                    current.setNext(newNext);
                    size--;
                    return true;
                }
                current = current.next;
                currentIndex++;
            }

        }
        return false;
    }


    /**
     * Gets the object at the given position
     *
     * @param index
     *            where the object is located
     * @return The object at the given position
     * @throws IndexOutOfBoundsException
     *             if no node at the given index
     */
    public T get(int index) {
        Node<T> current = head;
        int currentIndex = 0;
        T data = null;
        while (current != null) {
            if (currentIndex == index) {
                data = current.data;
            }
            currentIndex++;
            current = current.next;
        }

        // check if the data was null...
        if (data == null) {
            // ... if so throw an exception
            throw new IndexOutOfBoundsException("Index exceeds the size.");
        }
        return data;
    }


    /**
     * Clears the whole list
     */
    public void clear() {
        if (head != null) {
            head.setNext(null);
            head = null;
            size = 0;
        }

    }


    /**
     * Checks if the list contains the given object
     *
     * @param obj
     *            the object to check for
     * @return true if it contains the object
     */
    public boolean contains(T obj) {
        Node<T> current = head;
        while (current != null) {
            if (obj.equals(current.data)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }


    /**
     * Gets the last time the given object is in the list
     *
     * @param obj
     *            the object to look for
     * @return the last position of it. -1 If it is not in the list
     */
    public int lastIndexOf(T obj) {
        int lastIndex = -1;
        Node<T> current = head;
        int currentIndex = 0;
        while (current != null) {
            if (obj.equals(current.data)) {
                lastIndex = currentIndex;
            }
            currentIndex++;
            current = current.next;

        }
        return lastIndex;
    }


    /**
     * Returns true if both lists have the exact same contents
     * in the exact same order
     *
     * @return a boolean of whether two lists have the same contents,
     *         item per item and in the same order
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() == obj.getClass()) {
            @SuppressWarnings("unchecked")
            LinkedList<T> other = ((LinkedList<T>)obj);
            if (other.size() == this.size()) {
                Node<T> current = head;
                Node<T> otherCurrent = other.head;
                while (current != null) {
                    if (!current.getData().equals(otherCurrent.getData())) {
                        return false;
                    }
                    current = current.next();
                    otherCurrent = otherCurrent.next();
                }
                return true;
            }
        }

        return false;
    }


    /**
     * Returns a string representation of the list If a list contains A, B, and
     * C, the following should be returned "{A, B, C}" (Without the quotations)
     *
     * @return a string representing the list
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        if (!isEmpty()) {
            Node<T> current = head;
            while (current != null) {
                T piece = current.getData();
                result.append(piece.toString());
                if (current != null) {
                    result.append(", ");
                }
                current = current.next();
            }
        }
        result.append("}");
        return result.toString();
    }


    /**
     * Create an object that contains the elements of the list in an array
     *
     * @return an Array of the list contents
     */

    public Object[] toArray() {
        Object[] result = new Object[this.size()];
        Iterator<T> iter = iterator();
        for (int i = 0; i < size; i++) {
            result[i] = iter.next();
        }
        return result;
    }


    /**
     * 
     * @param <T>
     *            generic of the type to cast
     * @param list
     *            list to be put in
     * @param comp
     *            comparator to be put in
     */
    public static <T> void sort(
        LinkedList<T> list,
        Comparator<? super T> comp) {
        Iterator<T> iter = list.iterator();
        LinkedList<T> sortedList = new LinkedList<T>();
        while (iter.hasNext()) {
            T current = iter.next();
            int i = 0;

            while (i < sortedList.size && comp.compare(current, sortedList.get(
                i)) >= 0) {
                i++;
            }
            sortedList.add(i, current);
        }
        list.head = sortedList.head;
    }


    /**
     * Creates an iterator
     *
     * @param <T>
     *
     * @return an Iterator object
     */
    public Iterator<T> iterator() {
        return new CovidIterator<T>();
    }

    /**
     * Represents a node in a singly linked list. The node stores data along
     * with having a pointer to the next npde.
     *
     * @author Cristian
     *
     * @param <T>
     */
    private static class Node<T> {
        private T data;
        private Node<T> next;

        /**
         * Constructor for Node, instantiating it with given data
         *
         * @param newData
         *            the data being passed into the node
         */
        public Node(T newData) {
            this.data = newData;
        }


        /**
         * Constructor for node with a set nextNode
         *
         * @param newData
         *            the data being passed to the Node
         * @param nextNode
         *            the next node
         */
        public Node(T newData, Node<T> nextNode) {
            this.data = newData;
            this.next = nextNode;
        }


        /**
         * Sets the next node
         *
         * @param nextNode
         *            the next node in the list
         */
        public void setNext(Node<T> nextNode) {
            this.next = nextNode;
        }


        /**
         * Gets the next node
         *
         * @return the next node
         */
        public Node<T> next() {
            return next;
        }


        /**
         * Gets the data in the node
         *
         * @return the data in the node
         */
        public T getData() {
            return data;
        }
    }


    /**
     * Private Iterator Class for the LinkedList
     *
     * @author Cristian Valencia
     * @version 4.22.21
     *
     * @param <T>
     *            the generic type of the Iterator
     */
    private class CovidIterator<E> implements Iterator<T> {
        private Node<T> previous;
        private Node<T> next;

        /**
         * Initialize the iterator
         */
        public CovidIterator() {
            next = new Node<T>(null, head);
            previous = null;
        }


        /**
         * Checks whether the list has more elements
         *
         * @return true/false whether there are more elements in the list
         */
        @Override
        public boolean hasNext() {
            return (next.next != null);
        }


        /**
         * Moves and gets the next value in the list
         *
         * @return the next value in the list
         *
         * @throws NoSuchElementException
         *             When there are no nodes in the list
         */
        @Override
        public T next() {
            // Handles an empty list
            if (next.next == null) {
                throw new NoSuchElementException("No nodes left in list");
            }
            T data = next.next.getData();
            previous = next;
            next = next.next;
            return data;
        }

    }

}
