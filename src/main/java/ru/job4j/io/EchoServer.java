package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Сервер.
 * Сначала нам нужно создать сервер.
 * Вызов конструктора ServerSocket создает серверный сокет,
 * привязанный к указанному порту. Чтобы клиент мог узнать,
 * где находится сервер ему нужен адрес и порт, 9000 - это порт.
 * По умолчанию адрес будет localhost.
 * Вызов метода accept() заставляет программу ждать подключений
 * по указанному порту, работа программы продолжится только
 * после подключения клиента. После успешного подключения
 * метод возвращает объект Socket, который используется
 * для взаимодействия с клиентом.
 * С помощью объекта Socket программа может получить
 * входной поток и может отправить данные в выходной поток.
 * В ответ мы записываем строчку.
 * В программе читается весь входной поток(цикл for).
 * После чтения отправляем ответ окончательно.
 * Сервер работает, пока его принудительно не закроют.
 * Метод ассеpt принимает один запрос от клиента,
 * чтобы отправить второй, программа должна снова получить объект socket.
 * <p>
 * Curl — это сокращение от “Client URL”.
 * Это утилита командной строки, которая позволяет выполнять HTTP-запросы
 * с различными параметрами и методами.
 * Вместо того чтобы переходить к веб-ресурсам в адресной строке браузера,
 * можно использовать командную строку, чтобы получить те же ресурсы,
 * извлеченные в виде текста.
 * Параметр -i указывает curl вывести всю информацию принятую от сервера.
 * Параметр http://localhost:9000/?msg=Hello - это адрес.
 * Он состоит из протокола http. Адреса localhost. Порта 9000.
 * Параметров запроса /?msg=Hello.
 * <p>
 * На стороне сервера эти параметры можно получить.
 * Первая строчка содержит параметры запроса.
 * <p>
 * Если клиент отправляет запрос http://localhost:9000/?msg=Bye
 * нужно завершить работу сервера.
 */
public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write("Hello, dear friend.".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        if (str.contains("msg=Hello")) {
                            out.write("Hello".getBytes());
                        } else if (str.contains("msg=Exit")) {
                            server.close();
                        } else if (str.contains("msg=Any")) {
                            out.write("What".getBytes());
                        }
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("Error", e);
        }
    }
}
