package ru.job4j.io;

import java.io.*;

/**
 * FileInputStream читает файл по одному байту. Такой подход медленный.
 * Представьте, что вам нужно перенести 100 тетрадей из одной комнаты в другую.
 * По аналогии с нашим кодом мы стали бы переносить по одной тетради за раз.
 * Другой подход - это взять несколько тетрадей. Так будет быстрее.
 * В потоках ввода вывода для этого есть буферизированные обертки.
 * Перепишем код через буферизированные потоки.
 * <p>
 * Это наглядный пример использование шаблона декоратор. Один поток оборачивается в другой.
 * Обратите внимание мы заменили FileInputStream на FileReader.
 * Базовый поток - это поток байтов.
 * Его можно обернуть в символьный поток, если мы знаем, что нам нужно читать текст.
 * Здесь тоже используется декоратор.
 * Символьные потоки позволяют читать сразу символы, а не байты.
 * Так же у буферизированного символьного потока есть методы чтения целой строки.
 * В нашем примере используется чтение и вывод строк через stream api.
 * Когда конструкция try-with-resources вызывает метод close у BufferedReader
 * внутри метода close происходит вызов внутреннего потока, то есть потока FileReader.
 * Поэтому все ресурсы будут закрыты.
 */
public class ReadAndBufferedFile {

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

        try (BufferedReader in = new BufferedReader(new FileReader("input.txt"))) {
            in.lines().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
