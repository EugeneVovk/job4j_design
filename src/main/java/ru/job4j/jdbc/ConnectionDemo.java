package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JDBC – это API, т.е. набор вспомогательных классов,
 * которое позволяет работать с базами данных.
 * Причем JDBC предоставляет единый интерфейс для работы с ними,
 * ведь бывают различные базы данных.
 * Драйвер – это то, что позволяет работать с бд
 * (поддерживать подключения, выполнять запросы и т.д.).
 * Чтобы добавить драйвер в проект необходимо добавить зависимость
 * на этот самый драйвер.
 * forName загружает класс в память,
 * при загрузке класса в память в нём срабатывает статический блок инициализации
 * и он регистрирует себя в драйвер-менеджере, т.е. загружаем драйвер.
 * Далее подключаться к БД, открываем соединение.
 * Для подключения нам нужны url, логин (имя пользователя) и пароль.
 * Для подключения нужно воспользоваться классом DriverManager, передав ему эти аргументы.
 * Обратите внимание, что в url стоит префикс “jdbc:postgres” =>
 * мы подключаемся к postgres через jdbc.
 * Далее как обычно идет хост и порт, а за ними уже имя базы данных.
 * Если объект типа Connection не равен null, то
 * установлено подключение и теперь мы можем выполнять запросы к базе данных.
 * <p>
 * Давайте получим имя пользователя и url, а затем их выведем.
 * Для получения информации о БД и ее внутренней структуре
 * существует класс DatabaseMetaData.
 * Через него мы можем получить нужные данные.
 * <p>
 * Доработайте код программы чтобы чтение url,
 * имени пользователя и пароля происходило из файла app.properties.
 * Для чтения используйте класс Config,
 */
public class ConnectionDemo {
    public static void main(String[] args) {
        Config config = new Config("app.properties");
        config.load();
        try {
            Class.forName(config.value("driver"));
            System.out.println("Driver connected");
            String url = config.value("url");
            String login = config.value("username");
            String password = config.value("password");
            try (Connection connect = DriverManager.getConnection(url, login, password)) {
                System.out.println("Database connection established");
                DatabaseMetaData metaData = connect.getMetaData();
                System.out.println(metaData.getUserName());
                System.out.println(metaData.getURL());
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
