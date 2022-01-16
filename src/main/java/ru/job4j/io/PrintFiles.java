package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * Файловая система представляет собой древовидную структуру.
 * Есть алгоритмы, которые позволяет пройти по всем элементам дерева.
 * В Java есть встроенный механизм - FileVisitor.
 * Рассмотрим программу, которая выводит содержимое
 * всей директории включая вложенные директории.
 * Интерфейс FileVisitor имеет 4 метода.
 * Нас будет интересовать только visitFile.
 * Java последовательно передает в него файлы,
 * а программист их обрабатывает.
 * Здесь используется класс Path, а не File.
 * Path - это усовершенствованная модель File.
 */
public class PrintFiles implements FileVisitor<Path> {

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println(file.toAbsolutePath());
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return CONTINUE;
    }
}
