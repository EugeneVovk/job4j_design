package ru.job4j.io.scanner;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Пример #3
 * Еще одной интересной возможностью Scanner
 * является возможность задать систему счисления при чтении чисел.
 * Например, можно прочитать числа в шестнадцатеричном виде
 * и вывести в десятичном.
 * Мы указываем в качестве источника данных временный файл,
 * который создаем и в который записываем предварительно.
 * Метод useRadix() указывает в какой системе счисления находятся входные числа.
 * Важно! Если Scanner работает с внешними источниками
 * его нужно использовать с try-with-resources.
 * Класс java.util.Scanner может быть полезен,
 * когда нужно вычленить из данных только те, что Вам нужны.
 * Для этого нужно назначить разделитель,
 * например, пробел, запятая или регулярное выражение.
 * Также Scanner имеет полезную особенность для чтения чисел
 * различных систем счисления.
 */
public class ScannerEx3 {
    public static void main(String[] args) throws IOException {
        var data = "A 1B FF 110";
        var file = File.createTempFile("data", null);
        try (BufferedOutputStream out = new BufferedOutputStream(
                new FileOutputStream(file))) {
            out.write(data.getBytes());
        }
        try (var scanner = new Scanner(file).useRadix(16)) {
            while (scanner.hasNextInt()) {
                System.out.print(scanner.nextInt());
                System.out.print(" ");
            }
        }
    }
}
