package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;
    private int modCount;

    @Override
    public void add(E value) {
        Node<E> lst = last;
        Node<E> newNode = new Node<>(value, null);
        last = newNode;
        if (lst == null) {
            first = newNode;
        } else {
            lst.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, this.size);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> node = first;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E rsl = node.item;
                node = node.next;
                return rsl;
            }
        };
    }

    private static class Node<E> {
        private final E item;
        private Node<E> next;

        private Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
