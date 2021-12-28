package streams.examples;

import streams.data.Dish;

import java.util.List;
import java.util.Optional;

public class MatchFindExample {

    public Optional<Dish> findAnyExample(List<Dish> menu) {
        return menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
    }

    public Optional<Dish> findFirstExample(List<Dish> menu) {
        return menu.stream()
                .filter(Dish::isVegetarian)
                .findFirst();
    }

    /**
     * Нижеприведенные примеры используют то, что обычно называется сокращенногой схемой вычисения (|| &&)
     */
    public boolean anyMatchExample(List<Dish> menu) {
        return menu.stream()
                .anyMatch(dish -> dish.getCalories() < 1000);
    }

    public boolean allMatchExample(List<Dish> menu) {
        return menu.stream()
                .allMatch(dish -> dish.getCalories() < 1000);
    }

    public boolean noneMatchExample(List<Dish> menu) {
        return menu.stream()
                .noneMatch(dish -> dish.getCalories() < 1000);
    }
}
