package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> implements List<T>, Iterable<T> {
    private Node<T> head;
    private Node<T> tail;  // Add a tail reference
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;  // Initialize tail to null
        this.size = 0;
    }

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }






    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public T delete(T element) {
        if (head == null) {
            return null;
        }
        if (head.data.equals(element)) {
            T save = head.data;
            head = head.next;
            size--;
            if (head == null) {
                tail = null;  // If the list becomes empty, set tail to null
            }
            return save;
        }
        Node<T> current = head;
        while (current.next != null && !current.next.data.equals(element)) {
            current = current.next;
        }
        if (current.next == null) {
            return null;
        }
        T save = current.next.data;
        current.next = current.next.next;
        if (current.next == null) {  // If we removed the last element, update the tail
            tail = current;
        }
        size--;
        return save;
    }

    public int size() {
        return size;
    }

    public void insert(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> newNode = new Node<>(element);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
            if (size == 0) {
                tail = newNode;  // If list was empty, the new node is also the tail
            }
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            if (newNode.next == null) {  // If we inserted at the end, update the tail
                tail = newNode;
            }
        }
        size++;
    }

    public Iterator<T> iterator() {
        return new SinglyLinkedListIterator();
    }

    private class SinglyLinkedListIterator implements Iterator<T> {
        private Node<T> current = head;
        private Node<T> previous = null;
        private Node<T> prevPrev = null;
        private boolean canRemove = false;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            prevPrev = previous;
            previous = current;
            current = current.next;
            canRemove = true;
            return data;
        }

        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException();
            }
            if (previous == head) {
                head = head.next;
                if (head == null) {
                    tail = null;  // If the list becomes empty, set tail to null
                }
            } else if (prevPrev != null) {
                prevPrev.next = previous.next;
                if (previous.next == null) {  // If we removed the last node, update tail
                    tail = prevPrev;
                }
            }
            size--;
            previous = null;
            canRemove = false;
        }
    }

    public void insertFirst(T element) {
        insert(0, element);
    }

    public T getFirst() {
        if (head == null) {
            return null;
        }
        return head.data;
    }

    public T deleteFirst() {
        if (head == null) {
            return null;
        }
        T data = head.data;
        head = head.next;
        size--;
        if (head == null) {  // If the list becomes empty, set tail to null
            tail = null;
        }
        return data;
    }

    public T delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return deleteFirst();
        }
        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        T data = current.next.data;
        current.next = current.next.next;
        if (current.next == null) {  // If we removed the last element, update tail
            tail = current;
        }
        size--;
        return data;
    }

    public int indexOf(T object) {
        if (head == null) {
            return -1;
        }
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            if (current.data.equals(object)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clear() {

        head = null;
        tail = null;  // Also clear the tail reference
        size = 0;
    }

    public T[] toArray() {
        T[] array = (T[]) java.lang.reflect.Array.newInstance(head.data.getClass(), size);
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            array[i] = current.data;
            current = current.next;
        }
        return array;
    }
}
