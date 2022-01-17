package ru.job4j.io;

import java.io.File;

/**
 * Эта программа выводит папки проекта. Начальная папка указана сразу в коде.
 * Изменим программу, чтобы начальная папка передавалась через аргументы запуска.
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
public class Dir {
    public static void main(String[] args) {

        if (args.length == 0) {
            throw new IllegalArgumentException(
                    "Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(
                    String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("Not directory %s", file.getAbsoluteFile()));
        }
        for (File subfile : file.listFiles()) {
            System.out.println(subfile.getName());
        }
    }
}
