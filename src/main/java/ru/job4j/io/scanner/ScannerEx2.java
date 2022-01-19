package ru.job4j.io.scanner;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

/**
 * Пример #2
 * Пусть надо из потока данных вычленить адреса почты,
 * которые разделены между собой “, ”.
 * Обратите внимание, что здесь в качестве источника
 * мы указали одну из реализаций InputStream – ByteArrayInputStream.
 * В качестве разделителя с помощью метода userDelimiter()
 * мы указали нужный разделитель.
 * В данном случае мы могли воспользоваться методом String.split(),
 * но когда дело доходит до чтения файлов,
 * то Scanner позволяет использовать преимущества буферизированных потоков
 * и возможности по разбиению на токены.
 */
public class ScannerEx2 {
    public static void main(String[] args) {
        var data = "empl1@mail.ru, empl2@mail.ru, empl3@mail.ru";
        var scanner = new Scanner(new ByteArrayInputStream(data.getBytes()))
                .useDelimiter(", ");
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }
}
