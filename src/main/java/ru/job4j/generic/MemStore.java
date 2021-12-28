package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;

/**
 * универсальное хранилище
 */
public final class MemStore<T extends Base> implements Store<T> {
    private final Map<String, T> storage = new HashMap<>();

    /**
     * метод добавляет в хранилище объект типа T,
     * при этом метод ничего не возвращает
     */
    @Override
    public void add(T model) {
        storage.putIfAbsent(model.getId(), model);
    }

    /**
     * метод выполняет замену объекта по id,
     * на объект который передается в параметрах метода
     * и возвращает true, если замена выполнена успешно
     */
    @Override
    public boolean replace(String id, T model) {
        return storage.replace(id, storage.get(id), model);
    }

    /**
     * метод удаляет пару ключ-значение по id,
     * который передается в метод и возвращает true,
     * если удаление выполнено успешно
     */
    @Override
    public boolean delete(String id) {
        return storage.remove(id, storage.get(id));
    }

    /**
     * метод возвращает объект по ключу,
     * который равен id, передаваемый в качестве параметра метода
     * или возвращает null если по такому ключу в Map еще нет пару ключ-значение
     */
    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}
