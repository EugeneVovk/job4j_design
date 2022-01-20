package ru.job4j.serialization.json.task.json;

public class Cat {
    private final String name;
    private final int age;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Cat{"
                + "name='" + name + '\''
                + ", age=" + age
                + '}';
    }
}
