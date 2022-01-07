package ru.job4j.collection;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<>();

    /**
     * Метод pop() - должен возвращать значение и удалять его из коллекции.
     */
    public T pop() {
        return linked.deleteFirst();
    }

    /**
     * Метод push(T value) - помещает значение в коллекцию.
     */
    public void push(T value) {
        linked.addFirst(value);
    }

    /**
     * Метод isEmpty() определяет, является ли стек пустым или нет
     */
    public boolean isEmpty() {
        return !linked.iterator().hasNext();
    }
}
