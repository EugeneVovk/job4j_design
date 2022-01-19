package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * В этом задании необходимо создать программу 'Консольный чат'.
 * Некоторое описание:
 * - пользователь вводит слово-фразу,
 * программа берет случайную фразу из текстового файла и выводит в ответ.
 * - программа замолкает если пользователь вводит слово «стоп»,
 * при этом он может продолжать отправлять сообщения в чат.
 * - если пользователь вводит слово «продолжить»,
 * программа снова начинает отвечать.
 * - при вводе слова «закончить» программа прекращает работу.
 * - запись диалога, включая слова-команды стоп/продолжить/закончить
 * должны быть записаны в текстовый лог.
 * Класс принимает в конструктор 2 параметра - имя файла,
 * в который будет записан весь диалог между ботом и пользователем,
 * и имя файла, в котором находятся строки с ответами,
 * которые будет использовать бот.
 * Вам нужно реализовать методы:
 * - run(), содержит логику чата;
 * - readPhrases(), читает фразы из файла;
 * - saveLog(), сохраняет лог чата в файл.
 */
public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> text = readPhrases();
        List<String> botLog = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String say = "";
            System.out.println("""
                    Тебе доступны лишь три команды:
                    \t- закончить
                    \t- стоп
                    \t- продолжить
                    Хочешь цитату?""");
            while (!say.equals(OUT)) {
                say = in.readLine();
                if (say.equals(STOP)) {
                    continue;
                } else if (say.equals(CONTINUE)) {
                    String random = text.get(new Random().nextInt(text.size()));
                    System.out.println(random);
                    botLog.add(random);
                }
            }
            saveLog(botLog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readPhrases() {
        List<String> text = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(
                new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            in.lines()
                    .map(s -> s.split("\n"))
                    .forEach(s -> text.add(s[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "./src/data/bot.txt";
        String botAnswers = "./src/data/Quotes.txt";
        ConsoleChat cc = new ConsoleChat(path, botAnswers);
        cc.run();
    }
}
