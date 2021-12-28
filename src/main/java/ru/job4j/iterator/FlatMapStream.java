package ru.job4j.iterator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * В Stream API есть метод flatMap.
 * Он позволяет получить из элемента потока другой поток.
 */
public class FlatMapStream {
    public static void main(String[] args) {
        List<List<Integer>> data = List.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6),
                List.of(7, 8, 9)
        );
        List<Integer> flat =
                data.stream()
                        .flatMap(List::stream)
                        .collect(Collectors.toList());
        System.out.println(flat);
    }
}
