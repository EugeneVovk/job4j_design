package ru.job4j.io;

import static org.hamcrest.Matchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Чтобы создать файлы во временной директории,
 * нужно использовать класс org.unit.rules.TemporaryFolder.
 * Этот класс позволяет создавать файлы и директории во временном каталоге.
 * После запуска теста файлы созданные через TemporaryFolder будут удалены.
 * Теперь нам нет необходимости заботиться о мусоре,
 * который оставляет наш тест, потому что его уберет класс TemporaryFolder.
 * <p>
 * folder.newFile() - мы создаем файл и заполняем его содержимое.
 * Abuse.drop() - мы выполняем действие программы, а далее читаем полученный.
 * assertThat() - проверяем результаты.
 *
 * Важно, класс TemporaryFolder нужно использовать,
 * когда программа записывает результат в файл.
 */
public class AbuseTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void drop() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("hello foolish dude");
            out.println("java job4j php");
        }
        Abuse.drop(source.getAbsolutePath(), target.getAbsolutePath(), List.of("foolish", "php"));
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("hello dude java job4j "));
    }

}