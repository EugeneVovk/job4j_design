package ru.job4j.io;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Логирование - это процесс записи в файл полезной информации о работе программы.
 * Полученный файл называют лог-файлом.
 * Если приложение работает плохо, то первое, что проверяют - это лог файл.
 * "Посмотри в лог" - это значит открыть файл логирования
 * и посмотреть наличие Exception.
 * В Java есть удобная библиотека, которая позволяется решать эти вопросы.
 * Log4j - библиотека позволяет осуществить логирование процессов в приложении.
 */
public class UsageLog4j {

    private static final Logger LOG = LogManager.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }
}
