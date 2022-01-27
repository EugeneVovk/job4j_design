package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

/**
 * Statement - для использования SQL запросов.
 * Описание методов:
 * - createTable() – создает пустую таблицу без столбцов с указанным именем;
 * - dropTable() – удаляет таблицу по указанному имени;
 * - addColumn() – добавляет столбец в таблицу;
 * - dropColumn() – удаляет столбец из таблицы;
 * - renameColumn() – переименовывает столбец.
 */
public class TableEditor implements AutoCloseable {

    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            Class.forName(properties.getProperty("driver"));
            System.out.println("Driver connected");
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password"));
            System.out.println("Database connection established");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void statementTable(String query) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        statementTable(String.format(
                "create table if not exists %s();", tableName
        ));
    }

    public void dropTable(String tableName) {
        statementTable(String.format(
                "drop table %s;", tableName
        ));
    }

    public void addColumn(String tableName, String columnName, String type) {
        statementTable(String.format(
                "alter table %s add column %s %s;",
                tableName, columnName, type
        ));
    }

    public void dropColumn(String tableName, String columnName) {
        statementTable(String.format(
                "alter table %s drop column %s;",
                tableName, columnName
        ));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        statementTable(String.format(
                "alter table %s rename column %s to %s;",
                tableName, columnName, newColumnName
        ));
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

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("app.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            try (TableEditor table = new TableEditor(properties)) {
                table.createTable("test");
                table.addColumn("test", "id", "serial");
                table.addColumn("test", "name", "text");
                table.renameColumn("test", "name", "username");
                table.dropColumn("test", "id");
                table.dropTable("test");
                System.out.println(getTableScheme(table.connection, "test"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
