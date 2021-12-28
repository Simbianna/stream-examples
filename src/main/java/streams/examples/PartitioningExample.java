package streams.examples;

import streams.data.Dish;
import streams.examples.collectors.PrimeNumbersCollector;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class PartitioningExample {

    public static Map<Boolean, List<Dish>> vegPartitionedMenu(List<Dish> dishes) {
        return dishes.stream()
                .collect(partitioningBy(Dish::isVegetarian));
    }

    //Можно использовать обычную филтрацию, но премущество секционирования в том,
    //что, сохраняются оба списка (true/false)
    public static List<Dish> getPartitionedMenu(List<Dish> dishes, boolean isVegan) {
        return vegPartitionedMenu(dishes).get(isVegan);
    }

    public static Map<Boolean, Map<Dish.Type, List<Dish>>> isVegDishesByType(List<Dish> dishes) {
        return dishes.stream()
                .collect(partitioningBy(Dish::isVegetarian,
                        groupingBy(Dish::getType)));
    }

    public static Map<Boolean, Dish> mostCaloricPartitionedByVegetarian(List<Dish> dishes) {
        return dishes.stream()
                .collect(partitioningBy(Dish::isVegetarian,
                        collectingAndThen(maxBy(comparingInt(Dish::getCalories)),
                                Optional::get)));
    }

    public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(new PrimeNumbersCollector());
    }


}
