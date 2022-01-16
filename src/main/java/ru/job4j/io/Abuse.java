package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

/**
 * Удаление из файла запрещённых слов
 * Метод принимает два файла и список слов для удаления.
 *
 * Результат работы этой программы будет новый файл.
 */
public class Abuse {

    public static void drop(String source, String target, List<String> words) throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            in.lines()
                    .flatMap(line -> Stream.of(line.split("\\s+")))
                    .filter(word -> !words.contains(word))
                    .map(word -> word + " ")
                    .forEach(out::print);
        }
    }
}
