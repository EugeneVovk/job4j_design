package ru.job4j.io;

import java.io.*;

/**
 * Частой задачей в программировании является задача преобразования одного файла в другой.
 * Представим, что у нас есть файл регистрации событий сервера.
 * Он имеет следующий формат:
 * Status Date
 * Status - может иметь 4 значения 200, 300, 400, 500
 * Date - это время проверки 10:56:01 (формат часы:минуты:секунды)
 * Например server.log
 * 200 10:56:01
 * 200 10:57:01
 * 400 10:58:01
 * 200 10:59:01
 * 500 11:01:02
 * 200 11:02:02
 * Метод main - записывает текст в файл "unavailable.csv"
 * Задание.
 * <p>
 * 1. Реализуйте метод unavailable().
 * source - путь к файлу лога.
 * target - имя путь к файлу результата анализа.
 * 2. Метод unavailable() должен находить диапазоны, когда сервер не работал.
 * Сервер не работал, если status = 400 или 500.
 * Диапазоном считается последовательность статусов 400 и 500
 * 3. Результат анализа нужно записать в файл target.
 * Формат файла
 * начала периода;конец периода;
 */
public class Analizy {

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileWriter(target))) {
            boolean isWork = true;
            while (in.ready()) {
                String str = in.readLine();
                String[] items = str.split("\\s");
                String status = items[0];
                String data = items[1];
                if (("400".contains(status) || "500".contains(status)) && isWork) {
                    out.print(data + ";");
                    isWork = false;
                }
                if (("200".contains(status) || "300".contains(status)) && !isWork) {
                    out.println(data + ";");
                    isWork = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy test = new Analizy();
        test.unavailable("./data/server.log", "unavailable.csv");
    }
}
