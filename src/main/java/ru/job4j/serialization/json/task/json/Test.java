package ru.job4j.serialization.json.task.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Задание:
 * Преобразуйте объекты в JSONObject и json-строку
 * с помощью библиотеки JSON-Java (org.json).
 */
public class Test {

    public static void main(String[] args) {

        JSONObject volunteer = new JSONObject("{\"name\":\"Ann\"}");
        List<String> list = new ArrayList<>();
        list.add("Simba");
        list.add("2");
        final JSONArray jsonIDs = new JSONArray(list);
        final Shelter shelter = new Shelter("Fluffy Cats", true,
                new Volunteer("Saule"), 2,
                new String[]{"Smokey", "9"});
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", shelter.getName());
        jsonObject.put("hasCat", shelter.isHasCat());
        jsonObject.put("volunteer", volunteer);
        jsonObject.put("amount", shelter.getAmount());
        jsonObject.put("id", jsonIDs);
        System.out.println(jsonObject);
        System.out.println(new JSONObject(shelter));
    }
}
