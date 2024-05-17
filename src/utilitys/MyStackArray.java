package utilitys;

import java.util.Arrays;

public class MyStackArray<E> {
    private int size = 0;
    private Object[] elements;

    public MyStackArray() {
        elements = new Object[Configuration.INSTANCE.initialCapacity];
    }

    public void push(E e) {
        if (size == elements.length) {
            ensureCapacity();
        }
        elements[size++] = e;
    }

    @SuppressWarnings("unchecked")
    public E pop() {
        E e = (E) elements[--size];
        elements[size] = null;
        return e;
    }

    public E peek() {
        @SuppressWarnings("unchecked")
        E e = (E) elements[size - 1];
        return e;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        int newSize = elements.length * 2;
        elements = Arrays.copyOf(elements, newSize);
    }
}
