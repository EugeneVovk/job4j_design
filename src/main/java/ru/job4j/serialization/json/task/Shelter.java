package ru.job4j.serialization.json.task;

import java.util.Arrays;

public class Shelter {
    private final String name;
    private final boolean hasCat;
    private final Volunteer volunteer;
    private final int amount;
    private final String[] id;

    public Shelter(String name, boolean hasCat, Volunteer volunteer, int amount, String[] id) {
        this.name = name;
        this.hasCat = hasCat;
        this.volunteer = volunteer;
        this.amount = amount;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Shelder{"
                + "name='" + name + '\''
                + ", hasCat=" + hasCat
                + ", volunteer=" + volunteer
                + ", amount=" + amount
                + ", id=" + Arrays.toString(id)
                + '}';
    }
}
