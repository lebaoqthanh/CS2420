package assign06;


import java.util.NoSuchElementException;

public class LinkedListStack<E> implements assign06.Stack<E>  {
    private SinglyLinkedList<E> list;  // Backed by SinglyLinkedList

    public LinkedListStack() {
        list = new SinglyLinkedList<>();
    }


    public void push(E element) {
        if (element.equals(null)){
            throw new NoSuchElementException();
        }
        list.insertFirst(element);  // Adds to the end of the list (this is the equivalent of "push")
    }


    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }
        return list.delete(list.get(0));  // Deletes the first element
    }


    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }
        return list.get(0);  // Returns the first element without removal
    }


    public boolean isEmpty() {
        return list.size() == 0;  // Checks if the list is empty
    }


    public int size() {
        return list.size();  // Returns the size of the list
    }
    public void clear(){
        list.clear();
    }
}
