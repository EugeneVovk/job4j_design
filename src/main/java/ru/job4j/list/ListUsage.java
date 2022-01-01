package ru.job4j.list;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public class ListUsage {
    public static void main(String[] args) {
        List<String> rsl = new ArrayList<>();
        rsl.add("one");
        rsl.add("two");
        rsl.add("three");
        rsl.add(1, "one and a half");

        List<String> list = new ArrayList<>();
        list.add("four");
        list.add("five");

        rsl.addAll(list);
        /* printInfo(rsl); */

        List<String> result = List.of("uno", "dos", "tres", "cuatro");
        ListIterator<String> iterator = result.listIterator();
        while (iterator.hasNext()) {
            System.out.println("Текущий элемент: " + iterator.next());
        }

        while (iterator.hasPrevious()) {
            System.out.println("Текущий элемент: " + iterator.previous());
        }

        rsl.set(2, "two and second");
        rsl.replaceAll(String::toUpperCase);
        /* printInfo(rsl); */

        rsl.removeAll(list);
        /* rsl.retainAll(list); */
        rsl.remove(1);
        rsl.remove("FIVE");
        rsl.removeIf(s -> s.length() <= 3);
        printInfo(rsl);

        boolean b = rsl.contains("FOUR");
        System.out.println("Список содержит элемент: " + b);

        int i = rsl.indexOf("THREE");
        System.out.println("Индекс элемента в списке: " + i);

        int size = rsl.size();
        System.out.println("Размер списка равен: " + size);

        rsl.sort(Comparator.naturalOrder());
        printInfo(rsl);
    }

    public static void printInfo(List<String> list) {
        for (String s : list) {
            System.out.println("Текущий элемент: " + s);
        }
    }
}
