package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Консольные программы требуют передачи разных параметров.
 * Мы работаем с виртуальной машиной Java.
 * Ее запуск может происходить с ключами.
 * Например, можно указать количество памяти под программу и кодировку.
 * java -Xmx=514 -encoding=UTF-8
 * Эти параметры можно указывать в произвольном порядке.
 * Можно некоторые заполнять, а некоторые пропускать.
 * В этом задании вам нужно будет написать программу,
 * которая принимает массив параметров и разбивает их на пары: ключ, значение.
 * Метод substring() возвращает новую строку,
 * которая является подстрокой этой строки с определённого индекса.
 */
public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("An invalid argument");
        }
        Stream.of(args)
                .map(el -> el.split("="))
                .forEach(els -> {
                    if (els.length != 2 || els[0].isEmpty() || els[1].isEmpty()) {
                        throw new IllegalArgumentException(
                                "One of the arguments is invalid");
                    }
                    values.put(els[0].substring(1), els[1]);
                });
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
