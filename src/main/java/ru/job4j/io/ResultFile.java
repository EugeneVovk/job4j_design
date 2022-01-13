package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Напишем программу, которая записывает текст в файл.
 * Класс java.io.FileOutputStream позволяет записать данные в файл
 * 1. Конструктор класса FileOutputStream принимает имя файла.
 * Файл будет создан в корне проекта.
 * 2. Для записи используется метод out.write.
 * Этот метод принимает массив байт, поэтому строку преобразовали в массив байтов.
 * 3. Любой ресурс должен быть закрыт для это используется конструкция try-with-resources.
 * Обратите внимание, что для перехода на новую строку используется System.lineSeparator().
 * Это сделано специально, поскольку переход на новую строку зависит от операционной системы.
 * В какой-то \n, в какой-то \r\n.
 * Использование System.lineSeparator() позволяет сделать код независимым от операционной системы.
 */
public class ResultFile {

    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write("Hello, world!".getBytes());
            out.write(System.lineSeparator().getBytes());
            int size = 10;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    out.write(((i + 1) * (j + 1) + "\t").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
