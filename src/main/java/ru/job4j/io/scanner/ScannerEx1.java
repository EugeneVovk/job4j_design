package ru.job4j.io.scanner;

import java.io.CharArrayReader;
import java.util.Scanner;

/**
 * Scanner - используется для нахождения
 * последовательности символов среди данных источника.
 * Формально говоря, последовательность символов называется токеном или лексемой,
 * а процесс сканирования лексическим анализом.
 * В качестве источника данных Scanner принимает любой вид данных,
 * включая Reader, InputStream, File для java.io и Readable, Path для java.util.nio.
 * Также можно задать источник в виде строки String.
 * <p>
 * Пример #1
 * Пусть надо из потока данных вычленить только числа.
 * Здесь в качестве источника мы указали одну из реализаций Reader - CharArrayReader.
 * Данная реализация позволяет читать данные из массива символов,
 * т.е. из временной памяти.
 * Важно! В примере выше шаблон разделителя не указан,
 * поэтому используется тот, что по умолчанию,
 * а именно символы перехода на новую строку и пробел.
 */
public class ScannerEx1 {
    public static void main(String[] args) {
        var ls = System.lineSeparator();
        var data = String.join(ls,
                "1 2 3",
                "4 5 6",
                "7 8 9");
        var scanner = new Scanner(new CharArrayReader(data.toCharArray()));
        while (scanner.hasNextInt()) {
            System.out.print(scanner.nextInt());
            System.out.print(" ");
        }
    }
}
