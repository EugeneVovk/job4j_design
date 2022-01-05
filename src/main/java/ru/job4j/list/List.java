package ru.job4j.list;

/**
 * создадим реализацию списка на основе динамического массива, аналог ArrayList.
 */
public interface List<T> extends Iterable<T> {
    void add(T value);

    T set(int index, T newValue);

    T remove(int index);

    T get(int index);

    int size();
}
