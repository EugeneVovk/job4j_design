package ru.job4j.serialization.json.task;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Задание:
 * 1) Придумайте Java объект, объект должен иметь поля булево,
 * какой-нибудь числовой тип, строковый тип, вложенный объект и массив.
 * 2) Опишите его представление в формате JSON в комментарии к заданию.
 * 3) Подключите библиотеку Gson, преобразуйте объект вашего класса
 * в json-строку и обратно.
 * 4) Оставьте ссылку на коммит.
 */
public class Test {

    public static void main(String[] args) {
        final Shelter shelter = new Shelter("Meow Foundation", true,
                new Volunteer("John"), 10,
                new String[]{"Simba", "2"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(shelter));

        final String shelterJson = "{"
                + "\"name\":\"Fluffy Cats\","
                + "\"hasCat\":true,"
                + "\"volunteer\":"
                + "{"
                + "\"name\":\"Ann\""
                + "},"
                + "\"amount\":20,"
                + "\"id\":"
                + "[\"Smokey\",\"9\"]"
                + "}";
        final Shelter shelterMod = gson.fromJson(shelterJson, Shelter.class);
        System.out.println(shelterMod);
    }
}
