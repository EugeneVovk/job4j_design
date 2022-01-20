package ru.job4j.serialization.json.task;

public class Volunteer {
    private String name;

    public Volunteer(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Volunteer{"
                + "name='" + name + '\''
                + '}';
    }
}
