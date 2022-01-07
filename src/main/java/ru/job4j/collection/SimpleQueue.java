package ru.job4j.collection;

import java.util.NoSuchElementException;

/**
 * Данные очереди нужно хранить в ru.job4j.collection.SimpleStack.
 * Для этого задания нужны два стека.
 * Представьте, что у вас стопка с тарелками.
 * Вам нужно достать нижнюю тарелку. Для этого вы перекладываете все тарелки в другую стопку.
 * Стопка - это стек. Для операции извлечения первой тарелки нужны две стопки.
 * FIFO - first input first output.
 */
public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * Метод poll() - возвращает первое значение и удаляет его из коллекции.
     */
    public T poll() {
        if (in.isEmpty()) {
            throw new NoSuchElementException();
        }
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    /**
     * Метод push(T value) - помещает значение в конец.
     */
    public void push(T value) {
        in.push(value);
    }
}
