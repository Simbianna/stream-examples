package streams.examples;

import streams.data.Dish;

import java.util.*;

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

    public static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel(List<Dish> dishes) {
        return dishes.stream()
                .collect(
                        groupingBy(Dish::getType,
                                groupingBy(d ->
                                {
                                    if (d.getCalories() <= 400) return CaloricLevel.DIET;
                                    else if (d.getCalories() <= 70) return CaloricLevel.NORMAL;
                                    else return CaloricLevel.FAT;
                                }))
                );
    }

    public static Map<Dish.Type, Dish> mostCaloricByType(List<Dish> dishes) {
        return dishes.stream()
                .collect(
                        groupingBy(Dish::getType,
                                collectingAndThen(
                                        maxBy(Comparator.comparingInt(Dish::getCalories)),
                                        Optional::get
                                ))
                );
    }

    public static Map<Dish.Type, Integer> totalCaloriesByType(List<Dish> dishes) {
        return dishes.stream()
                .collect(groupingBy(Dish::getType,
                        summingInt(Dish::getCalories)));
    }

    public static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType(List<Dish> dishes) {
        return dishes.stream()
                .collect(
                        groupingBy(Dish::getType,
                                mapping(d ->
                                        {
                                            if (d.getCalories() <= 400) return CaloricLevel.DIET;
                                            else if (d.getCalories() <= 70) return CaloricLevel.NORMAL;
                                            else return CaloricLevel.FAT;
                                        },
                                        toSet()))
                );
    }

    public static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByTypeHashSet(List<Dish> dishes) {
        return dishes.stream()
                .collect(
                        groupingBy(Dish::getType,
                                mapping(d ->
                                        {
                                            if (d.getCalories() <= 400) return CaloricLevel.DIET;
                                            else if (d.getCalories() <= 70) return CaloricLevel.NORMAL;
                                            else return CaloricLevel.FAT;
                                        },
                                       toCollection(HashSet::new)))
                );
    }

}
