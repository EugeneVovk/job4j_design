package ru.job4j.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 1. У нас появился клиент, который хочет создать базу данных для спамеров.
 * На рынке ему продали диск, в котором находятся txt файлы.
 * Формат данных dump.txt:
 * Petr Arsentev;parsentev@yandex.ru;
 * Ivan Ivanov;iivanov@gmail.com;
 * Клиент просит перевести эти файлы в базу данных PostgreSQL.
 */
public class ImportDB {

    private final Properties properties;
    private final String dump;

    public ImportDB(Properties properties, String dump) {
        this.properties = properties;
        this.dump = dump;
    }

    public List<User> load() {
        List<User> users = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(dump))) {
            in.lines()
                    .map(s -> s.split(";"))
                    .forEach(strings -> {
                        if (strings.length != 2 || strings[0].isEmpty() || strings[1].isEmpty()) {
                            throw new IllegalArgumentException("Incorrect incoming data");
                        }
                        users.add(new User(strings[0], strings[1]));
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("jdbc.driver"));
        try (Connection connection = DriverManager.getConnection(
                properties.getProperty("jdbc.url"),
                properties.getProperty("jdbc.username"),
                properties.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = connection.prepareStatement(
                        "insert into users (name, email) values (?, ?);")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (FileInputStream in = new FileInputStream("./app3.properties")) {
            properties.load(in);
        }
        ImportDB db = new ImportDB(properties, "./dump.txt");
        db.save(db.load());
    }
}
