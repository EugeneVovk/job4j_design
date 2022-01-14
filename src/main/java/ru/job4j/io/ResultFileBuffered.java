package ru.job4j.io;

import java.io.*;

/**
 * В первом уроке темы "Ввод вывод" мы записывали результат вычисления в файл.
 * Запись идет по байтам.
 * Чтобы убрать задержку в выполнении программы, нужно использовать буферизированные потоки.
 *
 * Исходный поток ввода - это файл FileOutputStream. Он записывает данные по байтам.
 * Блокирует всю программу, пока запись не закончится. Это плохо.
 * Первая обертка - это BufferedOutputStream.
 * Это буфер, который собираем переданные в него байты.
 * Аккумулирует их и постепенно отдает их в FileOutputStream.
 * В этом случае программа не блокируется до тех пока в буфере есть место.
 * Вторая обертка над буфером - это PrintWriter.
 * Мы знаем, что будем записывать текст.
 * В Java есть удобное АПИ для этого, например, PrintWriter поддерживает метод println()
 * для записи данных с последующим переходом на новую строку.
 */
public class ResultFileBuffered {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write(("Hello, world!" + System.lineSeparator()).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("result.txt")))) {
            out.println("Hello, word!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
