package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * JSON (JavaScript Object Notation) – текстовый формат обмена данными,
 * основан на синтаксисе JavaScript, удобен для написания и чтения человеком.
 * Несмотря на происхождение от JavaScript формат независим от него
 * и может использоваться практически с любым языком программирования,
 * для многих из которых существуют готовые библиотеки для создания
 * и обработки данных в формате JSON.
 */
public class MainJson {
    public static void main(String[] args) {
        final Person person = new Person(
                false, 30,
                new Contact("11-111"),
                new String[]{"Worker", "Married"});

        /* Преобразуем объект person в json-строку */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));

        /* Модифицируем json-строку */
        final String personJson = "{"
                + "\"sex\":false,"
                + "\"age\":35,"
                + "\"contact\":"
                + "{"
                + "\"phone\":\"+7(924)111-111-11-11\""
                + "},"
                + "\"statuses\":"
                + "[\"Student\",\"Free\"]"
                + "}";
        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);
    }
}
