package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/**
 * FileVisitor — это специальный интерфейс,
 * в котором описаны все методы для обхода дерева файлов.
 * Здесь мы описываем что нужно делать с каждым файлом в каждой директории.
 * Доработать код, чтоб он находил дубликаты.
 * Использовать модель FileProperty.
 * Подумайте какие структуры данных лучше подойдут для решения этого задания
 * <p>
 * При использовании коллекции Set код работает неправильно.
 * Это происходит, потому, что при первом вхождении файла
 * он помещается в коллекцию, не печатаясь.
 * Попробуйте использовать HashMap.
 * <p>
 * Проверьте правильность вывода результата на печать.
 * Если у файла нет дубликатов, не надо выводить его в печать.
 */
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty filePr = new FileProperty(
                file.toFile().length(), file.toFile().getName());
        if (map.containsKey(filePr)) {
            map.get(filePr).add(file);
        } else {
            map.put(filePr, new ArrayList<>(List.of(file)));
        }
        return super.visitFile(file, attrs);
    }

    public void info() {
        for (Map.Entry<FileProperty, List<Path>> entry : map.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.println(entry.getValue());
            }
        }
    }
}
