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
 * <p>
 * Запомните правило, если в проекте используется логгер,
 * то для вывода ошибок или отладочной информации нужно использовать только логгер.
 * Придерживаетесь единого стиля во всем проекте.
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
        byte team = 1;
        short someNum = -32000;
        long someBigNum = 9223372036854775807L;
        double tomMemory = 1.07e+15;
        LOG.debug("User info name: {}, age: {}, isChosen: {}, height: {}, gender: {},"
                        + " team: {}, someNum: {}, someBigNum: {}, tomMemory: {}",
                name, age, isChosen, height, gender, team, someNum, someBigNum, tomMemory);
        try {
            throw new Exception("Not support code");
        } catch (Exception e) {
            LOG.error("Exception in log example", e);
        }
    }
}
