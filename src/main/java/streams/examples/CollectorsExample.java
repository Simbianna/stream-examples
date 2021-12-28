package streams.examples;

import streams.data.Dish;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;


public class CollectorsExample {

    public static long countDishes(List<Dish> dishes) {
        return dishes.stream()
                .collect(counting());
    }

    public static Optional<Dish> gitMaxCaloriesDish(List<Dish> dishes) {
        return dishes.stream()
                .collect(maxBy(comparingInt(Dish::getCalories)));
    }

    public static long sumCalories(List<Dish> dishes) {
        return dishes.stream()
                .collect(summingInt(Dish::getCalories));
    }

    //wil return statistics: count, sum, min, average, max
    public static IntSummaryStatistics getStatistics(List<Dish> dishes) {
        return dishes.stream()
                .collect(summarizingInt(Dish::getCalories));
    }

    //.map(Dish::getName) is redundant if Dish class has toString() method
    public static String concatStrings(List<Dish> dishes) {
        return dishes.stream()
                .map(Dish::getName)
                .collect(joining(", "));
    }

    public static List<Dish> collectAll(List<Dish> dishes) {
        return dishes.stream()
                .collect(
                        ArrayList::new,
                        List::add,
                        List::addAll
                );
    }


}
