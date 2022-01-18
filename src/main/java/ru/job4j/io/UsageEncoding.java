package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Класс, в котором будут определены 2 метода -
 * для чтения файла и записи новых данных в него.
 * <p>
 * Наш код должен уметь читать данные из файла
 * и записывать в него новые данные.
 * При этом делать это в одной кодировке.
 * Добавим метод, который будет записывать новые данные файл
 * и при этом сразу определим для него кодировку данных для записи.
 *
 * Необходимо всегда стараться в потоках записи и чтения данных
 * задавать кодировку, чтобы не было непредвиденных результатов
 * при работе с текстовыми данными,
 * особенно если это касается кириллических символов.
 * Вы, как программист, должны беспокоиться о сохранности данных и о том,
 * чтобы их невозможно было потерять при кодировании.
 *
 * Для записи отдельно взятой строки каждый раз открывается поток вывода.
 * Так делать НЕ стоит, т.к. открытие потоков ввода/вывода тяжелая операция,
 * как и в целом работа с ресурсами (работы файловой системой, сетью, базой и т.д.).
 * Поэтому по возможности стоит хранить данные в памяти
 * и только при необходимости работать с ресурсами:
 * data.forEach(pw::println);
 */
public class UsageEncoding {

    public String readFile(String path) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(
                new FileReader(path, Charset.forName("WINDOWS-1251")))) {
            in.lines().forEach(e -> builder.append(e).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public void writeDataInFile(String path, String data) {
        try (PrintWriter out = new PrintWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            out.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "./src/data/text2.txt";
        UsageEncoding encoding = new UsageEncoding();
        List<String> strs = List.of(
                "Новая строка 1",
                "Новая строка 2",
                "Новая строка 3",
                "Новая строка 4",
                "Новая строка 5");
        for (String s : strs) {
            encoding.writeDataInFile(path, s);
        }
        String str = encoding.readFile(path);
        System.out.println("Данные из файла: ");
        System.out.println(str);
    }
}
