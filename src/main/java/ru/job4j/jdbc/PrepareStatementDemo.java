package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * PrepareStatement.
 * Главной отличительной чертой этого интерфейса является то,
 * что он используется для выполнения параметризованных SQL-запросов.
 * С помощью объекта типа PrepareStatement можно вставлять аргументы в запрос.
 * Во-первых, удобным образом, т.к. для вставки предназначены специальные методы.
 * Во-вторых, безопасным способом, т.е. без возможности возникновения SQL injection
 * (это когда наш код декомпилируют и меняют его,
 * тем самым появляется возможность потери и утечки данных).
 * Основная особенность объекта PrepareStatement заключается в том,
 * что при создании ему передается SQL - запрос с параметрами.
 * <p>
 * Обратите внимание. Во-первых, параметры, т.е.
 * места куда будут подставляться аргументы обозначаются «?».
 * Во-вторых, для подстановки аргументов используются
 * методы вида “setТип(позиция, аргумент)”.
 * В-третьих, позиция аргумента считается как его порядковый номер,
 * а не как индекс, т.е. позиции аргументов начинаются с 1.
 * <p>
 * Обратите внимание. Во-первых, метод update() возвращает boolean,
 * это нужно для того, чтобы узнать произошло обновление или нет.
 * Во-вторых, чтобы узнать произошло само обновление
 * мы используем метод executeUpdate(), если это метод возвращает 0,
 * то значит оно не произошло, поэтому мы проверяем, что результат больше 0.
 * <p>
 * Также важно запомнить, что методы execute(), executeUpdate()
 * и executeQuery() интерфейса PrepareStatement не принимают никаких аргументов,
 * в отличие от одноименных методов Statement.
 * Они выполняют указанный при создании объекта
 * SQL-запрос с подставленными аргументами.
 * <p>
 * Обратите внимание. Во-первых, ResultSet мы использовали вместе с try-with-resources.
 * Во-вторых, чтобы получить доступ к элементу записи используем
 * метод «getТип(имя_столбца)».
 * В-третьих, чтобы сдвинуть курсор используется метод next(),
 * если он возвращает true, то сдвиг произошел и мы можем получить данные.
 * <p>
 * Для того чтобы получить id сгенерированного БД нужно
 * при создании PrepareStatement вторым аргументом передать
 * Statement.RETURNING_GENERATED_KEYS.
 * После как обычно выполнить запрос.
 * Наконец, чтобы получить ключ нужно вызвать метод getGeneratedKeys().
 */
public class PrepareStatementDemo {
    private Connection connection;

    public PrepareStatementDemo() throws SQLException, IOException, ClassNotFoundException {
        initConnection();
    }

    private void initConnection() throws IOException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("app.properties"));
        Class.forName(properties.getProperty("driver"));
        connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("username"),
                properties.getProperty("password")
        );
    }

    /**
     * Метод вставки
     */
    public City insert(City city) {
        try (PreparedStatement statement =
                     connection.prepareStatement(
                             "insert into cities(name, population) values (?, ?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    city.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }

    /**
     * Метод обновления
     */
    public boolean update(City city) {
        boolean rsl = false;
        try (PreparedStatement statement = connection.prepareStatement(
                "update cities set name = ?, population = ? where id = ?;")) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.setInt(3, city.getId());
            rsl = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    /**
     * Метод удаления
     */
    public boolean delete(int id) {
        boolean rsl = false;
        try (PreparedStatement statement = connection.prepareStatement(
                "delete form cities where id = ?;")) {
            statement.setInt(1, id);
            rsl = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    /**
     * Метод получения всех элементов
     */
    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "select * from cities;")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(new City(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("population")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }


    public static void main(String[] args) {
        PrepareStatementDemo psd = null;
        try {
            psd = new PrepareStatementDemo();
            System.out.println("Connected");

            psd.insert(new City(1, "Warsaw", 1793579));
            psd.insert(new City(1, "Kiev", 2962180));
            psd.insert(new City(1, "Vienna", 1921153));

        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        } finally {
            assert psd != null;
            if (psd.connection != null) {
                try {
                    psd.connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
