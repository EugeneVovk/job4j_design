package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Логирование - это процесс записи в файл полезной информации о работе программы.
 * Полученный файл называют лог-файлом.
 * Если приложение работает плохо, то первое, что проверяют - это лог файл.
 * "Посмотри в лог" - это значит открыть файл логирования
 * и посмотреть наличие Exception.
 * В Java есть удобная библиотека, которая позволяется решать эти вопросы.
 * Log4j - библиотека позволяет осуществить логирование процессов в приложении.
 * <p>
 * Оператор плюс (+) для String создает в памяти новую строку.
 * Это плохо, потому что в памяти создаются копии строк.
 * Это приводит к нерациональному использованию памяти.
 * Чтобы избежать сложение строк в slf4j
 * используется шаблон для подстановки переменных.
 * Запишем в лог переменные разных типов.
 * Первый параметр метода - это шаблон.
 * Шаблон содержит текст и отметки, которые заменяются на параметры.
 * Параметры указываются после шаблона.
 * Метки заменяются последовательно.
 * Первая метка замениться первым параметром,
 * вторая - вторым и так далее.
 * Если меток или параметров будет разное количество,
 * логгер проигнорирует метку или параметр.
 */
public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
        System.out.println();
        String name = "Tom Anderson";
        int age = 33;
        boolean isChosen = true;
        float height = 186.0F;
        char gender = 'M';

        LOG.debug("User info name: {}, age: {}, isChosen: {}, height: {}, gender: {}",
                name, age, isChosen, height, gender);
    }
}
