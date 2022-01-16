package ru.job4j.io;

import java.io.File;

/**
 * Главным элементом файловой системы является объект java.io.File.
 * File может быть и текстовым документом и директорией.
 * Рассмотрим задачу с получением всех элементов директории.
 * Наш проект лежит в папке c:\projects\.
 * Напишем программу, которая проверяет,
 * что директория projects - это директория и выведем ее содержимое.
 * <p>
 * Функция getTotalSpace() является частью класса File в Java.
 * Эта функция возвращает общий размер раздела,
 * общий размер носителя (диска) в байтах,
 * обозначенного абстрактным путем,
 * если путь не существует, то возвращается 0L.
 * <p>
 * file.listFiles() - Получаем список файлов в этой директории.
 */
public class Dir2 {
    public static void main(String[] args) {

        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(
                    String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("size : %s", file.getTotalSpace()));
        for (File subfile : file.listFiles()) {
            System.out.println(subfile.getAbsoluteFile());
        }
    }
}
