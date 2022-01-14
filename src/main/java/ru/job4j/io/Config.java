package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Наша программа должна запускаться в различном окружении.
 * Например, разные базы данных, разные пути хранения.
 * Все эти настройки нужно хранить вне кода программы.
 * Это позволяет изменять настройки и не трогать собранную программу.
 * Файл настроек должен содержать пары ключ-знание.
 * Пары должны быть разделены символом равно.
 * <p>
 * Метод load() - должен загружать пару ключ-значение в Map values.
 * Метод value() - должен возвращать значение ключа.
 * Важно в файле могут быть пустые строки и комментарии их нужно пропускать.
 * Для считывания файлов нужно использовать
 * import java.io.BufferedReader;
 * import java.io.FileReader;
 */
public class Config {

    private final String path;
    private Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(this.path))) {
            values = in.lines()
                    .filter(s -> s.length() > 0 && s.charAt(0) != '#')
                    .map(s -> s.split("=", 2))
                    .collect(Collectors.toMap(k -> k[0], v -> v[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        String rsl = values.get(key);
        if (rsl == null) {
            throw new IllegalArgumentException("Нарушение шаблона ключ=значение");
        }
        return rsl;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
