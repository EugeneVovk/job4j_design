package ru.job4j.serialization.json.task.json;

public class Volunteer {
    private String name;

    public Volunteer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Volunteer{"
                + "name='" + name + '\''
                + '}';
    }
}
