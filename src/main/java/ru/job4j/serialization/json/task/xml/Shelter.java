package ru.job4j.serialization.json.task.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "shelter")
@XmlAccessorType(XmlAccessType.FIELD)
public class Shelter {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private boolean hasCat;
    private Volunteer volunteer;
    @XmlAttribute
    private int amount;
    @XmlElementWrapper(name = "ids")
    @XmlElement(name = "id")
    private String[] id;

    public Shelter() {
    }

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
