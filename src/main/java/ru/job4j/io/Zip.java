package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Техническое задание.
 * <p>
 * 1. При запуске указывается папка, которую мы хотим архивировать,
 * например: c:\project\job4j\
 * 2. В качестве ключа передаётся расширение файлов,
 * которые не нужно включать в архив.
 * 3. Архив должен сохранять структуру проекта.
 * То есть содержать подпапки.
 * 4. Запуск проекта.
 * java -jar pack.jar -d=c:\project\job4j\ -e=class -o=project.zip
 * java -jar pack.jar - Это собранный jar.
 * -d - directory - которую мы хотим архивировать.
 * -e - exclude - исключить файлы с расширением class.
 * -o - output - во что мы архивируем.
 * 5. Для работы с входными аргументами используйте класс ArgsName из прошлого задания.
 * Важно!
 * Перед запуском проекта нужно делать валидацию аргументов,
 * проверив что они все присутствуют.
 * Также нужно проверить, что архивируемая директория существует.
 * 6. Для архивации использовать классы ZipOutputStream.java. Создайте класс Zip.
 * Для поиска и фильтрации файлов нужно использовать
 * класс Search из задания "Сканирование файловой системы".
 * 7. Для тестирования кода в IDEA нужно прописать параметры запуска main метода
 * Run - Edit
 */
public class Zip {

    public static void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(
                    new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip"));
    }
}
