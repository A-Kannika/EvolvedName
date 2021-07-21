import java.util.*;

/**
 * Create by Kannika Armstrong
 * TCSS342(Spring 2021): April 14, 2021
 * Assignment1 : Evolved Names (MyLinkedList class)
 * Professor. Christopher Paul Marriott
 */

public class MyLinkedList<Type extends Comparable> {

    private Node first;
    private Node last;


    public MyLinkedList() {
        this.first = null;
        this.last = null;
    }

    // add an item to the end of the list
    public void add(Type item) {
        if (isEmpty()) {
            first = new Node(item);
        } else {
            last = first;
            while (last.next != null) {
                last = last.next;
            }
            last.next = new Node(item);
        }
    }

    // add an item to the given index
    public void add(Type item, int index) {
        Node current = first;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        current.next = new Node(item, current.next);
    }

    // remove an item at the given index
    public void remove(int index) {
        Node temp = first;
        if (index == 0) {
            first = temp.next;
            return;
        }
        for (int i = 0; temp != null && i < index - 1; i++) {
            temp = temp.next;
        }
        if (temp == null || temp.next == null) {
            return;
        }
        temp.next = temp.next.next;
    }

    // returns the value at the given index in the list
    public Type get(int index) {
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    // replaces the value at the given index with the given value
    public void set(int index, Type item) {
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.item = item;
    }

    // returns size of list
    public int size() {
        Node current = first;
        int count = 0;
        while (current != null) {
            current = current.next;
            count++;
        }
        return count;
    }

    // return whether list is empty
    public boolean isEmpty() {
        return this.size() == 0;
    }

    // iterates through list
    public Iterator<Type> iterator() {
        if (isEmpty()) {
            return Collections.emptyIterator();
        }
        return new Iterator<>() {
            Node current = null;

            @Override
            public boolean hasNext() {
                return current != last;
            }

            @Override
            public Type next() {
                if (current == null) {
                    current = first;
                    return current.item;
                }
                if (current.next == null) {
                    throw new NoSuchElementException();
                }
                current = current.next;
                return current.item;
            }
        };
    }

    // arranges list using bubble sort method
    public void sort() {
        Node firstNode = first;
        Node nextNode = first.next;
        for (int i = 0; i < size() - 1; i++) {
            boolean swap = false;
            for (int j = 0; j < size() - i - 1; j++) {
                while (nextNode != null) {
                    if (firstNode.item.compareTo(nextNode.item) > 0) {
                        swap = true;
                        Type tItem = firstNode.item;
                        firstNode.item = nextNode.item;
                        nextNode.item = tItem;
                    }
                    firstNode = nextNode;
                    nextNode = nextNode.next;
                }
            }
            if (!swap) {
                break;
            }
        }
    }

    //returns result as string
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node current = this.first;
        while (current != null) {
            result.append(current);
            current = current.next;
        }
        return result.toString();
    }

    private class Node {
        public Type item;
        public Node next;

        public Node(Type item) {
            this.item = item;
            this.next = null;
        }

        public Node(Type item, Node next) {
            this.item = item;
            this.next = next;
        }

        @Override
        public String toString() {
            return item.toString();
        }
    }
}

