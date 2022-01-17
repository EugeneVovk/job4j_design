package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * Разработайте программу Search,
 * которая будет искать файлы только по определенному предикату.
 * Программа должна вернуть файлы с расширением js.
 * <p>
 * Добавим валидацию.
 * Валидация - это процесс проверки данных перед выполнением операции.
 * Блок if - это валидация аргументов.
 * Если ее не выполнять, то программа будет падать
 * с ошибками не понятными для пользователя.
 * Если пользователь забыл указать параметры,
 * то в консоли он увидит в чем причина и как ее поправить.
 * Программа должна запускаться с параметрами.
 * Первый параметр - начальная папка.
 * Второй параметр - расширение файлов, которые нужно искать.
 * Необходимо добавить валидацию данных параметров.
 */
public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException(
                    "There should be two parameters.");
        }
        Path file = Paths.get(args[0]);
        search(file, p -> p.toFile().getName().endsWith(args[1]))
                .forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPath();
    }
}
