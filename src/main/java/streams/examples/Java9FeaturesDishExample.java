package streams.examples;

import streams.data.Dish;

import java.util.List;
import java.util.stream.Collectors;

public class Java9FeaturesDishExample {

    /**
     * Прекращает работу сразу после обнаружения неподходящего элемента. Рекомендовано использовать,
     * если данные отсротированы
     */
    public static List<Dish> takeWileExample(List<Dish> menu) {
        return menu.stream()
                .takeWhile(dish -> dish.getCalories() > 320)
                .collect(Collectors.toList());
    }

    /**
     * Отбрасывает первые элементы, для которых предикат ложен
     * Прекращает работу как только результат вычисления становится истинным
     */
    public static List<Dish> dropWileExample(List<Dish> menu) {
        return menu.stream()
                .takeWhile(dish -> dish.getCalories() > 320)
                .collect(Collectors.toList());
    }

}
