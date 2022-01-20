package ru.job4j.serialization.json.task.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Задание:
 * Сущность созданную прошлом задании сериализовать/десериализовать
 * с помощью JAXB
 */
public class Test {

    public static void main(String[] args) throws JAXBException, IOException {
        final Shelter shelter = new Shelter("Meow Foundation", true,
                new Volunteer("John"), 10,
                new String[]{"Simba", "2"});
        JAXBContext context = JAXBContext.newInstance(Shelter.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(shelter, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (final StringReader reader = new StringReader(xml)) {
            Shelter rsl = (Shelter) unmarshaller.unmarshal(reader);
            System.out.println(rsl);
        }
    }
}
