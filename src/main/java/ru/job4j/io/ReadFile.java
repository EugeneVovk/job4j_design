package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Этот класс позволяет прочитать данные из файла.
 * Данные считываются по байтам.
 * Здесь используется конструкция try-with-resources, чтобы закрыть поток ввода.
 * Метод read() работает как итератор, считывает информацию потока
 * и как только он достигнет конца, он вернёт "-1"
 * Метод read() возвращает int, а не byte потому что надо такой тип,
 * который может вместить в себя один байт (реальные данные),
 * плюс одно служебное значение (это тот самый -1),
 * которое является признаком окончания чтения.
 * Сохраняем и выводим в консоль символы, а не число - (char) read
 * <p>
 * Получившийся текст можно разбить на строчки через метод String.split:
 * <p>
 * String[] lines = text.toString().split(System.lineSeparator());
 * for (String line : lines) {
 * System.out.println(line);
 * }
 */
public class ReadFile {

    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("input.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
