package ru.job4j.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.StringJoiner;

/**
 * Рассмотрим, как производить различные операции.
 * Для исполнения операций существуют специальные интерфейсы:
 * Statement, PrepareStatement.
 * Для его выполнения запросов существуют 3 метода:
 * <p>
 * 1. execute() - метод для выполнения любых команд.
 * Возвращает true, если результатом выполнения является ResultSet
 * (то есть был выполнен SELECT запрос) , или false,
 * если результатом является int (количество изменённых строк).
 * Получить ResultSet или количество строк мы можем с помощью
 * последующего вызова getUpdateCount() или getResultSet().
 * <p>
 * 2. executeUpdate() - этот метод используется как для выполнения
 * операторов управления данными (DML - операторы),
 * например INSERT, UPDATE или DELETE, так и для операторов
 * определения структуры базы данных (DDL - операторы),
 * например CREATE TABLE, DROP TABLE.
 * Возвращает int – количество affected строк, т.е.
 * количество строк на которые оказал влияние запрос.
 * Для операторов, которые не манипулируют строками,
 * таких как CREATE TABLE или DROP TABLE,
 * возвращаемое значение executeUpdate всегда равно нулю.
 * <p>
 * 3. executeQuery() - этот метод используется для выполнения
 * операции SELECT и возвращает объект ResultSet,
 * который позволяет пройтись по результатам запроса.
 */
public class StatementDemo {
    public static void main(String[] args) throws Exception {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "create table if not exists demo_table(%s, %s);",
                        "id serial primary key",
                        "name text"
                );
                statement.execute(sql);
                System.out.println(getTableScheme(connection, "demo_table"));
            }
        }
    }

    private static Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/idea_db";
        String login = "postgres";
        String password = "root";
        return DriverManager.getConnection(url, login, password);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }
}
