package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Для использования JAXB нам нужно в модели добавить дефолтные конструкторы.
 * С полей для простоты также уберем final.
 * <p>
 * Для того чтобы сериализовать и десериализовать
 * нам нужно добавить аннотации JAXB, которая даст библиотеке
 * информацию о том как парсить объект.
 * 1) Как вы уже знаете xml обязательно должен иметь корневой тег,
 * в котором все и будет располагаться.
 * Для его обозначения служит @XmlRootElement.
 * Эту аннотацию нужно ставить над сущностью,
 * которая будет корневой в нашем случае это Person
 * 2) Над вложенными сущностями нам нужно поставить просто @XmlElement
 * 3) Для того чтобы поле считалось атрибутом нужно поставить @XmlAttribute,
 * по умолчанию поле парсится как тег
 * 4) Мы можем указать также как мы хотим читать/писать объект.
 * По геттерам/сеттерам или напрямую по полям (используется рефлексия).
 * Мы будем использовать доступ по полям.
 * Для этих целей служит аннотация @XmlAccessType
 */
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
    @XmlAttribute
    private boolean sex;
    @XmlAttribute
    private int age;
    private Contact contact;
    @XmlElementWrapper(name = "statuses")
    @XmlElement(name = "status")
    private String[] statues;

    public Person() {
    }

    public Person(boolean sex, int age, Contact contact, String[] statues) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.statues = statues;
    }

    @Override
    public String toString() {
        return "Person{"
                + "sex=" + sex
                + ", age=" + age
                + ", contact=" + contact
                + ", statues=" + Arrays.toString(statues)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {

        final Person person = new Person(
                false, 30,
                new Contact("11-111"),
                new String[]{"Worker", "Married"});

        JAXBContext context = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(person, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
