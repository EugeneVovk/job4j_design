package ru.job4j.serialization.json.task.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "volunteer")
public class Volunteer {
    @XmlAttribute
    private String name;

    public Volunteer() {
    }

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
