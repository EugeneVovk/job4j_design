package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayList<T> implements List<T> {
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        increaseArrayLength();
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T prev = get(index);
        container[index] = newValue;
        return prev;
    }

    @Override
    public T remove(int index) {
        T prev = get(index);
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        container[size - 1] = null;
        size--;
        modCount++;
        return prev;
    }

    @Override
    public T get(int index) {
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }
        };
    }

    private void increaseArrayLength() {
        if (container.length == 0) {
            container = Arrays.copyOf(container, 10);
        } else if (container.length == size) {
            container = Arrays.copyOf(container, size * 2);
        }
    }
}
