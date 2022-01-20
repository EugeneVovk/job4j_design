package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * POJO (Plain Old Java Object) — «старый добрый Java-объект», простой Java-объект.
 * Термин впервые начал употребляться Мартином Фаулером и его коллегами
 * как результат поиска способов упрощения разработки. Нет формального определения,
 * какие объекты являются POJO, обычно руководствуются следующими соглашениями для класса:
 * - не наследуется от других классов (возможно, кроме POJO-классов того же пакета)
 * - не реализует интерфейсов (иногда делается исключение для маркерных интерфейсов
 * из стандартной библиотеки, или тех, которые нужны для бизнес-модели),
 * - не использует аннотаций в определениях
 * - не зависит от сторонних библиотек.
 * Иногда под POJO подразумевают JavaBean, но JavaBeans имеют более строгие ограничения.
 * <p>
 * Для использования JAXB нам нужно в модели добавить дефолтные конструкторы.
 * С полей для простоты также уберем final.
 * <p>
 * Для того чтобы сериализовать и десериализовать нам нужно добавить аннотации JAXB,
 * которая даст библиотеке информацию о том как парсить объект.
 * 1) Как вы уже знаете xml обязательно должен иметь корневой тег,
 * в котором все и будет располагаться.
 * Для его обозначения служит @XmlRootElement. Эту аннотацию нужно ставить над сущностью,
 * которая будет корневой в нашем случае это Person
 * 2) Над вложенными сущностями нам нужно поставить просто @XmlElement
 * 3) Для того чтобы поле считалось атрибутом нужно поставить  @XmlAttribute,
 * по умолчанию поле парсится как тег
 * 4) Мы можем указать также как мы хотим читать/писать объект.
 * По геттерам/сеттерам или напрямую по полям (используется рефлексия).
 * Мы будем использовать доступ по полям.
 * Для этих целей служит аннотация @XmlAccessType
 * Чтобы выводилось так как мы хотим мы можем
 * переименовать элемент statuses в status и использовать тег @XmlElementWrapper
 */
public class MainXml {
    public static void main(String[] args) throws JAXBException, IOException {
        final Person person = new Person(
                false, 30,
                new Contact("11-111"),
                new String[]{"Worker", "Married"});
        /* Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(Person.class);
        /* Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();
        /* Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(person, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /* десериализуем */
            Person rsl = (Person) unmarshaller.unmarshal(reader);
            System.out.println(rsl);
        }
    }
}
