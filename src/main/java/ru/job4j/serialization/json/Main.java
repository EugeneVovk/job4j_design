package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * JSON-Java (org.json) легковесная функциональная библиотека для работы с JSON,
 * которая дополнительно умеет преобразовывать JSON в XML, HTTP header, Cookies и др.
 * В отличие от Jackson или Gson, JSON-Java преобразует json-строку
 * не в объект пользовательского класса (способ Data bind),
 * а в объекты своей библиотеки JSONObject, JSONArray (способ Tree Model).
 */
public class Main {
    public static void main(String[] args) {

        /* JSONObject из json-строки строки */
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        final JSONArray jsonStatuses = new JSONArray(list);
        final Person person = new Person(
                false, 30,
                new Contact("11-111"),
                new String[]{"Worker", "Married"});

        /* JSONObject напрямую методом put */
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", person.isSex());
        jsonObject.put("age", person.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(person).toString());
    }
}
