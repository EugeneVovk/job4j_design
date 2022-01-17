package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

/**
 * FileVisitor — это специальный интерфейс,
 * в котором описаны все методы для обхода дерева файлов.
 * Здесь мы описываем что нужно делать с каждым файлом в каждой директории.
 * Доработать код, чтоб он находил дубликаты.
 * Использовать модель FileProperty.
 * Подумайте какие структуры данных лучше подойдут для решения этого задания
 */
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Set<FileProperty> set = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty filePr = new FileProperty(
                file.toFile().length(), file.toFile().getName());
        if (!set.contains(filePr)) {
            set.add(filePr);
        } else {
            System.out.println(file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }
}
