package streams.exapmles;

import streams.data.Dish;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class CollectorsGroupingExample {

    public enum CaloricLevel {DIET, NORMAL, FAT}

    public static Map<Dish.Type, List<Dish>> groupDishesByType(List<Dish> dishes) {
        return dishes.stream()
                .collect(groupingBy(Dish::getType));
    }

    public static Map<CaloricLevel, List<Dish>> groupDishesByCaloricLevel(List<Dish> dishes) {
        return dishes.stream()
                .collect(groupingBy(d ->
                {
                    if (d.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (d.getCalories() <= 70) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }));

    }

    //filtering & grouping
    public static Map<Dish.Type, List<Dish>> caloricDishesByType(List<Dish> dishes, long cal) {
        return dishes.stream()
                .collect(groupingBy(Dish::getType,
                        filtering(d -> d.getCalories() > cal, toList())));
    }

    // grouping & transforming
    public static Map<Dish.Type, List<String>> dishNamesByType(List<Dish> dishes) {
        return dishes.stream()
                .collect(groupingBy(Dish::getType,
                        mapping(Dish::getName, toList())));
    }

}
