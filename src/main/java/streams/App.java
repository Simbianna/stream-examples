package streams;

import streams.data.Dish;

import java.util.List;

import static streams.data.Data.*;
import static streams.examples.Java9FeaturesDishExample.dropWileExample;
import static streams.examples.Java9FeaturesDishExample.takeWileExample;
import static streams.examples.FlatMapExample.flatMapIntegerExample;
import static streams.examples.FlatMapExample.flatMapStringExample;

public class App {
    private static final List<Dish> MENU = Dish.menu;

    public static void main(String[] args) {
        printList(takeWileExample(MENU));
        printList(dropWileExample(MENU));
        printList(flatMapStringExample(firstWord, secondWord));
        printList(flatMapIntegerExample(firstIntList, secondIntList));
    }

    private static <T> void printList(List<T> list) {
        list.forEach(System.out::println);
    }
}
